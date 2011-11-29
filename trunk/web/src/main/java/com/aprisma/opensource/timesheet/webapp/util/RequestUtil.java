package com.aprisma.opensource.timesheet.webapp.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Convenience class for setting and retrieving cookies.
 */
public final class RequestUtil {

    private static final Log log = LogFactory.getLog(RequestUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private RequestUtil() {
    }

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name the name of the cookie
     * @param value the value of the cookie
     * @param path the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name,
            String value, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(3600 * 24 * 30); // 30 days

        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name the name of the cookie to find
     *
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

//        if (cookies == null) {
//            return returnCookie;
//        }
        if (cookies != null) {
            for (final Cookie thisCookie : cookies) {
                if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
                    returnCookie = thisCookie;
                    break;
                }
            }
        }
        return returnCookie;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response,
            Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method to get the application's URL based on request
     * variables.
     * 
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    /**
     * 
     * @param year
     * @param month 0..11 for all = -1
     * @param week  1..6 fro all = -1
     * @return 
     */
    public static Date[] getRangeDateFor(int year,int month,int week){
        //Calendar c = Calendar.getInstance();
        GregorianCalendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.set(year,month,1,0,0,0);
        Date firstDate ,eddDate;
        if(month<0){
            c.set(year,0,1,0,0,0);
            firstDate =new Date(c.getTimeInMillis());
            c.set(year,11,31,0,0,0);
            eddDate =new Date(c.getTimeInMillis());
            return new Date[]{firstDate,eddDate};
        }else
        if(week <1){
            c.set(year,month,1,0,0,0);
            firstDate =new Date(c.getTimeInMillis());
            c.set(year,month,c.getActualMaximum(Calendar.DAY_OF_MONTH),0,0,0);
            eddDate =new Date(c.getTimeInMillis());
            return new Date[]{firstDate,eddDate};
        }else{
            if(week==1){
                c.set(year,month,1,0,0,0);
            }else{
                c.clear();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.WEEK_OF_MONTH, week);
                
            }
            firstDate =new Date(c.getTimeInMillis());
            if((c.get(Calendar.DATE)+6 )>  c.getActualMaximum(Calendar.DATE)){
                c.clear();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
            } else{
                c.clear();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.WEEK_OF_MONTH, week+1);
                c.add(Calendar.DATE, -1);
            }
            eddDate =new Date(c.getTimeInMillis());
            return new Date[]{firstDate,eddDate};
        }
        
    } 
}
