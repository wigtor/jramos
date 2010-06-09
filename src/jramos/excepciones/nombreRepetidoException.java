/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jramos.excepciones;

/**
 *
 * @author victor
 */
public class nombreRepetidoException extends Exception{
        public nombreRepetidoException()
	{	super("El nombre introducido ya existe");
	}
}
