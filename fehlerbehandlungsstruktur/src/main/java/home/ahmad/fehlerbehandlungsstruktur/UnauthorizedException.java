package home.ahmad.fehlerbehandlungsstruktur;

public class UnauthorizedException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5165621980882317383L;

	public UnauthorizedException(String message) {
        super(message);
    }
}
