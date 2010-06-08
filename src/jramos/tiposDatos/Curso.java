//Probando cambios con git-bash

/**
 ******************************************************
 * @file Profesor.java
 * @author AirZs
 * @date mayo 2010
 * @version 0.1
 * Esta clase representa un curso de la universidad
 * Se define como una instancia de un curso diferente cuando un
 * curso posee un codigo de curso y sección distinto a otro curso
 * Para diferenciar una instancia de otra se usa un id.
 * @brief En este archivo se especifica la clase del tipo Curso.
 *****************************************************/

package jramos.tiposDatos;
import java.util.ArrayList;

public class Curso {
    static int idCursoActual = 0;

    private String nomCurso;
    private String descrip;
    private int codCurso;
    private String seccion;
    private Carrera enCarrera;
    private int codigoCarrera;
    private Profesor profeAsig;
    private int idProfeAsig;
    private ArrayList<Integer> listSalas;
    private ArrayList<Hora> horario;
    private int idCurso;
    private Semestre enSemestre;
    private int idSemestre;


    //////////////////////////////////////////////////////////////////////
    // Contructor

    /**
     * 1º Constructor para Curso:
     * Este constructor debe ser utilizado para crear un objeto Curso la primera vez.
     * Se le asigna un id de forma automática.
     * Se instancian todos los ArrayList del objeto como listas vacias.
     * @param nombreRamo Nombre del Ramo
     * @param codigoCurso Codigo del Curso
     */
    public Curso(String nombreRamo, int codigoCurso){

        this.nomCurso = nombreRamo;
        this.codCurso = codigoCurso;
        this.enCarrera = null;
        this.codigoCarrera = 0;
        this.listSalas = new ArrayList<Integer>();
        this.horario = new ArrayList<Hora>();
        this.idCurso = ++idCursoActual;
        this.enSemestre = null;
        this.idSemestre = 0;
        this.seccion = "0";
        this.descrip = "";
    }

