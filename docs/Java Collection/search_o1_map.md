Cấu trúc cơ bản của HashMap

HashMap là một class chứa mảng nội bộ Node<K,V>[] table.

Mảng table[]:

Là mảng thông thường trong Java, được JVM cấp phát liên tiếp trên heap.

Mỗi phần tử là một reference (địa chỉ) trỏ đến object Node<K,V> trên heap (không phải lưu trực tiếp object).

Kích thước ban đầu mặc định là 16 (power of 2).

🔁 Quy trình khi thêm/tìm kiếm key trong HashMap

Tính hash code:
int h = key.hashCode();

Hash lại (Java 8 trở lên):

int hash = (h = h) ^ (h >>> 16);


Tính index trong table[]:
index = hash & (table.length - 1);
👉 Lý do dùng & thay %: nhanh hơn, do table.length là số mũ của 2.

Truy cập table[index]:

JVM tính địa chỉ thực bằng: address = base + index * size_of_reference

Truy cập trực tiếp ô nhớ đó (O(1)) để lấy Node đang trỏ tới.

So sánh key để lấy đúng phần tử:

Nếu table[index] == null → không có key → return null.

Nếu có Node, cần so sánh:

So sánh hash trước: node.hash == inputHash

Sau đó dùng equals() để so sánh key thật sự.

🎯 Cơ chế đảm bảo key duy nhất

Dù 2 key có thể có cùng hashCode(), HashMap vẫn đảm bảo key là duy nhất vì so sánh equals().

Nếu key đã override hashCode() & equals() → so sánh nội dung.

Nếu chưa override → dùng mặc định từ class Object, tức là so sánh địa chỉ (==).

⚠️ Xử lý va chạm (Collision)

Khi nhiều key cùng index (collision), HashMap:

Dùng danh sách liên kết trong bucket: Node → Node → Node

Nếu số phần tử trong bucket > 8 (Java 8+) → chuyển thành cây đỏ đen → tăng hiệu suất tra cứu từ O(n) lên O(log n)

📈 Mở rộng HashMap (resize)

Khi số phần tử > load factor * capacity (mặc định 0.75 × 16 = 12)

HashMap sẽ:

Tạo mảng mới có kích thước gấp đôi

Re-hash toàn bộ phần tử từ mảng cũ → mảng mới

Không dùng con trỏ để “nối” mảng như C/C++



----Class Node bên trong ----
static class Node<K, V> implements Map.Entry<K, V> {
    final int hash;
    final K key;
    V value;
    Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

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
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return Objects.equals(key, e.getKey()) &&
                   Objects.equals(value, e.getValue());
        }
        return false;
    }
}



-----------------
vậy custom trong Node hashCode và equals là để so sánh 2 Node sao 
ChatGPT đã nói:

👍 Chính xác! Việc override equals() và hashCode() trong class Node (ở bên trong HashMap) là để so sánh 2 Node với nhau, cụ thể là để kiểm tra xem 2 entry (key–value pair) có giống nhau hay không.

📌 Cụ thể:
Bên trong HashMap, mỗi Node<K, V> đại diện cho một entry:
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    // ...
}

✅ equals() trong Node dùng để:

So sánh 2 entry có bằng nhau không, tức là key bằng nhau và value cũng bằng nhau

public final boolean equals(Object o) {
    if (o == this)
        return true;
    if (o instanceof Map.Entry) {
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        return Objects.equals(key, e.getKey()) &&
               Objects.equals(value, e.getValue());
    }
    return false;
}


➜ Dùng khi bạn gọi map.entrySet().contains(...), hoặc bất kỳ lúc nào bạn so sánh Entry trong các tập hợp.

✅ hashCode() trong Node dùng để:
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}


Trả về hashCode đại diện cho toàn bộ Entry

Dùng cho các collection như Set<Map.Entry<...>> hoặc khi cần hash toàn bộ entry

⚠️ Nhưng lưu ý:

Khi bạn put/get vào HashMap, Java chỉ quan tâm đến:

key.equals(...)

key.hashCode()

Chứ không dùng Node.equals() hay Node.hashCode() trong quá trình truy cập HashMap.

📌 Tóm lại:
Mục đích	Dùng equals() / hashCode() của
So sánh 2 key trong Map	Key (Student, String, ...) phải override
So sánh 2 Node (Entry)	Node.equals()
Lưu trữ Entry trong Set	Node.hashCode() và Node.equals()

Nếu bạn cần ví dụ rõ ràng hơn giữa Node và key, mình có thể minh họa thêm
------------------------------------