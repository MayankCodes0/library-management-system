import java.time.LocalDateTime;
import java.util.*;

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
    void getBookIssuedDate() {
        System.out.println(currentDateTime);
    }

    void getBookReturnDate() {
        System.out.print(currentDateTime);
    }
}

interface UtilitiesMethod {

    // method listing all books (to be overridden according to each switch case)
    default void showAllBooks(List<Book> list) {
        int bookId = 101;
        System.out.println("\nAvailable books are/is:");
        for (Book b1 : list) {
            System.out.println("\nBook ID: " + bookId + "\nBook Name: " + b1.getBookName() + "\nAuthor Name: " + b1.getBookAuthorName() + "\nIssued Status: " + b1.getIssuedStatus());
            bookId++;
            if(b1.getIssuedStatus()) {
                System.out.print("Issuer Name: " + b1.getBookIssuedTo() + "\nIssued On: ");
                b1.getBookIssuedDate();
            }
        }
    }
}

public class LibraryManagementSystem implements UtilitiesMethod {

    static LibraryManagementSystem l1 = new LibraryManagementSystem();

    // List<Book> libraryData = new ArrayList<>(); // what would happen if
    // collection was declared here

    static void asteriskPrinter(int numOfAsterisk) {
        for(int i = 0; i < numOfAsterisk; i++) {
            System.out.print("*");
        }
    }

    public static void main(String[] args) {

        Book b2 = new Book();

        List<Book> libraryData = new ArrayList<>();
        LibraryManagementSystem l1 = new LibraryManagementSystem(); // what was the significance of instantiating here

        asteriskPrinter(18);
        System.out.println("\nWelcome to Library!\n");
        asteriskPrinter(18);

        libraryData.add(new Book("book1", "author1"));
        libraryData.add(new Book("book2", "author2"));

        Scanner sc = new Scanner(System.in);

        int i;

        do {

            System.out.print(
                    "\nActions:\nTo add book - 1\nTo issue book - 2\nTo return book - 3\nTo see all available books - 4\nExit - 5\nEnter number to choose your action:");

            i = sc.nextInt();

            int j = 1;

            switch (i) {

                case 1:
                    System.out.print("Enter Title & Author's name of new book to be added:\nTitle:");
                    String str1 = sc.next();
                    System.out.print("Author Name:");
                    String str2 = sc.next();
                    libraryData.add(new Book(str1, str2));

                    System.out.println("\nNew book is added successfully!");
                    l1.showAllBooks(libraryData);

                    System.out.println();

                    break;

                case 2:
                    System.out.println("\nFollowing are available books:");

                    for (Book b1 : libraryData) {
                        if (b1.issuedStatus != true) {
                            System.out.println(j + "-" + "Book name:" + b1.getBookName() + " Author name:"
                                    + b1.getBookAuthorName());
                            j++;
                        }
                    }
                    System.out.println("Enter Issuer's Name: ");
                    String issuerName = sc.next();
                    
                    System.out.print("Enter chronological sequence of books from following list to issue it:");
                    int userIssueChoice = sc.nextInt();

                    Book b3 = libraryData.get(userIssueChoice - 1);
                    b3.issuedStatus = true;
                    b3.setBookIssuedTo(issuerName);
                    // Book(issuerName, userIssueChoice);
                    System.out.println("\nBook is issued successfully!");

                    l1.showAllBooks(libraryData);

                    System.out.println();

                    break;

                case 3:
                    System.out.print("Enter Book ID:");
                    int returnedBookId = sc.nextInt();

                    Book b4 = libraryData.get(returnedBookId - 101);
                    b4.setBookIssuedTo(null);
                    b4.issuedStatus = false;
                    // String str3 = sc.next();
                    // String str4 = sc.next();

                    // libraryData.add(new Book(str3, str4));

                    System.out.println("\nBook is returned successfully!");

                    l1.showAllBooks(libraryData);

                    System.out.println();

                    break;
                
                case 4:
                l1.showAllBooks(libraryData);
                default:
                    System.out.println();
            }
        } while (i != 5);

        if (i == 4) {
            System.out.println("Program ended Successfully!\nThank You!");
        }
    }
}