package com.justrap.exception;

public class InvalidExtensionException extends Exception {

	private static final long serialVersionUID = 1940730750898623488L;
	
	public InvalidExtensionException() {
		super("Format du fichier invalide");
	}

}
