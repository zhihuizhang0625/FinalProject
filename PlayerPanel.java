import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Zhihui Zhang
 *
 */
class PlayerPanel extends JPanel {
    private JTextField input;
    private JButton button;

    /**
     * initiates the PlayerPanel and set up style
     * 
     */
    public PlayerPanel() {
        setLayout(new GridLayout(1, 1));
        input = new JTextField();
        add(input);
        button = new JButton("ENTER");
        add(button);
    }

    /**
     * get input field
     * 
     * @return JTextField input
     * 
     */
    public JTextField getInput() {
        return input;
    }

    /**
     * get button
     * 
     * @return JButton button
     * 
     */
    public JButton getButton() {
        return button;
    }
}
