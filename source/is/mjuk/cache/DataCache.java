package is.mjuk.cache;

import java.lang.StringBuilder;
import java.lang.Math;

/**
* Stores and operates on cache blocks
* <p>
* Creates a set of cache blocks which can be accessed using indexes.
* Supports associativity (i.e. multiple blocks with same index)
*
*/
public class DataCache {
    private int hits;
    private int misses;
    private int loads;
    private int stores;
    private Block[][] blockset;

    /**
    * Parses a cache layout and generates the block objects
    *
    * @param layout Contains the layout for the cache in a 
    * {@link is.mjuk.cache.LayoutDTO}
    */
    public DataCache(LayoutDTO layout) {
        this.blockset = 
            new Block[layout.getAssociativity()][layout.getBlockCount()];
        for (int i = 0; i < this.blockset[0].length; i++) {
            for (int ii = 0; ii < this.blockset.length; ii++) {
                blockset[ii][i] = new Block();
            }
        }
    }

    /**
    * Returns the data in the cache as a string
    * <p>
    * Returns every {@link is.mjuk.cache.Block} of the cache in string 
    * representation.
    *
    */
    public String displayCache() {
        StringBuilder cacheDisplay = new StringBuilder();
        for (int i = 0; i < this.blockset[0].length; i++) {
            cacheDisplay.append("Index 0x" + Integer.toString(i, 16));
            cacheDisplay.append(": ");
            for (int ii = 0; ii < this.blockset.length; ii++) {
                cacheDisplay.append(blockset[ii][i].toString() + " ");
            }
            cacheDisplay.append("\n");
        }
        return cacheDisplay.toString();
    }

    /**
    * Loads data from a specific cache address
    * <p>
    * Increments the load counter and then checks if a single position
    * in the cache is already existing. If it's not, updates cache
    * accordingly
    */
    public boolean loadData(AddressDTO address){
        this.loads += 1;
        return updateCachePosition(address);
    }

    /**
    * Stores data at a specific cache address
    * <p>
    * Increments the store counter and then checks if a single position
    * in the cache is already existing. If it's not, updates cache
    * accordingly
    */
    public boolean storeData(AddressDTO address){
        this.stores += 1;
        return updateCachePosition(address);
    }

    private boolean updateCachePosition(AddressDTO address) {
        int cacheSet = -1;

        for (int i = 0; i < this.blockset.length; i++) {
            if (this.blockset[i][(int) address.getIndex()]
                .isValid(address.getTag())) {
                this.hits += 1;
                return true;
            } else if (this.blockset[i][(int) address.getIndex()]
                .isValid() == false) {
                cacheSet = i;
            }
        }

        if (cacheSet == -1) {
            cacheSet = (int) Math.floor(Math.random() 
                * this.blockset.length);
        }

        this.blockset[cacheSet][(int) address.getIndex()]
            .setTag(address.getTag());

        this.misses += 1;
        return false;
    }
}
