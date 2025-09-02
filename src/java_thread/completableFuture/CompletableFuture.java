package java_thread.completableFuture;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.processing.Completion;

public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
    volatile Object result; // lưu kết quả trả về
    volatile Completion stack; // danh sách callback cần chạy sau khi hoàn thành
    static final Executor asyncPool = ForkJoinPool.commonPool(); // executor mặc định

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        CompletableFuture<U> f = new CompletableFuture<>();
        asyncPool.execute(() -> {
            try {
                f.complete(supplier.get()); // chạy task, gán kết quả
            } catch (Throwable ex) {
                f.completeExceptionally(ex); // gán lỗi nếu có
            }
        });
        return f;
    }

    public boolean complete(T value) {
        if (result == null) {
            result = value;
            postComplete(); // kích hoạt callback trong stack
            return true;
        }
        return false;
    }

    public T get() throws InterruptedException, ExecutionException {
        // Nếu kết quả đã sẵn sàng thì trả về
        // Nếu chưa → block chờ đến khi có
    }

    public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn) {
        CompletableFuture<U> dst = new CompletableFuture<>();
        // khi this hoàn thành → chạy fn.apply(result) → gán vào dst
        // thêm dst vào stack chờ postComplete()
        return dst;
    }

    // ... còn nhiều method: thenAccept, thenCombine, handle, exceptionally,...
}
