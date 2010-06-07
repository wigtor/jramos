/**
 ******************************************************
 * @file Referenciador.java
 * @author AirZs
 * @date mayo 2010
 * @version 0.1
 * @brief En este archivo se especifica la clase Referenciador
 * La función de esta clase es hacer las referencias reales entre
 * los distintos objetos que interactuan entre si
 * Esta clase no puede ser instanciada, todos sus métodos son static.
 *****************************************************/

package jramos;
import java.util.ArrayList;
import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Facultad;
import jramos.tiposDatos.Profesor;
import jramos.tiposDatos.Semestre;

public class Referenciador {

    ////////////////////////////////////////
    //Metodo Principal
    
    static public void crearReferencias(ArrayList<Carrera> listCarreras, ArrayList<Curso> listCursos, ArrayList<Facultad> listFacultades, ArrayList<Profesor> listProfesores, ArrayList<Semestre> listSemestres){
        Referenciador.crearReferenciasCarrera(listCarreras, listSemestres);
        Referenciador.crearReferenciasSemestre(listSemestres, listCursos);
        Referenciador.crearReferenciasCurso(listCursos, listProfesores, listCarreras);
        Referenciador.crearReferenciasFacultad(listFacultades, listCarreras);
        
    }

    ///////////////////////////////////////////////////////////
    // Metodos secundarios

    /**
     * Metodo para crear las referencias de Carrera, se crean las referencias reales de la Carrera<->Semestre
     *
     * @param listaCarreras ArrayList con carreras por crear referencias
     * @param listaSemestres ArrayList con Semestres para crear referencias
     */
    static private void crearReferenciasCarrera(ArrayList<Carrera> listaCarreras, ArrayList<Semestre> listaSemestres){
        int idActual;
        if (!listaCarreras.isEmpty() && !listaSemestres.isEmpty()){
            for (Carrera carrera : listaCarreras){
                idActual = carrera.getCodigoCarrera();
                for (Semestre semestre : listaSemestres){
                    if (semestre.getCodigoEnCarrera() == idActual){
                        //Acá se
                        carrera.modSemestres(semestre, 1);
                        semestre.setEnCarrera(carrera);
                    }
                }
            }
        }
    }

    /**
     * Metodo para crear las referencias de Semestre, se crean las referencias reales de la Semestre<->Curso
     *
     * @param listaSemestres ArrayList con Semestre por crear referenciaas
     * @param listaCursos ArrayList con Cursos para crear referencias
     */
    static private void crearReferenciasSemestre(ArrayList<Semestre> listaSemestres, ArrayList<Curso> listaCursos){
        ArrayList<Integer> listaIdActual;
        if (!listaSemestres.isEmpty() && !listaCursos.isEmpty()){
            for (Semestre semestre: listaSemestres){
                listaIdActual = semestre.getCodigosRamosArrayList();
                for (Integer idActual : listaIdActual){
                    for (Curso curso: listaCursos){
                        if (curso.getIdCurso() == idActual.intValue())
                        {   //Acá se crean las referencias a los cursos en el atributo "ramos" de los objetos Semestre
                            semestre.modRamos(curso, 1);
                            //Acá se crean las referencias a los semestres en el atributo "listSemestres" de los objetos Curso
                            curso.modSemestres(semestre, 1);
                            
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo para crear las referencias de Curso, se crean las referencias reales de la Curso<->Profesor
     * Setea las referencias entre los cursos y los profesores que tengan asignados
     * Setea las referencias entre las carreras a las que pertenece un curso
     * @param listaCursos ArrayList con Cursos por crear referencias
     * @param listaProfesores ArrayList con Profesores por crear referencias
     */
    static private void crearReferenciasCurso(ArrayList<Curso> listaCursos, ArrayList<Profesor> listaProfesores, ArrayList<Carrera> listaCarreras){
        int idProfesorAsignado;
        ArrayList<Integer> listaCodCarreras;
        if (!listaCursos.isEmpty() && !listaProfesores.isEmpty()){
            for (Curso curso: listaCursos){
                idProfesorAsignado = curso.getIdProfeAsig();
                for (Profesor profesor: listaProfesores){
                    if (profesor.getIdProfesor() == idProfesorAsignado){
                        curso.setProfesor(profesor);
                        profesor.modCursosAsignados(curso, 1);
                        break;
                    }
                }
            }
            //referencio las carreras a las que pertenece un curso.
            for (Curso curso: listaCursos){
                listaCodCarreras = curso.getEnCarrerasCodigosArrayList();
                for (Integer codCarrera: listaCodCarreras){
                    for (Carrera carrera : listaCarreras){
                        if (carrera.getCodigoCarrera() == codCarrera.intValue()){
                            curso.modCarrera(carrera, 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Esté metodo se encarga de setear las listas de referencias de carreras que poseen las facultades
     * Setear las referencias a las facultades a las que pertenecen las carreras
     * @param listaFacultades lista de facultades que se desea unir referencias
     * @param listaCarreras lista de carreras que se desea unir referencias
     */
    static private void crearReferenciasFacultad(ArrayList<Facultad> listaFacultades, ArrayList<Carrera> listaCarreras){
        ArrayList<Integer> listaCarrerasFacultadActual;
        if (!listaFacultades.isEmpty() && !listaCarreras.isEmpty()){
            for (Facultad facultad: listaFacultades){
                listaCarrerasFacultadActual = facultad.getCodigosCarrerasArrayList();
                for (Integer codigoCarrera : listaCarrerasFacultadActual){
                    for (Carrera carrera : listaCarreras){
                        if (carrera.getCodigoCarrera() == codigoCarrera.intValue()){
                            //Acá se crean las referencias a las facultades en el atributo "enFacultad" de los objetos carrera
                            carrera.setFacultad(facultad);
                            //Acá se crean las referencias a las carreras en el atributo "listCarreras" de los objetos facultad
                            facultad.modListaCarreras(carrera, 1);
                        }
                    }

                }
            }
        }
    }

}
