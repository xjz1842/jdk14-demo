package com.jdk14.demo.concurrent.stream.limit;


public class LeakyBucketDemo {

    //时间刻度
    private static long time = System.currentTimeMillis();

    //桶里的水
    private static int water = 0;

    //桶里的大小
    private static int size = 10;

    //出水的速率
    private static int rate = 3;

    public static boolean grant(){
        //计算出水的数量
        long now = System.currentTimeMillis();

        int out = (int)((now - time)/700 * rate);

        //漏水后的剩余
       water =  Math.max(0,water - out);
       time = now;

       if((water + 1) < size){
           ++water;
           return true;
       }else{
           return false;
       }
    }

    public static void main(String[] args) {

        for(int i=0; i < 5000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(grant()){
                        System.out.println("执行业务逻辑");
                    }else{
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }
}
