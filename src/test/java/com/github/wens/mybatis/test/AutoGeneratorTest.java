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
package com.github.wens.mybatis.test;

import com.github.wens.mybatis.annotation.IdType;
import com.github.wens.mybatis.generator.Configuration;
import com.github.wens.mybatis.generator.MyBatisGenerator;

/**
 * <p>
 * 自动生成映射工具类测试
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public class AutoGeneratorTest {

    /**
     * 测试 generate 执行
     * <p>
     * 配置方法查看 {@link Configuration}
     * </p>
     */
    public static void main(String[] args) {    
        Configuration cg = new Configuration();
        cg.setEntityPackage("com.github.wens.mybatis.test.entity");//entity 实体包路径
        cg.setMapperPackage("com.github.wens.mybatis.test.mapper");//binding 映射文件路径
        cg.setServicePackage("com.github.wens.mybatis.test.service");//service 层路径

		/* 此处可以配置 SuperServiceImpl 子类路径，默认如下 */
        //cg.setSuperServiceImpl("com.baomidou.framework.service.impl.SuperServiceImpl");

        cg.setSaveDir("/Users/wens/dev/mybatis-quick/src/test/java/com/github/wens/mybatis/test");// 生成文件保存位置

		/*
         * 设置字段是否为驼峰命名，驼峰 true 下划线分割 false
		 */
        cg.setColumnHump(false);

		/* 数据库相关配置 */
        cg.setDbDriverName("com.mysql.jdbc.Driver");
        cg.setDbUser("root");
        cg.setDbPassword("123456");
        cg.setDbUrl("jdbc:mysql://192.168.15.101:3306/test_db?useUnicode=true&characterEncoding=utf8");

		/*
		 * 表主键 ID 生成类型, 自增该设置无效。
		 * <p>
		 * IdType.AUTO 			数据库ID自增
		 * IdType.ID_WORKER		全局唯一ID，内容为空自动填充（默认配置）
		 * IdType.INPUT			用户输入ID
		 * </p>
		 */
        cg.setIdType(IdType.ID_WORKER);

        cg.setDbPrefix(false);
        new MyBatisGenerator(cg).generate();
    }

}
