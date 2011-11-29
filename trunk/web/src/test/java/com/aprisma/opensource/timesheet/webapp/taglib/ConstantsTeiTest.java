/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.webapp.taglib;

import mockit.Mocked;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.VariableInfo;
import mockit.NonStrictExpectations;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author devxpbox
 */
public class ConstantsTeiTest {
    
    @Test
    public void getVariableInfo_InputNull_NotNull(){
        ConstantsTei test = new ConstantsTei();
        
        VariableInfo[] result = test.getVariableInfo(null);
        assertNotNull(result);
    }
    @Test
    public void getVariableInfo_InputNotNull_ArrayNotEmpty(@Mocked TagData data){
        ConstantsTei test = new ConstantsTei();
        
        VariableInfo[] result = test.getVariableInfo(data);
        assertTrue(result.length>0);
    }
    
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
    public void getVariableInfo_NoVarSpecified_GetAllConstant(@Mocked final TagData data){
        ConstantsTei test = new ConstantsTei();
        new NonStrictExpectations() {{
            data.getAttributeString("className");
            result = TestConstants.class.getName();
            data.getAttributeString("var");
            result = null;
        }};
        
        VariableInfo[] result = test.getVariableInfo(data);
        assertEquals(4,result.length);
    }
    @Test
    public void getVariableInfo_Input$getAtributStringVarIsCONFIG_VaryabelInfoSameAsConstant$CONFIG(@Mocked final TagData data){
        ConstantsTei test = new ConstantsTei();
        new NonStrictExpectations(){{data.getAttributeString("var");result="CONFIG";}};
        
        VariableInfo[] result = test.getVariableInfo(data);
        assertArrayEquals(new Object[]{"CONFIG","java.lang.String",true,VariableInfo.AT_END},
                new Object[]{result[0].getVarName(),result[0].getClassName(),result[0].getDeclare(),result[0].getScope()
        });
    }
    @Test
    public void getVariableInfo_Input$getAtributeStringVarIsARRAYTEST(@Mocked final TagData data){
        ConstantsTei test = new ConstantsTei();
        new NonStrictExpectations(){{
            data.getAttributeString("className");result = TestConstants.class.getName();
            data.getAttributeString("var");result="ARRAYTEST";
        }};
        
        VariableInfo[] result = test.getVariableInfo(data);
        assertArrayEquals(new Object[]{"ARRAYTEST","java.lang.String[]",true,VariableInfo.AT_END},
                new Object[]{result[0].getVarName(),result[0].getClassName(),result[0].getDeclare(),result[0].getScope()
        });
    }

}
