// Probando nuevos cambios para que el jefe este contento xD

/**
******************************************************
* @file Profesor.java
* @author AirZs
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase del tipo Profesor.
*****************************************************/

package jramos.tiposDatos;
import java.util.ArrayList;

public class Profesor {
    static int idProfesorActual = 0;

    private String nomProfe;
    private ArrayList<Hora> horaDisp;
    private ArrayList<Hora> horaOcup;
    private ArrayList<Integer> cursosDisp; //Solo codigos de curso
    private ArrayList<Curso> cursosAsig; //Referencias reales de curso
    private ArrayList<Integer> idCursosAsig; //Solo id de cursos
    private int rutProfe;
    private int idProfesor;

    //Contructor

    /**
     * 1º Constructor para Profesor:
     * Este constructor debe ser utilizado para crear un objeto Profesor la primera vez. Se le asigna un
     * id de forma automatica.
     *
     * @param nombreProfesor string con el Nombre del Profesor
     * @param cursosParaImpartir ArrayList<Integer> con los codigos de los cursos que este profesor puede impartir
     */
    public Profesor(String nombreProfesor, ArrayList<Integer> cursosParaImpartir){
        this.nomProfe = nombreProfesor;
        this.cursosDisp = cursosParaImpartir;
        this.horaDisp = new ArrayList<Hora>();
        this.horaOcup = new ArrayList<Hora>();
        this.cursosAsig = new ArrayList<Curso>();
        this.idCursosAsig =  new ArrayList<Integer>();
        this.idProfesor = ++idProfesorActual;

    }

    /**
     * 2º Constructor para Profesor:
     * Este constructor debe ser utilizado para crear un objeto Profesor a partir de un registro o archivo.
     *
     * @param nombreProfesor string con el Nombre del Profesor
     * @param cursosParaImpartir ArrayList<Integer> con los codigos de los cursos que este profesor puede impartir
     * @param id int con el id del profesor
     */
    public Profesor(String nombreProfesor, ArrayList<Integer> cursosParaImpartir, int id){
        this.nomProfe = nombreProfesor;
        this.cursosDisp = cursosParaImpartir;
        this.horaDisp = new ArrayList<Hora>();
        this.horaOcup = new ArrayList<Hora>();
        this.cursosAsig = new ArrayList<Curso>();
        this.idCursosAsig =  new ArrayList<Integer>();
        this.idProfesor = id;

    }

    // Metodos de Obtener variables

    /**
     * Metodo para obtener el nombre del Profesor
     *
     * @return String con el nombre del profesor
     */
    public String getNombreProfesor(){
        return this.nomProfe;
    }

