// Name: Xinang Li
// USC NetID: 4226-5855-53
// CS 455 PA4
// Fall 2019
import java.util.*;

public class ScoreTable {
	private int[] scores = new int[] {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private Map<String, Integer> map;
	
	private static final char LOWER_A = 'a';
	private static final char LOWER_Z = 'z';
	private static final char UPPER_A = 'A';
	private static final char UPPER_Z = 'Z';
	
	/**
		The constructor of the ScoreTable class computes the score for each word.
	*/
	
	public ScoreTable(List<String> wordlist) {
		map = new HashMap<>();
		for (String word: wordlist) {
			char[] chars = word.toCharArray();
			int score = 0;
			for (Character letter: chars) {
				if (letter >= UPPER_A && letter <= UPPER_Z) {
					score += scores[letter - UPPER_A];
				}
				else if (letter >= LOWER_A && letter <= LOWER_Z) {
					score += scores[letter - LOWER_A];
				}
			}
			map.put(word, score);
		}
	}

	public Map<String, Integer> getMap() {
		return this.map;
	}
}