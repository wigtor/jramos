/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jramos.excepciones;

/**
 *
 * @author victor
 */
public class HoraNoDisponibleException extends Exception{
        public static final int TOPE_NIVEL = 1;
        public static final int TOPE_NIVEL_ANT = 2;
        public static final int TOPE_NIVEL_SIG = 3;
        public static final int TOPE_HORAS_DISP_PROFE = 4;
        public static final int TOPE_HORAS_OCUP_PROFE = 5;

        private int codigoError;

        public HoraNoDisponibleException(int codigo, String mensaje)
        {       super(mensaje);
                this.codigoError = codigo;
        }
        public int getCodigoError()
        {       return this.codigoError;
        }
}
