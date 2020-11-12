import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoudy
 * @date 2020/11/10
 */
public class CountDownLatchMethod {

    public static volatile Integer count = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 异步执行 下面方法
        executor.execute(new SetNum(countDownLatch));
        countDownLatch.await();
        int result = count;//这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
    }

    static class SetNum implements Runnable {
        private CountDownLatch latch;

        public SetNum(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                count = sum();
                latch.countDown();
            }
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

}
