import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.ExpenseXiang;
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
            List<ExpenseXiang> listExpenses = UtilDBXiang.listExpensesByCategory(category);
            display(listExpenses, out);
        } else {
            out.println("Please select a category.");
        }

        out.println("</ul>");
        out.println("<a href=/" + projectName + "/" + categoricalWebName + ">Back to Categorical Search</a> <br>");
        out.println("</body></html>");
    }

    void display(List<ExpenseXiang> listExpenses, PrintWriter out) {
        if (listExpenses.isEmpty()) {
            out.println("<h1>None</h1>");
        } else {
        	int totalAmount = 0;
        	
        	out.println("<table class='expense-table'>");
            out.println("<tr><th>Expense</th><th>Amount</th><th>Date</th><th>Category</th></tr>");
            for (ExpenseXiang expense : listExpenses) {
                out.println("<tr><td>" + expense.getExpense() + "</td><td>" //
                        + expense.getAmount() + "</td><td>" //
                        + expense.getDate() + "</td><td>" //
                        + expense.getCategory() + "</td></tr>");
                
                totalAmount += expense.getAmount();
            }
            out.println("</table>");
            
            out.println("<h4>Total Amount: $" + totalAmount + "</h4>");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
