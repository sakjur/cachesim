package is.mjuk.cache;

import is.mjuk.utils.MisMath;

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
        rv.setOffset(MisMath.intToUnary(this.offsetSize) & address);
        rv.setIndex(MisMath.intToUnary(this.indexSize) & address >>> offsetSize);
        rv.setTag(MisMath.intToUnary(this.tagSize) & address
            >>> offsetSize + indexSize); 
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

}
