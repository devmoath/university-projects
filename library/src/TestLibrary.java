import java.util.*;

public class TestLibrary {
    public static void main(String[] args) {
        Library B = new Library();
        Scanner s = new Scanner(System.in);
        System.out.println("**********************************************************************");
        System.out.println("*                       Welcome to KSU Library :)                     ");
        System.out.println("*                       ---------------------------                   ");
        System.out.println("*      Please enter one of the following option:                      ");
        System.out.println("*      1) Add a book                                                  ");
        System.out.println("*      2) Delete a book                                               ");
        System.out.println("*      3) Find a book                                                 ");
        System.out.println("*      4) List all books                                              ");
        System.out.println("*      5) List books for a given genre                                ");
        System.out.println("*      6) List books for a given author                               ");
        System.out.println("*      7) Total number of books                                       ");
        System.out.println("*      8) List books for a given edition                              ");
        System.out.println("*      9) Exit                                                        ");
        System.out.println("*                                                                     ");
        System.out.println("**********************************************************************");
        System.out.print("Enter your option :> ");
        for (int d = s.nextInt(); d != 9 && d < 10 && d > 0; ) {
            int a = d;
            switch (a) {
                case 1:
                    System.out.print("Please, enter the book details #ISBN, author, title, genre, publisher and edition :");
                    B.addBook(s.nextInt(), s.next(), s.next(), s.next(), s.next(), s.nextInt());
                    break;
                case 2:
                    System.out.print("Enter ISBN: ");
                    B.deleteBook(s.nextInt());
                    break;
                case 3:
                    System.out.println("Enter ISBN: ");
                    B.findBook(s.nextInt());
                    break;
                case 4:
                    B.printAll();
                    break;
                case 5:
                    System.out.print("Enter genre: ");
                    B.printGenre(s.next());
                    break;
                case 6:
                    System.out.print("Enter author: ");
                    B.getNumberOfBooksByAuthor(s.next());
                    break;
                case 7:
                    B.getNumberOfBooks();
                    break;
                case 8:
                    System.out.println("Enter edition: ");
                    B.printBookBaseOnEdition(s.nextInt());
                    break;
            }
            System.out.println("**********************************************************************");
            System.out.println("*                       Welcome to KSU Library :)                     ");
            System.out.println("*                       ---------------------------                   ");
            System.out.println("*      Please enter one of the following option:                      ");
            System.out.println("*      1) Add a book                                                  ");
            System.out.println("*      2) Delete a book                                               ");
            System.out.println("*      3) Find a book                                                 ");
            System.out.println("*      4) List all books                                              ");
            System.out.println("*      5) List books for a given genre                                ");
            System.out.println("*      6) List books for a given author                               ");
            System.out.println("*      7) Total number of books                                       ");
            System.out.println("*      8) List books for a given edition                              ");
            System.out.println("*      9) Exit                                                        ");
            System.out.println("*                                                                     ");
            System.out.println("**********************************************************************");
            System.out.print("Enter your option :> ");
            d = s.nextInt();
        }
        System.out.println("Thanks. Goodbye!");
    }
}