package se.athega.lizell.gameoflife;

import java.io.IOException;

public class Game {
	private final static String ESC = "\033[";

	private World world;

	public Game() {
		this.world = new World();
	}

	public Game(final String pattern) throws Exception {
		if (pattern.startsWith(Cell.Symbols.DEAD.toString()) || pattern.startsWith(Cell.Symbols.LIVE.toString())) {
			this.world = new World(pattern);
		} else if ("blinker".equalsIgnoreCase(pattern)) {
			this.world = new World("---\n###\n---\n");
		} else if ("beehive".equalsIgnoreCase(pattern)) {
			this.world = new World(
					"------\n" +
					"--##--\n" +
					"-#--#-\n" +
					"--##--\n" +
					"------\n"
			);
		} else if ("glider".equalsIgnoreCase(pattern)) {
			this.world = new World(
					"--#---------\n" +
					"#-#---------\n" +
					"-##---------\n" +
					"------------\n" +
					"------------\n" +
					"------------\n"
			);
		} else if ("die".equalsIgnoreCase(pattern)) {
			this.world = new World(
					"-----------------\n" +
					"-----------------\n" +
					"----###---###----\n" +
					"--#----#-#----#--\n" +
					"--#----#-#----#--\n" +
					"--#----#-#----#--\n" +
					"----###---###----\n" +
					"-----------------\n" +
					"----###---###----\n" +
					"--#----#-#----#--\n" +
					"--#----#-#----#--\n" +
					"--#----#-#----#--\n" +
					"----###---###----\n" +
					"-----------------\n" +
					"-----------------\n"
			);
		} else {
			throw new Exception("Unrecognized pattern");
		}
	}

	private void run() throws InterruptedException, IOException {
		int generation = 1;
		while(true) {
			System.out.print(ESC + "2J"); System.out.flush(); // Quick and ugly ANSI clear
			System.out.println("Generation " + generation);
			System.out.print(world.getGrid());
			Thread.sleep(500);
			world.createNextGeneration();
			generation++;
		}
	}

	public static void main(final String[] args) throws Exception {
		final Game game = (args.length > 0) ? new Game(args[0]) : new Game();
		game.run();
	}
}
