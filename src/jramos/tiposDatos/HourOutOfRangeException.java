/**
******************************************************
* @file HourOutOfRangeException.java
* @author victor flores sanchez
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la excepción lanzada cuando la hora está fuera de rango.
*****************************************************/

package jramos.tiposDatos;


/** Defino la excepción lanzada por la clase hora cuando el atributo horaInt esta fuera de rango
*/
public class HourOutOfRangeException extends Exception
{	HourOutOfRangeException()
	{	super("La hora ingresada está fuera de rango, debe ser entre 1 y 54");
	}
}