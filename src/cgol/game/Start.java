package cgol.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cgol.game.swing.MainFrame;

public class Start {
	public static void main(String[] args) {
		
		DebugMsg.turnOn(false);
		DebugMsg.setLevel(0);

		EventQueue.invokeLater(() -> {
			JFrame frame = new MainFrame();
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.requestFocus();
		});

		Game.getInstance().run();
	}
}
