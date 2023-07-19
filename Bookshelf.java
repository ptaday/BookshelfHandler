/**
 * @author Pushkar Taday
 */


/**
 *This class represents a bookshelf which has array of books and the count of books.
 *This class contains various methods which perform certain operations on the bookshelf.
 */

public class Bookshelf implements Cloneable {

    private Book[] books;
    private int count;

    final static int CAPACITY = 21;

    /**
     * This is a default constructor used to create a default constructor.
     *
     * @param count
     *
     * @param books
     */

    Bookshelf() {
        count = 0;
        books = new Book[CAPACITY];//0th position in the array will always be null and the books will be stored from the 1st index to the 20th
    }

    /**
     * This parameterized constructor is created so that it can help during cloning.
     * @param count
     * @param books
     */

    Bookshelf(int count,Book[] books)
    {
        this.count=count;
        this.books=books;
    }

    /**
     * This method serves as a getter method and returns array of books.
     * @return books
     */
    public Book[] getBooks() {
        return books;
    }

    /**
     * This method serves as a getter method and returns the count of the books.
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * This method returns the number of books in the bookshelf
     *
     * @return count
     */

    public int numBooks() {
        return count;
    }

    /**
     * This method returns a book at a specified index from the bookshelf
     * @param index
     * @return
     * book at index i
     * @throws ArrayIndexOutOfBoundsException
     * when index is out of the bound i.e >20 or <1
     */

    public Book getBook(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 1 && index < CAPACITY) {
            return books[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Not in the range of 0-20");
        }
    }

    /**
     * This method helps the user add book to the bookshelf
     * @param index
     * @param book
     * @throws IllegalArgumentException
     * when user doesn't add a book next to the last entered book and also when the user tries tries to out of bound in terms of the CAPACITY.
     * @throws FullShelfException
     * when the bookshelf is full i.e it already consists 20 books
     */

    public void addBook(int index, Book book) throws IllegalArgumentException, FullShelfException {

        if(index>CAPACITY-1||index<1)
            throw new IllegalArgumentException("Out of bound. Enter the index wisely.");

       else  if (numBooks() == CAPACITY - 1)
            throw new FullShelfException("Bookshelf is full");

        else if (index != 1 && books[index - 1] == null) {
            throw new IllegalArgumentException("Will create a hole in the shelf. Choose wisely.");
        }
        else if (books[index] == null) {
            books[index] = book;
            count++;
            System.out.println("Book added.");
        } else{

                for (int i = numBooks(); i >= index; i--) {
                    books[i + 1] = books[i];
                }
                books[index] = book;

                System.out.println("Book added.");
                count++;
            }

        }

    /**
     * This method helps the user to remove a book from the bookshelf
     * @param index
     * @return book
     * @throws IndexOutOfBoundsException
     * when the entered index is out of bound with respect to the CAPACITY
     * @throws EmptyShelfException
     * when the bookshelf doesn't have any book inside it
     */

    public Book removeBook(int index) throws IndexOutOfBoundsException, EmptyShelfException {


        if (index < 1 || index > CAPACITY - 1)
            throw new IndexOutOfBoundsException("Out of the index scope");

        else if (numBooks() == 0)
            throw new EmptyShelfException("Shelf is empty.");

        else if (books[index] != null && books[index + 1] != null) {

            for (int i = index; i < numBooks(); i++) {
                books[i] = books[i + 1];
            }
            books[numBooks()] = null;
            count--;
            return books[index];

        } else if (books[index] != null && books[index + 1] == null) {

            count--;
            Book book = books[index];
            books[index] = null;

            return book;
        } else {
            System.out.println("Cannot be removed. No Book at that index.");
            return null;
        }
    }

    /**
     * This method helps the user to swap books inside the bookshelf
     * @param index1
     * @param index2
     * @throws ArrayIndexOutOfBoundsException
     * when the index1 and index2 are out of bound with respect to CAPACITY and also when either of books at the index are null.
     */

    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException {

        if (index1 < 1 || index1 >CAPACITY-1 || index2 < 1 || index2 > CAPACITY-1)
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        else {

            if (books[index1] != null && books[index2] != null) {
                Book temp = books[index1];

                books[index1] = books[index2];
                books[index2] = temp;

                System.out.println(books[index2].getTitle() + " is swapped with " + books[index1].getTitle());
            } else
                throw new ArrayIndexOutOfBoundsException("Invalid index. Check if both the entered index have a book at that index of the bookshelf.");
        }
    }


    /**
     * This method is used to create a deep copy of a Bookshelf object
     * @return bookshelf
     * @throws CloneNotSupportedException
     * when the class doesn't implement Cloneable
     */

   public Object clone() throws CloneNotSupportedException {
        Bookshelf bookshelf = new Bookshelf(getCount(),getBooks());

        bookshelf.books = new Book[CAPACITY];
        for (int i = 1; i <= numBooks(); i++) {
            bookshelf.books[i] = (Book) this.books[i].clone();
        }
        return bookshelf;
    }

    /**
     * This method check whether the two bookshelf contain similar types of books.
     * @param o
     * @return boolean result
     */

    public boolean equals(Object o) {

        if (o instanceof Bookshelf) {
            boolean result = false;
            if (this.numBooks() == ((Bookshelf) o).numBooks()) {

                if (this.numBooks() == 0) {
                    System.out.println("Both the selves are empty.");
                    result = true;

                }
                for (int i = 1; i < this.numBooks(); i++) {
                    if (this.getBook(i).equals(((Bookshelf) o).getBook(i)))
                        result = true;

                    else {
                        result = false;
                        break;
                    }
                }
                return result;
            }
            return false;
        }
        return false;
    }

    /**
     * This method helps to store the information of all the books in the desired bookshelf's so that it can be printed efficiently.
     * @return s
     */

    public String toString() {
        String s = "";
        for (int i = 1; i <= numBooks(); i++) {
       s = s + (i) + ". " + books[i].toString();
        }
        return s;
    }
}

