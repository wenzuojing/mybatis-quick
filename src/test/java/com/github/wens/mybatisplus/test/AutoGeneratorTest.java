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
package com.github.wens.mybatisplus.test;

import com.github.wens.mybatisplus.annotations.IdType;
import com.github.wens.mybatisplus.generator.AutoGenerator;
import com.github.wens.mybatisplus.generator.ConfigGenerator;

import java.util.ArrayList;
import java.util.List;

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
     * 测试 run 执行
     * <p>
     * 配置方法查看 {@link ConfigGenerator}
     * </p>
     */
    public static void main(String[] args) {    
        ConfigGenerator cg = new ConfigGenerator();
        cg.setEntityPackage("com.yuyou.fn.educational.entity");//entity 实体包路径
        cg.setMapperPackage("com.yuyou.fn.educational.mapper");//mapper 映射文件路径
        cg.setServicePackage("com.yuyou.fn.educational.service");//service 层路径

		/* 此处可以配置 SuperServiceImpl 子类路径，默认如下 */
        //cg.setSuperServiceImpl("com.baomidou.framework.service.impl.SuperServiceImpl");

        cg.setSaveDir("/Users/wens/dev/mybatis-quick/code");// 生成文件保存位置

		/*
         * 设置字段是否为驼峰命名，驼峰 true 下划线分割 false
		 */
        cg.setColumnHump(false);

		/* 数据库相关配置 */
        cg.setDbDriverName("com.mysql.jdbc.Driver");
        cg.setDbUser("test");
        cg.setDbPassword("yuyou2017");
        cg.setDbUrl("jdbc:mysql://118.89.27.66:12420/yyfn?useUnicode=true&characterEncoding=utf8");

		/*
		 * 表主键 ID 生成类型, 自增该设置无效。
		 * <p>
		 * IdType.AUTO 			数据库ID自增
		 * IdType.ID_WORKER		全局唯一ID，内容为空自动填充（默认配置）
		 * IdType.INPUT			用户输入ID
		 * </p>
		 */
        cg.setIdType(IdType.ID_WORKER);


        List<String> tabls = new ArrayList<String>();


        tabls.add("teacher_continue_class_stat");
 


        cg.setTables(tabls);

		/*
		 * 表是否包括前缀
		 * <p>
		 * 例如 mp_user 生成实体类 false 为 MpUser , true 为 User
		 * </p>
		 */
        cg.setDbPrefix(false);
        AutoGenerator.run(cg);
    }

}
