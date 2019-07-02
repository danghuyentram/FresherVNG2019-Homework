

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by thinhda.
 * Date: 2019-04-15
 */

public class SlaServiceImpl implements SlaService {

    public LocalDateTime handleBeginTime(LocalDateTime begin){
        LocalDateTime time_start_work_morning =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,8,30);
        LocalDateTime time_finish_work_morning =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,12,0);
        LocalDateTime time_start_work_afternoon =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,13,30);
        LocalDateTime time_finish_work_afternoon =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,18,0);

        LocalDateTime time_start=LocalDateTime.now();


        // if sunday -> wait to 8h30 monday start
        if(begin.getDayOfWeek()== DayOfWeek.SUNDAY){
            time_start = LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,8,30);
        }
        else if(begin.isBefore(time_start_work_morning)){
            // send before 8h30  -> wait to 8h30 in day start
            time_start = time_start_work_morning;
        }
        else if(!begin.isBefore(time_start_work_morning) && begin.isBefore(time_finish_work_morning)){
            // send in 8h30 -> 12h : not wait
            time_start = begin;
        }
        else if(!begin.isBefore(time_finish_work_morning) ){
            // send after 12h

            if(begin.getDayOfWeek()== DayOfWeek.SATURDAY){
                // in saturday -> wait to 8h30 monday
                time_start = LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+2,8,30);
            }
            else{
                // in monday to friday
                if(begin.isBefore(time_start_work_afternoon)){
                    // send before 13h30 -> wait to 13h30
                    time_start = time_start_work_afternoon;
                }
                else if(!begin.isBefore(time_start_work_morning) && begin.isBefore(time_finish_work_afternoon) ){
                    // send in 13h30-> 18h : not wait
                    time_start=begin;
                }
                else if(!begin.isBefore(time_finish_work_afternoon) ){
                    // send after 18h: wait to tomorrow
                    time_start = LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,8,30);
                }
            }
        }

        return time_start;
    }

    @Override
    public Duration calculate(LocalDateTime begin, LocalDateTime end) {
        LocalDateTime time_start_work_morning =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,8,30);
        LocalDateTime time_finish_work_morning =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,12,0);
        LocalDateTime time_start_work_afternoon =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,13,30);
        LocalDateTime time_finish_work_afternoon =LocalDateTime.of(begin.getYear(),begin.getMonth(),begin.getDayOfMonth()+1,18,0);

        Duration time_work;

        begin = handleBeginTime(begin);

        if(begin.getYear()==end.getYear() && begin.getMonth()==end.getMonth() && begin.getDayOfMonth()== begin.getDayOfMonth()){
            // begin and end in 1 day
            if(begin.isBefore(time_finish_work_morning) && end.isBefore(time_start_work_afternoon)){
                // begin and end before 13h30
                time_work= Duration.between(begin,end);
            }
            else{
                // begin in 8h30->12h and end after 13h30
                time_work=Duration.between(begin,time_finish_work_morning).plus(Duration.between(time_start_work_afternoon,end));
            }
        }
        else{
            // begin and end in different day
            if(begin.isBefore(time_finish_work_morning)){
                // begin in morning

            }
        }

        return Duration.ofHours(1);
    }
}


