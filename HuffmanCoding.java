/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */

import java.util.*;
public class HuffmanCoding {
	/**
	 * This would be a good place to compute and store the tree.
	 */
	private HuffmanNode root;
	private HashMap<Character, Integer> frequencyMap = new HashMap<>();
	private HashMap<Character, String> huffmanCodeSheet = new HashMap<>();

	//I utilised this website to help
	//https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
	// Class to represent huffman tree
// Class representing a node in the Huffman Tree

	private static class HuffmanNode implements Comparable<HuffmanNode> {
		char data;
		int frequency;
		HuffmanNode left, right;
		HuffmanNode(char data, int frequency) {
			this.data = data;
			this.frequency = frequency;
			left = right = null;
		}

		@Override
		public int compareTo(HuffmanNode other) {
			if (this.frequency < other.frequency) {
				return -1;
			}
			if (this.frequency > other.frequency) {
				return 1;
			}
			if (this.frequency == other.frequency) {
				return Character.compare(this.data, other.data);
			}
			return 0;
		}
	}

	public HuffmanCoding(String text) {
		for (char c : text.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}
		// Create a priority queue
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();


		// Create a Huffman node
		for (char c : frequencyMap.keySet()) {
			priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
		}
		// Build the Huffman Tree
		while (priorityQueue.size() > 1) {
			// Remove the two nodes with the lowest frequency
			HuffmanNode left = priorityQueue.poll();
			HuffmanNode right = priorityQueue.poll();
			// Create a new internal node and add it back to the queue
			HuffmanNode newNode =
					new HuffmanNode('$', left.frequency + right.frequency);
			newNode.left = left;
			newNode.right = right;
			priorityQueue.add(newNode);
		}

		// The remaining node is the root of the Huffman Tree
		this.root = priorityQueue.poll();
		//test section
		StringBuilder code = new StringBuilder();
		printCodes(root,code);
	}
	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text){
		StringBuilder myString = new StringBuilder();
		for(char c:text.toCharArray()){
			System.out.println(c);
			myString.append(huffmanCodeSheet.get(c));

		}


		return String.valueOf(myString);
	}

	//from Geeks for Geeks ,slightly modified
	public void printCodes(HuffmanNode root, StringBuilder code) {
		if (root == null) return;

		if (root.data != '$') {
			System.out.println(root.data + ": " + code);
			huffmanCodeSheet.put(root.data, String.valueOf(code));
		}

		if (root.left != null) {
			printCodes(root.left, code.append('0'));
			code.deleteCharAt(code.length() - 1);
		}

		if (root.right != null) {
			printCodes(root.right, code.append('1'));
			code.deleteCharAt(code.length() - 1);
		}
	}


	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		if (root == null) return "";
		StringBuilder code = new StringBuilder();
		HuffmanNode myNode = root;

		for(char c:encoded.toCharArray()){
			if(c == '0'){
				myNode = myNode.left;
			}else{
				myNode = myNode.right;
				}
			if(myNode.left == null || myNode.right == null){
				code.append(myNode.data);
				myNode = root;
			}
		}
		return String.valueOf(code);
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
