package listClass;

import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.NoSuchElementException;


public class BasicLinkedList<T> implements Iterable<T> {
	private Node<T> head; //head (front) and tail (end) of the linkedList they're of type "Node<whatever Type>"
	//because they're references to their
	private Node<T> tail; // respective nodes and memory addresses
	private int size; //keeps track of the size of the LinkedList

	private static class Node<T>{ //type is "T"
		private T data; // an element in the list
		private Node<T> next; //reference to the "next" node

		public Node(T element){ //In Java, you don't need to use "<T>" in your constructor name
			this.data = element;
			next = null;
		}
	} // END OF PRIVATE STATIC CLASS

	public BasicLinkedList() {
		head = null; //initializes this list as "empty"
		tail = null;
		size = 0;
	}
	public int getSize() {
		return size;
	}
	/**
	 * Adds the element to the tail of the list.
	 * @param data
	 * @return Returns a reference to the current object.
	 */
	public BasicLinkedList<T> addToEnd(T data){
		//first create a node to add w/ data ofc :)
		Node<T> node = new Node<>(data);
		if (head == null) { // if the list is currently empty, set the new I made node as both head and tail
			head = node;
			tail = node;
		}else {
			tail.next = node; 
			//the next value of the tail is "null" so I'm putting this new node in place of that "null"
			tail = node; 
			//assigning the new node as the new "tail" (instance variable "tail")
			// and in doing so, we disregard the previous tail
		}
		size++; //increasing the size by "1"
		return this;
	}

	/**
	 * Adds the element to the head of the list.
	 * @param data
	 * @return
	 */
	public BasicLinkedList<T> addToFront(T data){ //DO NOT NEED TO CHECK IF THE LIST IS EMPTY
		Node<T> node = new Node<>(data); //creating a node object named "node" and putting data inside the node
		node.next = head; //assigning the node the value of head (which should be "null" the first time around)
		//by first attaching it to the current "head" node
		head = node; // making "node" the new head of the LinkedList 
		size++;
		return this; //QUESTION: is this correct? Returning a reference to the current object
	}
	/**
	 * Returns the head element (without removing it), or null if the list is empty.
	 * @return
	 */
	public T getFirst() { //SHOULD WORK
		if (head == null) {
			return null;
		}
		return head.data; //getting the data stored in the first node using ".data"
	}
	/**
	 * Returns the tail element (without removing it), or null if the list is empty.
	 * @return
	 */
	public T getLast() { //SHOULD WORK
		if (tail == null) {
			return null;
		}
		return tail.data;		
	}
	/**
	 * Removes and returns the head element. If the list is empty, returns null.
	 * @return
	 */
	public T retrieveFirstElement() {
		if(head == null) { //"if the list is empty..."
			return null; // "return null"
		}else {
			//when removing an element from the node, you need to store it first before getting rid of the node so...
			T headDataVal = head.data; //getting the value of the data inside the head node
			if (head.next == null) {// if there's only ONE element in the list
				head = null;
			} else {
				head = head.next;
				size--;
			}
			return headDataVal;
		}
	}
	/**
	 * Removes and returns the tail element. If the list is empty, returns null.
	 * @return
	 */
	public T retrieveLastElement() {
		if(head == null) { //If head is null, that means the list is empty :)
			return null;
		}else if (head.next == null) { 
			//if the next value after the node is "null," then there's only ONE node in this list! So...
			T headDataVal = head.data;
			tail = null;
			head = null;
			size--;
			return headDataVal;

		}else {
			Node<T> curr; 
			//moved this out so that I could modify the "curr" variable without affecting the incrementation
			for (curr = head; curr.next.next != null; curr = curr.next) {
				//start from the head node and keep going until you reach a node that has a next NEXT value equal to "null"
				//once the next NEXT value is equal to null, the loop will terminate and you've got your 2nd to last node!
				//"curr" will then be assigned the 2nd to last node
			}
			//after the loop is done, then we should have the 2nd to last node as "curr"
			T lastNodeDataVal = curr.next.data;
			curr.next = null;
			tail = curr;
			size--;
			return lastNodeDataVal;
		}

	}

	/**
	 * Removes ALL instances of the target element from the list.
	 * @param targetData
	 * @return just a reference to the current object.
	 */
	public BasicLinkedList<T> removeAllInstances(T targetData){
		if(head == null) {
			return this; // return the current object if the list is empty
		}else if(head.data.equals(targetData) && head != null && head.next == null )	{
			head = null;
			size--;
			return this;
		}
		int numOfInstances = 0;
		for(Node<T> curr = head, precedingNode = null; curr != null; precedingNode = curr, curr = curr.next) {
			//keeping going until the next element is null
			if (curr.data.equals(targetData)) {
				numOfInstances++;
				//NOTE TO SELF: use ".equals()" instead of "==" bc == checks if two object references point to the same
				//object, while equals checks if two objects have the same value.
				//first time around "curr" is head so we then check...(below)
				if (precedingNode == null) {
					//if "precedingNode" is null, that means we're out of bounds and the next value
					//  is ofc the "head" (in a singly linked list ofc)
					head = curr.next; //essentially going to skip over the original head value. Thus, making it garbage.
				}else {
					precedingNode.next = curr.next;
					//so we have a precedingNode (coming before), a current Node 
					//(that is equal to the data value we want to remove,
					// we want to remove and a succeeding node (coming after, "curr.next"). 
					//What I did here is that I rerouted the precedingNode's "next" referencing to
					//point to the current node's next reference. And since nothing is pointing to the current node, it
					// is removed from the link (bc it's no longer linked).
				}
			}else {
				precedingNode = curr; //if the current node does not have the target data value, then set it as the
				// new previous node
			}
		}
		size -= numOfInstances; //size tracking from the size by the numberOfInstances
		return this;
	}
	/**
	 * Returns an instance of an anonymous inner class that defines an Iterator over this list (from head to tail)
	 * 
	 */
	public Iterator<T> iterator(){ //Iterator method that cost me my free points in that first exam >:L
		return new Iterator<T>() {
			private Node<T> curr = head; //starting from the head of the LinkedList

			public boolean hasNext() {
				return curr != null; //returns "true" if the curr not is not "null," returns false otherwise
			}

			public T next() {
				if (!hasNext()) { //if there are no more nodes after the current node, then...
					throw new NoSuchElementException();
				}
				T currentNodeDataVal = curr.data; //storing the data value of the current node
				curr = curr.next; //moving onto the next node and making THAT node the new "curr" node
				return currentNodeDataVal; //returning the original value of the current node (before reassigning "curr"
			}
		};

	}
}
