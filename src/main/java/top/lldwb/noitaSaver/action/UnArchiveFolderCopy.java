package top.lldwb.noitaSaver.action;

import top.lldwb.noitaSaver.service.copy.FolderCopy;
import top.lldwb.noitaSaver.service.copy.UnArchiveFolderCopyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 读档API
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/unArchiveFolderCopy")
public class UnArchiveFolderCopy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 源文件夹地址
        String writePath = req.getParameter("writePath");
        // 目标文件夹地址
        String readPath = req.getParameter("readPath");

        FolderCopy folderCopy = FolderCopy.getFolderCopyFactory("读档");
        folderCopy.copy(writePath, readPath);
    }
}
