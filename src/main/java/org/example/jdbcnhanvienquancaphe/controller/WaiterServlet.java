package org.example.jdbcnhanvienquancaphe.controller;

import org.example.jdbcnhanvienquancaphe.HelloServlet;
import org.example.jdbcnhanvienquancaphe.model.Waiter;
import org.example.jdbcnhanvienquancaphe.service.DaoWaiter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@WebServlet(name = "WaiterServlet", urlPatterns = "/waiters")
public class WaiterServlet extends HttpServlet {
    private static final long serialVersionUrl = 1L;
    private DaoWaiter daoWaiter;

    public void init() {
        daoWaiter = new DaoWaiter();
    }


//    Dùng để gửi dữ liệu hoặc thực hiện thao tác thay đổi trên server (ví dụ: thêm, sửa, xóa dữ liệu).
//    Dữ liệu được gửi đi ẩn trong phần thân (body) của yêu cầu HTTP.

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                //Thêm dũ liệu trang hiển thị lên SQL
                case "create":
                    add_and_ínsert_WaiterfromJSQltoSQL(request, response);
                    break;
                case "edit":
                    updateWaiter(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


//
//    Dùng để truy xuất dữ liệu.
//    Khi một client (trình duyệt hoặc ứng dụng khác) muốn yêu cầu tài nguyên hoặc dữ liệu mà không thay đổi trạng thái trên server.
//    Thông tin được gửi qua URL dưới dạng tham số query string.
//

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteWaiter(request, response);
                    break;
                default:
                    listWaiter(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void listWaiter(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Waiter> listWaiter = daoWaiter.findAllWaiter();
        request.setAttribute("listWaiters", listWaiter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/list.jsp");
        dispatcher.forward(request, response);
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Waiter existingUser = daoWaiter.findbyId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/edit.jsp");
        request.setAttribute("waiter", existingUser);
        dispatcher.forward(request, response);

    }



   private void add_and_ínsert_WaiterfromJSQltoSQL(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
       int id = Integer.parseInt(request.getParameter("id"));
       String name = request.getParameter("name");
       String salary = request.getParameter("salary");
       Waiter newWaiter = new Waiter(id,name,salary);
       daoWaiter.addWaiter(newWaiter);
       RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/addWaiter.jsp");
       dispatcher.forward(request,response);
    }


    private void updateWaiter(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String salary = request.getParameter("salary");

        Waiter waiter = new Waiter(id, name, salary);
        daoWaiter.updateWaiter(id,waiter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/edit.jsp");
        dispatcher.forward(request, response);
    }






    private void deleteWaiter(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        daoWaiter.removeWaiter(id);

        List<Waiter> listWaiters = daoWaiter.findAllWaiter();
        request.setAttribute("listWaiters", listWaiters );
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/waiters/list.jsp");
        dispatcher.forward(request, response);
    }
}




