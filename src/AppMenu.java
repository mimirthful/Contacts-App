import java.io.Console;
/**
 * Class that represents the visual menu.
 */
public class AppMenu {
    /**
     * Console for user's input.
     */
    private Console console = System.console();
    /**
     * Arraylist that contains the Contacts.
     */
    private ContactArrayList contactList = new ContactArrayList();
    /**
     * Main Menu.
     * Pulls out contacts from the .csv file when started.
     * Has a switch case that takes user's input as
     * a key, and moves forward on the menus.
     * Presents an error message if user input isn't recognized.
     */
    public void start() {
        contactList.savedContacts();
        boolean isRunning = true;
        System.out.println("-MAIN MENU-");

        while (isRunning) {
            System.out.println("[A] Add a new contact "
            + "| [S] Show contacts "
            + "| [E] Edit Contacts "
            + "| [Q] Quit");
            System.out.print("> ");
            Character userInput = console.readLine().charAt(0);
            char key = Character.toUpperCase(userInput);
            System.out.println();

            switch (key) {
                case 'A': // Add a new contact
                    System.out.println("-NEW CONTACT-");
                    contactList.newContact();
                    break;
                case 'E': // Edit contact - master menu
                    editMasterMenu();
                    break;
                case 'Q': // Quits the loop.
                    isRunning = false;
                    break;
                case 'S': // Shows a list of the contacts.
                    System.out.printf(contactList.readContacts());
                    break;
                default:
                    System.out.println("Command not recognized.");
            }
        }
    }
    /**
     * Shows user the edit master menu.
     * Has a switch case that takes user's input as
     * a key, and moves forward on the menus.
     * If "B" is pressed, breaks and returns to start().
     * Presents an error message if user input isn't recognized.
     */
    void editMasterMenu() {
        boolean isRunning = true;
        System.out.println("-EDIT CONTACTS-");
        while (isRunning) {
            System.out.println("[E] Edit a contact "
            + "| [D] Delete a contact "
            + "| [B] Main Menu");
            System.out.print("> ");

            Character userInput = console.readLine().charAt(0);
            char key = Character.toUpperCase(userInput);
            System.out.println();

            switch (key) {
                case 'E': // Proceeds to editMenu()
                    editMenu();
                    break;
                case 'D': // Proceeds to deleteMenu()
                    deleteMenu();
                    break;
                case 'B': // Breaks back to start()
                    System.out.println("-MAIN MENU-");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Command not recognized.");
            }
        }
    }
    /**
     * Menu for deleting the contacts.
     * Presents the user a list of contacts.
     * If a contact is selected, proceeds to delete a contact.
     * If "B" is pressed, breaks and returns to editMasterMenu().
     * Presents an error message if user input isn't recognized.
     */
    void deleteMenu() {
        boolean isRunning = true;
        System.out.printf(contactList.readContacts());
        while (isRunning) {
            System.out.println();
            System.out.println("Type Contact's number to delete"
            + " a contact or [B] to go back.");
            System.out.print("> ");
            String dUserInput = console.readLine().toUpperCase();
            System.out.println();

            if (dUserInput.equals("B")) { // Breaks back to editMasterMenu()
                System.out.println("-EDIT CONTACTS-");
                isRunning = false;
            } else {
                try { // Moves to deletion method.
                    int index = Integer.parseInt(dUserInput);
                    contactList.removeContact(index);
                } catch (IllegalArgumentException e) {
                    System.out.println("Command not recognized.");
                }
            }
        }
    }
    /**
     * Menu for the user to edit the contacts.
     * Presents the user a list of contacts.
     * If contact is selected, proceeds to subEditMenu().
     * If "B" is pressed, breaks and returns to editMasterMenu().
     * Presents an error message if user input isn't recognized.
     */
    void editMenu() {
        boolean isRunning = true;
        System.out.printf(contactList.readContacts());
        while (isRunning) {
            System.out.println();
            System.out.println(
                "Type Contact's number to edit a contact or [B] to go back.");
            System.out.print("> ");
            String eUserInput = console.readLine().toUpperCase();
            System.out.println();

            if (eUserInput.equals("B")) { // Breaks to editMasterMenu().
                System.out.println("-EDIT CONTACTS-");
                isRunning = false;
            } else {
                try { // Shows the selected contact and moves to subEditMenu().
                    int index = Integer.parseInt(eUserInput);
                    System.out.printf(contactList.readSingleContact(index));
                    subEditMenu(index);

                } catch (IllegalArgumentException e) {
                    System.out.println("Command not recognized.");
                }
            }
        }
    }
    /**
     * Menu for the user to select the information to be edited.
     * Presents the user a contact's information.
     * If information is selected, proceeds to the information editing progress.
     * If "B" is pressed, breaks and returns to editMenu().
     * Presents an error message if user input isn't recognized.
     * @param index  user input representing the selected contact.
     */
    void subEditMenu(final int index) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println();
            System.out.println(
                "Select information [0-5] to edit or [B] to go back.");
            System.out.print("> ");
            String eeUserInput = console.readLine().toUpperCase();
            System.out.println();

            if (eeUserInput.equals("B")) { // Breaks to editMenu().
                System.out.println("-EDIT CONTACTS-");
                System.out.printf(contactList.readContacts());
                isRunning = false;
            } else {
                try { // moves to editing method.
                    int key = Integer.parseInt(eeUserInput);
                    contactList.editContact(index, key);
                } catch (IllegalArgumentException e) {
                    System.out.println("Command not recognized.");
                    System.out.println();
                    contactList.readSingleContact(index);
                }
            }
        }
    }
}
