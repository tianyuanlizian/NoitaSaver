package top.lldwb.noitaSaver.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IO工具类，提供文件和文件夹的读写操作
 */
public class FileUtils {

    // 源文件夹地址
    public static String readPath;

    /**
     * 读取指定文件夹中的所有文件并写入目标文件夹
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     */
    public static void copyDirectory(String readPath, String writePath) {
        // 设置源文件夹地址
        FileUtils.readPath = readPath;
        // 获取源文件夹中所有文件的路径列表
        List<String> list = getFilePathList(readPath, writePath);

        List<Thread> threadList = new ArrayList<>();

        // 逐一将源文件夹中的文件写入目标文件夹
        for (final String path : list) {
            // 构造目标文件路径
            final String write = writePath + path.substring(readPath.length());

            // 输出目标文件路径
//            System.out.println(write);

            Thread thread = new Thread(() -> {
                try {
                    FileUtils.fileIO(path, write);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            // 使用多线程将源文件夹中的文件写入目标文件夹中
//            if (new File(path).length() >= 1024 * 1024) {
            threadList.add(thread);
//            }
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取指定文件夹中所有文件的路径列表
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     * @return 指定文件夹中所有文件的路径列表
     */
    public static List getFilePathList(String readPath, String writePath) {
        // 在目标文件夹中创建子文件夹
        new File(writePath + readPath.substring(FileUtils.readPath.length())).mkdir();
        // 初始化路径列表
        List<String> list = new ArrayList();
        // 遍历源文件夹中的所有文件
        for (String file : new File(readPath).list()) {
            // 构造当前文件的路径
            String paths = readPath + '\\' + file;
            // 如果当前文件是文件夹，则递归获取该文件夹中的所有文件
            if (new File(paths).isDirectory()) {
                list.addAll(getFilePathList(paths, writePath));
            } else {
                // 如果当前文件是普通文件，则将其路径添加到路径列表中
                list.add(paths);
            }
        }
        // 返回路径列表
        return list;
    }

    /**
     * 读取指定文件夹下的文件夹名称列表
     *
     * @param path 源文件夹地址
     * @return 返回文件夹名称列表
     */
    public static List getFolderNameList(String path) {
        // 初始化路径列表
        List<String> list = new ArrayList();
        // 遍历源文件夹中的所有文件
        for (String file : new File(path).list()) {
            // 构造当前文件的路径
            String paths = path + '\\' + file;
            // 判断是否是文件夹
            if (new File(paths).isDirectory()) {
                list.add(file);
//                System.out.println(file);
            }
        }
        // 返回路径列表
        return list;
    }

    /**
     * 将指定文件从源地址复制到目标地址
     *
     * @param readPath  源地址
     * @param writePath 目标地址
     * @throws IOException
     */
    public static void fileIO(String readPath, String writePath) throws IOException {
        // 输入文件操作对象
        File read = new File(readPath);
        // 输出文件操作对象
        File write = new File(writePath);

        // 输入文件流
        FileInputStream inputStream = new FileInputStream(read);
        // 输出文件流
        FileOutputStream outputStream = new FileOutputStream(write);

        // 返回指定的文件长度
        byte[] bytes = new byte[(int) read.length()];
        // 读取输入文件的数据到字节数组中
        if (inputStream.read(bytes) != -1) {
            // 将字节数组中的数据写入输出文件
            outputStream.write(bytes);
        }

        //关闭流对象
        inputStream.close();
        outputStream.close();
    }


    /**
     * 大文件本地多线程io操作
     *
     * @param readPath  源地址
     * @param writePath 目标地址
     * @throws IOException
     */
    public static void largeFileIO(String readPath, String writePath) {
        new Thread(() -> {
            try {
                // 输入文件操作对象
                File read = new File(readPath);
                // 输出文件操作对象
                File write = new File(writePath);

                // 输入文件流
                FileInputStream inputStream = new FileInputStream(read);
                // 输出文件流
                FileOutputStream outputStream = new FileOutputStream(write);

                // 返回指定的文件长度
                byte[] bytes = new byte[(int) read.length()];

                // 读取输入文件的数据到字节数组中
                if (inputStream.read(bytes) != -1) {
                    // 将字节数组中的数据写入输出文件
                    outputStream.write(bytes);
                }

                //关闭流对象
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


}