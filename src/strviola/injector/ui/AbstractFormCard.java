package strviola.injector.ui;

import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractFormCard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected JTextField[] inputs;
	
	public AbstractFormCard(AbstractAction onButtonClick, String... labels) {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// panel for labels and forms
		int len = labels.length;
		JPanel forms = new JPanel();
		inputs = new JTextField[len];
		forms.setLayout(new GridLayout(len, 2));

		for (int i = 0; i < len; i++) {
			forms.add(new JLabel(labels[i]));
			inputs[i] = new JTextField();
			inputs[i].setSize(200, 20);
			forms.add(inputs[i]);
		}
		
		add(forms);
		
		// button to send http request to tikuwa server
		JButton sendButton = new JButton("Submit");
		sendButton.setAction(onButtonClick);
		add(sendButton);
	}
}
