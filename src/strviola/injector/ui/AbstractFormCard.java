package strviola.injector.ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractFormCard extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JTextField[] inputs;
	protected JLabel[] labels;
	protected JButton submit;
	protected static Component parentComponent;

	public AbstractFormCard(Component parent, String... labelString) {
		super();
		int len = labelString.length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		inputs = new JTextField[len];
		labels = new JLabel[len];
		parentComponent = parent;

		// panel for labels and forms
		JPanel labelForm = new JPanel();
		GroupLayout gl = new GroupLayout(labelForm);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

		// create widgets
		for (int i = 0; i < len; i++) {
			labels[i] = new JLabel(labelString[i] + ": ");
			inputs[i] = new JTextField();
			inputs[i].setMaximumSize(new Dimension(MainFrame.width, 20));
		}

		// horizontal / vertical group
		GroupLayout.SequentialGroup hg = gl.createSequentialGroup();
		GroupLayout.SequentialGroup vg = gl.createSequentialGroup();
		// vertical groups
		GroupLayout.ParallelGroup labelGroup = gl.createParallelGroup();
		GroupLayout.ParallelGroup formGroup = gl.createParallelGroup();

		for (int i = 0; i < len; i++) {
			// horizontal group
			vg.addGroup(gl.createParallelGroup().addComponent(labels[i])
					.addComponent(inputs[i]));
			// vertical group
			labelGroup.addComponent(labels[i]);
			formGroup.addComponent(inputs[i]);
		}
		hg.addGroup(labelGroup).addGroup(formGroup);

		gl.setHorizontalGroup(hg);
		gl.setVerticalGroup(vg);
		labelForm.setLayout(gl);
		add(labelForm);

		// button to send http request to tikuwa server
		submit = new JButton();
		submit.setText("Submit");
		submit.setVisible(true);
		add(new JPanel().add(submit));
	}

	protected void setAction(AbstractAction action) {
		submit.setAction(action);
	}

	protected static void showMessage(String message) {
		JOptionPane.showMessageDialog(parentComponent, message);
	}
}
