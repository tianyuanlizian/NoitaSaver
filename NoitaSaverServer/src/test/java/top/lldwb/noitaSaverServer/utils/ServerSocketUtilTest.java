package top.lldwb.noitaSaverServer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import top.lldwb.noitaSaver.fileUtil.entity.Folder;
import top.lldwb.noitaSaverServer.service.UserBackupPathService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketUtilTest {
    @Test
    public void file() throws IOException {
        ServerSocketUtil socketUtil = ServerSocketUtil.getServerSocketUtils();

//        // 设置服务器监听端口
//        ServerSocket s = new ServerSocket(8888);
//        Socket socket = s.accept();
//        InputStream inputStream = socket.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
//        DataInputStream dataInputStream = new DataInputStream(inputStream);
//        FileOutputStream fileOutputStream = new FileOutputStream("G:\\test\\1.zip");
//
//        byte[] bytes = new byte[1024];
//        int length;
//        int fileLength = dataInputStream.readInt();
//
//        while (fileLength != 0) {
//            length = inputStream.read(bytes, 0, fileLength < bytes.length ? fileLength : bytes.length);
//            fileLength -= length;
//            fileOutputStream.write(bytes, 0, length);
//            fileOutputStream.flush();
//        }
//
//        OutputStream outputStream = socket.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
//
//        writer.write("成功\n");
//        writer.flush();
//
//        System.out.println(reader.readLine());
    }

    @Test
    public void files() throws IOException {
//        new File("C:\\Users\\Public\\Documents\\NoitaSaverClient\\32.zip").delete();

        System.out.println("C:\\Users\\Public\\Documents\\NoitaSaver\\Server\\UserBackupPath.lldwb".replaceAll("\\\\[^\\\\.]*\\.[^\\\\.]*$", ""));
        System.out.println("C:\\Users\\Public\\Documents\\NoitaSaver\\Server\\UserBackupPath.lldwb".replaceAll("\\\\[^\\\\]*$", ""));
//        new UserBackupPathService().setUserBackupPath(new Folder("G:\\用户备份"));
    }
}
