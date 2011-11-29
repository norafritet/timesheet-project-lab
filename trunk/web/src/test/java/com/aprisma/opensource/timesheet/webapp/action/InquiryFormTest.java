package com.aprisma.opensource.timesheet.webapp.action;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 11:26 AM
 */
public class InquiryFormTest {



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


}
