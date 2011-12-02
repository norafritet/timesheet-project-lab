package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.service.ActivityManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import mockit.*;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.jdbc.Expectation;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 11:26 AM
 */
public class InquiryFormTest {
    
    @Tested private InquiryForm inquiryForm;
    @Mocked private InquiryForm inForm;
    private String username;
    private String fullname;
    @Mocked private HttpServletRequest request;
    @Mocked private HttpServletResponse response;
    @Mocked private User user;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private ServletOutputStream servletOutputStream;
    @Mocked private FacesContext facesContext ;
    @Mocked private UserManager userManager;
    @Mocked private ActivityManager activityManager;
    private Long userId=(long)13;
    private JRDataSource dataSource ;
    
    @Test
    public void getSpecificYears() throws Exception
    {

       InquiryForm iform = new InquiryForm();
       
       //iform.view();
       //Assert.assertNotNull(iform.view());

    }


     private void context_getWeeks() throws Exception{
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
     }



    @Test
     // Declare the mocks you need through mock fields or parameters.
    public void runOnceGetNumOfWeekOfMonth() throws Exception
    {


        context_getWeeks();



        /*
      // Record the desired results for method invocations, if any are needed.
        new NonStrictExpectations() {
         // An internal (encapsulated) dependency can be mocked as well:
         AnotherDependency anotherMock;

         {
            anotherMock.doSomething("test"); result = 123;
         }
      };

      // Exercise the code under test.
      new ServiceAbc(mock).doOperation("some data");
       */
      // Verify expected invocations, if any.
      inquiryForm.getWeeks();
      new Verifications()
      {
          {
            inForm.getNumOfWeekOfMonth(); times = 0;
          }
      };
   }

   @Test
   public void getWeeks_NotAllMonth_Call$getNumOfWeekOfMonthOnce(final InquiryForm yourform) throws Exception
   {
      new NonStrictExpectations(inquiryForm)
      {
          {

              inquiryForm.getNumOfWeekOfMonth(); result=4 ;
          }
      };
       inquiryForm.setYear("2011");
       inquiryForm.setMonth("1");

       inquiryForm.getWeeks();

       new Verifications(){{inquiryForm.getNumOfWeekOfMonth(); maxTimes=1;}};
   }
     @Test
   public void getWeeks_MonthHaveFourWeek_ReturnFiveString(final InquiryForm yourform) throws Exception
   {
      new NonStrictExpectations(inquiryForm)
      {
          {

              inquiryForm.getNumOfWeekOfMonth(); result=4 ;
          }
      };
       inquiryForm.setYear("2011");
       inquiryForm.setMonth("1");
       List<String> expected = Arrays.asList("All","1","2","3","4");

       List result= inquiryForm.getWeeks();
       Assert.assertEquals(expected,result);

   }
    @Test
    public void getAvailableWeekBasedOnMonth() throws Exception
    {

        InquiryForm iform = new InquiryForm();
       iform.setMonth("02");
        iform.setYear("2011");
         iform.getWeeks()  ;


        /*
         SimpleDateFormat sdf;
         Calendar cal;
         Date date;
         int week;
         String sample = "12/25/1979";
         sdf = new SimpleDateFormat("MM/dd/yyyy");
         date = sdf.parse(sample);
         cal = Calendar.getInstance();
         cal.setTime(date);

         week = cal.get( Calendar.WEEK_OF_MONTH );
         */
        //iform.getWeeksOfMonth();



    }

    @Test
    public void getResourceInputStream_InquiryActivityJasper_notNull(){
        
        InputStream inputStream = JRLoader.getResourceInputStream("InquiryActivity.jasper");
        Assert.assertNotNull(inputStream);
    }
    
