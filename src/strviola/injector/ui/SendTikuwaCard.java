package strviola.injector.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import strviola.injector.http.RequestSender;

public class SendTikuwaCard extends AbstractFormCard {

	private static final long serialVersionUID = -1394480198345289726L;

	public SendTikuwaCard(final Component parent) {
		super(parent, new AbstractAction() {

			private static final long serialVersionUID = -3232219846574293899L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// send tikuwa-upload request
				String tikuwaID = inputs[0].getText();
				String number = inputs[1].getText();
				String result = RequestSender.sendTikuwa(tikuwaID, number);
				showMessage(result);
			}

		}, "Tikuwa ID", "Number");
	}
}
