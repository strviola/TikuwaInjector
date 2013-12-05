package strviola.injector.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class LoginCard extends AbstractFormCard {

	private static final long serialVersionUID = 1L;

	public LoginCard() {
		super(new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO /auth/info/にリクエスト送信
				
			}
		}, "gmail", "password");
	}
}
