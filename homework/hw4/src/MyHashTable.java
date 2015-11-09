/***********************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework 4 HashTable Implementation with linear probing
 *
 * Andrew ID: mizhang
 * Name:  Mi Zhang
 *
 ***********************************************************/

public class MyHashTable implements MyHTInterface {
	private static final int DEFAULT_CAPACITY = 10;
	private static final double DEFAULT_LOAD_FACTOR = 0.5;
	private static final DataItem DELETED = new DataItem(null,0);
    private DataItem[] myhashtable;
    private double loadFactor;
    private int numOfItem;
    private int tableLength;
    private int numOfCollsion;
    
	// TODO implement constructor with no initial capacity
    public MyHashTable(){
    	myhashtable=new DataItem[DEFAULT_CAPACITY];
    	tableLength=DEFAULT_CAPACITY;
    	numOfItem=0;
    	loadFactor=0;
    	numOfCollsion=0;
    }
    
	// TODO implement constructor with initial capacity
    public MyHashTable(int size){
    	if(size<=0){
    		throw new IllegalArgumentException(
    				"Size is less or equal to zeros");
    	}else{
    		myhashtable=new DataItem[size];
    		tableLength=size;
    		loadFactor=0;
    		numOfItem=0;
    		numOfCollsion=0;
    	}
    } 
	/**
	 * Inserts a new String value (word).
	 * Frequency of each word be stored too.
	 *
	 * @param value
	 *            String value to be added.
	 */
	@Override
	public void insert(String value) {
		if(isWord(value)){
			value = value.toLowerCase();
			int hashValue =hashValue(value);
			DataItem newItem = new DataItem(value,1);
			boolean collision=true;
			if(myhashtable[hashValue]!=null 
					&& myhashtable[hashValue]!=DELETED 
					&& myhashtable[hashValue].value!=value){
				if(hashFunc(myhashtable[hashValue].value)!=hashValue)
					collision = false;
				//find proper loaction to insert
				while(myhashtable[hashValue]!=null && 
						myhashtable[hashValue]!=DELETED && 
						myhashtable[hashValue].value!=value){
					hashValue++;
					hashValue=hashValue%tableLength;
				}
				
				if(myhashtable[hashValue]==null || myhashtable[hashValue]==DELETED){
					// if collsion occurs, update the number of collsion
					if(collision!=false)
						numOfCollsion++;
				}				
			}			
			if(myhashtable[hashValue]==null || myhashtable[hashValue]==DELETED){
				myhashtable[hashValue]=newItem;
				numOfItem++;
			}else if(myhashtable[hashValue].value==value){
				myhashtable[hashValue].addFrequency();
			}
			//update load factor
			loadFactor =(double)numOfItem/tableLength;
			// if the loadFacotor is large than Default value, then rehash the table
			if(loadFactor>DEFAULT_LOAD_FACTOR)
				this.rehash();
		}
	}

	/**
	 * Returns the size, number of items, of the table
	 *
	 * @return the number of items in the table.
	 */
	@Override
	public int size() {
		// TODO implement this
		return numOfItem;
	}

	/**
	 * Displays the values of the table
	 * If an index is empty, it shows **
	 * If previously existed item is deleted, then it should show #DEL#
	 */
	@Override
	public void display() {
		// TODO implement this
		for(int i = 0;i<tableLength-1;i++){
			if(myhashtable[i]!=null){
				if(myhashtable[i]!=DELETED){
					System.out.print("["+myhashtable[i].value+", "+myhashtable[i].frequency+"]");
					System.out.print(" ");
				}else{
					System.out.print("#DEL# ");
				}
			}
			else{
				System.out.print("** ");
			}
		}
		//print the last element 
		if(myhashtable[tableLength-1]==null){
			System.out.println("**");
		}else if(myhashtable[tableLength-1]==DELETED){
			System.out.println("#DEL#");
		}else{
			System.out.println("["+myhashtable[tableLength-1].value+" "+myhashtable[tableLength-1].frequency+"]");
		}		
	}

	/**
	 * Returns true if value is contained in the table
	 *
	 * @param key
	 *            String key value to be searched
	 * @return true if found, false if not found.
	 */
	@Override
	public boolean contains(String key) {
		// TODO implement this
		// if input key is null or key is not a word, return false
		if(!isWord(key))
			return false;
		int hashCode = hashFunc(key);
		if(hashCode<0){
			return false;
		}
		while(myhashtable[hashCode]!=null){
			    // if key has been found, then return true
				if(key.equals(myhashtable[hashCode].value))
					return true;
				hashCode++;
				hashCode=hashCode%tableLength;
		}
		//not found key
		return false;
	}

	/**
	 * Returns the number of collisions in relation to insert and rehash.
	 *
	 * When rehashing process happens, the number of collisions should be
	 * properly updated.
	 *
	 * The definition of collision is "two different keys map to the same hash value."
	 *
	 * Be careful with the situation where you could over count.
	 * Try to think as if you are using separate chaining!
	 * "How would you count the number of collisions in separate chaining?"
	 *
	 * @return number of collisions
	 */
	@Override
	public int numOfCollisions() {
		// TODO implement this
		return numOfCollsion;
	}

	/**
	 * Returns the hash value of a String
	 *
	 * @param value
	 *            value for which the hash value should be calculated
	 * @return int hash value of a String.
	 */
	@Override
	public int hashValue(String value) {
		// TODO implement this
		int hashValue =  hashFunc(value);
		if(hashValue<0)
			return -1;
		else
			return hashValue;
	}

