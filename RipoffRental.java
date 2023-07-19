/**
 * @author Pushkar Taday
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents a online Textbook rental.
 */

public class RipoffRental {

    private static Bookshelf shelfA;
    private static Bookshelf shelfB;
    private static Bookshelf shelfC;

   public static Bookshelf bookshelf; // This acts like a reference pointer and it is through this reference all operations can be performed on Bookshelf's.

    /**
     * This method is used to help determine the shelf.
     * @param ch
     */

    public static void whichShelf(char character)
    {
            if (character == 'A') {
                bookshelf = shelfA;
            }

            else if (character == 'B') {
                bookshelf = shelfB;
            }

            else if (character == 'C') {
                bookshelf = shelfC;
            }

            else
                System.out.println("Wrong shelf. Choose either A, B or C.");
    }

    /**
     * This method is used to create a menu driven output.
     * @param args
     */
    public static void main(String[] args) {

        Scanner obj = new Scanner(System.in);

        shelfA = new Bookshelf();
        shelfB = new Bookshelf();
        shelfC = new Bookshelf();

        System.out.println("Welcome to Pushkar's World of Amazing Textbook Rentals, highest price guaranteed!");

        System.out.println("Which bookshelf do you want to start with?(A,B,C)");
        char shelf = obj.next().charAt(0);

        whichShelf(Character.toUpperCase(shelf));

        boolean check = true;

            while (check) {

                try {

                    System.out.println("Menu:\n" +
                            "\n" +
                            "     A) Add Book\n" +
                            "\n" +
                            "     S) Swap Books\n" +
                            "\n" +
                            "     L) Loan Book\n" +
                            "\n" +
                            "     R) Remove Book\n" +
                            "\n" +
                            "     D) Duplicate Book\n" +
                            "\n" +
                            "     C) Change Shelf\n" +
                            "\n" +
                            "     O) Overwrite shelf with clone of current shelf\n" +
                            "\n" +
                            "     E) Check if two shelves are equal\n" +
                            "\n" +
                            "     P) Print current bookshelf\n" +
                            "\n" +
                            "     Q) Quit");

                    System.out.println("Please select an option:");


                    char ch = obj.next().charAt(0);

                    obj.nextLine();//to accept the next token



                    ch=Character.toUpperCase(ch);

                    switch (ch) {

                        case 'A':

                            System.out.println("Please enter a title:");
                            String title = obj.nextLine();

                            System.out.println("Please enter an author:");
                            String author = obj.nextLine();

                            int condition=0;

                            boolean checkCondition=true;

                            while(checkCondition) {

                                System.out.println("Please enter condition (1-5) :");
                                 condition = obj.nextInt();

                                 obj.nextLine();//to accept the next token

                                if (condition >= 1 && condition <= 5) {

                                    checkCondition =false;
                                }
                                else
                                {
                                    System.out.println("Try Again! Please enter a valid condition. ");
                                }
                            }

                            Book book = new Book(title, author, condition);

                            System.out.println("Please enter position on shelf:");
                            int index = obj.nextInt();

                            bookshelf.addBook(index, book);

                            break;

                        case 'S':

                            System.out.println("Please enter an index");
                            int index1=obj.nextInt();

                            System.out.println("Please enter another index");
                            int index2=obj.nextInt();

                            bookshelf.swapBooks(index1,index2);

                            break;

                        case 'L':

                            System.out.println("Please enter an index");
                            int indexL=obj.nextInt();

                            obj.nextLine();

                            System.out.println("Please enter recipient");
                            String recipient=obj.nextLine();

                            int conditionL=0;

                            boolean checkConditionL=true;

                            while(checkConditionL) {

                                System.out.println("Please enter condition (1-5)");
                                 conditionL = obj.nextInt();


                                if (conditionL >= 1 && conditionL <= 5) {

                                    checkConditionL =false;
                                }

                                else
                                {
                                    System.out.println("Try Again! Please enter a valid condition. ");
                                }

                            }

                            if(indexL<=bookshelf.numBooks()) {

                                bookshelf.getBook(indexL).setCondition(conditionL);

                                bookshelf.getBook(indexL).setBorrower(recipient);

                                System.out.println(bookshelf.getBook(indexL).getTitle() + " has been loaned out to " + recipient + ".");
                            }

                            else
                            {
                                System.out.println("No book at that point to be loaned.");
                            }

                            break;

                        case 'R':

                            System.out.println("Please enter the index");
                            int indexR=obj.nextInt();

                            bookshelf.removeBook(indexR);

                            break;

                        case 'D':

                            int sourceIndex=0;
                            boolean checkIndex = true;
                            while(checkIndex) {

                                boolean checkSource=true;
                                boolean checkDestination=true;

                                while(checkSource) {

                                    System.out.println("Please enter the source index:");
                                    sourceIndex = obj.nextInt();

                                    if (sourceIndex <= bookshelf.numBooks() && sourceIndex > 0)
                                        checkSource = false;

                                    else
                                    {
                                        System.out.println("Source index invalid. No book available at that index.The total number of book in the shelf currently are: " + bookshelf.numBooks() + "so, choose the book you want to copy from only the available index.");
                                    }
                                }

                                while(checkDestination) {

                                    System.out.println("Please enter a destination index:");
                                    int destinationIndex = obj.nextInt();

                                    if (destinationIndex == bookshelf.numBooks() + 1) {

                                        Book newBook = (Book) bookshelf.getBook(sourceIndex).clone();

                                        bookshelf.addBook(destinationIndex, newBook);

                                        newBook.setBorrower("<none>");

                                        System.out.println("A new copy of " + bookshelf.getBook(sourceIndex).getTitle() + " is in index " + destinationIndex+".");

                                        checkIndex = false; checkDestination=false;

                                    } else if (destinationIndex != bookshelf.numBooks() + 1 && bookshelf.numBooks() != 20) {

                                        System.out.println("Will create a hole in the shelf. The total number of book in the shelf currently are: " + bookshelf.numBooks() + ". Hence you can add to the next available index.");
                                    } else if (bookshelf.numBooks() == 20) {
                                        System.out.println("Copy cannot be made as the shelf is full. ");
                                        checkIndex = false; checkDestination=false;
                                    } else {
                                        System.out.println("Copy couldn't be made. Check and enter again.");
                                    }

                                }
                            }


                            break;

                        case 'C':

                            System.out.println("Please select the shelf to look at:");
                            char select=obj.next().charAt(0);

                            select=Character.toUpperCase(select);

                            if(select=='A')
                                System.out.println("Bookshelf A selected.");

                            else if(select=='B')
                                System.out.println("Bookshelf B selected.");

                            else
                                System.out.println("Bookshelf C selected.");


                            whichShelf(select);

                            break;

                        case 'O':

                            System.out.println("Please select the shelf to overwrite with the :");
                            char selectShelf=obj.next().charAt(0);

                            Bookshelf clonedBookshelf = (Bookshelf) bookshelf.clone();

                            selectShelf=Character.toUpperCase(selectShelf);

                            if(selectShelf=='A')
                                shelfA=clonedBookshelf;

                            else if(selectShelf=='B')
                                shelfB=clonedBookshelf;

                            else
                                shelfC=clonedBookshelf;

                            System.out.println("Shelf "+Character.toUpperCase(selectShelf)+ " has been overwritten.");

                            break;

                        case 'E':

                            boolean checkFirst=true;
                            boolean checkSecond=true;

                            Bookshelf currentPointer=bookshelf;

                            while(checkFirst) {
                                System.out.println("Please select a shelf:");
                                char firstShelf = obj.next().charAt(0);

                                firstShelf = Character.toUpperCase(firstShelf);

                                if (firstShelf == 'A' || firstShelf == 'B' || firstShelf == 'C') {

                                    whichShelf(firstShelf);

                                    Bookshelf s = bookshelf; //s acts as a pointer for the recent current bookshelf so that it can be compared

                                    checkFirst=false;


                                    while (checkSecond) {

                                        System.out.println("Please select another shelf:");
                                        char secondShelf = obj.next().charAt(0);

                                        secondShelf=Character.toUpperCase(secondShelf);

                                        if (secondShelf == 'A' || secondShelf == 'B' || secondShelf == 'C') {

                                            whichShelf(secondShelf);

                                            Bookshelf s1 = bookshelf; //s1 acts as a pointer for the recent current bookshelf so that it can be compared

                                            if(s.equals(s1)==true)
                                            System.out.println("Both the selves are equal.");

                                            else
                                                System.out.println("Both the selves are not equal.");

                                            checkSecond = false;
                                        }
                                        else
                                            System.out.println("Wrong input.Enter A,B or C");
                                    }
                                }else
                                    System.out.println("Wrong input.Enter A,B or C");
                            }

                            bookshelf=currentPointer;

                            break;

                        case 'P':

                            if(bookshelf==shelfA)
                                System.out.println("Bookshelf A");

                             else if(bookshelf==shelfB)
                                System.out.println("Bookshelf B");

                            else
                                System.out.println("Bookshelf C");

                            System.out.printf("%-50s%-50s%-10s%-50s\n","Spot Title","Author","Cond.","Borrower");

                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

                            System.out.println(bookshelf.toString());
                            break;

                        case 'Q':

                            System.out.println("Goodbye!");

                            check=false;
                            break;

                        default:

                            System.out.println("This option does not exist. Please choose the correct option.");
                    }
                }

                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (FullShelfException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyShelfException e) {
                    System.out.println(e.getMessage());
                }
                catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                catch(InputMismatchException e){
                    System.out.println("Wrong input. See what type is required.");
                    obj.nextLine();//If there is an inputMisMatchException
                }
            }
        }
    }

