package java_collection.map.Ma_Nguon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashMap_CusTom<K, V> implements Map_Interface<K, V>, Cloneable, Serializable {
    static class Node<K, V> implements Map_Interface.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        //
        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //
        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
            // oldValue --> method return ve gia tri cu truoc khi thay doi
        }

        public final boolean equals(Object o) {
            if (o instanceof Map_Interface.Entry) {
                Map_Interface.Entry<?, ?> e = (Map_Interface.Entry<?, ?>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }

        //
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    // end static class Node
    transient Node<K, V>[] table;
    transient int size;
    int threshold;
    final float loadFactor;

    //
    public HashMap_CusTom() {
        this.loadFactor = 0.75f;
    }

    //
    public HashMap_CusTom(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    //
    public HashMap_CusTom(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        //
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    // HashKey
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // put
    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;

        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        i = (n - 1) & hash; // công thức tính index từ hash.

        // Case 1: Nếu node tại index chưa có --> thêm vào
        if ((p = tab[i]) == null)
            tab[i] = new Node<>(hash, key, value, null);
        //

        // case 2: Node != null (đã tồn tại) --> chia thành 2 case tiếp
        // case 2.2 --> Node thêm vào trùng với Node có trong cây liên kết đơn.
        // case 2.3 --> Node thêm vào không trùng node nào thì ta sẽ thêm mới Node vào
        // cuối cây.
        else {
            Node<K, V> e;
            K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else {
                for (;;) {
                    if ((e = p.next) == null) {
                        p.next = new Node<>(hash, key, value, null);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    p = e; // di chuyển con trỏ p đến node tiếp theo.
                }
            }
            //
            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent)
                    e.value = value;
                return oldValue;
            }
        }
        //
        size++;
        if (size > threshold) {
            resize();
        }
        return null;
    }

    // resize
    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int newCap = oldCap == 0 ? 16 : oldCap * 2;
        //
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        threshold = (int) (newCap * loadFactor);
        if (oldTab != null) {
            for (Node<K, V> e : oldTab) {
                while (e != null) {
                    Node<K, V> next = e.next;
                    int i = (newCap - 1) & e.hash;
                    e.next = newTab[i];
                    newTab[i] = e;
                    e = next;
                }
            }
        }
        return newTab;
    }

    // get Value theo Key
    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
    //

    // get Node
    public Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;
        //
        if ((tab = table) != null &&
                (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            //
            for (e = first.next; e != null; e = e.next) {
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k)))) {
                    return e;
                }
            }
            return null;
        }
        return null;
    }

    //
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (table != null) {
            for (Node<K, V> node : table) {
                while (node != null) {
                    if (Objects.equals(node.value, value))
                        return true;
                    node = node.next;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        Node<K, V>[] tab = table;
        if (tab == null || size == 0)
            return null;
        int hash = hash(key);
        int i = (tab.length - 1) & hash;
        Node<K, V> prev = null, node = tab[i];
        while (node != null) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                if (prev == null)
                    tab[i] = node.next;
                else
                    prev.next = node.next;
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void clear() {
        table = null;
        size = 0;
    }

    @Override
    public void putAll(Map_Interface<? extends K, ? extends V> m) {
        for (Map_Interface.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Map_Interface.Entry<K, V> e : entrySet()) {
            set.add(e.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map_Interface.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map_Interface.Entry<K, V>> entrySet() {
        Set<Map_Interface.Entry<K, V>> set = new HashSet<>();
        if (table != null) {
            for (Node<K, V> node : table) {
                while (node != null) {
                    set.add(node);
                    node = node.next;
                }
            }
        }
        return set;
    }

}

/*
 * Hàm tableSizeFor(int cap) trong HashMap dùng để tính kích thước mảng nội bộ
 * (table) sao cho là power of 2 lớn nhất >= cap. Đây là chi tiết lý do:
 * 
 * 1️⃣ Vấn đề cần giải quyết
 * 
 * HashMap lưu dữ liệu trong một mảng Node[] gọi là table.
 * 
 * Khi thêm key, vị trí index trong mảng được tính bằng:
 * 
 * index = (table.length - 1) & hash(key)
 * 
 * 
 * Cách này chỉ đúng nếu table.length là power of 2. Nếu không phải, hash sẽ
 * phân bố không đều, dễ gây collision → hiệu năng giảm.
 */