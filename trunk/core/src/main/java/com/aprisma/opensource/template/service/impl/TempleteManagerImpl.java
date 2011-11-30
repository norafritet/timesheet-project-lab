/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.template.service.impl;

import com.aprisma.opensource.template.dao.TempleteDao;
import com.aprisma.opensource.template.model.Templete;
import com.aprisma.opensource.template.service.TempleteManager;
import org.appfuse.dao.GenericDao;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author devxpbox
 */
//@Service("templeteManager")
public class TempleteManagerImpl extends GenericManagerImpl<Templete,String> implements TempleteManager{
    private TempleteDao templeteDao;
    public TempleteManagerImpl() {
    }
    public TempleteManagerImpl(TempleteDao genericDao) {
        super(genericDao);
        this.templeteDao=templeteDao;
    }

}
