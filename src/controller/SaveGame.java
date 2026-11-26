package controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;

public class SaveGame {

	public static void saveGame(Game game) {
		try (Formatter output = new Formatter("blokusSave")) {

			// save game info
			output.format("%d%n",game.getPlayers().indexOf(game.getCurrentPlayer()));

			// save board info
			for (int r = 0; r < Board.size; r++) {
				for (int c = 0; c < Board.size; c++) {
					PlayerColour cell = game.getBoard().getGameBoard(r, c);
					
					if (cell == null)
						output.format("%s ",  "EMPTY");
					else
						output.format("%s ", cell.name());
				}
				output.format("%n");
			}
			// save player info
			output.format("%d%n", game.getPlayers().size());

			for (Player player : game.getPlayers()) {

				output.format("%s%n", player.getName());
				output.format("%d%n", player.getScore());
				output.format("%s%n", player.getColour().name());
				output.format("%d%n", player.getRotationflipLeft());
				output.format("%b%n", player.isFirstMove());
				output.format("%b%n", player instanceof AIPlayer);
				if (player instanceof AIPlayer)
					output.format("%b%n", ((AIPlayer) player).getBest());

				// save peice info
				output.format("%d%n", player.getPieces().size());

				for (Piece piece : player.getPieces()) {

					output.format("%s%n", piece.getName());
					output.format("%b%n", piece.isPlaced());

				}

			}


		} catch (FileNotFoundException e) {
			System.out.println("Error Saving Game");
		}
	}

	public static Game loadGame() {

		try (Scanner input = new Scanner(new File("blokusSave"))) {

			int currentPlayer = input.nextInt();

			// load board
			PlayerColour[][] saved = new PlayerColour[Board.size][Board.size];

			for (int r = 0; r < Board.size; r++) {
				for (int c = 0; c < Board.size; c++) {
					String word = input.next();
					
					if (word.equals("EMPTY"))
						saved[r][c] = null;
					else
						saved[r][c] = PlayerColour.valueOf(word);
				}
			}

			// load players
			int playerCount = input.nextInt();
			ArrayList<Player> players = new ArrayList<Player>();

			for (int p = 0; p < playerCount; p++) {
				input.nextLine();
				String name = input.nextLine();
				int score = input.nextInt();
				PlayerColour playerColour = PlayerColour.valueOf(input.next());
				int rotationsLeft = input.nextInt();
				boolean firstMove = input.nextBoolean();
				boolean ai = input.nextBoolean();
				boolean best = false;
				if (ai)
					best = input.nextBoolean();

				Player player;

				if (ai)
					player = new AIPlayer(name, playerColour, best);
				else
					player = new Player(name, playerColour);

				player.setScore(score);
				player.setRotationflipLeft(rotationsLeft);
				player.setFirstMove(firstMove);

				int peiceCount = input.nextInt();

				for (int l = 0; l < peiceCount; l++) {
					String peiceName = input.next();
					boolean placed = input.nextBoolean();


					for (Piece piece : player.getPieces())
						if (piece.getName().equals(peiceName))
							piece.setPlaced(placed);

				}

				players.add(player);
			}
			

			// restore game
			Game game = new Game(players);
			restoreGame(game,currentPlayer,saved);

			
			

			return game;

		} catch (FileNotFoundException e) {
			System.out.println("No Saved Game Found");
			return null;
		}
	}


	

	private static void restoreGame(Game game, int currentPlayer, PlayerColour[][] saved) {
		
		for (int i = 0; i < currentPlayer; i++)
			game.nextPlayer();
		
		for (int r = 0; r < Board.size; r++) {
			for (int c = 0; c < Board.size; c++) {
				game.getBoard().setSquare(r, c, saved[r][c]);
			}
		}
		
	}
}
