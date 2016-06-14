/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1;

import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author christian_ruiz
 */
@Named(value = "firma")
@SessionScoped
public class JSFManagedBeanFirma implements Serializable {

    @EJB
    private PersonaFacade2 personaFacade;



    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct");
        FacesContext facesContext = FacesContext.getCurrentInstance();

    }

    /**
     * Creates a new instance of JSFManagedBeanFirma
     */
    public JSFManagedBeanFirma() {
    }
    private String cc = "";

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    public void buttonAction(ActionEvent actionEvent) {

        System.out.println("actionEvent = " + cc);

        addMessage("Welcome to Primefaces!!");
    }

    private List<Persona> findPersonalCriterios = new ArrayList<>();

    public void addMessage(String summary) {
    
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the findPersonalCriterios
     */
    public List<Persona> getFindPersonalCriterios() {
        return findPersonalCriterios;
    }

    /**
     * @param findPersonalCriterios the findPersonalCriterios to set
     */
    public void setFindPersonalCriterios(List<Persona> findPersonalCriterios) {
        this.findPersonalCriterios = findPersonalCriterios;
    }
}
