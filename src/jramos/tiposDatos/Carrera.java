/**
******************************************************
* @file Profesor.java
* @author AirZs
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase del tipo Carrera.
*****************************************************/

package jramos.tiposDatos;
import java.util.ArrayList;

public class Carrera {

    private String nomCarrera;
    private int codCarrera;
    private String descrip = "0";
    private ArrayList<Semestre> semestres;
    private ArrayList<Integer> idSemestres;
    

    //////////////////////////////////////////////////////////////////////
    // Contructor

    /**
     *  Constructor para Carrera:
     *
     * @param nombreCarrera Nombre de la carrera
     * @param codigoCarrera Codigo unico para la carrera
     */
    public Carrera(String nombreCarrera, int codigoCarrera){
        this.nomCarrera = nombreCarrera;
        this.codCarrera = codigoCarrera;
        this.semestres = new ArrayList<Semestre>();
        this.idSemestres = new ArrayList<Integer>();
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Obtener Variables

    /**
     * Metodo para obtener el nombre de la carrera
     *
     * @return String Nombre de la Carrera
     */

    public String getNombreCarrera(){
        return this.nomCarrera;
    }

    /**
     * Metodo para obtener el codigo de la carrera
     *
     * @return int codigo de la carrera
     */
    public int getCodigoCarrera(){
        return this.codCarrera;
    }

    /**
     * Metodo para obtener la descripcion de la carrera
     *
     * @return String con la descripcion
     */
    public String getDescripcion(){
        if (!this.descrip.contentEquals("0")){
            return this.descrip;
        }
        else{
            return "No Hay Descripción Disponible...";
        }
    }

    /**
     * Metodo para obtener un los Id de los semestres de esta carrera
     *
     * @return String con los Id separados con |
     */
    public String getIdSemestres(){
        StringBuilder text = new StringBuilder();
        for (Integer id: this.idSemestres){
            text.append(String.valueOf(id)).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    //////////////////////////////////////////////////////////////////////
    // Metodos de Modificar Variables

    /**
     * Metodo para agregar la descripcion a la Carrera
     *
     * @param desc String con la descripcion de la Carrera
     */
    public void setDescripcion(String desc){
       this.descrip = desc;
    }

    /**
     * Metodo para modificar los semestres de una carrera. Se pueden
     * agregar o quitar semestres.
     *
     * @param semestreToAsig El semestre que se quiere agregar o quitar
     * @param selector 1: Agregar | -1: Quitar
     */
    public void modSemestres(Semestre semestreToAsig, int selector){
        if (selector == 1){
            if (!this.semestres.contains(semestreToAsig)){
                this.semestres.add(semestreToAsig);
                this.idSemestres.add(semestreToAsig.getIdSemestre());
            }
            else{
                System.out.println("Ya existe el semestre...");
            }
            
        }
        else if (selector == -1){
            if (this.semestres.contains(semestreToAsig)){
                this.semestres.remove(semestreToAsig);
                this.idSemestres.remove(semestreToAsig.getIdSemestre());
            }
            else{
                System.out.println("El semestre no existe...");
            }
        }
        else{
            System.out.println("Error en la seleccion...");
        }
    } 


}
