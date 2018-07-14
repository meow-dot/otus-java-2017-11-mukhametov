package war_ioc.db.cache;

import java.lang.ref.SoftReference;

public class Element<K, V> {

    private K key;
    private SoftReference<V> value;
    private long lastAccessTime;

    public Element(K key, V value) {
        this.key = key;
        this.value = new SoftReference<>(value);
        this.lastAccessTime = getCurrentTime();
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value.get();
    }

    public void setValue(V value) {
        this.value = new SoftReference<>(value);
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed() {
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
