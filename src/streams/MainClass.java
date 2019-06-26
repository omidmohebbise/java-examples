package streams;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    List<String> persons = new ArrayList<>();
    public static void main(String[] args){
        System.out.println("Program starting...");
        MainClass me = new MainClass();
        //me.filterList();
        //me.anyMatch();
        //me.distinct();
        //me.sum();
        //me.avarage();
        //me.collect();
        //me.mapDistinctCollect();

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

//get count of empty string
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();

        System.out.println(count);
        //System.out.println(    me.initPersons().stream().findFirst().toString() );

        /*me.initIntList().stream().map(new Function<Integer, Object>() {
            @Override
            public <V> Function<V, Object> compose(Function<? super V, ? extends Integer> before) {
                return null;
            }

            @Override
            public <V> Function<Integer, V> andThen(Function<? super Object, ? extends V> after) {
                return null;
            }

            @Override
            public Object apply(Integer integer) {
                return null;
            }
        });*/


    }
    public List<Integer> mapDistinctCollect(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());

        squaresList.stream().forEach(s-> {
            System.out.println(s);
        });
        return  squaresList;
    }
    public List<String> collect(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(s->{
            System.out.println(s);
        });
        return filtered;
    }
    public void avarage(){
        OptionalDouble sum  = Stream.of(25, 14, 1).mapToInt(i -> i).average();
        System.out.println(sum);
    }

    public void sum(){
        int sum  = Stream.of(25, 14, 1).mapToInt(i -> i).sum();
        System.out.println(sum);
    }
    public List<Integer> initIntList(){
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(112);
        ints.add(12);
        ints.add(13);
        ints.add(2);
        ints.add(3);
        ints.add(66);
        ints.add(3);
        ints.add(3);
        return ints;

    }
    public  List<String> initPersons(){

        persons.add("omid");
        persons.add("ali");
        persons.add("sara");
        persons.add("amir");
        persons.add("saeed");
        persons.add("hadi");
        persons.add("hadi");
        persons.add("hadi");



        return persons;
    }

    public void filterList(){
        initPersons();
        // 1
        persons.stream().filter(
                new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        if(s.contains("mi"))
                            return true;
                        else
                            return false;

                    }
                }).forEach(ss-> {System.out.print(ss);
            System.out.println();});


        // 2
        System.out.println("--------------------");
        persons.stream().filter(tt-> {
            if(tt.contains("mi"))
                return true;
            else
                return false;
        }).forEach(ss-> {System.out.print(ss);
            System.out.println();});
    }

    public boolean anyMatch() {
        System.out.println("--------------------");
        boolean resutl = initPersons().stream().anyMatch(tt -> {
            if (tt.compareTo("omid") == 0)
                return true;
            else
                return false;
        });
    if(resutl)
        System.out.println("Item found");
    return resutl;
    }

    public void distinct(){
        initPersons().stream().distinct().forEach(t-> System.out.println(t));
    }
}
