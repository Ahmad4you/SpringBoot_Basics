package home.ahmad.fehlerbehandlungsstruktur;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6443255749670586997L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}

