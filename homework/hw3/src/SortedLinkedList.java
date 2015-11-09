/************************************************************
 *
 * 08-722 Data Structures for Applications Programmers
 * Homework Assignment 3
 * SortedLinkedList Implementation with Recursion
 *
 * Andrew ID:  mizhang
 * Name:  Mi Zhang
 *
 ************************************************************/

public class SortedLinkedList implements MyListInterface {

    private Node head;
    
    public SortedLinkedList(){
        head=null;
    }
    
    // TODO implement constructors and necessary methods here
    public  SortedLinkedList(String[] input){
        if(input.length!=0){
        	// create a list with segeration of this file
            segregateFile(input,head);
        }else{
            head=null;
        }            
    }
    
    @Override
    public void add(String value){
    	if(value!=null && isContain(value, head)==false){
	         Node tmp = findPlace(value,head);
	         //  find the proper place to insert a new value
	         if (tmp!=head){
	                addAfter(value,tmp);
	         }else if(tmp!=null && tmp.data.compareTo(value)>0){
	         // insert the node before head
	                Node newhead=new Node(value,head);
	                head=newhead;
	         }else if(tmp==null){ // Linked List is empty
	            	head=new Node(value,null);
	         }else{
	                addAfter(value,tmp);
	        }
    	}
    }
    
    @Override
    public int size(){
        return countSize(head);
    }
    
    @Override
    public void display(){
        System.out.print("[");
        displayWithHead(head);
    }
    
    @Override
    public boolean contains(String key) {
        return isContain(key,head);        
    }

    @Override
    public boolean isEmpty() {
        if(head==null)
            return true;
        else
            return false;
    }

    @Override
    public String removeFirst() {
    	//if Linked List is null, return ""
        if (head == null)
            return "";
        else{
            String item = head.data;
            head=head.next;
            return item;
        }
    }

    @Override
    public String removeAt(int index) {
    	// test whether the index is legal
    	if(index>=size() || index<0){
    		return null;
    	}
    	// because Linked List is single Linked List, 
    	// we should find the element before the index
        Node delete = findPre(index,head);
        if(delete==head){
        	// delete the first element 
        	if(index==0){
	            head=head.next;
	            return delete.data;
            }else{
            	// delete the second element (index is 1)
            	String temp = head.next.data;
            	head.next=head.next.next;
            	return temp;
            }
        }else{
            String tmp = delete.next.data;
            delete.next=delete.next.next;
            return tmp;
        }       
    }
    
    private boolean isContain(String key, Node temp){
    	// if Linked List is empty, return false
    	if(temp==null){
    		return false;
    	}
        if(temp.data.equals(key))
            return true;
        else if(temp.next==null){
            return false;
        }else{
            return isContain(key, temp.next);
        }        
    }
    
    private int findIndex(String key,Node curhead){
    	// if Linked list is empty, return 0
    	if(curhead==null){
    		return 0;
    	}
        if(curhead.next!=null&&curhead.data.equals(key)==false){
        	// recursion case
            return 1+findIndex(key,curhead.next);
        }else{
        	// base case
            return 0;
        }
    }
    
    //private help function, find the element before specific index
    private Node findPre(int index,Node temp){
    	if(temp==null){
    		return null;
    	}
        int curnum=findIndex(temp.data,head);
        if(curnum<index-1){
            return findPre(index,temp.next);
        }else{
            return temp;
        }
    }
    
    // constructor helper, add element recursively 
    private void helpConstructor(String[] input,Node curhead){
        if(input.length!=0){
            if(isContain(input[0],curhead)==false){
                Node tmp = findPlace(input[0],curhead);
                if (tmp!=curhead){
                        addAfter(input[0],tmp);
                }else if(tmp==null){
                	head=new Node(input[0],null);
                	curhead=head;
                }else{                
                    if(tmp.data.compareTo(input[0])>0){
                        Node newhead=new Node(input[0],head);
                        head=newhead;
                        curhead=head;
                    }else{
                        addAfter(input[0],tmp);
                    }
                }
                // add the rest string 
                String[] newinput = new String[input.length-1];
                System.arraycopy(input, 1, newinput, 0, input.length-1);
                helpConstructor(newinput,curhead);    
            }else{
                String[] newinput = new String[input.length-1];
                System.arraycopy(input, 1, newinput, 0, input.length-1);
                helpConstructor(newinput,curhead);    
            }
        }    
    }
    
    // private helper function ,find the proper place to insert element
    private Node findPlace(String key, Node curhead){
        if(curhead==null || curhead.next==null || 
        		curhead.data.compareTo(key)>0){
            return curhead;
        }else if(curhead.next.data.compareTo(key)>0 && 
        		curhead.data.compareTo(key)<=0){
                return curhead;                
        }else{
            return findPlace(key,curhead.next);    
        }
    }
    
    // private helper function , add element after current Node
    private void addAfter(String key,Node cur){
        Node tmp = new Node(key,cur.next);
        cur.next=tmp;    
    }
    
    // display the element recursively
    private void displayWithHead(Node tmp){
        if(tmp!=null && tmp.next!=null){
            System.out.print(tmp.data+", ");
            displayWithHead(tmp.next);
        }else if(tmp==null){
        	System.out.println("]");
        }else{
            System.out.println(tmp.data+"]");
        }
        
    }
    
    // count size recursively
    private int countSize(Node tmp){
    	if(tmp==null){
    		return 0;
    	}
        if (tmp.next!=null){
            tmp=tmp.next;
            return 1+countSize(tmp);
        }else{
            return 1;
        }         
    }
    
    // to avoid stack over flow, add 4000 element into the Linked List each time
    private void segregateFile(String[] input,Node curhead){
        if(input.length>4000){
            String[] firstinput = new String[4000];
            System.arraycopy(input, 0, firstinput, 0, 4000);    
            helpConstructor(firstinput,curhead);
            String[] newinput = new String[input.length-4000];
            System.arraycopy(input, 4000, newinput, 0, input.length-4000);    
            segregateFile(newinput,head);            
        }else{
            helpConstructor(input,head);
        }
    }
    
    /**********************************************************
     * Static Nested Node Class with String data
     **********************************************************/
    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}