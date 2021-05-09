/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletawwebapp.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sampletawwebapp.entity.Administrador;

/**
 *
 * @author guzman
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "SampleTAWWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    public Administrador findByEmailAndPassword (String email, String password) {
        Query q;
        List<Administrador> lista;
        
        q = this.em.createQuery("SELECT a FROM Administrador a WHERE a.email = :email AND a.password = :password");
        q.setParameter("email", email);
        q.setParameter("password", password);
        lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }        
    }
    
}
