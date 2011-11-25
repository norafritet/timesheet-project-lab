/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.dao.jpa;


import com.aprisma.opensource.timesheet.dao.ActivityDao;
import com.aprisma.opensource.timesheet.model.Activity;


import org.appfuse.dao.jpa.GenericDaoJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author user
 */
@Repository("ActivityDao")
public class ActivityDaoJpa extends GenericDaoJpa<Activity, Long> implements ActivityDao {

    public ActivityDaoJpa() {
        super(Activity.class);
    }

    @Override
    public List<Activity> findByActivityUser(String activityUser) {
        Query q = getEntityManager().createQuery("select a from Activity a where a.activityUser=?");
        q.setParameter(1, activityUser);
        return q.getResultList();
    }
    
}
