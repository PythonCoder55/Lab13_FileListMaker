import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Create variables to store current list file name and list edit status
    private static String currentListFile = null;
    private static boolean needsToBeSaved = false;
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        // Loop until user quits
        while (!quit) {
            // Display menu and get user choice
            displayMenu();
            String choice = SafeInput.getRegExString(in, "Enter your choice (A/D/V/O/S/C/Q):", "[AaDdVvOoSsCcQq]");
            // Call appropriate method based on user choice
            switch (choice.toUpperCase()) {
                case "A":
                    // Add item to list
                    addItemToList();
                    break;
                case "D":
                    // Delete item from list
                    deleteItemFromList();
                    break;
                case "V":
                    // View the list
                    displayList();
                    break;
                case "O":
                    // Open a list file from disk
                    openListFromFile();
                    break;
                case "S":
                    // Save the current list to disk
                    saveListToFile();
                    break;
                case "C":
                    // Clear the current list
                    clearList();
                    break;
                case "Q":
                    // Ask user if they want to quit
                    quit = confirmQuit();
                    break;
            }
        }
        System.out.println("Exiting the program");
        in.close();
    }

    // Method to display menu
    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("V - View the list");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list to disk");
        System.out.println("C - Clear the current list");
        System.out.println("Q - Quit the program");
    }

    // Method to add item to list
    private static void addItemToList() {
        if (currentListFile == null) {
            currentListFile = SafeInput.getNonZeroLenString(in, "No current list. Enter a name for the new list") + ".txt";
        }
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        myArrList.add(item);
        needsToBeSaved = true;
        System.out.println("Item added successfully!");
    }

    // Method to delete item from list if list is not empty
    private static void deleteItemFromList() {
        // Check if list is empty and return if it is
        if (currentListFile == null || myArrList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        // Display the list. Won't be empty if we get here so we don't need to worry about it printing "List is empty" twice
        displayList();
        int itemNumber = SafeInput.getRangedInt(in, "Enter the item number to delete", 1, myArrList.size());
        String deletedItem = myArrList.remove(itemNumber - 1);
        needsToBeSaved = true;
        System.out.println("Item \"" + deletedItem + "\" removed from the list.");
    }

    // Method to display list as long as it is not empty
    private static void displayList() {
        if (currentListFile == null || myArrList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Numbered list:");
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
    }

    // Method to open a list file from disk
    private static void openListFromFile() {
        if (needsToBeSaved) {
            boolean saveBeforeOpen = SafeInput.getYNConfirm(in, "Do you want to save the current list before opening a new one? (Y/N)");
            if (saveBeforeOpen) {
                saveListToFile();
            }
        }
        String filename = SafeInput.getNonZeroLenString(in, "Enter the name of the list file to open (without the .txt extension)");
        currentListFile = filename + ".txt";
        myArrList = loadListFromFile(currentListFile);
        needsToBeSaved = false;
        System.out.println("List loaded successfully.");
    }

    // Method to save the current list to disk
    private static void saveListToFile() {
        if (currentListFile == null) {
            System.out.println("No list to save.");
            return;
        }
        saveListToFile(currentListFile, myArrList);
        needsToBeSaved = false;
        System.out.println("List saved successfully.");
    }

    // Method to clear the current list
    private static void clearList() {
        if (currentListFile == null) {
            System.out.println("List is already empty.");
            return;
        }
        myArrList.clear();
        needsToBeSaved = true;
        System.out.println("List cleared.");
    }

    // Method to confirm if user wants to quit
    private static boolean confirmQuit() {
        if (needsToBeSaved) {
            boolean saveBeforeQuit = SafeInput.getYNConfirm(in, "Do you want to save the current list before quitting? (Y/N)");
            if (saveBeforeQuit) {
                saveListToFile();
            }
        }
        return true;
    }

    // Method to load list from file
    private static ArrayList<String> loadListFromFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading list from file: " + e.getMessage());
        }
        return list;
    }

    // Method to save list to file
    private static void saveListToFile(String filename, ArrayList<String> list) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String item : list) {
                writer.println(item);
            }
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }
}
