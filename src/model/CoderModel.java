package model;

import database.CRUD;
import database.ConfigDb;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Coder objCoder = (Coder) obj;

        try {
            String sql = "INSERT INTO coder (nombre,apellidos,documento,cohorte,cv,clan) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }
        }catch (
                SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return objCoder;
    }


    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listCoders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder = new Coder(objResult.getInt("id"),
                        objResult.getString("nombre"),
                        objResult.getString("apellidos"),
                        objResult.getString("documento"),
                        objResult.getString("clan"),
                        objResult.getInt("cohorte"),
                        objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listCoders;
    }

    public List<Object> findByCohorte(int cohorte) {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listCoders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE coder.cohorte = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,cohorte);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder = new Coder(objResult.getInt("id"),
                        objResult.getString("nombre"),
                        objResult.getString("apellidos"),
                        objResult.getString("documento"),
                        objResult.getString("clan"),
                        objResult.getInt("cohorte"),
                        objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return listCoders;
    }

    public List<Object> findByClan(String clan) {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listCoders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE coder.clan LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%" + clan + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder = new Coder(objResult.getInt("id"),
                        objResult.getString("nombre"),
                        objResult.getString("apellidos"),
                        objResult.getString("documento"),
                        objResult.getString("clan"),
                        objResult.getInt("cohorte"),
                        objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return listCoders;
    }

    public List<Object> findByTecnologia(String tecnologia) {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listCoders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE coder.cv LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%" + tecnologia + "%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Coder objCoder = new Coder(objResult.getInt("id"),
                        objResult.getString("nombre"),
                        objResult.getString("apellidos"),
                        objResult.getString("documento"),
                        objResult.getString("clan"),
                        objResult.getInt("cohorte"),
                        objResult.getString("cv"));

                listCoders.add(objCoder);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return listCoders;
    }

    public boolean findByDocumento(String documento) {
        Connection objConnection = ConfigDb.openConnection();
        boolean exist = false;
        try {
            String sql = "SELECT * FROM coder WHERE coder.documento LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,documento);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                exist = true;
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }

        return exist;
    }
    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDb.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM coder WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCoder.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,objCoder.toString() + "\nCoder eliminad@ correctamente!!");
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
        Coder objCoder = (Coder) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE coder SET nombre = ?,apellidos = ?,documento = ?,cohorte = ?,cv = ?,clan = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());
            objPrepare.setInt(7,objCoder.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,objCoder.toString() + "\nCoder actualizad@ correctamente!!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDb.closeConnection();
        }
        return isUpdated;
    }
}
