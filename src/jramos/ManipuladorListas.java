/**
 ******************************************************
 * @file ManipuladorListas.java
 * @author victor flores sanchez
 * @date junio 2010
 * @version 0.1
 * Esta clase se encarga de gestionar las listas de profesores, cursos, carreras, semestres y facultades
 * Proporciona métodos para agregar y eliminar elementos de las listas
 * A la vez cuando agrega o elimina elementos crea las correspondientes referencias entre los demas objetos de las listas
 * Esta clase no se encarga de gestionar si hay nombres repetidos o tope horario.
 * Es esta clase a la que la interfaz gráfica le solicita sus métodos.
 ******************************************************
 */

package jramos;

import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Facultad;
import jramos.tiposDatos.Semestre;
import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Profesor;
import java.util.ArrayList;

public class ManipuladorListas
{       /* ATRIBUTOS */
        ArrayList<Facultad> listaFacultades;
        ArrayList<Carrera> listaCarreras;
        ArrayList<Semestre> listaSemestres;
        ArrayList<Curso> listaCursos;
        ArrayList<Profesor> listaProfesores;
        
        /* CONSTRUCTOR */
        /**
         * Constructor que setea los atributos que corresponden a las listas de carreras, facultades, cursos, semestres y profesores
         * @param listaFacultades 
         * @param listaCarreras
         * @param listaSemestres
         * @param listaCursos
         * @param listaProfesores
         */
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


        public void eliminaCurso(Curso cursoABorrar)
        {
            
        }
        public void agregaCurso(String nombreCurso, int codCurso, Carrera carreraAlQuePertenece)
        {

        }
        /**
         * Crea y agrega un objeto Facultad a la lista de facultades
         * @param nombreFacultad El string con el nombre de la facultad que se está creando.
         * @param descripcion El string con la descripción de la facultad que se está creando
         */
        public void agregaFacultad(String nombreFacultad, String descripcion)
        {       
                Facultad facultadNueva = new Facultad(nombreFacultad);
                facultadNueva.setDescripcion(descripcion);
                this.listaFacultades.add(facultadNueva);
        }

        /**
         * Elimina una facultad de la lista de facultades
         * Elimina las carreras que posea esa facultad
         * Esto lo realiza llamando al metodo eliminaCarrera()
         * por lo tanto ademas se quitan las referencias que borra ese método.
         * @param facultadAEliminar Es el objeto Facultad que se desea eliminar de la lista de facultades
         */
        public void eliminaFacultad(Facultad facultadAEliminar)
        {       //Elimino todoas las carreras que pertenecen a esa facultad.
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

        /**
         * Crea un objeto Profesor y lo agrega al sistema
         * Agrega el profesor a la lista de profesores
         * Setea el atributo de sus horas disponibles
         * @param nombreProfesor Es el string con el nombre del nuevo profesor
         * @param rut es el entero con el rut del profesor, sin dígito verificador
         * @param cursosDisponibles Es un Arraylist de Integer con los códigos de curso que puede dictar el profesor
         * @param horasDisponibles Es un ArrayList de Hora con las horas disponibles del profesor para hacer clases
         */
        public void agregaProfesor(String nombreProfesor, int rut, ArrayList<Integer> cursosDisponibles, ArrayList<Hora> horasDisponibles)
        {       Profesor profesorNuevo = new Profesor(nombreProfesor, rut, cursosDisponibles);
                for (Hora hora : horasDisponibles)
                        profesorNuevo.modHorasDisponibles(hora, 1);
                this.listaProfesores.add(profesorNuevo);
        }

        /**
         * Elimina un profesor del sistema
         * Elimina las referencias al profesor que puedan existir en los cursos.
         * @param profeABorrar Es el Objeto Profesor que se desea eliminar de la lista de profesores
         */
        public void eliminaProfesor(Profesor profeABorrar)
        {       //Elimino al profesor de la lista de profesores
                this.listaProfesores.remove(profeABorrar);
                //elimino las rederencias al profesor que puedan existir en sus cursos asignados
                for (Curso curso : this.listaCursos)
                {       if (curso.getIdProfeAsig() == profeABorrar.getIdProfesor())
                                {       curso.setProfesor(null);
                                }
                }
        }

        /**
         * Este método se encarga de agregar una carrera a la lista de carreras
         * Setea sus atributos
         * Crea los semestres que debe poseer la nueva carrera
         * Referencia la carrera a la facultad a la que pertenece
         * @param nombreCarrera Nombre de la carrera que se desea agregar
         * @param facultadALaQuePertenece Objeto Facultad a la que va a pertenecer esta nueva carrera
         * @param descripcion La descripción de la carrera.
         * @param cantidadSemestres El número de semestres que tendrá la carrera
         */
        public void agregaCarrera(String nombreCarrera, Facultad facultadALaQuePertenece, String descripcion, int cantidadSemestres)
        {       int i;
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

        /**
         * Este método elimina una carrera de la lista de carreras del sistema buscandolo por su codigo de carrera
         * Elimina las referencias a esa carrera que puedan existir en las facultades.
         * Elimina los semestres que posee la carrera eliminada de la lista de semestres.
         * Elimina las referencias de los semestres eliminados que existan en los cursos.
         * Elimina las referencias de la carreras eliminada que existan en los cursos.
         * @param codCarreraAEliminar Es el codigo de la carrera aque se desea eliminar del sistema
         */
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
                //Elimino los cursos que pertenecen a la carrera
                for (Semestre semestre : semestresAEliminar)
                {       for (Curso curso : semestre.getCursosArrayList())
                        {       this.listaCursos.remove(curso);
                        }
                }
                //elimino las referencias de los cursos eliminados que existan en los profesores
                //HACER ESTA PARTE DEL CODIGO!!!

                //elimino los semestres que posee la carrera eliminada de la lista de semestres
                for (Semestre semestre : semestresAEliminar)
                {       this.listaSemestres.remove(semestre);
                }
        }

}
