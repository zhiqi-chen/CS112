package PS.PS4;

public class ArrayRecursion {

    public static boolean search(Object item, Object[] arr, int start) {
        if (arr == null) {
            throw new IllegalArgumentException();
        }
      
        boolean found = false;
        if (arr[start].equals(item)) {
            found = true;
        } else {
            if (start + 1 < arr.length) { // if has next arr item
                found = search(item, arr, start += 1);
            }
        }
        return found;
    }

    public static String reverseArrayToString(Object[] arr, int index ) {
        if (arr == null) {
            return "";
        } else if (arr.length == 0) {
            return "[]";
        } else {
            if (index == 0) {
                return reverseArrayToString(arr, index += 1) + "," + arr[index - 1] + "]";
            } else if (index == arr.length - 1) {
                return "[" + arr[index];
            } else {
                return reverseArrayToString(arr, index += 1) + "," + arr[index - 1];
            }
        }
    }
    
    public static void main(String [] args) {
        Object[] arr = {"!", "hello", "world", "!", "world", "hello"};
        System.out.println(search("hello", arr, 0));
        System.out.println(search("hello", arr, 2));

        String a[] = { "abc", "def", "ghi", "klm", "nop", "qrs" };
        System.out.println(reverseArrayToString(a, 0));
    }
}
