package domain.api.exceptions;

/**
 * Excecao levantada quando se tenta criar algo com um nome que ja' existe. 
 * 
 * Pode ser subclassed especificando o que ja' existe.
 */
public class NonUniqueException extends Exception {

	private static final long serialVersionUID = 1L;

	public NonUniqueException(String str) {
		super(str);
	}

}
