package com.github.wens;

import com.github.wens.entity.City;
import com.github.wens.entity.MyCity;
import com.github.wens.mybatis.examples.Example;
import com.github.wens.service.ICityService;
import com.google.common.base.CaseFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {



        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:service-spring.xml");

        ICityService cityService = applicationContext.getBean(ICityService.class);

        System.out.println(cityService);

        Example<City> cityExample = Example.of(City.class);

        System.out.println(cityExample);

        List<MyCity> list = cityService.findListByExample(cityExample , MyCity.class );

        for(MyCity city : list ){
            System.out.println(city.getName());
        }


    }
}
