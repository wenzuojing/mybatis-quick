/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.wens.mybatisplus.toolkit;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * 在分布式系统中，需要生成全局UID的场合还是比较多的，twitter的snowflake解决了这种需求，
 * 实现也还是很简单的，除去配置信息，核心代码就是毫秒级时间41位+机器ID 10位+毫秒内序列12位。
 * 该项目地址为：https://github.com/twitter/snowflake是用Scala实现的。
 * python版详见开源项目https://github.com/erans/pysnowflake。
 * </p>
 *
 * @author hubin
 * @Date 2016-01-22
 */
public class IdWorker {

    //private static Logger log = LoggerFactory.getLogger(IdWorker.class);

    private static Sequence sequence = new Sequence();

    private static Cache<Long, Long> recentIds = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();

    public static synchronized long getId() {

        while (true){
            long id = sequence.nextId();
            if(recentIds.getIfPresent(id) != null ){
                //log.warn("Duplication id");
                continue;
            }
            recentIds.put(id,timeGen());
            return id ;
        }

    }

    private static long timeGen() {
        return SystemClock.now();
    }

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(10);

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        final List<Long> ll = new CopyOnWriteArrayList<>();



        for(int i = 0 ; i < 10 ; i++ ){
            new Thread(){
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    List<Long> list = new ArrayList<>(600000 );
                    for(int i = 0 ; i < 600000 ; i++ ){

                        list.add(IdWorker.getId());

                    }

                    ll.addAll(list);

                    //System.out.println(ll.size());


                    countDownLatch.countDown();

                    //System.out.println("-----");
                }
            }.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ll.size());
        System.out.println(new HashSet<>(ll).size());


    }

}