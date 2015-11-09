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

public class AlphaFreq implements Comparator<Word> {

	public int compare(Word o1, Word o2) {
		// TODO Auto-generated method stub
		// compare the word first, if there is a tie, then compare 
		// frequency of two word and return it
		int diff = o1.compareTo(o2);
		if(diff!=0)
			return diff;
		else
			return o1.getFrequency()-o2.getFrequency();
	}

}