	/**
	 * Returns the frequency of a key String
	 *
	 * @param key
	 *            key string value to find its frequency
	 * @return frequency value if found. If not found, return 0
	 */
	@Override
	public int showFrequency(String key) {
		// TODO implement this
		// if input key is not a word, or is null, then return 0
		if(!isWord(key))
			return 0;
		int hashCode = hashFunc(key);
		if(hashCode>=0){
			while(myhashtable[hashCode]!=null){
				// if found the key, then return the frequency of this key
				if(key.equals(myhashtable[hashCode].value)){
					return myhashtable[hashCode].frequency;
				}
				 hashCode++;
				 hashCode=hashCode%tableLength;
			}
		}
		// not found keyreturn 0
		return 0;
	}

	/**
	 * Removes and returns removed value
	 *
	 * @param value
	 *            String value to be removed
	 * @return value that is removed
	 */
	@Override
	public String remove(String key) {
		// TODO implement this
		// if input key is not a word, or is null, then return 0
		if(!isWord(key))
			return key;
		int hashCode = hashFunc(key);
		String tmp;
		if(hashCode>=0){
			while(myhashtable[hashCode]!=null){
				  if(key.equals(myhashtable[hashCode].value)){
					  tmp = myhashtable[hashCode].value;
					  myhashtable[hashCode]=DELETED;
					  numOfItem--;
					  loadFactor =(double)numOfItem/tableLength;
					  return tmp;
				  }
				  hashCode++;
				  hashCode=hashCode%tableLength;
			}
		}
		//not found
		return key;
	}

	/*
	 * Instead of using String's hashCode, you are to implement your own here,
	 * taking the table length into your account.
	 *
	 * In other words, you are to combine the following two steps into one step here.
	 * 1. converting Object into integer value
	 * 2. compress into the table using modular hashing (division method)
	 *
	 * Helper method to hash a string for English lowercase alphabet and blank,
	 * we have 27 total.
	 * But, you can assume that blank will not be added into your table.
	 * Refer to the instructions for the definition of words.
	 *
	 * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
	 *
	 * But, to make the hash process faster, Horner's method should be applied
	 * as follows;
	 *
	 * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0
	 * can be rewritten as
	 * (((var4*n + var3)*n + var2)*n + var1)*n + var0
	 *
	 * Note: You must use 27 for this homework.
	 *
	 * However, if you have time,
	 * I would encourage you to try with other constant values other than 27
	 * and compare the results but it is not required.
	 */
	private int hashFunc(String input) {
		// TODO implement this
		//if input is null, just return -1
		if(input==null)
			return -1;
		int result =0;
		int inputLength = input.length();
		for(int i=0;i<inputLength;i++){
			result=result*27+((int)input.charAt(i)-(int)'a'+1);
			result=result%this.tableLength;
		}
		return (int)(result%this.tableLength);
	}

	// doubles array length and rehash items whenever the load factor is reached
	private void rehash() {
		// TODO implement this
		int newtableLength = this.getNextPrime(tableLength);
		DataItem[] newhashtable = new DataItem[newtableLength];
		int tmpHashCode,tmpFrequency;
		numOfCollsion=0;
		String tmpvalue;
		int oldTableLength = tableLength;
		tableLength=newtableLength;
		for (int i=0;i<oldTableLength;i++){
			 if(myhashtable[i]!=null && myhashtable[i]!=DELETED){
				 tmpvalue = myhashtable[i].value;
				 tmpFrequency=myhashtable[i].frequency;
				 tmpHashCode= hashFunc(tmpvalue);
				 DataItem newItem = new DataItem(tmpvalue,tmpFrequency);
				 if(newhashtable[tmpHashCode]!=null){
					 //if hashvalue of temp location in newhashtable is same as the 
					 // DataItem in oldhashtable, then collsion occurs
					 if(hashFunc(newhashtable[tmpHashCode].value)==tmpHashCode &&
							 newhashtable[tmpHashCode].value!=tmpvalue){
						 numOfCollsion++;
					 }
					 while(newhashtable[tmpHashCode]!=null){
						 tmpHashCode++;
						 tmpHashCode=tmpHashCode%newtableLength;
					 }
				 }
				 newhashtable[tmpHashCode]=newItem;
			 }
		}
		loadFactor=(double)numOfItem/newtableLength;
		myhashtable=newhashtable;	
		System.out.println("Rehashing "+numOfItem+" items, new size is "+newtableLength);
	}
	
	// private helper function, find next prime
    private int getNextPrime(int curLength){
    	int length=curLength*2+1;
    	while(!this.isPrime(length))
    		length+=1;
    	return length;
    }
    
    private boolean isPrime(int num){
    	if(num%2 == 0)
    		return false;
    	for(int i=3;i<num/2;i+=2){
    		if (num%i==0){
    			return false;
    		}
    	}
    	return true;
    }
    
    //test whethe input text is a word or not
	private boolean isWord(String text) {
		if(text==null){
			return false;
		}else{
			return text.matches("[a-zA-Z]+");
		}
	}
	
	
	// private static data item nested class
	private static class DataItem {
		private String value;
		private int frequency;
		// TODO implement constructor and methods
		
		private DataItem(String input,int fre){
			this.value = input;
			this.frequency = fre;
	    }
		
		private DataItem(){
			this.value = null;
			this.frequency = 0;
	    }
		
		private void addFrequency(){
			this.frequency++;
		}		
	}	
}

