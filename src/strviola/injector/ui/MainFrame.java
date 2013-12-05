package strviola.injector.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private HeaderPanel header;
	private JPanel contentCards;
	private CardLayout cardManager;
	
	public MainFrame() {
		setTitle("Tikuwa Injector");
		setSize(600, 200);
		setLocation(300, 100);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		this.header = new HeaderPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO ComboBox 変更時の処理
				int index = selectMethod.getSelectedIndex();
				cardManager.show(contentCards, String.valueOf(index));
			}
		};
		
		this.contentCards = new JPanel();
		this.cardManager = new CardLayout();
		contentCards.setLayout(cardManager);
		contentCards.add("0", new LoginCard());
		// TODO: contentCards.add("1", new TikuwaUploadCard());
		// TODO: contentCards.add("2", new ReceivePointCard());
		// TODO: contentCards.add("3", new GivePointCard());
		cardManager.show(contentCards, "0");
		
		add(header);
		add(contentCards);
	}
}
