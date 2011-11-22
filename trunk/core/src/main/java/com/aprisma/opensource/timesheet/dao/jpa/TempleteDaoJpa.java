/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.dao.jpa;

import com.aprisma.opensource.timesheet.dao.TempleteDao;
import com.aprisma.opensource.timesheet.model.Activity;

import javax.persistence.EntityManager;
import org.appfuse.dao.jpa.GenericDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author devxpbox
 */
@Repository("templeteDao")

public class TempleteDaoJpa extends GenericDaoJpa<Activity,String> implements TempleteDao {
    
    public TempleteDaoJpa() {
        super(Activity.class);
    }

    public TempleteDaoJpa( EntityManager entityManager) {
        super(Activity.class, entityManager);
    }
    
 
}
