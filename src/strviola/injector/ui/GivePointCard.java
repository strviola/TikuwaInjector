package strviola.injector.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import strviola.injector.http.RequestSender;

public class GivePointCard extends AbstractFormCard {

	private static final long serialVersionUID = -7986822265425089877L;

	public GivePointCard(Component parent) {
		super(parent, "User ID", "Point");
		setAction(new AbstractAction() {

			private static final long serialVersionUID = -4285097400928371703L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String uid = inputs[0].getText();
				String points = inputs[1].getText();
				String result = RequestSender.sendMoney(uid, points);
				showMessage(result);
			}
		});
	}
}
