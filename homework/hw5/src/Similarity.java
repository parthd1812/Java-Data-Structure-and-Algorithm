import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/***********************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework Assignment 5 Document Similarity
 *
 * Andrew ID: mizhang
 * Name: Mi Zhang
 *
 ***********************************************************/

public class Similarity {
	// TODO implement this class. You can use Java Collections Framework
	private int numOfWords;
	private int numOfWordsNoDups;
	private int numberOfLines;
	private HashMap<String,Integer> myHashmap = new HashMap<String,Integer>(10,0.65f);
	
    public Similarity(){
    	this.numOfWords=0;
    	this.numOfWordsNoDups=0;
    	this.numberOfLines=0;
    }
    
    public Similarity(String input){
    	if(input!=null){
    		// split input into different line and update number of lines
	    	String[] newinput = input.split("\n");
	    	numberOfLines=newinput.length;
	    	numOfWordsNoDups=0;
        	numOfWords=0;
        	// split each word in input string 
        	newinput = input.split("\\W");
	    	for(String word : newinput){
	    		if(isWord(word)){
	    			word = word.toLowerCase();
	    			numOfWords++;
	    			if(myHashmap.containsKey(word)){
	    				// if hashmap contains this word, just update frequency
	    				int frequency = myHashmap.get(word);
	    				myHashmap.put(word,frequency+1);
	    			}else{
	    				// hashmap does not contain this word, add it into hashmap
	    				myHashmap.put(word,1);
	    				numOfWordsNoDups++;
	    			}
	    		}
	    	}	    	       	
    	}else{
    		// input is null, set all parameter as 0
    		numOfWords=0;
        	numOfWordsNoDups=0;
        	numberOfLines=0;
        	return;
    	}
    }

	public Similarity(File file) {
		// TODO Auto-generated constructor stub
		Scanner scanner = null;
		numberOfLines=0;
		numOfWords=0;
		numOfWordsNoDups=0;
		if(file==null){
			return;
		}
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				//scan next line and update number of lines
				String line = scanner.nextLine();
				numberOfLines++;
				String[] wordsFromText = line.split("\\W");
				// add each word in the newline into hashmap
				for (String word : wordsFromText){
					if(isWord(word)){
						numOfWords++;
						word = word.toLowerCase();
						if(myHashmap.containsKey(word)){
							// if hashmap contains this word, just update frequency
							Integer tmpfre = myHashmap.get(word);
							myHashmap.put(word,tmpfre+1);
						}else{
							// hashmap does not contain this word, add it into hashmap
							numOfWordsNoDups++;
							myHashmap.put(word,1);
						}	
					}
				}
			}
		} catch (FileNotFoundException e) {
			// if could not find this file
			System.err.println("Cannot find the file");
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	public int numOfWords() {
		// TODO Auto-generated method stub
		return numOfWords;
	}

	public int numOfWordsNoDups() {
		// TODO Auto-generated method stub
		return numOfWordsNoDups;
	}

	public double euclideanNorm() {
		// TODO Auto-generated method stub
		double distance=0;
		for(String word: myHashmap.keySet()){
			distance+=Math.pow(myHashmap.get(word),2);
		}
		return Math.sqrt(distance);
	}

	public HashMap getMap() {
		// TODO Auto-generated method stub
		return myHashmap;
	}

	public double dotProduct(HashMap<String,Integer> map) {
		// TODO Auto-generated method stub
		if(map==null){
			return 0;
		}
		double dotProduct=0;
		for(String word : this.myHashmap.keySet()){
			//find whether a word is in map
			if(map.containsKey(word)){
				// if this word exists in map, update the dotProduct
				Integer wordFre= map.get(word);
				dotProduct+=(wordFre*this.myHashmap.get(word));
			}
		}
		return dotProduct;
	}

	public double distance(HashMap<String,Integer> map) {
		// TODO Auto-generated method stub
		// if input is null, just return PI/2
		if(map==null){
			return Math.PI*0.5;
		}
		double distance=0;
		double dotProduct = this.dotProduct(map);
	
		//calculate the euclideanNorm of two map
		double euclideanMyHashMap = this.euclideanNorm();
		double euclideanMap =0;
		for(String word: map.keySet()){
			euclideanMap+=Math.pow(map.get(word),2);
		}
		euclideanMap=Math.sqrt(euclideanMap);

		if(euclideanMyHashMap!=0 && euclideanMap!=0){
			distance=dotProduct/euclideanMyHashMap/euclideanMap;
			// if two map is not identical
			if(euclideanMyHashMap!=euclideanMap)
				return Math.acos(distance);
			else
				return 0;// two map is identical, return 0
		}
		else{
			// if either euclieanNorm is 0, means one map contain no word
			// then return PI/2
			return Math.PI*0.5;
		}
	}

	public int numberOfLines() {
		// TODO Auto-generated method stub
		return this.numberOfLines;
	}
	
	private boolean isWord(String text){
		if(text==null || text == " "){
			return false;
		}else{
			return text.matches("[a-zA-Z]+");
		}
	}	
}








