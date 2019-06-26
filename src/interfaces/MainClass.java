package interfaces;

public class MainClass {

    public static void main(String[] args){
        MyInterface.printHelloWorld();


        new Thread(() -> {

            while(true) {
                System.out.println("Hello");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();



        new Thread(()-> {
            System.out.println("ssdsds");
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(new MyRunnable() {
            @Override
            public void printSth() {

            }

            @Override
            public void run() {

            }
        });
    }
    @FunctionalInterface
    public interface MyRunnable extends Runnable{
        default public void printSth(){

        }
    }



}
