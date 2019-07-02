import java.time.LocalDateTime;


public class App {

    public static void main(String[] args){
        LocalDateTime time1 = LocalDateTime.of(2019,7,7,12,12);
        LocalDateTime time2 = LocalDateTime.of(2019,7,8,12,12);


        ticketSLA t1 = new ticketSLA(time1,time2);
        System.out.println(time1.getDayOfWeek());


    }
}
