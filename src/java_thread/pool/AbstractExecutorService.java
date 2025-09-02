package java_thread.pool;

import java_thread.clazz.FutureTask;
import java_thread.interfacez.Callable;
import java_thread.interfacez.Future;
import java_thread.interfacez.RunnableFuture;

public abstract class AbstractExecutorService implements ExecutorService {
    // Cung cap mac dinh cho submmit (Callable)

    public <T> Future<T> submit(Callable<T> task) {
        if (task == null)
            throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task);
        execute(ftask); // dung execute cua class con
        return ftask;
    }

    //
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<>(callable);
    }
    /*
     * protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
     * return new FutureTask<T>(runnable,value);
     * }
     */
}
