package is.mjuk.cache;

/**
* Object for transferring cache layout data.
*/
public class LayoutDTO
{
    private int blockSize;
    private int blockCount;
    private int associativity;
    private int tagSize;
    private int indexSize;
    private int offsetSize;

    public int getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getBlockCount() {
        return this.blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public int getAssociativity() {
        return this.associativity;
    }

    public void setAssociativity(int associativity) {
        this.associativity = associativity;
    }

    public int getTagSize() {
        return this.tagSize;
    }

    public void setTagSize(int tagSize) {
        this.tagSize = tagSize;
    }

    public int getIndexSize() {
        return this.indexSize;
    }

    public void setIndexSize(int indexSize) {
        this.indexSize = indexSize;
    }

    public int getOffsetSize() {
        return this.offsetSize;
    }

    public void setOffsetSize(int offsetSize) {
        this.offsetSize = offsetSize;
    }

}