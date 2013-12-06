package strviola.injector.http;


public class ConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionException() {
		super("Connection failed.");
	}

	public ConnectionException(String message) {
		super(message);
	}
}
