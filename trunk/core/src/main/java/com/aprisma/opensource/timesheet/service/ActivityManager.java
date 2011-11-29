/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.service;


import org.appfuse.service.GenericManager;
import com.aprisma.opensource.timesheet.model.Activity;

import java.sql.Date;
import java.util.List;
/**
 *
 * @author user
 */
public interface ActivityManager extends GenericManager<Activity, Long> {
    public List<Activity> findByActivityWeek(Long userId, Date firstDate, Date endDate);
}