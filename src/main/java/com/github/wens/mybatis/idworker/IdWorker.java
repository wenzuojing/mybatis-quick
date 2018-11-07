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
package com.github.wens.mybatis.idworker;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

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


    private static Sequence sequence = new Sequence();

    private static Cache<Long, Long> recentIds = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();

    public static synchronized long getId() {

        while (true) {
            long id = sequence.nextId();
            if (recentIds.getIfPresent(id) != null) {
                //log.warn("Duplication id");
                continue;
            }
            recentIds.put(id, timeGen());
            return id;
        }

    }

    private static long timeGen() {
        return SystemClock.now();
    }


}