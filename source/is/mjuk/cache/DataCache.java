package is.mjuk.cache;

import java.util.ArrayList;
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
    private int hits = 0;
    private int misses = 0;
    private int loads = 0;
    private int stores = 0;
    private Block[][] blockset;
    private ArrayList<DataCacheObserver> observers = 
        new ArrayList<DataCacheObserver>();

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
    */
    public void addObserver(DataCacheObserver observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    /**
    */
    public void removeObserver(DataCacheObserver observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    /**
    * @return Number of sets in the cache (associativity)
    */
    public int getNumberOfSets() {
        return this.blockset.length;
    }

    /**
    * @return Number of blocks in each set (block count)
    */
    public int getNumberOfBlocks() {
        return this.blockset[0].length;
    }

    /**
    * @return Number of hits since creation of DataCache
    */
    public int getHits() {
        return this.hits;
    }

    /**
    * @return Number of misses since creation of DataCache
    */
    public int getMisses() {
        return this.misses;
    }

    /**
    * Returns the hitrate of the cache
    * <p>
    * Calculates number of hits divided by number of hits and misses.
    * Starts at zero if there are no hits nor misses.
    *
    * @return Hitrate since creation of DataCache
    */
    public double getHitrate() {
        if ((this.hits+this.misses) == 0) {
          return 0.0;  
        }

        return (double) this.hits / (double) (this.hits+this.misses);
    }

    /**
    * @return Number of store operations on cache since creation
    */
    public int getStores() {
        return this.stores;
    }

    /**
    * @return Number of load operations on cache since creation
    */
    public int getLoads() {
        return this.loads;
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
        Block currentBlock;

        for (int i = 0; i < this.blockset.length; i++) {
            currentBlock = this.blockset[i][(int) address.getIndex()];

            if (currentBlock.isValid(address.getTag())) {
                this.hits += 1;
                return true;
            } else if (currentBlock.isValid() == false) {
                cacheSet = i;
            }
        }

        if (cacheSet == -1) {
            cacheSet = (int) Math.floor(Math.random() 
                * this.blockset.length);
        }

        currentBlock = this.blockset[cacheSet][(int) address.getIndex()];
        currentBlock.setTag(address.getTag());

        this.notifyObservers();

        this.misses += 1;
        return false;
    }

    private void notifyObservers() {
        for (DataCacheObserver observer : this.observers) {
            observer.recvDataCacheUpdate(this.displayCache());
        }
    }
}
