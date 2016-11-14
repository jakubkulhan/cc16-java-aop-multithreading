package cz.codecamp.multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PingPong extends Thread {

    private String name;
    private int n;
    private BlockingQueue<Integer> serving;
    private BlockingQueue<Integer> receiving;
    private Random random = new Random();

    public PingPong(String name,
                    int n,
                    BlockingQueue<Integer> serving,
                    BlockingQueue<Integer> receiving) {
        this.name = name;
        this.n = n;
        this.serving = serving;
        this.receiving = receiving;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; ++i) {
            try {
                int k = receiving.take();
                System.out.println("[" + name + "]: got " + k);
                Thread.sleep(random.nextInt(1000));
                serving.put(k + 1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> q2 = new ArrayBlockingQueue<>(1);

        q2.offer(1);

        PingPong ping = new PingPong("PING", 10, q1, q2);
        PingPong pong = new PingPong("pong", 10, q2, q1);

        ping.start();
        pong.start();

        ping.join();
        pong.join();
    }

}
