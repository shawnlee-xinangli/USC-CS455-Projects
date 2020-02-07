// Name: Xinang Li
// USC NetID: 4226-5855-53
// CS 455 PA4
// Fall 2019

import java.util.*;
import java.io.*;
public class WordFinder {
	private static final String default_file = "sowpods.txt";
	
	/**
	 	The main method prompts user to input.
	 	And it also processes user's input and print results.
	  	@param args the dictionary that we use.
	*/
	public static void main(String[] args) {
		AnagramDictionary anagramDic = null;
		try {
			if (args.length == 0) {
				anagramDic = new AnagramDictionary(default_file);
			}
			else {
				anagramDic = new AnagramDictionary(args[0]);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File does not exist!");
		}

		System.out.println("Type . to quit.");
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Rack? ");
			String letters = in.nextLine();
			if(letters.equals(".")) {
				break;
			}
			Rack rack = new Rack(letters);
			ArrayList<String> subsets = rack.getSubsets();
			ArrayList<String> results = new ArrayList<>();
			for(String s : subsets){
				if(anagramDic.getAnagramsOf(s) != null){
					results.addAll(anagramDic.getAnagramsOf(s));
				}
			}
			ScoreTable table = new ScoreTable(results);
			printResult(table,letters);
		}	
	}

	/**
      After we get the subsets, 
      we put them into the scoretable to get the scores and sort them based on the scores.
      Note: The Treeset can be constructed by a comparator.
      @param table  the scoretable constructed by all the subset strings
      @param letters  the rack that the user typed in
    */

	public static void printResult(ScoreTable table, String letters) {
		Map<String, Integer> map = table.getMap();
		TreeSet<Map.Entry<String,Integer>> set = new TreeSet<>(new sortByScore());
		set.addAll(map.entrySet());
		System.out.println("We can make " +set.size()+ " words from " + '"' + letters + '"');
		if (set.size() == 0) {
			return;
		}
		System.out.println("All of the words with their scores (sorted by score):");
		for (Map.Entry<String,Integer> entry: set) {
			System.out.println(entry.getValue()+ ": " +entry.getKey());
		}
	}
}
	/**
      Create a comparator class
      First we compare the score(value) of each word.
      If the scores are the same, we compare the word(key) in its natural order. 
    */
class sortByScore implements Comparator<Map.Entry<String,Integer>> {
	public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
		if (e1.getValue() != e2.getValue()) {
			return e2.getValue() - e1.getValue();
		}
		// If the scores are the same, we will sort them by key.
		return e1.getKey().compareTo(e2.getKey());
	}
}