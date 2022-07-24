import java.util.Scanner;

public class Library {

    private Book[] libraryBooks;
    private int numOfBooks;
    public static final int MAX_SIZE = 40;
    Scanner s = new Scanner(System.in);

    public Library() {
        numOfBooks = 0;
        libraryBooks = new Book[MAX_SIZE];
    }

    public Library(int size) {
        libraryBooks = new Book[size];
    }

    public Boolean addBook(int ISBN, String author, String title, String genre, String publisher, int edition) {
        if (numOfBooks < libraryBooks.length) libraryBooks[numOfBooks] =
            new Book(ISBN, author, title, genre, publisher, edition);
        if (verifyISBN(ISBN) && findBook(ISBN) != ISBN) {
            numOfBooks++;
            System.out.println("The book has been added.");
            return true;
        }
        return false;
    }

    public Boolean deleteBook(int ISBN) {
        for (int i = 0; i < numOfBooks; i++) {
            if (libraryBooks[i].GetISBN() == ISBN) {
                libraryBooks[i] = libraryBooks[--numOfBooks];
                System.out.println("The book has been deleted.");
                return true;
            }
        }
        return false;
    }

    public int findBook(int ISBN) {
        for (int i = 0; i < numOfBooks; i++) {
            if (libraryBooks[i].GetISBN() == ISBN) return i;
        }
        return -1;
    }

    public void printAll() {
        for (int i = 0; i < numOfBooks; i++) libraryBooks[i].printBookInfo();
    }

    public void printGenre(String g) {
        for (int i = 0; i < numOfBooks; i++) if (libraryBooks[i].Getgenre() == g) libraryBooks[i].printBookInfo();
    }

    public int getNumberOfBooksByAuthor(String n) {
        int count = 0;
        for (int i = 0; i < numOfBooks; i++) if (libraryBooks[i].Getauthor() == n) count += i;
        return count;
    }

    public int getNumberOfBooks() {
        return numOfBooks;
    }

    public void printBookBaseOnEdition(int e) {
        for (int i = 0; i < numOfBooks; i++) if (libraryBooks[i].GetEdition() == e) printAll();
    }

    public void setNumOfBooks(int s) {
        this.numOfBooks = s;
    }

    public Book[] getLibraryBooks() {
        return libraryBooks;
    }

    public boolean verifyISBN(int ISBN) {
        int n1 = ISBN / 1000;
        ISBN %= 1000;
        int n2 = ISBN / 100;
        ISBN %= 100;
        int n3 = ISBN / 10;
        ISBN %= 10;
        int n4 = ISBN % 10;
        int f = (n1 * 3 + n2 * 2 + n3 * 1) % 4;
        if (f == n4) return true; else return false;
    }
}
