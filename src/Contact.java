/**
 * This class represents a contact in the user's contact book.
 * Every Contact object represents a single contact on the book.
 * This class contains the contact's information as private strings
 * that can be added and modified with the methods in the class.
 * It also contains various String methods for printing and
 * representing the contact's information to the user.
 */
public class Contact {
    /**
     * Contact's social security number.
     * Must be a legal one. Mandatory.
     */
    private String id;
    /**
     * Contact's first name.
     * Must be at least two letters and
     * contain only characters. Mandatory.
     */
    private String firstName;
    /**
     * Contact's last name.
     * Must be at least two letters and
     * contain only characters. Mandatory.
     */
    private String lastName;
    /**
     * Contact's phone number.
     * Must only contain digits. Mandatory.
     */
    private String number;
    /**
     * Contact's address.
     * Optional for the user.
     */
    private String address = "Not added";
    /**
     * Contact's email.
     * Optional for the user.
     */
    private String email = "Not added"; // Optional

    /**
     * Contact
     * Blank constructor.
     * Used when creating a new contact.
     */

    public Contact() {

    }
    /**
     * Contact constructor with parameters for all
     * information about the contact.
     * Used when inputting contact information from
     * the .csv file.
     * @param parId  Contact's social security number.
     * @param parFirstName  Contact's first name.
     * @param parLastName  Contact's last name.
     * @param parNumber  Contact's phone number.
     * @param parAddress  Contact's address.
     * @param parEmail  Contact's email.
     */

    public Contact(final String parId, final String parFirstName,
    final String parLastName, final String parNumber,
    final String parAddress, final String parEmail) {
        this.id = parId;
        this.firstName = parFirstName;
        this.lastName = parLastName;
        this.number = parNumber;
        this.address = parAddress;
        this.email = parEmail;
    }

