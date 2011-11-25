/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.dao;

import com.aprisma.opensource.timesheet.model.Activity;

import java.util.List;
import org.appfuse.dao.GenericDao;

/**
 *
 * @author user
 */
public interface ActivityDao extends GenericDao<Activity, Long> {
    
   public List<Activity> findByActivityUser(String activityUser);
}
