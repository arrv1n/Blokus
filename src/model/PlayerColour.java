package model;
import java.awt.Color;

public enum PlayerColour {
	
	BLUE(new Color(50,119,218)), RED(new Color(220,50,45)), GREEN(new Color(90,165,75)), YELLOW(new Color(239,189,44));
	private final Color colour;
	PlayerColour(Color colour) {
		this.colour = colour; 
	}
	
}
