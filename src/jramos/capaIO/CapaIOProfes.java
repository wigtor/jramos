/**
******************************************************
* @file CapaIOProfes.java
* @author victor flores sanchez
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase CapaIOCursos que se encarga de leer/escribir datos en el archivo de cursos.
*****************************************************/


package jramos.capaIO;

//import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Profesor;
import jramos.tiposDatos.Curso;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CapaIOProfes
{	/* ATRIBUTOS */
	private String nombreArchivoProfes;
	private static final int capacidadInicialVector = 100;
	private static final int capacidadInicialString = 200;

	/* CONSTRUCTORES */
	public CapaIOProfes() throws IOException
	{	this.nombreArchivoProfes = (System.getProperty("user.home") + System.getProperty("file.separator") + "archProfes.txt");
		File archPrueba = new File(this.nombreArchivoProfes);
		/** Si no existe el archivo de cursos, este es creado.*/
		try
		{	if (archPrueba.createNewFile())
				System.out.println("Se ha creado el archivo de profesores vacio\nEn: " + this.nombreArchivoProfes);
		}
		catch (IOException IOE)
		{	System.out.println("No se puede crear un archivo de profesores en: \n" + this.nombreArchivoProfes + "\nError grave!!");
			throw IOE;
		}
	}
	
	/* METODOS */
	/** Metodo para leer todos los profesores.
	* 
	*/
	public ArrayList<Profesor> leeProfes() throws FileNotFoundException, IOException
	{	ArrayList<Profesor> listaProfes= new ArrayList(CapaIOProfes.capacidadInicialVector);
		BufferedReader lector;
		StringBuilder lineaDatos = new StringBuilder(CapaIOProfes.capacidadInicialString);
		int caracterLeido = 0;
		long i, j;
		
		/** Intento abrir el archivo de cursos */
		try
		{	lector = new BufferedReader(new FileReader(this.nombreArchivoProfes));
		}
		catch (FileNotFoundException FNFE)
		{	throw FNFE; //<Devuelvo la excepción haca quien llame el método leeCursos.
		}
		
		/** Leo el archivo de profesores hasta el final */
		for (i = 0; caracterLeido != -1; i++)
		{	caracterLeido = lector.read();
			/**Comienza a leer datos desde que encuentra un caracter '<' */
			if (caracterLeido == '<')
			{	for (j = 0; ((caracterLeido != -1) || (caracterLeido != '>')); j++) //ver que el -1 que se almacena si llego al final del archivo. en teoria no debe ocurrir se antes compruebo sintaxis.
				{	lineaDatos.append(caracterLeido);
					caracterLeido = lector.read();
				}
				lineaDatos.append(caracterLeido);//agrego el caracter '>' que no fue agregado en el bucle
				i += j; //sumo los caracteres que ya se han leido a i, aun no se si esto pueda ser necesario a futuro.
			}
			/** Como se ha encontrado una linea con una especificacion de un objeto, ahora proceso esa linea y agrego el objeto que retorna el metodo analizaLinea */
			Profesor ProfesorEncontrado = this.stringToProfesor(lineaDatos.toString());
			if (ProfesorEncontrado != null)
				listaProfes.add(ProfesorEncontrado);
			else
				System.out.println("Aviso: Lo que se ha encontrado en la linea analizada no es un curso");
		}
		/** Cierro el archivo*/
		try
		{	lector.close();
		}
		catch (IOException IOE)
		{	System.out.println("Error: No se puede cerrar el archivo de cursos\nError grave!!");
			throw IOE;
		}
		/**  Retorno la lista con los profesores leidos*/
		return listaProfes;
	}

	
	/** 
	* Método que guarda todos los profesores en el archivo de profesores.
	*/
	public void escribeProfes(ArrayList<Curso> listaProfes) throws FileNotFoundException, SecurityException, IOException
	{	PrintWriter escritor;
		ArrayList<Carrera> listaCarreras = new ArrayList(CapaIOProfes.capacidadInicialVector);

		int i;
		/** Intenta abrir el archivo de profesores para escribir en él. */
		try
		{	escritor = new PrintWriter(this.nombreArchivoProfes);
		}
		catch (FileNotFoundException FNFE)
		{	System.out.println("ERROR: El archivo no existe"); //no deberia llegar a esta excepcion con el constructor que crea el archivo.
			throw FNFE;
			
		}
		catch (SecurityException SE)
		{	System.out.println("ERROR: No tiene permisos de escritura sobre el archivo de cursos.");
			throw SE;
		}

		//Escribo los profesores del ArrayList<Profesor> en el archivo de profesores.
		for(i = 0; i<listaProfes.size();i++)
		{	escritor.println(this.profesorToString(listaProfes.get(i)));//Escribo en el archivo de profes.
		}
		
		/** Cierro el archivo*/
		escritor.close();
	}

	/** 
	* Este método recibe un String que contiene especificado un objeto del tipo Curso, analiza este String y devuelve un objeto Curso.
	*/
	private Profesor stringToProfesor(String linea)
	{	Profesor profesorLeido;
                String idProfe; //id Interna del profesor
		String nombProfe; //Nombre del cur
                String rutProfe; //Rut del profesor
                String idCursosAsig; //Id de los cursos que el profesor tiene asignados
                String codCursosDisp; //Codigo de los cursos que puede dictar el profesor
		String horasDisp; //Horas que el profesor tiene disponibles para hacer clases
		String horasAsig; //Horas que al profesor se le han asignado.
		int comienzoDato;
		
		/* Si es un curso lo que está espeficado en la linea, creo un objeto "Curso" */
		if ((linea.indexOf("<Profesor") != -1))
		{	/* Busco errores de sintaxis en la linea analizada*/
			if ((linea.indexOf("nomProfe=") == -1) || (linea.indexOf("idProfe=") == -1) || (linea.indexOf("rutProfe=") == -1) || (linea.indexOf("idCursosAsig=") == -1) || (linea.indexOf("horasDisp=") == -1) || (linea.indexOf("horasAsig=") == -1))
			{	System.out.println("ERROR: La linea leida desde el archivo de cursos es incorrecta");
			}

			/* Busco el nombre del curso en la linea*/
			comienzoDato = linea.indexOf("idProfesor=") + "idProfesor=".length();
			idProfe = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco la descripción del curso en la linea*/
			comienzoDato = linea.indexOf("nombProfe=") + "nombProfe=".length();
			nombProfe = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco el código del curso en la linea*/
			comienzoDato = linea.indexOf("rutProfe=") + "rutProfe=".length();
			rutProfe = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco las secciones del curso en la linea*/
			comienzoDato = linea.indexOf("idCursosAsig=") + "idCursosAsig=".length();
			idCursosAsig = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

                        comienzoDato = linea.indexOf("codCursosDisp=") + "codCursosDisp=".length();
			codCursosDisp = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1));

			/* Busco las carreras del curso en la linea*/
			comienzoDato = linea.indexOf("horasDisp=") + "horasDisp=".length();
			horasDisp = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Busco el profesor del curso en la linea*/
			comienzoDato = linea.indexOf("horasAsig=") + "horasAsig=".length();
			horasAsig = linea.substring(comienzoDato, linea.indexOf("\"", comienzoDato+1)); //confirmar que debo sumar 1 !!!

			/* Construyo el objeto cursoLeido con los datos recopilados */
			profesorLeido = new Profesor(nombProfe, null);
			/* Seteo los demas atributos del curso leido */
			return profesorLeido;
		}

		else
		{	return null;
		}
	}
	/**
	* Esté metodo recibe un objeto Curso y crea un string de como debe ser escrito en el archivo de cursos
	*/
	private String profesorToString(Profesor profesorAEscribir)
	{	String cursoString;
		String nomCurso = cursoAEscribir.getNombreCurso(); //Nombre del curso
		String descrip = cursoAEscribir.getDescripcion(); //Descripción del curso
		String codCurso = Integer.toString(cursoAEscribir.getCodigoCurso()); //Código del ramo
		String seccion = cursoAEscribir.getSeccion(); //código de sección
		String enCarreras = cursoAEscribir.getEnCarreras(); //Carreras en que se dicta.
		String profesor = cursoAEscribir.getNombreProfesor(); //nombre del profesor
		String listSalas = cursoAEscribir.getSalas(); //Salas donde se dicta el ramo.
		String horario = cursoAEscribir.getHorario(); //Horas en que se dicta e la semana.
		cursoString = "<Curso nomCurso=\""+nomCurso+"\" descrip=\""+descrip+"\" codCurso=\""+codCurso+"\" seccion=\""+seccion+"\" enCarreras=\""+enCarreras+"\" profesor=\""+profesor+"\" listSalas=\""+listSalas+"\" horario=\""+horario+"\" >";
		return cursoString;
	}

	private String carreraToString(Carrera carreraAEscribir)
	{	System.out.println("Se va a pasar una carrera a String...");
		String nomCarrera = "nombre de la carrera"; //cambiar por un getter
		String descrip = "Descripcion de la carrera"; //Cambiar por un getter
		int codCarrera = 1863; //cambiar por un getter
		return "<Carrera nomCarrera=\""+nomCarrera+"\" descrip=\""+descrip+"\" codCarrera=\""+codCarrera+"\"";
	}
}

