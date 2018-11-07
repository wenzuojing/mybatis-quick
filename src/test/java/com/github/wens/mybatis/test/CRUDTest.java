package com.github.wens.mybatis.test;

import com.alibaba.fastjson.JSONObject;
import com.github.wens.mybatis.example.Example;
import com.github.wens.mybatis.test.entity.TestTb;
import com.github.wens.mybatis.test.service.ITestTbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-spring.xml"})
public class CRUDTest {

    @Resource
    private ITestTbService testTbService;

    @Before
    public void before() {
        testTbService.deleteByExample(Example.of(TestTb.class));
    }


    @Test
    public void test_insert() {
        testTbService.insert(newTestTb());
    }


    @Test
    public void test_insert2() {

    }

    @Test
    public void test_1() {
        testTbService.findById(1l);
    }


    private TestTb newTestTb() {
        TestTb testTb = new TestTb();
        testTb.setId(1);
        testTb.setTinyintField(1);
        testTb.setSmallintField(1);
        testTb.setMediumintField(1);
        testTb.setIntField(1);
        testTb.setBigintField(1l);
        testTb.setFloatField(0.01f);
        testTb.setDoubleField(0.01d);
        testTb.setDecimalField(BigDecimal.valueOf(100));
        testTb.setBitField(true);
        testTb.setCharField("A");
        testTb.setVarcharField("B");
        testTb.setTinytextField("AAAAAAAAAAA");
        testTb.setMediumtextField("BBBBBBBBBB");
        testTb.setLongtextField("CCCCCCCCCCCC");
        testTb.setTinyblobField("DDDDDDDDDDD".getBytes());
        testTb.setMediumblobField("FFFFFFFFF".getBytes());
        testTb.setBlobField("EEEEEEEEEE".getBytes());
        testTb.setLongblobField("HHHHHHHHHHH".getBytes());
        testTb.setBinaryField(null);
        testTb.setVarbinaryField(null);
        testTb.setEnumField("one");
        testTb.setSetField("one");
        testTb.setDateField(new Date());
        testTb.setDatetimeField(new Date());
        testTb.setTimestampField(new Date());
        testTb.setTimeField(new Date());
        testTb.setYearField(2018);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xxx", "fdfdf");
        testTb.setJsonField(jsonObject);
        return testTb;
    }


}
