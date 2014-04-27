package is.mjuk.cache;

import java.lang.StringBuilder;

/**
* A single block within a cache
* <p>
* Stores a tag with a memory address of a single unit of data 
* (when combined) with index.
*/
public class Block {
    private boolean validity;
    private long tag;

    /**
    * Initializes an empty block
    */
    public Block()
    {
        this.validity = false;
        this.tag = 0x00000000;
    }

    /**
    * Checks an input tag to see if block is containing the same tag
    * and is valid.
    *
    * @param tag Tag to be checked against block
    */
    public boolean isValid(long tag) {
        if (this.tag == tag && this.validity == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Checks the block if it's validity property is set to true
    */
    public boolean isValid() {
        if (this.validity == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Updates the content of the block.
    * <p>
    * Sets the tag and the validity to reflect change in the cacheblock.
    *
    * @param tag Address-tag to be stored
    * @param validity New validity of the tag
    */
    public void setData(long tag, boolean validity) {
        this.validity = validity;
        this.tag = tag;
    }

    /**
    * Change the validity of the tag.
    *
    * @param validity New validity of the tag.
    */
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    /**
    * Saves a single tag
    * <p>
    * Also sets <code>validity</code> to <i>true</i>.
    *
    * @see is.mjuk.cache.Block#setData
    * @param tag Address-tag to be saved
    */
    public void setTag(long tag) {
        setData(tag, true);
    }

    /**
    * Returns a string representation of the block
    *
    * @return A string containing the validity and tag of the block
    */
    public String toString() {
        StringBuilder rv = new StringBuilder();
        if (this.validity) {
            rv.append("valid");    
        } else {
            rv.append("invalid");
        }
        rv.append(" 0x");
        rv.append(Long.toString(this.tag, 16));
        return rv.toString();
    }
}