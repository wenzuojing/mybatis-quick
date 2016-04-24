package com.zy.app.service.impl;

import com.github.wens.framework.service.impl.SuperServiceImpl;
import com.github.wens.mybatisplus.examples.Example;
import com.github.wens.mybatisplus.plugins.Page;
import com.zy.app.entity.TbUser;
import com.zy.app.mapper.TbUserMapper;
import com.zy.app.service.ITbUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TbUser 表数据服务层接口实现类
 */
@Service
public class TbUserServiceImpl extends SuperServiceImpl<TbUserMapper, TbUser, String> implements ITbUserService {


    @Override
    public Page<TbUser> findUsers() {

        Page<TbUser> tbUserPage = new Page<>(1, 9);
        Example example = new Example(TbUser.class);
        example.createCriteria().andLike("personName", "曲苗苗");
        example.or(example.createCriteria().andLike("userName", "x"));
        example.orderBy("editTime desc");
        List<TbUser> tbUsers = autoMapper.find(example);
        tbUserPage.setRecords(tbUsers);
        return tbUserPage;
    }


}