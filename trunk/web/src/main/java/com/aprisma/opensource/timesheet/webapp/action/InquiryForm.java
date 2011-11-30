package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;
import org.appfuse.model.User;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 10:13 AM
 */


public class InquiryForm extends BasePage implements Serializable {

    private String year;
    private String month;
    private String week;

    private List years;
    private List months;
    private List weeks;

    private Activity activity = new Activity();
    private ActivityManager inquiryManager;
    
    public InquiryForm()
    {
        this.week = "";
        this.month = "";
        this.year = "";
    }
    
    public void setInquiryManager(ActivityManager manager) {
        this.inquiryManager = manager;
    }
    
    
    
    //activity
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List getYears() {

        years = new ArrayList();
        Calendar now = Calendar.getInstance();


        for ( int i = now.get( Calendar.YEAR ),j=0 ; j < 5 ; ++j,--i  )
        {
            years.add( i );
        }
        return years;
    }

    public void setYears(List years) {

        this.years = years;
    }

    public List getMonths() {

        months = new ArrayList();
        //Calendar now = Calendar.getInstance();


        for ( int i = 1 ; i < 13; ++i  )
        {
            months.add( i );
        }

        months.add( "All" );
        return months;
    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getWeeks() {

         weeks = new ArrayList();
        //Calendar now = Calendar.getInstance();

        for ( int i = 1 ; i < 6; ++i  )
        {
            weeks.add( i );
        }

        weeks.add( "All" );
        return weeks;
    }

    public void setWeeks(List weeks) {
        this.weeks = weeks;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
        
    }

     public List view()
     {
         
         String username = getRequest().getRemoteUser();
         
         User user = userManager.getUserByUsername(username);
         //activity.setActivityUser(user);
         
         Date[] rangeDate = RequestUtil.getRangeDateFor(Integer.parseInt(getYear()), Integer.parseInt(getMonth()), Integer.parseInt(getWeek()));

         List result = sort(inquiryManager.findByActivityWeek(user.getId(),rangeDate[0],rangeDate[1]));
         
         System.out.println("Success");
         return result;
         
         /*Date fd = Date.valueOf("2011-10-10");
         Date ed = Date.valueOf("2011-10-15");
         return sort(inquiryManager.findByActivityWeek(new Long(-1),fd,ed));*/
         //System.out.println(" VIEW ");
     }

    public void download() throws IOException, JRException {
        Map parameter = new HashMap();
        InputStream inputStream = null;
        JRDataSource dataSource = new JREmptyDataSource(1);
        JasperPrint jasperPrint = null;
        
        String monthYear = "Jan11";
        String fullName = "Agus Muhammad Ramdan";
        String fileName = "WR"+week+"_"+monthYear+"_"+fullName+".xls";
        
        inputStream = JRLoader.getResourceInputStream("InquiryActivity.jasper");
        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
        jasperPrint = JRFiller.fillReport(jasperReport, parameter, dataSource);
        
        // write to response
        HttpServletResponse response = getResponse();
        response.reset();
        response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        response.setContentType("application/vnd.ms-excel");
        OutputStream outputSteam = response.getOutputStream() ;
        JRXlsExporter exporter = new JRXlsExporter();		
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);		
	exporter.exportReport();
        response.flushBuffer();
        getFacesContext().responseComplete();
    }
}
