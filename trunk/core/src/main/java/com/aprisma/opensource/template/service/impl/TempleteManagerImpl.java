/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.template.service.impl;

import com.aprisma.opensource.timesheet.model.Activity;
import com.aprisma.opensource.timesheet.service.TempleteManager;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author devxpbox
 */
@Service("templeteManager")
public class TempleteManagerImpl extends GenericManagerImpl<Activity,String> implements TempleteManager{
    
    public TempleteManagerImpl() {
    }
    public TempleteManagerImpl(GenericDao genericDao) {
        super(genericDao);
    }

}
