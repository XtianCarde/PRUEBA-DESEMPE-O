package controller;

import Utils.Utils;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;

import static Utils.Utils.*;

public class VacanteController {

    public static void create(){
        Object[] opcionesE = Utils.fromListToArray(instanceOfEmpresaModel().findAll());



        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Selecciona la empresa para crear la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesE,
                opcionesE[0]);

        String titulo = JOptionPane.showInputDialog("Ingresa el titulo requerido");
        String tecnologia = JOptionPane.showInputDialog("Ingresa la tecnologia principal requerida");
        String descripcion = JOptionPane.showInputDialog("Ingresa una descripción requerida");
        String duracion = JOptionPane.showInputDialog("Ingresa la duracion");
        Object estado =  JOptionPane.showInputDialog(null,
                "Selecciona el estado de la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estados(),
                estados()[0]);

        Vacante objVacante = new Vacante();
        objVacante.setEmpresa_id(objEmpresa.getId());
        objVacante.setTitulo(titulo);
        objVacante.setTecnologia(tecnologia);
        objVacante.setDescripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEmpresa(objEmpresa);
        objVacante.setEstado(estado.toString());

        instanceOfVacanteModel().insert(objVacante);
        JOptionPane.showMessageDialog(null,objVacante.toString());
    }

    public static void getAll(){
        String listaVacantes = "LISTA DE VACANTES\n";

        for (Object iterador: instanceOfVacanteModel().findAll()){
            Vacante objVacante = (Vacante) iterador;
            listaVacantes += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listaVacantes);
    }

        public static String getAllString(){
        String listaVacantes = "LISTA DE VACANTES\n";

        for (Object iterador: instanceOfVacanteModel().findAll()){
            Vacante objVacante = (Vacante) iterador;
            listaVacantes += objVacante.toString() + "\n";
        }

        return listaVacantes;
    }

    public static void getAllTitulo(){
        Vacante op = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona el titulo a buscar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                titulos(instanceOfVacanteModel().findAll()),
                titulos(instanceOfVacanteModel().findAll())[0]);
        String titulo = op.getTitulo();
        String listVacante = "LISTA POR " +  titulo + "\n";

        for (Object iterador: instanceOfVacanteModel().findByTitulo(titulo)){
            Vacante objVacante = (Vacante) iterador;
            listVacante += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listVacante);
    }

    public static void getAllTecnologia(){
        Vacante op = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona la tecnologia a buscar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tecnologias(instanceOfVacanteModel().findAll()),
                tecnologias(instanceOfVacanteModel().findAll())[0]);
        String tecnologia = op.getTitulo();
        String listVacante = "LISTA POR " +  tecnologia + "\n";

        for (Object iterador: instanceOfVacanteModel().findByTecnologia(tecnologia)){
            Vacante objVacante = (Vacante) iterador;
            listVacante += objVacante.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listVacante);
    }

    public static void delete(){
        Object[] vacantes = Utils.fromListToArray(instanceOfVacanteModel().findAll());
        int isDeleted;
        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona la vacante a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vacantes,
                vacantes[0]);

        isDeleted = JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar esta vacante? " + objVacante.toString());
        if (isDeleted == 0) {
            instanceOfVacanteModel().delete(objVacante);
        }
    }

    public static void update(){
        Object[] vacantes = Utils.fromListToArray(instanceOfVacanteModel().findAll());
        Object[] opcionesE = Utils.fromListToArray(instanceOfEmpresaModel().findAll());
        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(null,
                "Selecciona la vacante a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vacantes,
                vacantes[0]);


        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Selecciona la empresa para editar la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesE,
                opcionesE[0]);

        String titulo = JOptionPane.showInputDialog(null,"Ingresa el titulo requerido",objVacante.getTitulo());
        String tecnologia = JOptionPane.showInputDialog(null,"Ingresa la tecnologia principal requerida",objVacante.getTecnologia());
        String descripcion = JOptionPane.showInputDialog(null,"Ingresa una descripción requerida",objVacante.getDescripcion());
        String duracion = JOptionPane.showInputDialog(null,"Ingresa la duracion",objVacante.getDuracion());
        Object estado =  JOptionPane.showInputDialog(null,
                "Selecciona el estado de la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estados(),
                objVacante.getEstado());

        objVacante = new Vacante();
        objVacante.setEmpresa_id(objEmpresa.getId());
        objVacante.setEmpresa(objEmpresa);
        objVacante.setTitulo(titulo);
        objVacante.setTecnologia(tecnologia);
        objVacante.setDescripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEstado(estado.toString());


        instanceOfVacanteModel().update(objVacante);
    }


}
