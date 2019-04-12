package com.glinlf.growth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author glinlf
 * @date 2019/2/12
 * @remark
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {

    private final static Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 分布式统计需要使用中间件协助 redis
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    /**
     * websocket 连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           );
        addOnlineCount();
        LOG.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功!");
        } catch (IOException e) {
            LOG.info("websocket IO异常");
        }
    }

    /**
     * socket 关闭
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        LOG.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 接收来自客户端的消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("来自客户端的消息:" + message);
        //群发消息 房间聊天？
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void sendInfo(String message) throws IOException {
        LOG.info(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * socket消息发送
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private int getOnlineCount() {
        return onlineCount.get();
    }

    private int addOnlineCount() {
        return onlineCount.addAndGet(1);
    }

    private int subOnlineCount() {
        return onlineCount.addAndGet(-1);
    }

    /**
     * 测试服务器主动推送
     *
     * @throws ParseException
     */
//    @Scheduled(fixedDelay=60000)
    public void tryCreateBookCalendar() throws ParseException, IOException {

        sendInfo("每分钟消息推送！");
    }

}
