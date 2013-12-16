package strviola.injector.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import strviola.injector.http.RequestSender;

public class ReceivePointCard extends AbstractFormCard {

	private static final long serialVersionUID = -7383727444819028335L;

	public ReceivePointCard(Component parent) {
		super(parent, new AbstractAction() {

			private static final long serialVersionUID = 9167211141263789196L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = RequestSender.sendGetMoney();
				showMessage(result);
			}
		});
	}

}
