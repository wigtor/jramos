/**
 ******************************************************
 * @file Profesor.java
 * @author AirZs
 * @date mayo 2010
 * @version 0.1
 * La clase Carrera representa una carrera de la universidad
 * Cada carrera debe pertenecer a una facultad
 * Cada carrera debe tener un codigo de carrera (equivalente a un id en otros objetos)
 * Cada carrera posee distintas instancia de semestres.
 * @brief En este archivo se especifica la clase del tipo Carrera.
 *****************************************************/

package jramos.tiposDatos;
import java.util.ArrayList;

public class Carrera implements Comparable{

    static int codCarreraActual = 1000;

    private String nomCarrera;
    private int codCarrera;
    private String descrip = "";
    private ArrayList<Semestre> semestres;
    private ArrayList<Integer> idSemestres;
    private Facultad enFacultad;

    //////////////////////////////////////////////////////////////////////
    // Contructor

    /**
     * 1º Constructor para Carrera:
     *
     * @param nombreCarrera Nombre de la carrera
     */
    public Carrera(String nombreCarrera){
        this.nomCarrera = nombreCarrera;
        this.codCarrera = ++codCarreraActual;
        this.semestres = new ArrayList<Semestre>();
        this.idSemestres = new ArrayList<Integer>();
    }

    /**
     * 2º Constructor para Carrera:
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

    public ArrayList<Semestre> getListaSemestres()
    {       return this.semestres;
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
     * Metodo para obtener el ultimo codigo de la carrera utilizadp
     *
     * @return int codigo de la carrera
     */
    static public int getCodigoCarreraGlobal(){
        return Carrera.codCarreraActual;
    }

    /**
     * Metodo para obtener la descripcion de la carrera
     *
     * @return String con la descripcion
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
     * Metodo para obtener un los Id de los semestres de esta carrera
     *
     * @return String con los Id separados con |
     */
    public String getIdSemestres(){ ///ACA HAY UN POSIBLE ERROR CUANDO LA LISTA DE SEMESTRES ESTÁ VACÍA!!!
        StringBuilder text = new StringBuilder();
        if (this.idSemestres.size() == 0 ){
                return "";
        }
        for (Integer id: this.idSemestres){
            text.append(String.valueOf(id)).append("|");
        }
        text.deleteCharAt(text.length()-1);
        return text.toString();
    }

    /**
     * Metodo para obtener el id de la facultad en la que está esta carrera
     *
     * @return int con el id de la facultad
     */
    public int getIdFacultad(){
        return this.enFacultad.getIdFacultad();
    }

    public ArrayList<Integer> getIdSemestresArrayList(){
        return this.idSemestres;
    }



    //////////////////////////////////////////////////////////////////////
    // Metodos de Modificar Variables

    /**
     * Metodo para cambiar el nombre a la Carrera
     *
     * @param nomb String con el nuevo nombre de la Carrera
     */
    public void setNombre(String nomb){
       this.nomCarrera = nomb;
    }
    /**
     * Metodo para agregar la descripcion a la Carrera
     *
     * @param desc String con la descripcion de la Carrera
     */
    public void setDescripcion(String desc){
       this.descrip = desc;
    }

    /**
     * Metodo para setear el Id de la Carrera actual(variable static). Se debe obtener desde un archivo.
     * Se utiliza este metodo siempre que se cargue el sistema, para no volver a utilizar los mismos
     * id.
     * 
     * @param id int con el id static para ser seteado
     */
    static public void setIdCarreras(int id){
        Carrera.codCarreraActual = id;
    }

    public void modIdSemestres(int idSemestre, int selector)
    {   if (selector == 1)
        {   if (!this.idSemestres.contains(idSemestre))
            {   this.idSemestres.add(new Integer(idSemestre));
            }
            else
            {   System.out.println("Ya existe el codigo de semestre...");
            }
        }
        else if (selector == -1){
           if (this.idSemestres.contains(idSemestre)){
               this.idSemestres.remove(new Integer (idSemestre));
           }
           else{
               System.out.println("No existe ese codigo del semestre...");
           }
       }
       else{
           System.out.println("Error en la seleccion...");
       }
            
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
                this.modIdSemestres(semestreToAsig.getIdSemestre(), 1);
            }
            else{
                System.out.println("Ya existe el semestre...");
            }
            
        }
        else if (selector == -1){
            if (this.semestres.contains(semestreToAsig)){
                this.semestres.remove(semestreToAsig);
                this.modIdSemestres(semestreToAsig.getIdSemestre(), -1);
            }
            else{
                System.out.println("El semestre no existe...");
            }
        }
        else{
            System.out.println("Error en la seleccion...");
        }
    } 

    /**
     * Metodo para asignar la facultad en la que está esta carrera
     * 
     * @param facultadToAsig Objeto facultad para ser asignado
     */
    public void setFacultad(Facultad facultadToAsig){
        this.enFacultad = facultadToAsig;
    }
    
    /**
     * Metodo para obtener la facultad en la que está esta carrera
     * 
     * @return Objeto facultad al cual está asignada esa carrera
     */
    public Facultad getFacultad(){
        return this.enFacultad;
    }

    @Override
    public String toString()
    {       return this.getNombreCarrera();
    }

    public int compareTo(Object o)
    {       if (o.getClass() != Carrera.class)
                    return -1;
            else
                    return this.getNombreCarrera().compareTo(((Carrera)o).getNombreCarrera());
    }
}
