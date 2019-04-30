package eccezioni;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LoginException() {
		super("Errore di login!");
	}
	
	public LoginException(String msg) {
		super(msg);
	}
	
}
