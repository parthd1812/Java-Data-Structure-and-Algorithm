/**********************************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Lab 7 Heaps and Java PriorityQueue class
 *
 * Find median of sequence of integers using both maxHeap and minHeap
 *
 * Andrew ID: mizhang
 * Name:  Mi Zhang
 *
 **********************************************************************/

import java.util.*;

public class FindMedian {
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20,
			Collections.reverseOrder());
	private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(20);

	public static void main(String[] args) {
		FindMedian tester = new FindMedian();
		tester.addNumber(6);
		tester.addNumber(4);
		tester.addNumber(3);
		tester.addNumber(10);
		tester.addNumber(12);

		// 6.0
		System.out.println(tester.getMedian());

		tester.addNumber(5);

		// 5.5
		System.out.println(tester.getMedian());

		tester.addNumber(7);
		tester.addNumber(8);

		// 6.5
		System.out.println(tester.getMedian());
	}

	/*
	 * It maintains a condition that maxHeap.size() >= minHeap.size()
	 */
	public void addNumber(int value) {
		// TODO implement this method
		if(maxHeap.size()==minHeap.size()){
			if(minHeap.peek()!= null && value >minHeap.peek()){
				maxHeap.offer(minHeap.poll());
				minHeap.offer(value);
			}else{
				maxHeap.offer(value);
			}
		}else{
			if(value<maxHeap.peek()){
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(value);
			}else{
				minHeap.offer(value);
			}
		}
	}

	/*
	 * If maxHeap and minHeap are of different sizes,
	 * then maxHeap must have one extra element.
	 */
	public double getMedian() {
		// TODO implement this method
		if(maxHeap.isEmpty())
			return (Double) null;
		if(maxHeap.size()==minHeap.size())
			return (minHeap.peek()+maxHeap.peek())/2.0;
		else
			return maxHeap.peek();
	}

}