package is.mjuk.cache;

import java.util.Scanner;

class View
{
    public static Scanner scanner = new Scanner(System.in);
    Controller c;

    public View(Controller controller)
    {
        this.c = controller;
        this.requireNickname();
        this.getCacheInformation();
    }

    private void requireNickname()
    {
        System.out.println("Please enter nickname: ");
        String newNick = scanner.nextLine();

        c.setNickname(newNick);
        System.out.println("Your nickname is: " + c.getNickname());
    }

    private void getCacheInformation()
    {
        System.out.println("Enter block size in bytes: ");
        int blockSize = scanner.nextInt();
        System.out.println("Enter block count: ");
        int blockCount = scanner.nextInt();
        System.out.println("Enter associativity: ");
        int associativity = scanner.nextInt();
        c.setCacheLayout(blockSize, blockCount, associativity);
        System.out.println("Tag of 0xabad1dea is 0x" + Long.toString(c.parseAddress().tag, 16));
    }
}