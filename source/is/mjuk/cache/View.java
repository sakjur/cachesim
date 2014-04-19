package is.mjuk.cache;

import java.util.Scanner;

/**
* User interaction class
* <p>
* Handles interaction between user and the application
*/
public class View
{
    public static Scanner scanner = new Scanner(System.in);
    Controller c;

    /**
    * Constructs the View
    * <p>
    * Creates a new view and attaches it to a {@link is.mjuk.cache.Controller}
    * and then launches the text-based UI of the application.
    *
    * @param controller Controller for communication with the application logic
    */
    public View(Controller controller)
    {
        this.c = controller;
        this.requireNickname();
        this.getCacheInformation();
    }

    private void requireNickname()
    {
        System.out.println("ASKS USER TO ENTER USER PROPERTIES");
        System.out.println("Please enter nickname: ");
        String newNick = scanner.nextLine();

        c.setNickname(newNick);
        System.out.println("Your nickname is: " + c.getNickname());
    }

    private void getCacheInformation()
    {
        System.out.println("USER SPECIFIES BLOCK PROPERTIES");
        System.out.println("Enter block size in bytes: ");
        int blockSize = scanner.nextInt();
        System.out.println("Enter block count: ");
        int blockCount = scanner.nextInt();
        System.out.println("Enter associativity: ");
        int associativity = scanner.nextInt();

        System.out.println("CALCULATES CACHE LAYOUT & CREATES CACHE");
        c.setCacheLayout(blockSize, blockCount, associativity);

        System.out.println("Displaying Cache Data");
        System.out.println(c.displayCache());
    }
}