package strviola.injector.ui;

import java.awt.Component;

import strviola.injector.http.RequestSender;

public class SendTikuwaCard extends AbstractFormCard {

	private static final long serialVersionUID = -1394480198345289726L;

	public SendTikuwaCard(final Component parent) {
		super(parent, "Tikuwa ID", "Number");
	}

	@Override
	protected String getMessage() {
		String tikuwaID = inputs[0].getText();
		String number = inputs[1].getText();
		return RequestSender.sendTikuwa(tikuwaID, number);
	}
}
