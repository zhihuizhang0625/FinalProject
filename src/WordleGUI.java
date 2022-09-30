import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Zhihui Zhang
 *
 */ 

public class WordleGUI implements ActionListener {
	private WordGuesser w;
	private JFrame frame;
	private PlayerPanel playerPanel;
	private ListPanel listPanel;
	private GUIPanel[] grid = new GUIPanel[6];
	private String wordleString;
	private int count = -1;
	private ArrayList<String> wordlist = new ArrayList<>();
	private Collection<Character> notInWord = new ArrayList<>();
	private Map<Integer, Character> guessedCorrectly = new HashMap<>();

	/**
	 * initiates the WordleGUI and integrates with other GUI Panels
	 * 
	 * @param WordGuesser w
	 */
	public WordleGUI(WordGuesser w) {
		// set up word guesser
		this.w = w;

		// initialize generator
		Generator g = new Generator(w);

		// set up game frame and the basic design
		frame = new JFrame("Wordle Game");
		frame.setLayout(new GridLayout(8, 1));
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set up grid on frame
		for (int i = 0; i < 6; i++) {
			grid[i] = new GUIPanel();
			frame.add(grid[i]);
		}

		// set up list panel for recommendation list
		listPanel = new ListPanel();
		frame.add(listPanel);

		// set up player panel for user input
		playerPanel = new PlayerPanel();
		playerPanel.getButton().addActionListener(this);
		frame.add(playerPanel);

		// use generator to generate random word as answer key
		wordlist = g.loadDict("wiktionary.txt");
		wordleString = g.generateWord(wordlist).toUpperCase();
		System.out.println("Word for the day : " + wordleString);

		// first round of recommendation
		// recommend 10 words with the highest weight
		List<String> words = w.getTenMostCommon();

		int countnum = 1;
		String s = "";
		for (String word : words) {
			if (countnum == words.size()) {
				s += word;
			} else {
				s += (word + ", ");
				countnum++;
			}

		}
		// show the recommendation on list panel
		listPanel.getRecommended().setText(s);
		frame.revalidate();
	}

	/**
	 * get the grid panel
	 * 
	 * @return GUIPanel
	 */
	public GUIPanel getPanel() {
		return grid[count];
	}

	/**
	 * get the grid panel
	 * 
	 * @return boolean: whether the input word matches the target word
	 */
	private boolean EqualsTo(String userWord) {
		Map<Integer, Character> inWordButNotSpot = new HashMap<>();
		List<String> target = Arrays.asList(wordleString.split(""));

		// search through the word
		HashSet<Boolean> isLetter = new HashSet<>();
		for (int i = 0; i < userWord.length(); i++) {
			if (!Character.isLetter(userWord.charAt(i))) {
				isLetter.add(false);
			}
		}

		// error handling
		if (userWord.length() != 5) {
			JOptionPane.showMessageDialog(null, "Please put in a word with correct length", "Congrats",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (isLetter.contains(false)) {
			JOptionPane.showMessageDialog(null, "Please put in a word with valid characters", "Congrats",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		String[] inputArray = userWord.split("");
		List<Boolean> booleanlist = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			Character c = Character.toLowerCase(inputArray[i].charAt(0));
			if (target.contains(inputArray[i])) {
				// if the guessed character is correct -- green
				if (target.get(i).equals(inputArray[i])) {
					guessedCorrectly.put(i, c);
					getPanel().setText(inputArray[i], i, Color.GREEN);
					booleanlist.add(true);
				} else {
					// if the character is in the word but not at the right spot
					inWordButNotSpot.put(i, c);
					getPanel().setText(inputArray[i], i, Color.YELLOW);
					booleanlist.add(false);
				}
			} else {
				// if the character is not in the list
				notInWord.add(c);
				getPanel().setText(inputArray[i], i, Color.GRAY);
				booleanlist.add(false);
			}
		}
		// update feedback to help recommend word
		w.updateWord(notInWord, inWordButNotSpot, guessedCorrectly);
		return !booleanlist.contains(false);
	}

	/**
	 * send messages through window when action performed
	 * 
	 * @param ActionEvent
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// check if userWord is in the right format
		count++;
		String userWord = this.playerPanel.getInput().getText();
		HashSet<Boolean> isLetter = new HashSet<>();
		for (int i = 0; i < userWord.length(); i++) {
			if (!Character.isLetter(userWord.charAt(i))) {
				isLetter.add(false);
			}
		}

		// if userWord has an invalid length
		if (userWord.length() != 5) {
			count--;
			JOptionPane.showMessageDialog(null, "Please put in a word with correct length", "Oops",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (isLetter.contains(false)) {
			// if userWord contains elements other than characters
			count--;
			JOptionPane.showMessageDialog(null, "Please put in a word with valid characters", "Oops",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (!wordlist.contains(userWord)) {
			// if userWord is not in our wiktionary.txt
			count--;
			JOptionPane.showMessageDialog(null, "Word not in dictionary", "Oops", JOptionPane.INFORMATION_MESSAGE);
		} else if (userWord.length() == 5) {
			// if userWord is exactly the answer
			if (EqualsTo(userWord.trim().toUpperCase())) {
				JOptionPane.showMessageDialog(null, "You Win!!! :)", "Congrats", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				return;
			} else {
				// if userWord is not exactly the answer but is in valid format
				List<String> words = w.guessWords(10);
				int num = 1;
				String s = "";
				for (String word : words) { 
					if (num == words.size()) {
						s += word;
					} else {
						s += (word + ", ");
						num++;
					}

				}
				listPanel.getRecommended().setText(s);
			}
		} if (count == 5) {
			// if user run out of chances
			JOptionPane.showMessageDialog(null, "You Lost. Try again next time :(", "Oops",
					JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			return;
		} 
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.buildTrie("wiktionary.txt");
		WordGuesser w = new WordGuesser(t);
		WordleGUI gui = new WordleGUI(w);
	}
}
