/* 
 * LLBag.java
 * 
 * Computer Science 112, Boston University
 * 
 * Student: Zhiqi Chen
 * Partner: Yufan Lin
 * 
 */


import java.util.*;
public class LLBag implements Bag {
    private class Node {                        // Inner class for a node.  We use an inner class so that the LLbag
        private Object item;                    // methods can access the instance variables of the nodes.
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }
    private Node head;     // dummy head node
    private int length;    // # of items in the list
    
    public LLBag() {                        //Constructor with no parameters
        head = new Node(null, null);
        length = 0;
    } 

    public int numItems() {     //numItems - accessor method that returns the number of items in the LLBag
        return length;
    }

    public boolean add(Object item) {           //add - adds the specified item to this LLBag. Returns true if there 
        if (item == null) {                     //is room to add it, and false otherwise. Throws an IllegalArgumentException if the item is null.
            throw new IllegalArgumentException("item must be non-null");
        }
        else{
            Node newNode = new Node(item, null);
            newNode.next=head.next;
            head.next=newNode;
            length=length+1;
            return true;
        }
    }

    public boolean remove(Object item) {            //remove - removes one occurrence of the specified item (if any)
        Node prev=head;                             //from this LLBag.  Returns true on success and false if the
        Node current=head.next;                     //specified item  is not in this LLBag.
        while(current!=null){
            if(current.item.equals(item)){
                prev.next=prev.next.next;
                length=length-1;
                return true;
            }
            current=current.next;
            prev=prev.next;
        }
        return false;
    }

    public boolean contains(Object item) {             //contains - returns true if the specified item is in the LLBag, and false otherwise.
        Node trav=head;
        while(trav.next!=null){
            if(trav.next.item.equals(item)){
                return true;
            }
            trav=trav.next;
        }
        return false;
    }

    public Object grab() {                             //grab - returns  a randomly chosen item in this LLBag.
        if (length == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * length);
        Node trav=head.next;
        for(int i=0;i<whichOne;i++){
            trav=trav.next;
        }
        return trav.item;
    }

    public Object[] toArray() {                         //toArray - return an array containing the current contents of the bag
        Object[] copy = new Object[length];
        Node trav=head.next;
        for (int i = 0; i < length; i++) {
            copy[i] = trav.item;
            trav=trav.next;
        }     
        return copy;
    }

    public String toString() {                  //toString - converts this ArrayBag into a string that can be printed.
        String str = "{";                       //Overrides the version of this method inherited from the Object class.
        Node trav=head.next;
        for (int i = 0; i < length; i++) {
            str = str + trav.item;
            trav=trav.next;
            if (i != length-1 ) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        Bag bag1 = new LLBag();
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;       
        for (int i = 0; i < 5; i++) {
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
    }


}
