/**
******************************************************
* @file Profesor.java
* @author AirZs
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase del tipo Semestre.
*****************************************************/
package jramos.tiposDatos;
import java.util.ArrayList;

public class Semestre {
    static int idSemestreActual = 0;

    private int numSemestre;
    private ArrayList<Curso> ramos;
    private ArrayList<Integer> listCodRamos;
    private Carrera enCarrera;
    private int codEnCarrera;
    private int idSemestre;


    //////////////////////////////////////////////////////////////////////
    // Contructor

    /**
     * 1º Constructor de Semestre:
     * Este constructor debe ser utilizado para crear un objeto Semestre la primera vez. Se le asigna un 
     * id de forma automatica.
     *
     * @param numeroSemestre int con el numero del semestre
     * @param codEnLaCarrera int con el codigo de la carrera a la cual pertenece este semestre
     */
    public Semestre(int numeroSemestre, int codEnLaCarrera){
        this.numSemestre = numeroSemestre;
        this.codEnCarrera = codEnLaCarrera;
        this.idSemestre = ++idSemestreActual;
        this.ramos = new ArrayList<Curso>();
        this.listCodRamos = new ArrayList<Integer>();
    }

    /**
     * 2º Constructor de Semestre:
     * Este constructor debe ser utilizado para crear un objeto Semestre a partir de un registro o archivo.
     *
     * @param numeroSemestre int con el numero del semestre
     * @param codEnLaCarrera int con el codigo de la carrera a la cual pertenece este semestre
     * @param id int con el id del semestre
     */
    public Semestre(int numeroSemestre, int codEnLaCarrera, int id){
        this.numSemestre = numeroSemestre;
        this.codEnCarrera = codEnLaCarrera;
        this.idSemestre = id;
        this.ramos = new ArrayList<Curso>();
        this.listCodRamos = new ArrayList<Integer>();
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Obtener Variables

    /**
     * Metodo para obtener el numero del semestre
     *
     * @return int con el numero del semestre
     */
    public int getNumeroSemestre(){
        return this.numSemestre;
    }

    /**
     * Metodo para obtener los nombres de los ramos que existen en este semestre
     *
     * @return String con los nombres separados por |
     */
    public String getNombreRamos(){
        StringBuilder text = new StringBuilder();
        for (Curso curso: this.ramos){
            text.append(curso.getNombreCurso()).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obtener los codigos de los ramos que existen en este semestre
     *
     * @return String de los codigos separados por |
     */
    public String getCodigosRamos(){
        StringBuilder text = new StringBuilder();
        for (Integer codigo: this.listCodRamos){
            text.append(String.valueOf(codigo)).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obtener el nombre de la carrera a la cual pertenece este semestre
     *
     * @return String con el nombre de la carrera
     */
    public String getNombreCarreraPoseedora(){
        return this.enCarrera.getNombreCarrera();
    }

    /**
     * Metodo para obtener el codigo de la carrera que posee este semestre
     *
     * @return int con el codigo de la carrera
     */
    public int getCodigoEnCarrera(){
        return this.codEnCarrera;
    }

    /**
     * Metodo para obtener el id de este semestre
     *
     * @return int con el id de este semestre
     */
    public int getIdSemestre(){
        return this.idSemestre;
    }

    /**
     * Metodo para obtener el ultimo id del semestre utilizado
     *
     * @return int con el id de este semestre
     */
    public int getIdSemestreGlobal(){
        return Semestre.idSemestreActual;
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Modificar Variables

    /**
     * Metodo para agregar o quitar un ramo a este semestre
     *
     * @param cursoToAsig Objeto curso que se quiere agregar o quitar
     * @param selector 1: Agregar  -1: Quitar
     */
    public void modRamos(Curso cursoToAsig, int selector){
        if (selector == 1){
            if (!this.ramos.contains(cursoToAsig)){
                this.ramos.add(cursoToAsig);
            }
            else{

            }
        }
        else if (selector == -1){
            if (this.ramos.contains(cursoToAsig)){
                this.ramos.remove(cursoToAsig);
                this.modCodRamos(cursoToAsig.getCodigoCurso(), -1);
            }
            else{
                System.out.println("No existe ese curso...");
            }
            
        }
        else{
            System.out.println("Error en la seleccion...");
        }
    }

    public void modCodRamos(int codRamo, int selector){
        Integer codRamoWrap = new Integer(codRamo);
        if (selector == 1){
            if (!this.listCodRamos.contains(codRamoWrap)){
                this.listCodRamos.add(codRamoWrap);
            }
            else{
                System.out.println("el semestre ya contiene este ramo en su listado de cursos");
                return ;
            }
        }
        else if (selector == -1){
            if (this.listCodRamos.contains(codRamoWrap)){
                this.listCodRamos.remove(codRamoWrap);
            }
            else{
                System.out.println("No existe ese curso...");
            }

        }
        else{
            System.out.println("Error en la seleccion...");
        }
    }


    /**
     * Metodo para setear el Id del Semestre actual(variable static). Se debe obtener desde un archivo.
     * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
     * id.
     *
     * @param id int con el id static para ser seteado
     */
    public void setIdSemestres(int id){
        Semestre.idSemestreActual = id;
    }

}
