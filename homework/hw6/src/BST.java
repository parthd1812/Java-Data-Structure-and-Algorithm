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

public class BST<T extends Comparable<T>> implements Iterable<T>,
		BSTInterface<T> {

	private Node<T> root;
	private Comparator<T> comparator=null;

	public BST() {
		root = null;
	}

	public BST(Comparator<T> comparator) {
		this.comparator = comparator;
		root = null;
	}

	/**
	 * Returns the comparator used to order this collection.
	 *
	 * @return comparator
	 */
	public Comparator<T> comparator() {
		return comparator;
	}

	/**
	 * Returns the root data of this tree.
	 *
	 * @return root data
	 */
	public T getRoot() {
		if(root!=null)
			return root.getData();
		else
			return null;
	}

	/**
	 * Returns the height of this tree.
	 * if the tree is empty or tree has only a root node,
	 * then the height of the tree is 0.
	 *
	 * @return int value of the height
	 */
	public int getHeight() {
		// if root is empty or this bst only has one node, height is 0
		if(root==null)
			return 0;
		if(root.left==null && root.right==null)
			return 0;
		else
			return  getHeight(root,0);
	}
	
	
	private int getHeight(Node<T> curr,int height){
		if(curr==null){
		  // there is node child of the parent
          return height-1;
	     }else{
	    	  // image curr node has a child ,so call getHeight recursively
	    	  // and update height 
	          int leftHeight =  getHeight(curr.left,height+1);
	          int rightHeight = getHeight(curr.right,height+1);
	          return Math.max(leftHeight, rightHeight);
	     }
	}

	/**
	 * Returns the number of ndoes in this tree.
	 *
	 * @return int value of the number of nodes.
	 */
	public int getNumberOfNodes() {
		if(root==null)
			return 0;
		else
			return getNumberOfNodes(root);
	}
	
	private int getNumberOfNodes(Node<T> curr){
		if(curr==null){
			return 0;
		}else{
			int leftNodes = getNumberOfNodes(curr.left);
			int rightNodes = getNumberOfNodes(curr.right);
			// return the number of nodes in left child and right child and itself
			return leftNodes+rightNodes+1;
		}
					
	}

	/**
	 * Given the value (object) to be searched, it tries to find it.
	 *
	 * @param toSearch
	 *            - value to be searched
	 * @return The value (object) of the search result. If nothing found, null.
	 */
	@Override
	public T search(T toSearch) {
		if(root==null || toSearch==null){
			return null;
		}else{
			if(this.comparator()==null){
				return search(toSearch,root);
			}else{
				return search(toSearch,root,this.comparator());
			}
		}
	}
	
	private T search(T toSearch, Node<T> parent,Comparator<T> comparator){
		if(parent==null){
			return null;
		}
		if(comparator.compare(parent.getData(),toSearch)==0){
			return parent.getData();
		}else{
			if(comparator.compare(parent.getData(),toSearch)>0){
				return search(toSearch,parent.left,comparator);	
			}else{
				return search(toSearch, parent.right,comparator);
			}
		}
	}
	
	private T search(T toSearch,Node<T> parent){
		if(parent==null){
			return null;
		}
		if(parent.getData().compareTo(toSearch)==0){
			return parent.getData();
		}else{
			if(parent.getData().compareTo(toSearch)>0){
				return search(toSearch,parent.left);	
			}else{
				return search(toSearch, parent.right);
			}
		}
	}

	/**
	 * Inserts a value (object) to the tree.
	 * No duplicates allowed.
	 *
	 * @param toInsert
	 *            - a value (object) to be inserted to the tree.
	 */
	@Override
	public void insert(T toInsert) {
		if(toInsert!=null){
			Node<T> newNode = new Node<T>(toInsert);
			if(root == null){
				root = newNode;
			}
			if(comparator == null)
				insert(newNode,root);	
			else
				insert(newNode,root,comparator);	
		}
	}
    
	private void insert(Node<T> newNode, Node<T> node,Comparator<T> comparator){
		// if node is already exist in the bst, return 
		if(comparator.compare(node.getData(), newNode.getData()) == 0 &&
				node.getData().compareTo(newNode.getData())==0){
			return;
		}else if(comparator.compare(node.getData(), newNode.getData()) < 0){
			if(node.right==null){
				// new node is bigger than current node ,add it as right child 
				node.right = newNode;
				return;
			}else{
				insert(newNode,node.right,comparator);
			}
		}else if(comparator.compare(node.getData(), newNode.getData()) > 0){
			if(node.left == null){
				// new node is smaller than current node ,add it as left child 
				node.left = newNode;
				return;
			}else{
				insert(newNode,node.left,comparator);
			}
		}
		
	}
	
	private void insert(Node<T> newNode,Node<T> node){
		// if node is already exist in the bst, return 
		if(node.getData().compareTo(newNode.getData())==0){
			return;
		}else if(node.getData().compareTo(newNode.getData()) < 0){
			if(node.right==null){
				// new node is bigger than current node ,add it as right child 
				node.right = newNode;
				return;
			}else{
				insert(newNode,node.right);
			}
		}else if(node.getData().compareTo(newNode.getData()) > 0){
			if(node.left == null){
				// new node is smaller than current node ,add it as left child 
				node.left = newNode;
				return;
			}else{
				insert(newNode,node.left);
			}
		}
	}
	
	/**
	 * In-order iterator
	 *
	 * @return iterator object
	 */

	public Iterator<T> iterator() {
		myIterator iterator = new myIterator();
		return (Iterator<T>)iterator;
	}

	// private static nested class for Node
	private static class Node<T> {
		private T data;
		private Node<T> left;
		private Node<T> right;

		public Node(T data) {
			this(data, null, null);
		}

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
        
		@Override
		public String toString() {
			return data.toString();
		}
		
		public T getData(){
			return data;
		}

	}
    
	private class myIterator implements Iterator<T>{
		private Node<T> curr;
		private LinkedList<Node<T>> data = new LinkedList<Node<T>>();
		
		public myIterator(){
			this.curr=root;
			while(this.curr!=null){
				data.add(this.curr);
				this.curr = this.curr.left;
			}
		}
		
		public boolean hasNext(){
			if(this.data.isEmpty() && root==null){
				return false;
			}else{
				return !this.data.isEmpty();
			}
		}
		
		public T next(){
			if(this.hasNext()){
				Node<T> node = data.removeLast();
				this.curr = node.right;
				while(this.curr!=null){
					data.add(this.curr);
					this.curr=this.curr.left;
				}
				return node.getData();
			}else{
				return null;
			}
	    }
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException("remove");
		}
}
	/***********************************************************
	 *
	 * For a very simple debug purpose:
	 *
	 * Test your BST with this first to make sure your BST works.
	 * But, this is a starting point. Should test more!
	 *
	 ***********************************************************/
	public static void main(String[] args) {
		BST<Integer> b = new BST<Integer>();
		int[] ar = { 31, 16, 49, 5, 18, 51, 4, 13, 17, 19, 8 };
		for (Integer x : ar)
			b.insert(x);

		for (Integer x : b)
			System.out.print(x + " ");

		System.out.println();
		System.out.println(b.search(8));
		System.out.println(b.getHeight());
		System.out.println(b.getNumberOfNodes());
	}

}