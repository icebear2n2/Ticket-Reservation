package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Enums.ModuleType;
import Module.ModuleManager;
import Module.EditModule;
public class addAirplaneServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditModule module = getEditModule();
        req.setAttribute("airplaneList", module.getAirPlaneList_by_Database());

        req.getRequestDispatcher("/views/addAirplane.jsp").forward(req, resp);

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditModule module = getEditModule();

        String airplaneName = req.getParameter("airplaneName");
        String departureTime = req.getParameter("departureTime");
        String departureAirport = req.getParameter("departureAirport");
        String arrivalAirport = req.getParameter("arrivalAirport");

        // 날짜/분 만 받아온 데이터를 날짜/분/초로 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parsedDate = inputFormat.parse(departureTime);
            String formattedDate = outputFormat.format(parsedDate);

            Timestamp departureTime2 = Timestamp.valueOf(formattedDate);

            if (module.insertAirplane(airplaneName, departureTime2, departureAirport, arrivalAirport)){
                //성공 알림
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.println("<script>alert('추가성공');  location.href='/addAirplane'; </script>");
                writer.close();
            }
            else {
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.println("<script>alert('추가실패');  location.href='/addAirplane'; </script>");
                writer.close();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 에러 처리 (예: 잘못된 입력을 사용자에게 알리는 등)
        }



    }

    private EditModule getEditModule(){
        EditModule module = null;
        module = ModuleManager.getInstance().getEditModuleByNowMobule();
        if (module == null){
            ModuleManager.getInstance().changeModule(ModuleType.EDIT);
            module = ModuleManager.getInstance().getEditModuleByNowMobule();
        }

        return module;
    }
}
