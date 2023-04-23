package PS.PS3;

/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;

    // the array of digits for this BigInt object
    private int[] digits;

    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that represents the
     * number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1; // 0 has one sig. digit--the rightmost 0!
    }

    public BigInt(int[] arr) {

        this.digits = new int[SIZE];
        this.numSigDigits = 1;

        if (arr == null) {
            throw new IllegalArgumentException("cannot be null");
        } else if (arr.length > SIZE) {
            throw new IllegalArgumentException("exceeds the maximum size");
        }

        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.forDigit(arr[i], 10) > 48 && Character.forDigit(arr[i], 10) <= 57) {
                counter = i; // index for first non-zero digit
                break;
            } else if (Character.forDigit(arr[i], 10) == 48) {
                counter = arr.length - 1;
            } else {
                throw new IllegalArgumentException("invalid input");
            }
        }

        this.numSigDigits = arr.length - counter;

        for (int j = counter; j < arr.length; j++) {
            if (Character.forDigit(arr[j], 10) >= 48 && Character.forDigit(arr[j], 10) <= 57) {
                this.digits[j - counter + (SIZE - this.numSigDigits)] = arr[j];
            } else {
                throw new IllegalArgumentException("invalid input");
            }
        }            
    }

    public int getNumSigDigits() {
        return numSigDigits;
    }

    public String toString() {
        String str_BigInt = "";
        for (int i = 0; i < numSigDigits; i++) {
            str_BigInt += this.digits[i + (SIZE - this.numSigDigits)];
        }
        return str_BigInt;
    }

    public BigInt(int n) {

        this.digits = new int[SIZE];

        if (n < 0) {
            throw new IllegalArgumentException("cannot be negative");
        } else if (n == 0) {
            this.digits[SIZE - 1] = 0;
            this.numSigDigits = 1;
        } else {
            int tenth = n;
            int counter = 0;
            while (tenth > 0) {
                tenth /= 10;
                counter++;
            }
            this.numSigDigits = counter;
            for (int i = 0; i < counter; i++) {
                n %= Math.pow(10, counter-i);
                this.digits[i + (SIZE - this.numSigDigits)] = n / (int) Math.pow(10, (counter-i)-1);
            }
        }
    }

    public int compareTo(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("cannot be null");
        }
        if (this.numSigDigits < other.numSigDigits) {
            return -1;
        } else if (this.numSigDigits > other.numSigDigits){
            return 1;
        } else {
            for (int i = 0; i < SIZE; i++) {
                if (this.digits[i] < other.digits[i]) {
                    return -1;
                } else if (this.digits[i] > other.digits[i]){
                    return 1;
                }
            }
        }
        return 0;
    }

    public BigInt add(BigInt other) {

        int[] sum = new int[SIZE]; 
        int sum_digits;
        int carry = 0;
        int length = 0;

        if (other == null) {
            throw new IllegalArgumentException("cannot be null");
        }

        if (other.numSigDigits > SIZE || this.numSigDigits > SIZE) {
            throw new ArithmeticException("overflow");
        }

        if (this.numSigDigits <= other.numSigDigits) {
            length = other.numSigDigits;
        } else {
            length = this.numSigDigits;
        }

        for (int i = SIZE - 1; i >= 0; i--) {
            sum_digits = this.digits[i] + other.digits[i];    
            if (carry == 1) {
                sum_digits += carry;
                if (sum_digits >= 10) {
                    if (i == 0) {
                        throw new ArithmeticException ("result overflows");
                    }
                    sum[i] = sum_digits - 10;
                    carry = 1;
                    
                } else {
                    sum[i] = sum_digits;
                    carry = 0;
                }
            } else {
                if (sum_digits >= 10) {
                    sum[i] = sum_digits - 10;
                    carry = 1;
                } else {
                    sum[i] = sum_digits;
                    carry = 0;
                }
            }
        }
        BigInt result = new BigInt(sum);
        return result;
    }

    public BigInt mul(BigInt other) {

        int long_length = 0;
        int short_length = 0;
        int[] long_digits = new int[SIZE];
        int[] short_digits = new int[SIZE];

        int product_digits;
        int[] product_forSum = new int[SIZE];

        boolean carry = false;
        int carry_num = 0;
        BigInt product = new BigInt();

        if (other == null) {
            throw new IllegalArgumentException("cannot be null");
        }

        if (other.numSigDigits > SIZE || this.numSigDigits > SIZE) {
            throw new ArithmeticException("overflow");
        }

        if (this.numSigDigits <= other.numSigDigits) {
            long_length = other.numSigDigits;
            short_length = this.numSigDigits;
            long_digits = other.digits;
            short_digits = this.digits;
        } else {
            long_length = this.numSigDigits;
            short_length = other.numSigDigits;
            long_digits = this.digits;
            short_digits = other.digits;
        }

        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = SIZE - 1; j >= 0; j--) {
                product_digits = short_digits[i] * long_digits[j];
                if (carry == true) {
                    product_digits += carry_num;
                    if (product_digits >= 10) {
                        carry_num = product_digits / 10;
                        product_forSum[j] = product_digits % 10;
                        carry = true;
                        if (j == 0) {
                            throw new ArithmeticException ("result overflows");
                        }
                    } else {
                        product_forSum[j] = product_digits;
                        carry = false;
                    }
                } else {
                    if (product_digits >= 10) {
                        carry_num = product_digits / 10;
                        product_forSum[j] = product_digits % 10;
                        carry = true;
                    } else {
                        product_forSum[j] = product_digits;
                        carry = false;
                    }
                }
            }
            // shift one digit left for each next row
            for (int n = i; n < SIZE - 1; n++) {
                for(int k = 0; k < SIZE - 1; k++){
                    product_forSum[k] = product_forSum[k + 1];
                }
            }
            // sum all the product row
            product = product.add(new BigInt(product_forSum));            
        }
        BigInt result = product;
        return result;
    }


    public static void main(String[] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();

        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
       

        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        /*
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        */
    }
}
