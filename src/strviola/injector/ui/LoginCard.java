package strviola.injector.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import strviola.injector.http.RequestSender;

public class LoginCard extends AbstractFormCard {

	private static final long serialVersionUID = 8954038241662950530L;

	public LoginCard(Component parent) {
		super(parent, new AbstractAction() {

			private static final long serialVersionUID = -6241995510617637291L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// send login request to /auth/info/
				String gmail = inputs[0].getText();
				String password = inputs[1].getText();
				String loginResult = RequestSender.sendLogin(gmail, password);
				showMessage(loginResult);
			}

		}, "gmail", "password");
	}
}
