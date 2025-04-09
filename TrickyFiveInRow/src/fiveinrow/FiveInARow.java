package fiveinrow;

/**
 * The entry point for the Five in a Row game application.
 * This class contains the main method which launches the game by
 * creating and displaying the main window.
 */

public class FiveInARow {
    
    /**
     * The main method that starts the Five in a Row game.
     * 
     * @param args Command line arguments (not used in this application).
     */

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
    
}
