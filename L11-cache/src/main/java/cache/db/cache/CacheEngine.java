package cache.db.cache;

public interface CacheEngine<K, V> {

    void put(Element<K,V> element);

    Element<K,V> get(K key);

    void remove(K key);

    long size();

    int getHitCount();

    int getMissCount();

    void dispose();
}
