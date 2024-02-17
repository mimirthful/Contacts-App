import java.util.ArrayList;
import java.io.Console;
/**
 * A class that contains a list of Contacts.
 * This class has methods to manage the contact list.
 */

public class ContactArrayList {
    /**
     * Console to read user input.
     */
    private Console console = System.console();
    /**
     * Arraylist of Contact objects. This arraylist is used for
     * management of the contacts.
     */
    private ArrayList<Contact> contactArr = new ArrayList<Contact>();

    /**
     * Prints out the ArrayList's Contacts as String.
     * The string uses a readable format.
     * Used to show the user the contacts on their contact book.
     * @return Returns a string of the contacts.
     */
    String readContacts() {
        String str = "";
        for (int i = 0; i < contactArr.size(); i++) {
            String index = "%n Contact [" + i + "]";
            Contact contact = contactArr.get(i);
            str = str + index + contact.formatStr();
        }

        return str;
    }
    /**
     * Returns a readable string of the Contact's info.
     * It fetches a Contact object from the array of objects
     * (contactArr) from a position determined by the parameter index.
     * if given parameter index is out of bounds of the array,
     * it will return an error message to the user.
     * @param index int to represent the target position in the array.
     * @return Returns string, either a single Contact's info
     * in a formatted string or an error message in a formatted string.
     */
    String readSingleContact(final int index) {
        if (index < (contactArr.size()) && index >= 0) {
            String str = contactArr.get(index).formatEditString();
            return str;
        } else {
            return "Contact [" + index + "] doesn't exist.";
        }
    }
    /**
     * Edits a Contact's information.
     * Returns a readable string of the Contact's info.
     * It fetches a Contact object from the array of objects
     * (contactArr) from a position determined by the parameter index.
     * After that it starts a loop, where the user may change
     * their desired information from the object.
     * The loop contains a switch case that takes the "key" parameter
     * as a switch case parameter. Every case represents a different
     * variable in the Contact object, and accesses their setter method.
     * The information of the contacts is updated to the .csv
     * file after every action.
     * @param index index of the Contact object at the arraylist.
     * @param key number representing the desired information
     * to be changed.
     */
    void editContact(final int index, final int key) {
        Contact eContact = contactArr.get(index);
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Give new value:");
            String userInput = console.readLine();

            switch (key) {
                case 0:
                    try {
                        eContact.setId(userInput);
                        correctInput = true;
                        System.out.println();
                        System.out.println("ID updated.");
                        System.out.printf(readSingleContact(index));
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                case 1:
                    try {
                        eContact.setFirstName(userInput);
                        correctInput = true;
                        System.out.println();
                        System.out.println("First Name updated.");
                        System.out.printf(readSingleContact(index));
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                case 2:
                    try {
                        eContact.setLastName(userInput);
                        correctInput = true;
                        System.out.println();
                        System.out.println("Last Name updated.");
                        System.out.printf(readSingleContact(index));
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                case 3:
                    try {
                        eContact.setNumber(userInput);
                        System.out.println();
                        System.out.println("Number updated.");
                        System.out.printf(readSingleContact(index));
                        correctInput = true;
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                case 4:
                    try {
                        eContact.setAddress(userInput);
                        correctInput = true;
                        System.out.println();
                        System.out.println("Address updated.");
                        System.out.printf(readSingleContact(index));
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                case 5:
                    try {
                        eContact.setEmail(userInput);
                        correctInput = true;
                        System.out.println();
                        System.out.println("Email updated.");
                        System.out.printf(readSingleContact(index));
                    } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                    break;
                default:
                    break;
            }
        }
        updateFile();
    }
    /**
     * Deletes a contact.
     * Will prompt a user with a confirmation question. If
     * user answers "Y", it will fetch a Contact from the
     * contactArr arraylist, and delete it. After deletion, it
     * updated the .csv file accordingly.
     * @param index index of the Contact object at the arraylist.
     */
    void removeContact(final int index) {
        if (index < (contactArr.size()) && index >= 0) {
            System.out.println("Removing Contact [" + index + "]");
            System.out.println("Continue? Y/N");
            System.out.print("> ");
            Character confirm = console.readLine().charAt(0);
            char key = Character.toUpperCase(confirm);
            System.out.println();

            if (key == 'Y') {
                contactArr.remove(index);
                System.out.println("Contact [" + index + "] deleted.");
                updateFile();
                readContacts();
            } else if (key == 'N') {
                System.out.println("Operation cancelled.");
            } else {
                System.out.println("Command not recognized.");
            }
        } else {
            System.out.println("Contact [" + index + "] doesn't exist.");
        }
    }

    /**
     * Transfers contacts from the .csv file to contactArr Arraylist.
     * This is used when opening the program.
     */

    void savedContacts() {
        // Saves the whole .csv file text as a String
        String savedContacts = FileRead.readFile();
        // if .csv file isn't empty.
        if (savedContacts.length() > 0) {
            /* Splits the string into array of strings when "<" is hit.
            * every string on the array now
            * contains one Contact's information */
            String[] arrayOfSaved = savedContacts.split("<", 0);
            /* Splits the arrayOfSaved strings every time "," is hit.
            * after that it creates a new contact with the split strings
            * and adds it to the contactArr */
            for (int i = 0; i < arrayOfSaved.length; i++) {
                String[] data = arrayOfSaved[i].split(",");
                Contact contact = new Contact(
                    data[0], data[1], data[2], data[3], data[4], data[5]);
                contactArr.add(contact);
            }
        }
    }
    /**
     * Creates a new Contact object.
     * After creating the Contact with a blank constructor
     * the Contact's information is set by setters.
     * After the information has been set
     * it saves the Contact object on the csv file
     * and on the ContactArr Arraylist of Contact objects.
     */
    void newContact() {
        Contact contact = new Contact();
        // Setting first name
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("[MANDATORY] First Name:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setFirstName(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Setting Last name
        correctInput = false;
        while (!correctInput) {
            System.out.println("[MANDATORY] Last Name:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setLastName(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Setting ID
        correctInput = false;
        while (!correctInput) {
            System.out.println("[MANDATORY] Personal ID:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setId(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Number
        correctInput = false;
        while (!correctInput) {
            System.out.println("[MANDATORY] Phone Number:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setNumber(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Setting address
        correctInput = false;
        while (!correctInput) {
            System.out.println("[OPTIONAL] Address:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setAddress(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Setting email
        correctInput = false;
        while (!correctInput) {
            System.out.println("[OPTIONAL] e-mail:");
            System.out.print("> ");
            String userInput = console.readLine();
            try {
                contact.setEmail(userInput);
                correctInput = true;
            // Catches possible errors the setter may throw.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Writes the object's information on the csv file.
        FileWrite.writeToFile(contact.toString());
        // Adds the object on the list of objects.
        contactArr.add(contact);
    }
    /**
     * Updates the .csv file.
     * It removes the old content from
     * the file and updates the contactArr
     * arraylist's information accordingly.
     */
    void updateFile() {
        FileWrite.deleteFileContent();
        for (int i = 0; i < contactArr.size(); i++) {
            Contact target = contactArr.get(i);
            FileWrite.writeToFile(target.toString());
        }
    }
}
