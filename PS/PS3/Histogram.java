package PS.PS3;

/* File: Histogram.java
 * Author: CS112 Instructor
 * 
 * Purpose: This is a potential solution to the
 * Histogram problem.
 */

//  Collaborator：Yufan Lin


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]
    private static int count=0;
    private static boolean more_input=true;
    public static double [] output= new double[MAX_NUMBERS];
    //private static final int BIN_SIZE = ??           // size of each bin
   
    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    // This method should create and return an array of integers
    // that represents the resulting histogram from the numbers
    // entered and passed to the method.

    public static int [] calculateHistogram( double [] numbers ) {
        int count_0_10=0;
        int count_10_20=0;
        int count_20_30=0;
        int count_30_40=0;
        int count_40_50=0;
        int count_50_60=0;
        int count_60_70=0;
        int count_70_80=0;
        int count_80_90=0;
        int count_90_100=0;
        // System.out.println(numbers.length);
        for (int i=0; i<numbers.length;i++){
            if(numbers[i]>UPPER_BOUND || numbers[i]<LOWER_BOUND){
                throw  new IllegalArgumentException();
            }
            if(0<=numbers[i] && numbers[i]<=10){
                count_0_10=count_0_10+1;
            }
            else if(10<numbers[i] && numbers[i]<=20){
                count_10_20=count_10_20+1;
            }
            else if(20<numbers[i] && numbers[i]<=30){
                count_20_30=count_20_30+1;
            }
            else if(30<numbers[i] && numbers[i]<=40){
                count_30_40=count_30_40+1;
            }
            else if(40<numbers[i] && numbers[i]<=50){
                count_40_50=count_40_50+1;
            }
            else if(50<numbers[i] && numbers[i]<=60){
                count_50_60=count_50_60+1;
            }
            else if(60<numbers[i] && numbers[i]<=70){
                count_60_70=count_60_70+1;
            }
            else if(70<numbers[i] && numbers[i]<=80){
                count_70_80=count_70_80+1;
            }
            else if(80<numbers[i] && numbers[i]<=90){
                count_80_90=count_80_90+1;
            }
            else {
                count_90_100=count_90_100+1;
            }
        }
        // System.out.println(count_0_10);
        int[] histogram_list={count_0_10,count_10_20,count_20_30,count_30_40,count_40_50,count_50_60,count_60_70,count_70_80,count_80_90,count_90_100};
        return histogram_list;
    }

    public static int findBin( double num ) {
        double[] num_list={num};
        int index=-1;
        int [] histogram_list=calculateHistogram(num_list);
        for (int i=0;i<histogram_list.length;i++){
            if (histogram_list[i] == 1){
                index=i;
                break;
            }
        }
        return index;
    }

    public static String toString( int [] histogram ) {
        StringBuilder h =new StringBuilder();
        // System.out.println(histogram[0]);
        h.append("[0..10]:" +"*".repeat(histogram[0]));
        h.append("\n(10..20]:" +"*".repeat(histogram[1]));
        h.append("\n(20..30]:" +"*".repeat(histogram[2]));
        h.append("\n(30..40]:" +"*".repeat(histogram[3]));
        h.append("\n(40..50]:" +"*".repeat(histogram[4]));
        h.append("\n(50..60]:" +"*".repeat(histogram[5]));
        h.append("\n(60..70]:" +"*".repeat(histogram[6]));
        h.append("\n(70..80]:" +"*".repeat(histogram[7]));
        h.append("\n(80..90]:" +"*".repeat(histogram[8]));
        h.append("\n(90..100]:" +"*".repeat(histogram[9]));
        return h.toString();
    }

    public static boolean validInput( double num ) {
        boolean valid=true;
        if(num>UPPER_BOUND || num<LOWER_BOUND){
            valid=false;
        }
        return valid;
    }

    public static double[] inputNumbers( Scanner scan ) {
        do{
            double num=Double.parseDouble(scan.nextLine());
            if(num == SENTINAL){
                more_input=false;
            }
            else if(count>MAX_NUMBERS){
                throw new IllegalArgumentException("too many input");
            }
            else if(validInput(num) == false){
                throw new IllegalArgumentException("enter a correct input");
            }
            else{
                // System.out.println(count);
                output[count]=num;
                count=count+1;
            }
        }while (more_input);
        double[] new_output=new double[count];
        for (int i=0;i<new_output.length;i++){
            new_output[i]=output[i];
        }
        return new_output;
    }

    
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	    me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	return (name+" ").split(" ")[0]; 
    }

    /* 
     * local main test driver
     *
     */
    public static void main(String args[]) {  

        // Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );
    
        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();
    
        String heading = getHeaderAsString( user );
    
        // Print out welcome message
        System.out.println( heading ); 
            
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );
    
        // Call the method to reate the array histogram
        int[] histogram = calculateHistogram( numbers );
    
        // Print the historgram
        System.out.println( toString( histogram ) );
    }
}
