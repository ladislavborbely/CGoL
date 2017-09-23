package cgol.game;

import java.awt.Color;
import java.awt.Rectangle;

public class Cell {
	private int x, y;
	private CellState state; // 1 = healthy, 2 = will die, 3 = will be born 
	private int age;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		age = 0;
		state = CellState.ALIVE;
	}
	
	public Cell(int x, int y, CellState state) {
		this(x, y);
		this.state = state;
	}

	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle(x * Values.getCellSize(), y * Values.getCellSize(), Values.getCellSize(), Values.getCellSize());
		return rect ;
	}

	public Color getColor() { // gets brighter over time
		// TODO optimalize
		int r = Values.getCellColorRed();
		int g = Values.getCellColorGreen();
		int b = Values.getCellColorBlue();
		
		if ((g + age + Values.getCellAgeing()) > 255) { // green must be dominant
			int max = (255 - g);
			r +=  max;
			g +=  max;
			b +=  max;
		} else {
			r += age + Values.getCellAgeing();
			g += age + Values.getCellAgeing();
			b += age + Values.getCellAgeing();
		}
		return new Color(r, g, b);
	}

	public void addAge() {
		age++;
	}

	public CellState getState() {
		return state;
	}

	public void setState(CellState state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Cell_x:" + x + "_y:" + y;
	}


}
