package com.jdk9.demo.Flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class SubmissionPubliserDemo {

    public static void main(String[] args)throws Exception {

        SubmissionPublisher<Integer> publisher  = new SubmissionPublisher<>();

        publisher.subscribe(new IntegerSubscriber("A"));

        publisher.subscribe(new IntegerSubscriber("B"));

        publisher.subscribe(new IntegerSubscriber("C"));

        publisher.submit(100);

        Thread.currentThread().join(1000);

        publisher.close();

        Thread.sleep(20000);
    }

    private static class IntegerSubscriber implements Flow.Subscriber<Integer>{

        private String name;

        public IntegerSubscriber(String name){
            this.name = name;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("current thread subscribe :" + Thread.currentThread().getName() + ":name"+name);

            subscription.request(1L);
        }

        @Override
        public void onNext(Integer item) {
            System.out.println("current thread subscribe :" + Thread.currentThread().getName() + ", next item:" +item );

        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("current thread complete :" + Thread.currentThread().getName());
        }
    }
}