    /**
     * Metodo para obtener las horas que el profesor tiene disponibles
     *
     * @return String con las horas en formato Str separadas con |
     */
    public String getHorasDisponibles(){ 
        StringBuilder text = new StringBuilder();
        for (Hora hora: this.horaDisp){
            try{
                text.append(hora.getHoraStr()).append("|");
            }
            catch (Exception e){
                System.out.println("Error al pedir datos de hora");
            }
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obtener las horas que el profesor yatiene asignadas
     *
     * @return String con las horas en formato Str separadas con |
     */
    public String getHorasAsignadas(){
        StringBuilder text = new StringBuilder();
        if (this.horaOcup.size() !=0)
        {   for (Hora hora: this.horaOcup){
                try{
                    text.append(hora.getHoraStr()).append("|");
                }
                catch (Exception e){
                    System.out.println("Error al pedir datos de hora");
                }
            }
            text.deleteCharAt(text.length()-1);
        }
        return text.toString();
    }

    /**
     * Metodo para obtener los codigos de los curso que puede impartir este profesor
     *
     * @return String con los codigos separados con |
     */
    public String getCursosQueImparte(){
        StringBuilder text = new StringBuilder();
        for (Integer codCurso: this.cursosDisp){
            text.append(String.valueOf(codCurso)).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obtener los codigos  y las secciones de los curso que ya esta impartiendo este profesor
     *
     * @return String con formato BNF  <curso>:=<codCurso>/<Seccion> Separados con |
     */
    public String getCursosAsignados(){
        int posicion = 0;
        StringBuilder text = new StringBuilder();
        for (Curso curso: this.cursosAsig){
            text.append(String.valueOf(curso.getCodigoCurso())).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obetener los codigos de los cursos asignados
     * 
     * @return String con los codigos separados por |
     */
    public String getIdCursosAsignados(){
        int posicion = 0;
        StringBuilder text = new StringBuilder();
        for (Integer idCurso: this.idCursosAsig){
            text.append(String.valueOf(idCurso));
            text.append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    public ArrayList<Integer> getIdCursosAsignadosArrayList(){
        return this.idCursosAsig;
    }

    /**
     * Metodo para obtener el rut del profesor
     *
     * @return int con el rut del profesor
     */
    public int getRutProfesor(){
        return this.rutProfe;
    }

    /**
     * Metodo para obtener el id del profesor
     *
     * @return int con el id del profesor
     */
    public int getIdProfesor(){
        return this.idProfesor;
    }

    /**
     * Metodo para obtener el ultimo id del profesor utilizado
     *
     * @return int con el id del profesor
     */
    public int getIdProfesorGlobal(){
        return Profesor.idProfesorActual;
    }

    // Metodos para agregar o modificar Variables

    /**
     * Metodo para agregar o quitar las horas que el profesor tiene disponibles
     *
     * @param horaToAsig Objeto Hora que se quiere agregar o quitar
     * @param selector 1: Agregar  -1:Quitar
     */
    public void modHorasDisponibles(Hora horaToAsig, int selector){
        if (selector == 1){
            if (!this.horaDisp.contains(horaToAsig)){
                this.horaDisp.add(horaToAsig);
            }
            else{
                System.out.println("Ya existe esa hora..");
            }
        }
        else if (selector == -1){
            if (this.horaDisp.contains(horaToAsig)){
                this.horaDisp.remove(horaToAsig);
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
     * Metodo para agregar o quitar las horas que el profesor tiene asignadas
     *
     * @param horaToAsig Objeto Hora que se quiere agregar o quitar
     * @param selector 1: Agregar  -1:Quitar
     */
    public void modHorasAsignadas(Hora horaToAsig, int selector) throws HourOutOfRangeException {
        if (selector == 1)
        {       if (!this.horaOcup.contains(horaToAsig))
                {       this.horaOcup.add(horaToAsig);
                }
                else
                {
                        System.out.println("Ya existe esa hora...");
                }
        }
        else if (selector == -1){
            if (this.horaOcup.contains(horaToAsig)){
                this.horaOcup.remove(horaToAsig);
            }
            else{
                System.out.println("No existe esa hora...");
            }
        }
        else {
            System.out.println("Error en la seleccion...");
        }
    }

    /**
     * Metodo para agregar o quitar los codigos de los cursos que el profesor puede impartir
     *
     * @param codCursoToAsig int con el codigo del curso que se quiere agregar o quitar
     * @param selector 1: Agregar  -1:Quitar
     */
    public void modCursosParaImpartir(int codCursoToAsig, int selector){
        if (selector == 1){
            if (!this.cursosDisp.contains(codCursoToAsig)){
                this.cursosDisp.add(codCursoToAsig);
            }
            else{
                System.out.println("Ya existe ese curso...");
            }
        }
        else if (selector == -1){
            if (this.cursosDisp.contains(codCursoToAsig)){
                this.cursosDisp.remove(codCursoToAsig);
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
     * Metodo para agregar o quitar un curso de los que el profesor tiene asignados
     *
     * @param cursoToAsig Objeto Curso que se quiere agregar o quitar
     * @param selector 1: Agregar  -1:Quitar
     */
    public void modCursosAsignados(Curso cursoToAsig,  int selector){
        Integer codigoActual = new Integer(cursoToAsig.getCodigoCurso());
        if (selector == 1){
            if (!this.cursosAsig.contains(cursoToAsig)){
                this.cursosAsig.add(cursoToAsig);
                if(this.idCursosAsig.contains(codigoActual)){
                    this.idCursosAsig.add(codigoActual);
                }
            }
            else{
                System.out.println("Ya existe ese curso...");
            }
        }
        else if (selector == -1){
            if (this.cursosAsig.contains(cursoToAsig)){
                this.cursosAsig.remove(cursoToAsig);
                if (this.idCursosAsig.contains(codigoActual))
                this.idCursosAsig.remove(codigoActual);
            }
            else{
                System.out.println("No existe ese curso...");
            }
        }
        else{
            System.out.println("Error en la seleccion...");
        }
    }

    public void modIdCursosAsignados(Integer idCursoToAsig, int selector){
        if (selector == 1){
            if (!this.idCursosAsig.contains(idCursoToAsig)){
                this.idCursosAsig.add(idCursoToAsig);
            }
            else{
                System.out.println("Ya existe ese curso...");
            }
        }
        else if (selector == -1){
            if (!this.idCursosAsig.contains(idCursoToAsig)){
                this.idCursosAsig.remove(idCursoToAsig);
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
     * Metodo para agregar el rut del profesor
     *
     * @param rut int con el rut del profesor
     */
    public void setRutProfesor(int rut){
        this.rutProfe = rut;
    }

    /**
     * Metodo para setear el Id del profesor actual(variable static). Se debe obtener desde un archivo.
     * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
     * id.
     * 
     * @param id int con el id static para ser seteado
     */
    public void setIdProfesores(int id){
        Profesor.idProfesorActual = id;
    }

    }