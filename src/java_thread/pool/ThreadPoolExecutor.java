package java_thread.pool;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java_thread.interfacez.Runnable;

public class ThreadPoolExecutor extends AbstractExecutorService {
    private final int corePoolSize;
    private final int maxPoolSize;
    private final BlockingQueue<Runnable> workQueue;

    private int runningWorkers = 0; // đếm số worker (giả lập)
    private final long keepAliveTime = 2000; // 2s timeout cho non-core;

    public ThreadPoolExecutor(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.workQueue = workQueue;

    @Override
    public void execute(Runnable command) {
        System.out.println("Nhận task: " + command);

        // 1. Nếu chưa đủ core → tạo worker
        if (runningWorkers < corePoolSize) {
            addWorker(command, true);
            return;
        }

        // 2. Nếu core đủ → thử cho vào queue
        if (workQueue.offer(command)) {
            System.out.println("→ Đưa task vào queue (size queue: " + workQueue.size() + ")");
            return;
        }

        // 3. Nếu queue đầy → thử tạo worker vượt core
        if (runningWorkers < maxPoolSize) {
            addWorker(command, false);
            return;
        }

        // 4. Nếu vượt max → reject
        System.out.println("→ REJECT task: " + command);
    }

    private void addWorker(Runnable firstTask, boolean core) {
        runningWorkers++;
        System.out.println("→ Tạo " + (core ? "core" : "non-core") +
                " worker (worker hiện tại: " + runningWorkers + ")");
        new Worker(firstTask, core).start();
    }

    // ----------------------
    // Worker inner class
    // ----------------------
    private class Worker extends Thread {
        private Runnable firstTask;
        private boolean core;

        Worker(Runnable firstTask, boolean core) {
            this.firstTask = firstTask;
            this.core = core;
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask(core)) != null) {
                    System.out.println("Worker chạy task: " + task);
                    task.run();
                    task = null; // xong thì reset để lần sau lấy từ queue
                }
            } finally {
                synchronized (ThreadPoolExecutor.this) {
                    runningWorkers--;
                    System.out.println("Worker kết thúc (còn lại: " + runningWorkers + ")");
                }
            }
        }
    }

    private Runnable getTask(boolean core) {
        try {
            if (core) {
                // Core Worker: wait vo thoi han de lay task
                return workQueue.take();
            } else {
                // Non-core worker: cho trong mot thoi gian roi thoat
                return workQueue.poll(keepAliveTime, TimeUnit.MICROSECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    // Demo
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, queue);

        for (int i = 1; i <= 7; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println("   >>> Task " + taskId + " đang chạy trong " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }
            });
        }

        // (để đơn giản mình không implement các method shutdown() của
        // AbstractExecutorService)
    }
}
