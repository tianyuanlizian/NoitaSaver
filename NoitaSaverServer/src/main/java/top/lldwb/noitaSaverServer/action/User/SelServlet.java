package top.lldwb.noitaSaverServer.action.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.servlet.ResponseData;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * 查询全部用户
 */
@WebController("/selServlet")
public class SelServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ResponseData responseData = successJson(new UserDao().getUserList());
            print(response,responseData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
