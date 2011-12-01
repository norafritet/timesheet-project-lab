package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import org.appfuse.model.User;
import java.sql.Date;
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

public class InquiryForm extends BasePage{

    // Declaration
    private final static String strAll = "All";
    private String year;
    private String month;
    private String week;

    private List years;
    private List months;
    private List weeks;

    private Activity activity = new Activity();
    
    private ActivityManager activityManager;
    
    private List<Activity> activitys;

    public InquiryForm()
    {
        activitys = new ArrayList<Activity>();
        // Initialization
        this.week = "";
        this.month = strAll;
        this.year = "";
    }
    public List<Activity> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<Activity> activitys) {
        this.activitys = activitys;
    }
    
    public void setActivityManager(ActivityManager manager) {
        this.activityManager = manager;
    }
    
    //activity
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List getYears() {

        // Initialization
        years = new ArrayList();
        Calendar now = Calendar.getInstance();

        // Add Years to List
        for ( int i = now.get( Calendar.YEAR ),j=0 ; j < 5 ; ++j,--i  )
        {
            years.add( i );
        }

        // Return
        return years;
    }

    public void setYears(List years) {

        this.years = years;
    }

    public List getMonths() {

        // Initialization
        months = new ArrayList();
        months.add( strAll );

        // Add months to List
        for ( int i = 1 ; i < 13; ++i  )
        {
            months.add( i );
        }

        // Return
        return months;

    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getWeeks() {

        // Initialize
        weeks = new ArrayList();
        weeks.add( strAll );

        // Add Num Of Week to List
        if ( !getMonth().equals( strAll ) )

            for ( int i = 1; i <= getNumOfWeekOfMonth(); ++i )

                weeks.add( new Integer(i).toString() );
        // End Add

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
   
    public String view()
    {
        
        String username = getRequest().getRemoteUser();
        User user = userManager.getUserByUsername(username);
        Date[] rangeDate = RequestUtil.getRangeDateFor(Integer.parseInt(getYear()), Integer.parseInt(getMonth()), Integer.parseInt(getWeek()));

        List<Activity> act = activityManager.findByActivityWeek(user.getId(),rangeDate[0],rangeDate[1]);
        for(int i=0;i<act.size();i++){
            activity = act.get(i);
            activitys.add(activity);
        }
        return null;
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
    
    private int getNumOfWeekOfMonth()
    {

        // Initialize
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt( getYear() );
        int month = Integer.parseInt( getMonth() ) - Calendar.FEBRUARY ;

        // Set First Day
        cal.set( year, month, 1 );

        // Set Last Day
        cal.set( year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH) );

        // Get Number of Week
        int numOfWeek = cal.get( Calendar.WEEK_OF_MONTH );
        System.out.println("Date " + year + "-" + month + "-" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("num Of Week--> " + numOfWeek + "(" + Calendar.JANUARY);

        // Return
        return numOfWeek;

    }
}
