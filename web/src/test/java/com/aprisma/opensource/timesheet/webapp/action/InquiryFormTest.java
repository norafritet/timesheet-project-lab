package com.aprisma.opensource.timesheet.webapp.action;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 11:26 AM
 */
public class InquiryFormTest {
    
    @Tested private InquiryForm inquiryForm;

    @Mocked private HttpServletResponse response;
    @Mocked private ServletOutputStream servletOutputStream;
    @Mocked private FacesContext facesContext ;
    
    @Test
    public void getSpecificYears() throws Exception
    {

       InquiryForm iform = new InquiryForm();
       //Assert.assertEquals( "ss", iform.getYears() );

    }

    /*
    @Test
    public void getAvailableWeekBasedOnMonth() throws Exception
    {

        InquiryForm iform = new InquiryForm();
       iform.setMonth("11");
        iform.setYear("2011");
         iform.getWeeks()  ;


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

        //iform.getWeeksOfMonth();



    }
     */

    private void context_download_SecondWeekOfJanuaryEleven() throws IOException{
        inquiryForm.setYear("2011");
        inquiryForm.setMonth("0");
        inquiryForm.setWeek("2");
            new NonStrictExpectations(inquiryForm){
            {
                inquiryForm.getResponse(); result =response;
                response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");
                response.getOutputStream(); result = servletOutputStream;
                servletOutputStream.write(anyInt);
                inquiryForm.getFacesContext(); result = facesContext;
                facesContext.responseComplete();
            }
        };
    }
    @Test 
    public void download_SecondWeekOfJanuaryEleven_SendDataFileNameOfAttacment() throws IOException{
        
        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.addHeader("Content-Disposition", "attachment; filename=\"WR2_Jan11_Agus Muhammad Ramdan.xls\"");times =1; }};
    }
    
    @Test 
    public void download_SecondWeekOfJanuaryEleven_Send$flushBuffer( ) throws IOException{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.flushBuffer();times =1; }};

    }
 @Test 
    public void download_SecondWeekOfJanuaryEleven_SenddataContentType$setContentType( ) throws IOException{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.setContentType("application/vnd.ms-excel");times =1; }};
    }
 
 @Test 
 public void download_SecondWeekOfJanuaryEleven_Call$reset() throws IOException{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{response.reset();times =1; }};
    }
  @Test 
 public void download_SecondWeekOfJanuaryEleven_Call$write() throws IOException{
        

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{servletOutputStream.write(anyInt); minTimes=1; }};
    }
  @Test
  public void download_SecondWeekOfJanuaryEleven_Call$responseComplete() throws IOException{

        context_download_SecondWeekOfJanuaryEleven();

        inquiryForm.download();
        new Verifications(){{facesContext.responseComplete(); times=1; }};
    }
   
}
