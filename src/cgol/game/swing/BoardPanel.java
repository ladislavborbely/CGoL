package cgol.game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cgol.game.Cell;
import cgol.game.DebugMsg;
import cgol.game.Game;
import cgol.game.Values;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 3302086441807300398L;
	private JFrame mainFrame;
	private Game game;
	private JPanel instance;

	public BoardPanel() {
		game = Game.getInstance();
		instance = this;
		game.setBoard(this);
		initBoard();
		//mainFrame = SwingUtilities.getWindowAncestor(instance);

	}

	private void initBoard() {
		setPreferredSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), Values.getNrOfCellsX() * Values.getCellSize()));
		setSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), Values.getNrOfCellsY() * Values.getCellSize()));
		setBackground(new Color(247, 247, 247));

		addMouseMotionListener(new MouseHandler());
		addMouseListener(new MouseHandler());
		addMouseWheelListener(new MouseHandler());
	}

	private void drawGrid(Graphics g) {
		// TODO lines not rectagles
		Graphics2D something = (Graphics2D) g;

		something.setColor(new Color(255, 255, 255));

		for (int x = 0; x < Values.getNrOfCellsX() * Values.getCellSize() + 1; x = x + Values.getCellSize())
			for (int y = 0; y < Values.getNrOfCellsX() * Values.getCellSize() + 1; y = y + Values.getCellSize()) {
				something.drawRect(0, 0, x, y);
			}
	}

	private void drawRectangles(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Cell cell : game.getCells()) {
			//g2.setColor(new Color(Constants.DEFAULT_CELL_COLOR_RED, Constants.DEFAULT_CELL_COLOR_GREEN, Constants.DEFAULT_CELL_COLOR_BLUE));
			g2.setPaint(cell.getColor());
			g2.fill(cell.getRectangle());
			g2.setColor(new Color(100, 140, 90));
			g2.draw(cell.getRectangle());
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawRectangles(g);
	}

	public void repaintBoard() {
		repaint();
	}

	public void setFrame(JFrame frame) {//title can be changed
		mainFrame = frame;
	}

	private class MouseHandler extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			// if the click is inside the board
			if (((e.getX() / Values.getCellSize()) < ((Values.getNrOfCellsX() * Values.getCellSize()) / Values.getCellSize()))
					&& ((e.getY() / Values.getCellSize()) < (Values.getNrOfCellsY() * Values.getCellSize()) / Values.getCellSize())) {

				int xNr = e.getX() / Values.getCellSize(); // NOT in pixels
				int yNr = e.getY() / Values.getCellSize(); // NOT in pixels
				game.cellClicked(xNr, yNr);
				repaint();
				mainFrame.setTitle("Cell added at X:" + xNr + " Y:" + yNr);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mainFrame.setTitle("X: " + (e.getX() / Values.getCellSize()) + " Y: " + e.getY() / Values.getCellSize());//pos in title
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			DebugMsg.echo("mwl: Wheel moved", 2);
			if ((Values.getCellSize() + e.getWheelRotation()) >= 8 && (Values.getCellSize() + e.getWheelRotation()) <= 50) {//limit
				Values.setCellSize(Values.getCellSize() + e.getWheelRotation());
				instance.setSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), Values.getNrOfCellsX() * Values.getCellSize()));
				instance.setPreferredSize(new Dimension(Values.getNrOfCellsX() * Values.getCellSize(), Values.getNrOfCellsX() * Values.getCellSize()));
			}


		}
	}

}
