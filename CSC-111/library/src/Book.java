import java.util.Scanner;

public class Book {

    private int ISBN, edition;
    private String author, publisher, title, genre, refCode;
    Scanner s = new Scanner(System.in);

    public Book() {}

    public Book(int ISBN, String author, String title, String genre, String publisher, int edition) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.edition = edition;
    }

    public void SetISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void Setauthor(String author) {
        this.author = author;
    }

    public void Settitle(String title) {
        this.title = title;
    }

    public void Setgenre(String genre) {
        this.genre = genre;
    }

    public int GetISBN() {
        return ISBN;
    }

    public String Getauthor() {
        return author;
    }

    public String Gettitle() {
        return title;
    }

    public String Getgenre() {
        return genre;
    }

    public void generateReference() {
        this.refCode =
            String.valueOf(author.charAt(0)) +
            String.valueOf(author.charAt(1)) +
            "-" +
            String.valueOf(genre.charAt(0)) +
            String.valueOf(genre.charAt(1));
        SetRefCode(refCode);
    }

    public void printBookInfo() {
        System.out.println(
            "Title:" +
            Gettitle() +
            "\n" +
            "Author:" +
            Getauthor() +
            "\n" +
            "ISBN: " +
            GetISBN() +
            " - Reference Code :" +
            GetRefCode() +
            "\n" +
            "Genre:" +
            Getgenre()
        );
    }

    public void equals(Book WWW) {
        if (
            this.ISBN == WWW.ISBN &&
            this.author == WWW.author &&
            this.genre == WWW.genre &&
            this.title == WWW.title &&
            this.publisher == WWW.publisher &&
            this.edition == WWW.edition
        ) {
            System.out.println("Are equels");
        } else {
            System.out.println("Not equals");
        }
    }

    public int GetEdition() {
        return edition;
    }

    public void SetEdition(int edition) {
        this.edition = edition;
    }

    public String GetPublisher() {
        return publisher;
    }

    public void SetPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void SetRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String GetRefCode() {
        return refCode;
    }
}
