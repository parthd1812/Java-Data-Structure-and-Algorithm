import java.util.ArrayDeque;
import java.util.LinkedList;

/**********************************************************
 *
 * 08-722 Data Structures for Application Programmers
 *
 * Homework Assignment 2 Solve Josephus problem
 * with different data structures and different algorithms
 * and compare running time
 *
 * Andrew ID: mizhang
 * Name:  Mi Zhang
 *
 **********************************************************/

public class Josephus {
	private ArrayDeque<Integer> thequeue= new ArrayDeque<Integer>();
	private LinkedList<Integer> theLinkedList = new LinkedList<Integer>();
	private int first;
	private int result;
	private int count;
	/**
	 * This method uses ArrayDeque data structure as Queue/Deque to find the
	 * survivor's position.
	 *
	 * @param size
	 *            Number of people in the circle that is bigger than 0.
	 * @param rotation
	 *            Elimination order in the circle. The value has to be greater
	 *            than 0.
	 * @return The position value of the survivor.
	 */
	public int playWithAD(int size, int rotation) {
		// TODO implement this
		checkInput(size,rotation);
		initQueue(size);
//		after every calculation, ArrayDeque is empty, do not have to clear it.
		while(!thequeue.isEmpty()){
			count=1;
			while(count<rotation){
				first=thequeue.removeFirst();
				thequeue.addLast(first);
				count=count+1;
			}
			result=thequeue.removeFirst();
		}
		return result;		
	}
	
	

	/**
	 * This method uses LinkedList data structure as Queue/Deque to find the
	 * survivor's position.
	 *
	 * @param size
	 *            Number of people in the circle that is bigger than 0.
	 * @param rotation
	 *            Elimination order in the circle. The value has to be greater
	 *            than 0.
	 * @return The position value of the survivor.
	 */
	
	public int playWithLL(int size, int rotation) {
		checkInput(size,rotation);
		initLinkedList(size);
//		after every calculation, LinkedList is empty, do not have to clear it.
		while(!theLinkedList.isEmpty()){
			count=1;
			while(count<rotation){
				first=theLinkedList.removeFirst();
				theLinkedList.addLast(first);
				count=count+1;
			}
			result=theLinkedList.removeFirst();
		}		
		return result;		
	}

	
	/**
	 * This method uses LinkedList data structure to find the survivor's position.
	 * However, this does not use the LinkedList as Queue/Deque.
	 * Instead, this method uses LinkedList as "Linked List."
	 *
	 * That means, it uses index value to find and remove a person to be executed in the
	 * circle.
	 *
	 * @param size
	 *            Number of people in the circle that is bigger than 0.
	 * @param rotation
	 *            Elimination order in the circle. The value has to be greater
	 *            than 0.
	 * @return The position value of the survivor.
	 */
	
	public int playWithLLAt(int size, int rotation) {
		checkInput(size,rotation);
		initLinkedList(size);
		int front=0;
		while(!theLinkedList.isEmpty()){
			count=1;
			while(count<rotation){
				count=count+1;
//				 calculate the index which should be removed
				front=(front+1)%theLinkedList.size();
			}
			result=theLinkedList.remove(front);
		}
		return result;
	}
	
	private void initQueue(int size){
		for(int i=1;i<=size;i++){
			thequeue.addLast(i);
		}
		first=0;
		result=0;
	}
	
	private void initLinkedList(int size){
		for(int i=1;i<=size;i++){
			theLinkedList.addLast(i);
		}
		first=0;
		result=0;
	}
	
	private void checkInput(int size, int rotation){
//		check whether the input is legal
		if (size<=0) {
			throw new RuntimeException("Number of people should be positive");
		}
		if(rotation<=0){
			throw new RuntimeException("Elimination order should be positive");
		}
	} 
}



