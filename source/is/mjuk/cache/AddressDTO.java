package is.mjuk.cache;

/**
* Data relevant for parsing a single address
*/
public class AddressDTO {
    private long tag;
    private long index;
    private long offset;

    /**
     * Get the tag of this address
     */
    public long getTag() {
        return this.tag;
    }

    /**
     * Set the tag of this address
     *
     * @param tag tag to use
     */
    public void setTag(long tag) {
        this.tag = tag;
    }

    /**
     * Get the index of this address
     */
    public long getIndex() {
        return this.index;
    }

    /**
     * Set the index of this address
     *
     * @param index index to use
     */
    public void setIndex(long index) {
        this.index = index;
    }

    /**
     * Get the offset of this address
     */
    public long getOffset() {
        return this.offset;
    }

    /**
     * Set the offset of this address
     *
     * @param offset the offset to use
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }
}
