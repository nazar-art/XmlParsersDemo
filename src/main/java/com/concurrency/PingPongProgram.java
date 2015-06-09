package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PingPongProgram {

    static final int times = 5;
    static final Phaser p = new Phaser(1) {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            return phase >= times || registeredParties == 0;
        }
    };

    public static class PingPong extends Thread {
        private String msg;

        public PingPong(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            while (!p.isTerminated()) {
                System.out.println(msg);
                p.awaitAdvance(p.arrive() + 1);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start:\n");

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new PingPong("Ping"));
        executor.execute(new PingPong("Pong"));

        while (!p.isTerminated()) {
        }
        System.out.println("\nDone!");
        executor.shutdown();
//        executor.awaitTermination();
    }
}
