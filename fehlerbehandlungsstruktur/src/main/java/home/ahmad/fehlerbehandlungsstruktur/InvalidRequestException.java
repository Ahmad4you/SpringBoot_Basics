package home.ahmad.fehlerbehandlungsstruktur;

public class InvalidRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1135924227935694769L;

	public InvalidRequestException(String message) {
        super(message);
    }
}

