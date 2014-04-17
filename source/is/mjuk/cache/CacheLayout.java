package is.mjuk.cache;

import java.lang.Math;
import java.lang.IllegalArgumentException;

class CacheLayout
{
    private static final int MEMORY_ADDRESS_SIZE = 32; 

    private int blockSize;
    private int blockCount;
    private int associativity;

    private int tagSize;
    private int indexSize;
    private int offsetSize;

    public CacheLayout(int blockSize, int blockCount, int associativity)
    {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        this.associativity = associativity;
        this.calculateAddressLayout();
    }

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
    * @args int input Amount of bits to set to one
    * @args int offset Amount of zeroes right off the ones
    * @return A digit with a row of bits set to one
    */
    private long setOnes(int input, int offset)
    {
        return ((long) Math.pow(2, input)-1) << offset;
    }

    private long setOnes(int input)
    {
        return setOnes(input, 0);
    }

    /**
    * 
    * @return The value of log2(n)
    * @throws IllegalArgumentException
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