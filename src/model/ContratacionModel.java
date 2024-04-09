package model;

import database.CRUD;
import database.ConfigDb;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql = "INSERT INTO contratacion (vacante_id,coder_id,estado,salario) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objContratacion.getVacante_id());
            objPrepare.setInt(2,objContratacion.getCoder_id());
            objPrepare.setString(3,objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objContratacion.setId(objResult.getInt(1));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listContrataciones = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contratacion INNER JOIN coder ON contratacion.coder_id = coder.id " +
                    "INNER JOIN vacante ON vacante.id = contratacion.vacante_id " +
                    "INNER JOIN empresa ON empresa.id = vacante.empresa_id;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Empresa objEmpresa = new Empresa(objResult.getInt("empresa.id"),
                        objResult.getString("empresa.nombre"),
                        objResult.getString("empresa.sector"),
                        objResult.getString("empresa.ubicacion"),
                        objResult.getString("empresa.contacto"));

                Vacante objVacante = new Vacante(objResult.getInt("vacante.id"),
                        objResult.getInt("empresa.id"),
                        objResult.getString("vacante.titulo"),
                        objResult.getString("vacante.descripcion"),
                        objResult.getString("vacante.duracion"),
                        objResult.getString("vacante.tecnologia"),
                        objResult.getString("vacante.estado"),
                        objEmpresa);

                Coder objCoder = new Coder(objResult.getInt("coder.id"),
                        objResult.getString("coder.nombre"),
                        objResult.getString("coder.apellidos"),
                        objResult.getString("coder.documento"),
                        objResult.getString("coder.clan"),
                        objResult.getInt("coder.cohorte"),
                        objResult.getString("coder.cv"));

                Contratacion objContratacion = new Contratacion(objResult.getInt("contratacion.id"),
                        objResult.getInt("contratacion.vacante_id"),
                        objResult.getInt("contratacion.coder_id"),
                        objResult.getString("contratacion.fecha_aplicacion"),
                        objResult.getString("contratacion.estado"),
                        objResult.getDouble("contratacion.salario"),
                        objVacante,
                        objCoder);

                listContrataciones.add(objContratacion);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listContrataciones;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM contratacion WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objContratacion.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,objContratacion.toString() + "\nContratación eliminada correctamente!!");

            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isDeleted;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE contratacion SET vacante_id = ?,coder_id = ?,estado = ?,salario = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objContratacion.getVacante_id());
            objPrepare.setInt(2,objContratacion.getCoder_id());
            objPrepare.setString(3,objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,objContratacion.toString() + "\nContratación actualizada correctamente!!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isUpdated;
    }
}
