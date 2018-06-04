package com.geekcattle.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;

import com.geekcattle.netty.cache.MsgCache;
import com.geekcattle.netty.msg.AbsMsg;
import com.geekcattle.netty.server.thread.CheckClientThread;
import com.geekcattle.netty.server.thread.CheckTemClientThread;
import com.geekcattle.netty.server.thread.ParseMsgThreadManager;
import com.geekcattle.utils.soket.msg.Converter;
import com.geekcattle.utils.soket.msg.TCPCodec;
import com.geekcattle.utils.utils.LogUtil;
import com.geekcattle.utils.utils.PropertiesUtil;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 
 * ClassName: TCPServer 
 * date: 2015年1月29日 下午4:11:19 
 *
 * @author nifeng
 */
public class TCPServer extends AbstractServer  {

	public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("KNIGHT-QRCODE-TCP-CHANNELS", GlobalEventExecutor.INSTANCE);

	private static final Logger LOG = LoggerFactory.getLogger(TCPServer.class);

	private ServerBootstrap serverBootstrap;

	public static ChannelHandlerContext chtx;


	public TCPServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
		super(nettyConfig, channelInitializer);
	}

	private ChannelFuture cf;
	private Logger logger = LogUtil.getInstance().getLogger(TCPServer.class);

	@Override
	public TransmissionProtocol getTransmissionProtocol() {
		return TRANSMISSION_PROTOCOL.TCP;
	}

	@Override
	public void startServer() throws Exception {
		try {
			serverBootstrap = new ServerBootstrap();
			LOG.info("****** Start the CarLock TCP server, port {} ******", this.getNettyConfig().getPortNumber());
			if (getChannelInitializer() == null) {
				LOG.error("****** Start the CarLock TCP server failed, port {}. Please check the server config. ******", nettyConfig.getPortNumber());
				return;
			}

			Map<ChannelOption<?>, Object> chnOptions = nettyConfig.getChannelOptions();
			if (null != chnOptions && chnOptions.size() > 0) {
				Set<ChannelOption<?>> set = chnOptions.keySet();
				for (ChannelOption opt : set) {
					serverBootstrap.childOption(opt, chnOptions.get(opt));
				}
			}

			serverBootstrap.group(getBossGroup(), getWorkerGroup())
					.channel(NioServerSocketChannel.class)
					.childHandler(getChannelInitializer())
					.handler(new LoggingHandler(LogLevel.INFO));
			final ChannelFuture cf = serverBootstrap.bind(nettyConfig.getSocketAddress());
			cf.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (cf.isSuccess()) {
						ALL_CHANNELS.add(cf.channel());
						LOG.info("****** Start the CarLock TCP server successfully, port {} ******", nettyConfig.getPortNumber());
					} else {
						LOG.info("****** Start the CarLock TCP server failed, port {}. The cause is {} ******", nettyConfig.getPortNumber(), cf.cause());
					}
				}
			});
		} catch (Exception e) {

			LOG.error("Visitor TCP Server start error {}, going to shut down", e);
			stopServer();
			throw e;

		}
	}

	@Override
	public void stopServer() throws Exception {
		LOG.debug("In stopServer method of class: {}", this.getClass()
				.getName());
		ChannelGroupFuture future = ALL_CHANNELS.close();
		try {
			future.await();
		} catch (InterruptedException e) {
			LOG.error(
					"Execption occurred while waiting for channels to close: {}",
					e);
		} finally {
			// TODO move this part to spring.
			if (null != nettyConfig.getBossGroup()) {
				Future fb = nettyConfig.getBossGroup().shutdownGracefully(0, 1, TimeUnit.MILLISECONDS);
				try {
					fb.await();
				} catch (InterruptedException ignore) {
					LOG.error("Exception while waiting for tcpserver to complete shutdown  ", ignore);
				}
			}
			if (null != nettyConfig.getWorkerGroup()) {
				Future fw = nettyConfig.getWorkerGroup().shutdownGracefully(0, 1, TimeUnit.MILLISECONDS);
				try {
					fw.await();
				} catch (InterruptedException ignore) {
					LOG.error("Exception while waiting for proxy to tcpserver shutdown  ", ignore);
				}
			}
		}
	}


	@Override
	public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer) {
		this.channelInitializer = initializer;
		serverBootstrap.childHandler(initializer);


	}

	/*@Override
	public void run() {
		if (logger.isDebugEnabled()) {
			logger.debug("run() - start"); //$NON-NLS-1$
		}

		init();


		// 启动临时客户端管理(秒)
		CheckTemClientThread.getInstance().run(
				Integer.parseInt(PropertiesUtil.getProperties().getProperty(
						"tcp.temclientdelay")),
				Integer.parseInt(PropertiesUtil.getProperties().getProperty(
						"tcp.temclientdelay")));

		//启动客户端检查线程
		CheckClientThread.getInstance().run(
				Integer.parseInt(PropertiesUtil.getProperties().getProperty(
						"tcp.clientdelay")),
				Integer.parseInt(PropertiesUtil.getProperties().getProperty(
						"tcp.clientdelay")));
		
		// 启动消息处理
		ParseMsgThreadManager.getInstance().run(0,0);

		if (logger.isDebugEnabled()) {
			logger.debug("run() - end"); //$NON-NLS-1$
		}
	}*/

	/*private void init() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class) // (3)
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
								@Override
								public void initChannel(SocketChannel ch)
										throws Exception {
									 ch.pipeline().addLast(new TCPCodec(),new
									 TCPServerHandler(),new ReadTimeoutHandler(3000));
								}
							}).option(ChannelOption.SO_BACKLOG, 128) // (5)
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			// Bind and start to accept incoming connections.
			String serverport = PropertiesUtil.getProperties().getProperty("serverport");
			cf = b.bind(Integer.parseInt(serverport)).sync(); // (7)
			logger.info("TCP Server start    success   port："+serverport);
		} catch (InterruptedException e) {
			logger.error("run()", e); //$NON-NLS-1$
			e.printStackTrace();
		}catch (Exception ex){
			ex.printStackTrace();
		}

		finally {
			// workerGroup.shutdownGracefully();
			// bossGroup.shutdownGracefully();
		}
	}*/

	/**
	 * 
	 * closeConnect
	 *
	 * @author nifeng
	 */
	public void closeConnect() {
		try {
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * send:(发送消息).
	 *
	 * @author nifeng
	 * @param m
	 */
	public void send(AbsMsg m) {
		if (chtx != null && chtx.channel().isOpen()) {
			MsgCache.getInstance().put(m);
			chtx.channel().write(m);
			chtx.flush();
		}
	}

	/**
	 * 只发消息不缓存，用于心跳类消息
	 * 
	 * @param m
	 */
	public void sendWithoutCache(AbsMsg m) {
		logger.debug("CLINET发送WithoutCache："+Converter.bytes2HexsSpace(m.toBytes()));
		if (chtx != null && chtx.channel().isOpen()) {
			chtx.write(m);
			chtx.flush();
		}
	}

	public static ChannelHandlerContext getChtx() {
		return chtx;
	}

	public static void setChtx(ChannelHandlerContext chtx) {
		TCPServer.chtx = chtx;
	}

	public ChannelFuture getCf() {
		return cf;
	}

	public void setCf(ChannelFuture cf) {
		this.cf = cf;
	}
}
