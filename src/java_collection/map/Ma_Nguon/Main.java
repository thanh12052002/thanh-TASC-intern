package java_collection.map.Ma_Nguon;

public class Main {
    public static void main(String[] args) {
        Map_Interface<String, Integer> myMap = new HashMap_CusTom<>();
        // add
        myMap.put("apple", 10);
        myMap.put("banana", 20);
        myMap.put("orange", 30);
        // get value
        System.out.println("get value Apple" + myMap.get("apple"));
        System.out.println("get value Banana" + myMap.get("banana"));
        // override value;
        myMap.put("apple", 99);
        System.out.println("apple (updated): " + myMap.get("apple"));

        // Kiem tra ton tai
        System.out.println("Contains key 'orange' ? " + myMap.containsKey("orange"));
        System.out.println("Contains value 30 ?" + myMap.containsValue(30));

        // Duyet entrySet
        System.out.println("\n---EntrySet---");
        for (Map_Interface.Entry<String, Integer> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        // Su dung KeySet va values:
        System.out.println("\nKeys: " + myMap.keySet());
        System.out.println("Values: " + myMap.values());

        // remove
        myMap.remove("banana");
        System.out.println("\nAfter remove banana:");
        System.out.println("banana: " + myMap.get("banana")); // null

        // size
        System.out.println("Size: " + myMap.size());

        // clear
        myMap.clear();
        System.out.println("Size after clear: " + myMap.size()); // 0
    }
}
