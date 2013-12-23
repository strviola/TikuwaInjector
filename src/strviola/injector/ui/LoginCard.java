package strviola.injector.ui;

import java.awt.Component;

import strviola.injector.http.RequestSender;

public class LoginCard extends AbstractFormCard {

	private static final long serialVersionUID = 8954038241662950530L;

	public LoginCard(Component parent) {
		super(parent, "gmail", "password");
	}

	@Override
	protected String getMessage() {
		String gmail = inputs[0].getText();
		String password = inputs[1].getText();
		return RequestSender.sendLogin(gmail, password);
	}
}
