package is.mjuk.cache;

/**
* DataCacheObserver is an interface for objects that wish to observe a 
* {@link is.mjuk.cache.DataCache}-object
*
* @see is.mjuk.cache.DataCache
*/
public interface DataCacheObserver {

    /**
    * The observed object will call this on the observing object whenever a
    * observeable event occurs.
    * @param dataCacheContent Updated content of the observed object
    */
    public void recvDataCacheUpdate(String dataCacheContent);
}