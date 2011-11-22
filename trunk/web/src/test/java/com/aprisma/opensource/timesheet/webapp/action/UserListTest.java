package com.aprisma.opensource.timesheet.webapp.action;

import org.junit.Before;
import mockit.Mocked;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.appfuse.service.impl.UserManagerImpl;
import mockit.Expectations;
import mockit.Delegate;
import mockit.Mock;
import java.util.ArrayList;
import org.appfuse.model.User;
import java.util.List;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import org.appfuse.service.UserManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserListTest {
    
    @Mocked private UserManager userManager;
    private UserList bean;
    
    @Before
    public void before(){
        bean = new UserList();
        bean.setUserManager(userManager);
    }
    
    @Test
    public void getUsers_NormalInvoke_NotEmptyList() {
        
        new NonStrictExpectations() {
            
            {
                userManager.getUsers();
                result = new Delegate() {
                    public List<User> getUsers() {
                        List<User> list = new ArrayList<User>();
                        list.add(new User("user1"));
                        return list;
                    }
                };
            }
        };

        List result = bean.getUsers();

        assertFalse(result.isEmpty());
    }

    @Test
    public void getUsers_QueryAdmin_NotEmptyList() {

        bean.setQuery("admin");
        new NonStrictExpectations() {
            
            {
                userManager.search(anyString);
                result = new Delegate() {

                    public List<User> search(String query) {
                        List<User> list = new ArrayList<User>();
                        list.add(new User("user1"));
                        return list;
                    }
                };
            }
        };

        List result = bean.getUsers();

        assertFalse(result.isEmpty());
    }

    @Test
    public void getUsers_QueryAdmin_Call_userManager$search() {
 
        bean.setQuery("admin");
        new Expectations() {
            
            {
                userManager.search("admin");
                result = new Delegate() {

                    public List<User> search(String query) {
                        List<User> list = new ArrayList<User>();
                        list.add(new User("user1"));
                        return list;
                    }
                };
                times = 1;
            }
        };

        bean.getUsers();

    }

    @Test
    public void search_SearchAdmin_Success() {
        
        String result = bean.search();

        assertEquals("success", result);
    }
}
/*
import org.appfuse.service.UserManager;
import org.compass.gps.CompassGps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserListTest extends BasePageTestCase {
    private UserList bean;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CompassGps compassGps;

    @Override
    @Before
    public void onSetUp() {
        super.onSetUp();
        bean = new UserList();
        bean.setUserManager(userManager);
    }

    @Test
    public void testListUsers() throws Exception {
        assertTrue(bean.getUsers().size() >= 1);
        assertFalse(bean.hasErrors());
    }

    @Test
    public void testSearch() throws Exception {
        compassGps.index();
        bean.setQuery("admin");
        assertEquals("success", bean.search());
        assertTrue(bean.getUsers().size() == 1);
    }
}
*/