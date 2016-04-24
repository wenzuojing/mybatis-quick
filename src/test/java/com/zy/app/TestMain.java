package com.zy.app;

import com.github.wens.mybatisplus.plugins.Page;
import com.zy.app.entity.TbUser;
import com.zy.app.service.ITbUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wens on 16/4/23.
 */
public class TestMain {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("service-spring.xml");

        ITbUserService tbUserService = applicationContext.getBean(ITbUserService.class);

        /*TbUser tbUser = tbUserService.selectById("0403dab8-5ed0-4d66-bafa-3cc323e6b2ec");

        System.out.println(tbUser);*/


        Page<TbUser> users = tbUserService.findUsers();

        for (TbUser tbUser : users.getRecords()) {
            System.out.println(tbUser);
        }

    }
}
