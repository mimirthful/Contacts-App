/**
 * Class for initiation.
 * Mainly used to show opening text and
 * closing text.
 */
public class ContactsApp {
    public static void main(final String[] args) {
        System.out.println("   _____            _             _       ");
        System.out.println("  / ____|          | |           | |      ");
        System.out.println(" | |     ___  _ __ | |_ __ _  ___| |_ ___ ");
        System.out.println(" | |    / _ \\| '_ \\| __/ _` |/ __| __/ __|");
        System.out.println(" | |___| (_) | | | | || (_| | (__| |_\\__ \\");
        System.out.println("  \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/");
        AppMenu appMenu = new AppMenu();
        appMenu.start();
        System.out.println("");
        System.out.println("App closed.");
    }
}
