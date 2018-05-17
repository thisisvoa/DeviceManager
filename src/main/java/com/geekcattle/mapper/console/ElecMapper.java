/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.mapper.console;

import com.geekcattle.model.console.Admin;
import com.geekcattle.model.console.Elec;
import com.geekcattle.util.CustomerMapper;
import org.springframework.stereotype.Service;


/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:32
 */
@Service
public interface ElecMapper extends CustomerMapper<Elec> {
    Elec selectByElecName(String elecName);
    void deleteById(String Id);
}
