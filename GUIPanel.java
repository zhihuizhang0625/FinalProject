import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * @author Zhihui Zhang
 *
 */
public class GUIPanel extends JPanel {
    JLabel[] columns = new JLabel[5];

    /**
     * initiates the GUIPanel and set up style
     * 
     */
    public GUIPanel() {
        this.setLayout(new GridLayout(1, 5));
        Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        for (int i = 0; i < 5; i++) {
            columns[i] = new JLabel();
            columns[i].setHorizontalAlignment(JLabel.CENTER);
            columns[i].setOpaque(true);
            columns[i].setBorder(blackBorder);
            this.add(columns[i]);
        }
    }

    /**
     * set the content and the style of the grid
     * 
     * @param the   text that shows up on the grid
     * @param the   position
     * @param color
     */
    public void setText(String text, int pos, Color c) {
        this.columns[pos].setText(text);
        this.columns[pos].setBackground(c);
    }

    /**
     * set the JLanelcolumns
     * 
     * @param JLabel array
     * 
     */
    public void setColumns(JLabel[] columns) {
        this.columns = columns;
    }

    /**
     * get the JLanelcolumns
     * 
     * @return JLabel array
     * 
     */
    public JLabel[] getColumns() {
        return columns;
    }

}
