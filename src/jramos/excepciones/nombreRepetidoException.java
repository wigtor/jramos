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
        private int codigoError;
        public nombreRepetidoException()
	{	super("El nombre introducido ya existe");
                this.codigoError = 0;
	}
        public nombreRepetidoException(int codigo)
	{	super();
                this.codigoError = codigo;
	}
        public int getCodigoError()
        {       return this.codigoError;
        }
}
