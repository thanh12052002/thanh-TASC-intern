package java_thread.pool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import java_thread.interfacez.Callable;
import java_thread.interfacez.Future;

public interface ExecutorService extends Executor {
    // Add them method chi tiet
    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);

    Future<?> submit(Runnable task);

    // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
    // throws InterruptedException;

    // <T> T invokeAny(Collection<? extends Callable<T>> tasks)
    // throws InterruptedException, ExecutionException;

    // void shutdown();

    // List<Runnable> shutdownNow();

    // boolean isShutdown();
    // boolean isTerminated();
    // boolean awaitTermination(long timeout, TimeUnit unit) throws
    // InterruptedException;

}
