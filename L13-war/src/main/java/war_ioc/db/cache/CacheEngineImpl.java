package war_ioc.db.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImpl<K,V> implements CacheEngine<K,V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long idleTimeMs;

    private final Map<K,Element<K,V>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements, long idleTimeMs) {
        this.maxElements = maxElements;
        this.idleTimeMs = idleTimeMs;
    }

    @Override
    public void put(Element<K,V> element) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        K key = element.getKey();
        elements.put(key, element);

        TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
        timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
    }

    @Override
    public Element<K,V> get(K key) {
        Element<K,V> element = elements.get(key);
        if (element != null) {
            if (element.getValue() != null) {
                hit++;
                element.setAccessed();
            } else {
                elements.remove(key);
                element = null;
                miss++;
            }
        } else {
            miss++;
        }
        return element;
    }

    @Override
    public void remove(K key) {
        elements.remove(key);
    }

    @Override
    public long size() {
        long sum = 0;
        for (K key : elements.keySet()) {
            if (get(key) != null) {
                sum += 1;
            }
        }
        return sum;
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<Element<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                Element<K, V> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
