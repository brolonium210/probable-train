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

	//I utilised this website to help
	//https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
	// Class to represent huffman tree
// Class representing a node in the Huffman Tree

	private static class HuffmanNode implements Comparable<HuffmanNode> {
		// Character data
		char data;
		// Frequency of the character
		int frequency;
		// Left and right child nodes
		HuffmanNode left, right;
		// Constructor to initialize the node
		HuffmanNode(char data, int frequency) {
			this.data = data;
			this.frequency = frequency;
			left = right = null;
		}
		@Override
		public int compareTo(HuffmanNode other) {
			return Integer.compare(this.frequency, other.frequency);
		}
	}

	public HuffmanCoding(String text) {
		for (char c : text.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}
		// Create a priority queue to build the Huffman Tree
		PriorityQueue<HuffmanNode> priorityQueue =new PriorityQueue<>();
		// Create a Huffman node for each character and add it to the priority queue
		for (char c : frequencyMap.keySet()) {
			priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
		}
		// Build the Huffman Tree
		while (priorityQueue.size() > 1) {
			// Remove the two nodes with the lowest frequency
			HuffmanNode left = priorityQueue.poll();
			HuffmanNode right = priorityQueue.poll();
			// Create a new internal node with these two nodes
			// as children and add it back to the queue
			HuffmanNode newNode =
					new HuffmanNode('$', left.frequency + right.frequency);
			newNode.left = left;
			newNode.right = right;
			priorityQueue.add(newNode);
		}

		// The remaining node is the root of the Huffman Tree
		this.root = priorityQueue.poll();
	}
	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encodeHelper(String text) {
		return encodeHelper(root,text);
	}
	public String encodeHelper(HuffmanNode root,String text){
		String code = new String();
		if (root == null) return "";

		// If this is a leaf node, print the character and its code
//		if (root.data != '$') {
//			System.out.println(root.data + ": " + code);
//		}

		// Traverse the left subtree
		if (root.left != null) {
			this.encodeHelper(root.left, code+"0");
//			code.deleteCharAt(code.length() - 1);
		}

		// Traverse the right subtree
		if (root.right != null) {
			this.encodeHelper(root.right, code+"1");
//			code.deleteCharAt(code.length() - 1);
		}
		return code;
	}


	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		return "";
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
