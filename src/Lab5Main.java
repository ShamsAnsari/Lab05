import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lab 5
 * @author  Shams Ansari
 * TO learn and implement Binary Search Trees and their traversal methods
 */
public class Lab5Main {
    final static String FILE_PATH = "output.txt";
    final static double MINIMUM_DOLLAR = 0.01;
    final static double MAXIMUM_DOLLAR = Double.MAX_VALUE;

    /**
     * 1. initialize output file
     * 2. Create BST
     * 3. seed BST with data
     * 4. Run main loop
     *
     * @param args
     */
    public static void main(String[] args) {
        //Create Output.txt file
        initFile();

        //Create BST
        BinarySearchTree<USD> binarySearchTree = new BinarySearchTree<>();

        //Seed BST
        double[] seed = {23, 18, 12, 20, 44, 35, 52, 17.99, 0.01, 25.5, 50, 39.1};
        for (double input : seed) {
            USD dollar = new USD((int) input, (int) Math.round(input * 100 % 100), "USD");
            binarySearchTree.insert(dollar);
        }

        /*
        Main Loop
           1. print menu
           2. get user input [1 to 7]
           based of user input:
           - if input == 1 // print the traversals
           - if input == 2 // print the tree in 2d form
           - if input == 3 // insert a node
           ---- get value of dollar from user and validate
           ---- insert dollar into tree
           - if input == 4 // remove a node
           ---- get value of dollar from user and validate
           ---- remove that value from tree
           - if input == 5 // clear tree
           - if input == 6 // search for a node
           ---- get value of dollar from user and validate
           ---- search node
           ---- print node and its children
           - if input == 7 // exit Main loop
         */
        mainLoop:
        while (true) {
            printMainMenu();
            int choice = getUserInput(1, 7, "");
            switch (choice) {
                case 1:// print()
                    write(binarySearchTree.print() + "\n");
                    break;
                case 2: //print2D()
                    String twoD = binarySearchTree.toString();
                    System.out.println(twoD);
                    write(twoD + "\n");
                    break;
                case 3: // insert(E)
                    USD dollar = getElementFromUser();
                    if (dollar != null) {
                        // Binary tree will handle duplicate
                        binarySearchTree.insert(dollar);
                    }
                    break;
                case 4: // remove(E)
                    dollar = getElementFromUser();
                    if (dollar != null) {
                        binarySearchTree.remove(dollar);
                    }
                    break;
                case 5://clear()
                    binarySearchTree.clear();
                    break;
                case 6:// search(E)
                    dollar = getElementFromUser();
                    if (dollar != null) {
                        BinaryTreeNode<USD> node = binarySearchTree.search(dollar);
                        if (node != null) {
                            BinaryTreeNode<USD> left_node = (node.getLeft() == null) ? null : node.getLeft();
                            BinaryTreeNode<USD> right_node = (node.getRight() == null) ? null : node.getRight();
                            System.out.println("Data: " + node + " Left: " + left_node + " Right: " + right_node);
                        } else {
                            System.out.println(node);
                        }
                    }

                    break;
                case 7:// exit()
                    break mainLoop;

            }
        }


    }

    /**
     * Initializes output file.
     */
    public static void initFile() {
        File output = new File(FILE_PATH);
        try {
            output.createNewFile();
            new FileWriter(FILE_PATH, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints main menu with input message
     */
    public static void printMainMenu() {
        System.out.print("Main menu\n" +
                "\t1 - print()\n" +
                "\t2 - print2D()\n" +
                "\t3 - insert(E)\n" +
                "\t4 - remove(E)\n" +
                "\t5 - clear()\n" +
                "\t6 - search(E)\n" +
                "\t7 - Exit\n" +
                "Enter a choice: ");
    }

    /**
     * Write obj  to output file
     * Output file path is "FILE_PATH" variable
     * Appends to file
     *
     * @param obj obj to print
     */
    public static void write(Object obj) {

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH, true); //Appends to file
            writer.write(obj.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets user input (int) and validates in loop
     *
     * @param lowerBound Lower bound of input (inclusive)
     * @param upperBound upper bound of input (inclusive)
     * @param msg        message before  user enter input
     * @return user input
     */
    public static int getUserInput(int lowerBound, int upperBound, String msg) {

        Scanner scan = new Scanner(System.in);

        int input = 0;
        A:
        while (true) {
            System.out.print(msg);
            input = scan.nextInt();
            if (input <= upperBound && input >= lowerBound) {
                break;
            }
            System.out.print("\tBounds of input: [" + lowerBound + ", " + upperBound + "]\n");

        }
        return input;

    }


    /**
     * Gets a dollar(USD) element from user
     * If dollar is negative or not in range, then ignore and returns null
     *
     * @return
     */
    public static USD getElementFromUser() {

        String msg = "\tNode = ";
        Scanner scan = new Scanner(System.in);

        double input = 0;
        System.out.print(msg);
        String line = scan.next();
        try {
            input = Double.parseDouble(line);
        } catch (Exception e){
            String errorMsg = "\tIGNORE: \"" + line + "\" REASON: Not a number\n";
            write(errorMsg);
            System.out.print(errorMsg);
            return null;
        }

        USD dollar = null;
        if (!(input <= MAXIMUM_DOLLAR && input > MINIMUM_DOLLAR)) {
            String errorMsg = "\tIGNORE: " + input + " REASON: Bounds of input: [" + MINIMUM_DOLLAR + ", " + MAXIMUM_DOLLAR + "]\n";
            write(errorMsg);
            System.out.print(errorMsg);
        } else {
            dollar = new USD((int) input, (int) Math.round(input * 100 % 100), "USD");
        }
        return dollar;
    }
}
