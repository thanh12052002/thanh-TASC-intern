public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {
    private transient HashMap<E,Object> map;

    // Một object constant dùng làm value mặc định
    private static final Object PRESENT = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    // ...
}

---------------
tại sao lại tạo giá trị mặc định là object rỗng mà không phải cái gì khác
ChatGPT đã nói:

Rất hay – đây là câu hỏi giúp hiểu sâu hơn cách thiết kế của HashSet và HashMap.

🎯 Câu trả lời ngắn:

Vì HashSet chỉ quan tâm đến key, không cần lưu value, nên chỉ cần một giá trị placeholder để điền vào value.
Java chọn dùng một object rỗng duy nhất (static final Object PRESENT = new Object();) vì:

✅ Không tốn thêm bộ nhớ cho nhiều value giống nhau

✅ Không ảnh hưởng logic: chỉ cần có mặt là đủ

✅ Không gây nhầm lẫn: bạn sẽ không vô tình truy cập hay dùng đến value

✅ Dễ kiểm tra: dùng == so với PRESENT cũng được

🧠 Câu trả lời chi tiết:
📌 1. Vì HashSet chỉ lưu key, không dùng value
Set<String> set = new HashSet<>();
set.add("apple");
set.add("banana");


Thì thật ra nội bộ là:

map.put("apple", PRESENT);
map.put("banana", PRESENT);


Value không được dùng trong bất kỳ logic nào của HashSet. Nó chỉ là chỗ trống để HashMap hoạt động đúng.

📌 2. Vì dùng 1 object rỗng sẽ tiết kiệm và an toàn hơn

Giả sử bạn dùng "DUMMY" hay 0 làm value, thì:

❌ Gây lãng phí bộ nhớ nếu mỗi phần tử tạo ra 1 new Object() riêng

❌ Nếu là giá trị như "dummy" thì dễ nhầm với giá trị thật

❌ Nếu cho phép value khác nhau thì vi phạm logic Set (chỉ lưu key)

Dùng static final Object PRESENT = new Object(); thì:

✅ Không bao giờ bị sửa đổi

✅ Chỉ có 1 instance dùng chung

✅ Không liên quan đến logic dữ liệu của người dùng

🧪 Demo nhỏ:
Set<String> set = new HashSet<>();
set.add("A");
set.add("B");

System.out.println(set); // [A, B]


Nếu bạn dùng Map như sau:

Map<String, Object> fakeSet = new HashMap<>();
Object PRESENT = new Object();
fakeSet.put("A", PRESENT);
fakeSet.put("B", PRESENT);


Thì bạn vừa mô phỏng lại HashSet một cách chính xác!

✅ Tổng kết:
Lý do dùng Object PRESENT = new Object()	Giải thích
Không cần lưu value thật	Vì Set chỉ cần key
Dùng 1 object chung	Tiết kiệm bộ nhớ
Không tạo nhầm logic dữ liệu	Tránh trùng với giá trị của người dùng
Đảm bảo so sánh bằng ==	Nhanh hơn so sánh equals
--------------
