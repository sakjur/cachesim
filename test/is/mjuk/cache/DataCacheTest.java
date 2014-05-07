package is.mjuk.cache;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataCacheTest {

    @Test
    public void constructCache() {
        CacheLayout cacheLayout = new CacheLayout(4, 4, 1);
        DataCache dataCache = cacheLayout.getDataCache();

        assertEquals("There should be one set", dataCache.getNumberOfSets(), 1);
        assertEquals("There should be four blocks", 
            dataCache.getNumberOfBlocks(), 4);
        assertEquals("There shouldn't be any hits", dataCache.getHits(), 0);
        assertEquals("There shouldn't be any misses", dataCache.getMisses(), 0);
        assertEquals("There shouldn't be any stores", dataCache.getStores(), 0);
        assertEquals("There shouldn't be any loads", dataCache.getLoads(), 0);
    }

    @Test
    public void loadData() throws IllegalAddressException {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        assertFalse("1) Miss", 
            dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0)));
        
        assertTrue("2) Hit", 
            dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0)));
        
        assertFalse("3) Miss", 
            dataCache.loadData(addressLayout.parseAddress(0xADA5F000)));

        assertFalse("4) Miss",
            dataCache.loadData(addressLayout.parseAddress(0xAAAABBE0)));

        assertFalse("5) Miss (unloaded)", 
            dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0)));

        assertTrue("6) Hit",
            dataCache.loadData(addressLayout.parseAddress(0xADA5F000)));

        assertEquals("There should be 6 loads", 6, dataCache.getLoads());
        assertEquals("There should be 0 stores", 0, dataCache.getStores());
        assertEquals("There should be 4 misses", 4, dataCache.getMisses());
        assertEquals("There should be 2 hits", 2, dataCache.getHits());
    }

    @Test
    public void storeData() throws IllegalAddressException {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        assertFalse("1) Miss", 
            dataCache.storeData(addressLayout.parseAddress(0xABAD1DE0)));
        
        assertTrue("2) Hit", 
            dataCache.storeData(addressLayout.parseAddress(0xABAD1DE0)));
        
        assertFalse("3) Miss", 
            dataCache.storeData(addressLayout.parseAddress(0xADA5F000)));

        assertFalse("4) Miss",
            dataCache.storeData(addressLayout.parseAddress(0xAAAABBE0)));

        assertFalse("5) Miss (unloaded)", 
            dataCache.storeData(addressLayout.parseAddress(0xABAD1DE0)));

        assertTrue("6) Hit",
            dataCache.storeData(addressLayout.parseAddress(0xADA5F000)));

        assertEquals("There should be 0 loads", 0, dataCache.getLoads());
        assertEquals("There should be 6 stores", 6 , dataCache.getStores());
        assertEquals("There should be 4 misses", 4, dataCache.getMisses());
        assertEquals("There should be 2 hits", 2, dataCache.getHits());
    }

    @Test
    public void hitRate() throws IllegalAddressException {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        assertEquals("0 Hitrate should begin at 0.00", 
            0.00, dataCache.getHitrate(), 0.00);

        dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0));

        assertEquals("1 Hitrate should be 0.00", 
            0.00, dataCache.getHitrate(), 0.00);

        dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0));

        assertEquals("2 Hitrate should be 0.50", 
            0.50, dataCache.getHitrate(), 0.00);

        dataCache.storeData(addressLayout.parseAddress(0xABAD1DE0));

        assertEquals("3 Hitrate should be 0.66",
            0.66, dataCache.getHitrate(), 0.01);

        dataCache.storeData(addressLayout.parseAddress(0xABAD1DC0));

        assertEquals("4 Hitrate should be 0.50",
            0.5, dataCache.getHitrate(), 0.0);

        dataCache.storeData(addressLayout.parseAddress(0xAAAABBE0));

        assertEquals("5 Hitrate should be 0.40",
            0.4, dataCache.getHitrate(), 0.01);

        dataCache.loadData(addressLayout.parseAddress(0xABAD1DE0));

        assertEquals("6 Hitrate should be 0.33",
            0.33, dataCache.getHitrate(), 0.01);
    }

}