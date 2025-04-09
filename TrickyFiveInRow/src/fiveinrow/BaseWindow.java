package fiveinrow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 * A base window class for the Five in a Row game.
 * This class extends JFrame and provides common functionality for windows in the game,
 * such as setting the default size, title, and close operation.
 */

public class BaseWindow extends JFrame {
    
    /**
     * Constructs a BaseWindow with default settings.
     * It sets the title, size, default close operation, and window icon.
     */
    
    public BaseWindow(){
        setTitle("Tricky five-in-a-row");
        setSize(500, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                showExitConfirmation();
            }
        });
        
        URL url = Window.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

    }
    
    /**
     * Displays a confirmation dialog when attempting to close the window.
     */
    
    private void showExitConfirmation(){
        int n = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Do You Want To Quit?", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION){
            doUponExit();
        }
    }
    
    /**
     * Performs actions upon confirming exit, such as disposing the window.
     * This method can be overridden in subclasses to provide additional exit functionality.
     */
    
    protected void doUponExit(){
        this.dispose();
    }
    
}
