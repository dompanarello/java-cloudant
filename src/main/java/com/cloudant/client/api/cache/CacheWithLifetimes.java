/**
 *
 */
package com.cloudant.client.api.cache;

import java.util.Map;

/**
 * @author ArunIyengar
 */
public interface CacheWithLifetimes<K, V> extends Cache<K, V> {
    /**
     * cache a key-value pair
     *
     * @param key      key associated with value
     * @param value    value associated with key
     * @param lifetime lifetime in milliseconds associated with data
     */
    void put(K key, V value, long lifetime);

    /**
     * cache one or more key-value pairs
     *
     * @param map      map containing key-value pairs to cache
     * @param lifetime lifetime in milliseconds associated with each key-value pair.
     *                 If the system supports revalidation of expired cache entries to determine if
     *                 expired entries are really obsolete, a value {@code <= 0} indicates cached
     *                 entry should always be revalidated before being returned to client
     */
    void putAll(Map<K, V> map, long lifetime);

    /**
     * look up a CacheEntry in the cache. The CacheEntry may correspond to
     * expired data. This method can be used to revalidate cached objects whose
     * expiration times have passed
     *
     * @param key key corresponding to value
     * @return value corresponding to key (may be expired), null if key is not
     * in cache
     */
    CacheEntry<V> getCacheEntry(K key);
}