package cgol.game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = -3426688560182292446L;

	public MainPanel(JFrame frame) {

		setSize(450, 350);
		setPreferredSize(new Dimension(450, 350));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createTitledBorder("test"));


		JMenuBar menubar = new JMenuBar();
		//ImageIcon icon = new ImageIcon("exit.png");

		JMenu file = new JMenu("Options");
		//file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit");
		// eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		
		JLabel speedLabel = new JLabel(" Speed");

		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 200);
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speedLabel.setText(" Speed " + speedSlider.getValue() + " ");
			}
		});

		JLabel zoomLabel = new JLabel(" Zoom");

		JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, 1, 200, 100);
		zoomSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				zoomLabel.setText(" Zoom " + zoomSlider.getValue() + " ");
			}
		});

		file.add(eMenuItem);

		menubar.add(file);
		
		
		menubar.add(speedLabel);
		menubar.add(speedSlider);
		menubar.add(zoomLabel);
		menubar.add(zoomSlider);

		frame.setJMenuBar(menubar);


	}

}
