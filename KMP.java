import java.util.ArrayList;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	//I used the following site to help with this
	//https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

	static void constructLps(String pat, int[] lps) {
		// len stores the length of longest prefix which
		// is also a suffix for the previous index
		int len = 0;
		// lps[0] is always 0
		lps[0] = 0;
		int i = 1;
		while (i < pat.length()) {
			// If characters match, increment the size of lps
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			}
			// If there is a mismatch
			else {
				if (len != 0) {
					// Update len to the previous lps value
					// to avoid redundant comparisons
					len = lps[len - 1];
				}
				else {
					// If no matching prefix found, set lps[i] to 0
					lps[i] = 0;
					i++;
				}
			}
		}
	}

	public static int search(String pattern, String text) {
		int n = text.length();
		int m = pattern.length();

		int[] lps = new int[m];
		ArrayList<Integer> res = new ArrayList<>();

		constructLps(pattern, lps);

		// Pointers i and j, for traversing
		// the text and pattern
		int i = 0;
		int j = 0;

		while (i < n) {
			// If characters match, move both pointers forward
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;

				// If the entire pattern is matched
				// store the start index in result
				if (j == m) {
					res.add(i - j);

					// Use LPS of previous index to
					// skip unnecessary comparisons
					j = lps[j - 1];
				}
			}
			// If there is a mismatch
			else {

				// Use lps value of previous index
				// to avoid redundant comparisons
				if (j != 0)
					j = lps[j - 1];
				else
					i++;
			}
		}
		if(res.isEmpty()){
			return -1;
		}else{
			return res.getFirst();
		}
	}
}
