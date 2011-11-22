/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
//@NamedQueries(
//        @NamedQuery (name="", query="" )
// )
@Entity 
@Table(name="T_ACTIVITY")
public class Activity implements Serializable {
    
    @Id
    @Column(name="TEMPLATE_ID")
    private String id;
    
//    @Version
//    @Column(name="TEMPLATE_NM")
//    private Integer version;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }

    
}
