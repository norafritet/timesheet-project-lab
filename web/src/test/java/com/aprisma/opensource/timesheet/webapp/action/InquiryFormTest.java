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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.junit.Ignore;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 11:26 AM
 */
public class InquiryFormTest {
    
    @Tested private InquiryForm inquiryForm;
    @Capturing JRXlsExporter jrxe;
    private String username;
    private String fullname;
    private String filename;
    @Mocked private HttpServletRequest request;
    @Mocked private HttpServletResponse response;
    @Mocked private User user;
    @Mocked private ServletOutputStream servletOutputStream;
    @Mocked private FacesContext facesContext ;
    @Mocked private UserManager userManager;
    @Mocked private ActivityManager activityManager;
    private Long userId=(long)13;
    private JRDataSource dataSource ;
    @Mocked private JasperPrint jasperPrint;
 
//    @Test
//    public void getSpecificYears() throws Exception
//    {
//
//       InquiryForm iform = new InquiryForm();
//       
//       //iform.view();
//       //Assert.assertNotNull(iform.view());
//
//    }


//     private void context_getWeeks() throws Exception{
//        inquiryForm.setYear("2011");
//        inquiryForm.setMonth("1");
//        inquiryForm.setWeek("2");
//     }

//    @Test
//     // Declare the mocks you need through mock fields or parameters.
//    public void runOnceGetNumOfWeekOfMonth() throws Exception
//    {
//
//
//        context_getWeeks();
//
//        /*
//      // Record the desired results for method invocations, if any are needed.
//        new NonStrictExpectations() {
//         // An internal (encapsulated) dependency can be mocked as well:
//         AnotherDependency anotherMock;
//
//         {
//            anotherMock.doSomething("test"); result = 123;
//         }
//      };
//
//      // Exercise the code under test.
//      new ServiceAbc(mock).doOperation("some data");
//       */
//      // Verify expected invocations, if any.
//      inquiryForm.getWeeks();
//      new Verifications()
//      {
//          {
//            inForm.getNumOfWeekOfMonth(); times = 0;
//          }
//      };
//   }

   @Test
   public void getWeeks_NotAllMonth_Call$getNumOfWeekOfMonthOnce(final InquiryForm yourform) throws Exception
   {
      new NonStrictExpectations(inquiryForm){{inquiryForm.getNumOfWeekOfMonth(); result=4 ;}};
       inquiryForm.setYear("2011");
       inquiryForm.setMonth("1");

       inquiryForm.getWeeks();
       new Verifications(){{inquiryForm.getNumOfWeekOfMonth(); maxTimes=1;}};
   } 
   
   @Test
   public void getWeeks_MonthHaveFourWeek_ReturnFiveString() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("1");
      new NonStrictExpectations(inquiryForm){{inquiryForm.getNumOfWeekOfMonth(); result=4 ;}};

       List<String> expected = Arrays.asList("All","1","2","3","4");

       List result= inquiryForm.getWeeks();
       Assert.assertEquals(expected,result);

   }
   
   @Test
   public void getWeeks_MonthHaveFiveWeek_ReturnSixString() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("1");
      new NonStrictExpectations(inquiryForm){{inquiryForm.getNumOfWeekOfMonth(); result=5 ;}};
      List<String> expected = Arrays.asList("All","1","2","3","4","5");

      List result= inquiryForm.getWeeks();
      Assert.assertEquals(expected,result);
   } 
   
   @Test
   public void getWeeks_MonthHaveSixWeek_ReturnSevenString() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("1");
      new NonStrictExpectations(inquiryForm){{inquiryForm.getNumOfWeekOfMonth(); result=6 ;}};
      List<String> expected = Arrays.asList("All","1","2","3","4","5","6");

      List result= inquiryForm.getWeeks();
      Assert.assertEquals(expected,result);
   }
       
   @Test
   public void getWeeks_MonthALL_ReturnAllString() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("All");
      //new NonStrictExpectations(inquiryForm){{inquiryForm.getNumOfWeekOfMonth(); result=6 ;}};
      List<String> expected = Arrays.asList("All");

      List result= inquiryForm.getWeeks();
      Assert.assertEquals(expected,result);
   }
    
   @Test
   public void getNumOfWeekOfMonth_MonthHaveFourWeek_ReturnFour() throws Exception
   {
      inquiryForm.setYear("2009");
      inquiryForm.setMonth("2");

      int result= inquiryForm.getNumOfWeekOfMonth();
      Assert.assertEquals(4,result);
   } 
   
   @Test
   public void getNumOfWeekOfMonth_MonthHaveFiveWeek_ReturnFive() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("12");

      int result= inquiryForm.getNumOfWeekOfMonth();
      Assert.assertEquals(5,result);
   } 
   
   @Test
   public void getNumOfWeekOfMonth_MonthHaveSixWeek_ReturnSix() throws Exception
   {
      inquiryForm.setYear("2011");
      inquiryForm.setMonth("10");

      int result= inquiryForm.getNumOfWeekOfMonth();
      Assert.assertEquals(6,result);
   }    
