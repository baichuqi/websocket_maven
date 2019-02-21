package com.bai.websocket.client;

import com.bai.websocket.common.Message;
import com.bai.websocket.common.UserPool;
import com.bai.websocket.poxy.MessageProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/init")
public class BarrageClient {

	private static Logger LOG = LoggerFactory.getLogger(BarrageClient.class);

    @OnOpen
    public void onOpen(Session session) {
        //加入用户池
        UserPool.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        LOG.info("user [" + session.getId() + "] send: " + message);
        //给所有用户发送消息
        MessageProxy.getInstance().getProxy(Message.class).send(message, session);
//        MessageProxy.getInstance().getProxy(Message.class).sendto(message, session);
    //得到当前用户的session id，并且获得对方ID
    }

    @OnError
    public void onError(Throwable throwable) {
        LOG.error(throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        //移除用户池
        UserPool.remove(session.getId());

    }
	
}