    /**
     * Setter for the variable "id".
     * Checks if parameter parId is legal.
     * If parId doesn't fulfill standards,
     * the method will throw an exception.
     * If parId is legal, it sets it to object's id.
     * @param parId id (social security number).
     */
    void setId(final String parId) {
        // Finnish ID's are 11 characters.
        final int targetLenght = 11;
        // Checks if ID is 11 characters
        if (parId.length() != targetLenght) {
            throw new IllegalArgumentException(
                "ID is not valid.");
        }
        // Creates char[] of the ID
        char[] idArray = new char[targetLenght];
        for (int i = 0; i < parId.length(); i++) {
            idArray[i] = parId.charAt(i);
        }

        // first batch of the ID contains DD-MM-YY.
        final int ddmmyyLength = 6;
        // Checks if first 6 characters of array are digits
        for (int i = 0; i < ddmmyyLength; i++) {
            if (!Character.isDigit(idArray[i])) {
                throw new IllegalArgumentException(
                    "ID is not valid. First 6 characters must be digits.");
            }
        }

        // These variables are being used in later loops.
        final String day = "" + idArray[0] + idArray[1];
        int idDay = Integer.parseInt(day);
        final String month = "" + idArray[2] + idArray[3];
        int idMonth = Integer.parseInt(month);

        // Checks if day is in range.
        final int maxDaysonMonth = 31;
        if (idDay <= 0 || idDay > maxDaysonMonth) {
            throw new IllegalArgumentException(
                    "ID is not valid. Day must be between 01-31");
        }

        // Checks if month is in range
        final int maxAmountOfMonths = 12;
        if (idMonth <= 0 || idMonth > maxAmountOfMonths) {
            throw new IllegalArgumentException(
                    "ID is not valid. Month must be between 1-12");
        }

        // character presenting the mark in the middle of the ID.
        final int middleCharPos = 6;
        char yearMark = idArray[middleCharPos];

        // Checks if character is valid.
        if (!(yearMark == '+' || yearMark == '-' || yearMark == 'A')) {
            throw new IllegalArgumentException(
                    "ID is not valid. Year Mark is wrong.");
        }

        /* Checks if the check digit on the social security number
         * is on valid range. */
        String strCheckDigit = "" + idArray[7] + idArray[8] + idArray[9];
        int checkDigit = Integer.parseInt(strCheckDigit);
        final int maxCheckNumber = 999;
        if (checkDigit > maxCheckNumber) {
            throw new IllegalArgumentException(
                "ID is not valid. Index number not in range");
        }

        /* Variable that combines all of the numbers found
         * on the social security number expect on the last
         * possible number on the last index of the array.
         */
        String strDigitsOnId = "" + idArray[0] + idArray[1] + idArray[2]
        + idArray[3] + idArray[4] + idArray[5]
        + idArray[7] + idArray[8] + idArray[9];
        int digitsOnId = Integer.parseInt(strDigitsOnId);

        // Variable representing last character of the social security number
        final int lastCharOnArr = 10;
        char checkLetter = idArray[lastCharOnArr];

        /*Array of characters that are allowed to exists in the last index
         * of the social security number.
         */
        char[] validChars = {'0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
        'F', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y'};

        // Boolean used for confirmation in the last loop.
        boolean isValidChar = false;

        // Checks the validChars Array index by index,
        for (char c : validChars) {
            // if checkLetter is present in the Array.
            if (c == checkLetter) {
                // doing a modulus by 31.
                final int modulusBy = 31;
                double remainder = digitsOnId % modulusBy;
                // if the remainder is under 30
                final int maxRemainder = 30;
                if (!((remainder) > maxRemainder)) {
                    int rounded = (int) remainder;
                    /* At validChars array,
                     * if the character found on the index
                     * (that is the rounded remainder of the modulus)
                     * is the same, than the checkLetter,
                     * the character is valid.
                     */
                    if (validChars[rounded] == checkLetter) {
                        isValidChar = true;
                    } else {
                        throw new IllegalArgumentException(
                        "ID is not valid. Modulus is wrong");
                    }
                }
            }
        }
        // Sets id.
        if (isValidChar) {
            this.id = parId;
        } else {
            // Character doesn't exist on validChars.
            throw new IllegalArgumentException(
                        "ID is not valid. SOmething wrong");
        }
    }
    /**
     * Getter for the variable "id".
     * @return  Returns id.
     */
    String getId() {
        return id;
    }

    /**
     * Setter for the variable "firstName".
     * Checks if parameter parFirstName is legal.
     * If parFirstName doesn't fulfill standards,
     * the method will throw an exception.
     * If parFirstName is legal, it sets it to object's firstName.
     * @param parFirstName Contact's first name.
     */
    void setFirstName(final String parFirstName) {
        if (parFirstName.length() < 2) {
            throw new IllegalArgumentException(
                "Name must be at least two letters.");
        }
        for (int i = 0; i < parFirstName.length(); i++) {
            char targetchar = parFirstName.charAt(i);
            if (!Character.isLetter(targetchar)) {
                throw new IllegalArgumentException(
                    "Name must only contain letters.");
            }
        }
        this.firstName = parFirstName;
    }
    /**
     * Getter for the variable "firstName".
     * @return  Returns firstName
     */
    String getFirstname() {
        return firstName;
    }

    /**
     * Setter for the variable "lastName".
     * Checks if parameter parLastName is legal.
     * If parLastName doesn't fulfill standards,
     * the method will throw an exception.
     * If parLastName is legal, it sets it to object's lastName.
     * @param parLastName Contact's last name.
     */
    void setLastName(final String parLastName) {
        if (parLastName.length() < 2) {
            throw new IllegalArgumentException(
                "Name must be at least two letters.");
        }
        for (int i = 0; i < parLastName.length(); i++) {
            char targetchar = parLastName.charAt(i);
            if (!Character.isLetter(targetchar)) {
                throw new IllegalArgumentException(
                    "Name must only contain letters.");
            }
        }
        this.lastName = parLastName;
    }
    /**
     * Getter for the variable "lastName".
     * @return  Returns lastName.
     */
    String getLastName() {
        return lastName;
    }

    /**
     * Setter for the variable "number".
     * Checks if parameter parNumber is legal.
     * If parNumber doesn't fulfill standards,
     * the method will throw an exception.
     * If parNumber is legal, it sets it to object's number.
     * @param parNumber  String of the phone number.
     */
    void setNumber(final String parNumber) {
        final int minimumLength = 3;
        for (int i = 0; i < parNumber.length(); i++) {
            char targetchar = parNumber.charAt(i);
            if (!Character.isDigit(targetchar)) {
                throw new IllegalArgumentException(
                    "Phone Number must only contain digits.");
            }
            if (parNumber.length() < minimumLength) {
            throw new IllegalArgumentException(
                "Number must be at least three letters.");
        }
        }
        this.number = parNumber;
    }
    /**
     * Getter for the variable "number".
     * @return  Returns number.
     */
    String getNumber() {
        return number;
    }

    /**
     * Setter for the variable "address".
     * Doesn't have special requirements.
     * @param parAddress  String of the phone number.
     */
    void setAddress(final String parAddress) {
        if (parAddress.length() != 0) {
            this.address = parAddress;
        }
    }
    /**
     * Getter for the variable "address".
     * @return  Returns address.
     */
    String getAddress() {
        return address;
    }

    /**
     * Setter for the variable "email".
     * Doesn't have special requirements.
     * @param parEmail  String of the email.
     */
    void setEmail(final String parEmail) {
        if (parEmail.length() != 0) {
            this.email = parEmail;
        }
    }
    /**
     * Getter for the variable "email".
     * @return  Returns email.
     */
    String getEmail() {
        return email;
    }

    /**
     * Used to return Contact's information as a String.
     * The String is formatted in a form, where it's usable
     * in .csv file, where the "<" mark represents
     * end of the line.
     * @return Suitable string for .csv file.
     */
    public String toString() {
        return id + "," + firstName + ","
        + lastName + "," + number + ","
        + address + "," + email + ",<";
    }
    /**
     * Used to return a formatted string.
     * The string contains all the Contact's information
     * and can be printed on the console using
     * printf(), which will print out a clear
     * and readable string for the user.
     * @return Returns formatted String.
     */
    public String formatStr() {
            return "%n ID: " + id
            + "%n First Name: " + firstName
            + "%n Last Name: " + lastName
            + "%n Number: " + number
            + "%n Address: " + address
            + "%n E-mail: " + email + "%n";
    }
    /**
     * Used to return a formatted string.
     * Used for the edit information menu.
     * The string contains all the Contact's information
     * and can be printed on the console using
     * printf(), which will print out a clear
     * and readable string for the user.
     * It additionally contains numbers representing
     * the variables for contact.
     * @return returns a formatted string.
     */
    public String formatEditString() {
            return "%n [0] ID: " + id
            + "%n [1] First Name: " + firstName
            + "%n [2] Last Name: " + lastName
            + "%n [3] Number: " + number
            + "%n [4] Address: " + address
            + "%n [5] E-mail: " + email + "%n";
    }
}
