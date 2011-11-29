/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.dao.jpa;


import com.aprisma.opensource.timesheet.dao.ActivityDao;
import com.aprisma.opensource.timesheet.model.Activity;


import java.sql.Date;
import org.appfuse.dao.jpa.GenericDaoJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author user
 */
@Repository("activityDao")
public class ActivityDaoJpa extends GenericDaoJpa<Activity, Long> implements ActivityDao {

    public ActivityDaoJpa() {
        super(Activity.class);
    }
 
    @Override
    public List<Activity> findByActivityWeek(Long userId, Date firstDate, Date endDate) {
        
        
        Query q = getEntityManager().createQuery("select a from Activity a where a.activityUser=? AND a.activityDate >= ? AND a.activityDate <= ?");
        
        q.setParameter(1, userId);
        q.setParameter(2, firstDate);
        q.setParameter(3, endDate);
        return q.getResultList();
    }
    
}
