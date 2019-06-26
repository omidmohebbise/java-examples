package foreach;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainClass {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("omid");
        list.add("ali");
        list.add("hamid");
        list.add("hossein");
        list.add("hamed");
        list.add("saeed");
        list.add("sara");

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.print("*");
            }
        }.andThen(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.print(" ------------------->");
            }
        }.andThen(new Consumer<String>() {
            @Override
            public void accept(String s) {

                System.out.println(s);
            }
        })));
    }
}
