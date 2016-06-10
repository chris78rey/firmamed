package ec.mil.he1;

import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author christian_ruiz
 */
@Named(value = "uploadImage")
@SessionScoped

public class UploadImage implements Serializable {

    @ManagedProperty("#{personaController}")
    private PersonaController personacontroller;
    @EJB
    private MedFirmaFacade medFirmaFacade;


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

    @Resource(name = "refname_firmas")
    private DataSource refname_firmas;

    private static final long serialVersionUID = 1L;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws SQLException {
        habilita = "true";
        System.out.println("sssss");
        if (file != null) {
            try (Connection con = refname_firmas.getConnection()) {
                System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                String contentType = file.getContentType();
                try (PreparedStatement pre = con.prepareStatement("insert into FIRMA.MED_FIRMA (NOMBRE_IMAGEN,DIGITAL, CONTENTTYPE) values(?,?,?)")) {
                    pre.setString(1, file.getFileName());
                    pre.setBinaryStream(2, fin2, file.getSize());
                    pre.setString(3, contentType);
                    pre.executeUpdate();
                    System.out.println("Inserting Successfully!");
                }
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (IOException | SQLException e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        } else {
            FacesMessage msg = new FacesMessage("Please select image!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    private MedFirma digital = null;

    public void uploadupdate() throws SQLException {
        String cc = "";
        habilita = "true";
        if (clave.equalsIgnoreCase(clave2)) {

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
    private String habilita = "true";

    public void listen1(AjaxBehaviorEvent event) {
        System.out.println("event = " + event);
        if (clave.equalsIgnoreCase(clave2)) {
            habilita = "false";
        }

    }

    /**
     * @return the habilita
     */
    public String getHabilita() {
        return habilita;
    }

    /**
     * @param habilita the habilita to set
     */
    public void setHabilita(String habilita) {
        this.habilita = habilita;
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
}
