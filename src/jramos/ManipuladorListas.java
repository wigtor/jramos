/*
 * Es esta clase a la que la interfaz gráfica le solicita sus métodos.
 */

package jramos;

import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Facultad;
import jramos.tiposDatos.Semestre;
import jramos.tiposDatos.Profesor;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class ManipuladorListas
{       ArrayList<Facultad> listaFacultades;
        ArrayList<Carrera> listaCarreras;
        ArrayList<Semestre> listaSemestres;
        ArrayList<Curso> listaCursos;
        ArrayList<Profesor> listaProfesores;
        

        public ManipuladorListas(ArrayList<Facultad> listaFacultades, ArrayList<Carrera> listaCarreras, ArrayList<Semestre> listaSemestres, ArrayList<Curso> listaCursos, ArrayList<Profesor> listaProfesores)
        {       this.listaFacultades = listaFacultades;
                this.listaCarreras = listaCarreras;
                this.listaSemestres = listaSemestres;
                this.listaCursos = listaCursos;
                this.listaProfesores = listaProfesores;
        }

        public ArrayList<Curso> getListaCursos()
        {       return this.listaCursos;
        }

        public ArrayList<Facultad> getListaFacultades()
        {       return this.listaFacultades;
        }

        public ArrayList<Carrera> getListaCarreras()
        {       return this.listaCarreras;
        }

        public ArrayList<Semestre> getListaSemestres()
        {       return this.listaSemestres;
        }

        public ArrayList<Profesor> getListaProfesores()
        {       return this.listaProfesores;
        }

        public void agregaFacultad(String nombreFacultad)
        {       Facultad facultadNueva = new Facultad(nombreFacultad);
                //debo validar los datos ingresados y devolver una excepcion si no se puede

                this.listaFacultades.add(facultadNueva);
        }

        public void agregaCarrera(String nombreCarrera, Facultad facultadALaQuePertenece)
        {       Carrera carreraNueva = new Carrera(nombreCarrera);
                //Debo válidar los datos ingresados y devolver excepción si no se puede agregar.

                this.listaCarreras.add(carreraNueva);
        }

        public void eliminaCarrera(int idCarrera)
        {

        }
}
