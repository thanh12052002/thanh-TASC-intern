# cách nhận key của map
- Khi truyền key vào tìm kiếm trong map, map sẽ gọi key.hashCode() của đối tượng bằm thành hashKey --> dùng công thức tính ra index:  index = hash & (table.length-1);

-------------------------------------------------
Ví dụ cụ thể:

Giả sử hash = 99162322 → nhị phân: 00000101111001110101100000100010

table.length = 16 → thì table.length - 1 = 15 → nhị phân: 00000000000000000000000000001111

index = hash & 15;  // Lấy 4 bit cuối của hash


→ index chỉ nằm trong khoảng từ 0 đến 15.
--------------------------------------------------

HashMap có giống như vậy không?

Không hẳn! HashMap không cấp phát liên tục như mảng nguyên thủy. Thay vào đó:

▶️ HashMap dùng mảng nội bộ Node<K, V>[] table

table là một mảng của các object Node, được tạo như:

Node<K,V>[] table = (Node<K,V>[]) new Node<?,?>[16];


Mỗi phần tử table[i] là một "bucket", có thể chứa:

null (nếu chưa có phần tử)

Một Node object (nếu có dữ liệu)

Một chuỗi liên kết Node → Node → ... (nếu có collision)

📍 Khi bạn đã tính được index = hash & (length - 1), thì:

Node<K, V> node = table[index];  // đây là tham chiếu đến object

-----------------------------------------------------------------

Như vậy:

index là chỉ số của phần tử trong mảng table

Nhưng bạn không thể tính được địa chỉ thật trong bộ nhớ như mảng nguyên thủy, vì:

✅ Các object trong Java được cấp phát trên heap và không liên tục. Bộ nhớ thực tế (address) do JVM quản lý và bạn không truy cập trực tiếp được.

-------------------------------------------------------------------------

ArrayCopy, Clone (ứng dụng trong mảng động để mở rộng mảng)

static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
}


hashCode() không đảm bảo duy nhất cho mỗi key, nhưng:

Nếu hai key có hashCode khác nhau → chắc chắn sẽ vào bucket khác nhau → không va chạm.

Nếu hai key trùng hashCode (hiếm nhưng có thể xảy ra) → va chạm → vào cùng bucket → xử lý riêng (bạn nói "10%", tức là collision – đúng rồi, sẽ xử lý sau).

🔑 Dù vậy, HashMap vẫn đảm bảo key là duy nhất nhờ phép so sánh equals() ở cuối quá trình lookup.



Trong HashMap, mảng cũng được tạo nội bộ, developer không thấy

✔️ Hoàn toàn đúng.

Khi bạn tạo:

Map<String, Integer> map = new HashMap<>();


thì bên trong constructor của HashMap, nó tạo ra mảng Node[] table:

this.table = new Node[DEFAULT_INITIAL_CAPACITY];  // thường là 16


Tức là HashMap tự động cấp phát một mảng, giống hệt cách bạn tạo Node[] bên ngoài, chỉ khác là ẩn bên trong class.