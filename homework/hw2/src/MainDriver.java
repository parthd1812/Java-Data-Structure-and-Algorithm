import java.util.Arrays;

/******************************************************************************
 *
 * 08-722 Data Structures for Application Programmers
 *
 * Homework Assignment 2 Solve Josephus Problem
 *
 * Objectives:
 * 1. Understand how Queue/Deque can be used as an aid in an algorithm.
 * 2. Explore and compare different data structures
 * (ArrayDeque and LinkedList) and their operations to solve the same problem.
 *
 ******************************************************************************/

public class MainDriver {

	public static void main(String[] args) {
		// the size and rotation values to be changed for testing
//		int size = 1000;
//		int rotation = 1000;
//
//		Josephus game = new Josephus();
//		Stopwatch timer1 = new Stopwatch();
//		System.out.println("Survivor's position: "
//				+ game.playWithAD(size, rotation));
//		System.out
//				.println("Computing time with ArrayDeque used as Queue/Deque: "
//						+ timer1.elapsedTime() + " millisec.");
//
//		Stopwatch timer2 = new Stopwatch();
//		System.out.println("Survivor's position: "
//				+ game.playWithLL(size, rotation));
//		System.out
//				.println("Computing time with LinkedList used as Queue/Deque: "
//						+ timer2.elapsedTime() + " millisec.");
//
//		Stopwatch timer3 = new Stopwatch();
//		System.out.println("Survivor's position: "
//				+ game.playWithLLAt(size, rotation));
//		System.out
//				.println("Computing time with LinkedList (remove with index) : "
//						+ timer3.elapsedTime() + " millisec.");
		
//		String course = "8722";
//		Object[] A = {course, Integer.valueOf(1),new int[3]};
//		Object[] B = new Object[3];
//		System.arraycopy(A,0,B,0,A.length);
//		B[0]="08722";
//		
//		System.out.println(Arrays.equals(A,B));
//		System.out.println(A[0]==B[0]);
//		System.out.println(A[2].equals(B[2]));
		String[] content= {"we love","data structures","so much"};
		StrArray printMe = new StrArray(content);
		content[0]="I love";
		printMe.getStrArray()[0]=content[0];
		foo(printMe);
		System.out.println(Arrays.toString(printMe.getStrArray()));
		
	}
    
	public static void foo(StrArray array){
		String[] content = {"Every one","loves","data structures","so much"};
		array = new StrArray(content);
	}
	
	
	
	
	
	
	
	
}