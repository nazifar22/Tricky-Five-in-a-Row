package fiveinrow;
        
import javax.swing.JButton;

public class CustomButton {
    private final JButton button;
    private final int row;
    private final int column;

    /**
     * Instantiate an object of CustomButton where this object will contain
     * the button itself and its coordinates in the grid.
     *
     * @param button the JButton
     * @param row    the row number in the grid
     * @param column the column number in the grid
     */
    public CustomButton(JButton button, int row, int column) {
        this.button = button;
        this.row = row;
        this.column = column;
    }

    // Getters for button, row, and column
    public JButton getButton() {
        return button;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
