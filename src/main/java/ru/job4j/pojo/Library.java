package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode = new Book("Clean Code", 600);
        Book thinkingInJava = new Book("Thinking in java", 943);
        Book headFirst = new Book("Head First", 451);
        Book theFinancier = new Book("The Financier", 511);
        Book[] books = new Book[4];
        books[0] = cleanCode;
        books[1] = thinkingInJava;
        books[2] = headFirst;
        books[3] = theFinancier;
        for (Book book:books) {
            System.out.println("Book name: " + book.getName());
            System.out.println("pages: " + book.getCountPages());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (Book book:books) {
            System.out.println("Book name: " + book.getName());
            System.out.println("pages: " + book.getCountPages());
        }
        for (Book book:books) {
            if ("Clean Code".equals(book.getName())) {
                System.out.println("Book name: " + book.getName());
                System.out.println("pages: " + book.getCountPages());
                break;
            }
        }
    }
}
