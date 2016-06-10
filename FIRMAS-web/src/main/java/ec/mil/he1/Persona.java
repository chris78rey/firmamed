/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author christian_ruiz
 */
@Entity
@Table(name = "PERSONAL")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ESPPRS_CODIGO")
    private String espprsCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_DE_DISPONIBILIDAD")
    private Character estadoDeDisponibilidad;
    @Size(max = 10)
    @Column(name = "CEDULA")
    private String cedula;
    @Size(max = 3)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 24)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 50)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 5)
    @Column(name = "NUMERO_CMA")
    private String numeroCma;
    @Size(max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 1)
    @Column(name = "PERMITIR_TURNO")
    private String permitirTurno;
    @Column(name = "PERSONAL_CIRUGIA")
    private Character personalCirugia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BENEFICIARIO")
    private Character beneficiario;
    @Size(max = 20)
    @Column(name = "CODIGO_CM")
    private String codigoCm;
    @Size(max = 20)
    @Column(name = "LIBRO_MSP")
    private String libroMsp;
    @Size(max = 20)
    @Column(name = "FOLIO_MSP")
    private String folioMsp;
    @Size(max = 20)
    @Column(name = "NUMERO_MSP")
    private String numeroMsp;
    @Size(max = 100)
    @Column(name = "PATH_FIRMA")
    private String pathFirma;
    @Size(max = 80)
    @Column(name = "TEXTO_XML")
    private String textoXml;
    @Size(max = 80)
    @Column(name = "CADENA_XML")
    private String cadenaXml;
    @Size(max = 80)
    @Column(name = "DES_TEXTO_XML")
    private String desTextoXml;

    public Persona() {
    }

    public Persona(String codigo) {
        this.codigo = codigo;
    }

    public Persona(String codigo, String espprsCodigo, String apellidos, String nombres, Character estadoDeDisponibilidad, Character beneficiario) {
        this.codigo = codigo;
        this.espprsCodigo = espprsCodigo;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.estadoDeDisponibilidad = estadoDeDisponibilidad;
        this.beneficiario = beneficiario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEspprsCodigo() {
        return espprsCodigo;
    }

    public void setEspprsCodigo(String espprsCodigo) {
        this.espprsCodigo = espprsCodigo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Character getEstadoDeDisponibilidad() {
        return estadoDeDisponibilidad;
    }

    public void setEstadoDeDisponibilidad(Character estadoDeDisponibilidad) {
        this.estadoDeDisponibilidad = estadoDeDisponibilidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroCma() {
        return numeroCma;
    }

    public void setNumeroCma(String numeroCma) {
        this.numeroCma = numeroCma;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPermitirTurno() {
        return permitirTurno;
    }

    public void setPermitirTurno(String permitirTurno) {
        this.permitirTurno = permitirTurno;
    }

    public Character getPersonalCirugia() {
        return personalCirugia;
    }

    public void setPersonalCirugia(Character personalCirugia) {
        this.personalCirugia = personalCirugia;
    }

    public Character getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Character beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getCodigoCm() {
        return codigoCm;
    }

    public void setCodigoCm(String codigoCm) {
        this.codigoCm = codigoCm;
    }

    public String getLibroMsp() {
        return libroMsp;
    }

    public void setLibroMsp(String libroMsp) {
        this.libroMsp = libroMsp;
    }

    public String getFolioMsp() {
        return folioMsp;
    }

    public void setFolioMsp(String folioMsp) {
        this.folioMsp = folioMsp;
    }

    public String getNumeroMsp() {
        return numeroMsp;
    }

    public void setNumeroMsp(String numeroMsp) {
        this.numeroMsp = numeroMsp;
    }

    public String getPathFirma() {
        return pathFirma;
    }

    public void setPathFirma(String pathFirma) {
        this.pathFirma = pathFirma;
    }

    public String getTextoXml() {
        return textoXml;
    }

    public void setTextoXml(String textoXml) {
        this.textoXml = textoXml;
    }

    public String getCadenaXml() {
        return cadenaXml;
    }

    public void setCadenaXml(String cadenaXml) {
        this.cadenaXml = cadenaXml;
    }

    public String getDesTextoXml() {
        return desTextoXml;
    }

    public void setDesTextoXml(String desTextoXml) {
        this.desTextoXml = desTextoXml;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mil.he1.Persona[ codigo=" + codigo + " ]";
    }
    
}
