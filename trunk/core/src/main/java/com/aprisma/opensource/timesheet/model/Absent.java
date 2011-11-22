/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author devxpbox
 */
@Entity
public class Absent extends CheckRoll implements  Serializable {
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        return "com.aprisma.opensource.timesheet.model.Absent[ id=" + getId() + " ]";
    }
    
}
