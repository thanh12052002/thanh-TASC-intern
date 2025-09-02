package java_thread.clazz;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java_thread.interfacez.Callable;
import java_thread.interfacez.RunnableFuture;

public class FutureTask<V> implements RunnableFuture<V> {
    private volatile int state = NEW;
    private static final int NEW = 0;
    private static final int COMPLETING = 1;
    private static final int NORMAL = 2;
    private static final int EXCEPTIONAL = 3;
    private static final int CANCELLED = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED = 6;

    private Callable<V> callable;
    private Object outcome;
    private volatile Thread runner;
    // private final LockSupport lockSupport = new LockSupport(); // Giả định một class trợ giúp

    public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;
    }

    // @Override
    // public boolean cancel(boolean mayInterruptIfRunning) {
    // if (state != NEW) {
    // return false;
    // }

    // if (mayInterruptIfRunning) {
    // // Cố gắng thay đổi trạng thái thành INTERRUPTING
    // if (tryChangeState(INTERRUPTING, NEW)) {
    // Thread r = runner;
    // if (r != null) {
    // r.interrupt();
    // }
    // while (state == INTERRUPTING) {
    // Thread.yield(); // Đợi cho đến khi luồng bị gián đoạn
    // }
    // }
    // }

    // Cố gắng thay đổi trạng thái thành CANCELLED
    // boolean cancelled = tryChangeState(CANCELLED, NEW);if(cancelled)
    // {
    //     // Thông báo cho các luồng đang chờ
    //     lockSupport.unparkAll();
    // }return cancelled;
}

    // @Override
    // public boolean isCancelled() {
    // return state >= CANCELLED;
    // }

    // @Override
    // public boolean isDone() {
    // return state > COMPLETING;
    // }

    // @Override
    // public V get() throws InterruptedException, ExecutionException {
    // int s = state;
    // if (s <= COMPLETING) {
    // // Nếu tác vụ chưa hoàn thành, chờ
    // s = awaitDone(s);
    // }
    // return report(s);
    // }

    // @Override
    // public V get(long timeout, TimeUnit unit) throws InterruptedException,
    // ExecutionException, TimeoutException {
    // int s = state;
    // if (s <= COMPLETING) {
    // s = awaitDone(s, unit.toNanos(timeout));
    // if (s <= COMPLETING) {
    // throw new TimeoutException();
    // }
    // }
    // return report(s);
    // // }

    // @Override
    // public void run() {
    // // Chỉ chạy nếu tác vụ ở trạng thái NEW
    // if (state != NEW || !tryChangeRunner()) {
    // return;
    // }
    // try {
    // V result = callable.call();
    // set(result); // Tác vụ hoàn thành thành công
    // } catch (Throwable ex) {
    // setException(ex); // Tác vụ ném ngoại lệ
    // }
    // }

    // Các phương thức trợ giúp được đơn giản hóa để minh họa
    // private void set(V v) {
    // if (tryChangeState(COMPLETING, NEW)) {
    // outcome = v;
    // state = NORMAL;
    // lockSupport.unparkAll();
    // }
    // }

    // private void setException(Throwable t) {
    // if (tryChangeState(COMPLETING, NEW)) {
    // outcome = t;
    // state = EXCEPTIONAL;
    // lockSupport.unparkAll();
    // }
    // }

    // @SuppressWarnings("unchecked")
    // private V report(int s) throws ExecutionException {
    // Object x = outcome;
    // if (s == NORMAL)
    // return (V) x;
    // if (s >= CANCELLED)
    // throw new CancellationException();
    // throw new ExecutionException((Throwable) x);
    // }

    // private int awaitDone(int s) throws InterruptedException {
    // // Tên thực tế: awaitDone()
    // // Giả lập cơ chế chờ
    // while (state < NORMAL) {
    // lockSupport.park(); // Giả lập việc luồng bị chặn
    // }
    // return state;
    // }

    // private int awaitDone(int s, long nanos) throws InterruptedException {
    // // Giả lập cơ chế chờ có giới hạn thời gian
    // long deadline = System.nanoTime() + nanos;
    // while (state < NORMAL) {
    // if (nanos <= 0)
    // return state;
    // lockSupport.parkNanos(nanos);
    // nanos = deadline - System.nanoTime();
    // }
    // return state;
    // }

    // Giả lập các phương thức CAS (Compare-and-Swap)
// private boolean tryChangeState(int newState, int expectedState) {
// return state == expectedState;
// }

// private boolean tryChangeRunner() {
// return true;
// }

//     // Giả lập LockSupport
// private static class LockSupport {
// void park() {
// }

// void parkNanos(long nanos) {
// }

// void unparkAll() {
// }
}
