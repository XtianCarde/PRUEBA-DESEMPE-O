import controller.CoderController;
import controller.ContratacionController;
import controller.EmpresaController;
import controller.VacanteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String opcionMain = "";
        do {
            opcionMain = JOptionPane.showInputDialog("""
                    Selecciona la opción a administrar.
                    1. Vacante.
                    2. Coder.
                    3. Contratacion.
                    4. Empresa.
                    5. Salir.
                    """);

            switch (opcionMain){
                case "1" -> {
                    String opcionV = "";
                    do {
                        opcionV = JOptionPane.showInputDialog("""
                                1. Crear vacante.
                                2. Buscar vacantes.
                                3. Buscar vacante por titulo.
                                4. Buscar vacante por tecnología.
                                5. Eliminar vacante.
                                6. Actualizar vacante.
                                7. Salir.
                                """);

                        switch (opcionV) {
                            case "1" -> VacanteController.create();
                            case "2" -> VacanteController.getAll();
                            case "3" -> VacanteController.getAllTitulo();
                            case "4" -> VacanteController.getAllTecnologia();
                            case "5" -> VacanteController.delete();
                            case "6" -> VacanteController.update();
                        }
                    }while (!opcionV.equals("7"));
                }
                case "2" -> {
                    String opcionCoder = "";
                    do {
                        opcionCoder = JOptionPane.showInputDialog("""
                                1. Crear coder.
                                2. Buscar coders.
                                3. Buscar coders por cohorte.
                                4. Buscar coders por clan.
                                5. Buscar coder por tecnología.
                                6. Eliminar coder.
                                7. Actualizar coder.
                                8. Salir.
                                """);

                        switch (opcionCoder){
                            case "1" -> CoderController.create();
                            case "2" -> CoderController.getAll();
                            case "3" -> CoderController.getAllByCohorte();
                            case "4" -> CoderController.getAllByClan();
                            case "5" -> CoderController.getAllByTecnologia();
                            case "6" -> CoderController.delete();
                            case "7" -> CoderController.update();
                        }
                    } while (!opcionCoder.equals("8"));
                }

                case "3" -> {
                    String opcionContratacion = "";
                    do {
                       opcionContratacion = JOptionPane.showInputDialog("""
                                1. Crear contratación.
                                2. Buscar contrataciones.
                                3. Eliminar contratación;
                                4. Actualizar contratación.
                                5. Salir.\s
                               """);

                       switch (opcionContratacion){
                           case "1" -> ContratacionController.create();
                           case "2" -> ContratacionController.getAll();
                           case "3" -> ContratacionController.delete();
                           case "4" -> ContratacionController.update();
                       }
                    } while (!opcionContratacion.equals("5"));
                }

                case "4" -> {
                    String opcionE = "";
                    do {
                        opcionE = JOptionPane.showInputDialog("""
                                1. Listar empresas.
                                2. Salir.
                                """);
                        switch (opcionE){
                            case "1" -> EmpresaController.getAll();
                        }
                    } while (!opcionE.equals("2"));
                }
            }
        } while (!opcionMain.equals("5"));
    }
}