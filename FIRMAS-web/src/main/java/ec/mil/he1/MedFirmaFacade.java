/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author christian_ruiz
 */
@Stateless
public class MedFirmaFacade extends AbstractFacade<MedFirma> {

    @PersistenceContext(unitName = "ec.mil.he1_FIRMAS-web_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedFirmaFacade() {
        super(MedFirma.class);
    }

    public List<MedFirma> findPersonaCriteriosCC(String par) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MedFirma> cq = cb.createQuery(MedFirma.class);
        Root<MedFirma> root = cq.from(MedFirma.class);
        List<Predicate> predicatesOR = new ArrayList<>();
        predicatesOR.add(cb.equal(root.get(MedFirma_.cedula), par.toUpperCase()));
        cq.where(cb.or(predicatesOR.toArray(new Predicate[predicatesOR.size()])));
        List resultList = em.createQuery(cq).setMaxResults(1).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }
}
