import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ReatLimiterDemo {
    private  static  ConcurrentHashMap<String,RateLimiter> resourceLimiter = new ConcurrentHashMap<>();

    static {
      createResourceLimit("order",50);
    }

    public static  void  createResourceLimit(String resource, double qps){

        if(resourceLimiter.contains(resource)){
            resourceLimiter.get(resource).setRate(qps);
        }else{
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceLimiter.putIfAbsent(resource,rateLimiter);
        }
    }

    public static void main(String[] args) {
        for(int i=0; i < 5000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(resourceLimiter.get("order").tryAcquire(10, TimeUnit.MILLISECONDS)){
                        System.out.println("执行业务逻辑");
                    }else{
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }
}
