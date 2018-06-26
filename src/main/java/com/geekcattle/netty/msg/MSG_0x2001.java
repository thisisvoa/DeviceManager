package com.geekcattle.netty.msg;

import org.slf4j.Logger;

import com.geekcattle.utils.soket.msg.Converter;
import com.geekcattle.utils.utils.LogUtil;

import java.nio.ByteBuffer;


/**
 * 
 * ClassName: MSG_0x2001 
 * Reason: 位置消息 
 * date: 2015年5月11日 下午4:25:01 
 *
 * @author geekcattle
 */
public class MSG_0x2001 extends AbsMsg {
	
	Logger logger = LogUtil.getInstance().getLogger(MSG_0x2001.class);

	private static final long serialVersionUID = 1L;
	
	private String data;



	@Override
	protected int getMsgID() {
		return MessageID.MSG_0x2001;
	}

	@Override
	protected int getBodylen() {

		byte[] bytes = data.getBytes();
		int length = bytes.length;

		return length;
	}

	@Override
	protected byte[] bodytoBytes() {
		byte[] data = new byte[getBodylen()];
		try {
			System.arraycopy(Converter.getBytes(this.data), 0, data, 0,getBodylen());
		} catch (Exception e) {
			logger.error("位置消息toBytes转换异常",e);
			e.printStackTrace();
		}
		return data;
	}

	@Override
	protected boolean bodyfrombytes(byte[] data) {
		boolean resultState = false;
		int offset = 0;
		try {
			byte[] decode = decode(data);
			this.data = Converter.toGBKString(decode, offset, data.length);


			resultState = true;
		} catch (Exception e) {
			LogUtil.getInstance().getLogger(MSG_0x2001.class).error("位置消息fromBytes转换异常",e);
			e.printStackTrace();
		}
		return resultState;
	}
	private byte[] decode(byte[] b) {
		ByteBuffer buffer = ByteBuffer.allocate(5 * 1024 * 1024);
		ByteBuffer buffer1 = ByteBuffer.wrap(b);
		buffer.position(0);
		while (buffer1.remaining() > 0) {

			byte d = buffer1.get();
			if (d == 0x5a) {
				if(buffer1.remaining()>0){
					byte e = buffer1.get();
					if (e == 0x02)
						buffer.put((byte) 0x5a);
					else if (e == 0x01)
						buffer.put((byte) 0x5b);
				}
				else{
					System.out.println("0x5d remaining!");
				}
			} else if (d == 0x5e) {
				if(buffer1.remaining()>0){
					byte e = buffer1.get();
					if (e == 0x02)
						buffer.put((byte) 0x5e);
					else if (e == 0x01)
						buffer.put((byte) 0x5d);
				}
				else{
					System.out.println("无buffer");
				}
			} else {
				buffer.put(d);
			}
		}

		byte[] result = new byte[buffer.position()];
		buffer.position(0);
		buffer.get(result);
		return result;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MSG_0x2001{" +
				"data='" + data + '\'' +
				'}';
	}
}
