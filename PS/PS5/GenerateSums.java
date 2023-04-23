package PS.PS5;

public class GenerateSums {

    public static String generateSums(int n) {

        int sum = 0;
        String result = "";
        String adding = "";

        if (n > 0) {
            for (int i = 1; i <= n; i++) {

                // formulate the adding part
                if (i != 1) {
                    adding += (" + " + i);
                } else {
                    adding += i;
                }

                // calculate sum
                sum = (i * (i + 1) / 2);

                // add adding part and =
                if (sum != 1) {
                    result += adding;
                    result += " = ";
                }

                // add sum
                result += sum;

                // add nextline
                if (i != n) {
                    result += "\n";
                }
            }
        }
        return result;
    }

//    public static void main(String [] args) {
//        System.out.println(generateSums(6));
//    } 

}
