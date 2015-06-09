package com.concurrency;

class PingPong {

    private String state;

    synchronized void ping(boolean running) {
        if (!running) {
            state = "ponged";
            notify();
            return;
        }
        System.out.print("Ping ");

        state = "ponged";
        notify();

        try {
            while (!state.equals("pinged")) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void pong(boolean running) {
        if (!running) {
            state = "pinged";
            notify();
            return;
        }
        System.out.println("Pong");

        state = "pinged";
        notify();

        try {
            while (!state.equals("ponged")) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable {

    Thread thrd;
    PingPong ppOb;

    MyThread(String name, PingPong pp) {
        thrd = new Thread(this, name);
        ppOb = pp;
        thrd.start();
    }

    @Override
    public void run() {
        if (thrd.getName().compareTo("Ping") == 0) {
            for (int i = 0; i < 5; i++) ppOb.ping(true);
            ppOb.ping(false);
        } else {
            for (int i = 0; i < 5; i++) ppOb.pong(true);
            ppOb.pong(false);
        }
    }
}

public class PingPongDemo {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        MyThread myThreadOne = new MyThread("Ping", pingPong);
        MyThread myThreadTwo = new MyThread("Pong", pingPong);

        try {
            myThreadOne.thrd.join();
            myThreadTwo.thrd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}