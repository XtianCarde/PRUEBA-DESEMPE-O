package controller;
import Utils.Utils;
import entity.Coder;

import javax.swing.*;

import static Utils.Utils.*;
public class CoderController {

    public static void create(){
        String doc = JOptionPane.showInputDialog(null,"Ingresa el documento");
        if (!instanceOfCoderModel().findByDocumento(doc)){
            String nombre = JOptionPane.showInputDialog(null,"Ingresa el nombre");
            String apellidos = JOptionPane.showInputDialog(null,"Ingresa los apellidos");
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cohorte"));
            String cv = JOptionPane.showInputDialog(null,"Ingresa el cv");
            String clan = JOptionPane.showInputDialog(null,"Ingresa el clan");

            Coder objCoder = new Coder();
            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellidos);
            objCoder.setDocumento(doc);
            objCoder.setCohorte(cohorte);
            objCoder.setClan(clan);
            objCoder.setCv(cv);


            instanceOfCoderModel().insert(objCoder);
            JOptionPane.showMessageDialog(null,objCoder.toString());
        } else {
            JOptionPane.showMessageDialog(null,"Ya existe un(a) coder con dicho documento!.");
        }
    }


    public static void getAll(){
        String listCoders = "LISTA CODERS\n";
        for (Object iterator: instanceOfCoderModel().findAll()){
            Coder objCoder = (Coder) iterator;
            listCoders += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listCoders);
    }

    public static void getAllByCohorte(){
        try {
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cohorte a listar"));
            String listCoders = "LISTA CODERS POR COHORTE\n";
            if (instanceOfCoderModel().findByCohorte(cohorte) != null){
                for (Object iterator: instanceOfCoderModel().findByCohorte(cohorte)){
                    Coder objCoder = (Coder) iterator;
                    listCoders += objCoder.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null,listCoders);
            }else {
                JOptionPane.showMessageDialog(null,"Cohorte no encontrada!");
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Ingresa un valor numerico");
        }
    }

    public static void getAllByClan(){
        try {
            String clan = JOptionPane.showInputDialog("Ingresa el clan a listar");
            String listCoders = "LISTA CODERS POR CLAN\n";
            if (instanceOfCoderModel().findByClan(clan) != null){
                for (Object iterator: instanceOfCoderModel().findByClan(clan)){
                    Coder objCoder = (Coder) iterator;
                    listCoders += objCoder.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null,listCoders);
            }else {
                JOptionPane.showMessageDialog(null,"Clan no encontrado!");
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Ingresa un valor valido");
        }
    }

    public static void getAllByTecnologia(){
        try {
            String tecnologia = JOptionPane.showInputDialog("Ingresa la tecnologia a listar");
            String listCoders = "LISTA CODERS POR TECNOLOGIA\n";
            if (instanceOfCoderModel().findByTecnologia(tecnologia) != null){
                for (Object iterator: instanceOfCoderModel().findByTecnologia(tecnologia)){
                    Coder objCoder = (Coder) iterator;
                    listCoders += objCoder.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null,listCoders);
            }else {
                JOptionPane.showMessageDialog(null,"Tecnología no encontrada!");
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Ingresa un valor valido");
        }
    }

    public static void delete(){
        Object[] op = Utils.fromListToArray(instanceOfCoderModel().findAll());
        int isDelete;
        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona el/la coder a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]);

        isDelete = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar este coder?\n"+ objCoder.toString());
        if (isDelete == 0) {
            instanceOfCoderModel().delete(objCoder);
        }
    }

    public static void update(){
        Object[] op = Utils.fromListToArray(instanceOfCoderModel().findAll());
        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona el/la coder a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]);

        String doc = JOptionPane.showInputDialog(null,"Ingresa el documento",objCoder.getDocumento());
        if (!instanceOfCoderModel().findByDocumento(doc)){
            String nombre = JOptionPane.showInputDialog(null,"Ingresa el nombre",objCoder.getNombre());
            String apellidos = JOptionPane.showInputDialog(null,"Ingresa los apellidos",objCoder.getApellidos());
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrsa la cohorte"),objCoder.getCohorte());
            String cv = JOptionPane.showInputDialog(null,"Ingresa el cv",objCoder.getCv());
            String clan = JOptionPane.showInputDialog(null,"Ingresa el clan",objCoder.getClan());

            objCoder = new Coder();
            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellidos);
            objCoder.setDocumento(doc);
            objCoder.setCohorte(cohorte);
            objCoder.setClan(clan);
            objCoder.setCv(cv);

            instanceOfCoderModel().update(objCoder);
            JOptionPane.showMessageDialog(null,objCoder.toString());
        } else {
            JOptionPane.showMessageDialog(null,"Ya existe un(a) coder con dicho documento!.");
        }
    }
}
