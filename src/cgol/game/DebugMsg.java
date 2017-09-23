package cgol.game;

public class DebugMsg {

	private static boolean on = false;
	private static int level = 0;

	public static void turnOn(boolean on) {
		DebugMsg.on = on;
	}

	public static void setLevel(int lvl) {
		DebugMsg.level = lvl;
	}

	public static void echo(String text, int nr) {
		if (on == true && nr >= level) {
			System.out.println("#" + nr + " Debug: " + text);
		}
	}
}
