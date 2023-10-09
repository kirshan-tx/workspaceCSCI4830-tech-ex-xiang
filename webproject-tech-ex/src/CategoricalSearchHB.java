import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.MyTableXiangTechEx;
import util.Info;
import util.UtilDBXiang;

@WebServlet("/CategoricalSearchHB")
public class CategoricalSearchHB extends HttpServlet implements Info {
    private static final long serialVersionUID = 1L;

    public CategoricalSearchHB() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Categorical Search Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
        out.println("<ul>");

        if (category != null && !category.isEmpty()) {
            List<MyTableXiangTechEx> listExpenses = UtilDBXiang.listExpensesByCategory(category);
            display(listExpenses, out);
        } else {
            out.println("Please select a category.");
        }

        out.println("</ul>");
        out.println("<a href=/" + projectName + "/" + categoricalWebName + ">Back to Categorical Search</a> <br>");
        out.println("</body></html>");
    }

    void display(List<MyTableXiangTechEx> listExpenses, PrintWriter out) {
        if (listExpenses.isEmpty()) {
            out.println("<h1>None</h1>");
        } else {
            for (MyTableXiangTechEx expense : listExpenses) {
                out.println("<li>" + expense.getId() + ", " //
                    + expense.getExpense() + ", " //
                    + expense.getAmount() + ", " //
                    + expense.getDate() + ", " //
                    + expense.getCategory() + "</li>");
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
