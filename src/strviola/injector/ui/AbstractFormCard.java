package strviola.injector.ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractFormCard extends JPanel {

	private static final long serialVersionUID = 1L;

	protected static JTextField[] inputs;
	protected static Component parentComponent;

	public AbstractFormCard(Component parent, AbstractAction onButtonClick,
			String... labels) {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		inputs = new JTextField[labels.length];
		AbstractFormCard.parentComponent = parent;

		// panel for labels and forms
		for (int i = 0; i < labels.length; i++) {
			// set base panel like "label: [    ]"
			JPanel labelForm = new JPanel();
			labelForm.setLayout(new BoxLayout(labelForm, BoxLayout.X_AXIS));

			// add labels
			JLabel formTitle = new JLabel(labels[i] + ": ");
			labelForm.add(formTitle);
			inputs[i] = new JTextField();
			inputs[i].setPreferredSize(new Dimension(50, 200));
			labelForm.add(inputs[i]);

			// add to parent component
			add(labelForm);
		}

		// button to send http request to tikuwa server
		JButton sendButton = new JButton("Submit");
		sendButton.setAction(onButtonClick);
		add(sendButton);
	}
}
