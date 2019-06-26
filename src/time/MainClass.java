package time;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
   public static void main(String [] args){
       System.out.println("Program started...");
       LocalDateTime localDateTime = LocalDateTime.now();
       System.out.println(localDateTime);

       LocalDate localDate = localDateTime.toLocalDate();
       System.out.println(localDate);


       LocalDateTime date2 = localDateTime.withDayOfMonth(10).withYear(2012);
       System.out.println("date2: " + date2);


       LocalTime localTime = LocalTime.parse("20:20:20");
       System.out.println(localTime);

       System.out.println("####################################################3");
       ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Tehran]");
       System.out.println("date1: " + date1);

       ZoneId id = ZoneId.of("Europe/Paris");
       System.out.println("ZoneId: " + id);

       ZoneId currentZone = ZoneId.systemDefault();
       System.out.println("CurrentZone: " + currentZone);
   }
}
