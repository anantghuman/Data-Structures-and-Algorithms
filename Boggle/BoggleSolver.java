import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver
{
	HashSet<String> dictionary = new HashSet<>();
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		File dict = new File(dictionaryName);
		Scanner console = null;
		try {
			console = new Scanner(dict);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		while(console.hasNext()){
			dictionary.add(console.next());
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		HashSet<String> validWords = new HashSet<>();
		for(int r = 0; r < board.rows(); r++)
			for(int c = 0; c < board.cols(); c++) {
				boolean[][] used = new boolean[board.rows()][board.cols()];
				getAllValidWords(r, c, "", board, used, validWords);
			}
		return validWords;
	}

	private void getAllValidWords(int r, int c, String soFar, BoggleBoard board, boolean[][] used, HashSet<String> validWords){
		used[r][c] = true;
		if(board.getLetter(r,c) == ('Q'))
			soFar += "QU";
		else
			soFar += board.getLetter(r,c);
		if(dictionary.contains(soFar)){
			validWords.add(soFar);
		}
		int[] x = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] y = {0, 1, 1, 1, 0, -1, -1, -1};
		for(int i = 0; i < x.length; i++) {
			if (r + x[i] < 0 || c + y[i] < 0 || r + x[i] >= board.rows() || c + y[i] >= board.cols())
				continue;
			else if(!used[r+x[i]][c+y[i]]){
				getAllValidWords(r + x[i], c + y[i], soFar, board, used, validWords);
			}
		}
		used[r][c] = false;
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		switch(word.length()){
			case 0,1,2:
				return 0;
			case 3,4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			default:
				return 11;
		}
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
