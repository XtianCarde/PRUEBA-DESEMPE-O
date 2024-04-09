package controller;

import Utils.Utils;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;

import static Utils.Utils.*;

public class ContratacionController {

    public static void create(){
        Object[] op = Utils.fromListToArray(instanceOfVacanteModel().findAllActivas());
        Object[] opC = Utils.fromListToArray(instanceOfCoderModel().findAll());
        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona la vacante por contratar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]);

        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona el/la coder a contratar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opC,
                opC[0]);

        Object estado =  JOptionPane.showInputDialog(null,
                "Selecciona el estado de la contratación",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estados(),
                estados()[0]);

        if (estado.toString() == "ACTIVO"){
            objVacante.setEstado("INACTIVO");
            instanceOfVacanteModel().update(objVacante);
        }

        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el salario para el coder"));

        Contratacion objContratacion = new Contratacion();
        objContratacion.setCoder_id(objCoder.getId());
        objContratacion.setVacante_id(objVacante.getId());
        objContratacion.setSalario(salario);
        objContratacion.setEstado(estado.toString());
        objContratacion.setVacante(objVacante);
        objContratacion.setCoder(objCoder);

        if (objCoder.getCv().contains(objVacante.getTecnologia())){
        instanceOfContratacionModel().insert(objContratacion);
        } else {
            JOptionPane.showMessageDialog(null, "No cumples con los requisitos.");
        }
    }

    public static void getAll(){
        String listContrataciones = "LISTA CONTRATACIONES\n";
        for (Object iterator: instanceOfContratacionModel().findAll()){
            Contratacion objContratacion = (Contratacion) iterator;
            listContrataciones += objContratacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listContrataciones);
    }

    public static void delete(){
        Object[] op = Utils.fromListToArray(instanceOfContratacionModel().findAll());
        int isDelete;
        Contratacion objContratacion = (Contratacion) JOptionPane.showInputDialog(null,
                "Selecciona la contratación a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]);

        isDelete = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar esta contratación?\n"+ objContratacion.toString());
        if (isDelete == 0) {
            instanceOfContratacionModel().delete(objContratacion);
        }
    }

    public static void update(){
        Object[] op = Utils.fromListToArray(instanceOfContratacionModel().findAll());
        Object[] op1 = Utils.fromListToArray(instanceOfVacanteModel().findAllActivas());
        Object[] opC = Utils.fromListToArray(instanceOfCoderModel().findAll());
        Contratacion objContratacion = (Contratacion) JOptionPane.showInputDialog(null,
                "Selecciona la contratación a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op,
                op[0]);

        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona la vacante por contratar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                op1,
                objContratacion.getVacante());

        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Selecciona el/la coder a contratar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opC,
                objContratacion.getCoder());

        String estado = "ACTIVO";
        double salario = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa el salario para el coder",objContratacion.getSalario()));

        objContratacion.setCoder_id(objCoder.getId());
        objContratacion.setVacante_id(objVacante.getId());
        objContratacion.setSalario(salario);
        objContratacion.setEstado(estado);
        objContratacion.setVacante(objVacante);
        objContratacion.setCoder(objCoder);
    }
}
