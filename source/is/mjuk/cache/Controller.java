package is.mjuk.cache;

public class Controller
{
	private User user;
    private CacheLayout cacheLayout;
    private DataCache dataCache;
    private AddressLayout addressLayout;

	public Controller() {
		user = new User();
	}

	public String getDateTime() {
		return user.getDateTime();
	}

    public InstructionDTO executeInstruction(String type, long address) {
        Instruction instruction;

        if(type.equals("load")) {
            instruction = new Instruction(dataCache, addressLayout, 
                Instruction.InstructionType.LOAD, address);
        } else if (type.equals("store")) {            
            instruction = new Instruction(dataCache, addressLayout, 
                Instruction.InstructionType.STORE, address);
        } else {
            InstructionDTO rv = null;
            return rv;
        }

        return instruction.executeInstruction();
    };

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
}
