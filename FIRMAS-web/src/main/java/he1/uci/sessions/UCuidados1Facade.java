/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.uci.sessions;


import he1.uci.entities.UCuidados1;
import he1.uci.entities.UOpcionPaciente;
import he1.uci.entities.UUciMenu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luis_guanoluiza
 */
@Stateless
public class UCuidados1Facade extends AbstractFacade<UCuidados1> {
    @PersistenceContext(unitName = "ec.mil.he1_FIRMAS-web_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UCuidados1Facade() {
        super(UCuidados1.class);
    }
    
    public List<UCuidados1> listCuidados(UOpcionPaciente opcPaciente, UUciMenu uciMenu) {
        StringBuilder sb = new StringBuilder("select object(o) from UCuidados1 o");
        sb.append(" where o.uOpcionPaciente = :opcPaciente AND O.uOpciones.uUciMenu = :uciMenu ORDER BY o.uOpciones.opcOrdinal");
        Query q = em.createQuery(sb.toString());
        q.setParameter("opcPaciente", opcPaciente);
        q.setParameter("uciMenu", uciMenu);
        q.setHint("eclipselink.refresh", true);
        return q.getResultList();
    }
   
}
