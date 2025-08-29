package java_collection.array;

public class ArrayDemo {

    // Phương thức hiển thị 1D array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    // Phương thức hiển thị 2D array
    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            printArray(arr[i]);
        }
    }

    // Phương thức demo anonymous array
    public static void sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr)
            sum += num;
        System.out.println("Sum = " + sum);
    }

    public static void main(String[] args) {

        // 1. Mảng một chiều
        int[] arr1 = new int[5]; // Khởi tạo mảng rỗng
        arr1[0] = 10;
        arr1[1] = 20;
        arr1[2] = 30;
        arr1[3] = 40;
        arr1[4] = 50;
        System.out.println("Mảng 1D:");
        printArray(arr1);

        int[] arr2 = { 1, 2, 3, 4, 5 }; // Khởi tạo & gán giá trị ngay
        System.out.println("Mảng 1D khởi tạo trực tiếp:");
        printArray(arr2);

        // Duyệt mảng với for-each
        System.out.print("Duyệt arr2: ");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // 2. Mảng hai chiều
        int[][] matrix = new int[3][3];
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = count++;
            }
        }
        System.out.println("Mảng 2D:");
        print2DArray(matrix);

        // 3. Mảng jagged
        int[][] jagged = new int[3][];
        jagged[0] = new int[3];
        jagged[1] = new int[4];
        jagged[2] = new int[2];

        System.out.println("Mảng jagged trước khi gán giá trị:");
        print2DArray(jagged);

        jagged[0][0] = 1;
        jagged[0][1] = 2;
        jagged[0][2] = 3;
        jagged[1][0] = 4;
        jagged[1][1] = 5;
        jagged[1][2] = 6;
        jagged[1][3] = 7;
        jagged[2][0] = 8;
        jagged[2][1] = 9;

        System.out.println("Mảng jagged sau khi gán giá trị:");
        print2DArray(jagged);

        // 4. Anonymous array
        System.out.println("Anonymous array:");
        sumArray(new int[] { 10, 20, 30, 40, 50 });

        // 5. Sao chép mảng
        int[] arrCopy = arr1.clone(); // 1D: deep copy
        System.out.println("Mảng arrCopy clone từ arr1:");
        printArray(arrCopy);

        // 6. Sử dụng System.arraycopy
        int[] arrDest = new int[5];
        System.arraycopy(arr2, 0, arrDest, 0, arr2.length);
        System.out.println("Sao chép arr2 vào arrDest:");
        printArray(arrDest);

        // 7. Truy cập phần tử
        System.out.println("Phần tử arr1[2] = " + arr1[2]);

        // 8. Độ dài mảng
        System.out.println("Độ dài arr1 = " + arr1.length);
    }
}
