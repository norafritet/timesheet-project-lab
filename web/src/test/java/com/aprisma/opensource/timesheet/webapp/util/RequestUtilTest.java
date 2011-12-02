/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.util;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import mockit.Mock;
import mockit.Mockit;
import javax.security.auth.login.LoginContext;
import mockit.Expectations;
import mockit.Verifications;
import javax.servlet.http.Cookie;
import mockit.Mocked;
import javax.servlet.http.HttpServletResponse;
import mockit.NonStrictExpectations;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class RequestUtilTest {

    @Test
    public void setCookie_NotNullParameter_CallResponse$addCookie(@Mocked final HttpServletResponse response) {

        new Expectations() {
            Cookie cookie;

            {
                response.addCookie(withAny(cookie)); times = 1;
            }
        };

        RequestUtil.setCookie(response, "name", "value", "path");

    }

    @Test
    public void setCookie_NotNullParameter_initParamterNameValue(@Mocked final HttpServletResponse response) {

        Mockit.setUpMock(Cookie.class, new Object() {

            @Mock
            void $init(String name, String value) {
                assertArrayEquals(new String[]{"name", "value"}, new String[]{name, value});
            }
        });

        RequestUtil.setCookie(response, "name", "value", "path");

    }

    @Test
    public void setCookie_NotNullParameter_InvokeCookie$setPath(@Mocked final HttpServletResponse response) {

        Mockit.setUpMock(Cookie.class, new Object() {

            @Mock
            void setPath(String path) {
                assertEquals("path", path);
            }
        });

        RequestUtil.setCookie(response, "name", "value", "path");

    }

    @Test
    public void getCookie_CookieFound_ReturnCookie(@Mocked final Cookie cookie, @Mocked final HttpServletRequest request) {

        new NonStrictExpectations() {

            {
                request.getCookies();
                result = new Cookie[]{cookie};
                cookie.getName();
                result = "name";
                cookie.getValue();
                result = "value";
            }
        };

        Cookie result = RequestUtil.getCookie(request, "name");
        assertEquals(cookie, result);
    }
    @Test
    public void getCookie_CookieNameMatchButValueEmptyString_ReturnCookie(@Mocked final Cookie cookie, @Mocked final HttpServletRequest request) {

        new NonStrictExpectations() {

            {
                request.getCookies();
                result = new Cookie[]{cookie};
                cookie.getName();
                result = "name";
                cookie.getValue();
                result = "";
            }
        };

        Cookie result = RequestUtil.getCookie(request, "name");
        assertNull(result);
    }
    @Test
    public void getCookie_CookieNotFoud_ReturnNull(@Mocked final Cookie cookie, @Mocked final HttpServletRequest request) {

        new NonStrictExpectations() {

            {
                request.getCookies();
                result = new Cookie[]{cookie};
                cookie.getName();
                result = "WrongName";
            }
        };

        Cookie result = RequestUtil.getCookie(request, "name");
        assertNull(result);

    }

    @Test
    public void getCookie_request$getCookiesReturnNull_ReturnNull(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {{request.getCookies(); result = null;}};

        Cookie result = RequestUtil.getCookie(request, "name");
        assertNull(result);

    }

    @Test
    public void deleteCookie_Cookie_Call$addCookie(@Mocked final Cookie cookie, @Mocked final HttpServletResponse response) {
        new NonStrictExpectations() {{ response.addCookie(cookie);}};

        RequestUtil.deleteCookie(response, cookie, "path");
        new Verifications() {{response.addCookie(cookie);}};
    }

    @Test
    public void deleteCookie_Cookie_Call$setMaxAgeZero(@Mocked final Cookie cookie, @Mocked final HttpServletResponse response) {
        new NonStrictExpectations() {{cookie.setMaxAge(0);}};

        RequestUtil.deleteCookie(response, cookie, "path");
        new Verifications() {{cookie.setMaxAge(0);}};
    }

    @Test
    public void deleteCookie_Cookie_Call$setPath(@Mocked final Cookie cookie, @Mocked final HttpServletResponse response) {
        final String path = "path";
        new NonStrictExpectations() {{cookie.setPath(path);}};

        RequestUtil.deleteCookie(response, cookie, path);
        new Verifications() {{cookie.setPath(path);}};
    }
    @Test
    public void deleteCookie_CookieNull_NotThrowExceptions() {

        RequestUtil.deleteCookie(null, null, null);

    }
    @Test
    public void getAppURL_RequestNull_ReturnEmptyString() {

        String result = RequestUtil.getAppURL(null);
        assertEquals("", result);
    }

    @Test
    public void getAppURL_RequestNotNull_ReturnSomeHttpURL(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {

            {
                request.getServerPort();
                result = 80;
                request.getScheme();
                result = "http";
                request.getServerName();
                result = "localhost";
                request.getContextPath();
                result = "/data";
            }
        };

        String result = RequestUtil.getAppURL(request);
        assertEquals("http://localhost/data", result);
    }
    @Test
    public void getAppURL_RequestNegatifPort_ReturnSomeHttpURL(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {

            {
                request.getServerPort();
                result = -1;
                request.getScheme();
                result = "http";
                request.getServerName();
                result = "localhost";
                request.getContextPath();
                result = "/data";
            }
        };

        String result = RequestUtil.getAppURL(request);
        assertEquals("http://localhost/data", result);
    }

    @Test
    public void getAppURL_RequestNotNull_ReturnSomeHttpURL2(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {

            {
                request.getServerPort();
                result = 88;
                request.getScheme();
                result = "http";
                request.getServerName();
                result = "localhost";
                request.getContextPath();
                result = "/data";
            }
        };

        String result = RequestUtil.getAppURL(request);
        assertEquals("http://localhost:88/data", result);
    }

    @Test
    public void getAppURL_RequestNotNull_ReturnSomeHttpsURL(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {

            {
                request.getServerPort();
                result = 443;
                request.getScheme();
                result = "https";
                request.getServerName();
                result = "localhost";
                request.getContextPath();
                result = "/data";
            }
        };

        String result = RequestUtil.getAppURL(request);
        assertEquals("https://localhost/data", result);
    }

    @Test
    public void getAppURL_RequestNotNull_ReturnSomeHttpsURL2(@Mocked final HttpServletRequest request) {
        new NonStrictExpectations() {

            {
                request.getServerPort();
                result = 444;
                request.getScheme();
                result = "https";
                request.getServerName();
                result = "localhost";
                request.getContextPath();
                result = "/data";
            }
        };

        String result = RequestUtil.getAppURL(request);
        assertEquals("https://localhost:444/data", result);
    }
    
    @Test
    public void getRageDateFor_OneYear_haveTwoLenght(){
        
        Date[] result = RequestUtil.getRangeDateFor(2011,-1,-1);
        
        assertEquals(2,result.length);
    }
    
    @Test
    public void getRageDateFor_OneYear_ReturnRangeAllYear(){
        GregorianCalendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        Date firstDate ,eddDate;
        c.set(2011,0,1,0,0,0);
        firstDate =new Date(c.getTimeInMillis());
        c.set(2011,11,31,0,0,0);
        eddDate =new Date(c.getTimeInMillis());
        
        Date[] result = RequestUtil.getRangeDateFor(2011,-1,-1);
        assertArrayEquals(new Date[]{firstDate,eddDate},result);
    }
    @Test
    public void getRageDateFor_OneMonthOfJanuary_ReturnRangeDateInMonth(){
        GregorianCalendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        Date firstDate ,eddDate;
        c.set(2011,0,1,0,0,0);
        firstDate =new Date(c.getTimeInMillis());
        c.set(2011,0,31,0,0,0);
        eddDate =new Date(c.getTimeInMillis());
        
        Date[] result = RequestUtil.getRangeDateFor(2011,0,-1);
        assertArrayEquals(new Date[]{firstDate,eddDate},result);
    }
    
    @Test
    public void getRageDateFor_OneMonthOfJanuary_ReturnLastDate(){

        Date[] result = RequestUtil.getRangeDateFor(2012,0,-1);
        assertEquals(31,result[1].getDate());
    }
    
    @Test
    public void getRageDateFor_OneMonthOfFebruaryLeapYear_ReturnLastDateTwentyNinet(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,1,-1);
        assertEquals(29,result[1].getDate());
        
    }
    
    @Test
    public void getRageDateFor_OneMonthOfFebruaryisNotLeapYear_ReturnLastDateTwentyEight(){
        
        Date[] result = RequestUtil.getRangeDateFor(2011,1,-1);
        assertEquals(28,result[1].getDate());    
    }
    
    @Test
    public void getRageDateFor_FirstWeekOfMonth_ReturnLastDayIsSeven(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,0,1);
        assertEquals(7,result[1].getDate());
    }
    
    @Test
    public void getRageDateFor_FirstWeekOfMonth_ReturnLastDayIsFour(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,1,1);
        assertEquals(4,result[1].getDate());
    }
    
    @Test
    public void getRageDateFor_FirstWeekOfMonth_ReturnRangeDay(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,1,2);
        assertArrayEquals(new int[]{5,11},new int[]{result[0].getDate(),result[1].getDate()});
    }
    
    @Test
    public void getRageDateFor_LastWeekOfMonth_ReturnRangeEndDay(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,0,5);
        assertArrayEquals(new int[]{29,31},new int[]{result[0].getDate(),result[1].getDate()});
    }
      
    @Test
    public void getRageDateFor_LastWeekOfMonth_ReturnRangeEndDay2(){
        
        Date[] result = RequestUtil.getRangeDateFor(2009,1,4);
        assertArrayEquals(new int[]{22,28},new int[]{result[0].getDate(),result[1].getDate()});
    } 
    
    @Test
    public void getRageDateFor_WeekOutOffRange_ReturnRangeEndWeek(){
        
        Date[] result = RequestUtil.getRangeDateFor(2012,0,6);
        assertArrayEquals(new int[]{29,31},new int[]{result[0].getDate(),result[1].getDate()});
    }
    
    @Test
    public void getRageDateFor_WeekOutOffRange_ReturnRangeEndWeek2(){
        
        Date[] result = RequestUtil.getRangeDateFor(2009,1,5);
        assertArrayEquals(new int[]{22,28},new int[]{result[0].getDate(),result[1].getDate()});
    }    
}