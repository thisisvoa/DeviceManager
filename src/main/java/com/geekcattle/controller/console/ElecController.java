/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.geekcattle.model.console.Elec;
import com.geekcattle.model.console.Menu;
import com.geekcattle.service.console.ElecService;
import com.geekcattle.util.ReturnUtil;
import com.geekcattle.util.UuidUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/elec")
public class ElecController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElecService elecService;

    @RequiresPermissions("elec:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/elec/index";
    }

    @RequiresPermissions("elec:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Elec member) {
        ModelMap map = new ModelMap();
        List<Elec> pageList = elecService.getPageList(member);
        map.put("pageInfo", new PageInfo<Elec>(pageList));
        map.put("queryParam", member);
        return ReturnUtil.Success("加载成功", map, null);
    }

    @RequiresPermissions("elec:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    @ResponseBody
    public Map add(Elec elec, Model model) {
        Map map = new HashMap();
        if (null != (elec.getId())) {
            elec = elecService.getById(elec.getId());

        } else {

        }
        map.put("success", 1);
        map.put("data", elec);
        return map;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @Transactional
    @ResponseBody
    public ModelMap save(@Valid Elec elec, BindingResult result) {
        try {
            if (result.hasErrors()) {
                for (ObjectError er : result.getAllErrors())
                    return ReturnUtil.Error(er.getDefaultMessage(), null, null);
            }
            if (null !=elec && elec.getId() == null) {
                elecService.insert(elec);
            } else {
                elecService.save(elec);
            }

            return ReturnUtil.Success("操作成功", null, "/console/elec/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @ResponseBody
    @RequiresPermissions("elec:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    public ModelMap delete(String[] ids) {
        try {
            if ("null".equals(ids) || "".equals(ids)) {
                return ReturnUtil.Error("Error", null, null);
            } else {
                for (String id : ids) {
                    elecService.deleteById(Integer.valueOf(id));
                }
                return ReturnUtil.Success("Success", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("Error", null, null);
        }
    }

}
