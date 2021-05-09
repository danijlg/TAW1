/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletawwebapp.dao;

import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sampletawwebapp.entity.Customer;

/**
 *
 * @author guzman
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "SampleTAWWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    public List<Customer> findBySimilarName (String filtro) {
        Query q;
        
        q = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :nombre");
        q.setParameter("nombre", "%" + filtro + "%");
        return q.getResultList();        
    }
    
    public List<Customer> findBySimilarName2 (String filtro) {
        Query q;
        
        q = em.createNamedQuery("Customer.findBySimilarNameEmail");
        q.setParameter("nombre", "%" + filtro + "%");
        return q.getResultList();                       
    }
    
    public List<Customer> findByMicroMarket (String [] filtroSuper) {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c WHERE c.zip.zipCode IN :supermercado");
        q.setParameter("supermercado", Arrays.asList(filtroSuper));
        return q.getResultList();                       
    }
    

    public List<Customer> findBySimilarNameOrMicroMarket (String filtroNombre, String [] filtroSuper) {
        Query q;
        List<Customer> lista;
        
        if (filtroNombre != null && filtroNombre.length() >0 &&
            filtroSuper != null  && filtroSuper.length > 0) {
            q = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :nombre OR c.zip.zipCode IN :supermercado");
            q.setParameter("nombre", "%" + filtroNombre + "%");
            q.setParameter("supermercado", Arrays.asList(filtroSuper));
            lista = q.getResultList();                                               
        } else if (filtroNombre == null || filtroNombre.length()==0) {
            lista = findByMicroMarket (filtroSuper);
        } else {
            lista = findBySimilarName (filtroNombre);
        }        
        return lista;       
    }
    
    
    
    
    
}
