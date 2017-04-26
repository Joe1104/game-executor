package com.snowcattle.game.executor.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangwenping on 17/1/11.
 */
public class ThreadNameFactory implements ThreadFactory {
    private ThreadGroup group;
    private AtomicInteger threadNumber = new AtomicInteger(0);
    private String namePrefix;

    public ThreadNameFactory(String namePreFix)
    {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
                .getThreadGroup();
        this.namePrefix = namePreFix + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r)
    {
        Thread t = new Thread(group, r, namePrefix
                + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}

