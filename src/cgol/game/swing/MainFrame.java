package cgol.game.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cgol.game.DebugMsg;
import cgol.game.Game;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8594916559820823847L;

	public MainFrame() {
		initUI();
		this.setLayout(new BorderLayout());

		JPanel toolPanel = new ToolPanel();
		getContentPane().add(toolPanel, BorderLayout.NORTH);
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.setFrame(this);//to set title to pos
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		JScrollPane scrPane = new JScrollPane(boardPanel);
		scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrPane);
		InfoPanel infoPanel = new InfoPanel();
		getContentPane().add(infoPanel, BorderLayout.SOUTH);


		Game.getInstance().setInfoPanel(infoPanel);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 32) { // 32 = space bar
					Game.getInstance().pause();
					DebugMsg.echo("keyPressed: MainFrame - space", 3);
				}
			}
		});
		pack();
	}

	private void initUI() {
		setTitle("CGoL");
		setSize(500, 400);
		setSize(new Dimension(500, 400));
		setVisible(true);
	}
}
