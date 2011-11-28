package com.aprisma.opensource.timesheet.webapp.action;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

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
       Assert.assertEquals( "ss", iform.getYears() );

    }
}
