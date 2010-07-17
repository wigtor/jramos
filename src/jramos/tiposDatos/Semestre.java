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
    private ArrayList<Integer> listIdRamos;
    private Carrera enCarrera;
    private int codEnCarrera;
    private int idSemestre;


    //////////////////////////////////////////////////////////////////////
    // Contructor

    /**
     * 1º Constructor de Semestre:
     * Este constructor debe ser utilizado para crear un objeto Semestre la primera vez. Se le asigna un 
     * id de forma automatica.
     * Se instancian los ArrayList de ramos y codigos de ramos que posee el semestre
     * @param numeroSemestre int con el numero del semestre
     * @param codEnLaCarrera int con el codigo de la carrera a la cual pertenece este semestre
     */
    public Semestre(int numeroSemestre, int codEnLaCarrera){
        this.numSemestre = numeroSemestre;
        this.codEnCarrera = codEnLaCarrera;
        this.idSemestre = ++Semestre.idSemestreActual;
        this.ramos = new ArrayList<Curso>();
        this.listIdRamos = new ArrayList<Integer>();
    }

    /**
     * 2º Constructor de Semestre:
     * Este constructor debe ser utilizado para crear un objeto Semestre a partir de un registro o archivo.
     * Se instancian los ArrayList de ramos y codigos de ramos que posee el semestre
     * @param numeroSemestre int con el numero del semestre
     * @param codEnLaCarrera int con el codigo de la carrera a la cual pertenece este semestre
     * @param id int con el id del semestre
     */
    public Semestre(int numeroSemestre, int codEnLaCarrera, int id){
        this.numSemestre = numeroSemestre;
        this.codEnCarrera = codEnLaCarrera;
        this.idSemestre = id;
        this.ramos = new ArrayList<Curso>();
        this.listIdRamos = new ArrayList<Integer>();
    }

    /**
     * 3º Constructor de Semestre:
     * Este constructor debe ser utilizado para crear un objeto Semestre a partir de una carrera que se esté creando
     * Se instancian los ArrayList de ramos y codigos de ramos que posee el semestre
     * Se asignan las referencias con la carrera del que fue creado.
     * @param numeroSemestre int con el numero del semestre
     * @param enLaCarrera objero carrera a la cual pertenece este semestre
     */
    public Semestre(int numeroSemestre, Carrera enLaCarrera){
        this.numSemestre = numeroSemestre;
        this.codEnCarrera = enLaCarrera.getCodigoCarrera();
        this.idSemestre = ++Semestre.idSemestreActual;
        this.ramos = new ArrayList<Curso>();
        this.listIdRamos = new ArrayList<Integer>();
        this.enCarrera = enLaCarrera;
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
        if (this.listIdRamos.size() != 0){
            for (Integer codigo: this.listIdRamos){
                text.append(String.valueOf(codigo)).append("|");
            }
            text.deleteCharAt(text.length()-1);
            return text.toString();
        }
        else
            return "";
    }

    /**
     * Este metodo obtiene los id de los cursos que posee el semestre.
     * @return Devuelve un ArrayList de Integer con los id de los ramos que posee el semestre
     */
    public ArrayList<Integer> getCodigosRamosArrayList()
    {       return this.listIdRamos;
    }

    /**
     * Este método retorna un arraylist con los codigos de cursos que contiene el semestre, 
     * se encarga de que los codigos de curso no se repitan, ya que pueden haber varios cursos iguales, pero de distinta sección en un semestre
     * @return Devuelve el arraylist con los codigos de cursos que contiene el semestre sin repetirse.
     */
    public ArrayList<Integer> getCodigoCursos()
    {       ArrayList<Integer> listCodCursos = new ArrayList();
            for (Curso curso : this.getCursosArrayList())
            {       if (!listCodCursos.contains(new Integer(curso.getCodigoCurso())))
                        listCodCursos.add(new Integer(curso.getCodigoCurso()));
            }
            return listCodCursos;
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
     * Obtiene los cursos que posee el semestre
     * 
     * @return Devuelve los cursos del semestre en un Arraylist de cursos.
     */
    public ArrayList<Curso> getCursosArrayList()
    {       return this.ramos;
    }
    /**
     * Metodo para obtener el id de este semestre
     *
     * @return int con el id de este semestre
     */
    public int getIdSemestre(){
        return this.idSemestre;
    }

    public String getIdCursosStr()
    {   StringBuilder text = new StringBuilder();
        if (this.listIdRamos.size() != 0){
            for (Integer codigo: this.listIdRamos){
                text.append(String.valueOf(codigo)).append("|");
            }
            text.deleteCharAt(text.length()-1);
            return text.toString();
        }
        else
            return "";
    }

    /**
     * Metodo para obtener el ultimo id del semestre utilizado
     *
     * @return int con el id de este semestre
     */
    static public int getIdSemestreGlobal(){
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
                this.modIdRamos(cursoToAsig.getIdCurso(), 1); //Creo que esa linea sobre, pues esa acción la realiza el referenciador
            }
            else{

            }
        }
        else if (selector == -1){
            if (this.ramos.contains(cursoToAsig)){
                this.ramos.remove(cursoToAsig);
                this.modIdRamos(cursoToAsig.getCodigoCurso(), -1);
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
     * Metodo para agregar o quitar un ramo a este semestre usando el codigo del ramo
     *
     * @param idRamo código del ramo que se quiere agregar o quitar del semestre
     * @param selector 1: Agregar  -1: Quitar
     */
    public void modIdRamos(int idRamo, int selector){
        Integer idRamoWrap = new Integer(idRamo);
        if (selector == 1){
            if (!this.listIdRamos.contains(idRamoWrap)){
                this.listIdRamos.add(idRamoWrap);
            }
            else{
                System.out.println("el semestre ya contiene este ramo en su listado de cursos");
                return ;
            }
        }
        else if (selector == -1){
            if (this.listIdRamos.contains(idRamoWrap)){
                this.listIdRamos.remove(idRamoWrap);
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
     * Metodo para hacer la referencia real de la carrera donde esta este semestre
     * 
     * @param carreraToAsig carrera para hacer la referencia
     */
    public void setEnCarrera(Carrera carreraToAsig){
        this.enCarrera = carreraToAsig;
    }

    /**
     * Metodo para setear el Id del Semestre actual(variable static). Se debe obtener desde un archivo.
     * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
     * id.
     *
     * @param id int con el id static para ser seteado
     */
    static public void setIdSemestres(int id){
        Semestre.idSemestreActual = id;
    }

    @Override
    public String toString()
    {   return this.getNumeroSemestre() + "° semestre";
    }
}
