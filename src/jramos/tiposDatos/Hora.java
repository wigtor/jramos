/**
******************************************************
* @file Hora.java
* @author victor flores sanchez
* @date mayo 2010
* @version 0.1
* @brief En este archivo se especifica la clase del tipo Hora.
*****************************************************/

package jramos.tiposDatos;

/** La hora puede ser mostrada según distintos formatos
* El primer formato es como un entero que comienza a contar desde la primera hora del dia lunes, hasta la ultima hora del dia sábado
* Cada dia tiene 9 horas y a la semana hay 6 dias de clases. 54 horas maximas a la semana.
* El 1° formato es un byte, según BNF:
*	<hora> ::= 1|2|3|4|.... |52|53|54
*
* El 2° formato utilizado es un String, según BNF:
*	<día> ::= <L>|<M>|<W>|<J>|<V>|<S>
*	<bloque> ::= <1>|<2>|<3>|<4>|<5>|<6>|<7>|<8>|<9>
*	<hora> ::= <día><bloque>
* El 3° formato utilizado es un String, según BNF:
*	<día> ::= <Lunes>|<Martes>|<Miercoles>|<Jueves>|<Viernes>|<Sabado>
*	<bloque> ::= <1>|<2>|<3>|<4>|<5>|<6>|<7>|<8>|<9>
*	<hora> ::= <dia>, <bloque>° bloque
*
* El 4° formato es un String, según BNF:
*	<dia> ::= <Lunes>|<Martes>|<Miercoles>|<Jueves>|<Viernes>|<Sabado>
*	<bloque> ::= >
*	<hora> ::= <dia>, <bloque>
*/
public final class Hora
{	/* ATRIBUTOS DE LA CLASE */
	/** horaInt representa la hora como un número, es el único atributo de esta clase*/
	private int horaInt;
	/* FIN DE LOS ATRIBUTOS */

