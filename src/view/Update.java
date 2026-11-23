package view;

import model.*;

//This interface will be used for the panels on the blokus frame (board, 
//player, piece panels)
//It has an abstract method that will allow those panels to update the information
//displayed

public interface Update {
	
	//The method parameter takes in the game object to use it data to update 
	void update(Game game);
	
}