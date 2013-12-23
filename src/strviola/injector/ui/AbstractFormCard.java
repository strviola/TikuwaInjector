package strviola.injector.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractFormCard extends JPanel implements Action {

	private static final long serialVersionUID = 1L;
	private final String filename = getClass().getSimpleName() + ".pushed";

	protected JTextField[] inputs;
	protected JLabel[] labels;
	protected JButton submit;
	protected static Component parentComponent;
	protected int pushed;

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
		submit.setAction(this);
		add(new JPanel().add(submit));

		// button pushed number
		try {
			// open file
			File saved = new File(filename);
			BufferedReader csvReader = new BufferedReader(new FileReader(saved));
			// read file if exists
			String line = csvReader.readLine();
			this.pushed = Integer.parseInt(line);
			csvReader.close();

		} catch (NumberFormatException e) {
			this.pushed = 0;

		} catch (IOException e) {
			// file not found (launch for first time) or broken
			this.pushed = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// count up pushed number
		pushed++;

		// print message
		JOptionPane.showMessageDialog(parentComponent, getMessage() + "\n\n"
				+ "pushed: " + pushed);

		// save pushed number
		File saved = new File(filename);
		try {
			BufferedWriter csvWriter = new BufferedWriter(new FileWriter(saved));
			csvWriter.write(String.valueOf(pushed));
			csvWriter.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	protected abstract String getMessage();

	@Override
	public Object getValue(String arg0) {
		// may not used
		return null;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// may not used
	}
}
