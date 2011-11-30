/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.template.dao.jpa;

import com.aprisma.opensource.template.dao.TempleteDao;
import com.aprisma.opensource.template.model.Templete;

import javax.persistence.EntityManager;
import org.appfuse.dao.jpa.GenericDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author devxpbox
 */
//@Repository("templeteDao")
public class TempleteDaoJpa extends GenericDaoJpa<Templete,String> implements TempleteDao {
    
    public TempleteDaoJpa() {
        super(Templete.class);
    }

    public TempleteDaoJpa(EntityManager entityManager) {
        super(Templete.class, entityManager);
    }

}
