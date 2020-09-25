package com.company;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureTut {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        // submit task and accept the placeholder object for return value
        Future<Integer> future = service.submit(new Task());

        Future<Integer> future1 =  service.submit(new Task());
        Future<Integer> future3 = service.submit(new Task());

        AtomicInteger runnableResult = new AtomicInteger();
        FutureTask<AtomicInteger> future4 =  new FutureTask<AtomicInteger>(()->{
            int localResult = 100;
            localResult *=9;
            runnableResult.set(localResult);
        },runnableResult);

        service.submit(future4);

        System.out.println("Future 4 get called : " + future4.get());

        System.out.println("After 4th future call " + Thread.currentThread().getName());


        try{
            System.out.println("Result is not calculated yet..");
            System.out.println("getName call: "+Thread.currentThread().getName());
            // get the task return value
            Integer result = future.get(); // blocking operation
            System.out.println("After future get call : " + Thread.currentThread().getName());

            System.out.println("Result: " + result);
            System.out.println("Running after wait..");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            // checking whether task is executed sequentially or parallel
            for(int i = 0 ;i<10;i++)
                System.out.println("Printing : " + i + "and caller : " + Thread.currentThread().getName());
            return new Random().nextInt();
        }
    }

//    static  class TaskRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            System.out.println("Running from Runnable");
//        }
//    }
}

