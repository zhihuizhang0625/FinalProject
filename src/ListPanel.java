import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Zhihui Zhang
 *
 */

public class ListPanel extends JPanel {

    private JPanel r;
    private JLabel recommended;

    /**
     * initiates the ListPanel and set up style
     * 
     */
    public ListPanel() {
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // set up the recommended list
        recommended = new JLabel();
        r = new JPanel();
        this.add(r);
        r.add(recommended);

    }

    /**
     * get recommended list
     * 
     * @return JLabel recommended
     */
    public JLabel getRecommended() {
        return recommended;
    }

}
