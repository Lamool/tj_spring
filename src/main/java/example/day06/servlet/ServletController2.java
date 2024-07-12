package example.day06.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// http://localhost:8080/day06/servlet?data=10
@WebServlet("/day06/test")
public class ServletController2 extends HttpServlet {

    // 1. doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("ServletController2.doGet");
        int reqData = Integer.parseInt(req.getParameter("value"));
        System.out.println("request Data : " + reqData);
        resp.getWriter().print("response Data : [get]" + (reqData + 2));
    }

    // 2. doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("ServletController2.doPost");
        int reqData = Integer.parseInt(req.getParameter("value"));
        System.out.println("request Data : " + reqData);
        resp.getWriter().print("response Data : [post]" + (reqData * 2));
    }

    // 3. doPut
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        System.out.println("ServletController2.doPut");
        int reqData = Integer.parseInt(req.getParameter("value"));
        System.out.println("request Data : " + reqData);
        resp.getWriter().print("response Data : [put]" + (reqData / 2));
    }

    // 4. doDelete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        System.out.println("ServletController2.doDelete");
        int reqData = Integer.parseInt(req.getParameter("value"));
        System.out.println("request Data : " + reqData);
        resp.getWriter().print("response Data : [delete]" + (reqData % 2));
    }

}
