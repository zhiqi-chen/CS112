package PS.PS1;

/*
 * Problem Set 1
 * 
 * A simple interactive program that performs operations 
 * on a set of three integers.
 *
 * CS112
 *
 */

import java.util.*;

/*
 * Program Class Defintion
 */
public class SimpleStats {

    /*
     * printMenu()
     *
     * Method to display user options.
     */
    public static void printMenu() {
        System.out.println("(0) Enter new numbers");
        System.out.println("(1) Find the largest");
        System.out.println("(2) Compute the sum");
        System.out.println("(3) Compute the range (largest - smallest)");
        System.out.println("(4) Compute the average");
        System.out.println("(5) Print the numbers in ascending order");
        System.out.println("(6) Quit");
        System.out.println();
    }
    
    /*** PUT YOUR SEPARATE METHODS FOR OPTIONS 1-5 HERE 
     * @return ***/
    
    public static int method1(int n1, int n2, int n3) {
        int largest = 0;
        if (n1 <= n2) {
            if (n2 >= n3) {
                largest = n2;
            } else if (n2 <= n3) {
                largest = n3;
            }
        } else if (n1 >= n2) {
            if (n1 >= n3) {
                largest = n1;
            } else if (n1 <= n3) {
                largest = n3;
            }
        }
        return largest;
    }

    public static int method2(int n1, int n2, int n3) {
        int sum = 0;
        sum = n1 + n2 + n3;
        return sum;
    }

    public static int method3(int n1, int n2, int n3) {
        int range = 0;
        if (n1 <= n2) {
            if (n2 >= n3) {
            // largest = n2;
                if (n1 >= n3) {
                // smallest = n3
                    range = n2 - n3;
                } else if (n1 <= n3) {
                // smallest = n1
                    range = n2 - n1;
                }
            } else if (n2 <= n3) {
            // largest = n3;
                if (n2 >= n1) {
                // smallest = n1
                    range = n3 - n1;
                } else if (n2 <= n1) {
                // smallest = n2
                    range = n3 - n2;
                }
            }
        } else if (n1 >= n2) {
            if (n1 >= n3) {
            // largest = n1;
                if (n2 >= n3) {
                // smallest = n3
                    range = n1 - n3;
                } else if (n2 <= n3) {
                // smallest = n2
                    range = n1 - n2;
                }
            } else if (n1 <= n3) {
            // largest = n3;
                if (n1 >= n2) {
                // smallest = n2
                    range = n3 - n2;
                } else if (n1 <= n2) {
                // smallest = n1
                    range = n3 - n1;
                }
            }
        }
        return range;        
    }

    public static float method4(int n1, int n2, int n3) {
        float average = 0;
        average = method2(n1, n2, n3) / 3; 
        return average;
    }

    public static void method5(int n1, int n2, int n3) {
        if (n1 <= n2) {
            if (n2 >= n3) {
            // largest = n2;
                if (n1 >= n3) {
                // smallest = n3
                    // n3 n1 n2
                    System.out.println(n3 + " " + n1 + " " + n2);
                } else if (n1 <= n3) {
                // smallest = n1
                    // n1 n3 n2
                    System.out.println(n1 + " " + n3 + " " + n2);
                }
            } else if (n2 <= n3) {
            // largest = n3;
                if (n2 >= n1) {
                // smallest = n1
                    // n1 n2 n3
                    System.out.println(n1 + " " + n2 + " " + n3);
                } else if (n2 <= n1) {
                // smallest = n2
                    // n2 n1 n3
                    System.out.println(n2 + " " + n1 + " " + n3);
                }
            }
        } else if (n1 >= n2) {
            if (n1 >= n3) {
            // largest = n1;
                if (n2 >= n3) {
                // smallest = n3
                    // n3 n2 n1
                    System.out.println(n3 + " " + n2 + " " + n1);
                } else if (n2 <= n3) {
                // smallest = n2
                    // n2 n3 n1
                    System.out.println(n2 + " " + n3 + " " + n1);
                }
            } else if (n1 <= n3) {
            // largest = n3;
                if (n1 >= n2) {
                // smallest = n2
                    // n2 n1 n3
                    System.out.println(n2 + " " + n1 + " " + n3);
                } else if (n1 <= n2) {
                // smallest = n1
                    // n1 n2 n3
                    System.out.println(n1 + " " + n2 + " " + n3);
                }
            }
        }        
    }
    
    /*
     * main()
     *
     * Program execution begins with this method.
     */
    public static void main(String[] args) {
        // Create an instance of a scanner object and
	// connect to the standard input device
        Scanner scan = new Scanner(System.in);
        

        // variable declarations and initializations as needed
        boolean more_input = true;
        boolean first_choice = true;
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        
	/*
 	 * Control loop
 	 */
        do {
	    // If numbers have been entered (choice 0) has been selected, output the numbers


	    // Call the method which displays the menu choices
            printMenu();

	    // Prompt to enter the choice of operation
            System.out.println("Enter your choice: ");
            int choice = scan.nextInt();
            
            /*
             * Expand this conditional statement to correctly process all choices.
             * Make sure to follow the guidelines in the assignment for
             * doing so.
             */
            if (choice == 0) {
                System.out.print("Enter three new numbers: ");
		// Input the three numbers
                n1 = scan.nextInt();
                n2 = scan.nextInt();
                n3 = scan.nextInt();
                first_choice = false;
		
		// Verify the numbers entered by printing them out
                System.out.println(n1 + " " + n2 + " " + n3 );
		
            } else if (choice == 1) {
                if (first_choice == true) {
                    System.out.println("You cannot choose option (1) unless you have entered three numbers.");
                }
                else {
                    System.out.println("Result: " + method1(n1, n2, n3));
                }
            } else if (choice == 2) {
                if (first_choice == true) {
                    System.out.println("You cannot choose option (2) unless you have entered three numbers.");
                }
                else {
                    System.out.println("Result: " + method2(n1, n2, n3));
                }
            } else if (choice == 3) {
                if (first_choice == true) {
                    System.out.println("You cannot choose option (3) unless you have entered three numbers.");
                }
                else {
                    System.out.println("Result: " + method3(n1, n2, n3));
                }
            } else if (choice == 4) {
                if (first_choice == true) {
                    System.out.println("You cannot choose option (4) unless you have entered three numbers.");
                }
                else {
                    System.out.println("Result: " + method4(n1, n2, n3));
                }
            } else if (choice == 5) {
                if (first_choice == true) {
                    System.out.println("You cannot choose option (5) unless you have entered three numbers.");
                }
                else {
                    System.out.println("Result: ");
                    method5(n1, n2, n3);
                }
            } else if (choice == 6) {
                more_input = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println();
        } while (more_input);
        
        System.out.println("Have a nice day!");
        scan.close();
    }
}
