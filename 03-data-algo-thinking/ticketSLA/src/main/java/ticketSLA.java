import java.time.LocalDateTime;


public class ticketSLA {
    LocalDateTime time_start;
    LocalDateTime time_finish;

    public ticketSLA(LocalDateTime time_start,LocalDateTime time_finish){
        this.time_start = time_start;
        this.time_finish = time_finish;
    }
}