    private void context_download_SecondWeekOfJanuaryEleven() throws Exception{
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
        
        bos = new ByteArrayOutputStream();
        servletOutputStream = new ServletOutputStream(){

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user;
                user.getFullName(); result=fullname;
                user.getId();result=userId;
                activityManager.getJRDataSourceActivity(userId, withInstanceLike(new java.sql.Date(1)), withInstanceLike(new java.sql.Date(1))); result=dataSource;
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };
    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_SendDataFileNameOfAttacment() throws Exception{
        
        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");times =1; }};
    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Send$flushBuffer( ) throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.flushBuffer();times =1; }};

    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_SenddataContentType$setContentType( ) throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.setContentType("application/vnd.ms-excel");times =1; }};
    }
 
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Call$reset() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.reset();times =1; }};
    }
    
  
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Call$write() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        Assert.assertTrue(bos.size()>0);
    }
    
    @Test
    public void download_SecondWeekOfJanuaryEleven_Call$responseComplete() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{facesContext.responseComplete(); times=1; }};
    }
    
    @Test
    public void download_SecondWeekOfJanuaryEleven_Call$getFullName() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{user.getFullName(); times=1; }};
    }
    
    @Test
    public void download_secondWeekOfFebruaryElevent_SendDataFileNameOfAttacment()throws Exception{
    
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("2");
        inquiryForm.setWeek("2");
        
//        bos = new ByteArrayOutputStream();
//        servletOutputStream = new ServletOutputStream(){
//
//            @Override
//            public void write(int b) throws IOException {
//                bos.write(b);
//            }
//        };
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        final JRDataSource dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            ServletOutputStream servletOutputStream;
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user;
                user.getFullName(); result=fullname;
                inquiryForm.getResponse(); result =response;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Feb11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Feb11_Agus Muhammad Ramdan.xls\"");times =1; }};

    }
    @Test
    public void download_AllWeekOfFebruaryElevent_SendDataFileNameOfAttacment()throws Exception{
    
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("2");
        inquiryForm.setWeek("All");
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        final JRDataSource dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            ServletOutputStream servletOutputStream;
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user;
                user.getFullName(); result=fullname;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_Feb11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_Feb11_Agus Muhammad Ramdan.xls\"");times =1; }};

    }
    
    @Test
    public void download_AllMonthOfElevent_SendDataFileNameOfAttacment()throws Exception{
    
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("All");
        inquiryForm.setWeek("1");
        
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        final JRDataSource dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            ServletOutputStream servletOutputStream;
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user;
                user.getFullName(); result=fullname;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_All11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_All11_Agus Muhammad Ramdan.xls\"");times =1; }};
    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Call$getJRDataSourceActivity() throws Exception{
        
        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{activityManager.getJRDataSourceActivity(userId, withInstanceLike(new java.sql.Date(1)), withInstanceLike(new java.sql.Date(1)));  times =1; }};
    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_ContaintInPrametersWhenCallJRFiller$fillReport(
        final @Mocked JasperReport jasperReport ) throws Exception{
        final Map captureContentParameter = new HashMap();

        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
        
        bos = new ByteArrayOutputStream();
        servletOutputStream = new ServletOutputStream(){

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm,JRFiller.class){
            JasperPrint jasperPrint;
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user;
                user.getFullName(); result=fullname;
                user.getId();result=userId;
                activityManager.getJRDataSourceActivity(userId, withInstanceLike(new java.sql.Date(1)), withInstanceLike(new java.sql.Date(1))); result=dataSource;
                inquiryForm.generateJasperReportInquiryActivity(withInstanceLike(new HashMap()),dataSource);
                result = new Delegate(){
                    @Mock 
                    JasperPrint generateJasperReportInquiryActivity(
                    Map<String,Object> parameters,
                    JRDataSource dataSource
                    ) throws JRException{
                        captureContentParameter.putAll(parameters);
                        return jasperPrint;
                    }
                };
                inquiryForm.writeExportedExcelToResponse(anyString, jasperPrint);
            }
        };
        
        inquiryForm.download();
        Assert.assertEquals(fullname,captureContentParameter.get("USER_FULLNAME"));
    }
}