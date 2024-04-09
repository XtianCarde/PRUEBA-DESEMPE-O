package Utils;

import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.plaf.PanelUI;
import java.util.List;

public class Utils {
    public static <T> T[] fromListToArray(List<T> list){
        T[] array = (T[]) new Object[list.size()];
        int index = 0;
        for (T iterator: list){
            array[index++] = iterator;
        }
        return array;
    }

    public static String[] titulos(List<Object> list){
        String[] array = new String[list.size()];
        int index = 0;
        for (Object iterador: list){
            Vacante objVacante = (Vacante) iterador;
            array[index++] = objVacante.getTitulo();
        }
        return array;
    }

    public static String[] tecnologias(List<Object> list){
        String[] array = new String[list.size()];
        int index = 0;
        for (Object iterador: list){
            Vacante objVacante = (Vacante) iterador;
            array[index++] = objVacante.getTecnologia();
        }
        return array;
    }
    public static String[] estados(){
        return new String[]{"ACTIVO","INACTIVO"};
    }

    public static VacanteModel instanceOfVacanteModel(){
        return new VacanteModel();
    }

    public static ContratacionModel instanceOfContratacionModel(){
        return new ContratacionModel();
    }

    public static CoderModel instanceOfCoderModel(){
        return new CoderModel();
    }

    public static EmpresaModel instanceOfEmpresaModel(){
        return new EmpresaModel();
    }
}
