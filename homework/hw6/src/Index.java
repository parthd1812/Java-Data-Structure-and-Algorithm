/*****************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 *
 * Andrew ID: mizhang
 * Name: Mi Zhang
 *
 *****************************************************/
import java.io.*;
import java.util.*;

public class Index {
    private BST<Word> mybst;
    
    public Index(){
    	mybst = new BST<Word>();
    }
    
	/**
	 * Build a tree giving a file name
	 *
	 * @param fileName
	 *            - input file name
	 * @return BST object
	 */
	public BST<Word> buildIndex(String fileName) {
		Integer numOfLines=0;
		Scanner scanner = null;
		File file = new File(fileName);
		if(fileName!=null){
			try{
				scanner = new Scanner(file);
				while(scanner.hasNextLine()){
					// update the line index
					numOfLines++;
					String line = scanner.nextLine();
					// read words form file line by line and split it 			
					String[] wordsFromText = line.split("\\W");
					for(String oneWord: wordsFromText){
						if(this.isWord(oneWord)){
							Word word = new Word(oneWord,numOfLines,1);
							Word find = mybst.search(word);
							if(find!=null){
								// if word is already exist in BST, update index 
								find.addIndex(numOfLines);
								find.setFrequency(find.getFrequency()+1);
							}else{
								// if not find ,add a new word into mybst
								mybst.insert(word);
							}
						}
					}
				}	
			}catch (FileNotFoundException e){
				System.err.println("Cannot find the file");
			} finally {
				// close the file
				if(scanner!=null)
					scanner.close();
			}
		}
		return mybst;	
	}

	/**
	 * Build a tree with a file name and comparator object
	 *
	 * @param fileName
	 *            - input file name
	 * @param comparator
	 *            - comparator to be used
	 * @return BST object
	 */
	public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
		 mybst = new BST<Word>(comparator);
		 Integer numOfLines=0;
			Scanner scanner = null;
			File file = new File(fileName);
			if(fileName!=null){
				try{
					scanner = new Scanner(file);
					while(scanner.hasNextLine()){
						numOfLines++;
						String line = scanner.nextLine();
						// read words form file line by line and split it 				
						String[] wordsFromText = line.split("\\W");
						for(String oneWord: wordsFromText){
							if(this.isWord(oneWord)){
								// if comparator is IgnoreCase, transfer the word into lowercase
								if(comparator instanceof IgnoreCase){
									oneWord = oneWord.toLowerCase();
								}
								Word word = new Word(oneWord,numOfLines,1);
								Word find = mybst.search(word);
								if(find!=null){
									// if word is already exist in BST, update index 
									find.addIndex(numOfLines);
									find.setFrequency(find.getFrequency()+1);
								}else{
									// if not find ,add a new word into mybst
									mybst.insert(word);
								}
							}
						}
					}	
				}catch (FileNotFoundException e){
					System.err.println("Cannot find the file");
				} finally {
					// close the file
					if(scanner!=null)
						scanner.close();
				}
			}
			return mybst;		 
	}
		 

	/**
	 * Build a tree with a given list and comparator
	 *
	 * @param list
	 *            - ArrayList of words
	 * @param comparator
	 *            - comparator to be used
	 * @return BST object
	 */
	public BST<Word> buildIndex(ArrayList<Word> list,
			Comparator<Word> comparator) {
		if(list == null || list.isEmpty()){
			return null;
		}else{
			mybst = new BST<Word>(comparator);
			for(Word word:list){
				// if comparator is IgnoreCase, transfer the word into lowercase
				if(mybst.comparator() instanceof IgnoreCase){
					word.setWord(word.getWord().toLowerCase());
				}
				mybst.insert(word);
			}
			return mybst;
		}
	}

	/**
	 * Sort words alphabetically
	 * Note: Should keep the state of the tree
	 *
	 * @param tree
	 *            - BST tree
	 * @return ArrayList of words sorted alphabetically
	 */
	public ArrayList<Word> sortByAlpha(BST<Word> tree) {
		ArrayList<Word> myArrayList = new ArrayList<Word>(tree.getNumberOfNodes());
		for(Word word : tree){
			myArrayList.add(word);
		}
		Collections.sort(myArrayList);
		return myArrayList;
	}

	/**
	 * Sort words by frequency
	 * Note: Should keep the state of the tree
	 *
	 * @param tree
	 *            - BST tree
	 * @return ArrayList of words sorted by frequency
	 */
	public ArrayList<Word> sortByFrequency(BST<Word> tree) {
		ArrayList<Word> myArrayList = new ArrayList<Word>(tree.getNumberOfNodes());
		for(Word word : tree){
			myArrayList.add(word);
		}
		Collections.sort(myArrayList,new Frequency());
		return myArrayList;
	}

	/**
	 * Find all words of the highest frequency
	 * Note: Should keep the state of the tree
	 *
	 * @param tree
	 *            - BST tree
	 * @return ArrayList of words that have highest frequency
	 */
	public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
		// at first, sort the tree by frequency
		ArrayList<Word> myArrayList = this.sortByFrequency(tree);
		// get the highest frequency
		int highest=  myArrayList.get(0).getFrequency();
		ArrayList<Word> highestWord = new ArrayList<Word>(1);
		for (Word word : myArrayList){
			// add word in to list if they have same frequency
			if(word.getFrequency()==highest){
				highestWord.add(word);
			}else{
				break;
			}
		}
		return highestWord;
	}
    
    // test whether a word is legal. 	
	private boolean isWord(String word){
		if(word!=null && word.contains("_")==false){
			return word.matches("[a-zA-Z]+");
		}else{
			return false;
		}
	}
	
	public static void main(String[] args){
		Word word1 = new Word("s",1,1);
		Word word2 = new Word("s",1,1);
//		Word word2=word1;
		System.out.println(word1.compareTo(word2));
	}
}