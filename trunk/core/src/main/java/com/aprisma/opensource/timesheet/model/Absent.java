/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author devxpbox
 */
@Entity
@DiscriminatorValue("ABS")
public class Absent extends CheckRoll implements  Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name="ABS_TYPE")
    private String type;
    
    @Column(name="ABS_REMARK")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "com.aprisma.opensource.timesheet.model.Absent[ id=" + getId() + " ]";
    }
    
}
