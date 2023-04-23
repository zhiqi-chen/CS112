package PS.PS4;

public class StringRecursion {

    public static void printReverse(String str) {
        if (!(str.equals(null) && str.isEmpty())) {
            if (str.length() - 1 >= 0) {
                System.out.print(str.charAt(str.length() - 1));
                str = str.substring(0, str.length() - 1);
                printReverse(str);
            }
        }
    }

    public static String trim(String str) {

        if (str.charAt(str.length() - 1) == ' ') {
            str = str.substring(0, str.length() - 1);
            str = trim(str);
        } else if (str.charAt(0) == ' ') {
            str = str.substring(1);
            str = trim(str);
        }        
        return str;
    }

    public static int find(char ch, String str) {
        if (str.equals(null) || str.isEmpty()) {
            return -1;
        } else {
            if (str.charAt(0) == ch) {
                return 0;
            } else {
                str = str.substring(1);
                int index_counter = find(ch, str);
                if (index_counter == -1) {
                    return -1;
                } else {
                    return index_counter + 1;
                }
            } 
        }
    }

    public static String weave(String str1, String str2) {
        String weave_result = "";
        if (str1.equals(null) || str2.equals(null)) {
            throw new IllegalArgumentException("String cannot be null");
        } else if (str1.equals("") || str2.equals("")) {
            weave_result += str1 + str2;
            return weave_result;
        } else {
            weave_result += Character.toString(str1.charAt(0)) + Character.toString(str2.charAt(0));
            str1 = str1.substring(1);
            str2 = str2.substring(1);
            weave_result += weave(str1, str2);
        }
        return weave_result;
    }

    public static int indexOf(char ch, String str) {
        return find(ch, str);
    }

    public static void main(String [] args) {

        printReverse("Terriers");
        System.out.println();
        
        System.out.print("(start)");
        String trim1 = trim(" hello world    ");
        System.out.print(trim1);
        System.out.println("(end)");
        System.out.print("(start)");
        String trim2 = trim("recursion  ") ;
        System.out.print(trim2);
        System.out.println("(end)");
        System.out.println();

        int find1 = find('b', "Rabbit");
        System.out.println(find1);
        int find2 = find('P', "Rabbit");
        System.out.println(find2);
        System.out.println();

        System.out.println( weave("aaaa", "bbbb") );
        System.out.println( weave("hello", "world") );
        System.out.println( weave("recurse", "NOW") );
        System.out.println( weave("hello", "")  );
        System.out.println( weave("", "")  );
        System.out.println();

        System.out.println( indexOf('b', "Rabbit") ); 
        System.out.println( indexOf('P', "Rabbit") );
    }
}
