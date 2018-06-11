/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geekcattle.SpringUtil;
import com.geekcattle.model.console.ViewBO;
import com.geekcattle.model.member.Member;
import com.geekcattle.netty.bean.Client;
import com.geekcattle.netty.msg.MSG_0x2001;
import com.geekcattle.netty.msg.MsgFutureManager;
import com.geekcattle.netty.msg.MsgHeader;
import com.geekcattle.netty.msg.SessionFutureKey;
import com.geekcattle.netty.server.TCPServer;
import com.geekcattle.service.member.MemberService;
import com.geekcattle.util.ReturnUtil;
import com.geekcattle.utils.soket.msg.ClientManager;
import com.geekcattle.utils.soket.msg.Converter;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/device")
public class DeviceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    TCPServer tcpServer;
    @Autowired
    private MemberService memberService;

    @RequiresPermissions("device:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        System.out.println("aa");
        return "console/device/index";
    }

    @RequestMapping(value = "/{mac}/view", method = {RequestMethod.GET})
    public String view(Model model,@PathVariable String mac) {
        model.addAttribute("mac",mac);
        return "console/device/view";
    }

    @RequestMapping(value = "/{mac}/toview", method = {RequestMethod.GET})
    public ModelAndView toview(Model model, @PathVariable String mac) {
        model.addAttribute("mac",mac);
        return new ModelAndView ("console/device/toview");
    }

    @RequestMapping(value = "/send2001", method = {RequestMethod.GET})
    public @ResponseBody
    Map send2001() {
        Map<String, String> map = new HashMap<String, String>();
    //    ViewBO viewBO = new ViewBO();
        MSG_0x2001 request = new MSG_0x2001();
//				double[] wgs84ToBD09 = EvilTransform.WGS84ToBD09(12345615, 125645891);
        request.setData("aa");
        MsgHeader dd=new MsgHeader();
        request.setHead(dd);
        long seq = request.getHead().getSeq();
        tcpServer.sendWithoutCache(request);
//        TCPServer.getSingletonInstance().sendWithoutCache(request);
        MsgFutureManager msgFutureManager = (MsgFutureManager) SpringUtil.getBean("msgFutureManager");
        String json = null;
        MSG_0x2001 sessionFuture = null;
        long waitTime = 1000 * 3;
        long total = waitTime / 10L;
        long count = 0;
        while (count <= total) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            SessionFutureKey futureKey = new SessionFutureKey();
            futureKey.setSeq(seq);
           // futureKey.setDeviceId("111");
            futureKey.setDeviceType("A");
            sessionFuture = (MSG_0x2001) msgFutureManager.get(futureKey);
            if (null != sessionFuture) {

                msgFutureManager.remove(futureKey);
                json = JSON.toJSONString(sessionFuture);
                break;
                //处理data


            }


        }

        if (null != sessionFuture) {
            String jsonStr = JSON.toJSONString(sessionFuture);
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String jsonObject1 = (String) jsonObject.get("data");
            JSONObject jj = JSONObject.parseObject(jsonObject1);
            map.put("data", jsonObject1);
        }
        return map;
    }
    @RequestMapping(value = "/online",  produces = { "application/json;charset=UTF-8" },method = {RequestMethod.GET})
    public @ResponseBody
    Map online() {
        Map<String, String> map = new HashMap<String, String>();
        ConcurrentHashMap<String, Client> clientMap = ClientManager.getClientMap();
        Boolean isEmp = clientMap.isEmpty();
        if(!isEmp){
            for(Map.Entry<String, Client> entry: clientMap.entrySet()) {
                Client value = entry.getValue();
                System.out.println("Key = " + entry.getKey() + ", Value = " + value);
                map.put(entry.getKey(), value.toString());
            }
        }

        return map;

    }

    @RequestMapping(value = "/{mac}/send2001",  produces = { "application/json;charset=UTF-8" },method = {RequestMethod.GET})
    public @ResponseBody
    Map send2001(@PathVariable String mac) {
        MsgFutureManager msgFutureManager = (MsgFutureManager) SpringUtil.getBean("msgFutureManager");
        Map<String, String> map = new HashMap<String, String>();
        ViewBO viewBO = new ViewBO();
        MSG_0x2001 request = new MSG_0x2001();

        try {
            request.setData("aa");
            MsgHeader head = request.getHead();
            long seq = head.getSeq();
            head.setMac(mac);
            Client client = ClientManager.getClientByMac(mac);

            if (null != client) {
                client.getChannel().writeAndFlush(request);
                String json = null;
                MSG_0x2001 sessionFuture = null;
                long waitTime = 1000 * 3;
                long total = waitTime / 10L;
                long count = 0;
                while (count <= total) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    SessionFutureKey futureKey = new SessionFutureKey();
                    futureKey.setSeq(seq);
                    String s = Converter.fillDataPrefix(mac, 6, "0");
                    futureKey.setDeviceId(mac);
                    futureKey.setDeviceType("A");
                    sessionFuture = (MSG_0x2001) msgFutureManager.get(futureKey);
                    if (null != sessionFuture) {

                        msgFutureManager.remove(futureKey);
                        //  json = JSON.toJSONString(sessionFuture);
                        break;
                        //处理data


                    }


                }

                if (null != sessionFuture) {
                    String jsonStr = JSON.toJSONString(sessionFuture);
                    JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                    String jsonObject1 = (String) jsonObject.get("data");

                    map.put("data", jsonObject1);
                    return ReturnUtil.Success("加载成功", map, null);
                }
            } else {
                return ReturnUtil.Error("客户端未能建立连接，可能原因：1.配置ID不对；2.客户端启动失败！", null, null);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ReturnUtil.Error("服务器异常！！请联系管理员！！", null, null);
        }
        finally {
           
        }
        return map;
    }


   /* private ViewBO setViewBO(ViewBO viewBO, JSONObject entry) {
        JSONObject object = entry;
        String id = (String) object.get("id");
        String value = (String) object.get("value");
        if (id.equalsIgnoreCase("发电态1")) {
            value="1";
            viewBO.setState1(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态2")) {

            viewBO.setState2(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态3")) {

            viewBO.setState3(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态4")) {

            viewBO.setState4(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态5")) {

            viewBO.setState5(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态6")) {

            viewBO.setState6(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态7")) {

            viewBO.setState7(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("发电态8")) {

            viewBO.setState8(Integer.valueOf((String) value));
        } else if (id.equalsIgnoreCase("p1")) {
            value="2323.3";
            viewBO.setValidPower1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q1")) {

            viewBO.setIdlePower1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流1")) {
            value = "23.22";
            viewBO.setCurrent1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB1")) {

            viewBO.setVoltage1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F1")) {

            viewBO.setFreq1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("电度1")) {

            viewBO.setPPower1(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位1")) {

            viewBO.setWater1(Double.valueOf((String) value));
        }
        //
        if (id.equalsIgnoreCase("p2")) {

            viewBO.setValidPower2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q2")) {

            viewBO.setIdlePower2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流2")) {

            viewBO.setCurrent2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB2")) {

            viewBO.setVoltage2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F2")) {

            viewBO.setFreq2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("电度2")) {

            viewBO.setPPower2(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位2")) {

            viewBO.setWater2(Double.valueOf((String) value));
        }
        ///机组3
        if (id.equalsIgnoreCase("p3")) {

            viewBO.setValidPower3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q3")) {

            viewBO.setIdlePower3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流3")) {

            viewBO.setCurrent3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB3")) {

            viewBO.setVoltage3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F3")) {

            viewBO.setFreq3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP3")) {

            viewBO.setPPower3(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位3")) {

            viewBO.setWater3(Double.valueOf((String) value));
        }
        //机组4
        if (id.equalsIgnoreCase("p4")) {

            viewBO.setValidPower4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q4")) {

            viewBO.setIdlePower4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流4")) {

            viewBO.setCurrent4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB4")) {

            viewBO.setVoltage4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F4")) {

            viewBO.setFreq4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP4")) {

            viewBO.setPPower4(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位4")) {

            viewBO.setWater4(Double.valueOf((String) value));
        }
        //机组5

        if (id.equalsIgnoreCase("p5")) {

            viewBO.setValidPower5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q5")) {

            viewBO.setIdlePower5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流5")) {

            viewBO.setCurrent5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB5")) {

            viewBO.setVoltage5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F5")) {

            viewBO.setFreq5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP5")) {

            viewBO.setPPower5(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位5")) {

            viewBO.setWater5(Double.valueOf((String) value));
        }
        //机组6
        if (id.equalsIgnoreCase("p6")) {

            viewBO.setValidPower6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q6")) {

            viewBO.setIdlePower6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流6")) {

            viewBO.setCurrent6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB6")) {

            viewBO.setVoltage6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F6")) {

            viewBO.setFreq6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP6")) {

            viewBO.setPPower6(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位6")) {

            viewBO.setWater6(Double.valueOf((String) value));
        }
        //机组7

        if (id.equalsIgnoreCase("p7")) {

            viewBO.setValidPower7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q7")) {

            viewBO.setIdlePower7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流7")) {

            viewBO.setCurrent7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB7")) {

            viewBO.setVoltage7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F7")) {

            viewBO.setFreq7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP7")) {

            viewBO.setPPower7(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位7")) {

            viewBO.setWater7(Double.valueOf((String) value));
        }
        //机组8
        if (id.equalsIgnoreCase("p8")) {

            viewBO.setValidPower8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("q8")) {

            viewBO.setIdlePower8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("A相电流8")) {

            viewBO.setCurrent8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("UAB8")) {

            viewBO.setVoltage8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("F8")) {

            viewBO.setFreq8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("EP8")) {

            viewBO.setPPower8(Double.valueOf((String) value));
        } else if (id.equalsIgnoreCase("前池水位8")) {

            viewBO.setWater8(Double.valueOf((String) value));
        }
        return viewBO;
    }*/

    @RequiresPermissions("device:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Member member) {
        ModelMap map = new ModelMap();
        List<Member> Lists = memberService.getPageList(member);
        map.put("pageInfo", new PageInfo<Member>(Lists));
        map.put("queryParam", member);
        return ReturnUtil.Success("加载成功", map, null);
    }

}
