package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
CREATE TABLE MyTable0914Xiang(
id INT NOT NULL AUTO_INCREMENT,
EXPENSE VARCHAR(30) NOT NULL,
AMOUNT INT NOT NULL,
DATE VARCHAR(30) NOT NULL,
CATEGORY VARCHAR(30) NOT NULL,
PRIMARY KEY (ID));
 */
@Entity
@Table(name = "MyTableXiangTechEx")
public class MyTableXiangTechEx {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "expense")
   private String expense;

   @Column(name = "amount")
   private int amount;

   @Column(name = "date")
   private String date;
   
   @Column(name = "category")
   private String category;
   
   public MyTableXiangTechEx() {
   }

   public MyTableXiangTechEx(Integer id, String expense, int amount, String date, String category) {
      this.id = id;
      this.expense = expense;
      this.amount = amount;
      this.date = date;
      this.category = category;
   }

   public MyTableXiangTechEx(String expense, int amount, String date, String category) {
      this.expense = expense;
      this.amount = amount;
      this.date = date;
      this.category = category;
   }

   // Getters and Setters
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getExpense() {
      return expense;
   }

   public void setExpense(String expense) {
      this.expense = expense;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }
   
   @Override
   public String toString() {
      return "Expense=" + this.expense + ", Amount=" + this.amount + ", Date=" + this.date + ", Category=" + this.category;
   }
}