package java_thread.interfacez;

public interface RunnableFuture<V> extends Runnable, Future<V> {
    // chay cac tac vu thiet lap trang thai future
    void run();
}
