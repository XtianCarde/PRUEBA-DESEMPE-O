package model;

import database.ConfigDb;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {

    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listEmpresas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Empresa objEmpresa = new Empresa(objResult.getInt("empresa.id"),
                        objResult.getString("empresa.nombre"),
                        objResult.getString("empresa.sector"),
                        objResult.getString("empresa.ubicacion"),
                        objResult.getString("empresa.contacto"));

                listEmpresas.add(objEmpresa);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listEmpresas;
    }

}
