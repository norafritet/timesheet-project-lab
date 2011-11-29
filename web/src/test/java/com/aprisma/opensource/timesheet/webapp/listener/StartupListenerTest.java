package com.aprisma.opensource.timesheet.webapp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;
import org.appfuse.Constants;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;
import javax.servlet.ServletContext;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.appfuse.model.LabelValue;
import org.appfuse.service.LookupManager;
import org.compass.gps.CompassGps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupListenerTest {

    private StartupListener listener = new StartupListener();
    
    private @Mocked ServletContext servletContext;
    private @Mocked LookupManager lookupManager;
    private @Mocked CompassGps compassGps;
    
    public void setupStartupContext(final List<AuthenticationProvider> provaiders, final ServletContext context) {

        new NonStrictExpectations(WebApplicationContextUtils.class, StartupListener.class) {
            WebApplicationContext ctx;
            ProviderManager provider;
            {
                WebApplicationContextUtils.getRequiredWebApplicationContext(context); result = ctx;
                ctx.getBean("org.springframework.security.authentication.ProviderManager#0"); result = provider;
    
                provider.getProviders(); result = provaiders;
                StartupListener.setupContext(context);
            }
        };
    }

   public void setupFor_setupContext(final List<LabelValue> listRoles) {

        new NonStrictExpectations(WebApplicationContextUtils.class, StartupListener.class ) {
            WebApplicationContext ctx;
            {
                WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext); result = ctx;
                ctx.getBean("lookupManager");     result = lookupManager;
                
                servletContext.setAttribute(Constants.AVAILABLE_ROLES, listRoles);
                ctx.getBean(CompassGps.class);     result = compassGps;

            }
        };
    }
   
    @Test
    public void setupContext_NormalInvoke_Recall_mgr$getAllRoles() {
        final List<LabelValue> listRoles = new ArrayList<LabelValue> ();
        setupFor_setupContext(listRoles);
        
        StartupListener.setupContext(servletContext);

        new Verifications(){
            {
                lookupManager.getAllRoles(); times =1;
            }
        };
                
    }
    
    @Test
    public void setupContext_NormalInvoke_Recall_context$setAttribute() {
        
        final List<LabelValue> listRoles = new ArrayList<LabelValue> ();
        setupFor_setupContext( listRoles);
        
        StartupListener.setupContext(servletContext);
        
        new Verifications(){
            {
            servletContext.setAttribute(Constants.AVAILABLE_ROLES, listRoles); minTimes=1;
            }
        };
 
    }

    @Test
    public void setupContext_NormalInvoke_Recall_compassGps$index() {
        
        setupFor_setupContext(null);
        
        StartupListener.setupContext(servletContext);
        
        new Verifications(){
            {
                compassGps.index(); times = 1;
            }
        };
 
    }
    

    @Test
    public void contextInitialized_NormalInvoke_Recall_StartupListener$setupContext() {

        setupStartupContext(new ArrayList(), servletContext);

        listener.contextInitialized( new ServletContextEvent(servletContext) );

        new Verifications() {{ StartupListener.setupContext(servletContext); times = 1;}};
    }
    
    @Test
    public void contextInitialized_cssthemeNotNull_PutToConfig(){
        Map<String, Object> config = new HashMap();
        
    }
}
/**
 * This class tests the StartupListener class to verify that variables are
 * placed into the servlet context.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
/*
public class StartupListenerTest extends TestCase {
private MockServletContext sc = null;
private ServletContextListener listener = null;
private ContextLoaderListener springListener = null;

protected void setUp() throws Exception {
super.setUp();
sc = new MockServletContext("");
sc.addInitParameter(Constants.CSS_THEME, "simplicity");

// initialize Spring
sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
"classpath:/applicationContext-dao.xml, " +
"classpath:/applicationContext-service.xml, " + 
"classpath:/applicationContext-resources.xml");

springListener = new ContextLoaderListener();
springListener.contextInitialized(new ServletContextEvent(sc));
listener = new StartupListener();
}

protected void tearDown() throws Exception {
super.tearDown();
springListener = null;
listener = null;
sc = null;
}

public void testContextInitialized() {
listener.contextInitialized(new ServletContextEvent(sc));

assertTrue(sc.getAttribute(Constants.CONFIG) != null);
Map config = (Map) sc.getAttribute(Constants.CONFIG);
assertEquals(config.get(Constants.CSS_THEME), "simplicity");

assertTrue(sc.getAttribute(WebApplicationContext
.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null);
assertTrue(sc.getAttribute(Constants.AVAILABLE_ROLES) != null);
}
}
 */
