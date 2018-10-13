package connect;

import messageLei.Message;

import java.io.*;
import java.net.Socket;
/**
 * ͨ���˿��׳��ͽ���message��.
 * 
 *
 */


public class connect {
	
    private final int port = 8000;           //serverʹ��port
    private final String hostIp = "localhost";      //serverIp
    private boolean isConnected = false;
    static Socket socket=null;
    static ObjectOutputStream ob_os=null;
    static ObjectInputStream ob_is=null;
    /**
     * ���캯��.
     */
    public connect()
    {
    	try {
            socket = new Socket(hostIp, port);
    	}catch (IOException e) {
            isConnected = false;
        	System.out.println("���Ӵ�");            
            e.printStackTrace();
        }
/*    	try {
			ob_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			isConnected = false;
			System.out.println("��ȡ���ش�");    
			e.printStackTrace();
		}
		*/
    }
    /**
     * ����Message������.
     * @param messageSend Message�����.
     * @return Message ���յ�Message.
     */

    public Message connectServer(Message messageSend) {
        Message ansMessage = new Message("��ʼ������");
        try {
			ob_os = new ObjectOutputStream(socket.getOutputStream());
	        ob_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	        isConnected = true;
		} catch (IOException e1) {
			isConnected = false;
			System.out.println("��������");  
			e1.printStackTrace();
		}

        sendMessage(messageSend,ob_os);
        try {
        ansMessage = (Message) ob_is.readObject();
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("readobject��");
        }return ansMessage;
    }
    
    
    /**
     * �װ�����
     * @param message Message�����.
     * @param ob_os ���������.
     */

    public  void sendMessage(Message message,ObjectOutputStream ob_os){
        if (!isConnected) {
            System.out.println("���ӽ���ʧ��,���ܷ�����Ϣ��");
            return;
        }
        if (message == null) {
            System.out.println("��Ϣ����Ϊ�գ�");
            return;
        }
        try {
            ob_os.writeObject(message);
            ob_os.flush();
        } catch (IOException e) {
            System.out.print("���������ʧ�ܣ�\n");
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
