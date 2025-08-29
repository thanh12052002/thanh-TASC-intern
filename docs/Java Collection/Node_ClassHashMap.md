static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;       // hash của key
    final K key;          // key
    V value;              // value tương ứng
    Node<K,V> next;       // trỏ tới node tiếp theo trong cùng bucket (linked list)

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()       { return key; }
    public final V getValue()     { return value; }
    public final String toString(){ return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            return Objects.equals(key, e.getKey()) &&
                   Objects.equals(value, e.getValue());
        }
        return false;
    }
}



-----------------
a có thể dùng Node ở mọi chỗ yêu cầu Map.Entry (ví dụ entrySet() trả về Set<Map.Entry<K,V>>).     entrySet() là phương thức của Map nó trả về Map.Entry() tức kiểu interface thực ra nó trả về là Set<Node> sao
ChatGPT đã nói:

Chính xác! Bạn phân tích đúng. Mình giải thích kỹ hơn:

1️⃣ entrySet() trong Map

entrySet() là phương thức của Map:

Set<Map.Entry<K,V>> entrySet();


Nó trả về một Set các Entry (interface Map.Entry<K,V>).

2️⃣ Thực tế trong HashMap

HashMap triển khai entrySet() như sau:

Nó trả về một Set mà mỗi phần tử là Node.

Vì Node implements Map.Entry<K,V>, nên Node “được xem như Map.Entry”.

Set đó khai báo kiểu Set<Map.Entry<K,V>>, nhưng bên trong thực chất là Set<Node>.

Ví dụ minh họa:

HashMap<String, String> map = new HashMap<>();
map.put("A", "Apple");
map.put("B", "Banana");

Set<Map.Entry<String, String>> entries = map.entrySet();

for (Map.Entry<String, String> entry : entries) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}


Ở runtime, mỗi entry là Node trong HashMap, nhưng kiểu được nhìn nhận là Map.Entry (interface).
----------------