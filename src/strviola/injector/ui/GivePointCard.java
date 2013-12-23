package strviola.injector.ui;

import java.awt.Component;

import strviola.injector.http.RequestSender;

public class GivePointCard extends AbstractFormCard {

	private static final long serialVersionUID = -7986822265425089877L;

	public GivePointCard(Component parent) {
		super(parent, "User ID", "Point");
	}

	@Override
	protected String getMessage() {
		String uid = inputs[0].getText();
		String points = inputs[1].getText();
		return RequestSender.sendMoney(uid, points);
	}
}
