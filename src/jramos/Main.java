/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jramos;

import java.io.*;
import jramos.tiposDatos.*;
import jramos.capaIO.*;
import java.util.ArrayList;


/** Esta clase es para probar las clases que estoy creando, no forma parte del código oficial*/
/*class Main
{
	public static void main(String arg[])
	{	System.out.println("Se muestra una linea usando getenv()");
		System.out.println(System.getProperty("user.home") + System.getProperty("file.separator") + "archivoCursos.txt");

		///Pruebo la clase Hora
		System.out.print("Introduzca una hora según los formatos 1° o 2°: ");
		String linea = null;
		Hora horaDePrueba = null;
		try
		{	BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
			linea = entradaTeclado.readLine();
		}
		catch(IOException IOE){ IOE.printStackTrace();}

		try
		{	horaDePrueba = new Hora(java.lang.Integer.valueOf(linea));
		}
		catch (NumberFormatException nfe)
		{	try
			{	horaDePrueba = new Hora(linea);
			}
			catch (HourOutOfRangeException hoor)
			{	System.out.println("HourOutOfRangeException:" + hoor.getMessage());
				System.exit(0);
			}
		}
		catch (HourOutOfRangeException hoor)
		{	System.out.println("HourOutOfRangeException:\n" + hoor.getMessage());
			System.exit(0);
		}

		
		//horaDePrueba = new Hora(); //es para probar la excepcion al crear un objeto sin inicializar la hora.
		
		try
		{	System.out.println("La hora según 1° formato es : " + horaDePrueba.getHora());
			System.out.println("La hora según 2° formato es: " + horaDePrueba.getHoraStr());
			System.out.println("La hora según 3° formato es: " + horaDePrueba.getHoraStr2());
			System.out.println("La hora según 4° formato es: " + horaDePrueba.getHoraStr3());
		}
		catch (HourNotInicializatedException hnie)
		{	System.out.println("HourNotInicializatedException:\n" + hnie.getMessage());
			System.exit(0);
		}
	}

}
*/

//Este main está hecho para probar la capaIOCursos

public class Main
{
	public static void main(String args[])
	{       
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
                        Referenciador.crearReferencias(listaCarreras, listaCursos, listaFacultades, listaProfesores, listaSemestres);

                        ManipuladorListas listManager = new ManipuladorListas(listaFacultades, listaCarreras, listaSemestres, listaCursos, listaProfesores);
                        VentanaPrincipal ventanaPadre = new VentanaPrincipal(listManager, gestorIOCursos, gestorIOProfes);
                        ventanaPadre.setVisible(true);
                }
                        catch (Exception e)
                {
                        System.out.println("ERROR");
                }

	}
}


//Este main es para probar la capaIOProfes
 /*
public class Main
{
	public static void main(String args[])
	{       CapaIOProfes gestorIOProfes;
                ArrayList<Profesor> lista;
                try
                {       gestorIOProfes = new CapaIOProfes();
                        lista = gestorIOProfes.leeProfes();
                        System.out.println(lista);
                        System.out.println("Ahora al revez, escribo una lista de profes en el archivo.");
                        gestorIOProfes.escribeProfes(lista, 7);
                }
                        catch (Exception e)
                {
                        System.out.println("ERROR");
                }


        }
}
 */
