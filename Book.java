/**
 * @author Pushkar Taday
 * SBU ID:114375166
 * Recitation:04
 */

package Homework1;

/**
 * This class represents a book which has a title, author, borrower and condition.
 */
public class Book implements Cloneable{

    private String title;
    private String author;
    private String borrower;
    private int condition;

    /**
     * This is a default constructor
     *
     * Title of the book.
     * @param title
     *
     * Author of the book.
     * @param author
     *
     * Borrower of the book.
     * @param borrower
     *
     * Condition of the book.
     * @param condition
     */

    Book()
    {
        title="";
        author="";
        borrower="";
        condition=0;
    }

    /**
     * This is a parameterized constructor used to create a new book object
     *
     * Title of the book.
     * @param title
     *
     * Author of the book.
     * @param author
     *
     * Condition of the book.
     * @param condition
     */
    public Book(String title, String author, int condition)
    {
        this.title=title;
        this.author=author;
        this.condition=condition;
        borrower="";
    }

    /**
     *This getter method returns the title of the book
     * @return
     * title
     */


    public String getTitle() {
        return title;
    }

    /**
     *This getter method returns the author of the book
     * @return
     * author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *This getter method returns the borrower of the book
     * @return
     * borrower
     */

    public String getBorrower() {
        return borrower;
    }

    /**
     *This getter method returns the condition of the book
     * @return
     * condition
     */


    public int getCondition() {
        return condition;
    }

    /**
     * This method sets the title of the book
     * @param title
     */
    public void setTitle(String title) {
        this.title=title;
    }

    /**
     * This method sets the author of the book
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method sets the borrower of the book
     * @param borrower
     */

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    /**
     * This method sets the borrower of the book
     * @param condition
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    /**
     * This method checks whether two book objects are similar and return accordingly.
     * @param o
     * @return
     */

    public boolean equals(Object o) {
        if(o instanceof Book)
        {
            if(this.title.equals(((Book) o).getTitle())&&this.author.equals(((Book)o).getAuthor())&&this.condition==((Book)o).getCondition())
                return true;
        }
        return false;
    }

    /**
     * This method when called returns the string containing information about the book
     * @return
     */

    public String toString()
    {
        if(getBorrower()=="")
            return String.format("%-50s%-50s%-10d%-50s\n", getTitle(), getAuthor(), getCondition(),"<none>");

         else
         return String.format("%-50s%-50s%-10d%-50s\n", getTitle(), getAuthor(), getCondition(), getBorrower());
    }

    /**
     * This method helps cloning a book objectnmmm22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222223
     * @return book
     * @throws CloneNotSupportedException
     */
    public Object clone()throws CloneNotSupportedException  {

            Book book=new Book(this.getTitle(),this.getAuthor(),this.getCondition());

            book.setBorrower("<none>");
            return book;
        }

    }



