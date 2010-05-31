/**
******************************************************
* @file HourNotInicializatedException.java
* @author victor flores sanchez
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la excepción lanzada cuando la hora no ha sido inicializada en un objeto de la clase Hora
*****************************************************/

package jramos.tiposDatos;


/** Defino la excepción lanzada por la clase hora cuando el atributo horaInt vale 0, es decir, cuando aún no ha sido seteado su valor
*/
public class HourNotInicializatedException extends Exception
{	HourNotInicializatedException()
	{	super("La hora en el objeto no ha sido inicializada");
	}
}
