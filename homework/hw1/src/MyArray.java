/**********************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework 1 My Array implementation
 *
 * Andrew ID: mizhang	
 * Name:  Mi Zhang
 * 
 **********************************************************/
public class MyArray{
	private String[] text;
	private int numOfWords;
	
	public MyArray(int capacity){
		text = new String[capacity];
		numOfWords=0;
	}
	
	//helper function, add an element to text
	private void append(String str){
		text[numOfWords]=str;
		numOfWords++;
	}
	
	private void checkCapacity(){
		if ( numOfWords+1 <= text.length){
			return;
		}
		int newCapacity;
		// if there is no word in the text, initialize capacity as 1
		if(text.length==0){		
			newCapacity=1;
		}else{
			newCapacity=text.length*2;
		}
		//update the data from oldtext[] to newtext[]
		String[] newtext = new String[newCapacity];
		System.arraycopy(text, 0, newtext, 0, numOfWords);		
		text=newtext;	
	}
	
	//check whether a word is a sequence of letters [a..z]
	//private helper function
	

	
	public void add(String str){
		if(str == null){
			return;
		}
		if(!isWord(str) || str.isEmpty()){	
			return;
		}
		checkCapacity();
		append(str);
		return;
	}

	private boolean isWord(String str){	
		char c;
		for(int i=0;i< str.length();i++){
			c = str.charAt(i);
			if( c == '_' || c == ' ')
				return false;
			if( c <='9' && c >='0')
				return false;
			if ((c <='z' && c >='a') ||  (c<='Z' && c >= 'A'))
				continue;
			else
				return false;
		}
		return true;
	}
	
	public boolean search(String keyword){
		for (int i=0;i<numOfWords;i++){
			if (text[i].equals(keyword)){
				return true;
			}
		}
		return false;
	}
	
	public void display(){
		for(int i=0;i<numOfWords-1;i++){
			 System.out.print(text[i]);
			 System.out.print(' ');
		 }
		System.out.println(text[numOfWords-1]);
	}
	
	private boolean isFind(String word, String[] newString){
		for(int i=0; i < newString.length;i++){
			//the rest of newString are all null
			if(newString[i] == null)
				return false;
			else{ 
				if(newString[i].equals(word))
					return true;
			}
		}
		//does not find word in newString, then return false
		return false;
	}
	
	public void removeDups(){
		String[] newString = new String[numOfWords]; 
		int count = 0;
		for(int i=0;i< numOfWords ;i++){
			if(!isFind(text[i],newString)){
				newString[count] = text[i];
				count++;
			}
		}
		text = new String[text.length];
		numOfWords=count;
		System.arraycopy(newString, 0, text, 0,numOfWords);	
	}
		
	public int size(){
		return numOfWords;
	}
	
	public int getCapacity(){
		return  text.length;
	}
}