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
        this.getUserInstruction();
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

    private void getUserInstruction()
    {
        System.out.println("USER INPUTS INSTRUCTIONS");
        System.out.println("Write `exit` to stop the application");
		System.out.println(
            "To use instruction load, write 'load <memaddress>'"
        );
		System.out.println(
            "To use instruction store, write 'store <memaddress>'"
        );

        while (true) {
            String input = scanner.nextLine();
			input = input.trim();

            if (input.toLowerCase().equals("exit") 
                || input.toLowerCase().equals("x")) {
                break;
            } else if (input.matches("^l(oad)?\\s\\d+$")) {
                long address = Long.parseLong(input.split("\\s")[1]);
                System.out.println(
                    c.executeInstruction("load", address).toString()
                );
            } else if (input.matches("^s(tore)?\\s\\d+$")) {
                long address = Long.parseLong(input.split("\\s")[1]);
                System.out.println(
                    c.executeInstruction("store", address).toString()
                );
            } else if (input.matches("^$")) {
                // Intentionally left empty
            } else {
                System.err.println("Instruction not found `" + input + "`");
            }
        }

        System.out.println(c.displayCache());
    }
}
