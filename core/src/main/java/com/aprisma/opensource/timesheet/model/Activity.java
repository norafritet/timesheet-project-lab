/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import org.appfuse.model.User;
//@NamedQueries(
//        @NamedQuery (name="", query="" )
// )
@Entity 
@Table(name="T_ACTIVITY")
public class Activity implements Serializable {
    
    @Id
    @Column(name="ID")
    private String id;
    

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User activityUser;

    @Column(name="ACT_DT")
    private Date activityDate;
    
    @Column(name="ACT_TIME_FROM")
    private Time timeFrom;
    
    @Column(name="ACT_TIME_TO")
    private Time timeTo;
    
    @Column(name="ACT_TYPE")
    private String type;
    
    @Column(name="ACT_NAME")
    private String name;
        
    @Column(name="ACT_CASE")
    private String activityCase;
       
    @Column(name="ACT_ICENTER_NO")
    private String icenterNo;
    
    @Column(name="ACT_STATUS")
    private String activityStatus;
        
    @Column(name="ACT_LOCATION")
    private String location;
    
    @Column(name="ACT_REMARK")
    private String remark;

    public String getActivityCase() {
        return activityCase;
    }

    public void setActivityCase(String activityCase) {
        this.activityCase = activityCase;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public User getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(User activityUser) {
        this.activityUser = activityUser;
    }

    public String getIcenterNo() {
        return icenterNo;
    }

    public void setIcenterNo(String icenterNo) {
        this.icenterNo = icenterNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activity other = (Activity) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.activityUser != null ? this.activityUser.hashCode() : 0);
        hash = 97 * hash + (this.activityDate != null ? this.activityDate.hashCode() : 0);
        return hash;
    }
}
