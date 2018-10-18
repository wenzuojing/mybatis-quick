package com.github.wens;

import com.github.wens.entity.City;
import com.github.wens.entity.MyCity;
import com.github.wens.mybatis.examples.Example;
import com.github.wens.mybatis.support.Page;
import com.github.wens.service.ICityService;
import com.google.common.base.CaseFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {



        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:service-spring.xml");

        ICityService cityService = applicationContext.getBean(ICityService.class);

        List<City> list = new ArrayList<>(10);

        for(int i = 0  ; i < 100 ; i++ ){
            City city = new City();
            city.setId(100 + i );
            city.setCountry("c" + i );
            city.setName("n" + i );
            city.setState("s"+i );
            list.add(city);
        }

        cityService.insertBatch(list);




    }
}
