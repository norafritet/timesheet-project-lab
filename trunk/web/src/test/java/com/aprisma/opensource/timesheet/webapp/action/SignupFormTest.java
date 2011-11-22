package com.aprisma.opensource.timesheet.webapp.action;

import com.aprisma.opensource.timesheet.webapp.util.RequestUtil;
import java.util.HashSet;
import mockit.Delegate;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import org.appfuse.model.Address;
import org.appfuse.model.User;
import org.appfuse.service.UserExistsException;
import org.junit.Test;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.apache.lucene.store.VerifyingLockFactory;
import org.appfuse.service.MailEngine;
import org.appfuse.service.RoleManager;
import org.appfuse.service.UserManager;


import org.junit.Before;
import org.springframework.mail.SimpleMailMessage;
import static org.junit.Assert.*;

public class SignupFormTest {

    private SignupForm bean;
    private User user;
    @Mocked
    private UserManager userManager;
    @Mocked
    private RoleManager roleManager;
    private SimpleMailMessage simpleMailMessage;
    @Mocked
    private MailEngine mailEngine;

    public void context_save_NormalInvoke() throws UserExistsException{
        simpleMailMessage = new SimpleMailMessage();

        bean = new SignupForm();
        bean.setUserManager(userManager);
        bean.setRoleManager(roleManager);
        bean.setMessage(simpleMailMessage);
        //bean.setMailEngine(mailEngine);
        //bean.setTemplateName("accountCreated.vm");

        user = new User("self-registered");
        user.setPassword("Password1");
        user.setConfirmPassword("Password1");
        user.setFirstName("First");
        user.setLastName("Last");

        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");
        user.setAddress(address);

        user.setEmail("self-registered@raibledesigns.com");
        user.setWebsite("http://raibledesigns.com");
        user.setPasswordHint("Password is one with you.");

        bean.setUser(user);
        
        new NonStrictExpectations(bean,RequestUtil.class) {

            HttpServletRequest request;
            HttpSession session;

            {
                bean.getRequest(); result = request;
                bean.addMessage(anyString,any); 
                
                bean.getSession(); result = session;
                userManager.saveUser(user); result= new Delegate(){
                    User saveUser(User user) throws UserExistsException{
                        user.setRoles(new HashSet());
                        return user;
                    }
                };
                //bean.addMessage("errors.existing.user", withNotNull());
                bean.addMessage("user.registered");
                bean.getText("signup.email.subject"); result="signup.email.subject";
                bean.getText("signup.email.message"); result="signup.email.message";
                RequestUtil.getAppURL(request); result ="getAppURL";
                bean.sendUserMessage(user,anyString,anyString);
            }
        };
    }
    @Test
    public void save_NormalInvoke_NoErrors() throws Exception {
        context_save_NormalInvoke();

        bean.save();

        assertFalse(bean.hasErrors());

    }
    @Test
    public void save_NormalInvoke_Call$getTextRequestSubjectValue() throws Exception {
        context_save_NormalInvoke();
        
        bean.save();

        new Verifications(){
            {
            bean.getText("signup.email.subject");times=1;
            }
        };
    }
    
    @Test
    public void save_NormalInvoke_Call$getTextRequestMessageValue() throws Exception {
        context_save_NormalInvoke();
        
        bean.save();

        new Verifications(){
            {
            bean.getText("signup.email.message");times=1;
            }
        };
    }
    @Test
    public void save_NormalInvoke_Call$sendUserMessage() throws Exception {
        
        context_save_NormalInvoke();
        
        bean.save();

        new Verifications(){
            {
            bean.sendUserMessage(user,anyString,anyString);times=1;
            }
        };
    }

    
    @Test
    public void save_NormalInvoke_ReturnMainMenu() throws Exception {
        context_save_NormalInvoke();
        // start SMTP Server
//        Wiser wiser = new Wiser();
//        wiser.setPort(getSmtpPort());
//        wiser.start();

        assertEquals("mainMenu", bean.save());

//        assertFalse(bean.hasErrors());

        // verify an account information e-mail was sent
//        wiser.stop();
//        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the session
//        assertNotNull(bean.getSession().getAttribute(Constants.REGISTERED));

//        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
/* pindah ke test intergartion - spring

import org.appfuse.Constants;
import org.appfuse.model.Address;
import org.appfuse.model.User;
import org.appfuse.service.MailEngine;
import org.appfuse.service.RoleManager;
import org.appfuse.service.UserManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.subethamail.wiser.Wiser;

import static org.junit.Assert.*;

public class SignupFormTest extends BasePageTestCase {
    private SignupForm bean;

    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new SignupForm();
        bean.setUserManager((UserManager) applicationContext.getBean("userManager"));
        bean.setRoleManager((RoleManager) applicationContext.getBean("roleManager"));
        bean.setMessage((SimpleMailMessage) applicationContext.getBean("mailMessage"));
        bean.setMailEngine((MailEngine) applicationContext.getBean("mailEngine"));
        bean.setTemplateName("accountCreated.vm");
    }

    @Test
    public void testExecute() throws Exception {
        User user = new User("self-registered");
        user.setPassword("Password1");
        user.setConfirmPassword("Password1");
        user.setFirstName("First");
        user.setLastName("Last");

        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");
        user.setAddress(address);

        user.setEmail("self-registered@raibledesigns.com");
        user.setWebsite("http://raibledesigns.com");
        user.setPasswordHint("Password is one with you.");
        bean.setUser(user);

       // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(getSmtpPort());
        wiser.start();

        assertEquals("mainMenu", bean.save());
        assertFalse(bean.hasErrors());

        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the session
        assertNotNull(bean.getSession().getAttribute(Constants.REGISTERED));

        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
*/