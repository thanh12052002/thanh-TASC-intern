package java_thread.pool;

import java_thread.interfacez.Runnable;

@FunctionalInterface
public interface Executor {
    void execute(Runnable command);
}
