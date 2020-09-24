package com.company;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTut {

    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(3);

        // submit task and accept the placeholder object for return value
        Future<Integer> future = service.submit(new Task());

        try{
            System.out.println("Result is not calculated yet..");
            // get the task return value
            Integer result = future.get(); // blocking operation
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
                System.out.println("Printing : " + i);
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

