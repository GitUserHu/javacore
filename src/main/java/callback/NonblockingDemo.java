package callback;

import java.util.concurrent.*;

/**
 * @author Jia Bing
 * @date: 2019/12/06 16:02
 * 非阻塞
 */
public class NonblockingDemo {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //usingFutureInterface();
        usingCompleteFutureInterface();
    }

    /**
      * 使用Future接口实现的看起来像非阻塞的操作，其实是阻塞的。
      */
    public static void usingFutureInterface() throws ExecutionException, InterruptedException {
        Future<String> result = executorService.submit(() -> {
            System.out.println("task start...");
            Thread.sleep(3000);
            return "task end";
        });
        System.out.println(result.get());
        doOtherThingsWithoutWaitingResult();
        executorService.shutdown();
    }

    /**
     * 使用CompleteFuture接口实现真正非阻塞。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void usingCompleteFutureInterface() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("start task");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task end";
        });
        completableFuture.thenAcceptAsync(e->{
            // 得到task任务执行的结果后要执行的操作，这里只是简单的打印task执行的结果
            System.out.println(e);
        });
        doOtherThingsWithoutWaitingResult();

    }

    /**
     * do other things without waiting result.
     */
    public static void doOtherThingsWithoutWaitingResult() throws InterruptedException {
        while(true) {
            System.out.println("Doing other thing");
            Thread.sleep(100);
        }
    }
}
