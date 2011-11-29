/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.taglib;

import mockit.Mocked;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class ConstntsTagTest {
    
public static class TestConstants {
        //~ Static fields/initializers =============================================

        /**
         * The name of the ResourceBundle used in this application
         */
        public static final String BUNDLE_KEY = "ApplicationResources";
        /**
         * File separator from System properties
         */
        public static final String FILE_SEP = System.getProperty("file.separator");
        /**
         * User home from System properties
         */
        public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;
        
        public static final String[] ARRAYTEST=new String[]{"StringData"};
    }

    @Test
    public void doStartTag_varIsNull_ReturnsSKIPBODY(@Mocked PageContext pageContext) throws JspException{
        ConstantsTag constantsTag = new ConstantsTag();
        constantsTag.setClassName(TestConstants.class.getName());
        constantsTag.setPageContext(pageContext);
        
        int result = constantsTag.doStartTag();
        assertEquals(Tag.SKIP_BODY, result);
    }
    
    @Test
    public void doStartTag_varIsNull_CallpageContext$setAttributeFourTimes(@Mocked final PageContext pageContext) throws JspException{
        ConstantsTag constantsTag = new ConstantsTag();
        constantsTag.setClassName(TestConstants.class.getName());
        constantsTag.setPageContext(pageContext);
        new NonStrictExpectations(){{pageContext.setAttribute(anyString, any, anyInt);}};
        
        constantsTag.doStartTag();
        new Verifications(){{pageContext.setAttribute(anyString, any, anyInt); times=4;}};
    }
    
    @Test
    public void doStartTag_varIsNotNull_CallpageContext$setAttributeFourTimes(@Mocked final PageContext pageContext) throws JspException{
        ConstantsTag constantsTag = new ConstantsTag();
        constantsTag.setClassName(TestConstants.class.getName());
        constantsTag.setVar("BUNDLE_KEY");
        constantsTag.setPageContext(pageContext);
        new NonStrictExpectations(){{pageContext.setAttribute(anyString, any, anyInt);}};
        
        constantsTag.doStartTag();
        new Verifications(){{pageContext.setAttribute(anyString, any, anyInt); times=1;}};
    }
}
