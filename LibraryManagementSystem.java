
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.JOptionPane;

class Book {

    String bookName, authorName, issuedTo;
    boolean issuedStatus;

    LocalDateTime currentDateTime = LocalDateTime.now();

    List<Book> list = new ArrayList<>();

    Book() {
    }

    // constructor to add new book in library
    Book(String str1, String str2) {
        setBookName(str1);
        setBookAuthorName(str2);
        issuedStatus = false;
        setBookIssuedTo(null);;
    }

    // constructor to issue book
    Book(String str3, int bookId) {
        issuedStatus = true;
        setBookIssuedTo(str3);
    }

    // constructor to return book
    Book(int bookId) {
        issuedStatus = false;
        setBookIssuedTo(null);
    }

    // setters
    void setBookName(String str1) {
        bookName = str1;
    }

    void setBookAuthorName(String str2) {
        authorName = str2;
    }

    void setBookIssuedTo(String str3) {
        issuedTo = str3;
    }

    // getters
    String getBookName() {
        return bookName;
    }

    String getBookAuthorName() {
        return authorName;
    }

    String getBookIssuedTo() {
        return issuedTo;
    }

    Boolean getIssuedStatus() {
        return issuedStatus;
    }

    // issued and return date & time getting methods
    // void getBookIssuedDate() {
    //     System.out.println(currentDateTime);
    // }
    String getBookIssuedDate() {
        return currentDateTime.toString();
    }

    // void getBookReturnDate() {
    //     System.out.print(currentDateTime);
    // }
    String getBookReturnDate() {
        return currentDateTime.toString();
    }
}

interface UtilitiesMethod {

    StringBuffer sb1 = new StringBuffer(); // sb1 refers to dynamic string containing all avaliable books(issued & not issued both) in ArrayList list
    // method listing all books (to be overridden according to each switch case)

    default void showAllBooks(List<Book> list) {
        int bookId = 101;
        // System.out.println("\nAvailable books are/is:");
        for (Book b1 : list) {
            // System.out.println("\nBook ID: " + bookId + "\nBook Name: " + b1.getBookName() + "\nAuthor Name: " + b1.getBookAuthorName() + "\nIssued Status: " + b1.getIssuedStatus());
            sb1.append("\nBook ID: " + bookId + "\nBook Name: " + b1.getBookName() + "\nAuthor Name: " + b1.getBookAuthorName() + "\nIssued Status: " + b1.getIssuedStatus());
            bookId++;
            if (b1.getIssuedStatus()) {
                // System.out.print("Issuer Name: " + b1.getBookIssuedTo() + "\nIssued On: ");
                // b1.getBookIssuedDate();
                sb1.append("\nIssuer Name: " + b1.getBookIssuedTo() + "\nIssued On: " + b1.getBookIssuedDate());
            }
        }
        // JOptionPane.showMessageDialog(null, "Available books are/is:" + sb1);
    }
}

public class LibraryManagementSystem implements UtilitiesMethod {

    static LibraryManagementSystem l1 = new LibraryManagementSystem();

