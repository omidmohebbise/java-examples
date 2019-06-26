package interfaces;

@FunctionalInterface
public interface MyFunctionalInterface {

    public void doSomething();
//    public void doSomethingelse();
    static public void printSomething(){
        System.out.println("Hiiiiiiii");
    }

    default public void printTest(){
        System.out.println("default method");
    }
}
