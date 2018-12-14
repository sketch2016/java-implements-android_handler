package com.example.handler;

import java.util.concurrent.DelayQueue;

public class MessageQueue {

    Message mMessages;

    //private LinkedBlockingQueue<com.example.handler.Message> queue = new LinkedBlockingQueue<com.example.handler.Message>();

    DelayQueue<Message> queue = new DelayQueue<>();

    private Object lock = new Object();

    public void enqueue(Message msg) {
        queue.offer(msg);
        System.out.println("enqueue msg: what = " + msg.what);
    }

    public Message next() {
        Message msg = null;
        try {
            msg = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("next msg: what = " + msg.what);
        return msg;
    }

//    public void enqueue(com.example.handler.Message msg) {
//        try {
//            queue.put(msg);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    com.example.handler.Message next(){
//        com.example.handler.Message msg = null;
//        try {
//            msg = queue.take();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return msg;
//    }

//    public void enqueue(com.example.handler.Message msg) {
//        System.out.println("enqueue: msg.what = " + msg.what + " msg.when = " + msg.when);
//        synchronized(this) {
//            if (mMessages == null) {
//                mMessages = msg;
//                System.out.println("notify all with msg:what=" + msg.what);
//                //showMessages();
//                notifyAll();
//                return;
//            }
//
//            com.example.handler.Message m = mMessages;
//            com.example.handler.Message pre = null;
//            while (m != null) {
//                if (msg.when < m.when) {
//                    msg.next = m;
//                    if (pre == null) {
//                        mMessages = msg;
//                    } else {
//                        pre.next = msg;
//                    }
//
//                }
//
//                pre = m;
//                m = m.next;
//            }
//
//            if (m == null) pre.next = msg;
//
//            showMessages();
//        }
//    }
//
//    com.example.handler.Message next() {
//        synchronized (this) {
//            if (mMessages == null) {
//                try {
//                    System.out.println("mMessages is null, start wait");
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //showMessages();
//        while (true) {
//            System.out.println("mMessages.when = " + mMessages.when + " System.currentTimeMillis()=" + System.currentTimeMillis());
//
//            synchronized(this) {
//                long waitTime = mMessages.when - System.currentTimeMillis();
//                if (waitTime <= 0) {
//
//                    com.example.handler.Message m = mMessages;
//                    com.example.handler.Message next = m.next;
//                    m.next = null;
//                    mMessages = next;
//
//                    //System.out.println("return msg: what=" + m.what + " next.what=" + m.next.what);
//                    return m;
//                } else {
//                    try {
//                        wait(waitTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//    }
//
//    private void showMessages() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("showMessages:");
//        com.example.handler.Message msg = mMessages;
//        while (msg != null) {
//            sb.append("msg-what=" + msg.what + "->");
//            //System.out.println("msg: what=" + msg.what);
//            msg = msg.next;
//        }
//
//        System.out.println(sb.toString());
//    }
}
