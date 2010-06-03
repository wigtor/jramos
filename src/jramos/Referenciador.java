/**
******************************************************
* @file Referenciador.java
* @author AirZs
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase Referenciador
 * cuyo uso es hacer las referncias reales de los distintos objetos que interactuan
 * entre si.

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
        Referenciador.crearReferenciasCurso(listCursos, listProfesores);
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
        ArrayList<Integer> listaCodCarreras;
        if (!listaCarreras.isEmpty() && !listaSemestres.isEmpty()){
         for (Carrera carrera : listaCarreras){
             idActual = carrera.getCodigoCarrera();
             for (Semestre semestre : listaSemestres){
                 if (semestre.getIdSemestre() == idActual){
                     carrera.modSemestres(semestre, 1);
                     semestre.setEnCarrera(carrera);
                     break;
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
                        if (curso.getIdCurso() == idActual){
                            semestre.modRamos(curso, 1);
                            curso.modSemestres(semestre, 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo para crear las referencias de Curso, se crean las referencias reales de la Curso<->Profesor
     *
     * @param listaCursos ArrayList con Cursos por crear referencias
     * @param listaProfesores ArrayList con Profesores por crear referencias
     */
    static private void crearReferenciasCurso(ArrayList<Curso> listaCursos, ArrayList<Profesor> listaProfesores){
        int idProfesor;
        if (!listaCursos.isEmpty() && !listaProfesores.isEmpty()){
            for (Curso curso: listaCursos){
                idProfesor = curso.getIdProfeAsig();
                for (Profesor profesor: listaProfesores){
                    if (profesor.getIdProfesor() == idProfesor){
                        curso.setProfesor(profesor);
                        profesor.modCursosAsignados(curso, 1);
                        break;
                    }
                }
            }
        }
    }

    static private void crearReferenciasFacultad(ArrayList<Facultad> listaFacultades, ArrayList<Carrera> listaCarreras){
        int idFacultadActual;
        if (!listaFacultades.isEmpty() && !listaCarreras.isEmpty()){
            for (Facultad facultad: listaFacultades){
                idFacultadActual = facultad.getIdFacultad();
                for (Carrera carrera : listaCarreras){
                    if (carrera.getIdFacultad() == idFacultadActual){
                        carrera.setFacultad(facultad);
                        facultad.modListaCarreras(carrera, 1);
                        break;
                    }
                }
            }
        }



    }

    ///////////////////////////////////////////////////////////////////
    // Metodos adicionales para trabajar

    /**
     * Metodo para crear un ArrayList de Integer a partir de un string
     *
     * @param texto Un string con int separados por |
     *
     * @return Un ArrayList<Integer>
     */
    static private ArrayList<Integer> stringToArrayList(String texto){
        ArrayList<Integer> lista = new ArrayList();
        boolean lector = true;
        int indexAct = 0;
        int indexTemp;
        while (lector){
            indexTemp = texto.indexOf("|", indexAct);
            if (indexTemp == -1){
                lector = false;
            }
            else{
                lista.add(new Integer(texto.substring(indexAct, indexTemp)));
                indexAct = indexTemp;
            }
        }
        return lista;
    }

}
