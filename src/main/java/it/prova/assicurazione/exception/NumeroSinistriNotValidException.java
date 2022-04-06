package it.prova.assicurazione.exception;

public class NumeroSinistriNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NumeroSinistriNotValidException(String message) {
		super(message);
	}
}
