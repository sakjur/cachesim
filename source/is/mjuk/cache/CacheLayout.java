package is.mjuk.cache;

import java.lang.Math;
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

    private AddressLayout addressLayout;
    private DataCache dataCache;

    /**
    * Constructor for CacheLayout
    * <p>
    * Setting the internal values to specified data. 
    *
    * @param blockSize Amount of <i>bytes</i> in a single block
    * @param blockCount Amount of blocks in the cache
    * @param associativity Associativity of the cache
    * @throws java.lang.IllegalArgumentException
    */
    public CacheLayout(int blockSize, int blockCount, int associativity) {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.associativity = associativity;
        this.addressLayout = this.calculateAddressLayout();
        this.dataCache = this.generateDataCache();
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
        LayoutDTO dto = new LayoutDTO();
        dto.setBlockSize(this.blockSize);
        dto.setBlockCount(this.blockCount);
        dto.setAssociativity(this.associativity);
        dto.setTagSize(addressLayout.getTagSize());
        dto.setIndexSize(addressLayout.getIndexSize());
        dto.setOffsetSize(addressLayout.getOffsetSize());
        return dto;
    }

    public AddressLayout getAddressLayout() {
        return this.addressLayout;
    }

    public DataCache getDataCache()
    {
        return this.dataCache;
    }

    private DataCache generateDataCache() {
        LayoutDTO layout = this.generateLayoutDTO();
        return new DataCache(layout);
    }

    private AddressLayout calculateAddressLayout() {
        int offset = 0;
        int index = 0;

        if (log2(this.blockSize) % 1.00 == 0.00) {
            offset = (int) log2(this.blockSize);
        } else {
            throw new IllegalArgumentException();
        }

        if (log2(this.blockCount) % 1.00 == 0.00) {
            index = (int) log2(this.blockCount);
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

    private double log2(double n) {
        return Math.log(n)/Math.log(2.0);
    }

    private double log2(int n) {
        return log2((double) n);
    }

}
