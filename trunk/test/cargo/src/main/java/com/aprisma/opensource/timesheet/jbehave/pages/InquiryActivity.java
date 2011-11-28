/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.jbehave.pages;

/**
 *
 * @author devxpbox
 */
public interface InquiryActivity extends Generic{

    public void clickMenuInquiryActivity();

    public void fillInquiryForm(String year, String month, String week);

    public void clickInquiryButtonView();

    public void clickInquiryButtonDownload();
    
}
