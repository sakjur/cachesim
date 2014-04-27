package is.mjuk.cache;

import java.util.Scanner;
import java.util.Date;

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
        this.endSimulation();
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
        boolean legalData = true;

        System.out.println("USER SPECIFIES BLOCK PROPERTIES");
        do {
            System.out.println("Enter block size in bytes: ");
            int blockSize = scanner.nextInt();
            System.out.println("Enter block count: ");
            int blockCount = scanner.nextInt();
            System.out.println("Enter associativity: ");
            int associativity = scanner.nextInt();

            System.out.println("CALCULATES CACHE LAYOUT & CREATES CACHE");
            try{
                c.setCacheLayout(blockSize, blockCount, associativity);
                legalData = true;
            }
            catch(java.lang.IllegalArgumentException e){
                System.out.println("Illegal Data was entered.");
                System.out.println("Block Count and Block Size must be powers"
                    + " of two.");
                legalData = false;
            }
        } while (!legalData);
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
            } else if (input.matches("^[ls](oad|tore)?\\s\\d+$")) {
                long address = Long.parseLong(input.split("\\s")[1]);

                if (input.split("\\s")[0].matches("^l(oad)?$")) {
                    System.out.println(
                        c.executeInstruction("load", address).toString()
                    );
                } else if (input.split("\\s")[0].matches("^s(tore)?$")) {
                    System.out.println(
                        c.executeInstruction("store", address).toString()
                    );
                }

                System.out.printf(
                    "Hitrate is %.2f and missrate is %.2f.\n",
                    c.getHitrate(), c.getMissrate()
                );
            } else if (input.matches("^$")) {
                // Intentionally left empty
            } else {
                System.err.println("Instruction not found `" + input + "`");
            }
        }
    }

    private void endSimulation(){
        System.out.println(c.displayCache());   // TODO: Remove
        SimulationDTO simDTO = c.getSimulationData();
        System.out.println("Simulation data:");
        System.out.println("Username: " + simDTO.getNickname());
        System.out.println("Load instructions: " + simDTO.getLoads());
        System.out.println("Store instructions: " + simDTO.getStores());
        System.out.println("Hit rate: " + simDTO.getHitrate());
        System.out.println("Miss rate: " + simDTO.getMissrate());
        System.out.println("Block Size: "
            + simDTO.getLayoutDTO().getBlockSize() + " bytes");
        System.out.println("Block Count: "
            + simDTO.getLayoutDTO().getBlockCount() + " blocks");
        System.out.println("Associativity: "
            + simDTO.getLayoutDTO().getAssociativity());
        System.out.println("Address tag size: "
            + simDTO.getLayoutDTO().getTagSize() + " bits");
        System.out.println("Address index size: "
            + simDTO.getLayoutDTO().getIndexSize() + " bits");
        System.out.println("Address offset size: "
            + simDTO.getLayoutDTO().getOffsetSize() + " bits");
    }
}
