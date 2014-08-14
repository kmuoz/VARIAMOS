/**
 * 
 */
package com.variamos.core.exceptions;


/**
 * Represents a controller logical exception
 * 
 * @author Luisa Fernanda Rinc�n P�rez <lufrinconpe@unal.edu.co>
 *
 */
public class TransformerException extends FunctionalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TransformerException() {
		
	}

	/**
	 * @param arg0
	 */
	public TransformerException(String arg0) {
		super(arg0);
		 
	}

	/**
	 * @param arg0
	 */
	public TransformerException(Throwable arg0) {
		super(arg0);
		 
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TransformerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		 
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public TransformerException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		 
	}

}
