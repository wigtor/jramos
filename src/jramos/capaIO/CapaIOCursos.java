/**
******************************************************
* @file CapaIOCursos.java
* @author victor flores sanchez
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase CapaIOCursos que se encarga de leer/escribir datos en el archivo de cursos.
*****************************************************/


package jramos.capaIO;

import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Semestre;
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
        private int ultimoIdLeidoCursos;
        private int ultimoIdLeidoCarreras;

	/* CONSTRUCTORES */
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
			if (idInicial == 0)
				System.out.println("Aviso: Lo que se ha encontrado en la linea analizada no es un id");
                        lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();
                if (idInicial != 0)
                        return new Integer(idInicial);
                else
                        return null;

        }

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
                else
                        return 0;
        }

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
			else
				System.out.println("Aviso: Lo que se ha encontrado en la linea analizada no es un curso");
                        lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();

		/**  Retorno la lista con los cursos leidos*/
		return listaCarreras;
	}


	/** Metodo para leer todos los cursos
	* 
	*/
	public ArrayList<Curso> leeCursos() throws FileNotFoundException, IOException
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
			else
				System.out.println("Aviso: Lo que se ha encontrado en la linea analizada no es un curso");
                        lineaDatos = new StringBuilder(CapaIOCursos.capacidadInicialString);
                }
		/** Cierro el archivo*/
		lector.close();

		/**  Retorno la lista con los cursos leidos*/
		return listaCursos;
	}

        public void escribeCarreras(ArrayList<Carrera> listaCarreras) throws FileNotFoundException, SecurityException, IOException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                int idInicialCursos, idInicialCarreras, idInicialSemestres;
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

                escribeCarreras(listaCarreras, idInicialCursos, idInicialCarreras, idInicialSemestres);
        }

        public void escribeCarreras(ArrayList<Carrera> listaCarreras, int idInicialCursos, int idInicialCarreras, int idInicialSemestres) throws FileNotFoundException, SecurityException, IOException
        {       PrintWriter escritor;
                String aEscribir;
		ArrayList<Curso> listaCursos = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Semestre> listaSemestres = new ArrayList(CapaIOCursos.capacidadInicialVector);
		/** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaCursos = this.leeCursos();
                //Acá debo llamar a: listaSemestres = this.leeSemestres();


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
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

		//Escribo las carreras del ArrayList<Carreras> en el archivo de cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{       escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

                //Escribo los semestres en el archivo de cursos antes que los cursos.
		/*for(i = 0; i<listaSemestres.size();i++)
		{	escritor.println(this.semestreToString(listaSemestres.get(i)));//Escribo en el archivo de cursos.
		} */

                //Escribo los cursos en el archivo de cursos
		for(i = 0; i<listaCursos.size();i++)
		{	escritor.println(this.cursoToString(listaCursos.get(i)));//Escribo en el archivo de cursos.
		}

		/** Cierro el archivo*/
		escritor.close();

                
        }

        public void escribeCursos(ArrayList<Curso> listaCursos) throws FileNotFoundException, SecurityException, IOException
        {       Integer idInicialCursosWrap = this.leeIDInicial("idCursos");
                Integer idInicialCarrerasWrap = this.leeIDInicial("idCarreras");
                Integer idInicialSemestresWrap = this.leeIDInicial("idSemestres");
                int idInicialCursos, idInicialCarreras, idInicialSemestres;
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

                escribeCursos(listaCursos, idInicialCursos, idInicialCarreras, idInicialSemestres);
        }
	/** 
	* Método que guarda todos los cursos en el archivo de cursos.
	*/
	public void escribeCursos(ArrayList<Curso> listaCursos, int idInicialCursos, int idInicialCarreras, int idInicialSemestres) throws FileNotFoundException, SecurityException, IOException
	{	PrintWriter escritor;
                String aEscribir;
		ArrayList<Carrera> listaCarreras = new ArrayList(CapaIOCursos.capacidadInicialVector);
                ArrayList<Semestre> listaSemestres = new ArrayList(CapaIOCursos.capacidadInicialVector);
		/** Leo todo el resto del contenido del archivo de cursos que no sea un "Curso" para no perder los datos.*/
		listaCarreras = this.leeCarreras();
                //Acá debo llamar a: listaSemestres = this.leeSemestres();


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
                escritor.println("<idCursosInicial=\""+idInicialCursos+"\" >");
                escritor.println("<idCarrerasInicial=\""+idInicialCarreras+"\" >");
                escritor.println("<idSemestresInicial=\""+idInicialSemestres+"\" >");

		//Escribo las carreras en el archivo de cursos antes que los cursos.
		for(i = 0; i<listaCarreras.size();i++)
		{	escritor.println(this.carreraToString(listaCarreras.get(i)));//Escribo en el archivo de cursos.
		}

		//Escribo los Cursos del ArrayList<Curso> en el archivo de cursos.
		for(i = 0; i<listaCursos.size();i++)
		{   aEscribir = this.cursoToString(listaCursos.get(i));
                    escritor.println(aEscribir);//Escribo en el archivo de cursos.
		}
		
		/** Cierro el archivo*/
		escritor.close();
	}

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
                                {       System.out.println(idSemestresStr.substring(0, idSemestresStr.indexOf("|")));
                                        idSemestre = Integer.valueOf(idSemestresStr.substring(0, idSemestresStr.indexOf("|")));
                                        posicionBarra = idSemestresStr.indexOf("|");
                                        idSemestresStr = idSemestresStr.substring(posicionBarra+1);
                                        carreraLeida.modIdSemestres(idSemestre, 1);
                                        System.out.println("En carrera: " + idSemestre);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                carreraLeida.modIdSemestres(Integer.valueOf(idSemestresStr), 1);
                                System.out.println("En carrera: " +idSemestresStr);
                        }

                        return carreraLeida;
                }
                else
                    return null;
        }
	/** 
	* Este método recibe un String que contiene especificado un objeto del tipo Curso, analiza este String y devuelve un objeto Curso.
	*/
	private Curso stringToCurso(String linea)
	{	
		String nomCurso; //Nombre del curso
		String descrip; //Descripción del curso
		String codCurso; //Código del ramo
		String seccion; //código de sección
		String enCarreras; //Carreras en que se dicta.
		String idProfeAsig; //id del profesor que dicta el curso.
		String listSalas; //Salas donde se dicta el ramo.
		String horario; //Horas en que se dicta e la semana.
		int comienzoDato, i, codigoCarrera, posicionBarra, idCurso;
		
		/* Si es un curso lo que está espeficado en la linea, creo un objeto "Curso" */
		if ((linea.indexOf("<Curso") != -1))
		{	/* Busco errores de sintaxis en la linea analizada*/
			if ((linea.indexOf("idCurso=") == -1) || (linea.indexOf("nomCurso=") == -1) || (linea.indexOf("descrip=") == -1) || (linea.indexOf("codCurso=") == -1) || (linea.indexOf("seccion=") == -1) || (linea.indexOf("enCarreras=") == -1) || (linea.indexOf("idProfeAsig=") == -1) || (linea.indexOf("listSalas=") == -1) || (linea.indexOf("horario=") == -1))
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
			comienzoDato = linea.indexOf("enCarreras=") + "enCarreras=".length();
			enCarreras = linea.substring(comienzoDato+1, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

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
                        if (enCarreras.length() != 0)
                        {       for (i = 0; enCarreras.indexOf("|") != -1;i++)
                                {       System.out.println(enCarreras.substring(0, enCarreras.indexOf("|")));
                                        codigoCarrera = Integer.valueOf(enCarreras.substring(0, enCarreras.indexOf("|")));
                                        posicionBarra = enCarreras.indexOf("|");
                                        enCarreras = enCarreras.substring(posicionBarra+1);
                                        cursoLeido.modCodigosCarrera(codigoCarrera, 1);
                                        System.out.println("En carrera: " + codigoCarrera);
                                }
                                //Agrego el ultimo que no fue agregado en el bucle:
                                cursoLeido.modCodigosCarrera(Integer.valueOf(enCarreras), 1);
                                System.out.println("En carrera: " +enCarreras);
                        }
                        /* Seteo los demas atributos del curso leido */
                        //carrera.
                        //listaSalas.
                        //horario.
			return cursoLeido;
		}

		else
			return null;
	}
	/**
	* Esté metodo recibe un objeto Curso y crea un string de como debe ser escrito en el archivo de cursos
	*/
	private String cursoToString(Curso cursoAEscribir)
	{	String cursoString;
                int idCurso = cursoAEscribir.getIdCurso();
		String nomCurso = cursoAEscribir.getNombreCurso(); //Nombre del curso
		String descrip = cursoAEscribir.getDescripcion(); //Descripción del curso
		int codCurso = cursoAEscribir.getCodigoCurso(); //Código del ramo
		String seccion = cursoAEscribir.getSeccion(); //código de sección
		String enCarreras = cursoAEscribir.getEnCarreras_Codigo(); //Carreras en que se dicta.
		int idProfeAsig = cursoAEscribir.getIdProfeAsig(); //rut del profesor
		String listSalas = cursoAEscribir.getSalas(); //Salas donde se dicta el ramo.
		String horario = cursoAEscribir.getHorario(); //Horas en que se dicta e la semana.
		cursoString = "<Curso nomCurso=\""+nomCurso+"\" idCurso=\""+idCurso+"\" descrip=\""+descrip+"\" codCurso=\""+codCurso+"\" seccion=\""+seccion+"\" enCarreras=\""+enCarreras+"\" idProfeAsig=\""+idProfeAsig+"\" listSalas=\""+listSalas+"\" horario=\""+horario+"\" >";
		return cursoString;
	}

	private String carreraToString(Carrera carreraAEscribir)
	{	System.out.println("Se va a pasar una carrera a String...");
		String nomCarrera = carreraAEscribir.getNombreCarrera();
		String descrip = carreraAEscribir.getDescripcion();
		int codCarrera = carreraAEscribir.getCodigoCarrera();
                String idSemestresStr = carreraAEscribir.getIdSemestres();
		return "<Carrera nomCarrera=\""+nomCarrera+"\" descrip=\""+descrip+"\" codCarrera=\""+codCarrera+"\" idSemestres=\""+idSemestresStr+"\" >";
	}
}


