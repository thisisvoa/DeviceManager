package com.geekcattle.model.console;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * 页面展示
 * Created by geekcattle
 */
public class ViewBO {


    //水位
    @JSONField(name="Water")
    private Double Water;
    //有功
    @JSONField(name="ValidPower")
    private Double ValidPower;

    //无功
    @JSONField(name="IdlePower")
    private Double IdlePower;
    //-------------------------------------------------机组1 --start------------------------------------------------------//

    /**
     * 发电态1  机组1  （发电1  停机 0 ）
     */
    @JSONField(name="State1")
    private Boolean  State1;
    /**
     * 发电态2  机组2  （发电1  停机 0 ）
     */
    @JSONField(name="State2")
    private Boolean  State2;
    /**
     * 发电态3  机组3  （发电1  停机 0 ）
     */
    @JSONField(name="State3")
    private Boolean  State3;
    /**
     * 发电态4  机组4  （发电1  停机 0 ）
     */
    @JSONField(name="State4")
    private Boolean  State4;
    /**
     * 发电态5  机组5  （发电1  停机 0 ）
     */
    @JSONField(name="State5")
    private Boolean  State5;
    /**
     * 发电态6  机组6  （发电1  停机 0 ）
     */
    @JSONField(name="State6")
    private Boolean  State6;
    /**
     * 发电态7  机组7  （发电1  停机 0 ）
     */
    @JSONField(name="State7")
    private Boolean  State7;
    /**
     * 发电态8  机组8  （发电1  停机 0 ）
     */
    @JSONField(name="State8")
    private Boolean  State8;

    /**
     *机组1有功
     */
    @JSONField(name="ValidPower1")
    private Double  ValidPower1;
    //机组1有功
    @JSONField(name="IdlePower1")
    private Double  IdlePower1;
    //机组1电流
    @JSONField(name="Current1")
    private Double  Current1;
    //机组1电压
    @JSONField(name="Voltage1")
    private Double  Voltage1;
    //机组1频率
    @JSONField(name="Freq1")
    private Double  Freq1;
    //机组1电度
    @JSONField(name="PPower1")
    private Double  PPower1;
    //机组1水位
    @JSONField(name="Water1")
    private Double  Water1;

//-------------------------------------------------机组1-end-------------------------------------------------------//

