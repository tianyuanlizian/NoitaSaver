package top.lldwb.noitaSaverServer.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Socket服务端工具类,使用单例模式-懒汉式
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketUtils {
    /**
     * 存储客户端连接对象集合
     */
    private List<Socket> socketList = new ArrayList<>();

    /**
     * Socket服务端工具类对象
     */
    private static ServerSocketUtils serverSocketUtils;

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public static ServerSocketUtils getServerSocketUtils() {
        return getServerSocketUtils(8888);
    }

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public static ServerSocketUtils getServerSocketUtils(int port) {
        return getServerSocketUtils(8888, 50);
    }

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public synchronized static ServerSocketUtils getServerSocketUtils(int port, int backlog) {
        if (serverSocketUtils == null) {
            serverSocketUtils = new ServerSocketUtils(port, backlog);
        }
        return serverSocketUtils;
    }

    /**
     * 私有化构造器保证只有一个对象
     *
     * @param port    端口
     * @param backlog 连接数量
     */
    private ServerSocketUtils(int port, int backlog) {
        try {
            // 设置服务器配置:监听端口,连接次数
            ServerSocket serverSocket = new ServerSocket(port, backlog);
            System.out.println("启动服务器....");

            while (true) {
                // 侦听并接受到此套接字的连接。
                // 监听来自客户端的连接请求，当有客户端请求连接时，该方法会阻塞直到连接被接受
                // 当服务器端接受一个客户端的连接请求，并创建一个用于与该客户端通信的Socket对象
                Socket socket = serverSocket.accept();
                // 将Socket存入集合
                socketList.add(socket);
                // 创建一个新的线程处理与该客户端的通信
                new Thread(() -> ServerSocket(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 与客户端通信的Socket对象的方法，
     * 根据需求自定义修改
     * @param socket 与客户端通信的Socket对象
     */
    private void ServerSocket(Socket socket) {
        try {
            // s.getInetAddress().getLocalHost()
            // 返回客户端地址并打印出来
//        System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");

            // 创建一个缓冲输入流，用于读取来自客户端的数据，并将其转换为字符串
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 读取客户端发送来的消息
            String mess = null;
            mess = br.readLine();
            System.out.println("客户端：" + mess);

            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write(mess + "\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}