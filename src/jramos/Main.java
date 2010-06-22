/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jramos;

import jramos.GUI.VentanaPrincipal;
import jramos.tiposDatos.*;
import jramos.capaIO.*;
import java.util.ArrayList;


public class Main
{
	public static void main(String args[])
	{       //Seteo por defecto el uso de UTF-8 en la codificación de caracteres.
                System.out.println("codificación de caracteres usado: " +System.getProperty("file.encoding"));
                System.setProperty("file.encoding", "UTF-8");
                CapaIOCursos gestorIOCursos;
                CapaIOProfes gestorIOProfes;

                ArrayList<Curso> listaCursos;
                ArrayList<Carrera> listaCarreras;
                ArrayList<Semestre> listaSemestres;
                ArrayList<Facultad> listaFacultades;
                ArrayList<Profesor> listaProfesores;
                try
                {       gestorIOCursos = new CapaIOCursos();
                        gestorIOProfes = new CapaIOProfes();

                        //Leo los cursos del archivo y seteo los id iniciales de los cursos
                        listaCursos = gestorIOCursos.leeCursos();
                        Curso.setIdCursos(gestorIOCursos.leeIDInicial("idCursos"));

                        //Leo las carrera del archivo y seteo los id iniciales de las carreras
                        listaCarreras = gestorIOCursos.leeCarreras();
                        Carrera.setIdCarreras(gestorIOCursos.leeIDInicial("idCarreras"));

                        //Leo los semestres el archivo y seteo los id iniciales de los semestres
                        listaSemestres = gestorIOCursos.leeSemestres();
                        Semestre.setIdSemestres(gestorIOCursos.leeIDInicial("idSemestres"));

                        //Leo las facultades del archivo y seteo los id iniciales de la sfacultades
                        listaFacultades = gestorIOCursos.leeFacultades();
                        Facultad.setIdFacultades(gestorIOCursos.leeIDInicial("idFacultades"));

                        //Leo los profesores del archivo y seteo los id iniciales de los profesore
                        listaProfesores = gestorIOProfes.leeProfes();
                        Profesor.setIdProfesores(gestorIOProfes.leeIDInicial("idProfes"));

                        //Hago las conexiones de referencias entre las listas
                        ReferenciadorInicial.crearReferencias(listaCarreras, listaCursos, listaFacultades, listaProfesores, listaSemestres);

                        ManipuladorListas listManager = new ManipuladorListas(listaFacultades, listaCarreras, listaSemestres, listaCursos, listaProfesores);
                        VentanaPrincipal ventanaPadre = new VentanaPrincipal(listManager, gestorIOCursos, gestorIOProfes);
                        ventanaPadre.setVisible(true);
                }
                catch (Exception e)
                {
                        System.out.println("ERROR: \n"+ e.getMessage());
                }

	}
}