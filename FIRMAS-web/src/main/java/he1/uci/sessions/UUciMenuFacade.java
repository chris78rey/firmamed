/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.uci.sessions;


import he1.uci.entities.UUciMenu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luis_guanoluiza
 */
@Stateless
public class UUciMenuFacade extends AbstractFacade<UUciMenu> {
    @PersistenceContext(unitName = "ec.mil.he1_FIRMAS-web_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UUciMenuFacade() {
        super(UUciMenu.class);
    }
    
}
