package is.mjuk.cache;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressLayoutTest {

    @Test
    public void creatingValidAddressLayout() {
        CacheLayout cacheLayout = new CacheLayout(4, 4, 1);
        LayoutDTO cacheData = cacheLayout.generateLayoutDTO();

        assertEquals("Tag should be ", 28, cacheData.getTagSize());
        assertEquals("Index should be 4", 2, cacheData.getIndexSize());
        assertEquals("Offset should be 1", 2, cacheData.getOffsetSize());
    }

    @Test
    public void parseValidAddress() throws IllegalAddressException {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        AddressDTO addressDTO = addressLayout.parseAddress(0xABAD1DEC);
        assertEquals("Offset should be 0xC ", 0xC, addressDTO.getOffset());
        assertEquals("Index should be 0xE ", 0xE, addressDTO.getIndex());
        assertEquals("Tag should be 0xABAD1D ", 0xABAD1D,
            addressDTO.getTag());
    }

    @Test(expected=IllegalAddressException.class)
    public void parseInvalidAddressNonDivideableByFour()
    throws IllegalAddressException {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        AddressDTO addressDTO = addressLayout.parseAddress(0xABAD1DEA);
    }

}