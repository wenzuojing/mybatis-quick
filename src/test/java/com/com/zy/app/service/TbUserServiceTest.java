package com.com.zy.app.service;

/**
 * Created by wens on 16/4/23.
 */

import com.github.wens.mybatisplus.examples.Example;
import com.github.wens.mybatisplus.plugins.Page;
import com.zy.app.entity.TbUser;
import com.zy.app.service.ITbUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-spring.xml"})
public class TbUserServiceTest {

    @Resource
    private ITbUserService userService;


    @Test
    public void test_countByExample() {
        Example<TbUser> example = new Example<>(TbUser.class);
        example.createCriteria().andLike("userName", "qu%");
        long total = userService.countByExample(example);
        System.out.println(total);
    }

    @Test
    public void test_deleteBatchIds() {
        boolean b = userService.deleteBatchIds(Arrays.asList("0066E4DE-0F60-4BD0-982B-978C20858E1C", "xxx"));
        System.out.println(b);
    }

    @Test
    public void test_deleteById() {
        boolean b = userService.deleteById("xxx");
        System.out.println(b);
    }

    @Test
    public void test_deleteByExample() {
        Example<TbUser> example = new Example<>(TbUser.class);
        example.createCriteria().andLike("userName", "qu%");
        boolean b = userService.deleteByExample(example);
        System.out.println(b);
    }

    @Test
    public void test_findById() {
        TbUser tbUser = userService.findById("05472BD9-CC1E-4BE5-B149-E280DC34A56B");
        System.out.println(tbUser);
    }

    @Test
    public void test_findByIds() {
        List<TbUser> tbUsers = userService.findByIds(Arrays.asList("05472BD9-CC1E-4BE5-B149-E280DC34A56B"));
        System.out.println(tbUsers.size() > 0);
    }

    @Test
    public void test_findOneByExample() {
        Example<TbUser> example = new Example<>(TbUser.class);
        example.createCriteria().andEqualTo("userId", "05472BD9-CC1E-4BE5-B149-E280DC34A56B");
        TbUser tbUser = userService.findOneByExample(example);
        System.out.println(tbUser);
    }

    @Test
    public void test_findListByExample() {
        Example<TbUser> example = new Example<>(TbUser.class);
        example.createCriteria().andIsNotNull("userName");
        List<TbUser> tbUsers = userService.findListByExample(example);
        System.out.println(tbUsers.size() > 0);
    }

    @Test
    public void test_findPageByExample() {
        Example<TbUser> example = new Example<>(TbUser.class);
        example.createCriteria().andIsNotNull("userName");
        Page<TbUser> page = userService.findPageByExample(example, 1, 10);
        System.out.println(page.getSize() > 0);
    }

    @Test
    public void test_updateById() {
        TbUser tbUser = new TbUser();
        tbUser.setUserId("05472BD9-CC1E-4BE5-B149-E280DC34A56B");
        tbUser.setCampusName("xxx");
        boolean b = userService.updateById(tbUser);
        TbUser tbUser1 = userService.findById(tbUser.getUserId());
        Assert.assertEquals(tbUser.getCampusName(), tbUser1.getCampusName());
        System.out.println(tbUser1);
    }

    @Test
    public void test_updateSelectiveById() {
        TbUser tbUser = new TbUser();
        tbUser.setUserId("078D23AF-9A76-4673-AF83-A21335101CB0");
        tbUser.setCampusName("xxx");
        boolean b = userService.updateSelectiveById(tbUser);
        TbUser tbUser1 = userService.findById(tbUser.getUserId());
        Assert.assertEquals(tbUser.getCampusName(), tbUser1.getCampusName());
        System.out.println(tbUser1);
    }

    @Test
    public void test_updateSelectiveByExample() {
        TbUser tbUser = new TbUser();
        tbUser.setCampusName("xxx");
        Example<TbUser> example = new Example<>(TbUser.class);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -1);
        example.createCriteria().andGreaterThan("creTime", now.getTime());
        boolean b = userService.updateSelectiveByExample(tbUser, example);
        System.out.println(b);
    }


}