//    @Test
//    public void getAvailableWeekBasedOnMonth() throws Exception
//    {
//
//        InquiryForm iform = new InquiryForm();
//       iform.setMonth("02");
//        iform.setYear("2011");
//         iform.getWeeks()  ;
//
//
//        /*
//         SimpleDateFormat sdf;
//         Calendar cal;
//         Date date;
//         int week;
//         String sample = "12/25/1979";
//         sdf = new SimpleDateFormat("MM/dd/yyyy");
//         date = sdf.parse(sample);
//         cal = Calendar.getInstance();
//         cal.setTime(date);
//
//         week = cal.get( Calendar.WEEK_OF_MONTH );
//         */
//        //iform.getWeeksOfMonth();
//
//
//
//    }
   
    @Test
    public void convertYearMonthWeek_MonthNotAllWeekIsAll_intWeekIsNegativeOne(){
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("All");
        
        inquiryForm.convertYearMonthWeek();
        Assert.assertEquals(-1, Deencapsulation.getField(inquiryForm, "intWeek"));
    }
    
    @Test
    public void convertYearMonthWeek_MonthAll_intWeekIsNegativeOne(){
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("All");
        inquiryForm.setWeek("1");
        
        inquiryForm.convertYearMonthWeek();
        Assert.assertEquals(-1, Deencapsulation.getField(inquiryForm, "intWeek"));
    }
    
    @Test
    public void convertYearMonthWeek_MonthAll_intMonthIsNegativeOne(){
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("All");
        inquiryForm.setWeek("1");
        
        inquiryForm.convertYearMonthWeek();
        Assert.assertEquals(-1, Deencapsulation.getField(inquiryForm, "intMonth"));
    }
    
    @Test
    public void convertYearMonthWeek_YearIs2011_intYearIs2011(){
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("All");
        inquiryForm.setWeek("1");
        
        inquiryForm.convertYearMonthWeek();
        Assert.assertEquals(2011, Deencapsulation.getField(inquiryForm, "intYear"));
    }   
    
    @Test
    public void convertYearMonthWeek_MonthOne_intMonthIsZero(){
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("1");
        
        inquiryForm.convertYearMonthWeek();
        Assert.assertEquals(0, Deencapsulation.getField(inquiryForm, "intMonth"));
    }
    
    private void context_getCurrentUser_Invoke() {
        inquiryForm.setUserManager(userManager);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
                {
                    inquiryForm.getRequest(); result= request;
                    request.getRemoteUser(); result=username;
                    userManager.getUserByUsername(username); result= user;
                }
            };
    }
    
    @Test
    public void getCurrentUser_Invoke_Call$getRemoteUser(){
        
        context_getCurrentUser_Invoke();
        
        inquiryForm.getCurrentUser();
        new Verifications(){{request.getRemoteUser();times =1; }};
    }
    
    @Test
    public void getCurrentUser_Invoke_Call$getUserByUsername(){
        
        context_getCurrentUser_Invoke();
        
        inquiryForm.getCurrentUser();
        new Verifications(){{request.getRemoteUser();times =1; }};
    }
    
    @Test
    public void generateFileNameOfAttacment_isAllMonth_ReturnFileNameForAllMonth(){
        java.sql.Date startDate = java.sql.Date.valueOf("2011-01-01");
        fullname = "Agus Muhammad Ramdan";
        
        String result = inquiryForm.generateFileNameOfAttacment(true,true,startDate,fullname);
        Assert.assertEquals("WRAll_All11_Agus Muhammad Ramdan.xls",result);  
    }
    
    @Test
    public void generateFileNameOfAttacment_AllMonthIsFalseAndAllWeekIsTrue_ReturnFileNameForAllWeek(){
        java.sql.Date startDate = java.sql.Date.valueOf("2011-01-01");
        fullname = "Agus Muhammad Ramdan";
        
        String result = inquiryForm.generateFileNameOfAttacment(false,true,startDate,fullname);
        Assert.assertEquals("WRAll_Jan11_Agus Muhammad Ramdan.xls",result);  
    }
    
    @Test
    public void generateFileNameOfAttacment_SecondWeekOfJanuaryEleven_ReturnFileNameForSecondWeekOfJanuaryEleven(){
        java.sql.Date startDate = java.sql.Date.valueOf("2011-01-02");
        fullname = "Agus Muhammad Ramdan";
        inquiryForm.setWeek("2");
                
        String result = inquiryForm.generateFileNameOfAttacment(false,false,startDate,fullname);
        Assert.assertEquals("WR2_Jan11_Agus Muhammad Ramdan.xls",result);  
    }
    
    /**
     * TODO MOVE TO SPRING INTEGRATION TEST
     * NEED SOME 
     * @throws JRException 
     */
    @Test
    @Ignore 
    public void generateJasperReportInquiryActivity_Invoke_ReturnNotNull() throws JRException{
         
        Map parameters= new HashMap();
        dataSource = new JREmptyDataSource(1);
        
        JasperPrint result = inquiryForm.generateJasperReportInquiryActivity(parameters, dataSource);
        Assert.assertNotNull(result);
    }
    @Test
    public void generateJasperReportInquiryActivity_Invoke_Call$JRFiller$fillReport(
            final InputStream inputStream,
            final JasperReport jasperReport,
            final JRDataSource dataSource ) throws JRException{
         
        final Map parameters= new HashMap();
        new NonStrictExpectations(JRLoader.class,JRFiller.class){{
            JRLoader.getResourceInputStream("InquiryActivity.jasper"); result= inputStream;
            JRLoader.loadObject(inputStream); result = jasperReport;
            JRFiller.fillReport(jasperReport, parameters, dataSource);
        }};
        
        inquiryForm.generateJasperReportInquiryActivity(parameters, dataSource);
        new Verifications (){{JRFiller.fillReport(jasperReport, parameters, dataSource); times=1;     }};
      
    }
    
    @Test 
    public void exportExcelToStream_Invoke_Call$exportReport(final OutputStream outputSteam) throws JRException{
        new NonStrictExpectations(inquiryForm){
            {
                jrxe.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);
                jrxe.exportReport();
            }
        };

        inquiryForm.exportExcelToStream(jasperPrint, outputSteam);
        new Verifications(){{ jrxe.exportReport(); times=1;  }};
    }
    
    @Test 
    public void exportExcelToStream_Invoke_Call$setParameterJasperPrint(final OutputStream outputSteam) throws JRException{
        new NonStrictExpectations(inquiryForm){
            {
                jrxe.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);
                jrxe.exportReport();
            }
        };

        inquiryForm.exportExcelToStream(jasperPrint, outputSteam);
        new Verifications(){{jrxe.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);times=1; }};
    } 
    
    @Test 
    public void exportExcelToStream_Invoke_Call$setParameterOutputStream(final OutputStream outputSteam) throws JRException{
        new NonStrictExpectations(inquiryForm){
            {
                jrxe.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);
                jrxe.exportReport();
            }
        };

        inquiryForm.exportExcelToStream(jasperPrint, outputSteam);
        new Verifications(){{ jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);times=1;  }};
    } 

    private void context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven() throws Exception{
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setActivityManager(activityManager);
        
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        filename = "WR2_Jan11_Agus Muhammad Ramdan.xls";
        new NonStrictExpectations(inquiryForm){
            {
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream; 
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                jrxe.exportReport();
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };
    }
    
    @Test 
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_SendDataFileNameOfAttacment() throws Exception{
        
        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");times =1; }};
    } 
    
    @Test 
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Send$flushBuffer() throws Exception{

        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{response.flushBuffer();times =1; }};
    }   
    
    @Test 
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_SenddataContentType$setContentType() throws Exception{

        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{response.setContentType("application/vnd.ms-excel");times =1; }};
    }
      
    @Test 
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Call$reset() throws Exception{

        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{response.reset();times =1; }};
    }
    
    @Test 
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Call$exportReport() throws Exception{

        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{jrxe.exportReport(); times=1; }};
    }
   
    @Test
    public void writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Call$responseComplete() throws Exception{

        context_writeExportedExcelToResponse_SecondWeekOfJanuaryEleven();

        inquiryForm.writeExportedExcelToResponse(filename,jasperPrint);
        new Verifications(){{facesContext.responseComplete(); times=1; }};
    }
    /**
     * 
     */
    @Test
    public void getResourceInputStream_InquiryActivityJasper_notNull(){
        
        InputStream inputStream = JRLoader.getResourceInputStream("InquiryActivity.jasper");
        Assert.assertNotNull(inputStream);
    }
    
    private void context_download_SecondWeekOfJanuaryEleven() throws Exception{
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setActivityManager(activityManager);
        dataSource = new JREmptyDataSource(1);
        username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            {
                inquiryForm.getRequest(); result= request;
                request.getRemoteUser(); result=username;
                userManager.getUserByUsername(username); result= user; 
                // three line above can change with 
                // inquiryForm.getCurrentUser();result= user; 
                user.getFullName(); result=fullname;
                user.getId();result=userId;
                activityManager.getJRDataSourceActivity(userId, withInstanceLike(new java.sql.Date(1)), withInstanceLike(new java.sql.Date(1))); result=dataSource;
                
                inquiryForm.generateJasperReportInquiryActivity(withInstanceLike(new HashMap()), dataSource);
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream; 
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                jrxe.exportReport();
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };
    }
    
    /**
     * @see #writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_SendDataFileNameOfAttacment()
     * @throws Exception 
     */
    @Test 
    public void download_SecondWeekOfJanuaryEleven_SendDataFileNameOfAttacment() throws Exception{
        
        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");times =1; }};
    }
    
    /**
     * @see #writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Send$flushBuffer
     * @throws Exception 
     */
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Send$flushBuffer( ) throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.flushBuffer();times =1; }};
    }
    
    /**
     * @see #writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_SenddataContentType$setContentType()
     * @throws Exception 
     */
    @Test 
    public void download_SecondWeekOfJanuaryEleven_SenddataContentType$setContentType() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.setContentType("application/vnd.ms-excel");times =1; }};
    }
  
    /**
     * @see #writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Call$reset()
     * @throws Exception 
     */
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Call$reset() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.reset();times =1; }};
    }
    /**
     * @see #exportExcelToStream_Invoke_Call$exportReport(java.io.OutputStream) 
     * 
     * @throws Exception 
     */
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Call$exportReport() throws Exception{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{jrxe.exportReport(); times=1; }};
    }
