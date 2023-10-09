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
public class DeleteRowHB extends HttpServlet implements Info {
    private static final long serialVersionUID = 1L;

    public DeleteRowHB() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rowId = request.getParameter("rowId");

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

        if (rowId != null && !rowId.isEmpty()) {
            try {
                int id = Integer.parseInt(rowId);
                boolean success = UtilDBXiang.deleteRow(id);

                if (success) {
                    out.println("<h1>Row with ID " + id + " deleted successfully.</h1>");
                } else {
                    out.println("<h1>Error deleting row with ID " + id + ". Row not found.</h1>");
                }
            } catch (NumberFormatException e) {
                out.println("<h1>Error: Invalid ID format.</h1>");
            }
        } else {
            out.println("<h1>Error: Please provide a valid row ID.</h1>");
        }

        out.println("</ul>");
        out.println("<a href=/" + projectName + "/" + categoricalWebName + ">Back to Categorical Search</a> <br>");
        out.println("</body></html>");
    }

    void display(List<MyTableXiangTechEx> listExpenses, PrintWriter out) {
        if (listExpenses.isEmpty()) {
            out.println("<h1>None</h1>");
        } else {
        	int totalAmount = 0;
        	
        	out.println("<table class='expense-table'>");
            out.println("<tr><th>Expense</th><th>Amount</th><th>Date</th><th>Category</th></tr>");
            for (MyTableXiangTechEx expense : listExpenses) {
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
