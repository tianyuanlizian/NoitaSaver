package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverClient.utils.User;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class GetUser {
    public static User getUser(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(), "select * from user where user_name=?", name);
    }
}
