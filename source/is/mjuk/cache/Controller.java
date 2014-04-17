package is.mjuk.cache;

class Controller
{
	private User user;
    private CacheLayout cacheLayout;

	public Controller()
	{
		user = new User();
	}

	public String getDateTime()
	{
		return user.getDateTime();
	}

    public void setNickname(String newNick)
    {
        user.setNickname(newNick);
    }

    public String getNickname()
    {
        return user.getNickname();
    } 

    public void setCacheLayout(int blockSize, int blockCount, int associativity)
    {
        cacheLayout = new CacheLayout(blockSize, blockCount, associativity);
    }

    public AddressDTO parseAddress()
    {
        return cacheLayout.parseAddress(0xABAD1DEA);
    }

    // TODO: Replace with generateCacheDTO()
    public LayoutDTO generateLayoutDTO()
    {
        try {
           return cacheLayout.generateLayoutDTO();
        } catch (java.lang.NullPointerException e) {
            System.err.println("Must generate cache layout before layout DTO");
            throw e;
        }
    }    
}
