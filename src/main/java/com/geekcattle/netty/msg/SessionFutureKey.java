package com.geekcattle.netty.msg;
/**
 * Created by Administrator on 2018/2/26/026.
 */
public class SessionFutureKey {
    private long seq;
    private String deviceId;
    private String deviceType;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public int hashCode() {
        SessionFutureKey name = (SessionFutureKey) this;
        System.out.println("Hash" + name.seq);
        int i = (int) (Long.valueOf(this.deviceId) + Long.valueOf(seq).hashCode());
        return i;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof SessionFutureKey)){
            return false;
        }
        final SessionFutureKey person = (SessionFutureKey)obj;
        if(!Long.valueOf(this.getDeviceId()).equals(Long.valueOf(person.getDeviceId()))){
            return false;
        }
        if(this.getSeq() != person.getSeq()){
            return false;
        }
        System.out.println("seq:------------"+person.getSeq());
        return true;

    }
}
