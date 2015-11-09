/*****************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 *
 * Andrew ID: mizhang
 * Name:  Mi Zhang
 *
 *****************************************************/
import java.util.*;

public class Word implements Comparable<Word> {

	private String word;
	private Set<Integer> index=new HashSet<Integer>(); // word's line number in the source text
	private int frequency; // counts occurrences of the word
	
	public Word(){
		word = null;
		frequency = 0;
	}
	
	// constructor with one parameter word
	public Word(String input){
		this.word = input;
		this.frequency=1;
	}
	
	// constructor with parameter word, index and frequency
	public Word(String input,Integer index,int frequency){
		this.word = input;
	    this.index.add(index);
	    this.frequency=frequency;
	}
	
	// constructor with parameter word, index(set) and frequency
	public Word(String input, Set<Integer> index, int frequency){
		this.word = input;
		this.index.addAll(index);
		this.frequency=frequency;
	}
	
	public int compareTo(Word input) {	
		return this.word.compareTo(input.getWord());	
	}
	
	public String getWord(){
		return word;
	}
	
	public void setWord(String word){
		this.word = word;
	}
	
	public Set<Integer> getIndex(){
		return this.index;
	}
	
	// add new index into set and update the frequency
	public void addIndex(Integer index){
		this.index.add(index);
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}
	
	@Override
	public String toString(){
		String result = this.word+" "+this.getFrequency()+" "+this.getIndex();
		return result;
	}
	
	
}