package offer;

/**
 * @program: CodeAlgorithm
 * @description: 多线程代码
 * @author: zwh
 * @create: 2021-03-05 08:51
 **/

/**
 * 线程的两种方式：
 * 1. thread
  */

public class ThreadTest extends Thread{
    public static int race = 0;

    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        int numOfThread = 10;
        Thread[] threads = new Thread[numOfThread];
        for(int i = 0;i < numOfThread;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;i < 100;i++){
                        race++;
                        System.out.println(race);
                    }
                }
            });
            threads[i].start();
            
        }

//        while(Thread.activeCount() > 1){
//            Thread.yield();
//        }
        System.out.println(race);

        System.out.println("no end?");
    }
}
