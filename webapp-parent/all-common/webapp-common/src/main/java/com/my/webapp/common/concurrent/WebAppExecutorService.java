package com.my.webapp.common.concurrent;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class WebAppExecutorService implements Executor {

    private ExecutorService executorService;
    private static WebAppExecutorService webAppExecutorService;
    private static final int POOL_SIZE = 6;
    private AtomicInteger threadCount = new AtomicInteger(0);

    public void execute(Runnable command) {
        int count = threadCount.addAndGet(1);
        System.out.println("提交第" + count + "个任务！");
        executorService.execute(command);
    }

    private WebAppExecutorService() {

    }

    public <T> Future<T> submit(Callable<T> callable) {
        int count = threadCount.addAndGet(1);
        System.out.println("提交第" + count + "个任务！");
        return executorService.submit(callable);
    }

    /**
     * 获取线程池服务
     *
     * @return
     */
    public synchronized static WebAppExecutorService getWebAppExecutorService() {

        if (webAppExecutorService == null) {
            webAppExecutorService = new WebAppExecutorService();
            int corePoolSize = POOL_SIZE;
            int maximumPoolSize = POOL_SIZE;
            long keepAliveTime = 0L;
            TimeUnit unit = TimeUnit.NANOSECONDS;
            //初始化工作队列，设定队列大小为3
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
            //AbortPolicy 取消策略，当工作队列满后再加入任务会抛出异常RejectedExecutionException
            //DiscardPolicy 丢弃策略，当工作队列满后再加入任务会丢弃该任务
            //DiscardOldestPolicy 丢弃策略，当工作队列满后再加入任务会丢弃队列中队头的任务，然后将该任务加入工作队列
            //CallerRunsPolicy 运行策略，当工作队列满后再加入任务，该任务会在执行executor()或submit()的线程中被执行

            webAppExecutorService.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                    unit, workQueue, threadFactory, handler);
        }

        return webAppExecutorService;
    }

    /**
     * 创建线程池服务
     *
     * @param poolSize 线程池大小
     * @return
     */
    public static WebAppExecutorService newMyExecutorService(int poolSize) {

        WebAppExecutorService webAppExecutorService = new WebAppExecutorService();
        int corePoolSize = poolSize;
        int maximumPoolSize = poolSize;
        long keepAliveTime = 0L;
        TimeUnit unit = TimeUnit.NANOSECONDS;
        //初始化工作队列，设定队列大小为3
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        webAppExecutorService.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, workQueue, threadFactory, handler);
        return webAppExecutorService;
    }

    public int getCount() {
        return threadCount.get();
    }

    public void shotdown() throws InterruptedException{
        executorService.shutdown();
        executorService.awaitTermination(10L, TimeUnit.MINUTES);
    }
}
