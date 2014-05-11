package is.mjuk.cache;

import java.util.Scanner;
import java.util.Date;

/**
* User interaction class
* <p>
* Handles interaction between user and the application
*/
public class View implements DataCacheObserver
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
        System.out.println("----------------------------------\n");
        System.out.println("Please enter nickname: ");
        String newNick = scanner.nextLine();

        c.setNickname(newNick);
        System.out.println("Your nickname is: " + c.getNickname());
    }
    
    
    
    private void getCacheInformation()
    {
        boolean legalData = true;

        System.out.println("\nUSER SPECIFIES BLOCK PROPERTIES");
        System.out.println("-------------------------------\n");
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
        System.out.println(
            "To observe changes in the cache, write 'observe'"
        );
        System.out.println(
            "To stop observing changes in the cache, write 'deobserve'"
        );

        while (true) {
            String input = scanner.nextLine();
            input = input.trim();

            String regex = "^[ls](oad|tore)?\\s(0[x]?)?[0-9a-fA-F]+";

            if (input.toLowerCase().equals("exit") 
                || input.toLowerCase().equals("x")) {
                break;
            } else if (input.matches(regex)) {
                sendInstruction(input);
            } else if (input.matches("^$")) {
                // Intentionally left empty
            } else if (input.equals("observe")) {
                System.out.println("Now observing the DataCache for changes");
                c.addDataCacheObserver(this);
            } else if (input.equals("deobserve")) {
                System.out.println("No longer observing the DataCache");
                c.removeDataCacheObserver(this);
            } else {
                System.err.println("Instruction not found `" + input + "`");
            }
        }
    }

    private void endSimulation() {
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

    /**
    * Recieves data from a DataCache observee.
    */
    public void recvDataCacheUpdate(String dataCacheContent) {
        System.out.println(dataCacheContent);
    }

    private void sendInstruction(String input) {
        long address = Long.decode(input.split("\\s")[1]);

        try {
            if (input.split("\\s")[0].matches("^l(oad)?$")) {
                System.out.println(
                    c.executeInstruction("load", address).toString()
                );
            } else if (input.split("\\s")[0].matches("^s(tore)?$")) {
                System.out.println(
                    c.executeInstruction("store", address).toString()
                );
            }
        } catch(IllegalAddressException e) {
            System.out.println("Error parsing memory address: " + e);
            return;
        }

        System.out.printf(
            "Hitrate is %.2f and missrate is %.2f.\n",
            c.getHitrate(), c.getMissrate()
        );
    }
}
