package fiveinrow;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a window in the Five in a Row game.
 * This class extends BaseWindow and manages the game's GUI, including 
 * the game grid, new game button, and status label.
 */

public class Window extends BaseWindow{
    private final int size;
    private final Model model;
    private final JLabel label;
    private final MainWindow mainWindow;
    private List<CustomButton> buttons;
    
    /**
     * Constructs a new game window.
     * 
     * @param size The size of the game grid.
     * @param mainWindow Reference to the main window of the application.
     */
    
    public Window (int size, MainWindow mainWindow){
        buttons = new ArrayList<>();
        this.size = size;
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);
        
        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New Game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        
        for (int i = 0; i < size; ++i){
            for (int j = 0; j < size; ++j){
                addButton(mainPanel, i , j);
            }
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
    
     /**
     * Updates the game grid based on the current state of the model.
     */
    
    private void updateGrid() {
        Player[][] tableCopy = model.getTableCopy();
        for (CustomButton customButton : buttons) {
            JButton button = customButton.getButton();
            int row = customButton.getRow();
            int column = customButton.getColumn();

            if (!tableCopy[row][column].name().equals("NOBODY")) {
                button.setText(tableCopy[row][column].name());
            } else {
                button.setBackground(null);
                button.setText("");
            }
        }
    }

    /**
     * Adds a button to the game grid.
     * 
     * @param panel The panel where the button is to be added.
     * @param row The row index of the button in the grid.
     * @param column The column index of the button in the grid.
     */
    
    private void addButton(JPanel panel, final int row, final int column) {
    final JButton button = new JButton();

    button.addActionListener(e -> {
        Player newValue = model.step(row, column);
        button.setText(newValue.name());
        if (newValue.name().equals("O")) {
            button.setBackground(Color.pink);
        } else {
            button.setBackground(new Color(173, 216, 230));
        }

        updateLabelText();
        updateGrid();

        Player winner = model.findWinner(row, column);
        if (winner != Player.NOBODY) {
            showGameOverMessage(winner);
        }
    });

    buttons.add(new CustomButton(button, row, column));
    panel.add(button);
}

    /**
     * Displays a dialog showing the game over message and starts a new game.
     * 
     * @param winner The player who won the game.
     */
    
    private void showGameOverMessage (Player winner){
        JOptionPane.showMessageDialog(this, "Game Over! Winner: " + winner.name());
        newGame();
    }
    
    /**
     * Starts a new instance of the game and disposes of the current window.
     */

    
    private void newGame(){
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    /**
     * Updates the label to display the current player's turn.
     */
    
    private void updateLabelText(){
        label.setText("current Player: " + model.getActualPlayer().name());
    }
    
    /**
     * Performs actions upon exiting the window, such as removing the window from the main window's list.
     */
    
    @Override
    protected void doUponExit(){
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
}
