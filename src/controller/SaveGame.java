package model;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SaveGame {

	private void saveGame(Game game) {
		try {

			Formatter output = new Formatter("blokusSave");

			// save game info
			output.format("%d%n", game.getCurrentPlayer());

			// save board info
			int[][] board = game.getBoard().getGameBoard();

			for (int r = 0; r < game.getBoard().getSize(); r++) {
				for (int c = 0; c < game.getBoard().getSize(); c++) {
					output.format("%d ", board[r][c]);
				}
				output.format("%n");
			}
			// save player info
			output.format("%d%n", game.getPlayers().size());

			for (Player player : game.getPlayers()) {

				output.format("%s%n", player.getName());
				output.format("%d%n", player.getScore());
				output.format("%d%n", player.getRotationsLeft());
				output.format("%b%n", player.isFirstMove());
				output.format("%b%n", player instanceof AIPlayer);

				// save peice info
				output.format("%d%n", player.getPieces().size());

				for (Piece piece : player.getPieces()) {

					output.format("%s%n", piece.getName());
					output.format("%s%n", piece.getColor());
					output.format("%s%n", piece.getOrientation());
					output.format("%b%n", piece.isPlaced());

				}

			}

			output.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error Saving Game");
		}
	}

	public Game loadGame() {
		
		try {
			Scanner input = new Scanner(new File("blokusSave.txt"));
			
			game.setCurrentPlayer(input.nextInt());
			
			//load board
			int[][] board = game.getBoard().getGameBoard();
			
			for (int r = 0; r <game.getBoard().getSize(); r++) {
				for (int c = 0; c < game.getBoard().getSize(); c++) {
					board[r][c] = input.nextInt();
				}
			}
			
			//load players
			int playerCount = input.nextInt();
			ArrayList<Player> players = new ArrayList<Player>();
			
			for (int p =0; p < playerCount; p++) {
				String name = input.next();
				int score = input.nextInt();
				int rotationsLeft = input.nextInt();
				boolean firstMove = input.nextBoolean();
				boolean ai = input.nextBoolean();
				
				if (ai)
					player = new AIPlayer(name, playerColour);
				else
					player newPlayer = new Player(name, playerColour);
				
				player.setScore(score);
				player.setRotationsLeft(rotationsLeft);
				player.setFirstMove(firstMove);

				
				int peiceCount = input.nextInt();
				
				for (int l = 0; l < peiceCount; l++) {
					String peiceName = input.next();
					int peiceColour = input.nextInt();
					String orientation = input.next();
					boolean placed = input.nextBoolean();
					
					Piece piece = new Piece(pieceName, peiceColour, orientation, placed);
					
				}
				
				newPlayer.add(player);
			}
			
			//restore game
			Game game = new Game(newPlayer);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("No Saved Game Found");
			return null;
		}
	}
	
}
