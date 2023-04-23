/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Zhiqi Chen
 *     username: zchen530
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }
    
    /* 
     * inner class for a preorder iterator 
     * IMPORTANT: You will not be able to actually use objects from this class
     * to perform a preorder iteration until you have modified the LinkedTree
     * methods so that they maintain the parent fields in the Nodes.
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;
        
        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            // Store a copy of the key to be returned.
            int key = nextNode.key;
            
            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                       (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                
                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }
            
            return key;
        }
    }
    
    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 7, Problem 4
     */
    public int depth_originalVersion(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree_originalVersion(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 4. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree_originalVersion(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree_originalVersion(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree_originalVersion(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }

    /* 
    private static int numSmallerInTree(Node root, int t) {
        int count = 0;

        if (root == null) { // root is the root of the entire tree
            return 0;
        }
        // root.left < root.key and root.right >= root.key, 
        // so run both left and right subtrees to search for the keys that are smaller than t
        if (root.key < t) { 
            count += 1; // if smaller, plus one
            count += numSmallerInTree(root.left, t);
            count += numSmallerInTree(root.right, t);
        } else { // (root.key >= t)
        // because root.right >= root.key, 
        // run only right subtree to search for the keys that are smaller than t
            count += numSmallerInTree(root.right, t);            
        }
        return count;
    }
    */

    /*
     * Find and return the number of even-valued keys in the binary 
     * search tree or subtree whose root node is specified by the parameter
     */
    private static int countEvensInTree(Node root) {
        int count = 0;

        if (root.key % 2 == 0) { // if even value, count plus one
            count += 1;
        }

        if (root.left != null) { // count for left subtree
            count += countEvensInTree(root.left);
        }
        if (root.right != null) { // count for right subtree
            count += countEvensInTree(root.right);
        } 
        return count;
    }

    /*
     * “wrapper” method for countEvensInTree()
     */
    public int countEvens() {
        if (root == null) { // root is the root of the entire tree
            return 0;
        }

        return countEvensInTree(root);
    }
    
    /*
     * uses iteration to determine and return the depth of 
     * the node with that key.
     */
    private static int depthInTree(int key, Node root) {
        int count = 0;

        if (key == root.key) { // if is the root, return count
            return count;
        }
        
        // root.left < root.key and root.right >= root.key, 
        // so run both left and right subtrees to search for the keys that are smaller than t
        if (key < root.key && root.left != null ) {
            count += depthInTree(key, root.left);
            if (count != -1) {
                return count + 1; // plus one for depth counting
            }
        }

        // because root.right >= root.key, 
        // run only right subtree to search for the keys that are smaller than t
        if (key > root.key && root.right != null) {
            count += depthInTree(key, root.right);
            if (count != -1) {
                return count + 1; // plus one for depth counting
            }
        }

        return -1; // if not in the tree or empty tree, return -1
    }

    /*
     * “wrapper” method for depthInTree()
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }

        return depthInTree(key, root);
    }

    private static int deleteMaxInTree(Node root) {
        Node trav = root; 
        Node prevNode = root;
        
        while (trav.right != null) {
            prevNode = trav;
            trav = trav.right;
        }

        if (trav.left != null) {
            prevNode.right = trav.left;
        } else {
            prevNode.right = null;
        }

        int max = trav.key;
        return max;
    }

    public int deleteMax() {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return deleteMaxInTree(root);
    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing from Problem 4 ---");

        System.out.println();
        System.out.println("--------Problem 1 countEvens--------");
        System.out.println();
        System.out.println("(1) Testing on tree from Problem 1, number of even-valued keys in the tree");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {9, 7, 10, 5, 8, 12};
            tree.insertKeys(keys);
            
            int results = tree.countEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on tree from Problem 1, number of even-valued keys in the tree");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {12, 10, 15, 8, 13, 14, 19};
            tree.insertKeys(keys);
            
            int results = tree.countEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();
        System.out.println("--------Problem 2 depth--------");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 2, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            tree.insertKeys(keys);
            
            int results = tree.depth(28);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(1) Testing on tree from Problem 2, depth of 13 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {35, 29, 41, 10, 38, 57, 30, 49, 75};
            tree.insertKeys(keys);
            
            int results = tree.depth(10);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on tree from Problem 2, depth of 50 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {35, 29, 41, 10, 38, 57, 30, 49, 75};
            tree.insertKeys(keys);
            
            int results = tree.depth(60);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == -1);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();
        System.out.println("--------Problem 3 deleteMax--------");
        System.out.println();
        System.out.println("(1) Testing on tree from Problem 3, delete 75");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {35, 29, 41, 10, 38, 57, 30, 49, 75};
            tree.insertKeys(keys);
            
            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println("first deletion: " + results);
            System.out.println("expected results:");
            System.out.println(75);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 75);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on tree from Problem 3, delete 75 and 57");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {35, 29, 41, 10, 38, 57, 30, 49, 75};
            tree.insertKeys(keys);
            
            System.out.println("actual results:");
            int results1 = tree.deleteMax();
            System.out.println("first deletion: " + results1);
            int results2 = tree.deleteMax();
            System.out.println("second deletion: " + results2);
            System.out.println("expected results:");
            System.out.println(75);
            System.out.println(57);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results1 == 75 && results2 == 57);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("--------test cases given by instruction--------");

        System.out.println();    // include a blank line between tests

        LinkedTree tree1 = new LinkedTree();
        System.out.println("empty tree: " + tree1.countEvens());

        int[] keys1 = {4, 1, 3, 6, 5, 2};
        tree1.insertKeys(keys1);
        System.out.println("tree with keys from 1 to 6: " + tree1.countEvens());
                           
        System.out.println();    // include a blank line between tests
       
        LinkedTree tree2 = new LinkedTree();
        System.out.println("empty tree: " + tree2.depth(13));

        int[] keys2 = {37, 26, 42, 13, 35, 56, 30, 47, 70};
        tree2.insertKeys(keys2);
        System.out.println("depth of 13: " + tree2.depth(13));
        System.out.println("depth of 37: " + tree2.depth(37));
        System.out.println("depth of 47: " + tree2.depth(47));
        System.out.println("depth of 50: " + tree2.depth(50));

        System.out.println();    // include a blank line between tests

        LinkedTree tree3 = new LinkedTree();
        System.out.println("empty tree: " + tree3.deleteMax());
        System.out.println();

        int[] keys3 = {37, 26, 42, 13, 35, 56, 30, 47, 70};
        tree3.insertKeys(keys3);
        tree3.levelOrderPrint();
        System.out.println();

        System.out.println("first deletion: " + tree3.deleteMax());
        tree3.levelOrderPrint();
        System.out.println();

        System.out.println("second deletion: " + tree3.deleteMax());
        tree3.levelOrderPrint();
                           
        /*
         * Add at least two unit tests for each method from Problem 6.
         * Test a variety of different cases. 
         * Follow the same format that we have used above.
         */
        
    }
}
