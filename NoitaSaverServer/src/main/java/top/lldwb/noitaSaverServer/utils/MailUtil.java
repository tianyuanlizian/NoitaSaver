package top.lldwb.noitaSaverServer.utils;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class MailUtil {

    /**
     * 邮件发送
     * 创建Session调用send发送
     *
     * @param reception 接收邮箱
     * @param subject   邮件标题
     * @param text      邮件正文
     */
    public static void sendSession(String reception, String subject, String text) {
        // 发送邮箱服务器
        String server = "mail.lldwb.top";
        // 发送邮箱
        String sendMail = "lldwb@lldwb.top";
        // 发送邮箱密码
        String sendPasswd = "LLdwb15979809462";
        sendSession(reception, subject, text, server, sendMail, sendPasswd);
    }

    /**
     * 邮件发送
     * 创建Session调用send发送
     *
     * @param reception  接收邮箱
     * @param subject    邮件标题
     * @param text       邮件正文
     * @param server     发送邮箱服务器
     * @param sendMail   发送邮箱
     * @param sendPasswd 发送邮箱密码
     */
    public static void sendSession(String reception, String subject, String text, String server, String sendMail, String sendPasswd) {
        // 邮箱传输协议，默认smtp
        String protocol = "smtp";
        // 服务器端口
        int port = 465;
        // STARTTLS 协议
        boolean starttls = true;

//        MailUtil.sendSession(reception, subject, text, server, sendMail, sendPasswd, protocol, port, starttls);
    }
}