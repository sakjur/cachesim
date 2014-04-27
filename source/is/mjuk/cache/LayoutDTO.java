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

    /**
    * Getter for the blocksize property of the object
    * @return The blocksize property of the object
    */
    public int getBlockSize() {
        return this.blockSize;
    }

    /**
    * Updates the blocksize property of the object
    * @param blockSize New value for the blocksize of the object
    */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
    * Getter for the blockcount property of the object
    * @return The blockcount property of the object
    */
    public int getBlockCount() {
        return this.blockCount;
    }

    /**
    * Updates the blockcount property of the object
    * @param blockCount New value for the block count of the object
    */
    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    /**
    * Returns the value of the stored associativity-property
    * @return The associativity property of the object
    */
    public int getAssociativity() {
        return this.associativity;
    }

    /**
    * Updates the associativity property of the object
    * @param associativity New value for associativity
    */
    public void setAssociativity(int associativity) {
        this.associativity = associativity;
    }

    /**
    * Getter for the tagsize property of the object
    * @return Tagsize property of the object
    */
    public int getTagSize() {
        return this.tagSize;
    }

    /**
    * Updates the tagsize property of the object
    * @param tagSize New value for the tag size of the object
    */
    public void setTagSize(int tagSize) {
        this.tagSize = tagSize;
    }

    /**
    * Getter for the index size of the object
    * @return The index size property of the object
    */
    public int getIndexSize() {
        return this.indexSize;
    }

    /**
    * Updates the index size property of the object
    * @param indexSize New value for the index size of the object
    */
    public void setIndexSize(int indexSize) {
        this.indexSize = indexSize;
    }

    /**
    * Getter for the offset size
    * @return Offset size property of the object
    */
    public int getOffsetSize() {
        return this.offsetSize;
    }

    /**
    * Updates the offset size value of the object
    * @param offsetSize New value for offset size property.
    */
    public void setOffsetSize(int offsetSize) {
        this.offsetSize = offsetSize;
    }

}