package strviola.injector.ui;

import java.awt.Component;

import strviola.injector.http.RequestSender;

public class ReceivePointCard extends AbstractFormCard {

	private static final long serialVersionUID = -7383727444819028335L;

	public ReceivePointCard(Component parent) {
		super(parent);
	}

	@Override
	protected String getMessage() {
		return RequestSender.sendGetMoney();
	}
}
