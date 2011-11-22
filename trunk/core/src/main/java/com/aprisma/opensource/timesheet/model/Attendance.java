/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ATT")
public class Attendance extends CheckRoll implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name="CHECK_IN")
    private Time checkIn;
    
    @Column(name="CHECK_OUT")
    private Time checkOut;

    public Time getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }
  
    @Override
    public String toString() {
        return "com.aprisma.opensource.timesheet.model.Attendance[ id=" + getId() + " ]";
    }
    
}
