package java_thread.interfacez;

@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
