package PS.PS4;

/*
 * Student First Name: Zhiqi
 * Student Last Name: Chen
 * Student BU Number: U97832398
 * Purpose: 
 */

public class Set  {
    private static final int SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int size;       // current size of the set
    private int next;       // index to next available slot in the set array
    
    
    public Set() {
        // your code here
        this.set = new int[SIZE];
        size = SIZE;
        next = 0;
    }
    
    public Set(int[] arr) {
        // your code here
        this();
        for (int i = 0; i < arr.length; i++) {
            this.insert(arr[i]);
        }
    }

    public Set clone() {
        // your code here
        Set set_copy = new Set();
        for (int i = 0; i < next; i++) {
            set_copy.insert(this.set[i]);
        }
        return set_copy;
    }

    public void insert(int k) {
        // your code here
        if (!member(k)) {
            try {
                this.set[next] = k;
                next += 1;
            }
            catch (ArrayIndexOutOfBoundsException expected) {
                resize();
                this.set[next] = k;
                next += 1;
            }
        }
    }
  

    /** 
     * This method reallocates the array set to twice as 
     * big and copies all the elements over.
     * This method is called only by the insert method
     * when it cannot insert another element to the set.
     *
     * Note that this is the reason that in this class
     * the member size is not a class variable (i.e. static)
     * and it is not final, because the set can grow and size
     * will be modified accordingly.
     */
    
    private void resize() {
        size *= 2;

	// Create a new array double the size
        int[] temp = new int[size];

	// Copy all the elements from the current set
	// to the new set
        for(int i = 0; i < this.set.length; ++i) {
            temp[i] = set[i];
        }

	// Assign to the set reference the newly
	// resized array that represents the set.
        set = temp;
    }

 
    public String toString()  {
        // your code here
        String str = "";
        if (next == 0) {
            str += "[]";
        } else {
            str += "[";
            for (int i = 0; i < next - 1; i++) {
                str += this.set[i];
                str += ",";
            }
            str += this.set[next - 1];
            str += "]";
        }
        return str;
    } 
     
    public int cardinality() {
        // your code here
        return next;
    }
    
    public boolean isEmpty() {
        // your code here
        if (next == 0) {
            return true;
        }
        return false;
    }
      
    public boolean member(int k) {
        // your code here
        for (int i = 0; i < next; i++) {
            if (this.set[i] == k) {
                return true;
            }
        }
        return false;
    }    
   
    public boolean subset(Set S) {
        // your code here
        for (int i = 0; i < next; i++) {
            if (!S.member(this.set[i])) {
                return false;
            }
        }
        return true;
    }
    
    public boolean equal(Set S) {
        // your code here
        Set set_copy = clone();
        if (set_copy.subset(S) && S.subset(set_copy)) {
            return true;
        }
        return false;
    }
       
    public void delete(int k) {
        // your code here
        if (member(k)) {
            for (int i = 0; i < next; i++) {
                if (this.set[i] == k) {
                    for (int j = i; j < next - 1; j++) {
                        this.set[j] = this.set[j + 1];
                    }
                }
            }
            next -= 1;
        }
    }
  
    public Set union(Set S) {
        // your code here
        Set union = new Set();
        union = S.clone();
        for (int i = 0; i < next; i++) {
            union.insert(this.set[i]);
        }
        return union;
    }
   
    public Set intersection(Set S) {
        // your code here
        Set intersection = new Set();
        for (int i = 0; i < next; i++) {
            if (S.member(this.set[i])) {
                intersection.insert(this.set[i]);
            }
        }
        return intersection;
    }
    
    public Set setdifference(Set S) {
        // your code here
        Set difference = new Set();
        difference = clone();
        for (int i = 0; i < next; i++) {
            if (S.member(this.set[i])) {
                difference.delete(this.set[i]);
            }
        }
        return difference;
    }

}
