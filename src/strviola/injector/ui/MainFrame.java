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
		setSize(400, 600);
		setLocation(300, 100);
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
		// TODO: 各カードの内容をPanelで定義
		// TODO: contentCards.add(panel)
		
		add(header);
		add(contentCards);
	}

}
