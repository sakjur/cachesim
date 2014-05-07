package is.mjuk.cache;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
    AddressLayoutTest.class,
    CacheLayoutTest.class,
    DataCacheTest.class,
    StorageTest.class
})
public class TestMain {
    // Intentionally left empty
}