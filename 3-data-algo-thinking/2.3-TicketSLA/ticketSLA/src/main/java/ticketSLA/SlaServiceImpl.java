package ticketSLA;

import java.time.*;

/**
 * Created by thinhda.
 * Date: 2019-04-15
 */

public class SlaServiceImpl implements SlaService {

    static LocalTime time_start_work_morning = LocalTime.of(8,30);
    static LocalTime time_finish_work_morning =LocalTime.of(12,0);
    static LocalTime time_start_work_afternoon =LocalTime.of(13,30);
    static LocalTime time_finish_work_afternoon =LocalTime.of(18,0);


    public LocalDateTime handleBeginTime(LocalDateTime begin){


        LocalTime time_begin = begin.toLocalTime();

        switch (begin.getDayOfWeek()){
            case SUNDAY:{
                begin = begin.plusDays(1);
                break;
            }
            case SATURDAY:{
                // before 8h30 -> wait to 8h30
                if(time_begin.isBefore(time_start_work_morning)){
                    begin.withHour(time_start_work_morning.getHour());
                    begin.withMinute(time_start_work_morning.getMinute());
                }
                else // after 12h -> wait to 8h30 next monday
                    if(time_begin.isAfter(time_finish_work_morning)){

                        begin.withHour(time_start_work_morning.getHour());
                        begin.withMinute(time_start_work_morning.getMinute());
                        begin.plusDays(2);
                    }
                    break;
            }


            default:{
                // before 8h30 -> wait to 8h30
                if(time_begin.isBefore(time_start_work_morning)){
                    begin.withHour(time_start_work_morning.getHour());
                    begin.withMinute(time_start_work_morning.getMinute());
                }
                else // after 12h -> wait to 13h30
                    if(time_begin.isAfter(time_finish_work_morning)){

                        begin.withHour(time_finish_work_afternoon.getHour());
                        begin.withMinute(time_start_work_afternoon.getMinute());
                    }
                    else // after 18h -> wait to 8h30 next day
                        if(time_begin.isAfter(time_finish_work_afternoon)){
                        begin.withHour(time_start_work_morning.getHour());
                        begin.withMinute(time_start_work_morning.getMinute());
                        begin.plusDays(1);
                    }
                        break;
            }
        }


        return begin;
    }

    @Override
    public Duration calculate(LocalDateTime begin, LocalDateTime end) {


        Duration time_work_morning = Duration.between(time_start_work_morning,time_finish_work_morning);
        Duration time_work_afternoon = Duration.between(time_start_work_afternoon,time_finish_work_afternoon);
        Duration time_work_day = time_work_morning.plus(time_work_afternoon);

        LocalTime time_begin = LocalTime.of(begin.getHour(), begin.getMinute());
        LocalTime time_finish = LocalTime.of(end.getHour(),end.getMinute());


        begin = handleBeginTime(begin);
        Duration time_work;



        // begin and finish in 1 day
        if(begin.toLocalDate().equals(end.toLocalDate())){

            // begin in morning finish in afternoon
            if(!time_begin.isAfter(time_finish_work_morning) && time_finish.isAfter(time_start_work_afternoon)){
                time_work = Duration.between(time_begin,time_finish_work_morning).plus(Duration.between(time_start_work_afternoon,time_finish));
            }
            // begin and finish in morning or afternoon
            else{
                time_work = Duration.between(time_begin,time_finish);
            }

        }
        else {
            // begin and end in different day

            // begin in morning
            if (!time_begin.isAfter(time_finish_work_morning)) {
                if(begin.getDayOfWeek()!=DayOfWeek.SATURDAY){
                    time_work = Duration.between(time_begin, time_finish_work_morning).plus(time_work_afternoon);
                }
                else{
                    time_work = Duration.between(time_begin, time_finish_work_morning);
                }

            }
            // begin in afternoon
            else {
                    time_work = Duration.between(time_begin, time_finish_work_afternoon);

            }


            // finish in morning
            if (time_finish.isBefore(time_finish_work_morning)) {
                time_work = Duration.between(time_start_work_morning, time_finish).plus(time_work);
            }
            //finish in afternoon
            else {
                time_work = Duration.between(time_start_work_afternoon, time_finish).plus(time_work_morning).plus(time_work);
            }


            // calculate time between begin and end
            LocalDate date = begin.toLocalDate().plusDays(1);



            while(date.isBefore(end.toLocalDate())){

                switch (date.getDayOfWeek()){
                    case SATURDAY: {
                        time_work = time_work.plus(time_work_morning);
                        break;
                    }
                    case SUNDAY:{

                        break;

                    }
                    default:
                        time_work = time_work.plus(time_work_day);
                        break;

                }

                date = date.plusDays(1);


            }

        }

        //System.out.println(time_work.toString());
        return Duration.ofMinutes(time_work.toMinutes());
    }
}


