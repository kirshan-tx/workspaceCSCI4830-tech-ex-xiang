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

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<MyTableXiangTechEx> listExpenses = null;
      if (keyword != null && !keyword.isEmpty()) {
    	  listExpenses = UtilDBXiang.listExpenses(keyword);
      } else {
    	  listExpenses = UtilDBXiang.listExpenses();
      }
      display(listExpenses, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Expenses</a> <br>");
      out.println("</body></html>");
   }

   void display(List<MyTableXiangTechEx> listExpenses, PrintWriter out) {
	  out.println("<table class='expense-table'>");
      out.println("<tr><th>ID	</th><th>Expense</th><th>Amount</th><th>Date</th><th>Category</th></tr>");
      
      int totalAmount = 0;
      
      for (MyTableXiangTechEx expense : listExpenses) {
    	  System.out.println("[DBG] " + expense.getId() + ", " //
                  + expense.getExpense() + ", " //
                  + expense.getAmount() + ", " //
                  + expense.getDate() + ", " //
                  + expense.getCategory());
         
         out.println("<tr><td>" + expense.getId() + "</td><td>" //
        		 + expense.getExpense() + "</td><td>" //
                 + expense.getAmount() + "</td><td>" //
                 + expense.getDate() + "</td><td>" //
                 + expense.getCategory() + "</td></tr>");
         
         totalAmount += expense.getAmount();
      }
      out.println("</table>");
      
      out.println("<h4>Total Amount: $" + totalAmount + "</h4>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
