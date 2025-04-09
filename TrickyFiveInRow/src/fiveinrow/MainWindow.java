package fiveinrow;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Represents the main window of the Five in a Row game.
 * This class extends BaseWindow and is responsible for displaying the main menu
 * and launching new game windows.
 */

public class MainWindow extends BaseWindow {
    
    private final List<Window> gameWindows = new ArrayList<>();
    
    /**
     * Constructs the main window and initializes the game menu.
     */
    
    public MainWindow() {
        super();
        setSize(500, 500);

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JButton small = createButton("6 x 6", 6);
        JButton medium = createButton("10 x 10", 10);
        JButton large = createButton("14 x 14", 14);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(small);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(medium);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(large);
        panel.add(Box.createVerticalGlue());

        getContentPane().add(panel);
    }
    
    /**
     * Creates a button with specified text and size, and assigns an action listener to it.
     * 
     * @param text The text to display on the button.
     * @param size The size of the game to start when this button is clicked.
     * @return The created JButton object.
     */
    
    private JButton createButton(String text, int size) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 100)); 
        button.setPreferredSize(new Dimension(300, 100)); 
        button.addActionListener(getActionListener(size));
        button.setBackground(new Color(255, 200, 210));
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        return button;
    }
    
    /**
     * Opens a new game window with the specified size.
     * 
     * @param size The size of the game board for the new game window.
     */
    
    private void openGameWindow(int size) {
        Window gameWindow = new Window(size, this);
        gameWindow.setVisible(true);
        gameWindows.add(gameWindow);
    }
    
    /**
     * Creates and returns an action listener for game start buttons.
     * This listener opens a new game window of the specified size.
     * 
     * @param size The size of the game board for the new game window.
     * @return An ActionListener for the game start buttons.
     */
    
    private ActionListener getActionListener(final int size){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Window window = new Window(size, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
        };
    }
    
    /**
     * Gets the list of currently open game windows.
     * 
     * @return A list of Window objects representing open game windows.
     */
    
    public List<Window> getWindowList() {
        return gameWindows;
    }
    
     /**
     * Performs actions upon exiting the main window, such as exiting the application.
     */
    
    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}