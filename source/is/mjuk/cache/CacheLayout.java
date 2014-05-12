package is.mjuk.cache;

import is.mjuk.utils.MisMath;
import java.lang.IllegalArgumentException;

/**
* Template for a cache
* <p>
* Calculating the necessary parameters to generate a datacache and 
* parsing addresses for accessing the cache.
* @author   Emil Tullstedt <emiltu@kth.se>
* @version  0.1
*/
public class CacheLayout
{
    private static final int MEMORY_ADDRESS_SIZE = 32; 

    private int blockSize;
    private int blockCount;
    private int associativity;

    private AddressLayout addressLayout = null;
    private DataCache dataCache = null;

    /**
    * Constructor for CacheLayout
    * <p>
    * Setting the internal values to specified data. 
    *
    * @param blockSize Amount of <i>bytes</i> in a single block
    * @param blockCount Amount of blocks in the cache
    * @param associativity Associativity of the cache
    */
    public CacheLayout(int blockSize, int blockCount, int associativity) {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.associativity = associativity;
    }

    /**
    * Generating a object containing the properties of the cache and
    * it's address.
    *
    *
    * @return A LayoutDTO containing the block size, block count,
    * associativity, tag size, index size and offset size of the
    * cache.
    */
    public LayoutDTO generateLayoutDTO()
    {
        addressLayout = this.getAddressLayout();
        LayoutDTO dto = new LayoutDTO();
        dto.setBlockSize(this.blockSize);
        dto.setBlockCount(this.blockCount);
        dto.setAssociativity(this.associativity);
        dto.setTagSize(addressLayout.getTagSize());
        dto.setIndexSize(addressLayout.getIndexSize());
        dto.setOffsetSize(addressLayout.getOffsetSize());
        return dto;
    }

    /**
    * Returns the {@link is.mjuk.cache.AddressLayout} of the current
    * CacheLayout. The AddressLayout is generated if it's not defined
    * already.
    *
    * @return {@link is.mjuk.cache.AddressLayout}
    */
    public AddressLayout getAddressLayout() {
        if (this.addressLayout == null) {
            this.addressLayout = this.calculateAddressLayout();
        }

        return this.addressLayout;
    }

    /**
    * Returns the {@link is.mjuk.cache.DataCache} associated with the
    * CacheLayout-object. The DataCache is generated if it's not defined
    * already.
    *
    * @return {@link is.mjuk.cache.DataCache}
    */
    public DataCache getDataCache()
    {
        if (this.dataCache == null) {
            this.dataCache = this.generateDataCache();    
        }
        
        return this.dataCache;
    }

    private DataCache generateDataCache() {
        LayoutDTO layout = this.generateLayoutDTO();
        return new DataCache(layout);
    }

    private AddressLayout calculateAddressLayout() {
        int offset = 0;
        int index = 0;

        if (MisMath.log2(this.blockSize) % 1.00 == 0.00) {
            offset = (int) MisMath.log2(this.blockSize);
        } else {
            throw new IllegalArgumentException();
        }

        if (MisMath.log2(this.blockCount) % 1.00 == 0.00) {
            index = (int) MisMath.log2(this.blockCount);
        } else {
            throw new IllegalArgumentException();
        }

        int tag = MEMORY_ADDRESS_SIZE - (index + offset);
        if (tag < 0) {
            throw new IllegalArgumentException();
        }
        
        AddressLayout rv = new AddressLayout(tag, index, offset);
        return rv;
    }

}