    //-------------------------------------------------机组2 --start------------------------------------------------------//
    //机组2有功
    @JSONField(name="ValidPower2")
    private Double  ValidPower2;
    //机组2有功
    @JSONField(name="IdlePower2")
    private Double  IdlePower2;
    //机组2电流
    @JSONField(name="Current2")
    private Double  Current2;
    //机组2电压
    @JSONField(name="Voltage2")
    private Double  Voltage2;
    //机组2频率
    @JSONField(name="Freq2")
    private Double  Freq2;
    //机组2电度
    @JSONField(name="PPower2")
    private Double  PPower2;
    //机组2水位
    @JSONField(name="Water2")
    private Double  Water2;
    //-------------------------------------------------机组2-end-------------------------------------------------------//
//-------------------------------------------------机组3 --start------------------------------------------------------//
//机组3有功
    @JSONField(name="ValidPower3")
    private Double  ValidPower3;
    //机组3有功
    @JSONField(name="IdlePower3")
    private Double  IdlePower3;
    //机组3电流
    @JSONField(name="Current3")
    private Double  Current3;
    //机组3电压
    @JSONField(name="Voltage3")
    private Double  Voltage3;
    //机组3频率
    @JSONField(name="Freq3")
    private Double  Freq3;
    //机组3电度
    @JSONField(name="PPower3")
    private Double  PPower3;
    //机组3水位
    @JSONField(name="Water3")
    private Double  Water3;
    //-------------------------------------------------机组3-end-------------------------------------------------------//
//-------------------------------------------------机组4 --start------------------------------------------------------//
//机组4有功
    @JSONField(name="ValidPower4")
    private Double  ValidPower4;
    //机组4有功
    @JSONField(name="IdlePower4")
    private Double  IdlePower4;
    //机组4电流
    @JSONField(name="Current4")
    private Double  Current4;
    //机组4电压
    @JSONField(name="Voltage4")
    private Double  Voltage4;
    //机组4频率
    @JSONField(name="Freq4")
    private Double  Freq4;
    //机组4电度
    @JSONField(name="PPower4")
    private Double  PPower4;
    //机组4水位
    @JSONField(name="Water4")
    private Double  Water4;
    //-------------------------------------------------机组4-end-------------------------------------------------------//
//-------------------------------------------------机组5 --start------------------------------------------------------//
//机组5有功
    @JSONField(name="ValidPower5")
    private Double  ValidPower5;
    //机组5有功
    @JSONField(name="IdlePower5")
    private Double  IdlePower5;
    //机组5电流
    @JSONField(name="Current5")
    private Double  Current5;
    //机组5电压
    @JSONField(name="Voltage5")
    private Double  Voltage5;
    //机组5频率
    @JSONField(name="Freq5")
    private Double  Freq5;
    //机组5电度
    @JSONField(name="PPower5")
    private Double  PPower5;
    //机组5水位
    @JSONField(name="Water5")
    private Double  Water5;
    //-------------------------------------------------机组5-end-------------------------------------------------------//
//-------------------------------------------------机组6 --start------------------------------------------------------//
//机组6有功
    @JSONField(name="ValidPower6")
    private Double  ValidPower6;
    //机组6有功
    @JSONField(name="IdlePower6")
    private Double  IdlePower6;
    //机组6电流
    @JSONField(name="Current6")
    private Double  Current6;
    //机组6电压
    @JSONField(name="Voltage6")
    private Double  Voltage6;
    //机组6频率
    @JSONField(name="Freq6")
    private Double  Freq6;
    //机组6电度
    @JSONField(name="PPower6")
    private Double  PPower6;
    //机组6水位
    @JSONField(name="Water6")
    private Double  Water6;
    //-------------------------------------------------机组6-end-------------------------------------------------------//
//-------------------------------------------------机组7 --start------------------------------------------------------//
//机组7有功
    @JSONField(name="ValidPower7")
    private Double  ValidPower7;
    //机组7有功
    @JSONField(name="IdlePower7")
    private Double  IdlePower7;
    //机组7电流
    @JSONField(name="Current7")
    private Double  Current7;
    //机组7电压
    @JSONField(name="Voltage7")
    private Double  Voltage7;
    //机组7频率
    @JSONField(name="Freq7")
    private Double  Freq7;
    //机组7电度
    @JSONField(name="PPower7")
    private Double  PPower7;
    //机组7水位
    @JSONField(name="Water7")
    private Double  Water7;
//-------------------------------------------------机组7-end-------------------------------------------------------//

    //-------------------------------------------------机组8 --start------------------------------------------------------//
    //机组8有功
    @JSONField(name="ValidPower8")
    private Double  ValidPower8;
    //机组8有功
    @JSONField(name="IdlePower8")
    private Double  IdlePower8;
    //机组8电流
    @JSONField(name="Current8")
    private Double  Current8;
    //机组8电压
    @JSONField(name="Voltage8")
    private Double  Voltage8;
    //机组8频率
    @JSONField(name="Freq8")
    private Double  Freq8;
    //机组8电度
    @JSONField(name="PPower8")
    private Double  PPower8;
    //机组8水位
    @JSONField(name="Water8")
    private Double  Water8;
//-------------------------------------------------机组8-end-------------------------------------------------------//

    @JSONField(name="Water1")
    public Double getWater1() {
        return Water1;
    }

    public void setWater1(Double water1) {
        Water1 = water1;
    }
    @JSONField(name="ValidPower2")
    public Double getValidPower2() {
        return ValidPower2;
    }

    public void setValidPower2(Double validPower2) {
        ValidPower2 = validPower2;
    }

    @JSONField(name="IdlePower2")
    public Double getIdlePower2() {
        return IdlePower2;
    }

    public void setIdlePower2(Double idlePower2) {
        IdlePower2 = idlePower2;
    }

    @JSONField(name="Current2")
    public Double getCurrent2() {
        return Current2;
    }

    public void setCurrent2(Double current2) {
        Current2 = current2;
    }
    @JSONField(name="Voltage2")
    public Double getVoltage2() {
        return Voltage2;
    }

    public void setVoltage2(Double voltage2) {
        Voltage2 = voltage2;
    }

    @JSONField(name="Freq2")
    public Double getFreq2() {
        return Freq2;
    }

    public void setFreq2(Double freq2) {
        Freq2 = freq2;
    }

    @JSONField(name="PPower2")
    public Double getPPower2() {
        return PPower2;
    }

