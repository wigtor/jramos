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
    
    public void crearReferencias(ArrayList<Carrera> listCarreras, ArrayList<Curso> listCursos, ArrayList<Facultad> listFacultades, ArrayList<Profesor> listProfesores, ArrayList<Semestre> listSemestres){
        this.crearReferenciasCarrera(listCarreras, listSemestres);
        this.crearReferenciasSemestre(listSemestres, listCursos);
    }

    /**
     * Metodo para crear las referencias de Carrera, se crean las referencias reales de la Carrera->Semestre y
     * viceversa.
     *
     * @param listaCarreras ArrayList con carreras por crear referencias
     * @param listaSemestres ArrayList con Semestres para crear referencias
     */
    private void crearReferenciasCarrera(ArrayList<Carrera> listaCarreras, ArrayList<Semestre> listaSemestres){
        int idActual;
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

    private void crearReferenciasSemestre(ArrayList<Semestre> listaSemestres, ArrayList<Curso> listaCursos){
        ArrayList<Integer> listaIdActual;
        if (!listaSemestres.isEmpty() && !listaCursos.isEmpty()){
            for (Semestre semestre: listaSemestres){
                listaIdActual = stringToArrayList(semestre.getCodigosRamos());
                for (Integer idActual : listaIdActual){
                    for (Curso curso: listaCursos){
                        if (curso.getIdCurso() == idActual){
                            semestre.modRamos(curso, 1);

                        }
                    }
                }
            }
        }
    }

    private ArrayList<Integer> stringToArrayList(String texto){
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
                lista.add(new Integer(texto.substring(indexAct, indexTemp+1)));
                indexAct = indexTemp;
            }
        }
        return lista;
    }





}
