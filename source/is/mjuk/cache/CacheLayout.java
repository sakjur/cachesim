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

    private int tagSize;
    private int indexSize;
    private int offsetSize;

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
    public CacheLayout(int blockSize, int blockCount, int associativity)
    {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.associativity = associativity;
        this.calculateAddressLayout();
    }

    public DataCache generateDataCache()
    {
        LayoutDTO layout = this.generateLayoutDTO();
        return new DataCache(layout);
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
        dto.blockSize = this.blockSize;
        dto.blockCount = this.blockCount;
        dto.associativity = this.associativity;
        dto.tagSize = this.tagSize;
        dto.indexSize = this.indexSize;
        dto.offsetSize = this.offsetSize;
        return dto;
    }

    public AddressDTO parseAddress(int address)
    {
        AddressDTO rv = new AddressDTO();
        rv.offset = setOnes(this.offsetSize) & address;
        rv.index = setOnes(this.indexSize, offsetSize) & address;
        rv.tag = setOnes(this.tagSize, offsetSize + indexSize) & address;
        rv.index = rv.index >>> offsetSize;
        rv.tag = rv.tag >>> offsetSize + indexSize;
        return rv;
    }

    /**
    * Calculate and store address layout
    *
    * Uses the cache layout properties to calculate a proper address layout.
    *
    * @throws java.lang.IllegalArgumentException
    */
    private void calculateAddressLayout()
    {
        int offset = evenLog2(blockSize);
        int index = evenLog2(blockCount);
        int tag = MEMORY_ADDRESS_SIZE - (index + offset);
        if (tag < 0)
        {
            throw new IllegalArgumentException();
        }
        this.tagSize = tag;
        this.indexSize = index;
        this.offsetSize = offset;
    }

    /**
    * @param input Amount of bits to set to one
    * @param offset Amount of zeroes right off the ones
    * @return A digit with a row of bits set to one
    */
    private long setOnes(int input, int offset)
    {
        return ((long) Math.pow(2, input)-1) << offset;
    }

    /**
    * @see #setOnes(int input, int offset)
    */
    private long setOnes(int input)
    {
        return setOnes(input, 0);
    }

    /**
    * 
    * @return The value of log2(n)
    * @throws java.lang.IllegalArgumentException
    */
    private int evenLog2(double n)
    {
        double calc = Math.log(n)/Math.log(2.0);
        if (calc % 1.00 != 0.00)
        {
            throw new IllegalArgumentException();
        } 
        return (int) Math.ceil(calc);
    }

    private int evenLog2(int n)
    {
        return evenLog2((double) n);
    }
}