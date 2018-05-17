/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.console;

import com.geekcattle.mapper.console.AdminMapper;
import com.geekcattle.mapper.console.ElecMapper;
import com.geekcattle.model.console.Elec;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:43
 */
@Service
public class ElecService {

    @Autowired
    private ElecMapper elecMapper;

    public List<Elec> getPageList(Elec elec) {
        PageHelper.offsetPage(elec.getOffset(), elec.getLimit(), CamelCaseUtil.toUnderlineName(elec.getSort())+" "+elec.getOrder());
        return elecMapper.selectAll();
    }

    public Integer getCount(Example example){
        return elecMapper.selectCountByExample(example);
    }

    public Elec getById(Integer id) {
        return elecMapper.selectByPrimaryKey(id);
    }

    public Elec findByUsername(String username) {
        return elecMapper.selectByElecName(username);
    }

    public void deleteById(Integer id) {
        elecMapper.deleteByPrimaryKey(id);
    }

    public void insert(Elec elec){
        elec.setCreatedAt(new Date());
        elec.setUpdatedAt(new Date());
        elecMapper.insert(elec);
    }

    public void save(Elec elec) {
        if (elec.getId() != null) {
            elec.setUpdatedAt(new Date());
            elecMapper.updateByPrimaryKeySelective(elec);
        } else {
            elecMapper.insert(elec);
        }
    }

    public void updateExample(Elec elec, Example example){
        elecMapper.updateByExampleSelective(elec, example);
    }



}
