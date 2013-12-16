package strviola.injector.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class HeaderPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected JComboBox selectMethod;

	public HeaderPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // linear layout

		// label display
		add(new JLabel("Method: "));

		// combo box display
		this.selectMethod = new JComboBox();
		selectMethod.addItem("Login");
		selectMethod.addItem("Tikuwa Upload");
		selectMethod.addItem("Receive Point");
		selectMethod.addItem("Give Point");
		selectMethod.addActionListener(this);
		selectMethod.setMaximumSize(new Dimension(150, 25));
		add(selectMethod);
	}
}
