public class Main {
    private static boolean running = false;

    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            while (!running) {
            }
            System.out.println("Hi");
        };

        Runnable runnable2 = () -> {
            running = true;
            System.out.println("Hello");
        };

        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }
}