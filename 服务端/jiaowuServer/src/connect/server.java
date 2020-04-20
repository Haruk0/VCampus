
package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
/**
* 设置打开服务器端口，同时允许进行数据库访问，无请求时关闭数据库。
* @author 宋天昊,谢荣昌,潘振宇,周扬帆.
* @since JDK1.7.
*/
public class server {
    private static Connection connection;//连接对象
    static CourseDAO CD=new CourseDAO();
    public static void main(String[] args)
    {
        int port=8000;
        String host ="localhost";
        
        CD.openDb();
        try (ServerSocket server = new ServerSocket( port )){
            while (true) {
                Socket socket = server.accept();
                HandleMessageRunnable handleMessageRunnable =new HandleMessageRunnable( socket,CD );
                new Thread(handleMessageRunnable).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            	if (socket != null) {
				try {
					socket.close();// 关闭Socket
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            System.out.println("服务器关闭！");
        }
    }



    public static Connection getConnection() {
        return connection;
    }
}
