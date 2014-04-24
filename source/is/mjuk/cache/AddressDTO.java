package is.mjuk.cache;

/**
* Data relevant for parsing a single address
*/
public class AddressDTO {
    private long tag;
    private long index;
    private long offset;

    public long getTag() {
        return this.tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}