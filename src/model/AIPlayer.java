package model;

public class AIPlayer extends Player {

    // AI difficulty
    private boolean best;

    // Best move information
    private Piece bestPiece;
    private Piece bestOrientation;
    private int bestRow;
    private int bestCol;
    private int bestTransformation;
    private int bestScore;

    // Constructor
    public AIPlayer(String name, PlayerColour colour, boolean best) {
        super(name, colour);
        this.best = best;
    }

    // Select the search algorithm based on chosen difficulty
    public boolean findMove(Board board) {

    	resetBestMove();
    	// Find Best Move AI
        if (best) {
            searchPieces(board, 0, true);
        }
        // Find First Move AI
        else {
            searchPieces(board, 0, false);
        }
        return bestPiece != null;
    }

    // Reset information before each AI turn
    private void resetBestMove() {

        bestPiece = null;
        bestOrientation = null;

        bestRow = -1;
        bestCol = -1;

        bestTransformation = Integer.MAX_VALUE;
        bestScore = -1;
        
    }

    // Recursively search all pieces
    private boolean searchPieces(Board board, int index, boolean findBest) {

        // Base case
        if (index >= getPieces().size()) {
            return false;
        }

        Piece piece = getPieces().get(index);

        // Only search unused pieces
        if (!piece.isPlaced()) {
            if (searchOrientations(board, piece, findBest)) {
                return true;
            }
        }

        // Search the next piece
        return searchPieces(board, index + 1, findBest);
    }

    // Search the eight possible orientations
    private boolean searchOrientations(Board board, Piece original, boolean findBest) {

        for (int flip = 0; flip < 2; flip++) {

            for (int rotation = 0; rotation < 4; rotation++) {

                int transformation = rotation + flip;

                // Three rotations followed by a flip can also be achieved by a flip and one rotation
                if (flip == 1 && rotation == 3) {
                    transformation = 2;
                }

                // Check remaining transformations
                if (transformation > getRotationflipLeft()) {
                    continue;
                }

                Piece shape = copyPiece(original);

                // Use the shorter transformation sequence
                if (flip == 1 && rotation == 3) {

                    shape.flip();
                    shape.rotate();

                } else {

                    for (int i = 0; i < rotation; i++) {
                        shape.rotate();
                    }

                    if (flip == 1) {
                        shape.flip();
                    }
                }

                // Search positions using this orientation
                if (searchPositions(board, original, shape, transformation, findBest)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Search all board positions
    private boolean searchPositions(Board board, Piece original, Piece piece, int transformation, boolean findBest) {

        for (int row = 0; row < Board.size; row++) {

            for (int col = 0; col < Board.size; col++) {

                // Check placement using Board
                if (board.isValidMove(piece, row, col, this)) {

                    int score = piece.size();

                    if (!findBest || score > bestScore || (score == bestScore && transformation < bestTransformation)) {

                        // Store the move information
                        bestPiece = original;
                        bestOrientation = piece;

                        bestRow = row;
                        bestCol = col;

                        bestTransformation = transformation;
                        bestScore = score;

                        // Easy AI stops at the first legal move
                        if (!findBest) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    // Create a separate copy of a piece
    private Piece copyPiece(Piece original) {

        Piece copy = new Piece(
            original.getName(),
            original.getColor(),
            original.getOrientation(),
            original.isPlaced()
        );

        for (Block block : original.getBlocks()) {

            copy.newBlock(block.getRow(), block.getCol());
        }

        return copy;
    }

    // Apply the selected orientation to the original piece
    public void applyBestOrientation() {

        if (bestPiece == null || bestOrientation == null) {
            return;
        }

        for (int i = 0; i < bestPiece.size(); i++) {

            Block original = bestPiece.getBlocks().get(i);

            Block transformed = bestOrientation.getBlocks().get(i);

            original.setRow(transformed.getRow());
            original.setCol(transformed.getCol());
        }
    }

    // Getters
    public Piece getBestPiece() {
        return bestPiece;
    }

    public Piece getBestOrientation() {
        return bestOrientation;
    }

    public int getBestRow() {
        return bestRow;
    }

    public int getBestCol() {
        return bestCol;
    }

    public int getbestTransformation() {
        return bestTransformation;
    }

    public int getBestScore() {
        return bestScore;
    }
}
