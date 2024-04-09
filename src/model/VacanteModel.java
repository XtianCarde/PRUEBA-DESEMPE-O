package model;

import database.CRUD;
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

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Vacante objVacante = (Vacante) obj;

        try {
            String sql = "INSERT INTO vacante (empresa_id,titulo,descripcion,duracion,estado,tecnologia) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objVacante.getEmpresa_id());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDescripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listVacantes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id;";
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

                listVacantes.add(objVacante);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listVacantes;
    }

    public List<Object> findAllActivas() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listVacantes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id WHERE vacante.estado = 'ACTIVO';";
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

                listVacantes.add(objVacante);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listVacantes;
    }

    public List<Object> findByTitulo(String titulo) {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listVacantes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id WHERE vacante.titulo LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%" + titulo + "%");
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

                listVacantes.add(objVacante);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listVacantes;
    }

    public List<Object> findByTecnologia(String tecnologia) {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listVacantes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id WHERE vacante.tecnologia LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%" + tecnologia + "%");
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

                listVacantes.add(objVacante);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listVacantes;
    }
    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM vacante WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVacante.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,objVacante.toString() + "\nVacante eliminada correctamente!!");
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
        Vacante objVacante = (Vacante) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE vacante SET empresa_id = ?,titulo = ?,descripcion = ?,duracion = ?,estado = ?,tecnologia = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVacante.getEmpresa_id());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDescripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setInt(7,objVacante.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,objVacante.toString() + "\nVacante actualizada correctamente!!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isUpdated;
    }
}