    public void setPPower2(Double PPower2) {
        this.PPower2 = PPower2;
    }

    @JSONField(name="Water2")
    public Double getWater2() {
        return Water2;
    }

    public void setWater2(Double water2) {
        Water2 = water2;
    }

    @JSONField(name="ValidPower3")
    public Double getValidPower3() {
        return ValidPower3;
    }

    public void setValidPower3(Double validPower3) {
        ValidPower3 = validPower3;
    }

    @JSONField(name="IdlePower3")
    public Double getIdlePower3() {
        return IdlePower3;
    }

    public void setIdlePower3(Double idlePower3) {
        IdlePower3 = idlePower3;
    }

    @JSONField(name="Current3")
    public Double getCurrent3() {
        return Current3;
    }

    public void setCurrent3(Double current3) {
        Current3 = current3;
    }
    @JSONField(name="Voltage3")
    public Double getVoltage3() {
        return Voltage3;
    }

    public void setVoltage3(Double voltage3) {
        Voltage3 = voltage3;
    }

    @JSONField(name="Freq3")
    public Double getFreq3() {
        return Freq3;
    }

    public void setFreq3(Double freq3) {
        Freq3 = freq3;
    }

    @JSONField(name="PPower3")
    public Double getPPower3() {
        return PPower3;
    }

    public void setPPower3(Double PPower3) {
        this.PPower3 = PPower3;
    }
    @JSONField(name="Water3")
    public Double getWater3() {
        return Water3;
    }

    public void setWater3(Double water3) {
        Water3 = water3;
    }
    @JSONField(name="ValidPower4")
    public Double getValidPower4() {
        return ValidPower4;
    }

    public void setValidPower4(Double validPower4) {
        ValidPower4 = validPower4;
    }
    @JSONField(name="IdlePower4")
    public Double getIdlePower4() {
        return IdlePower4;
    }

    public void setIdlePower4(Double idlePower4) {
        IdlePower4 = idlePower4;
    }
    @JSONField(name="Current4")
    public Double getCurrent4() {
        return Current4;
    }

    public void setCurrent4(Double current4) {
        Current4 = current4;
    }
    @JSONField(name="Voltage4")
    public Double getVoltage4() {
        return Voltage4;
    }

    public void setVoltage4(Double voltage4) {
        Voltage4 = voltage4;
    }
    @JSONField(name="Freq4")
    public Double getFreq4() {
        return Freq4;
    }

    public void setFreq4(Double freq4) {
        Freq4 = freq4;
    }
    @JSONField(name="PPower4")
    public Double getPPower4() {
        return PPower4;
    }

    public void setPPower4(Double PPower4) {
        this.PPower4 = PPower4;
    }
    @JSONField(name="Water4")
    public Double getWater4() {
        return Water4;
    }

    public void setWater4(Double water4) {
        Water4 = water4;
    }
    @JSONField(name="ValidPower5")
    public Double getValidPower5() {
        return ValidPower5;
    }

    public void setValidPower5(Double validPower5) {
        ValidPower5 = validPower5;
    }
    @JSONField(name="IdlePower5")
    public Double getIdlePower5() {
        return IdlePower5;
    }

    public void setIdlePower5(Double idlePower5) {
        IdlePower5 = idlePower5;
    }
    @JSONField(name="Current5")
    public Double getCurrent5() {
        return Current5;
    }

    public void setCurrent5(Double current5) {
        Current5 = current5;
    }
    @JSONField(name="Voltage5")
    public Double getVoltage5() {
        return Voltage5;
    }

    public void setVoltage5(Double voltage5) {
        Voltage5 = voltage5;
    }

    @JSONField(name="Freq5")
    public Double getFreq5() {
        return Freq5;
    }

    public void setFreq5(Double freq5) {
        Freq5 = freq5;
    }
    @JSONField(name="PPower5")
    public Double getPPower5() {
        return PPower5;
    }

    public void setPPower5(Double PPower5) {
        this.PPower5 = PPower5;
    }
    @JSONField(name="Water5")
    public Double getWater5() {
        return Water5;
    }

    public void setWater5(Double water5) {
        Water5 = water5;
    }

    @JSONField(name="ValidPower6")
    public Double getValidPower6() {
        return ValidPower6;
    }

    public void setValidPower6(Double validPower6) {
        ValidPower6 = validPower6;
    }
    @JSONField(name="IdlePower6")
    public Double getIdlePower6() {
        return IdlePower6;
    }

