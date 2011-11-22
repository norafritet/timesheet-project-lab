package com.aprisma.opensource.template.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@NamedQueries({
    @NamedQuery(name = "ssss.ssss",
    query = "select p from Templete")
})

@Entity
@Table(name = "T_MODEL_TEMPLATE")
public class Templete implements Serializable {

    @Id
    @Column(name = "TEMPLATE_ID")
    private String id;
    @Version
    @Column(name = "TEMPLATE_NM")
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}