import java.util.*;

public class BoyerMoore{

	record BadTuple(char c,int lastMismatch){}

	/**
	 *
	 * @param myBad
	 * @param mismatchChar
	 * @return Find last mismatch index or -1.
	 *
	 */
	public static int badHelper(List<BadTuple> myBad, char mismatchChar) {
		int lastOccurrence = -1;  //if Fails
		for (BadTuple tuple : myBad) {
			if (tuple.c() == mismatchChar) {
				return tuple.lastMismatch();
			}
		}
		return lastOccurrence;
	}

	/**
	 * Searches for a pattern in the text using the bad char heuristic.
	 *
	 * @param pattern the substring to search for
	 * @param text the text to search
	 * @return the index of the first match, or -1 if no match
	 */

	public static int search(String pattern, String text) {
		// TODO fill this in.
		List<BadTuple>myBad = new ArrayList<>();

		for (int j = 0; j < pattern.length(); j++) {
			BadTuple temp = new BadTuple(pattern.charAt(j), j);
			myBad.add(temp);
		}

		int tLen = text.length();
		int pLen = pattern.length();
		int i = 0;

		while (i <= tLen - pLen) {
			int j = pLen - 1;
			while (j >= 0 && pattern.charAt(j) == text.charAt(i + j)) {
				j--;
			}
			if (j < 0) {
				return i;
			} else {
				char mismatchChar = text.charAt(i + j);
				int lastOccurrence = badHelper(myBad, mismatchChar);
				int skip = Math.max(1, j - lastOccurrence);
				i += skip;
			}
		}

		return -1;
	}

}
