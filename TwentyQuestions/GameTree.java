import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	private TreeNode root;
	private TreeNode curr;
	private String file;
	private class TreeNode {
		public TreeNode(String val) {
			this.val = val;
		}
		TreeNode left, right;
		String val;
	}
	public GameTree(String fileName) {
		try {
			file = fileName;
			File temp = new File(fileName);
			Scanner console = new Scanner(new File(fileName));
			GameTree(console, root, false);
			curr = root;
		}catch(Exception e){
			System.out.println("There is an error with the file");
		}
	}
	public void GameTree(Scanner console, TreeNode prev, boolean direction) {
		TreeNode temp;
		if(prev == null){
			root = new TreeNode(console.nextLine());
			temp = root;
		} else {
			 temp = new TreeNode(console.nextLine());
			if(direction)
				prev.left = temp;
			else
				prev.right = temp;
		}
		if(temp.val.endsWith("?")){
			GameTree(console, temp, true);
			GameTree(console, temp, false);
		}
	}


	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
		add(newQ, newA, root);
	}
	
	private void add(String newQ, String newA, TreeNode j) {
		if(j.left == null || j.right == null)
			return;
		if(j.left == curr){
			TreeNode temp = j.left;
			j.left = new TreeNode(newQ);
			j.left.left = new TreeNode(newA);
			j.left.right = temp;
		}
		if(j.right == curr){
			TreeNode temp = j.right;
			j.right = new TreeNode(newQ);
			j.right.left = new TreeNode(newA);
			j.right.right = temp;
		}
		else{
			add(newQ, newA, j.left);
			add(newQ, newA, j.right);
		}
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		return curr.left == null && curr.right == null && !curr.val.endsWith("?");
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent(){
		return curr.val;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		if(yesOrNo == Choice.Yes)
			curr = curr.left;
		else
			curr = curr.right;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		curr = root;
	}

	@Override
	public String toString(){
		return print(root, "", 0);
	}
	public String print(TreeNode cur, String c, int level) {
		if(cur.right != null)
			c = print(cur.right, c, level + 1);
		String temp = "";
		for(int i = 0; i < level; i++)
			temp += "- ";
		c += temp + cur.val + "\n";
		if(cur.left != null)
			c = print(cur.left, c, level + 1);
		return c;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame(){
		if(file.length() > 0) {
			try {
				System.out.println(file);
				PrintWriter writer = new PrintWriter(file);
				saveGame(root, writer);
				writer.close();
			} catch (Exception e) {}
		}
	}
	private void saveGame(TreeNode j, PrintWriter writer) {
		writer.write(j.val + "\n");
		if (j.right != null)
			saveGame(j.right, writer);
		if (j.left != null)
			saveGame(j.left, writer);
	}
}