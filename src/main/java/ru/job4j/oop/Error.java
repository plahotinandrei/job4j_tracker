package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("active: " + active);
        System.out.println("status: " + status);
        System.out.println("message: " + message);
    }

    public static void main(String[] args) {
        Error errorDefault = new Error();
        errorDefault.printInfo();
        Error error404 = new Error(true, 404, "not found");
        error404.printInfo();
        Error error503 = new Error(true, 503, "service unavailable");
        error503.printInfo();
    }
}
