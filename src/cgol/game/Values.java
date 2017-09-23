package cgol.game;

/**
 * Setters, getters, statistics 
 */
public class Values {

	//TODO upratat nepotrebne veci do tried
	static private int cellSize = Constants.DEFAULT_CELL_SIZE;
	static private int nrOfCellsX = Constants.DEFAULT_NR_OF_CELLS_X;
	static private int nrOfCellsY = Constants.DEFAULT_NR_OF_CELLS_Y;
	static private int cellColorRed = Constants.DEFAULT_CELL_COLOR_RED;
	static private int cellColorGreen = Constants.DEFAULT_CELL_COLOR_GREEN;
	static private int cellColorBlue = Constants.DEFAULT_CELL_COLOR_BLUE;
	static private int cellColorAgeing = Constants.DEFAULT_CELL_COLOR_AGEING;
	static private boolean exit = false;
	static private int speed = Constants.DEFAULT_GAME_SPEED;
	static private boolean pause = false;
	static private int statCellsDead = 0;
	static private int statCellsAlive = 0;
	static private int statCellsOldest = 0;

	static private int gameCycles = 0;

	private Values() {
		// no instance
	}

	public static int getStatCellsOldest() {
		return statCellsOldest;
	}

	public static void setStatCellsOldest(int statCellsOldest) {
		Values.statCellsOldest = statCellsOldest;
	}

	public static int getStatCellsDead() {
		return statCellsDead;
	}

	public static void setStatCellsDead(int statCellsDead) {
		Values.statCellsDead = statCellsDead;
	}

	public static int getStatCellsAlive() {
		return statCellsAlive;
	}

	public static void setStatCellsAlive(int statCellsAlive) {
		Values.statCellsAlive = statCellsAlive;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Values.speed = speed;
	}

	public static void setExit(boolean exit) {
		Values.exit = exit;
	}

	public static boolean getExit() {
		return exit;
	}

	public static int getCellAgeing() {
		return cellColorAgeing;
	}

	public static void setCellAgeing(int cellAgeing) {
		Values.cellColorAgeing = cellAgeing;
	}

	public static int getCellSize() {
		return cellSize;
	}

	public static void setCellSize(int cellSize) {
		Values.cellSize = cellSize;
	}

	public static int getNrOfCellsX() {
		return nrOfCellsX;
	}

	public static void setNrOfCellsX(int nrOfCellsX) {
		Values.nrOfCellsX = nrOfCellsX;
	}

	public static int getNrOfCellsY() {
		return nrOfCellsY;
	}

	public static void setNrOfCellsY(int nrOfCellsY) {
		Values.nrOfCellsY = nrOfCellsY;
	}

	public static int getCellColorRed() {
		return cellColorRed;
	}

	public static void setCellColorRed(int cellColorRed) {
		Values.cellColorRed = cellColorRed;
	}

	public static int getCellColorGreen() {
		return cellColorGreen;
	}

	public static void setCellColorGreen(int cellColorGreen) {
		Values.cellColorGreen = cellColorGreen;
	}

	public static int getCellColorBlue() {
		return cellColorBlue;
	}

	public static void setCellColorBlue(int cellColorBlue) {
		Values.cellColorBlue = cellColorBlue;
	}

	public static int getGameCycles() {
		return gameCycles;
	}

	public static void setGameCycles(int cellGameCycles) {
		Values.gameCycles = cellGameCycles;
	}


}
