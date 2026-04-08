package com.sky.websocket;

import java.util.Map;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Collection;


@Component
@ServerEndpoint("/ws/{sid}") 
public class WebSocketServer {
    //存放session
    private static Map<String, Session> sessionMap = new HashMap<>();
    //连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立连接");
        sessionMap.put(sid, session);
    }
    //收到客户端的消息后调用的方法
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("收到来自客户端：" + sid + "的消息：" + message);
    }
    //连接关闭调用的方法
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("连接断开：" + sid);
        sessionMap.remove(sid);
    }
    //使用时调用向所有建立连接的client发送消息
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