	/* CONSTRUCTORES */
	/**
	* Constructor por defecto, hace que la hora valga la primera hora de la semana
	*/
	public Hora()
	{	horaInt = 0;
	}
	/**
	* Constructor que se inicializa el atributo horaInt con un número de tipo byte.
	* @param horaSemanal valor numérico de la hora semanal con que se desea instanciar el objeto.
	* @throws HourOutOfRangeException Se lanza esta excepción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public Hora(byte horaSemanal) throws HourOutOfRangeException
	{	if ((horaSemanal > 0) && (horaSemanal <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = horaSemanal;
		else
			throw new HourOutOfRangeException();
	}
	/**
	* Constructor que se inicializa el atributo horaInt con un número long.
	* @param horaSemanal valor numérico de la hora semanal con que se desea instanciar el objeto.
	* @throws HourOutOfRangeException Se lanza esta excepción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public Hora(long horaSemanal) throws HourOutOfRangeException /** Lo mismo que el constructor anterior, pero acepta long como argumento*/
	{	if ((horaSemanal > 0) && (horaSemanal <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = (int)horaSemanal;
		else
			throw new HourOutOfRangeException();
	}
	/**
	* Constructor que se inicializa el atributo horaInt con un número int.
	* @param horaSemanal valor numérico de la hora semanal con que se desea instanciar el objeto.
	* @throws HourOutOfRangeException Se lanza esta excepción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public Hora(int horaSemanal) throws HourOutOfRangeException /** Lo mismo que el constructor anterior, pero acepta int como argumento*/
	{	if ((horaSemanal > 0) && (horaSemanal <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = horaSemanal;
		else
			throw new HourOutOfRangeException();
}
	/**
	* Constructor que instancia una hora usando el 2° formato de hora.
	* @param dia_hora Es un string con la hora del horario, dia_hora debe seguir la sintaxis BNF del 2° formato de hora. 
	* @throws HourOutOfRangeException Lanza esta excepción cuando el String dia_hora no es válido según la sintaxis del 2° formato de hora.
	*/
	public Hora(String dia_Hora) throws HourOutOfRangeException
	{	String diaHora = dia_Hora.trim().toLowerCase(); //Le quito los espacios finales y iniciales si es que los tiene
		if (diaHora.length() != 2) //Me aseguro que debe tener solo 2 caracteres diaHora para que cumpla comn la sintaxis válida.
			throw new HourOutOfRangeException();
		char dia = diaHora.charAt(0);
		char hora = diaHora.charAt(1);
		if (hora == '0') //El número 0 en la sintaxis del 2° formato no es válido
			throw new HourOutOfRangeException();
		int diaInt, horaDelDia;
		switch (dia)
		{	case 'l':
				diaInt = 0;
				break;
			case 'm':
				diaInt = 1;
				break;
			case 'w':
				diaInt = 2;
				break;
			case 'j':
				diaInt = 3;
				break;
			case 'v':
				diaInt = 4;
				break;
			case 's':
				diaInt = 5;
				break;
			default:
				throw new HourOutOfRangeException();
		}
		try
		{	horaDelDia = java.lang.Byte.valueOf(java.lang.String.valueOf(hora));
		}
		catch (NumberFormatException NFE)
		{	throw new HourOutOfRangeException();
		}
	this.horaInt = 9*diaInt + horaDelDia; ///aquí asigno el valor de la hora al atributo del objeto.
	}
	/* FIN DE LOS CONSTRUCTORES*/

	/* MÉTODOS DE LA CLASE */
	/** Método que asigna una hora al objeto usando un byte como argumento
	* @param horaSemanal valor numérico de la hora semanal con que se desea dejar el objeto.
	* @throws HourOutOfRangeException Se lanza esta opción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public void setHora(byte horaSemanal) throws HourOutOfRangeException
	{	if ((horaInt > 0) && (horaInt <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = horaSemanal;
		else
			throw new HourOutOfRangeException();
	}
	/** Método que asigna una hora al objeto usando un byte como argumento
	* @param horaSemanal valor numérico de la hora semanal con que se desea dejar el objeto.
	* @throws HourOutOfRangeException Se lanza esta opción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public void setHora(long horaSemanal) throws HourOutOfRangeException /** Lo mismo que el método anterior, pero acepta long como argumento*/
	{	if ((horaInt > 0) && (horaInt <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = (int)horaSemanal;
		else
			throw new HourOutOfRangeException();
	}
	/** Método que asigna una hora al objeto usando un byte como argumento
	* @param horaSemanal valor numérico de la hora semanal con que se desea dejar el objeto.
	* @throws HourOutOfRangeException Se lanza esta opción cuando el valor numérico del parametro horaSemanal no se encuentra en el rango válido de 1 a 54.
	*/
	public void setHora(int horaSemanal) throws HourOutOfRangeException /** Lo mismo que el método anterior, pero acepta int como argumento*/
	{	if ((horaInt > 0) && (horaInt <= 54)) /** Aseguro que es válido el rango de la hora*/
			this.horaInt = horaSemanal;
		else
			throw new HourOutOfRangeException();
	}
	/** Método que asigna una hora al objeto según el 2° formato
	* @param dia_hora Es un string con la hora del horario, dia_hora debe seguir la sintaxis BNF del 2° formato de hora. 
	* @throws HourOutOfRangeException Lanza esta excepción cuando el String dia_hora no es válido según la sintaxis del 2° formato de hora.
	*/
	public void setHora(String dia_Hora) throws HourOutOfRangeException
	{	String diaHora = dia_Hora.trim().toLowerCase(); ///Le quito los espacios finales y iniciales si es que los tiene
		if (diaHora.length() != 2)
			throw new HourOutOfRangeException();
		char dia = diaHora.charAt(0);
		char hora = diaHora.charAt(1);
		if (hora == '0') //El número 0 en la sintaxis del 2° formato no es válido
			throw new HourOutOfRangeException();
		int diaInt, horaDelDia;
		switch (dia)
		{	case 'l':
				diaInt = 0;
				break;
			case 'm':
				diaInt = 1;
				break;
			case 'w':
				diaInt = 2;
				break;
			case 'j':
				diaInt = 3;
				break;
			case 'v':
				diaInt = 4;
				break;
			case 's':
				diaInt = 5;
				break;
			default:
				throw new HourOutOfRangeException();
		}
		try
		{	horaDelDia = java.lang.Byte.valueOf(java.lang.String.valueOf(hora));
		}
		catch (NumberFormatException NFE)
		{	throw new HourOutOfRangeException();
		}
	this.horaInt = 9*diaInt + horaDelDia; ///aquí asigno el valor de la hora al atributo del objeto.
	return;
	}
	/** Método que obtiene la hora según el 1° formato
	* @return Retorna el valor de la hora según el 1° formato. Es la hora de la semana.
	* @throws HourNotInicializatedException Se lanza esta excepción cuando el objeto ha sido instanciado con el constructor por defecto sin parametros. Se debe setear el valor de horaInt con algún método.
	*/
	public int getHora() throws HourNotInicializatedException
	{	if (this.horaInt == 0)
			throw new HourNotInicializatedException();
		return this.horaInt;
	}
	/** Método que obtiene la hora según el 2° formato
	* @return Retorna el valor de la hora según el 2° formato.
	* @throws HourNotInicializatedException Se lanza esta excepción cuando el objeto ha sido instanciado con el constructor por defecto sin parametros. Se debe setear el valor de horaInt con algún método.
	*/
	public String getHoraStr() throws HourNotInicializatedException
	{	if (this.horaInt == 0)
			throw new HourNotInicializatedException();
		int diaInt, horaDelDia;
		String dia = new String();
		diaInt = (this.horaInt-1) / 9;
		horaDelDia = this.horaInt % 9;
		if (horaDelDia == 0)
			horaDelDia = 9;
		switch (diaInt)
		{	case 0:
				dia = "L";
				break;
			case 1:
				dia = "M";
				break;
			case 2:
				dia = "W";
				break;
			case 3:
				dia = "J";
				break;
			case 4:
				dia = "V";
				break;
			case 5:
				dia = "S";
				break;
		}
		return dia + (horaDelDia); ///Retorno un String de la concatenacion de dia y horaDelDia.
	}
	/** Método que obtiene la hora según el 3° formato
	* @return Retorna el valor de la hora según el 3° formato.
	* @throws HourNotInicializatedException Se lanza esta excepción cuando el objeto ha sido instanciado con el constructor por defecto sin parametros. Se debe setear el valor de horaInt con algún método.
	*/
	public String getHoraStr2() throws HourNotInicializatedException
	{	if (this.horaInt == 0)
			throw new HourNotInicializatedException();
		String diaStr = null;
		int bloqueInt, diaInt;

		diaInt = (this.horaInt-1) / 9;
		bloqueInt = this.horaInt % 9;
		if (bloqueInt == 0)
			bloqueInt = 9;
		switch (diaInt)
		{	case 0:
				diaStr = "Lunes";
				break;
			case 1:
				diaStr = "Martes";
				break;
			case 2:
				diaStr = "Miercoles";
				break;
			case 3:
				diaStr = "Jueves";
				break;
			case 4:
				diaStr = "Viernes";
				break;
			case 5:
				diaStr = "Sábado";
				break;
		}
		return diaStr + ", " + bloqueInt + "° bloque";
	}
	/** Método que obtiene la hora según el 4° formato
	* @return Retorna el valor de la hora según el 4° formato.
	* @throws HourNotInicializatedException Se lanza esta excepción cuando el objeto ha sido instanciado con el constructor por defecto sin parametros. Se debe setear el valor de horaInt con algún método.
	*/
	public String getHoraStr3() throws HourNotInicializatedException
	{	if (this.horaInt == 0)
			throw new HourNotInicializatedException();
		String diaStr = null, horaStr = null;
		int bloqueInt, diaInt;

		diaInt = (this.horaInt-1) / 9;
		bloqueInt = this.horaInt % 9;
		if (bloqueInt == 0)
			bloqueInt = 9;
		switch (diaInt)
		{	case 0:
				diaStr = "Lunes";
				break;
			case 1:
				diaStr = "Martes";
				break;
			case 2:
				diaStr = "Miercoles";
				break;
			case 3:
				diaStr = "Jueves";
				break;
			case 4:
				diaStr = "Viernes";
				break;
			case 5:
				diaStr = "Sábado";
				break;
		}
		switch (bloqueInt)
		{	case 1:
				horaStr = "De 8:00 a 9:30";
				break;
			case 2:
				horaStr = "De 9:40 a 11:10";
				break;
			case 3:
				horaStr = "De 11:20 a 12:50";
				break;
			case 4:
				horaStr = "De 13:50 a 15:20";
				break;
			case 5:
				horaStr = "De 15:30 a 17:00";
				break;
			case 6:
				horaStr = "De 17:10 a 18:40";
				break;
			case 7:
				horaStr = "De 19:00 a 20:10";
				break;
			case 8:
				horaStr = "De 20:20 a 22:00";
				break;
			case 9:
				horaStr = "De 22:00 a 23:00";
				break;
		}
		return diaStr + ", " + horaStr;
	}
	/* FIN DE LOS MÉTODOS DE LA CLASE */
}
