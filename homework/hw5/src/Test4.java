/*******************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Homework Assignment 5 Document Similarity
 * Acceptance Test (case 1)
 *
 * No need to worry about number of lines for this case.
 *
 * DO NOT MODIFY THIS CLASS!
 *
 *******************************************************/

public class Test4{

	public static void main(String[] args) {
		Similarity text1 = new Similarity("a");
//		System.out.println("hello\nhello, I am mizhang");
		System.out.println(text1.numOfWords() + " words.");
		System.out.println(text1.numOfWordsNoDups() + " distinct words");
		System.out.println(text1.euclideanNorm() + " Euclidean norm.\n");
		System.out.println(text1.numberOfLines() + " lines.\n");
//
		Similarity text2 = new Similarity("b");
		System.out.println(text2.numOfWords() + " words.");
		System.out.println(text2.numOfWordsNoDups() + " distinct words.");
		System.out.println(text2.euclideanNorm() + " Euclidean norm.\n");
//
		System.out.println(text1.dotProduct(text2.getMap()) + " dot product.");
		System.out.println(text1.distance(text2.getMap()) + " distance.");
	}
}