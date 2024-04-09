package controller;


import Utils.Utils;
import entity.Empresa;

import javax.swing.*;

import static Utils.Utils.*;

public class EmpresaController {
    public static void getAll(){
        String listEmpresas = "LISTA DE EMPRESAS\n";
        for (Object iterator: instanceOfEmpresaModel().findAll()){
            Empresa objEmpresa = (Empresa) iterator;
            listEmpresas += objEmpresa.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listEmpresas);
    }
}
