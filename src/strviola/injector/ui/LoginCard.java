package strviola.injector.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import strviola.injector.http.RequestSender;

public class LoginCard extends AbstractFormCard {

	private static final long serialVersionUID = 1L;

	public LoginCard(Component parent) {
		super(parent, new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO /auth/info/にリクエスト送信
				String gmail = inputs[0].getText();
				String password = inputs[1].getText();
				String loginResult = RequestSender.sendLogin(gmail, password);
				JOptionPane.showMessageDialog(parentComponent, loginResult);
			}
		}, "gmail", "password");
	}
}
