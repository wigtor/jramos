 /**
 ******************************************************
 * @file CapaIOCursos.java
 * @author victor flores sanchez
 * @date mayo 2010
 * @version 0.1
 * La clase capaIOCursos se encarga de leer y escribir ArrayList de profesores en el archivo de profesores
 * Cada profesor especificado en el archivo está especificado entre caracteres '<' '>'.
 * @brief En este archivo se especifica la clase CapaIOCursos que se encarga de leer/escribir datos en el archivo de cursos.
 *****************************************************/


package jramos.capaIO;

import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Facultad;
import jramos.tiposDatos.Semestre;
import jramos.excepciones.HourOutOfRangeException;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class CapaIOCursos
{	/* ATRIBUTOS */
	private String nombreArchivoCursos;
	private static final int capacidadInicialVector = 100;
	private static final int capacidadInicialString = 200;

	/* CONSTRUCTORES */
        /**
         * Este constructor instancia una capa de escritura de cursos
         * Usa como archivo de cursos por default un archivo llamado "archCursos.txt"
         * El archivo de cursos por default se almacena en el directorio home del usuario
         * Se encarga de comprobar si el archivo de cursos existe o no, si no es así crea un archivo vacio.
         * @throws IOException
         */
	public CapaIOCursos() throws IOException
	{	this.nombreArchivoCursos = (System.getProperty("user.home") + System.getProperty("file.separator") + "archCursos.txt");
		File archPrueba = new File(this.nombreArchivoCursos);
		/** Si no existe el archivo de cursos, este es creado.*/
		try
		{	if (archPrueba.createNewFile())
				System.out.println("Se ha creado el archivo de cursos vacio\nEn: " + this.nombreArchivoCursos);
		}
		catch (IOException IOE)
		{	System.out.println("No se puede crear un archivo de cursos en: \n" + this.nombreArchivoCursos + "\nError grave!!");
			throw IOE;
		}
	}

	/* METODOS */

        /**
         * Lee desde el archivo de cursos un idInicial
         * que será usado a futuro para setear el atributo static idObjetoActual de la clase Curso, Carrera, Facultad o Semestre
         * el tipoId que se debe proporcionar debe ser el String, por ejemplo:
         * "idCursos", "idCarreras", "idSemestres" o "idFacultades".
         * si no encuentra un id válido en el archivo de cursos devuelve un id con el valor 0 .
         * @param tipoId Corresponde a un String con el tipo de id que se desea leer desde el archivo
         * @return Devuelve el idInicial leido desde el archivo de cursos modelado como un objeto Integer
         * @throws FileNotFoundException
         * @throws IOException
         */
        public Integer leeIDInicial(String tipoId) throws FileNotFoundException, IOException
        {       int idInicial = 0;
                BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;

		/** Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoCursos));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}

                for (i = 0; (caracterLeido != -1) && idInicial == 0; i++)
		{	caracterLeido = lector.read();
			/**Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) && (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(String.valueOf((char)caracterLeido));
                                        //lineaDatos.append(Character.forDigit(caracterLeido, 10));
					caracterLeido = lector.read();
				}
				lineaDatos.append(String.valueOf((char)caracterLeido));//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/** Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
			idInicial = this.stringToIdInicial(new String(lineaDatos.toString()), tipoId);
			lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();
                return new Integer(idInicial);
        }

        /**
         * Extrae el valor de un idInicial de un string y lo devuelve como entero.
         * @param linea corresponde al String que debe ser analizado buscando un Id inicial.
         * @param tipoId es un string que especifica el tipo de id que se está buscando.
         * @return Devuelve un valor entero con el id inicial que debe tener la clase Curso, Semestre, Carrera o Facultad.
         */
        private int stringToIdInicial(String linea, String tipoId)
        {       int comienzoDato, idInicial;
                if (((linea.indexOf("<idCursosInicial") != -1)) && (tipoId.equals("idCursos")))
                {       comienzoDato = linea.indexOf("idCursosInicial=") + "idCursosInicial=".length();
                        idInicial = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));
                        return idInicial;
                }
                else if (((linea.indexOf("<idCarrerasInicial") != -1)) && (tipoId.equals("idCarreras")))
                {       comienzoDato = linea.indexOf("idCarrerasInicial=") + "idCarrerasInicial=".length();
                        idInicial = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));
                        return idInicial;
                }
                else if (((linea.indexOf("<idSemestresInicial") != -1)) && (tipoId.equals("idSemestres")))
                {       comienzoDato = linea.indexOf("idSemestresInicial=") + "idSemestresInicial=".length();
                        idInicial = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));
                        return idInicial;
                }
                else if (((linea.indexOf("<idFacultadesInicial") != -1)) && (tipoId.equals("idFacultades")))
                {       comienzoDato = linea.indexOf("idFacultadesInicial=") + "idFacultadesInicial=".length();
                        idInicial = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));
                        return idInicial;
                }
                else
                        return 0;
        }

        /**
         * Metodo que lee todas las carreras desde el archivo de cursos.
         * Comprueba que el archivo de cursos exista y es posible leerlo.
         * Lee caracter a caracter del archivo hasta que encuentra un caracter '<',
         * El caracter '<' indica que comienza la especificación de un objeto nuevo.
         * guarda en un String todos los caracteres desde el '<' hasta un '>'
         * El string leido es enviado al método stringToCarrera para modelarlo como un objeto Carrera
         * Si la linea que se encontró entre '<' '>' no es una carrera la omite.
         * El nuevo objeto modelado es agregado a un ArrayList de Carreras, el cual es devuelvo por este método
         * @return Devuelve un ArrayList con todos las carreras leidas desde el archivo de cursos.
         * @throws FileNotFoundException
         * @throws IOException
         */
        public ArrayList<Carrera> leeCarreras() throws FileNotFoundException, IOException
        {       ArrayList<Carrera> listaCarreras= new ArrayList(CapaIOCursos.capacidadInicialVector);
		BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;

		/** Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoCursos));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}

		/** Leo el archivo de cursos hasta el final */
		for (i = 0; caracterLeido != -1; i++)
		{	caracterLeido = lector.read();
			/**Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) && (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(String.valueOf((char)caracterLeido));
                                        //lineaDatos.append(Character.forDigit(caracterLeido, 10));
					caracterLeido = lector.read();
				}
				lineaDatos.append(String.valueOf((char)caracterLeido));//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/** Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
			Carrera carreraEncontrada = this.stringToCarrera(new String(lineaDatos.toString()));
			if (carreraEncontrada != null)
				listaCarreras.add(carreraEncontrada);
			lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();

		/**  Retorno la lista con los cursos leidos*/
		return listaCarreras;
	}


	/**
         * Metodo que lee todos los cursos desde el archivo de cursos.
         * Comprueba que el archivo de cursos exista y es posible leerlo.
         * Lee caracter a caracter del archivo hasta que encuentra un caracter '<',
         * El caracter '<' indica que comienza la especificación de un objeto nuevo.
         * guarda en un String todos los caracteres desde el '<' hasta un '>'
         * El string leido es enviado al método stringToCurso para modelarlo como un objeto Curso
         * Si la linea que se encontró entre '<' '>' no es un Curso la omite.
         * El nuevo objeto modelado es agregado a un ArrayList de Cursos, el cual es devuelvo por este método
         * @return Devuelve un ArrayList con todos los cursos leidos desde el archivo de cursos.
         * @throws FileNotFoundException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
	public ArrayList<Curso> leeCursos() throws FileNotFoundException, IOException, HourOutOfRangeException
	{	ArrayList<Curso> listaCursos= new ArrayList(CapaIOCursos.capacidadInicialVector);
		BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;
		
		/** Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoCursos));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}
		
		/** Leo el archivo de cursos hasta el final */
		for (i = 0; caracterLeido != -1; i++)
		{	caracterLeido = lector.read();
			/**Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) && (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(String.valueOf((char)caracterLeido));
                                        //lineaDatos.append(Character.forDigit(caracterLeido, 10));
					caracterLeido = lector.read();
				}
				lineaDatos.append(String.valueOf((char)caracterLeido));//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/** Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
			Curso cursoEncontrado = this.stringToCurso(new String(lineaDatos.toString()));
			if (cursoEncontrado != null)
				listaCursos.add(cursoEncontrado);
			lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();

		/**  Retorno la lista con los cursos leidos*/
		return listaCursos;
	}

        /**
         * Metodo que lee todos los semestres desde el archivo de cursos.
         * Comprueba que el archivo de cursos exista y es posible leerlo.
         * Lee caracter a caracter del archivo hasta que encuentra un caracter '<',
         * El caracter '<' indica que comienza la especificación de un objeto nuevo.
         * guarda en un String todos los caracteres desde el '<' hasta un '>'
         * El string leido es enviado al método stringToSemestre para modelarlo como un objeto Semestre
         * Si la linea que se encontró entre '<' '>' no es un Semestre la omite.
         * El nuevo objeto modelado es agregado a un ArrayList de Semestres, el cual es devuelvo por este método
         * @return Devuelve un ArrayList con todos los Semestres leidos desde el archivo de cursos.
         * @throws FileNotFoundException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public ArrayList<Semestre> leeSemestres() throws FileNotFoundException, SecurityException, IOException
        {       ArrayList<Semestre> listaSemestres= new ArrayList(CapaIOCursos.capacidadInicialVector);
		BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;

		/* Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoCursos));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}

		/* Leo el archivo de cursos hasta el final */
		for (i = 0; caracterLeido != -1; i++)
		{	caracterLeido = lector.read();
			/*Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) && (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(String.valueOf((char)caracterLeido));
                                        //lineaDatos.append(Character.forDigit(caracterLeido, 10));
					caracterLeido = lector.read();
				}
				lineaDatos.append(String.valueOf((char)caracterLeido));//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/* Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
			Semestre semestreEncontrado = this.stringToSemestre(new String(lineaDatos.toString()));
			if (semestreEncontrado != null)
				listaSemestres.add(semestreEncontrado);
			lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/* Cierro el archivo*/
		lector.close();

		/*  Retorno la lista con los cursos leidos*/
		return listaSemestres;



        }

        /**
         * Metodo que lee todas las facultades desde el archivo de cursos.
         * Comprueba que el archivo de cursos exista y es posible leerlo.
         * Lee caracter a caracter del archivo hasta que encuentra un caracter '<',
         * El caracter '<' indica que comienza la especificación de un objeto nuevo.
         * guarda en un String todos los caracteres desde el '<' hasta un '>'
         * El string leido es enviado al método stringToFacultad para modelarlo como un objeto Facultad
         * Si la linea que se encontró entre '<' '>' no es una Facultad la omite.
         * El nuevo objeto modelado es agregado a un ArrayList de Facultades, el cual es devuelvo por este método
         * @return Devuelve un ArrayList con todos las facultades leidas desde el archivo de cursos.
         * @throws FileNotFoundException
         * @throws IOException
         */
        public ArrayList<Facultad> leeFacultades() throws FileNotFoundException, SecurityException, IOException
        {       ArrayList<Facultad> listaFacultades= new ArrayList(CapaIOCursos.capacidadInicialVector);
		BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;
                Facultad facultadEncontrada;

		/* Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoCursos));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}

		/* Leo el archivo de cursos hasta el final */
		for (i = 0; caracterLeido != -1; i++)
		{	caracterLeido = lector.read();
			/*Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) && (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(String.valueOf((char)caracterLeido));
                                        //lineaDatos.append(Character.forDigit(caracterLeido, 10));
					caracterLeido = lector.read();
				}
				lineaDatos.append(String.valueOf((char)caracterLeido));//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/** Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
                        facultadEncontrada = this.stringToFacultad(new String(lineaDatos.toString()));
			if (facultadEncontrada != null)
				listaFacultades.add(facultadEncontrada);
			lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/* Cierro el archivo*/
		lector.close();

		/*  Retorno la lista con los cursos leidos*/
		return listaFacultades;
        }

        /**
         * Este método es usado cuando no se conoce el idInicial desde las clases Carrera, Curso, Semestre y Facultad
         * Lee los id desde le archivo de cursos, si no encuentra uno, entonces el id vale 0
         * Llama al otro método escribeCarreras() que necesita los id.
         * @param listaCarreras Es la lista de carreras que se desea escribir en el archivo de cursos
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeCarreras(ArrayList<Carrera> listaCarreras) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                Integer idInicialFacultadesWrap = this.leeIDInicial("idFacultades");
                int idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades;
                if (idInicialCursosWrap == null)
                        idInicialCursos = 1;
                else
                        idInicialCursos = idInicialCursosWrap.intValue();

                if (idInicialCarrerasWrap == null)
                        idInicialCarreras = 1;
                else
                        idInicialCarreras = idInicialCarrerasWrap.intValue();

                if (idInicialSemestresWrap == null)
                        idInicialSemestres = 1;
                else
                        idInicialSemestres = idInicialSemestresWrap.intValue();

                if (idInicialFacultadesWrap == null)
                        idInicialFacultades = 1;
                else
                        idInicialFacultades = idInicialFacultadesWrap.intValue();
                escribeCarreras(listaCarreras, idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades);
        }

        /**
         * Este metodo recorre el Arraylist de Carreras proporcionado,
         * transforma las Carreras a String usando el método carreraToString()
         * y escribe cada carrera en el archivo de cursos.
         * Antes de escribir todo se leen los cursos, semestres y facultades
         * para escribir estas listas tambien. Esto porque al escribir en el archivo se borra todo lo existente.
         * En la primera linea escribe la cuenta de los id de cada clase.
         * El metodo comprueba que se puede escribir en el archivo antes de usarlo.
         * @param listaCarreras Es la lista de carreras que se desea escribir en e archivo de cursos.
         * @param idInicialCursos Es el id inicial obtenido desde la clase Curso que lleva la cuenta de los id asignados.
         * @param idInicialCarreras Es el id inicial obtenido desde la clase Carrera que lleva la cuenta de los id asignados.
         * @param idInicialSemestres Es el id inicial obtenido desde la clase Semestre que lleva la cuenta de los id asignados.
         * @param idInicialFacultades Es el id inicial obtenido desde la clase Facultad que lleva la cuenta de los id asignados.
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeCarreras(ArrayList<Carrera> listaCarreras, int idInicialCursos, int idInicialCarreras, int idInicialSemestres, int idInicialFacultades) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
        {       PrintWriter escritor;
		ArrayList<Curso> listaCursos = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Semestre> listaSemestres = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Facultad> listaFacultades = new ArrayList(CapaIOCursos.capacidadInicialVector);
		/** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaFacultades = this.leeFacultades();
                listaCursos = this.leeCursos();
                listaSemestres = this.leeSemestres();


		int i;
		/** Intenta abrir el archivo de cursos para escribir en él. */
		try
		{	escritor = new PrintWriter(this.nombreArchivoCursos);
		}
		catch (FileNotFoundException FNFE)
		{	System.out.println("ERROR: El archivo no existe"); //no deberia llegar a esta excepcion con el constructor que crea el archivo.
			throw FNFE;

		}
		catch (SecurityException SE)
		{	System.out.println("ERROR: No tiene permisos de escritura sobre el archivo de cursos.");
			throw SE;
		}


                //Escribo los idIniciales de carreras, semestres y cursos
                escritor.println("<idFacultadesInicial=\""+idInicialFacultades+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

                //Escribo las facultades en el archivo de cursos.
		for(i = 0; i<listaFacultades.size();i++)
		{       escritor.println(this.facultadToString(listaFacultades.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo las carreras del ArrayList<Carreras> en el archivo de cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{       escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

                //Escribo los cursos en el archivo de cursos
		for(i = 0; i<listaCursos.size();i++)
		{	escritor.println(this.cursoToString(listaCursos.get(i)));//Escribo en el archivo de cursos.
		}

                //Escribo los Semestres del ArrayList<Semestre> en el archivo de cursos.
		for(i = 0; i<listaSemestres.size();i++)
		{       escritor.println(this.semestreToString(listaSemestres.get(i)));//Escribo en el archivo de cursos.
		}

		/** Cierro el archivo*/
		escritor.close();

                
        }

        /**
         * Este método es usado cuando no se conoce el idInicial desde las clases Carrera, Curso, Semestre y Facultad
         * Lee los id desde le archivo de cursos, si no encuentra uno, entonces el id vale 0
         * Llama al otro método escribeFacultades() que necesita los id.
         * @param listaFacultades Es la lista de facultades que se desea escribir en el archivo de cursos
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeFacultades(ArrayList<Facultad> listaFacultades) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                Integer idInicialFacultadesWrap = this.leeIDInicial("idFacultades");
                int idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades;
                if (idInicialCursosWrap == null)
                        idInicialCursos = 1;
                else
                        idInicialCursos = idInicialCursosWrap.intValue();

                if (idInicialCarrerasWrap == null)
                        idInicialCarreras = 1;
                else
                        idInicialCarreras = idInicialCarrerasWrap.intValue();

                if (idInicialSemestresWrap == null)
                        idInicialSemestres = 1;
                else
                        idInicialSemestres = idInicialSemestresWrap.intValue();

                if (idInicialFacultadesWrap == null)
                        idInicialFacultades = 1;
                else
                        idInicialFacultades = idInicialFacultadesWrap.intValue();
                escribeFacultades(listaFacultades, idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades);
        }

        /**
         * Este metodo recorre el Arraylist de facultades proporcionado,
         * transforma las facultades a String usando el método facultadToString()
         * y escribe cada facultad en el archivo de cursos.
         * Antes de escribir todo se leen los cursos, semestres y carreras
         * para escribir estas listas tambien. Esto porque al escribir en el archivo se borra todo lo existente.
         * En la primera linea escribe la cuenta de los id de cada clase.
         * El metodo comprueba que se puede escribir en el archivo antes de usarlo
         * @param listaFacultades Es la lista de facultades que se desea escribir en e archivo de cursos.
         * @param idInicialCursos Es el id inicial obtenido desde la clase Curso que lleva la cuenta de los id asignados.
         * @param idInicialCarreras Es el id inicial obtenido desde la clase Carrera que lleva la cuenta de los id asignados.
         * @param idInicialSemestres Es el id inicial obtenido desde la clase Semestre que lleva la cuenta de los id asignados.
         * @param idInicialFacultades Es el id inicial obtenido desde la clase Facultad que lleva la cuenta de los id asignados.
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeFacultades(ArrayList<Facultad> listaFacultades, int idInicialCursos, int idInicialCarreras, int idInicialSemestres, int idInicialFacultades) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
        {       PrintWriter escritor;
		ArrayList<Curso> listaCursos = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Semestre> listaSemestres = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Carrera> listaCarreras = new ArrayList(CapaIOCursos.capacidadInicialVector);
		/** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaCarreras = this.leeCarreras();
                listaCursos = this.leeCursos();
                listaSemestres = this.leeSemestres();


		int i;
		/** Intenta abrir el archivo de cursos para escribir en él. */
		try
		{	escritor = new PrintWriter(this.nombreArchivoCursos);
		}
		catch (FileNotFoundException FNFE)
		{	System.out.println("ERROR: El archivo no existe"); //no deberia llegar a esta excepcion con el constructor que crea el archivo.
			throw FNFE;

		}
		catch (SecurityException SE)
		{	System.out.println("ERROR: No tiene permisos de escritura sobre el archivo de cursos.");
			throw SE;
		}


                //Escribo los idIniciales de carreras, semestres y cursos
                escritor.println("<idFacultadesInicial=\""+idInicialFacultades+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

                //Escribo las facultades en el archivo de cursos.
		for(i = 0; i<listaFacultades.size();i++)
		{       escritor.println(this.facultadToString(listaFacultades.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo las carreras del ArrayList<Carreras> en el archivo de cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{       escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

                //Escribo los cursos en el archivo de cursos
		for(i = 0; i<listaCursos.size();i++)
		{	escritor.println(this.cursoToString(listaCursos.get(i)));//Escribo en el archivo de cursos.
		}

                //Escribo los Semestres del ArrayList<Semestre> en el archivo de cursos.
		for(i = 0; i<listaSemestres.size();i++)
		{       escritor.println(this.semestreToString(listaSemestres.get(i)));//Escribo en el archivo de cursos.
		}

		/** Cierro el archivo*/
		escritor.close();


        }

        /**
         * Este método es usado cuando no se conoce el idInicial desde las clases Carrera, Curso, Semestre y Facultad
         * Lee los id desde le archivo de cursos, si no encuentra uno, entonces el id vale 0
         * Llama al otro método escribeCursos() que necesita los id.
         * @param listaCursos Es la lista de cursos que se desea escribir en el archivo de cursos
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         */
        public void escribeCursos(ArrayList<Curso> listaCursos) throws FileNotFoundException, SecurityException, IOException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                Integer idInicialFacultadesWrap = this.leeIDInicial("idFacultades");
                int idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades;
                if (idInicialCursosWrap == null)
                        idInicialCursos = 1;
                else
                        idInicialCursos = idInicialCursosWrap.intValue();

                if (idInicialCarrerasWrap == null)
                        idInicialCarreras = 1;
                else
                        idInicialCarreras = idInicialCarrerasWrap.intValue();

                if (idInicialSemestresWrap == null)
                        idInicialSemestres = 1;
                else
                        idInicialSemestres = idInicialSemestresWrap.intValue();

                if (idInicialFacultadesWrap == null)
                        idInicialFacultades = 1;
                else
                        idInicialFacultades = idInicialFacultadesWrap.intValue();
                escribeCursos(listaCursos, idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades);
        }

        /**
         * Este metodo recorre el Arraylist de cursos proporcionado,
         * transforma los cursos a String usando el método cursoToString()
         * y escribe cada curso en el archivo de cursos.
         * Antes de escribir todo se leen las carreras, semestres y facultades
         * para escribir estas listas tambien. Esto porque al escribir en el archivo se borra todo lo existente.
         * En la primera linea escribe la cuenta de los id de cada clase.
         * El metodo comprueba que se puede escribir en el archivo antes de usarlo.
         * @param listaCursos Es la lista de cursos que se desea escribir en e archivo de cursos.
         * @param idInicialCursos Es el id inicial obtenido desde la clase Curso que lleva la cuenta de los id asignados.
         * @param idInicialCarreras Es el id inicial obtenido desde la clase Carrera que lleva la cuenta de los id asignados.
         * @param idInicialSemestres Es el id inicial obtenido desde la clase Semestre que lleva la cuenta de los id asignados.
         * @param idInicialFacultades Es el id inicial obtenido desde la clase Facultad que lleva la cuenta de los id asignados.
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         */
	public void escribeCursos(ArrayList<Curso> listaCursos, int idInicialCursos, int idInicialCarreras, int idInicialSemestres, int idInicialFacultades) throws FileNotFoundException, SecurityException, IOException
	{	PrintWriter escritor;
		ArrayList<Carrera> listaCarreras = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Semestre> listaSemestres = new ArrayList(CapaIOCursos.capacidadInicialVector);
		ArrayList<Facultad> listaFacultades = new ArrayList(CapaIOCursos.capacidadInicialVector);
                /** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaFacultades = this.leeFacultades();
                listaCarreras = this.leeCarreras();
                listaSemestres = this.leeSemestres();
		int i;
		/** Intenta abrir el archivo de cursos para escribir en él. */
		try
		{	escritor = new PrintWriter(this.nombreArchivoCursos);
		}
		catch (FileNotFoundException FNFE)
		{	System.out.println("ERROR: El archivo no existe"); //no deberia llegar a esta excepcion con el constructor que crea el archivo.
			throw FNFE;
			
		}
		catch (SecurityException SE)
		{	System.out.println("ERROR: No tiene permisos de escritura sobre el archivo de cursos.");
			throw SE;
		}


                //Escribo los idIniciales de carreras y cursos
                escritor.println("<idFacultadesInicial=\""+idInicialFacultades+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

                //Escribo las facultades en el archivo de cursos.
		for(i = 0; i<listaFacultades.size();i++)
		{       escritor.println(this.facultadToString(listaFacultades.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo las carreras en el archivo de cursos antes que los cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{	escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo los Cursos del ArrayList<Curso> en el archivo de cursos.
		for(i = 0; i<listaCursos.size();i++)
		{       escritor.println(this.cursoToString(listaCursos.get(i)));//Escribo en el archivo de cursos.
		}
		
                //Escribo los Semestres del ArrayList<Semestre> en el archivo de cursos.
		for(i = 0; i<listaSemestres.size();i++)
		{       escritor.println(this.semestreToString(listaSemestres.get(i)));//Escribo en el archivo de cursos.
		}

		/** Cierro el archivo*/
		escritor.close();
	}

        /**
         * Este método es usado cuando no se conoce el idInicial desde las clases Carrera, Curso, Semestre y Facultad
         * Lee los id desde le archivo de cursos, si no encuentra uno, entonces el id vale 0
         * Llama al otro método escribeCursos() que necesita los id.
         * @param listaSemestres Es la lista de semestres que se desea escribir en el archivo de cursos
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeSemestres(ArrayList<Semestre> listaSemestres) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                Integer idInicialFacultadesWrap = this.leeIDInicial("idFacultades");
                int idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades;
                if (idInicialCursosWrap == null)
                        idInicialCursos = 1;
                else
                        idInicialCursos = idInicialCursosWrap.intValue();

                if (idInicialCarrerasWrap == null)
                        idInicialCarreras = 1;
                else
                        idInicialCarreras = idInicialCarrerasWrap.intValue();

                if (idInicialSemestresWrap == null)
                        idInicialSemestres = 1;
                else
                        idInicialSemestres = idInicialSemestresWrap.intValue();

                if (idInicialFacultadesWrap == null)
                        idInicialFacultades = 1;
                else
                        idInicialFacultades = idInicialFacultadesWrap.intValue();
                escribeSemestres(listaSemestres, idInicialCursos, idInicialCarreras, idInicialSemestres, idInicialFacultades);
        }

        /**
         * Este metodo recorre el Arraylist de Semestres proporcionado,
         * transforma los semestres a String usando el método semestreToString()
         * y escribe cada semestre en el archivo de cursos.
         * Antes de escribir todo se leen los cursos, carreras y facultades
         * para escribir estas listas tambien. Esto porque al escribir en el archivo se borra todo lo existente.
         * En la primera linea escribe la cuenta de los id de cada clase.
         * El metodo comprueba que se puede escribir en el archivo antes de usarlo.
         * @param listaSemestres Es la lista de semestres que se desea escribir en e archivo de cursos.
         * @param idInicialCursos Es el id inicial obtenido desde la clase Curso que lleva la cuenta de los id asignados.
         * @param idInicialCarreras Es el id inicial obtenido desde la clase Carrera que lleva la cuenta de los id asignados.
         * @param idInicialSemestres Es el id inicial obtenido desde la clase Semestre que lleva la cuenta de los id asignados.
         * @param idInicialFacultades Es el id inicial obtenido desde la clase Facultad que lleva la cuenta de los id asignados.
         * @throws FileNotFoundException
         * @throws SecurityException
         * @throws IOException
         * @throws HourOutOfRangeException
         */
        public void escribeSemestres(ArrayList<Semestre> listaSemestres, int idInicialCursos, int idInicialCarreras, int idInicialSemestres, int idInicialFacultades) throws FileNotFoundException, SecurityException, IOException, HourOutOfRangeException
	{	PrintWriter escritor;
                ArrayList<Carrera> listaCarreras = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Curso> listaCursos = new ArrayList(CapaIOCursos.capacidadInicialVector);
		ArrayList<Facultad> listaFacultades = new ArrayList(CapaIOCursos.capacidadInicialVector);
                /** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaFacultades = this.leeFacultades();
                listaCarreras = this.leeCarreras();
                listaCursos = this.leeCursos();

		int i;
		/** Intenta abrir el archivo de cursos para escribir en él. */
		try
		{	escritor = new PrintWriter(this.nombreArchivoCursos);
		}
		catch (FileNotFoundException FNFE)
		{	System.out.println("ERROR: El archivo no existe"); //no deberia llegar a esta excepcion con el constructor que crea el archivo.
			throw FNFE;

		}
		catch (SecurityException SE)
		{	System.out.println("ERROR: No tiene permisos de escritura sobre el archivo de cursos.");
			throw SE;
		}


                //Escribo los idIniciales de carreras y cursos
                escritor.println("<idFacultadesInicial=\""+idInicialFacultades+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

                //Escribo las facultades en el archivo de cursos.
		for(i = 0; i<listaFacultades.size();i++)
		{	escritor.println(this.facultadToString(listaFacultades.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo las carreras en el archivo de cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{	escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo los Cursos del ArrayList<Curso> en el archivo de cursos.
		for(i = 0; i<listaCursos.size();i++)
		{       escritor.println(this.cursoToString(listaCursos.get(i)));//Escribo en el archivo de cursos.
                }

                //Escribo los Semestres del ArrayList<Semestre> en el archivo de cursos.
		for(i = 0; i<listaSemestres.size();i++)
		{       escritor.println(this.semestreToString(listaSemestres.get(i)));//Escribo en el archivo de cursos.
		}

		/** Cierro el archivo*/
		escritor.close();
	}

        /**
         * Este método crea una carrera usando los datos extraidos desde un String
         * Crea un objeto Carrera y setea sus atributos.
         * @param linea Es el string con la carrera que se desea modelar como objeto Carrera.
         * @return Devuelve un objeto Carrera que fue modelado a partir del String linea.
         */
        private Carrera stringToCarrera(String linea)
        {       String nomCarrera;
                String descrip;
                String idSemestresStr;
                Integer idSemestres;
                int comienzoDato, i, codCarrera, posicionBarra, idSemestre;
                if ((linea.indexOf("<Carrera") != -1))
                {       if ((linea.indexOf("nomCarrera=") == -1) || (linea.indexOf("descrip=") == -1) || (linea.indexOf("codCarrera=") == -1) || (linea.indexOf("idSemestres=") == -1))
                        {       System.out.println("ERROR: La linea leida desde el archivo de cursos es incorrecta");
                                return null;
                        }
                        comienzoDato = linea.indexOf("codCarrera=") + "codCarrera=".length();
                        codCarrera = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));

                        comienzoDato = linea.indexOf("nomCarrera=") + "nomCarrera=".length();
                        nomCarrera = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        comienzoDato = linea.indexOf("descrip=") + "descrip=".length();
                        descrip = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        comienzoDato = linea.indexOf("idSemestres=") + "idSemestres=".length();
                        idSemestresStr = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        Carrera carreraLeida = new Carrera(nomCarrera, codCarrera);
                        carreraLeida.setDescripcion(descrip);
                        //acá seteo los id de los semestres de carreraLeida!!!, alexis debes hacer un setter parar los id de semestre en las carreras
                        if (idSemestresStr.length() != 0)
                        {       for (i = 0; idSemestresStr.indexOf("|") != -1;i++)
                                {       idSemestre = Integer.valueOf(idSemestresStr.substring(0, idSemestresStr.indexOf("|")));
                                        posicionBarra = idSemestresStr.indexOf("|");
                                        idSemestresStr = idSemestresStr.substring(posicionBarra+1);
                                        carreraLeida.modIdSemestres(idSemestre, 1);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                carreraLeida.modIdSemestres(Integer.valueOf(idSemestresStr), 1);
                        }

                        return carreraLeida;
                }
                else
                    return null;
        }
	/**
         * Este método crea un curso usando los datos extraidos desde un String
         * Crea un objeto Curso y setean sus atributos.
         * @param linea Es el string con el curso que se desea modelar como objeto Curso.
         * @return Devuelve un objeto Curso que fue modelado a partir del String linea.
         * @throws HourOutOfRangeException
         */
	private Curso stringToCurso(String linea) throws HourOutOfRangeException
        {
		String nomCurso; //Nombre del curso
		String descrip; //Descripción del curso
		String codCurso; //Código del ramo
		String seccion; //código de sección
		String enCarrera; //Carrera en que se dicta.
		String idProfeAsig; //id del profesor que dicta el curso.
		String listSalas; //Salas donde se dicta el ramo.
		String horario; //Horas en que se dicta e la semana.
		int comienzoDato, i, codigoCarrera, posicionBarra, idCurso, sala;
                Hora objHora;
		
		/* Si es un curso lo que está espeficado en la linea, creo un objeto "Curso" */
		if ((linea.indexOf("<Curso") != -1))
		{	/* Busco errores de sintaxis en la linea analizada*/
			if ((linea.indexOf("idCurso=") == -1) || (linea.indexOf("nomCurso=") == -1) || (linea.indexOf("descrip=") == -1) || (linea.indexOf("codCurso=") == -1) || (linea.indexOf("seccion=") == -1) || (linea.indexOf("enCarrera=") == -1) || (linea.indexOf("idProfeAsig=") == -1) || (linea.indexOf("listSalas=") == -1) || (linea.indexOf("horario=") == -1))
                        {       System.out.println("ERROR: La linea leida desde el archivo de cursos es incorrecta");
                                return null;
                        }

                        /* Busco el id del curso */
                        comienzoDato = linea.indexOf("idCurso=") + "idCurso=".length();
                        idCurso = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));
                        
			/* Busco el nombre del curso en la linea*/
			comienzoDato = linea.indexOf("nomCurso=") + "nomCurso=".length();
			nomCurso = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco la descripción del curso en la linea*/
			comienzoDato = linea.indexOf("descrip=") + "descrip=".length();
			descrip = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco el código del curso en la linea*/
			comienzoDato = linea.indexOf("codCurso=") + "codCurso=".length();
			codCurso = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco las secciones del curso en la linea*/
			comienzoDato = linea.indexOf("seccion=") + "seccion=".length();
			seccion = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco las carreras del curso en la linea*/
			comienzoDato = linea.indexOf("enCarrera=") + "enCarrera=".length();
			enCarrera = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco el profesor del curso en la linea*/
			comienzoDato = linea.indexOf("idProfeAsig=") + "idProfeAsig=".length();
			idProfeAsig = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco las salas donde se dicta del curso en la linea*/
			comienzoDato = linea.indexOf("listSalas=") + "listSalas=".length();
			listSalas = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco el horario del curso en la linea*/
			comienzoDato = linea.indexOf("horario=") + "horario=".length();
			horario = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Construyo el objeto cursoLeido con los datos recopilados */
			Curso cursoLeido = new Curso(nomCurso, Integer.valueOf(codCurso), idCurso);
                        cursoLeido.setDescripcion(descrip);
                        cursoLeido.setSeccion(seccion);
                        cursoLeido.setIdProfeAsig(Integer.valueOf(idProfeAsig));
                        cursoLeido.setCodigoEnCarrera(Integer.valueOf(enCarrera));
                        
                        //Seteo la lista de salas asignadas al curso
                        if (listSalas.length() != 0)
                        {       for (i = 0; listSalas.indexOf("|") != -1;i++)
                                {       System.out.println(listSalas.substring(0, listSalas.indexOf("|")));
                                        sala = Integer.valueOf(listSalas.substring(0, listSalas.indexOf("|")));
                                        posicionBarra = listSalas.indexOf("|");
                                        listSalas = listSalas.substring(posicionBarra+1);
                                        cursoLeido.modListaSalas(sala, 1);
                                        System.out.println("En sala: " + sala);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                cursoLeido.modListaSalas(Integer.valueOf(listSalas), 1);
                                System.out.println("En sala: " +listSalas);
                        }

                        //Seteo la lista de horarios asignadas al curso
                        if (horario.length() != 0)
                        {       for (i = 0; horario.indexOf("|") != -1;i++)
                                {       objHora = new Hora(horario.substring(0, horario.indexOf("|")));
                                        posicionBarra = horario.indexOf("|");
                                        horario = horario.substring(posicionBarra+1);
                                        cursoLeido.modHorario(objHora, 1);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                cursoLeido.modHorario(new Hora(horario), 1);
                        }
                        
			return cursoLeido;
		}

		else
			return null;
	}
	/**
         * Este metodo transforma un objeto Curso a un string para que pueda ser almacenado en el archivo de cursos.
         * @param cursoAEscribir es un objeto Curso que se desea modelar como String.
         * @return Devuelve el objeto Curso modelado como String según la sintaxis válida del archivo de cursos.
         */
	private String cursoToString(Curso cursoAEscribir)
	{	String cursoString;
                int idCurso = cursoAEscribir.getIdCurso();
		String nomCurso = cursoAEscribir.getNombreCurso(); //Nombre del curso
		String descrip = cursoAEscribir.getDescripcion(); //Descripción del curso
		int codCurso = cursoAEscribir.getCodigoCurso(); //Código del ramo
		String seccion = cursoAEscribir.getSeccion(); //código de sección
		int enCarrera = cursoAEscribir.getEnCarreraCodigo(); //Carrera en que se dicta.
		int idProfeAsig = cursoAEscribir.getIdProfeAsig(); //rut del profesor
		String listSalas = cursoAEscribir.getSalas(); //Salas donde se dicta el ramo.
		String horario = cursoAEscribir.getHorario(); //Horas en que se dicta e la semana.
		cursoString = "<Curso nomCurso=\""+nomCurso+"\" idCurso=\""+idCurso+"\" descrip=\""+descrip+"\" codCurso=\""+codCurso+"\" seccion=\""+seccion+"\" enCarrera=\""+enCarrera+"\" idProfeAsig=\""+idProfeAsig+"\" listSalas=\""+listSalas+"\" horario=\""+horario+"\" >";
		return cursoString;
	}

        /**
         * Este metodo transforma un objeto Facultad a un string para que pueda ser almacenado en el archivo de cursos.
         * @param facultadAEscribir es un objeto Facultad que se desea modelar como String.
         * @return Devuelve el objeto Facultad modelado como String según la sintaxis válida del archivo de cursos.
         */
        private String facultadToString(Facultad facultadAEscribir)
        {       String nomFacultad = facultadAEscribir.getNombreFacultad();
		String descrip = facultadAEscribir.getDescripcion();
		int idFacultad = facultadAEscribir.getIdFacultad();
                String codCarreras = facultadAEscribir.getCodigosCarreras();
		return "<Facultad nomFacultad=\""+nomFacultad+"\" idFacultad=\""+idFacultad+"\" descrip=\""+descrip+"\" codCarreras=\""+codCarreras+"\" >";
        }

        /**
         * Este método crea una facultad usando los datos extraidos desde un String
         * Crea un objeto Facultad y setea sus atributos.
         * @param linea Es el string con la facultad que se desea modelar como objeto Facultad.
         * @return Devuelve un objeto Facultad que fue modelado a partir del String linea.
         */
        private Facultad stringToFacultad(String linea)
        {       String nomFacultad, descrip, codCarreras;
                int comienzoDato, i, posicionBarra, idFacultad, codCarrera;
                if ((linea.indexOf("<Facultad") != -1))
                {       if ((linea.indexOf("nomFacultad=") == -1) || (linea.indexOf("idFacultad=") == -1) || (linea.indexOf("descrip=") == -1) || (linea.indexOf("codCarreras=") == -1))
                        {       System.out.println("ERROR: La linea leida desde el archivo de cursos es incorrecta");
                                return null;
                        }
                        comienzoDato = linea.indexOf("nomFacultad=") + "nomFacultad=".length();
                        nomFacultad = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        comienzoDato = linea.indexOf("idFacultad=") + "idFacultad=".length();
                        idFacultad = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));

                        comienzoDato = linea.indexOf("descrip=") + "descrip=".length();
                        descrip = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        comienzoDato = linea.indexOf("codCarreras=") + "codCarreras=".length();
                        codCarreras = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        Facultad facultadLeida = new Facultad(nomFacultad, idFacultad);
                        facultadLeida.setDescripcion(descrip);
                        //acá seteo los id de los semestres de carreraLeida!!!, alexis debes hacer un setter parar los id de semestre en las carreras
                        if (codCarreras.length() != 0)
                        {       for (i = 0; codCarreras.indexOf("|") != -1;i++)
                                {       codCarrera = Integer.valueOf(codCarreras.substring(0, codCarreras.indexOf("|")));
                                        posicionBarra = codCarreras.indexOf("|");
                                        codCarreras = codCarreras.substring(posicionBarra+1);
                                        facultadLeida.modListaCodigoCarreras(codCarrera, 1);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                facultadLeida.modListaCodigoCarreras(Integer.valueOf(codCarreras), 1);
                        }

                        return facultadLeida;
                }
                else
                    return null;
        }

        /**
         * Este metodo transforma un objeto Carrera a un string para que pueda ser almacenado en el archivo de cursos.
         * @param carreraAEscribir es un objeto Carrera que se desea modelar como String.
         * @return Devuelve el objeto Carrera modelado como String según la sintaxis válida del archivo de cursos.
         */
	private String carreraToString(Carrera carreraAEscribir)
	{	String nomCarrera = carreraAEscribir.getNombreCarrera();
		String descrip = carreraAEscribir.getDescripcion();
		int codCarrera = carreraAEscribir.getCodigoCarrera();
                String idSemestresStr = carreraAEscribir.getIdSemestres();
		return "<Carrera nomCarrera=\""+nomCarrera+"\" descrip=\""+descrip+"\" codCarrera=\""+codCarrera+"\" idSemestres=\""+idSemestresStr+"\" >";
	}

        /**
         * Este metodo transforma un objeto Semestre a un string para que pueda ser almacenado en el archivo de cursos.
         * @param semestreAEscribir es un objeto Semestre que se desea modelar como String.
         * @return Devuelve el objeto Semestre modelado como String según la sintaxis válida del archivo de cursos.
         */
        private String semestreToString(Semestre semestreAEscribir)
	{	int numSemestre = semestreAEscribir.getNumeroSemestre();
		int idSemestre = semestreAEscribir.getIdSemestre();
		int enCarreraId = semestreAEscribir.getCodigoEnCarrera();
                String idCursosDelSemestre = semestreAEscribir.getIdCursosStr();
		return "<Semestre numSemestre=\""+numSemestre+"\" idSemestre=\""+idSemestre+"\" enCarreraId=\""+enCarreraId+"\" idCursosDelSemestre=\""+idCursosDelSemestre+"\" >";
	}

        /**
         * Este método crea un semestre usando los datos extraidos desde un String
         * Crea un objeto Semestre y setea sus atributos.
         * @param linea Es el string con el semestre que se desea modelar como objeto Semestre.
         * @return Devuelve un objeto Semestre que fue modelado a partir del String linea.
         */
        private Semestre stringToSemestre(String linea)
        {       String idRamosDelSemestre;
                int comienzoDato, i, posicionBarra, numSemestre, idSemestre, enCarreraId, codRamo;
                if ((linea.indexOf("<Semestre") != -1))
                {       if ((linea.indexOf("numSemestre=") == -1) || (linea.indexOf("idSemestre=") == -1) || (linea.indexOf("enCarreraId=") == -1) || (linea.indexOf("idCursosDelSemestre=") == -1))
                        {       System.out.println("ERROR: La linea leida desde el archivo de cursos es incorrecta");
                                return null;
                        }
                        comienzoDato = linea.indexOf("numSemestre=") + "numSemestre=".length();
                        numSemestre = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));

                        comienzoDato = linea.indexOf("idSemestre=") + "idSemestre=".length();
                        idSemestre = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));

                        comienzoDato = linea.indexOf("enCarreraId=") + "enCarreraId=".length();
                        enCarreraId = Integer.valueOf(linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)));

                        comienzoDato = linea.indexOf("idCursosDelSemestre=") + "idCursosDelSemestre=".length();
                        idRamosDelSemestre = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1));

                        Semestre semestreLeido = new Semestre(numSemestre, enCarreraId, idSemestre);
                        if (idRamosDelSemestre.length() != 0)
                        {       for (i = 0; idRamosDelSemestre.indexOf("|") != -1;i++)
                                {       codRamo = Integer.valueOf(idRamosDelSemestre.substring(0, idRamosDelSemestre.indexOf("|")));
                                        posicionBarra = idRamosDelSemestre.indexOf("|");
                                        idRamosDelSemestre = idRamosDelSemestre.substring(posicionBarra+1);
                                        semestreLeido.modIdRamos(codRamo, 1);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                semestreLeido.modIdRamos(Integer.valueOf(idRamosDelSemestre), 1);
                        }

                        return semestreLeido;
                }
                else
                    return null;
        }

}


