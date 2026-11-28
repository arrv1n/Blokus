package application;

import controller.BlokusController;

/*
 * Name: Group #4 - Arvin Yu, Sean Lee, Troy Williams
 * Contributions: 
 * 		Arvin:
 * 		Sean:
 * 		Troy:
 * 			- BlokusFrame (90%): BlokusFrame, setUp(BlokusFrame, Panels, Labels, Cursor), 
 * 				update(board, piece, player), showResults, sortPlayerScore, swap, 
 * 				createLeaderBoard, updateCursorImage, checkBlockIndex, updateCursorPosition,
 * 				hideCursorImage
 * 			- BoardPanel (95%): BoardPanel, setUpLabels, update, showGhostPiece
 * 			- Frame (90%): Frame, createGUIObjects, createMenuBar, createButtons,
 * 				 setUpFrame, setUpDesignLabels
 * 			- InstructionsFrame (100%): InstructionsFrame, setUpInstructionsFrame, 
 * 				setUpLabels, setUpInstructionsText, displayInstructions
 * 			- PiecePanel (90%): PiecePanel, setUpPiecePanel, setUpLabels, fillPieceArray, update
 * 			- PlayerPanel (95%): PlayerPanel, setUpPlayerPanel, setUpLabels, update
 * 			- TitleFrame (95%): TitleFrame, setUpTitleFrame, setUpButtons
 * 			- Update (100%): update
 * Date: November 27, 2026
 * Course Code: ICS4U1-01 Ms. Biswas
 * Title: Blokus
 * Description: This program allows the user to play the game Blokus. It includes the game with AI
 * players and instructions on how to play the game. The game is played by clicking on pieces and
 * placing them on the board. This version of the game features limited rotations and flips. There
 * is also the function to save and load games.
 * Major Skills: Data Structures(arrays, 2d arrays, arraylists), Swing GUI components, Sorting algorithm, 
 * Recursion, MVC framework, Classes and objects(abstract classes, inheritance, polymorphism), interfaces
 * Added features: AI players, saving and loading games, showing movement of pieces from panel to board,
 * background music
 * Areas of concern:
 */

public class BlokusApplication {
	
	public static void main(String[] args) {
		
		new BlokusController();
	}

}
