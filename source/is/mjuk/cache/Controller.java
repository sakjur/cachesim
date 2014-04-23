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

    public void executeInstruction(String instruction, long address) {
        AddressDTO addressdto = addressLayout.parseAddress(address);
        
        if(instruction.equals("load")) {
            dataCache.loadData(addressdto);
        } else if (instruction.equals("store")) {            
            dataCache.storeData(addressdto);
        }
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
