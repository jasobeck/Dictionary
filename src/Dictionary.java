// Jane Sobeck
// 3/15/2024
// CS 145 Lab 6
//     The purpose of these programs program is to help me understand both Binary search trees
// as well as how to traverse the tree in different orders.

//THIS IS AN ALPHABETICALLY SORTED BINARY SEARCH TREE!!!!
//DOES NOT BALANCE HEIGHT, BUT KEEPS NODES SORTED/RESORTS WHEN ADDING/DELETING/MODIFYING ENTRIES
//ALSO ADDED SEARCH METHOD THAT USES ALPHABETIC BINARY SEARCH, RATHER THAN NORMAL TRAVERSAL OF TREE

import java.util.Scanner;

/* Dictionary class creates the DictionaryTree object and handles the user input, and passes
* necessary variables to the DictionaryTree object for the DictionaryTree to handle
*  
*/
public class Dictionary {

    // Main creates the dicitonary tree and starts the menu process
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        char userSelection = '0';
        DictionaryTree tree = new DictionaryTree(null);
        System.out.println("Welcome to the People Dictionary program!");

        while (userSelection != 'Q') {
            printMenu();
            userSelection = selectFunction(userInput, userSelection);
            exeSelection(userInput, userSelection, tree);
        }
        userInput.close();
    }

    // Prints the menu options to the console
    public static void printMenu() {
        System.err.println();
        System.out.println("Please select an option:");
        System.out.println("    (A)dd an entry");
        System.out.println("    (D)elete an entry");
        System.out.println("    (M)odify an entry");
        System.out.println("    (S)earch for an entry via keyword");
        System.out.println("    (L)ist all entries");
        System.out.println("    (Q)uit the program");
    }

    // gets the char from the user to select which function to use
    public static char selectFunction(Scanner userInput, char userSelection) {
        userSelection = Character.toUpperCase((userInput.nextLine()).charAt(0));
        return userSelection;
    }

    // takes the user selection and executes the corresponding process
    public static void exeSelection(Scanner userInput, char userSelection, DictionaryTree tree) {
        switch (userSelection) {
            case 'A':
                addInfo(userInput, tree);
                break;

            case 'D':
                if (tree.overallRoot == null) {
                    System.out.println("No entries to delete, please add an entry first");
                    break;
                } else {
                    delInfo(userInput, tree);
                }
                break;

            case 'M':
                if (tree.overallRoot == null) {
                    System.out.println("No entries to modify, please add an entry first");
                    break;
                } else {
                    modNode(userInput, tree);
                }
                break;

            case 'S':
                if (tree.overallRoot == null) {
                    System.out.println("No entries to search, please add an entry first");
                    break;
                }
                TreeNode tempNode = searchInfo(userInput, tree);
                if (tempNode != null) {
                    tempNode.displayData();
                }
                break;

            case 'L':
                if (tree.overallRoot == null) {
                    System.out.println("0 entries in list, please add an entry first");
                    break;
                }
                System.out.println("(a)ll entry data, or just the (k)eywords?");
                char ak = Character.toUpperCase(userInput.nextLine().charAt(0));

                if (ak == 'A') {
                    tree.listAllNodes();
                } else {
                    System.out.println();
                    tree.listKeywords();
                }
                System.out.println();
                System.out.println(tree.entryCount + " entries in dictionary tree");

            case 'Q':
                break;

            default:
                System.out.println("Please enter a valid selection char!");
                break;
        }

    }

    // Creates a temp node and attaches user inputted data to temp node's datatypes.
    // Confirms if info is correct then adds tempNode to the tree if so
    public static void addInfo(Scanner userInput, DictionaryTree tree) {
        boolean keywordExists = true;
        while (keywordExists) {
            System.out.println("Please enter a keyword to be used as this entry's signifier:");
            System.out.println("(Reccomended format: \"LastnameFirstname)\"");
            TreeNode tempNode = new TreeNode(userInput.nextLine());

            if (tree.exists(tempNode.keyword)) {
                System.out.print("Entry with keyword \"" + tempNode.keyword);
                System.out.println("\" already exists in dictionary");
                continue;
            } else {
                keywordExists = false;
            }

            for (var data : DataEnum.values()) {
                System.out.println("Please enter the " + data.label + " :");
                tempNode.setData(data, userInput.nextLine());
            }

            char yn = 'N';
            tempNode.displayData();
            System.out.println("Does that all look correct? (y/n)");
            yn = Character.toUpperCase(userInput.nextLine().charAt(0));
            if (yn == 'N') {
                System.out.println();
                editInfo(userInput, tempNode, tree);
            } else {
                tree.addNode(tempNode);
                tree.entryCount++;
                System.out.println("Entry \"" + tempNode.keyword + "\" successfully added!");
            }
        }
    }

    /*
     * Takes in a user entered keyword to search for an entry/node in the binary
     * tree, then after
     * the user selects which type of search function, calls the selected search
     * function with the
     * specified keyword
     * 
     * 
     * 
     */
    public static TreeNode searchInfo(Scanner userInput, DictionaryTree tree) {
        System.out.println("Please enter the entry's keyword:");
        String keyword = userInput.nextLine();
        System.out.println("Which order would you like to use while searching for an entry?");
        System.out.println("    (al)phabetic binary search");
        System.out.println("    (pr)e-order");
        System.out.println("    (in)-order");
        System.out.println("    (po)st-order");
        String order = (userInput.nextLine().toLowerCase()).substring(0, 2);
        TreeNode searchedNode = null;
        switch (order) {
            case "al":
                searchedNode = tree.search(keyword);
                break;

            case "pr":
                searchedNode = tree.searchPre(keyword);
                break;

            case "in":
                searchedNode = tree.searchIn(keyword);
                break;

            case "po":
                searchedNode = tree.searchPost(keyword);
                break;

            default:
                System.out.println("Unable to parse search order: " + keyword);
                System.out.println("Please try again");
                break;
        }
        if (searchedNode == null) {
            System.out.println("Could not find entry via keyword: " + keyword);
            System.out.println("Please try again with a different keyword");
        }
        return searchedNode;

    }

    // Finds the user specified entry, confirms whether user would like to delete
    // before deleting
    public static void delInfo(Scanner userInput, DictionaryTree tree) {
        System.out.println("Please enter the keyword of the entry to be deleted:");
        String keyword = userInput.nextLine();
        if (!tree.exists(keyword)) {
            System.out.println("Deletion failed: entry \"" + keyword + "\" does not exist");
        } else {
            tree.searchPre(keyword).displayData();
            System.out.println("Are you sure you would like to delete entry \"" + keyword + "\"?");
            System.out.println("(y/n)");
            char yn;
            yn = Character.toUpperCase(userInput.nextLine().charAt(0));
            if (yn == 'Y') {
                tree.delNode(keyword);
                tree.entryCount--;
                System.out.println("Entry " + keyword + " successfully deleted!");
            } else {
                System.out.println("Entry \"" + keyword + "\" not deleted");
            }
        }
    }

    // Uses searchInfo to grab user specified node, then passes it to editInfo to
    // have info edited
    public static void modNode(Scanner userInput, DictionaryTree tree) {
        TreeNode tempNode = searchInfo(userInput, tree);
        if (tempNode != null) {
            editInfo(userInput, tempNode, tree);
            System.out.println("Entry \"" + tempNode.keyword + "\" successfully edited!");
        }
    }

    // Shows all of entries data, then prompts user for data type to edit.
    // If datatype to edit is the keyword, deletes the node from the tree before
    // editing,
    // then adds it back to retain alphabetization of tree
    public static void editInfo(Scanner userInput, TreeNode tempNode, DictionaryTree tree) {
        char yn = 'N';
        while (yn == 'N' || (yn != 'N' && yn != 'Y')) {
            tempNode.displayData();
            System.out.println();
            System.out.println("Which datatype would you like to edit?");
            System.out.println("Keyword (K)");

            for (DataEnum dataType : DataEnum.values()) {
                System.out.println(dataType.label + " (" + dataType.name() + ")");
            }

            String dataChoice = userInput.nextLine().toUpperCase().substring(0, 1);
            if (dataChoice.equals("K")) {
                if (tree.exists(tempNode.keyword)) {
                    tree.delNode(tempNode.keyword);
                }
                tempNode.left = null;
                tempNode.right = null;
                System.out.println("Please enter the new keyword:");
                tempNode.keyword = userInput.nextLine();
            } else {
                System.out.println("Please enter the new " + DataEnum.valueOf(dataChoice).label + ":");
                String data = userInput.nextLine();
                tempNode.setData(DataEnum.valueOf(dataChoice), data);
            }
            tempNode.displayData();
            System.out.println("Does that all look correct? (y/n)");
            yn = Character.toUpperCase(userInput.nextLine().charAt(0));
            if (yn == 'Y') {
                tree.addNode(tempNode);
            }
        }
    }
}
