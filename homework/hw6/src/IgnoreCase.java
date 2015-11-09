/*****************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 *
 * Andrew ID: mizhang
 * Name: Mi Zhang
 *
 *****************************************************/
import java.util.*;

public class IgnoreCase implements Comparator<Word> {

	public int compare(Word o1, Word o2) {
		// TODO Auto-generated method stub
		// compare the frequency of two words in lowercase
		String w1 = o1.getWord();
		String w2 = o2.getWord();
		return w1.toLowerCase().compareTo(w2.toLowerCase());
	}

}