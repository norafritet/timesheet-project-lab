package com.aprisma.opensource.timesheet.webapp.action;

import mockit.internal.util.Utilities;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import java.io.IOException;
import mockit.Verifications;
import org.junit.Before;
import mockit.Delegate;
import mockit.Mocked;
import org.appfuse.model.User;
import javax.servlet.http.HttpServletRequest;
import mockit.NonStrictExpectations;
import mockit.Mock;
import mockit.MockUp;
import org.appfuse.service.UserManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserFormTest {

    @Mocked
    private UserManager userManager;
    
    private UserForm bean;
    
    @Before
    public void onSetUp(){
        bean = new UserForm();
        bean.setUserManager(userManager);
    }
    
    @Test
    public void edit_NormalInvoke_Return$editProfile() {

        new NonStrictExpectations(bean) {

            HttpServletRequest request;

            {
                invoke(bean, "getRequest");  result = request;
                request.getRemoteUser();  result = "remote_user";
                userManager.getUserByUsername("remote_user");
                result = new Delegate() {
                    
                    public User getUserByUsername(String username) {
                        return new User(username);
                    }
                    
                };
                bean.isRememberMe(); result = false;
            }
        };

        String result = bean.edit();

        assertEquals("editProfile", result);
    }
    
    @Test
    public void edit_NormalInvoke_Call_userManager$getUserByUsername() {

        new NonStrictExpectations(bean) {

            HttpServletRequest request;

            {
                invoke(bean, "getRequest");  result = request;
                request.getRemoteUser();  result = "remote_user";
                userManager.getUserByUsername("remote_user");
                result = new Delegate() {
                    
                    public User getUserByUsername(String username) {
                        return new User(username);
                    }
                    
                };
                bean.isRememberMe(); result = false;
            }
        };

        bean.edit();

        new Verifications(){
            {
                userManager.getUserByUsername("remote_user");times=1;
            }
        };
    }
    
    @Test
    public void edit_NormalInvoke_Call_userManager$getUser() {

        bean.setId("user_id");
        
        new NonStrictExpectations(bean) {

            HttpServletRequest request;

            {
                invoke(bean, "getRequest");  result = request;
                userManager.getUser("user_id");
                result = new Delegate() {
                    
                    public User getUser(String username) {
                        return new User("remote_user");
                    }
                    
                };
                bean.isRememberMe(); result = false;
            }
        };

        bean.edit();

        new Verifications(){
            {
                userManager.getUser("user_id");times=1;
            }
        };
    }
    
    @Test
    public void save_NotFromList_ReturnMainMenu() throws IOException{
    
        new NonStrictExpectations(bean) {

            HttpServletRequest request;

            {
                invoke(bean, "getRequest");  result = request;
                invoke(bean, "addMessage",anyString);  
            }
        };
        
        String result = bean.save();
        
        assertEquals("mainMenu", result );
    }
    
    @Test
    public void save_FromListAndVersionEmpty_ReturnList() throws Exception{
        final User user = bean.getUser();
        final String text = "newuser.email.message user.getFullName()";
        final String url = "http://localhost:8080/sss";
        //bean.setUser(user);
        user.setFirstName("FN");
        user.setLastName("LN");
        
        final String fullName = user.getFullName();
         
        new NonStrictExpectations(bean,RequestUtil.class) {

            HttpServletRequest request;

            {
                bean.getRequest(); result = request;
                userManager.saveUser(user); result = user;
                bean.getParameter("from"); result="list";
                bean.getParameter("userForm:version");result="";
                bean.addMessage("user.added", fullName);
                bean.getText("newuser.email.message", fullName); result=text;
                RequestUtil.getAppURL(request); result =url;
                bean.sendUserMessage(user,text,url);
           
            }
        };
        
        String result = bean.save();
        
        assertEquals("list", result );
    }
        
        
    @Test
    public void save_FromListAndVersionNotEmpty_ReturnEditProfile() throws Exception{
        final User user = bean.getUser();
        //final String text = "newuser.email.message user.getFullName()";
        //final String url = "http://localhost:8080/sss";
        //bean.setUser(user);
        user.setFirstName("FN");
        user.setLastName("LN");
        
        final String fullName = user.getFullName();
         
        new NonStrictExpectations(bean,RequestUtil.class) {

            HttpServletRequest request;

            {
                bean.getRequest(); result = request;
                //invoke(bean, "addMessage",anyString);  
                userManager.saveUser(user); result = user;
                bean.getParameter("from"); result="list";
                bean.getParameter("userForm:version"); result="2";
                bean.addMessage("user.updated.byAdmin", fullName);
                //bean.getText("newuser.email.message", fullName); result=text;
                //RequestUtil.getAppURL(request); result =url;
                //invoke(bean, "sendUserMessage",user,text,url);
           
            }
        };
        
        String result = bean.save();
        
        assertEquals("editProfile", result );
    }        
}

/* pindah ke test intergartion - spring
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserFormTest extends BasePageTestCase {
    private UserForm bean;
    @Autowired
    private UserManager userManager;

    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new UserForm();
        bean.setUserManager(userManager);
        assertNotNull(bean);
    }

    @Override
    @After
    public void onTearDown() {
        super.onTearDown();
        bean = null;
    }

    @Test
    public void testEdit() throws Exception {
        bean.setId("-1");
        assertEquals("editProfile", bean.edit());
        assertNotNull(bean.getUser().getUsername());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSave() throws Exception {
        User user = userManager.getUser("-1");
        user.setPassword("user");
        user.setConfirmPassword("user");
        bean.setUser(user);

        assertEquals("mainMenu", bean.save());
        assertNotNull(bean.getUser());
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testRemove() throws Exception {
        User user2Delete = new User();
        user2Delete.setId(-2L);
        bean.setUser(user2Delete);
        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}
*/