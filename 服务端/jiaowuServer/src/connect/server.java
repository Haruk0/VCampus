
package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
/**
* ���ô򿪷������˿ڣ�ͬʱ����������ݿ���ʣ�������ʱ�ر����ݿ⡣
* @author �����,л�ٲ�,������,���﷫.
* @since JDK1.7.
*/
public class server {
    private static Connection connection;//���Ӷ���
    static CourseDAO CD=new CourseDAO();
    static StudentDAO SD=new StudentDAO();
    static TeacherDAO TD=new TeacherDAO();
    public static void main(String[] args)
    {
        int port=8000;
        String host ="localhost";
        
        /*CourseDAO CD=new CourseDAO();*/
        CD.openDb();
        SD.openDb();
        try (ServerSocket server = new ServerSocket( port )){
            while (true) {
                Socket socket = server.accept();
                HandleMessageRunnable handleMessageRunnable =new HandleMessageRunnable( socket,CD );
                new Thread(handleMessageRunnable).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           // CD.closeDb();
           // SD.closeDb();
            System.out.println("�Ͽ����ݿ����ӣ�");
        }
    }



    public static Connection getConnection() {
        return connection;
    }
}