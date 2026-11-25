package model;
import java.awt.Color;

public enum PlayerColour {

    BLUE(new Color(50,119,218), 0, 0),
    RED(new Color(220,50,45), 0, 19),
    GREEN(new Color(90,165,75), 19, 0),
    YELLOW(new Color(239,189,44), 19, 19);

    private Color colour;
    private int startRow;
    private int startCol;

    PlayerColour(Color colour, int startRow, int startCol) {
        this.colour = colour;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public Color getPaintColor() {
        return colour;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }
}