    public void setIdlePower6(Double idlePower6) {
        IdlePower6 = idlePower6;
    }
    @JSONField(name="Current6")
    public Double getCurrent6() {
        return Current6;
    }

    public void setCurrent6(Double current6) {
        Current6 = current6;
    }
    @JSONField(name="Voltage6")
    public Double getVoltage6() {
        return Voltage6;
    }

    public void setVoltage6(Double voltage6) {
        Voltage6 = voltage6;
    }
    @JSONField(name="Freq6")
    public Double getFreq6() {
        return Freq6;
    }

    public void setFreq6(Double freq6) {
        Freq6 = freq6;
    }
    @JSONField(name="PPower6")
    public Double getPPower6() {
        return PPower6;
    }

    public void setPPower6(Double PPower6) {
        this.PPower6 = PPower6;
    }
    @JSONField(name="Water6")
    public Double getWater6() {
        return Water6;
    }

    public void setWater6(Double water6) {
        Water6 = water6;
    }
    @JSONField(name="ValidPower7")
    public Double getValidPower7() {
        return ValidPower7;
    }

    public void setValidPower7(Double validPower7) {
        ValidPower7 = validPower7;
    }
    @JSONField(name="IdlePower7")
    public Double getIdlePower7() {
        return IdlePower7;
    }

    public void setIdlePower7(Double idlePower7) {
        IdlePower7 = idlePower7;
    }
    @JSONField(name="Current7")
    public Double getCurrent7() {
        return Current7;
    }

    public void setCurrent7(Double current7) {
        Current7 = current7;
    }
    @JSONField(name="Voltage7")
    public Double getVoltage7() {
        return Voltage7;
    }

    public void setVoltage7(Double voltage7) {
        Voltage7 = voltage7;
    }
    @JSONField(name="Freq7")
    public Double getFreq7() {
        return Freq7;
    }

    public void setFreq7(Double freq7) {
        Freq7 = freq7;
    }
    @JSONField(name="PPower7")
    public Double getPPower7() {
        return PPower7;
    }

    public void setPPower7(Double PPower7) {
        this.PPower7 = PPower7;
    }
    @JSONField(name="Water7")
    public Double getWater7() {
        return Water7;
    }

    public void setWater7(Double water7) {
        Water7 = water7;
    }

    @JSONField(name="ValidPower8")
    public Double getValidPower8() {
        return ValidPower8;
    }

    public void setValidPower8(Double validPower8) {
        ValidPower8 = validPower8;
    }
    @JSONField(name="IdlePower8")
    public Double getIdlePower8() {
        return IdlePower8;
    }

    public void setIdlePower8(Double idlePower8) {
        IdlePower8 = idlePower8;
    }
    @JSONField(name="Current8")
    public Double getCurrent8() {
        return Current8;
    }

    public void setCurrent8(Double current8) {
        Current8 = current8;
    }
    @JSONField(name="Voltage8")
    public Double getVoltage8() {
        return Voltage8;
    }

    public void setVoltage8(Double voltage8) {
        Voltage8 = voltage8;
    }
    @JSONField(name="Freq8")
    public Double getFreq8() {
        return Freq8;
    }

    public void setFreq8(Double freq8) {
        Freq8 = freq8;
    }
    @JSONField(name="PPower8")
    public Double getPPower8() {
        return PPower8;
    }

    public void setPPower8(Double PPower8) {
        this.PPower8 = PPower8;
    }
    @JSONField(name="Water8")
    public Double getWater8() {
        return Water8;
    }

    public void setWater8(Double water8) {
        Water8 = water8;
    }
    @JSONField(name="ValidPower")
    public Double getValidPower() {
        Double _ValidPower=0.0;
        Double validPower1 = this.getValidPower1();
        Double validPower2 = this.getValidPower2();
        Double validPower3 = this.getValidPower3();
        Double validPower4 = this.getValidPower4();
        Double validPower5 = this.getValidPower5();
        Double validPower6 = this.getValidPower6();
        Double validPower7 = this.getValidPower7();
        Double validPower8 = this.getValidPower8();
        if(null != validPower1){
            _ValidPower+=validPower1;
        }
        if(null != validPower2){
            _ValidPower+=validPower2;
        }
        if(null != validPower3){
            _ValidPower+=validPower3;
        }
        if(null != validPower4){
            _ValidPower+=validPower4;
        }
        if(null != validPower5){
            _ValidPower+=validPower5;
        }
        if(null != validPower6){
            _ValidPower+=validPower6;
        }
        if(null != validPower7){
            _ValidPower+=validPower7;
        }
        if(null != validPower8){
            _ValidPower+=validPower8;
        }
        this.ValidPower=_ValidPower;
        return _ValidPower;
    }

