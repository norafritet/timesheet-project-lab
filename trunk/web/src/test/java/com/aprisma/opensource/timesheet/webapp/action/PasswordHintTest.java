package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.webapp.action.PasswordHint;
import org.springframework.mail.MailException;
import org.appfuse.service.MailEngine;
import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import org.springframework.mail.SimpleMailMessage;
import javax.servlet.http.HttpServletRequest;
import mockit.Mock;
import mockit.MockUp;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class PasswordHintTest{
    
    @Test
    public void execute_NormalSecenario_ReturnSuccess() throws Exception {
        final PasswordHint passwordHint = new PasswordHint();
        passwordHint.setUsername("usernametesthint");
        
        final User user = new User ();
        final UserManager userManager = new MockUp<UserManager>(){
            @Mock
            public User getUserByUsername(String username) throws UsernameNotFoundException{
                return user;
            }
        }.getMockInstance();
        
        
        passwordHint.setUserManager(userManager);
        final SimpleMailMessage message = new SimpleMailMessage();
        passwordHint.setMessage(message);
        
        final MailEngine mailEngine = new MailEngine();
        passwordHint.setMailEngine(mailEngine);
        
        new NonStrictExpectations(passwordHint,mailEngine,RequestUtil.class){
            FacesContext facesContext;
            UIViewRoot uiViewRoot;
            HttpServletRequest request;
            {
                invoke(passwordHint, "getFacesContext"); result=facesContext;
                facesContext.getViewRoot();result=uiViewRoot;
                uiViewRoot.setViewId(anyString);
                
                invoke(passwordHint, "getRequest"); result=request;
                RequestUtil.getAppURL(request);
                
                passwordHint.getText(anyString); result = " ";
                
                mailEngine.send(message);
                 
                passwordHint.addMessage(anyString,any);
            }
        };
        
        String result = passwordHint.execute();
        
        assertEquals("success", result);
    }
    
    @Test
    public void execute_NormalSecenario_InvokeMailEngineSend() throws Exception {
        final PasswordHint passwordHint = new PasswordHint();
        passwordHint.setUsername("usernametesthint");
        
        final User user = new User ();
        final UserManager userManager = new MockUp<UserManager>(){
            @Mock
            public User getUserByUsername(String username) throws UsernameNotFoundException{
                return user;
            }
        }.getMockInstance();
        passwordHint.setUserManager(userManager);
        
        final SimpleMailMessage message = new SimpleMailMessage();
        passwordHint.setMessage(message);
        
        final MailEngine mailEngine = new MailEngine();
        passwordHint.setMailEngine(mailEngine);
        
        new NonStrictExpectations(passwordHint,mailEngine,RequestUtil.class){
            FacesContext facesContext;
            UIViewRoot uiViewRoot;
            HttpServletRequest request;
            {
                invoke(passwordHint, "getFacesContext"); result=facesContext;
                facesContext.getViewRoot();result=uiViewRoot;
                uiViewRoot.setViewId(anyString);
                
                invoke(passwordHint, "getRequest"); result=request;
                RequestUtil.getAppURL(request);
                
                passwordHint.getText(anyString); result = " ";
                
                mailEngine.send(message);
                 
                passwordHint.addMessage(anyString,any);
            }
        };
        
        passwordHint.execute();
         
        new Verifications(){
            {
                mailEngine.send(message); 
            }
        };
             
    }
    
    public class MailExceptionImpl extends MailException{

        public MailExceptionImpl(String msg, Throwable cause) {
            super(msg, cause);
        }

        public MailExceptionImpl(String msg) {
            super(msg);
        }
    }
    
    @Test
    public void execute_SendMailError_Invoke$addError() throws Exception {
        final PasswordHint passwordHint = new PasswordHint();
        passwordHint.setUsername("usernametesthint");
        
        final User user = new User ();
        final UserManager userManager = new MockUp<UserManager>(){
            @Mock
            public User getUserByUsername(String username) throws UsernameNotFoundException{
                return user;
            }
        }.getMockInstance();
        
        
        passwordHint.setUserManager(userManager);
        final SimpleMailMessage message = new SimpleMailMessage();
        passwordHint.setMessage(message);
        
        final MailEngine mailEngine = new MailEngine();
        passwordHint.setMailEngine(mailEngine);
        
        final Throwable throwable= new Throwable("test");
        final MailException me = new MailExceptionImpl("haloo",throwable);

        new NonStrictExpectations(passwordHint,mailEngine,RequestUtil.class){
            FacesContext facesContext;
            UIViewRoot uiViewRoot;
            HttpServletRequest request;

            {
                invoke(passwordHint, "getFacesContext"); result=facesContext;
                facesContext.getViewRoot();result=uiViewRoot;
                uiViewRoot.setViewId(anyString);
                
                invoke(passwordHint, "getRequest"); result=request;
                RequestUtil.getAppURL(request);
                
                passwordHint.getText(anyString); result = " ";
                
                mailEngine.send(message);result=me;
                
                passwordHint.addError(anyString); 
            }
        };
        
        passwordHint.execute();
         
        new Verifications(){
            {
                 passwordHint.addError(anyString); 
            }
        };      
    }

  
    @Test
    public void execute_UserNotFound_Invoke$addError() throws Exception {
        final PasswordHint passwordHint = new PasswordHint();
        final String username = "usernametesthint";
        passwordHint.setUsername(username);
        
        UserManager userManager = new MockUp<UserManager>(){
            @Mock
            public User getUserByUsername(String username) throws UsernameNotFoundException{
                throw new UsernameNotFoundException(username);
            }
        }.getMockInstance();
        
        passwordHint.setUserManager(userManager);
        
        new NonStrictExpectations(passwordHint,RequestUtil.class){
            FacesContext facesContext;
            UIViewRoot uiViewRoot;
            HttpServletRequest request;
            {
                invoke(passwordHint, "getFacesContext"); result=facesContext;
                facesContext.getViewRoot();result=uiViewRoot;
                uiViewRoot.setViewId(anyString);
                
                passwordHint.addError("login.passwordHint.error", username); 
         
            }
        };
        
        passwordHint.execute();
         
        new Verifications(){
            {
                passwordHint.addError("login.passwordHint.error", username); 
            }
        };
             
    }    
    
    
    // Pola dari : The Art of Unit Tesing pathen
    //             methodOrFunction_Secenario_Behavior
    @Ignore
    @Test
    public void methodOrFunction_Secenario_Behavior() throws Exception {

    }

}


/* pindah ke test intergartion - spring
import org.appfuse.service.MailEngine;
import org.appfuse.service.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.subethamail.wiser.Wiser;

import static org.junit.Assert.*;


public class PasswordHintTest extends BasePageTestCase {
    private PasswordHint bean;

    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new PasswordHint();
        bean.setUserManager((UserManager) applicationContext.getBean("userManager"));
        bean.setMessage((SimpleMailMessage) applicationContext.getBean("mailMessage"));
        bean.setMailEngine((MailEngine) applicationContext.getBean("mailEngine"));
        bean.setTemplateName("accountCreated.vm");
    }

    @Test
    public void testExecute() throws Exception {
        // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(getSmtpPort());
        wiser.start();

        bean.setUsername("user");
        assertEquals("success", bean.execute());
        assertFalse(bean.hasErrors());

        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the request
        assertNotNull(bean.getSession().getAttribute("messages"));
    }
}
*/