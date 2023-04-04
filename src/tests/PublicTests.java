package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClass.BasicLinkedList;

public class PublicTests {
	
	//Tests the "addToEnd()" method works as well as my iterator (using the for-each loop)
	@Test
	public void test1() {
		
		BasicLinkedList<Integer> blist = new BasicLinkedList<>();
		
		blist.addToEnd(6);
		blist.addToEnd(4);
		String answer = "";
		for (Integer entry : blist) {
			answer += entry;
		}
		assertEquals(answer, "64"); //the two numbers should be in the correct order
		/////////////////
		int expectedSize = 2; //CHECKING THE SIZE EACH TIME I REMOVE AND RETURN AN ELEMENT
		int actualSize = blist.getSize();
		assertEquals(expectedSize, actualSize);
	}
	@Test
	public void test2() {
		
		BasicLinkedList<String> blist = new BasicLinkedList<String>();
		
		blist.addToEnd("Zebra").addToEnd("Bear").addToEnd("Dove");
		String answer = "";
		for (String entry : blist) {
			answer += entry;
		}
		assertEquals(answer, "ZebraBearDove");
		/////////////////
		int expectedSize = 3; //CHECKING THE SIZE EACH TIME I REMOVE AND RETURN AN ELEMENT
		int actualSize = blist.getSize();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void testAddToFront() {
	    BasicLinkedList<Integer> blist = new BasicLinkedList<>();
	    blist.addToFront(2);
	    blist.addToFront(1);
	    String answer = "";
	    for (Integer entry : blist) {
	        answer += entry;
	    }
	    assertEquals(answer, "12");
		/////////////////
		int expectedSize = 2; //CHECKING THE SIZE EACH TIME I REMOVE AND RETURN AN ELEMENT
		int actualSize = blist.getSize();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void testRetrieveFirstElement() {
	    BasicLinkedList<Integer> blist = new BasicLinkedList<>();
	    blist.addToEnd(1);
	    blist.addToEnd(2);
	    blist.addToEnd(3);

	    // Test retrieving the first element
	    Integer first = blist.retrieveFirstElement();
	    assertEquals(first, Integer.valueOf(1));

	    // Test that the size of the list has been decremented
	    assertEquals(blist.getSize(), 2);

	    // Test that the remaining elements are still in the list and in the correct order
	    String answer = "";
	    for (Integer entry : blist) {
	        answer += entry;
	    }
	    assertEquals(answer, "23");

	    // Test retrieving the first element from an empty list
	    BasicLinkedList<String> emptyList = new BasicLinkedList<>();
	    String nullResult = emptyList.retrieveFirstElement();
	    assertNull(nullResult);
	}
	@Test
	public void testAddToFrontAndRetrieveFirstElement() {
	    BasicLinkedList<Integer> blist = new BasicLinkedList<>();
	    blist.addToFront(3);
	    blist.addToFront(2);
	    blist.addToFront(1);
	    assertEquals(1, (int) blist.retrieveFirstElement());
	    //retrieves and removes the First element in the list
	    assertEquals(2, (int) blist.retrieveFirstElement());
	    assertEquals(3, (int) blist.retrieveFirstElement());
	}
	@Test
	public void testRetrieveLastElement() {
	    BasicLinkedList<String> blist = new BasicLinkedList<>();
	    blist.addToEnd("apple");
	    blist.addToEnd("banana");
	    blist.addToEnd("orange");
	    
	    String lastElement = blist.retrieveLastElement();
	    assertEquals(lastElement, "orange");
	    /////////////////
	    int expectedSize = 2; //CHECKING THE SIZE EACH TIME I REMOVE AND RETURN AN ELEMENT
	    int actualSize = blist.getSize();
	    assertEquals(expectedSize, actualSize);
	    
	    lastElement = blist.retrieveLastElement();
	    assertEquals(lastElement, "banana");
	    /////////////////
	    expectedSize = 1;
	    actualSize = blist.getSize();
	    assertEquals(expectedSize, actualSize);
	    
	    lastElement = blist.retrieveLastElement();
	    assertEquals(lastElement, "apple");
	    /////////////////
	    expectedSize = 0;
	    actualSize = blist.getSize();
	    assertEquals(expectedSize, actualSize);

	    lastElement = blist.retrieveLastElement();
	    assertNull(lastElement);
	}
	@Test
	public void testRemoveAllInstances() { //SOMETHING IS WRONG WITH THIS METHOD
	    BasicLinkedList<Integer> list = new BasicLinkedList<>();
	    list.addToEnd(1);
	    list.addToEnd(2);
	    list.addToEnd(3);
	    list.addToEnd(2);
	    list.addToEnd(4);
	    list.addToEnd(2);
	    list.removeAllInstances(2);
	    String answer = "";
		for (Integer entry : list) {
			answer += entry;
		}
		assertEquals(answer, "134");
		/////////////////
		int expectedSize = 3;
	    int actualSize = list.getSize();
	    assertEquals(expectedSize, actualSize);
	}
	
	//[TESTING DIFFERENT OPERATION LOCATIONS]
	
	// ["SOMEWHERE IN THE MIDDLE"]
	@Test
	public void testRemoveAllInstancesMiddle() {
	    BasicLinkedList<String> list = new BasicLinkedList<>();
	    list.addToEnd("apple");
	    list.addToEnd("banana");
	    list.addToEnd("orange");
	    list.addToEnd("apple");
	    list.addToEnd("grape");
	    list.addToEnd("apple");
	    list.addToEnd("apple");

	    list.removeAllInstances("apple");

	    // The resulting list should have three elements: "banana", "orange", and "grape"
	    assertEquals(3, list.getSize());
	    assertEquals("banana", list.retrieveFirstElement());
	    assertEquals("orange", list.retrieveFirstElement());
	    assertEquals("grape", list.retrieveFirstElement());
	}



	
	//CHECKING TO MAKE SURE THE SIZE OF THE LIST IS CORRECT EVEN AFTER I'VE REMOVED ALL INSTANCES OF A TARGET VALUE/DATA
	
	



	
	


}
