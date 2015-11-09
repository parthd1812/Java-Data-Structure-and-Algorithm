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

public class Frequency implements Comparator<Word> {

	public int compare(Word o1, Word o2) {
		// TODO Auto-generated method stub
		// return result of comparing frequency of two words
		return o2.getFrequency()-o1.getFrequency();
	}

}