    public void setValidPower(Double validPower) {
        ValidPower = validPower;
    }

    @JSONField(name="IdlePower")
    public Double getIdlePower() {
        Double _IdlePower=0.0;
        Double IdlePower1 = this.getIdlePower1();
        Double IdlePower2 = this.getIdlePower2();
        Double IdlePower3 = this.getIdlePower3();
        Double IdlePower4 = this.getIdlePower4();
        Double IdlePower5 = this.getIdlePower5();
        Double IdlePower6 = this.getIdlePower6();
        Double IdlePower7 = this.getIdlePower7();
        Double IdlePower8 = this.getIdlePower8();
        if(null != IdlePower1){
            _IdlePower+=IdlePower1;
        }
        if(null != IdlePower2){
            _IdlePower+=IdlePower2;
        }
        if(null != IdlePower3){
            _IdlePower+=IdlePower3;
        }
        if(null != IdlePower4){
            _IdlePower+=IdlePower4;
        }
        if(null != IdlePower5){
            _IdlePower+=IdlePower5;
        }
        if(null != IdlePower6){
            _IdlePower+=IdlePower6;
        }
        if(null != IdlePower7){
            _IdlePower+=IdlePower7;
        }
        if(null != IdlePower8){
            _IdlePower+=IdlePower8;
        }
        this.IdlePower=_IdlePower;
        return _IdlePower;

    }

    public void setIdlePower(Double idlePower) {
        IdlePower = idlePower;
    }
    @JSONField(name="ValidPower1")
    public Double getValidPower1() {
        return ValidPower1;
    }

    public void setValidPower1(Double validPower1) {
        ValidPower1 = validPower1;
    }

    @JSONField(name="IdlePower1")
    public Double getIdlePower1() {
        return IdlePower1;
    }

    public void setIdlePower1(Double idlePower1) {
        IdlePower1 = idlePower1;
    }

    @JSONField(name="Current1")
    public Double getCurrent1() {
        return Current1;
    }

    public void setCurrent1(Double current1) {
        Current1 = current1;
    }

    @JSONField(name="Voltage1")
    public Double getVoltage1() {
        return Voltage1;
    }

    public void setVoltage1(Double voltage1) {
        Voltage1 = voltage1;
    }
    @JSONField(name="Freq1")
    public Double getFreq1() {
        return Freq1;
    }

    public void setFreq1(Double freq1) {
        Freq1 = freq1;
    }

    @JSONField(name="PPower1")
    public Double getPPower1() {
        return PPower1;
    }

    public void setPPower1(Double PPower1) {
        this.PPower1 = PPower1;
    }

    @JSONField(name="Water")
    public Double getWater() {
        return this.Water1;
    }

    public void setWater(Double water) {
        Water = water;
    }
    @JSONField(name="State1")
    public Boolean getState1() {
        return State1;
    }

    public void setState1(Boolean state1) {
        State1 = state1;
    }
    @JSONField(name="State2")
    public Boolean getState2() {
        return State2;
    }

    public void setState2(Boolean state2) {
        State2 = state2;
    }
    @JSONField(name="State3")
    public Boolean getState3() {
        return State3;
    }

    public void setState3(Boolean state3) {
        State3 = state3;
    }
    @JSONField(name="State4")
    public Boolean getState4() {
        return State4;
    }

    public void setState4(Boolean state4) {
        State4 = state4;
    }
    @JSONField(name="State5")
    public Boolean getState5() {
        return State5;
    }

    public void setState5(Boolean state5) {
        State5 = state5;
    }
    @JSONField(name="State6")
    public Boolean getState6() {
        return State6;
    }

    public void setState6(Boolean state6) {
        State6 = state6;
    }
    @JSONField(name="State7")
    public Boolean getState7() {
        return State7;
    }

    public void setState7(Boolean state7) {
        State7 = state7;
    }
    @JSONField(name="State8")
    public Boolean getState8() {
        return State8;
    }

    public void setState8(Boolean state8) {
        State8 = state8;
    }
}