    // List<Book> libraryData = new ArrayList<>(); // what would happen if
    // collection was declared here
    // static void asteriskPrinter(int numOfAsterisk) {
    //     for(int i = 0; i < numOfAsterisk; i++) {
    //         System.out.print("*");
    //     }
    // }
    public static void main(String[] args) {

        Book b2 = new Book();

        List<Book> libraryData = new ArrayList<>();
        LibraryManagementSystem l1 = new LibraryManagementSystem(); // what was the significance of instantiating here

        // asteriskPrinter(18);
        // System.out.println("\nWelcome to Library!\n");
        // asteriskPrinter(18);
        libraryData.add(new Book("book1", "author1"));
        libraryData.add(new Book("book2", "author2"));
        // l1.showAllBooks(libraryData);

        Scanner sc = new Scanner(System.in);

        int i;

        do {

            // System.out.print(
            //         "\nActions:\nTo add book - 1\nTo issue book - 2\nTo return book - 3\nTo see all available books - 4\nExit - 5\nEnter number to choose your action:");
            // i = sc.nextInt();
            i = JOptionPane.showOptionDialog(null, "Welcome to Library!\nActions:", "Library Actions Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Add Book", "Issue Book", "Return Book", "Available Books", "Exit"}, null);
            int j = 1;

            switch (i) {

                case 0:
                    // System.out.print("Enter Title & Author's name of new book to be added:\nTitle:");
                    // String str1 = sc.next();
                    String str1 = JOptionPane.showInputDialog(null, "Enter Title & Author's name of new book to be added:\nTitle:");
                    // System.out.print("Author Name:");
                    // String str2 = sc.next();
                    String str2 = JOptionPane.showInputDialog(null, "Author Name:");
                    libraryData.add(new Book(str1, str2));

                    // System.out.println("\nNew book is added successfully!");
                    l1.showAllBooks(libraryData);
                    JOptionPane.showMessageDialog(null, "New book is added successfully!" + sb1);
                    // System.out.println();

                    break;

                case 1:
                    // System.out.println("\nFollowing are available books:");
                    StringBuffer sb2 = new StringBuffer("Following are available books:");
                    StringBuffer sb4 = new StringBuffer("Following are available books:");
                    l1.showAllBooks(libraryData);
                    // System.out.println("Enter Issuer's Name: ");
                    // String issuerName = sc.next();
                    String issuerName = JOptionPane.showInputDialog(null, "Enter Issuer's Name: ");

                    for (Book b1 : libraryData) {
                        if (b1.issuedStatus == false) {
                            // System.out.println(j + "-" + "Book name:" + b1.getBookName() + " Author name:"
                            //         + b1.getBookAuthorName());
                            sb2.append("\n" + j + "-" + "Book name:" + b1.getBookName() + " Author name:" + b1.getBookAuthorName());
                            j++;
                        }
                    }
                    // System.out.print("Enter chronological sequence of books from following list to issue it:");
                    // int userIssueChoice = sc.nextInt();
                    int userIssueChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter chronological sequence of books from following list to issue it:\n" + sb2));

                    for (Book b1 : libraryData) {
                        if (b1 == libraryData.get(userIssueChoice - 1)) {
                            b1.issuedStatus = true;
                            b1.setBookIssuedTo(issuerName);
                        }
                    }

                    // Book(issuerName, userIssueChoice);
                    // System.out.println("\nBook is issued successfully!");
                    for (Book b1 : libraryData) {
                        if (b1.issuedStatus == false) {
                            // System.out.println(j + "-" + "Book name:" + b1.getBookName() + " Author name:"
                            //         + b1.getBookAuthorName());
                            sb4.append("\nBook name:" + b1.getBookName() + " Author name:" + b1.getBookAuthorName());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Book is issued successfully to " + issuerName + "!\n" + sb4);

                    // l1.showAllBooks(libraryData);
                    // System.out.println();
                    break;

                case 2:
                    // System.out.print("Enter Book ID:");
                    // int returnedBookId = sc.nextInt();
                    l1.showAllBooks(libraryData);

                    int returnedBookId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Book ID:"));
                    Book b4 = libraryData.get(returnedBookId - 101);
                    b4.setBookIssuedTo(null);
                    b4.issuedStatus = false;
                    // String str3 = sc.next();
                    // String str4 = sc.next();

                    // libraryData.add(new Book(str3, str4));
                    // System.out.println("\nBook is returned successfully!");
                    // l1.showAllBooks(libraryData);
                    // System.out.println();
                    JOptionPane.showMessageDialog(null, "Book is returned successfully!" + sb1);

                    break;

                case 3:
                    l1.showAllBooks(libraryData);
                    JOptionPane.showMessageDialog(null, sb1);

                default:
                // System.out.println();
            }
        } while (i != 4);

        if (i == 3) {
            System.out.println("Program ended Successfully!\nThank You!");
        }
    }
}