// isolated from export        
//    @Test 
//    public void download_SecondWeekOfJanuaryEleven_Call$write() throws Exception{
//
//        context_download_SecondWeekOfJanuaryEleven();
//
//        inquiryForm.download();
//        Assert.assertTrue(bos.size()>0);
//    }
    /**
     * @see #writeExportedExcelToResponse_SecondWeekOfJanuaryEleven_Call$responseComplete() 
     * @throws Exception 
     */
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
        inquiryForm.setUserManager(userManager);
        inquiryForm.setActivityManager(activityManager);
        //final JRDataSource dataSource = new JREmptyDataSource(1);
        dataSource = new JREmptyDataSource(1);
        //username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            //ServletOutputStream servletOutputStream;
            {
                inquiryForm.getCurrentUser();result= user; 
                user.getFullName(); result=fullname;
                inquiryForm.getResponse(); result =response;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                inquiryForm.generateJasperReportInquiryActivity(withInstanceLike(new HashMap()), dataSource); result = jasperPrint;
                
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Feb11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                jrxe.exportReport();
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
        inquiryForm.setActivityManager(activityManager);
        //final JRDataSource dataSource = new JREmptyDataSource(1);
        dataSource = new JREmptyDataSource(1);
        //username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            ServletOutputStream servletOutputStream;
            {
                inquiryForm.getCurrentUser();result= user; 
                user.getFullName(); result=fullname;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                inquiryForm.generateJasperReportInquiryActivity(withInstanceLike(new HashMap()), dataSource); result = jasperPrint;
                
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_Feb11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                jrxe.exportReport();
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
        dataSource = new JREmptyDataSource(1);
        //username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm){
            //ServletOutputStream servletOutputStream;
            {
                inquiryForm.getCurrentUser();result= user; 
                user.getFullName(); result=fullname;
                activityManager.getJRDataSourceActivity(anyLong, withInstanceOf(java.sql.Date.class), withInstanceOf(java.sql.Date.class)); result=dataSource;
                inquiryForm.generateJasperReportInquiryActivity(withInstanceLike(new HashMap()), dataSource); result = jasperPrint;
                
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WRAll_All11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream; 
                jrxe.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                jrxe.exportReport();
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
    public void download_SecondWeekOfJanuaryEleven_ContaintInPrametersWhenCallJRFiller$fillReport() throws Exception{
        final Map captureContentParameter = new HashMap();

        inquiryForm.setYear("2011");
        inquiryForm.setMonth("1");
        inquiryForm.setWeek("2");
        
        inquiryForm.setUserManager(userManager);
        inquiryForm.setInquiryManager(activityManager);
        dataSource = new JREmptyDataSource(1);
        //username = "ramdan";
        fullname = "Agus Muhammad Ramdan";
        new NonStrictExpectations(inquiryForm,JRFiller.class){
            JasperPrint jasperPrint;
            {
                inquiryForm.getCurrentUser(); result= user;
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