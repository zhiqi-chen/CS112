/*
    Student: Zhiqi Chen
    Added function: removeAll: remove all of the input item in the item list items, and return true if at least one 
    item is removed, and return false if there were no occurence of the input item
    Partner: Yufan Lin
    
 * ArrayList.java
 *
 * Computer Science 112, Boston University
 */

import java.util.*;

/*
 * A class that implements our simple List interface using an array.
 */
public class ArrayList implements List {
    private Object[] items;     // the items in the list
    private int length;         // # of items in the list
    
    /*
     * Constructs an ArrayList object with the specified maximum size
     * for a list that is initially empty.
     */
    public ArrayList(int maxSize) {
        items = new Object[maxSize];
        length = 0;
    }
    
    /*
     * Constructs an ArrayList object containing the items in the specified
     * array, and with a max size that is twice the size of that array 
     * (to allow room for growth).
     */
    public ArrayList(Object[] initItems) {
        items = new Object[2 * initItems.length];        
        for (int i = 0; i < initItems.length; i++) {
            items[i] = initItems[i];
        }
        
        length = initItems.length;
    }
    
    /*
     * length - returns the number of items in the list 
     */
    public int length() {
        return length;
    }
    
    /* 
     * isFull - returns true if the list is full, and false otherwise
     */
    public boolean isFull() {
        return (length == items.length);
    }
    
    /* getItem - returns the item at position i in the list */
    public Object getItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        return items[i];
    }
    
    /* 
     * addItem - adds the specified item at position i in the list,
     * shifting the items that are currently in positions i, i+1, i+2,
     * etc. to the right by one.  Returns false if the list is full,
     * and true otherwise.
     */
    public boolean addItem(Object item, int i) {
        if (i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        } else if (isFull()) {
            return false;
        }
        
        // make room for the new item
        for (int j = length - 1; j >= i; j--) {
            items[j + 1] = items[j];
        }
        
        items[i] = item;
        length++;
        return true;
    }
    
    /* 
     * removeItem - removes the item at position i in the list,
     * shifting the items that are currently in positions i+1, i+2,
     * etc. to the left by one.  Returns a reference to the removed
     * object.
     */
    public Object removeItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        Object removed = items[i];
        
        // fill in the "hole" left by the removed item
        for (int j = i; j < length - 1; j++) {
            items[j] = items[j + 1];
        }
        items[length - 1] = null;
        
        length--;
        // System.out.println(length);
        return removed;
    }

    public boolean removeAll(Object item){
        boolean result=false;               //set result to false and if the item is removed from the item list items, result will be set to true
        // System.out.println(length);
        int index=0;                        //counter for tracking index
        Object[] new_result=new Object[length];  //create a new Object list to store item
        for (int i=0;i<length;i++){         //Go through the item list and find if the input item occurs
            if(items[i].equals(item)){      
                result=true;
                // System.out.println(i);
            }
            else{                           // if item[i] does not equal to the input item, them store it in the new_result list
                new_result[index]=items[i];
                index++;                    // increase counter
            }                               //O(n) running time
        }
        // System.out.println(index);
        // System.out.println(length);
        for (int j=index;j<length;j++){        //Go through the new_result list and set the elements after position index in the list to null     
            new_result[j]=null;
        }  
        items=new_result;                        //assign the new_result list to the items list              
        length=index;                           // drop the nulls in the list which is the part after position index              
        return result;                          //total O(n) running time.
    }    
    /*
     * toString - converts the list into a String of the form 
     * {item0, item1, ...}
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < length; i++) {
            str = str + items[i];
            if (i < length - 1) {
                str = str + ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    /*
     * iterator - returns an iterator for this list
     */
    public ListIterator iterator() {
        // still needs to be implemented
        return null;
    }

    public static void main(String[] arr) { 
        String[] letters = {"a", "b", "c", "a", "c", "d", "e", "a"};
        ArrayList list1 = new ArrayList(letters);
        System.out.println(list1);
        System.out.println(list1.removeAll("a"));
        System.out.println(list1);
        System.out.println(list1.removeAll("c"));
        System.out.println(list1);
        System.out.println(list1.removeAll("x"));
        System.out.println(list1);
    }
    
}
