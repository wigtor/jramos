/*
 * Es esta clase a la que la interfaz gráfica le solicita sus métodos.
 */

package jramos;

import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Facultad;
import jramos.tiposDatos.Semestre;
import jramos.tiposDatos.Hora;
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

        public void agregaFacultad(String nombreFacultad, String descripcion)
        {       //debo validar los datos ingresados y devolver una excepcion si no se puede

                Facultad facultadNueva = new Facultad(nombreFacultad);
                facultadNueva.setDescripcion(descripcion);
                this.listaFacultades.add(facultadNueva);
        }

        public void eliminaFacultad(Facultad facultadAEliminar)
        {       //SE DEBE HACER ESTE METODO
                //Elimino todoas las carreras que pertenecen a esa facultad.
                //ATENTO A ALGUN POSIBLE FALLO EN LA ITERACION Y ELIMINACION DE CARRERAS CON EL METODO eliminaCarrera()
                ArrayList<Carrera> carrerasAEliminar = new ArrayList();
                for (Carrera carrera : this.listaCarreras)
                {       if (carrera.getFacultad().equals(facultadAEliminar))
                        {       carrerasAEliminar.add(carrera);
                        }
                }
                for (Carrera carrera : carrerasAEliminar) //Esto es para evitar un error en el acceso al ArrayList al ir eliminando elementos
                {       this.eliminaCarrera(carrera.getCodigoCarrera());
                }
                //Elimino la facultad de la lista de facultades
                for (Facultad facultad :this.listaFacultades)
                {       if (facultadAEliminar.equals(facultad))
                        {       this.listaFacultades.remove(facultad);
                                break;
                        }
                }
        }

        public void agregaProfesor(String nombreProfesor, int rut, ArrayList<Integer> cursosDisponibles, ArrayList<Hora> horasDisponibles)
        {       Profesor profesorNuevo = new Profesor(nombreProfesor, rut, cursosDisponibles);
                for (Hora hora : horasDisponibles)
                        profesorNuevo.modHorasDisponibles(hora, 1);
                this.listaProfesores.add(profesorNuevo);
        }

        public void agregaCarrera(String nombreCarrera, Facultad facultadALaQuePertenece, String descripcion, int cantidadSemestres)
        {       //Debo válidar los datos ingresados y devolver excepción si no se puede agregar.
                int i;
                Carrera carreraNueva = new Carrera(nombreCarrera);
                Semestre semestreNuevo;
                carreraNueva.setFacultad(facultadALaQuePertenece);
                carreraNueva.setDescripcion(descripcion);
                facultadALaQuePertenece.modListaCarreras(carreraNueva, 1);
                //Agrego los semestres que posea, pero sin cursos.
                for (i = 0; i < cantidadSemestres; i++)
                {       semestreNuevo = new Semestre(i+1, carreraNueva);
                        carreraNueva.modSemestres(semestreNuevo, 1);
                        this.listaSemestres.add(semestreNuevo);
                }

                this.listaCarreras.add(carreraNueva);
        }

        public void eliminaCarrera(int codCarreraAEliminar)
        {       //elimino la carrera desde la lista de carreras
                Carrera carreraAEliminar = null;
                for (Carrera carrera : this.listaCarreras)
                {       if (carrera.getCodigoCarrera() == codCarreraAEliminar)
                        {       carreraAEliminar = carrera;
                                this.listaCarreras.remove(carrera);
                                break;
                        }

                }
                //elimino la carrera desde las referencias de la facultad a la que pertenece
                for (Facultad facultad : this.listaFacultades)
                {       facultad.modListaCarreras(carreraAEliminar, -1); //borro todas las referencias de esa carrera de las facultades, si no existe, se muestra una advertencia
                }

                ArrayList<Semestre> semestresAEliminar = carreraAEliminar.getListaSemestres();
                //elimino las referencias de los semestres eliminados que existan en los cursos
                for (Semestre semestre : semestresAEliminar)
                {       for (Curso curso : this.listaCursos)
                        {       curso.modSemestres(semestre, -1);
                        }
                }
                //elimino las referencias de todos los semestres que posee la carrera eliminada de la lista de semestres
                for (Semestre semestre : semestresAEliminar)
                {       this.listaSemestres.remove(semestre);
                }
        }

}
