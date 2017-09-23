package cgol.game;

import java.util.ArrayList;
import java.util.Iterator;

import cgol.game.swing.BoardPanel;
import cgol.game.swing.InfoPanel;

public class Game {
	/**
	 * xy00 xy10 xy20
	 * xy01 xy11 xy21
	 * xy02 xy12 xy22
	 */
	private static Game instance = null;
	private ArrayList<Cell> cells;
	private Cell[][] cellsGridArray;
	private BoardPanel board;
	private InfoPanel infoPanel;
	//private boolean start = false;
	private boolean pause = false;

	private Game() {
		initArrays();
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	private void initArrays() {
		cells = new ArrayList<>();
		cellsGridArray = new Cell[Values.getNrOfCellsX()][Values.getNrOfCellsY()];
		for (int x = 0; x < Values.getNrOfCellsX(); x++)
			for (int y = 0; y < Values.getNrOfCellsY(); y++) {
				cellsGridArray[x][y] = null;
			}
	}

	public void run() { // game cycles
		addCell(29, 30);
		addCell(30, 30);
		addCell(31, 30);
		long start = System.currentTimeMillis();
		while (Values.getExit() == false) {

			while (pause == false) {

				if ((System.currentTimeMillis() - start) >= Values.getSpeed()) {
					Values.setGameCycles(Values.getGameCycles() + 1);
					DebugMsg.echo(" ---------- Cycle: " + Values.getGameCycles() + " List size " + cells.size() + " ----------", 2);

					ageBornCells();
					checkCells();
					removeDeadCells();
					board.repaintBoard();

					infoPanel.updateData();

					//
					start = System.currentTimeMillis();
				}
			}
		}
	}

	private void checkCells() {
		int neighbours;
		Cell current;

		// TODO iterator!
		for (int i = 0; i < cells.size(); i++) {
			current = cells.get(i);
			if (i == 0) {
				Values.setStatCellsOldest(current.getAge());
			}
			DebugMsg.echo(" -- checkCells ROUND i:" + i + "/" + cells.size() + " cell x:" + current.getX() + " y:" + current.getY() + " stat:" + current.getState(), 0);
			if (current.getState().equals(CellState.ALIVE) || current.getState().equals(CellState.DYING)) {// for non-new cells
				checkNeighboursForNewCells(current);
				neighbours = countNeighbours(current);
				if (neighbours == 2 || neighbours == 3) { // will live and age
					DebugMsg.echo("checkCells: >> Cell: " + current.getX() + "/" + current.getY() + " Will live, has 2 or 3 neighbours (" + neighbours + ")", 1);
					current.addAge();
					current.setState(CellState.ALIVE);
				} else if (neighbours < 2 || neighbours > 3) {
					DebugMsg.echo("checkCells: >> Cell: " + current.getX() + "/" + current.getY() + " Will die, has < 2 or 3 < neighbours (" + neighbours + ")", 1);
					current.setState(CellState.DYING);
				}
			}
		}
	}

	private void ageBornCells() {
		Iterator<Cell> cellsIterator = cells.iterator();
		while (cellsIterator.hasNext()) {
			cellsIterator.next().setState(CellState.ALIVE);
		}
	}

	private void removeDeadCells() {
		java.util.Iterator<Cell> cellsIterator = cells.iterator();
		Cell current;
		int cellCount = cells.size();
		DebugMsg.echo("removeDeadCells: list before:" + cells.size(), 3);
		while (cellsIterator.hasNext()) {
			current = cellsIterator.next();
			if (current.getState().equals(CellState.DYING)) {
				DebugMsg.echo("removeDeadCells: x:" + current.getX() + " y:" + current.getY() + " is dead, removing", 1);
				cellsGridArray[current.getX()][current.getY()] = null;
				cellsIterator.remove();
			}

		}
		DebugMsg.echo("removeDeadCells: list after:" + cells.size(), 3);
		Values.setStatCellsDead(Values.getStatCellsDead() + (cellCount - cells.size())); // add dead cell count to stats
	}

	private int countNeighbours(Cell cell) {
		int x, y, neighbours;
		x = cell.getX();
		y = cell.getY();
		neighbours = 0;

		if (x == 0 || y == 0 || x == (Values.getNrOfCellsX() - 1) || y == (Values.getNrOfCellsX() - 1)) { // cell on border/s
			DebugMsg.echo("countN. : cell x:" + x + " y:" + y + " som na kraji", 0);
			return neighbours;
		} else { // cell in non-border area
			for (int row = -1; row <= 1; row++) {
				neighbours += oneWhenCellExists(x + row, y - 1); // top
				neighbours += oneWhenCellExists(x + row, y + 1); // bottom
			}
			neighbours += oneWhenCellExists(x + 1, y); // right
			neighbours += oneWhenCellExists(x - 1, y); // left
		}
		return neighbours;
	}

	private int oneWhenCellExists(int x, int y) {
		if (cellsGridArray[x][y] != null && (cellsGridArray[x][y].getState().equals(CellState.ALIVE) || (cellsGridArray[x][y].getState().equals(CellState.DYING)))) {
			return 1;
		}
		return 0;
	}

	/**
	 * Goes trough the neighbours of the input cell
	 * and checks for new cells
	 * @param cell - the "middle" cell
	 */
	private void checkNeighboursForNewCells(Cell cell) {
		int x, y;
		x = cell.getX();
		y = cell.getY();

		if (x == 0 || y == 0 || x == (Values.getNrOfCellsX() - 1) || y == (Values.getNrOfCellsX() - 1)) { // cell on border/s
			DebugMsg.echo("checkN. : cell x:" + x + " y:" + y + " som na kraji", 0);
		} else { // cell in non-border area
			for (int row = -1; row <= 1; row++) {
				checkIfNewCell(x + row, y - 1);
				checkIfNewCell(x + row, y + 1);
			}
			checkIfNewCell(x + 1, y);
			checkIfNewCell(x - 1, y);
		}
	}

	private void checkIfNewCell(int x, int y) {
		if (countNeighbours(new Cell(x, y)) == 3) {
			if (cellsGridArray[x][y] == null) {
				DebugMsg.echo("X:" + x + " Y:" + y + "chechIfnewCell: >> has == 3neighbours, will come alive next round", 1);
				addCell(x, y, CellState.BORN);
			}
		}
	}

	public void cellClicked(int x, int y) {
		if (checkIfCellExists(x, y)) { // cell exists, remove it
			DebugMsg.echo("Cell clicked - exists -> remove it", 3);
			removeCell(x, y);
		} else { // non-existing cell, add it
			DebugMsg.echo("Cell clicked - does not exists -> add it", 3);
			addCell(x, y);
		}
	}

	private void addCell(int x, int y) {
		Cell cellToAdd = new Cell(x, y);
		addCellToVariables(x, y, cellToAdd);
	}

	private void addCell(int x, int y, CellState state) {
		Cell cellToAdd = new Cell(x, y, state);
		addCellToVariables(x, y, cellToAdd);

	}

	private void addCellToVariables(int x, int y, Cell cellToAdd) {
		cellsGridArray[x][y] = cellToAdd; // set the cell in 2darray				
		cells.add(cellToAdd); // add cell to list	
	}

	private void removeCell(int x, int y) {
		cells.remove(cellsGridArray[x][y]);
		cellsGridArray[x][y] = null;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void pause() { //puase/unpause
		if (pause == true) {
			pause = false;
			DebugMsg.echo("pause: !!! PAUSE OFF", 3);
		} else {
			pause = true;
			DebugMsg.echo("pause: !!! PAUSE ON", 3);
		}

	}

	public boolean checkIfCellExists(int x, int y) {
		if (cellsGridArray[x][y] != null)
			return true;
		else
			return false;
	}

	public void setBoard(BoardPanel board) {
		this.board = board;
	}

	public void setInfoPanel(InfoPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

}
