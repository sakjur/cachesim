package is.mjuk.cache;

/**
* Controller for communication between storage, models and view.
*/
public class Controller
{
    private User user;
    private CacheLayout cacheLayout;
    private DataCache dataCache;
    private AddressLayout addressLayout;
    private Storage store = Storage.getStorage();

    /**
    * Constructs a controller with an embedded
    * {@link is.mjuk.cache.User}-object
    */
    public Controller() {
        user = new User();
    }

    /**
    * Returns the {@link is.mjuk.cache.User}-object's stored datetime
    * as a string.
    */
    public String getDateTimeString() {
        return user.getDateTime().toString();
    }

    /**
    * Creates an Instruction object and executes a single instruction
    * <p>
    * Creates an Instruction object and loads it with the datacache and
    * addresslayout objects and then requests it to perform it's
    * instruction which status is sent through back through a
    * {@link is.mjuk.cache.InstructionDTO} object which is subsequently
    * stored in the {@link is.mjuk.cache.Storage}-singleton and returned
    * to the callee.
    *
    * @param type String containing data of instruction type
    * (either store or load)
    * @param address A long containing the address to perform the
    * instruction on.
    * @return {@link is.mjuk.cache.InstructionDTO} containing data about
    * the instruction.
    * @see is.mjuk.cache.Instruction
    */
    public InstructionDTO executeInstruction(String type, long address)
    throws IllegalAddressException {
        Instruction instruction;

        if(type.equals("load")) {
            instruction = new Instruction(dataCache, addressLayout, 
                InstructionType.LOAD, address);
        } else if (type.equals("store")) {            
            instruction = new Instruction(dataCache, addressLayout, 
                InstructionType.STORE, address);
        } else {
            InstructionDTO rv = null;
            return rv;
        }

        InstructionDTO instructionDTO = instruction.executeInstruction();

        store.addInstructionDTO(instructionDTO);

        return instructionDTO;
    };

    /**
    * Gets hitrate from the datacache object
    */
    public double getHitrate() {
        return dataCache.getHitrate();
    }

    /**
    * Calculates and displays missrate.
    * <p>
    * Subtracts hitrate from 1.
    */
    public double getMissrate() {
        double hitrate = dataCache.getHitrate();
        return 1.00 - hitrate;
    }

    /**
    * Setter for the user-property's nickname.
    * @see is.mjuk.cache.User#setNickname(String newNickname)
    */
    public void setNickname(String newNick) {
        user.setNickname(newNick);
    }

    /**
    * Getter for the user-property's nickname.
    * @see is.mjuk.cache.User#getNickname()
    */
    public String getNickname() {
        return user.getNickname();
    } 

    /**
    * WARNING: Will flush cache. Updates the controller's cache layout
    * object.
    * <p>
    * Updates the object containing a {@link is.mjuk.cache.CacheLayout}
    * contained within the controller. Also updates the
    * {@link is.mjuk.cache.AddressLayout} and
    * {@link is.mjuk.cache.DataCache}.
    * Stores the {@link is.mjuk.cache.LayoutDTO} of the created cache
    * layout in the {@link is.mjuk.cache.Storage} singleton.
    */
    public void setCacheLayout(int blockSize, int blockCount,
        int associativity) {
        cacheLayout = new CacheLayout(blockSize, blockCount, associativity);
        addressLayout = cacheLayout.getAddressLayout();
        dataCache = cacheLayout.getDataCache();
        store.storeLayoutDTO(this.cacheLayout.generateLayoutDTO());
    }

    /**
    * Returns a string representation of the cache.
    */
    public String displayCache() {
        return dataCache.displayCache();
    }

    /**
    * Creates and returns data relevant to the simulation as a
    * {@link is.mjuk.cache.SimulationDTO} object.
    * @see is.mjuk.cache.SimulationDTO
    */
    public SimulationDTO getSimulationData(){
        store.storeHitrate(this.dataCache.getHitrate());
        store.storeNickname(this.user.getNickname());
        store.storeDateTime(this.user.getDateTime());
        store.storeLayoutDTO(this.cacheLayout.generateLayoutDTO());

        SimulationDTO simDTO = store.createDTO();
        simDTO.setStores(this.dataCache.getStores());
        simDTO.setLoads(this.dataCache.getLoads());
        return simDTO;
    }
}
