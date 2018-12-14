package com.example.handler;

public class Main {

    public static void main(String[] args) {
        new Main().start();

    }

    private void start() {
        Looper.prepare();

        prepareTestThread();

        Looper.loop();

        throw new RuntimeException("exit unexpected!");
    }

    private Handler handler;

    private void prepareTestThread() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("time:" + System.currentTimeMillis() + " Thread " + Thread.currentThread().getName()
                        + " receive msg:{" + msg.obj + " what=" + msg.what + " when=" + msg.when + "}");
            }
        };

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain(handler);
                msg.what = 1;
                msg.obj = "msg from " + Thread.currentThread().getName();
                //handler.sendMessage(msg);
                handler.sendMessageDelayed(msg, 100);
            }
        });
        thread1.setName("thread1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain(handler);
                msg.what = 2;
                msg.obj = "msg from " + Thread.currentThread().getName();
                //handler.sendMessage(msg);
                handler.sendMessageDelayed(msg, 200);
            }
        });
        thread2.setName("thread2");


        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain(handler);
                msg.what = 3;
                msg.obj = "msg from " + Thread.currentThread().getName();
                //handler.sendMessage(msg);
                handler.sendMessageDelayed(msg, 300);
            }
        });
        thread3.setName("thread3");


        thread1.start();
        thread2.start();
        thread3.start();
    }


}
