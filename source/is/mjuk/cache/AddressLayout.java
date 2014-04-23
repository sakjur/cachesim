package is.mjuk.cache;

/**
* Stores data relevant to parsing addresses for the cache
*/
public class AddressLayout {

    private int tagSize;
    private int indexSize;
    private int offsetSize;

    public AddressLayout(int tag, int index, int offset) {
        this.tagSize = tag;
        this.indexSize = index;
        this.offsetSize = offset;
    }

    /**
    * Splits an address to an {@link is.mjuk.cache.AddressDTO}
    * <p>
    * The address is split into a tag, index and offset to be used when
    * locating the correct cache block for storing/loading the data.
    *
    * @see is.mjuk.cache.AddressDTO
    * @param address Address to be parsed into an AddressDTO 
    * @return An {@link is.mjuk.cache.AddressDTO} containing the 
    * tag, index and offset of the input address
    */
    public AddressDTO parseAddress(long address)
    {
        AddressDTO rv = new AddressDTO();
        rv.offset = intToUnary(this.offsetSize) & address;
        rv.index = intToUnary(this.indexSize) & address >>> offsetSize;
        rv.tag = intToUnary(this.tagSize) & address >>> offsetSize + indexSize; 
        return rv;
    }

    /**
    * Returns the number of bits in the address tag
    *
    * @return Bits in address tag
    */
    public int getTagSize() {
        return this.tagSize;
    }

    /**
    * Returns the number of bits in the address index
    * 
    * @return Bits in address index
    */
    public int getIndexSize() {
        return this.indexSize;
    }

    /**
    * Returns the number of bits in the address offset
    *
    * @return Bits in the address offset
    */
    public int getOffsetSize() {
        return this.offsetSize;
    }

    /**
    * Returns the unary representation of an integer.
    * <p>
    * Sets the amount of bits in input to one.
    *
    * @param input Amount of bits to set to one
    * @return A digit with a row of bits set to one
    */
    private long intToUnary(int input)
    {
        return ((long) Math.pow(2, input)-1);
    }
}
