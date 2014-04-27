package is.mjuk.cache;

public class Controller
{
    private User user;
    private CacheLayout cacheLayout;
    private DataCache dataCache;
    private AddressLayout addressLayout;
    private Storage store = Storage.getStorage();

    public Controller() {
        user = new User();
    }

    public String getDateTimeString() {
        return user.getDateTime().toString();
    }

    public InstructionDTO executeInstruction(String type, long address) {
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
    * @see is.mjuk.cache.User#setNickname(String newNickname)
    */
    public void setNickname(String newNick) {
        user.setNickname(newNick);
    }

    public String getNickname() {
        return user.getNickname();
    } 

    public void setCacheLayout(int blockSize, int blockCount,
        int associativity) {
        cacheLayout = new CacheLayout(blockSize, blockCount, associativity);
        addressLayout = cacheLayout.getAddressLayout();
        dataCache = cacheLayout.getDataCache();
        store.storeLayoutDTO(this.cacheLayout.generateLayoutDTO());
    }

    public String displayCache() {
        return dataCache.displayCache();
    }

    // TODO: Replace with generateCacheDTO()
    public LayoutDTO generateLayoutDTO() {
        try {
           return cacheLayout.generateLayoutDTO();
        } catch (java.lang.NullPointerException e) {
            System.err.println("Must generate cache layout before layout DTO");
            throw e;
        }
    }

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
