package cgol.game.swing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cgol.game.Values;

public class ToolPanel extends JPanel {
	private static final long serialVersionUID = 3050579767918243370L;

	public ToolPanel() {
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), 50));
		setSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), 50));
		

		JLabel speedDescLabel = new JLabel(" [Speed - duration of a cycle in ms, more = slower] ");
		add(speedDescLabel);
		JLabel speedLabel = new JLabel(" Speed: " + Values.getSpeed());
		add(speedLabel);
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 15, 1000, Values.getSpeed());
		add(speedSlider);
		JLabel pasueDescLabel = new JLabel(" [press SPACE for un/pause] ");
		add(pasueDescLabel);

		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speedLabel.setText(" Speed: " + speedSlider.getValue() + " ");
				Values.setSpeed(speedSlider.getValue());
				SwingUtilities.windowForComponent(speedSlider).requestFocus(); // aby fungoval keylistener
			}
		});
	}
}
