package ec.mil.he1;

import ec.mil.he1.util.JsfUtil;
import ec.mil.he1.util.JsfUtil.PersistAction;
import he1.seguridades.entities.nuevos.VUsuariosClasif;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.primefaces.model.UploadedFile;

@Named("personaController")
@SessionScoped
public class PersonaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(PersonaController.class.getName());

    @EJB
    private MedFirmaFacade medFirmaFacade;

    @EJB
    private ec.mil.he1.PersonaFacade ejbFacade;

    private List<Persona> lista = new ArrayList<>();

    public List<Persona> getFindPersonaCriterios(String par) {
        return ejbFacade.findPersonaCriterios(par);
    }

    private String cedula = "";
    VUsuariosClasif findByCedulaLogin = new VUsuariosClasif();

    @PostConstruct
    private void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        findByCedulaLogin = (VUsuariosClasif) session.getAttribute("vUsuariosClasif");
        lista = getFindPersonaCriterios("-111");
        Iterator<Persona> iterator = lista.iterator();
        while (iterator.hasNext()) {
            selected = iterator.next();

        }
        try {
            cedula = findByCedulaLogin.getCedulaLogin();
        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    private List<Persona> items = null;
    private Persona selected;

    private Persona persona = new Persona();

    public PersonaController() {

    }

    public Persona getSelected() {
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaFacade getFacade() {
        return ejbFacade;
    }

    public Persona prepareCreate() {
        selected = new Persona();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Persona> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Persona getPersona(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Persona> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Persona> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the lista
     */
    public List<Persona> getLista() {
        return lista;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the digital
     */
    public MedFirma getDigital() {
        return digital;
    }

    /**
     * @param digital the digital to set
     */
    public void setDigital(MedFirma digital) {
        this.digital = digital;
    }

    /**
     * @return the clave2
     */
    public String getClave2() {
        return clave2;
    }

    /**
     * @param clave2 the clave2 to set
     */
    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    @FacesConverter(forClass = Persona.class)
    public static class PersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaController controller = (PersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaController");
            return controller.getPersona(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Persona) {  
                Persona o = (Persona) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Persona.class.getName()});
                return null;
            }
        }

    }
    private MedFirma digital = new MedFirma();

    public void buttonAction(ActionEvent actionEvent) {
        MedFirma medFirma = new MedFirma();
        digital.setNombreArchivo("");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("cedula", cedula);

        List<MedFirma> findPersonaCriteriosCCDigi = medFirmaFacade.findPersonaCriteriosCC(cedula);
        Iterator<MedFirma> iteratordigi = findPersonaCriteriosCCDigi.iterator();

        while (iteratordigi.hasNext()) {
            digital = iteratordigi.next();

        }

        try {
            selected = new Persona();
            lista = getFindPersonaCriterios(cedula);
            Iterator<Persona> iterator = lista.iterator();
            while (iterator.hasNext()) {
                selected = iterator.next();

            }

            List<MedFirma> findPersonaCriteriosCC = medFirmaFacade.findPersonaCriteriosCC(cedula);
            //si esta vacia la lista se debe crear el registro en medfirma
            if (findPersonaCriteriosCC.isEmpty()) {
                medFirma.setCedula(cedula);
                medFirma.setNombres(selected.getNombres());
                medFirma.setApellidos(selected.getApellidos());
                medFirmaFacade.create(medFirma);
            } else {
                List<MedFirma> findPersonaCriterios = medFirmaFacade.findPersonaCriteriosCC(cedula);
                Iterator<MedFirma> iterator1 = findPersonaCriterios.iterator();
                while (iterator1.hasNext()) {
                    medFirma = iterator1.next();
                }
            }

            lista = new ArrayList<>();

        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            List<MedFirma> findPersonaCriteriosCC = medFirmaFacade.findPersonaCriteriosCC(cedula);
            Iterator<MedFirma> iterator1 = findPersonaCriteriosCC.iterator();
            while (iterator1.hasNext()) {
                medFirma = iterator1.next();

            }
        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void encuentraCedula() {

        MedFirma medFirma = new MedFirma();
        digital.setNombreArchivo("");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("cedula", (findByCedulaLogin.getCedulaLogin() == null) ? "-11" : findByCedulaLogin.getCedulaLogin());

        List<MedFirma> findPersonaCriteriosCCDigi = medFirmaFacade.findPersonaCriteriosCC(findByCedulaLogin.getCedulaLogin());
        Iterator<MedFirma> iteratordigi = findPersonaCriteriosCCDigi.iterator();

        while (iteratordigi.hasNext()) {
            digital = iteratordigi.next();

        }

        try {
            selected = new Persona();
            lista = getFindPersonaCriterios(cedula);
            Iterator<Persona> iterator = lista.iterator();
            while (iterator.hasNext()) {
                selected = iterator.next();

            }

            medFirma.setCedula(cedula);
            medFirma.setNombres(selected.getNombres());
            medFirma.setApellidos(selected.getApellidos());

            if (lista.size() == 1) {
                medFirmaFacade.create(medFirma);
            } else {
                addMessage("Usuario no registrado!!");
            }

            lista = new ArrayList<>();

        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            List<MedFirma> findPersonaCriteriosCC = medFirmaFacade.findPersonaCriteriosCC(cedula);
            Iterator<MedFirma> iterator1 = findPersonaCriteriosCC.iterator();
            while (iterator1.hasNext()) {
                medFirma = iterator1.next();

            }
        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void buttonAction2(ActionEvent actionEvent) {
        MedFirma medFirma = new MedFirma();
        digital.setNombreArchivo("");

        cedula = "";

        try {
            selected = new Persona();
            lista = getFindPersonaCriterios(cedula);
            Iterator<Persona> iterator = lista.iterator();
            while (iterator.hasNext()) {
                selected = iterator.next();

            }

        } catch (Exception e) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private String clave = "";
    private String clave2 = "";

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    @Resource(name = "refname_firmas")
    private DataSource refname_firmas;
    String habilita = "true";

    public void uploadupdate() throws SQLException {
        String cc = "";
        String habilita = "true";
        if (clave.equalsIgnoreCase(getClave2()) && !clave.isEmpty()) {

            if (file != null) {
                try (Connection con = refname_firmas.getConnection()) {

                    System.out.println("clave*************** = " + clave);

                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

                    cc = (String) session.getAttribute("cedula");

                    System.out.println(file.getFileName());
                    InputStream fin2 = file.getInputstream();
                    String contentType = file.getContentType();
                    try (PreparedStatement pre = con.prepareStatement("update  FIRMA.MED_FIRMA set NOMBRE_IMAGEN=?,DIGITAL=?, CONTENTTYPE=?, clave=?  where cedula=?  ")) {
                        pre.setString(1, file.getFileName());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3, contentType);
                        pre.setString(4, clave);
                        pre.setString(5, cc);
                        pre.executeUpdate();
                        System.out.println("Su firma digital se ha registrado!");

                        List<MedFirma> findPersonaCriteriosCC = medFirmaFacade.findPersonaCriteriosCC(cc);
                        Iterator<MedFirma> iterator = findPersonaCriteriosCC.iterator();

                        while (iterator.hasNext()) {
                            digital = iterator.next();

                        }

                    }
                    FacesMessage msg = new FacesMessage("Se ha cargado correctamente su fiirma", file.getFileName() + " is uploaded.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                } catch (IOException | SQLException e) {
                    System.out.println("Exception-File Upload." + e.getMessage());
                }
            } else {
                FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            FacesMessage msg = new FacesMessage("No se subio, revise sus claves no coinciden!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
