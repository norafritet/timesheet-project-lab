package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.ActivityManager;
import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import org.appfuse.model.User;
import java.sql.Date;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
import org.appfuse.model.User;

/**
 * Created by IntelliJ IDEA.
 * User: max
 * Date: 12/15/11
 * Time: 10:13 AM
 */
public class InquiryForm extends  BasePage implements Serializable {

    // Declaration
    private final static String strAll = "All";
    
    private int intMonth ;
    private int intWeek ;
    private int intYear ;
        
    private String year;
    private String month;
    private String week;

    private List years;
    private static List months = Arrays.asList( strAll, "1" , "2" , "3" , "4" , "5", "6" , "7" , "8" , "9" , "10" , "11" , "12" );
    private List weeks ;

    private Activity activity = new Activity();
    //private ActivityManager inquiryManager;
    private ActivityManager activityManager;
    private List<Activity> activitys;

    public InquiryForm()
    {
        // Initialization
        activitys = new ArrayList<Activity>();
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
    
    public void setInquiryManager(ActivityManager manager) {
        //this.inquiryManager = manager;
        activityManager=manager;
    }

    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
        //this.inquiryManager = activityManager;
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

        /*
        // Initialization
        months = new ArrayList();
        months.add( strAll );

        // Add months to List
        for ( int i = 1 ; i < 13; ++i  )
        {
            months.add( i );
        }

        // Return
        */
        return months;
    }
//
//    public void setMonths(List months) {
//        this.months = months;
//    }
    private static final List FOUR_WEEKS =Arrays.asList("All","1","2","3","4");
    private static final List FIVE_WEEKS =Arrays.asList("All","1","2","3","4","5");
    private static final List SIX_WEEKS =Arrays.asList("All","1","2","3","4","5","6");
    private static final List[] WEEKS = {Arrays.asList("All" ),FOUR_WEEKS,FIVE_WEEKS,SIX_WEEKS};
    public List getWeeks() {
        return weeks = WEEKS[!month.equals( strAll ) ? this.getNumOfWeekOfMonth()-3 : 0];
    }

//    public void setWeeks(List weeks) {
//        this.weeks = weeks;
//    }

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
         Date[] rangeDate = null;
         
         int month2 = getMonth().equals( "All" ) ? -1 : Integer.parseInt(getMonth());
         int week2 = getWeek().equals( "All" ) ? 0 : Integer.parseInt(getWeek());
         
         rangeDate  = RequestUtil.getRangeDateFor(Integer.parseInt(getYear()), month2, week2 );
         List<Activity> act = activityManager.findByActivityWeek(user.getId(),rangeDate[0],rangeDate[1]);
         
         for(int i=0;i<act.size();i++){
             activity = act.get(i);
             activitys.add(activity);
         }
         return null;
     }

    public void download() throws IOException, JRException {
        
        String fullName = null;
        String fileName = null;
        
        User user = getCurrentUser();
        fullName = user.getFullName();
        Long userId = user.getId();
        
        convertYearMonthWeek();
        Date[] rangeDate = RequestUtil.getRangeDateFor(intYear, intMonth, intWeek);
        
        fileName =  generateFileNameOfAttacment(intMonth<0,intWeek<1,rangeDate[0], fullName);
        Map parameter = new HashMap();
        parameter.put("USER_FULLNAME",fullName);
        JRDataSource dataSource = activityManager.getJRDataSourceActivity(userId, rangeDate[0], rangeDate[1]);
        JasperPrint jasperPrint =generateJasperReportInquiryActivity(parameter,dataSource);
        writeExportedExcelToResponse(fileName, jasperPrint);
    }
    
    protected void convertYearMonthWeek(){
         try {
            intMonth = Integer.parseInt(month)-1;
            try {
                intWeek = Integer.parseInt(week);
            }catch (NumberFormatException e){
                intWeek = -1;
            }
        }catch (NumberFormatException e){
            intMonth = -1;
            intWeek = -1;
        }
        intYear = Integer.parseInt(year);
    }
    protected User getCurrentUser(){
        String username = getRequest().getRemoteUser();
        return userManager.getUserByUsername(username);
    }
    protected String generateFileNameOfAttacment(boolean isAllMonth,boolean isAllWeek,Date startDate,String fullName){
        String monthYear;
        if(isAllMonth){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
            monthYear = "All"+simpleDateFormat.format(startDate);
        }else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMyy");
            monthYear = simpleDateFormat.format(startDate);
        }
        return "WR"+(isAllWeek? "All":week)+"_"+monthYear+"_"+fullName+".xls";
    }
    protected JasperPrint generateJasperReportInquiryActivity(Map parameters,JRDataSource dataSource) throws JRException{
        InputStream inputStream = JRLoader.getResourceInputStream("InquiryActivity.jasper");
        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
        return  JRFiller.fillReport(jasperReport, parameters, dataSource);
    }
    protected void exportExcelToStream(JasperPrint jasperPrint,OutputStream outputSteam) throws JRException{
        JRXlsExporter exporter = new JRXlsExporter();		
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputSteam);		
	exporter.exportReport();
    }
    protected void writeExportedExcelToResponse(String fileName, JasperPrint jasperPrint) throws IOException, JRException{
        HttpServletResponse response = getResponse();
        response.reset();
        response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        response.setContentType("application/vnd.ms-excel");
        OutputStream outputSteam = response.getOutputStream() ;
        exportExcelToStream( jasperPrint, outputSteam);
        response.flushBuffer();
        getFacesContext().responseComplete();
    }
            
    public int getNumOfWeekOfMonth()
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
