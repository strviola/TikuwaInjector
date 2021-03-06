package strviola.injector.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final HeaderPanel header;
	private final JPanel contentCards;
	private final CardLayout cardManager;
	public static final int width = 500;
	public static final int height = 200;

	public MainFrame() {
		setTitle("Tikuwa Injector");
		setSize(width, height);
		setLocation(300, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		this.header = new HeaderPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// ComboBox 変更時の処理
				int index = selectMethod.getSelectedIndex();
				cardManager.show(contentCards, String.valueOf(index));
			}
		};

		this.contentCards = new JPanel();
		this.cardManager = new CardLayout();
		contentCards.setLayout(cardManager);
		contentCards.add("0", new LoginCard(this));
		contentCards.add("1", new SendTikuwaCard(this));
		contentCards.add("2", new ReceivePointCard(this));
		contentCards.add("3", new GivePointCard(this));
		cardManager.show(contentCards, "0");

		add(header);
		add(contentCards);
	}
}
