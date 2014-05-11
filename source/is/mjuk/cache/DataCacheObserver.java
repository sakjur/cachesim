package is.mjuk.cache;

/**
* DataCacheObserver is an interface for objects that wish to observe a 
* {@link is.mjuk.cache.DataCache}-object
*
* @see is.mjuk.cache.DataCache
*/
public interface DataCacheObserver {
    public void recvDataCacheUpdate(String dataCacheContent);
}