    /**
     * 2º Constructor para Curso:
     * Este constructor debe ser utilizado para crear un objeto Profesor
     * a partir de un registro o archivo.
     * Se instancian todos los ArrayList del objeto como listas vacias.
     * @param nombreRamo Nombre del Ramo
     * @param codigoCurso Codigo del Curso
     * @param id int con el id del Curso
     */
    public Curso(String nombreRamo, int codigoCurso, int id){
        this.nomCurso = nombreRamo;
        this.codCurso = codigoCurso;
        this.enCarrera = null;
        this.codigoCarrera = 0;
        this.listSalas = new ArrayList<Integer>();
        this.horario = new ArrayList<Hora>();
        this.idCurso = id;
        this.enSemestre = null;
        this.idSemestre = 0;
        this.seccion = "0";
        this.descrip = "";
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Obtener Variables

   /**
    * Metodo para obetener el nombre del curso
    *
    * @return String con el nombre del curso
    */
   public String getNombreCurso(){
       return this.nomCurso;
    }

   /**
    * Metodo para obtener la descripcion del curso
    * Si la descripción es un string vacio, devuelve un mensaje diciendo que no hay descripción.
    * @return String con la descripcion del curso
    */
   public String getDescripcion(){
        if (!this.descrip.contentEquals("")){
            return this.descrip;
        }
        else{
            return "No Hay Descripción Disponible...";
        }
        
    }

   /**
    * Metodo que obtiene el id del semestre al que pertenece el curso.
    *
    * @return Devuelve un ArrayList de Integer con los id de los semestres que tienen al curso.
    */
   public int getIdSemestre(){
       return this.idSemestre;
   }

   /**
    * Obtiene el código del curso
    *
    * @return valor entero con el código del curso.
    */
   public int getCodigoCurso(){
        return this.codCurso;
    }

   /**
    * Obtiene la sección del curso
    *
    * @return String con la sección del curso
    */
   public String getSeccion(){
        return this.seccion;
    }

   /**
    * Obtiene la carrera que contiene a este curso
    *
    * @return Devuelve un String con el nombre de la carrera al cual pertenece el curso
    */
   public String getEnCarreraStr(){
        if (this.enCarrera != null)
        {   return this.enCarrera.toString();
        }
        else
            return "";
    }

   /**
    * Obtiene el código de la carrera que posee al curso
    *
    * @return Devuelve un entero con el código de la carrera en que se dicta el curso.
    */
   public int getEnCarreraCodigo(){
       return this.codigoCarrera;
   }

   /**
    * Obtiene la carrera a la que pertenece el curso.
    *
    * @return Devuelve un objeto tipo Carrera con la carrera donde se dicta el curso.
    */
   public Carrera getEnCarrera()
   {        return this.enCarrera;
   }

   /**
    * Obtiene el semestre donde se dicta el curso.
    * 
    * @return Devuelve el objeto Semestre al cual pertenece este curso
    */
   public Semestre getEnSemestre()
   {        return this.enSemestre;
   }
   /**
    * Obtiene el nombre del profesor al que esta asignado este curso
    * Si el curso no tiene profesor asignado devuelve un String vacio
    * @return String con el nombre del profesor asignado al curso
    */
   public String getNombreProfesor(){
       if (this.profeAsig != null)
            return this.profeAsig.getNombreProfesor();
       else
           return "";
   }

   /**
    * Metodo para obtener el id del profesor que esta asignado a este curso
    * Si no existe un profesor asignado el valor pedido vale 0.
    * @return valor entero con el id del profesor asignado al curso.
    */
   public int getIdProfeAsig(){
       return this.idProfeAsig;
   }

   /**
    * Metodo para obtener las salas donde se imparte este curso
    * Funciona concatenando los numeros de sala asignadas al curso e
    * intercalandole un "|" entre salas.
    * @return String con los numeros de las salas separados por un "|"
    */
   public String getSalas(){
        StringBuilder text = new StringBuilder();
        if (this.listSalas.size() != 0)
        {   for (Integer numSala: this.listSalas){
                text.append(String.valueOf(numSala)).append("|");
            }
            text.deleteCharAt(text.length()-1);
            return text.toString();
        }
        else
            return "";
   }

   /**
    * Metodo para obtener los horarios de este curso
    * Funciona concatenando las horas asignadas al curso e
    * intercalandole un "|" entre las horas.
    * @return String con los horarios con formato String separados por un  "|"
    */
   public String getHorario(){
      StringBuilder text = new StringBuilder();
      if (this.horario.size() != 0 )
      {     for (Hora hora: this.horario){
                try
                {     text.append(hora.getHoraStr()).append("|");
                }
                catch (Exception e)
                {     System.out.println("Error al pedir datos de hora");
                }
            }
            text.deleteCharAt(text.length()-1);
      return text.toString();
      }
      else
          return "";
   }

   /**
    * Metodo para obtener el id del curso
    *
    * @return valor entero con el id del curso
    */
   public int getIdCurso(){
       return this.idCurso;
   }

   /**
    * Obtiene el valor del contador de id de la clase curso.
    *
    * @return valor entero con el contador id de la clase curso.
    */
   static public int getIdCursoGlobal(){
       return Curso.idCursoActual;
   }

   //////////////////////////////////////////////////////////////////////
   // Metodos de Modificar Variables

   /**
    * Metodo para agregar la descripcion al curso
    *
    * @param desc String con la descripcion del curso
    */

   public void setDescripcion(String desc){
       this.descrip = desc;
   }

    @Override
   public String toString()
    {       return this.getNombreCurso();
    }

   /**
    * Metodo para agregar el codigo del curso
    *
    * @param codigo int con el codigo del curso
    */
   public void setCodigoCurso(int codigo){
       this.codCurso = codigo;
   }

   /**
    * Metodo para agregar la seccion del curso
    *
    * @param codSec Codigo con el formato BNF <codSec>:= <letra>-<num><num>
    */
   public void setSeccion(String codSec){
       this.seccion = codSec;
   }

   /**
    * Metodo para hacer la conexion al profesor que esta asignado a este curso.
    * Actualiza automaticamente el rut del profesor
    *
    * @param profesorToAsign Objeto Profesor
    */
   public void setProfesor(Profesor profesorToAsig){
       this.profeAsig = profesorToAsig;
       if (this.profeAsig != null)
            this.idProfeAsig = profesorToAsig.getIdProfesor();
       else
            this.idProfeAsig = 0;
   }

   /**
    * Metodo para agregar el id del profesor que esta asignado a este curso (Usese como referencia)
    *
    * @param id int con el id del profesor
    */
   public void setIdProfeAsig(int id){
       this.idProfeAsig = id;
   }

   /**
    * Metodo para setear el código de la carrera que contiene a este curso (Usese como referencia)
    *
    * @param codigo int con el código de la carrera
    */
   public void setCodigoEnCarrera(int codigo){
       this.codigoCarrera = codigo;
   }

   /**
    * Metodo para setear la Carrera que contienen a este curso
    *
    * @param carrera Objeto carrera que se desea agregar o quitar
    */
   public void setCarrera(Carrera carreraToAsig){
      this.enCarrera = carreraToAsig;
   }

   /**
    * Metodo para agregar o quitar una de las salas en donde se imparte este curso
    *
    * @param sala int con el numero de la sala
    * @param selector 1: Agregar  -1: Quitar
    */
   public void modListaSalas (int sala, int selector){
       if (selector == 1)
       {    this.listSalas.add(new Integer(sala));
       }
       else if (selector == -1)
       {    if (this.listSalas.contains(sala)){
                this.listSalas.remove(new Integer(sala));
            }
            else
            {   System.out.println("No existe la sala...");
            }
       }
       else{
           System.out.println("Error en la seleccion...");
       }
   }

   /**
    * Metodo para agregar o quitar una bloque horario donde se imparte este curso
    *
    * @param horaToAsign Objeto Hora que se desea agregar o quitar
    * @param selector 1: Agregar  -1: Quitar
    */
   public void modHorario(Hora horaToAsig, int selector){
       if (selector == 1)
       {    this.horario.add(horaToAsig);
       }
       else if (selector == -1)
       {    if (this.horario.contains(horaToAsig)){
                this.horario.remove(horaToAsig);
            }
            else{
                System.out.println("No existe esa hora...");
            }
       }
       else{
           System.out.println("Error en la seleccion...");
       }
   }

   /**
    * Metodo para setear el Id del curso actual(variable static). Se debe obtener desde un archivo.
    * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
    * id.
    * 
    * @param id int con el id static para ser seteado
    */
   static public void setIdCursos(int id){
       Curso.idCursoActual = id;
   }

   /**
    * Metodo para setear el id del semestre al cual pertenece el curso.
    *
    * @param id int con el id para agregar o quitar
    */
   public void modIdSemestre(int id, int selector){
       this.idSemestre = id;
   }

   /**
    * Metodo para setear el semestre al que pertenece el curso.
    *
    * @param semestreToAsig Objeto semestre que se quiere agregar o quitar
    */
   public void setSemestre(Semestre semestreToAsig){
       this.enSemestre = semestreToAsig;
   }
}
