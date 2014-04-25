package is.mjuk.cache;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.Math;

public class CacheLayoutTest {

    @Test
    public void creatingValidCacheLayout() {
        CacheLayout cacheLayout = new CacheLayout(4, 4, 1);
        LayoutDTO cacheData = cacheLayout.generateLayoutDTO();

        assertEquals("BlockSize should be 4", cacheData.getBlockSize(), 4);
        assertEquals("BlockCount should be 4", cacheData.getBlockCount(), 4);
        assertEquals("Associativity should be 1", 
            cacheData.getAssociativity(), 1);
    }

    @Test
    public void creatingDataCache() {
        CacheLayout cacheLayout = new CacheLayout(4, 4, 1);
        DataCache dataCache = cacheLayout.getDataCache();

        assertEquals("There should be one set", dataCache.getNumberOfSets(), 1);
        assertEquals("There should be four blocks", 
            dataCache.getNumberOfBlocks(), 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidCacheLayout() {
        // BlockSize and BlockCount must be powers of two.
        CacheLayout cacheLayout = new CacheLayout(3, 4, 1);
        cacheLayout.getAddressLayout();
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBigAddress() {
        // Will result in an address of size 34 bits, which exceeds
        // maximum 32.
        CacheLayout cacheLayout = new CacheLayout((int) Math.pow(2, 30), 
            (int) Math.pow(2, 4), 1);
        cacheLayout.getAddressLayout();
    }
}
