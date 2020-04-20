package connect;

import messageLei.Message;

import java.io.*;
import java.net.Socket;
/**
 * 通过端口抛出和接收message包.
 * 
 *
 */


public class connect {
	
    private final int port = 8000;           //server使用port
    private final String hostIp = "localhost";      //serverIp
    private boolean isConnected = false;
    static Socket socket=null;
    static ObjectOutputStream ob_os=null;
    static ObjectInputStream ob_is=null;
    /**
     * 构造函数.
     */
    public connect()
    {
    	try {
            socket = new Socket(hostIp, port);
    	}catch (IOException e) {
            isConnected = false;
        	System.out.println("连接错");            
            e.printStackTrace();
        }
/*    	try {
			ob_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			isConnected = false;
			System.out.println("获取返回错");    
			e.printStackTrace();
		}
		*/
    }
    /**
     * 接收Message包函数.
     * @param messageSend Message类对象.
     * @return Message 接收的Message.
     */

    public Message connectServer(Message messageSend) {
        Message ansMessage = new Message("初始化类型");
        try {
			ob_os = new ObjectOutputStream(socket.getOutputStream());
	        ob_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	        isConnected = true;
		} catch (IOException e1) {
			isConnected = false;
			System.out.println("创建流错");  
			e1.printStackTrace();
		}

        sendMessage(messageSend,ob_os);
        try {
        ansMessage = (Message) ob_is.readObject();
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("readobject错");
        }finally{
		try{
			socket.close();
		   }catch(Exception e){
			e.printStackTrace();
		}
	}return ansMessage;
    }
    
    
    /**
     * 抛包函数
     * @param message Message类对象.
     * @param ob_os 输出流对象.
     */

    public  void sendMessage(Message message,ObjectOutputStream ob_os){
        if (!isConnected) {
            System.out.println("连接建立失败,不能发送消息！");
            return;
        }
        if (message == null) {
            System.out.println("消息不能为空！");
            return;
        }
        try {
            ob_os.writeObject(message);
            ob_os.flush();
        } catch (IOException e) {
            System.out.print("建立输出流失败！\n");
        }
    }

    public int getPort() {
        return port;
    }
    public String getHostIp() {
        return hostIp;
    }
    public boolean isConnected(){return isConnected;}
}
