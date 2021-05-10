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
        for (int i = 0; i < books.length; i++) {
            System.out.println("Book name: " + books[i].getName());
            System.out.println("pages: " + books[i].getCountPages());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            System.out.println("Book name: " + books[i].getName());
            System.out.println("pages: " + books[i].getCountPages());
        }
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals("Clean Code")) {
                System.out.println("Book name: " + books[i].getName());
                System.out.println("pages: " + books[i].getCountPages());
                break;
            }
        }
    }
}
