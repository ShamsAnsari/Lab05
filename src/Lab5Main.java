import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab5Main {
    final static String FILE_PATH = "output.txt";
    final static int MIN_NUM_ELEMENTS = 7;

    public static void main(String[] args) {
        //Create Output.txt file
        File output = new File(FILE_PATH);
        try {
            output.createNewFile();
            new FileWriter(FILE_PATH, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BinarySearchTree<USD> binarySearchTree = new BinarySearchTree<>();

        int numElements = getUserInput(MIN_NUM_ELEMENTS, Integer.MAX_VALUE - 1, "Enter number of elements: ");

        //Get data for array from user
        write("Enter money in form: X.XX\n");
        for (int i = 0; i < numElements; i++) {
            //Money has to be at least 1 cent
            double input = getElementFromUser(0.009, Double.MAX_VALUE, "\tInsertion = ");
            // Note:cents part, rounding needed because 19.99 * 100 % 100 = .98.  Precision error
            USD dollar = new USD((int) input, (int) Math.round(input * 100 % 100), "USD");
            binarySearchTree.insert(dollar);
        }


        mainLoop:
        while (true) {
            printMainMenu();
            int choice = getUserInput(1, 5, "");
            switch (choice) {
                case 1:
                    binarySearchTree.print();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break mainLoop;

            }
        }


    }

    /**
     * prints main menu with input message
     */
    public static void printMainMenu() {
        System.out.print("Main menu\n" +
                "\t1 - print()\n" +
                "\t2 - insert(E)\n" +
                "\t3 - remove(E)\n" +
                "\t4 - search(E)\n" +
                "\t5 - Exit\n" +
                "Enter a choice: ");
    }

    /**
     * Write obj to both console and to and output file
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

        System.out.print(obj);

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

        int input;
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
     * @param lowerBound Lower bound of input (Exclusive)
     * @param upperBound upper bound of input (inclusive
     * @param msg        message before  user enter input
     * @return user input
     */
    public static double getElementFromUser(double lowerBound, double upperBound, String msg) {

        Scanner scan = new Scanner(System.in);

        double input;
        System.out.print(msg);
        input = scan.nextDouble();
        if (!(input <= upperBound && input > lowerBound)) {
            write("\tBounds of input: [" + lowerBound + ", " + upperBound + "] -- IGNORE: " + input + "\n");

        }
        return input;

    }
}
