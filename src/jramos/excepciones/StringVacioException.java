/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jramos.excepciones;

/**
 *
 * @author victor
 */
public class StringVacioException extends Exception{
        private int codigoString;
        public StringVacioException()
	{	super("El string introducido está vacio");
                this.codigoString = 0;
	}
        public StringVacioException(int codigo)
        {       super("El string introducido está vacio, codigo de string" + codigo);
                this.codigoString = codigo;
        }
        public int getCodigoString()
        {       return this.codigoString;
        }
}
