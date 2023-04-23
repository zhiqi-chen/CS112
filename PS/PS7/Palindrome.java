/*
 * Palindrome.java
 * 
 * Computer Science 112, Boston University
 * 
 * Student: Zhiqi Chen
 * Partner: Yufan Lin
 * 
 */
import java.lang.Character; 
public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String str){                
        boolean Pal=false;                              //initial boolean setting
        LLQueue<Character> queue=new LLQueue<Character>();          //create a queue in order to store the string in the correct order
        LLStack<Character> stack=new LLStack<Character>();          //create a stack in order to store the string in the reverse orfer
        str=str.toUpperCase();                                      //set the input string to upper case
        int char_length=0;                                          // store the length of characters in the string 
        for (int i=0;i<str.length();i++){                           //Go through the string
            if(Character.isLetter(str.charAt(i))== true){              // Check if it's a character
                queue.insert(str.charAt(i));                            // store it in the stack and queue
                stack.push(str.charAt(i));
                char_length=char_length+1;
            }
        }
        for(int j=0;j<char_length/2;j++){                       //the queue peek will go through the string from left to right
            if(queue.peek()==stack.peek()){                     // the stack peek will go through the string from right to left
                Pal=true;                                       // so we only need to go half way through the string
            }                                                   
            else{
                Pal=false;
            }
            queue.remove();                                     // remove and pop whenever we finish one comparison
            stack.pop();
        }    
        return Pal;
    }
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
    }
}