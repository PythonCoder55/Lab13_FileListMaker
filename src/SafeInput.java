import java.util.Scanner;
public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int value = 0;
        boolean isValidInput;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                isValidInput = true;
            } else {
                String trash = pipe.next(); // Read and discard invalid input
                System.out.println("Invalid input. Please enter an integer.");
                isValidInput = false;
            }

        } while (!isValidInput);

        pipe.nextLine(); // Clear the newline character from the pipe

        return value;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double value = 0;
        boolean isValidInput;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                isValidInput = true;
            } else {
                String trash = pipe.next(); // Read and discard invalid input
                System.out.println("Invalid input. Please enter a double.");
                isValidInput = false;
            }

        } while (!isValidInput);

        pipe.nextLine(); // Clear the newline character from the pipe

        return value;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value = 0;
        boolean isValidInput;

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                if (value >= low && value <= high) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid input. Please enter an integer within the range.");
                    isValidInput = false;
                }
            } else {
                String trash = pipe.next(); // Read and discard invalid input
                System.out.println("Invalid input. Please enter an integer.");
                isValidInput = false;
            }

        } while (!isValidInput);

        pipe.nextLine(); // Clear the newline character from the pipe

        return value;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double value = 0;
        boolean isValidInput;

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                if (value >= low && value <= high) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a double within the range.");
                    isValidInput = false;
                }
            } else {
                String trash = pipe.next(); // Read and discard invalid input
                System.out.println("Invalid input. Please enter a double.");
                isValidInput = false;
            }

        } while (!isValidInput);

        pipe.nextLine(); // Clear the newline character from the pipe

        return value;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean isValidInput;
        boolean isConfirmed = false;

        do {
            System.out.print("\n" + prompt + ": ");

            String input = pipe.nextLine().trim().toLowerCase();

            if (input.equals("y")) {
                isConfirmed = true;
                isValidInput = true;
            } else if (input.equals("n")) {
                isConfirmed = false;
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please enter Y, y, N or n.");
                isValidInput = false;
            }

        } while (!isValidInput);

        return isConfirmed;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String value;
        boolean isValidInput;

        do {
            System.out.print("\n" + prompt + " (RegEx: " + regEx + "): ");

            value = pipe.nextLine();

            if (value.matches(regEx)) {
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please enter a string that matches the pattern.");
                isValidInput = false;
            }

        } while (!isValidInput);

        return value;
    }

    public static void prettyHeader(String msg) {
        int headerWidth = 60;
        int msgLength = msg.length();
        int spaceLength = (headerWidth - msgLength - 6) / 2; // Calculate the number of spaces on each side of the message

        String starsLine = "";
        String messageLine = "";

        for (int i = 0; i < headerWidth; i++) {
            starsLine += "*";
        }

        messageLine += "***";
        for (int i = 0; i < spaceLength; i++) {
            messageLine += " ";
        }
        messageLine += msg;
        for (int i = 0; i < spaceLength; i++) {
            messageLine += " ";
        }
        if (msgLength % 2 != 0) {
            messageLine += " ";
        }
        messageLine += "***";

        System.out.println(starsLine);
        System.out.println(messageLine);
        System.out.println(starsLine);
    }
}
