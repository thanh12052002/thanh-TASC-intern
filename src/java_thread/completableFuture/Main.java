package java_thread.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Tạo completable future chạy async trong executor
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // mô phỏng tác vụ mất 3 giây
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Kết quả";
        }, executor);

        System.out.println("Đang chờ kết quả (non-blocking)...");

        // Gắn callback khi kết quả sẵn sàng
        future.thenAccept(result -> {
            System.out.println("Kết quả: " + result);
            executor.shutdown(); // đảm bảo tắt executor sau khi xử lý xong
        });

        // Nếu bạn muốn giữ chương trình chạy đủ lâu để thấy kết quả (trong demo
        // console)
        try {
            Thread.sleep(4000); // chờ 4 giây để không kết thúc main quá sớm
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
