package java_collection.map.Ma_Nguon;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;

interface Map_Interface<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    void putAll(Map_Interface<? extends K, ? extends V> m);

    void clear();

    // views
    Set<K> keySet();

    Collection<V> values();

    Set<Entry<K, V>> entrySet();

    //
    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);

        boolean equals(Object o);

        int hashCode();
    }

    default V getOrDefaultCustom(Object key, V defaultValue) {
        V v;
        return ((v = get(key)) != null) ? v : defaultValue;
    }

    default void ForEachCustom(BiConsumer<? super K, ? super V> action) {
        for (Entry<K, V> e : entrySet()) {
            action.accept(e.getKey(), e.getValue());
        }
    }
}
