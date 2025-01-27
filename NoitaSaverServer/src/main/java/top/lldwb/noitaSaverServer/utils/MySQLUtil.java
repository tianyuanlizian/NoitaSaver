package top.lldwb.noitaSaverServer.utils;

import top.lldwb.noitaSaver.DbUtil.Handler.BeanHandler;
import top.lldwb.noitaSaver.DbUtil.Handler.BeanListHandler;
import top.lldwb.noitaSaver.DbUtil.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLUtil {
    static {
        /*
         * 加载驱动程序
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败...");
            e.printStackTrace();
        }
    }


    private static final String URL = "jdbc:mysql://mysql.lldwb.top:33366/noitaSaver?serverTimezone=Asia/Shanghai";
    //private static final String URL = "jdbc:mysql://127.0.0.1:3306/noitaSaver?serverTimezone=Asia/Shanghai";
    //账号
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "@dwb123456";
    //private static final String PASSWORD = "123456";
    //QueryRunner对象
    QueryRunner queryRunner = new QueryRunner();
    //数据库连接对象并传入数据库参数
    Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

    public MySQLUtil() throws SQLException {
    }

    /**
     * 从数据库中查询符合条件的记录，并将结果封装为指定类型的List<T>对象
     *
     * @param t   用于封装结果的Java对象
     * @param sql SQL语句
     * @param obj SQL语句中的参数列表
     * @param <T> Java类型
     * @return 返回符合条件的记录封装为的List<T>对象
     * @throws SQLException
     */
    public <T> List<T> pdsList(T t, String sql, Object... obj) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> list = queryRunner.query(conn, sql, new BeanListHandler<T>((Class<? extends T>) t.getClass()), obj);
//        DbUtils.close(conn);
        return list;
//        return null;
    }

    /**
     * 从数据库中查询符合条件的记录，并将结果封装为指定类型的T对象
     *
     * @param t   用于封装结果的Java对象
     * @param sql SQL语句
     * @param obj SQL语句中的参数列表
     * @param <T> Java类型
     * @return 返回符合条件的记录封装为的T对象
     * @throws SQLException
     */
    public <T> T pdsT(T t, String sql, Object... obj) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return queryRunner.query(conn, sql, new BeanHandler<T>((Class<? extends T>) t.getClass()), obj);
    }

    public int update(String sql, Object... obj) throws SQLException {
        return queryRunner.update(conn,sql,obj);
    }
}
