package com.aprisma.opensource.timesheet.webapp.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.sf.jasperreports.engine.JRException;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mockit.Delegate;
import mockit.Mock;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 11:26 AM
 */
public class InquiryFormTest {
    
    @Tested private InquiryForm inquiryForm;

    @Mocked private HttpServletResponse response;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private ServletOutputStream servletOutputStream;
    @Mocked private FacesContext facesContext ;

    @Test
    public void getSpecificYears() throws Exception
    {

       InquiryForm iform = new InquiryForm();
       //Assert.assertEquals( "ss", iform.getYears() );

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
        inquiryForm.setMonth("0");
        inquiryForm.setWeek("2");
        
        bos = new ByteArrayOutputStream();
        servletOutputStream = new ServletOutputStream(){

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };
        //final InputStream inputStreamJasperReport = JRLoader.getResourceInputStream("InquiryActivity.jasper");
        
//        final Map parameter= new HashMap();
//        final JRDataSource datasource = new JREmptyDataSource(1);
//        final JasperPrint jasperPrint = new JasperPrint();
        new NonStrictExpectations(inquiryForm){
            {
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
    
}
