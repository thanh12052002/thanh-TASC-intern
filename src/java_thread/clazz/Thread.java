
package java_thread.clazz;

public class Thread implements Runnable {
    private Runnable target; // Ckass dev viết implements interface Runabble
    private String name; // tên Thread;
    private long id; // id Thread
    private int priority; // độ ưu tiên của Thread.
    private ThreadGroup group;
    private boolean daemon = false; // mặc định là tác vụ của user --> JVM van chay neu Thread con.

    private static long threadCount = 0;

    // Constructor khong co runnable truyen vao
    public Thread() {
        this(null, "Thread-" + nextThreadId());
    }

    // constructor co runnable
    public Thread(Runnable target) {
        this(target, "Thread-" + nextThreadId());
    }

    public Thread(Runnable target, String name) {
        this.target = target;
        this.name = name;
        this.id = nextThreadId();
        // group, priority mac dinh
    }

    // setId cho Thread
    private static synchronized long nextThreadId() {
        return threadCount++;
    }

    // ham setDaemon
    public final void setDaemon(boolean on) {
        if (isAlive())
            throw new IllegalThreadStateException();
        this.daemon = on;
    }

    public final boolean isAlive() {
        // check thread start or end ? // do C++ hoat dong
        return false;// gia su == false;
    }
    //

    //
    // Start thread (goi run() trong method moi)
    public synchronized void start() {
        // Goi ma C++ native method tao OS-level thread ->
        // Khi thread chay -> JVM call lai run();
    }

    @Override
    public void run() {
        if (target != null) {
            target.run(); // goi logic cua task truyen vao
        }
    }

    // Mot so method khac
    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final long getId() {
        return id;
    }

    public final boolean isDaemon() {
        return daemon;
    }

    public static native void sleep(long millis) throws InterruptedException;

    public final void join() throws InterruptedException {
        // wait thread end (join)
    }

    public static Thread currenThread() {
        // return thread currently is running
        return null; // gia dinh
    }

    public static void yield() {
        // Nhuong CPU cho thread khac
    }

    public String toString() {
        return "Thread[" + name + "," + priority + "," + (group != null ? group.getName() : "") + "]";
    }
}
