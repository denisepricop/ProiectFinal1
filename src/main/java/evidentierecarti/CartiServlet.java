package evidentierecarti;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/carti")
public class CartiServlet extends HttpServlet {

    private static final String ACTION = "action";
    private static final String ADD = "add";
    private static final String LIST = "list";

    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("mytask list service called now.");

        HttpSession session = request.getSession(true);

        String action = request.getParameter(ACTION);

        if (action != null && action.equals(ADD)) {
            try {
                addAction(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (action != null && action.equals(LIST)) {
            listAction(request, response);
        }
    }

    private void listAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list action");

        List ListaDeCarti = new ArrayList<Carti>();

        try {
            ListaDeCarti = DBOper.demoRead();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


   JSONObject json = new JSONObject();
        json.put("carti", ListaDeCarti);

        returnJsonResponse(response, json.toString());
        System.out.println("end list action");
    }


    private void addAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, ClassNotFoundException {

        System.out.println("add action");

        HttpSession session = request.getSession(true);


        String numeleCartii = request.getParameter("numelecartii");
        String numeleAutorului = request.getParameter("numeleautorului");
        String dataInceperii = request.getParameter("datainceperii");
        String dataTerminarii = request.getParameter("dataTerminarii");

        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        Date dataInceperiiDate = format.parse(dataInceperii);
        Date dataTerminariiDate = format.parse(dataTerminarii);

        DBOper.demoCreate(numeleCartii, numeleAutorului, new java.sql.Date(dataInceperiiDate.getTime()), new java.sql.Date(dataTerminariiDate.getTime()));


        System.out.println("now I am done");

        try {
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**/
    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }


}