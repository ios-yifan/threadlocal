package com.sail;

public class Main {

    //构建 ThreadLocal 并制定初始值。
    public ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){

        //重写此方法来给一个默认值。
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

//    //构建 ThreadLocal 并制定初始值。
//    public ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>();

    /**
     * 启动线程
     */
    public void startThreadArray(){
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TestThread(i));
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    /**
     * 创建线程
     */
    public class TestThread implements Runnable {

        int id;
        public TestThread(int id){
            this.id = id;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "启动线程");
            Integer s = 1 + id;
            integerThreadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + ":" + integerThreadLocal.get());
            //如果不调用 remove 会发生内存泄露
            integerThreadLocal.remove();
        }
    }


    public static void main(String[] args) {
	// write your code here
        Main m = new Main();
        m.startThreadArray();
    }
}
