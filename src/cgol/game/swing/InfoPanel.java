package cgol.game.swing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cgol.game.Values;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 7954518554074806614L;

	JLabel cyclesLabel = new JLabel("Cycles");
	JLabel cellsDeadLabel = new JLabel("Dead cells");
	JLabel cellsOldestLabel = new JLabel("Oldest cell age");

	public InfoPanel() {
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), 25));
		setSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), 25));
		add(cyclesLabel);
		add(cellsDeadLabel);
		add(cellsOldestLabel);
	}

	public JPanel getInstance() {
		return this;
	}

	public void updateData() {
		cyclesLabel.setText("Cycles: " + Values.getGameCycles() + " ");
		cellsDeadLabel.setText("Dead cells: " + Values.getStatCellsDead() + " ");
		cellsOldestLabel.setText("Oldest cell: " + Values.getStatCellsOldest() + " ");

	}

}
