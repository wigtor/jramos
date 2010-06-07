//Probando cambios con git-bash

/**
******************************************************
* @file Profesor.java
* @author AirZs
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase del tipo Curso.
*****************************************************/

package jramos.tiposDatos;
import java.util.ArrayList;

public class Facultad {
    static int idFacultadActual = 0;

    private String nomFacultad;
    private String descrip = "";
    private int idFacultad;
    private ArrayList<Carrera> listCarreras;
    private ArrayList<Integer> listCodigosCarreras;

    ////////////////////////////////////////////////////////
    // Constructores

    /**
     * 1º Constructor para Facultad:
     * Este constructor debe ser utilizado para crear un objeto Facultad la primera vez. Se le asigna un
     * id de forma automatica.
     *
     * @param nombre Nombre de la Facultad
     */
    public Facultad(String nombre){
        this.nomFacultad = nombre;
        this.idFacultad = ++idFacultadActual;
        this.listCarreras = new ArrayList<Carrera>();
        this.listCodigosCarreras = new ArrayList<Integer>();
    }

    /**
     * 2º Constructor para Curso:
     * Este constructor debe ser utilizado para crear un objeto Facultad a partir de un registro o archivo.
     *
     * @param nombre Nombre de la Facultad
     * @param id int con el id de la Facultad
     */
    public Facultad (String nombre, int id){
        this.nomFacultad = nombre;
        this.idFacultad = id;
        this.listCarreras = new ArrayList<Carrera>();
        this.listCodigosCarreras = new ArrayList<Integer>();
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Obtener Variables

    /**
     * Metodo para obtener el nombre de la facultad
     *
     * @return String con el nombre de la facultad
     */
    public String getNombreFacultad(){
        return this.nomFacultad;
    }

    public ArrayList<Carrera> getCarreras(){
        return this.listCarreras;
    }
    /**
     * Metodo para obtener la descripcion de la facultad
     *
     * @return String con la descripcion
     */
    public String getDescripcion(){
        return this.descrip;
    }

    @Override
    public String toString()
    {       return this.getNombreFacultad();
    }
    /**
     * Metodo para obtener el id de la facultad actual
     *
     * @return int con el id de la facultad
     */
    public int getIdFacultad(){
        return this.idFacultad;
    }

    /**
     * Metodo para obtener el ultimo codigo de la facultad que fue utilizado
     *
     * @return int con el codigo de la facultad
     */
    static public int getIdFacultadGlobal(){
        return Facultad.idFacultadActual;
    }

    /**
     * Metodo para obtener los nombres de las carreras que componen la facultad
     *
     * @return String con los nombres de las carreras separados por |
     */
    public String getNombreCarreras(){
         StringBuilder text = new StringBuilder();
        if (!this.listCarreras.isEmpty()){
            for (Carrera carrera : this.listCarreras){
                text.append(carrera.getNombreCarrera()).append("|");
            }
            text.deleteCharAt(text.length()-1);
            return text.toString();
        }
        else{
            return "";
        }
    }

    /**
     * Metodo para obtener los codigos de las carreras que componen la facultad
     *
     * @return String con los codigos separados por |
     */
    public String getCodigosCarreras(){
        StringBuilder text = new StringBuilder();
        if (!this.listCodigosCarreras.isEmpty()){
            for (Integer codigo : this.listCodigosCarreras){
                text.append(String.valueOf(codigo)).append("|");
            }
            text.deleteCharAt(text.length()-1);
            return text.toString();
        }
        else{
            return "";
        }
    }

    public ArrayList getCodigosCarrerasArrayList(){
        return this.listCodigosCarreras;
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Modificar Variables

    /**
     * Metodo para modificar el nombre de la facultad
     *
     * @param nomb String con el nuevo nombre de la facultad
     */
    public void setNombre(String nomb){
        this.nomFacultad = nomb;
    }

    /**
     * Metodo para agregar una descripcion a la facultad
     *
     * @param desc String con la descripcion
     */
    public void setDescripcion(String desc){
        this.descrip = desc;
    }

    /**
     * Metodo para setear el Id de la Facultad actual(variable static). Se debe obtener desde un archivo.
     * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
     * id.
     *
     * @param id int con el id static para ser seteado
     */
    static public void setIdFacultades(int id){
        Facultad.idFacultadActual = id;
    }

    /**
     * Metodo para agregar o quitar una carrera a la facultad
     *
     * @param carreraToAsig Objeto Carrera que se desea agregar o quitar
     * @param selector 1: Agregar | -1: Quitar
     */
    public void modListaCarreras(Carrera carreraToAsig, int selector){
       if (selector == 1){
           if (!this.listCarreras.contains(carreraToAsig)){
               this.listCarreras.add(carreraToAsig);
               this.modListaCodigoCarreras(carreraToAsig.getCodigoCarrera(), 1);
           }
           else{
               System.out.println("Ya existe la carrera...");
           }
       }
       else if (selector == -1){
           if (this.listCarreras.contains(carreraToAsig)){
               this.listCarreras.remove(carreraToAsig);
               this.modListaCodigoCarreras(carreraToAsig.getCodigoCarrera(), -1);
           }
           else{
               System.out.println("No existe la carrera...");
           }
       }
       else{
           System.out.println("Error en la seleccion...");
       }
    }

    /**
     * Metodo para agregar o quitar los codigos que hacen referencia a las carrera de la facultad
     *
     * @param codigo int con el codigo que se desea agregar o quitar
     * @param selector 1: Agregar | -1: Quitar
     */
    public void modListaCodigoCarreras(int codigo, int selector){
        if (selector == 1)
        {   if (!this.listCodigosCarreras.contains(codigo))
            {   this.listCodigosCarreras.add(new Integer(codigo));
            }
            else
            {   System.out.println("Ya existe el codigo de carrera...");
            }
        }
        else if (selector == -1){
           if (this.listCodigosCarreras.contains(codigo)){
               this.listCodigosCarreras.remove(new Integer(codigo));
           }
           else{
               System.out.println("No existe ese codigo del carrera...");
           }
       }
       else{
           System.out.println("Error en la seleccion...");
       }
    }
}
