/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1;

import java.sql.*;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

@ManagedBean(name = "tableBean")
@SessionScoped

public class TableBean {

    @Resource(name = "refname_firmas")
    private DataSource refname_firmas;
    private String imageID;
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List<TableBean> getAllImage() throws SQLException {
        List<TableBean> imageInfo = new ArrayList<>();
        Connection con = refname_firmas.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id,NOMBRE_IMAGEN from FIRMA.MED_FIRMA order by id";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageID(rs.getString("id"));
                tbl.setImageName(rs.getString("NOMBRE_IMAGEN"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }

        }
        return imageInfo;
    }
}
