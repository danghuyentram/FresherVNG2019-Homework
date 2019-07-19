# ChatApp
## Giới thiệu
Thiết kế schema cho chương trình chat - trò chuyện (với Redis và với MySQL), sử dụng Python để tương tác.
Mô tả chương trình chat: cơ chế tương tự Zalo, Message.

1. Cơ bản:
   - Tạo tài khoản (username/password, email,...)
   - Chọn người trò chuyện (theo username hoặc email)
   - Hiển thị lịch sử trò chuyện (nếu có)
   - Trò chuyện (chat)
  
2. Nâng cao:
    - Kết bạn
    - Chat trong nhóm
    - Hiển thị trạng thái online/offline của người khác

3. Hướng dẫn
   - Thiết kế hệ thống tài khoản
   - Thiết kế cấu trúc lưu trữ lịch sử chat
   - Thiết kế cấu trúc lưu trữ nội dung tin nhắn

## Schema

### Mysql

### Redis


## Cài đặt

### Handle timestart
Với thời gian bắt đầu, chuyển nó về khung giờ làm việc gần nhất

```
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
```

### Calculate
Tính thời gian làm việc giữa begin và end
- Nếu begin và end cùng 1 ngày
  - Nếu begin vào buổi sáng và end vào buổi chiều: thì thời gian giữa begin tới thời gian kết thúc làm buổi sáng + thời gian bắt đầu làm buổi chiều tới end
  - Nếu chung 1 buổi thì tính thời gian giữa begin và end
- Nếu begin và end khác ngày:
  - Tính thời gian làm từ begin tới hết ngày đó
  - Tính thời gian từ lúc bắt đầu làm vào ngày end tới end
  - Tính thời gian mỗi ngày làm việc sau ngày begin tới trước ngày end

```
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
                time_work = Duration.between(time_start_work_afternoon, time_finish).plus(time_work_morning);
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

        System.out.println(time_work.toString());
        return Duration.ofMinutes(time_work.toMinutes());
    }
```

## Chạy và kiểm tra
Kiểm tra với 1 số testcase

```
 {
                        LocalDateTime.of(2019, 4, 30, 10, 0),
                        LocalDateTime.of(2019, 4, 30, 11, 0),
                        Duration.ofSeconds(3600) // 1h
                },
                {
                        LocalDateTime.of(2019, 4, 30, 11, 30),
                        LocalDateTime.of(2019, 4, 30, 14, 0),
                        Duration.ofSeconds(3600) // 1h
                },

                {
                        LocalDateTime.of(2019, 7, 5, 12, 00),
                        LocalDateTime.of(2019, 7, 6, 9, 0),
                        Duration.ofMinutes(300) // 5h
                },
                {
                        LocalDateTime.of(2019, 7, 6, 12, 00),
                        LocalDateTime.of(2019, 7, 8, 9, 0),
                        Duration.ofMinutes(30) // 0.5h
                },
                {
                        LocalDateTime.of(2019, 7, 6, 12, 00),
                        LocalDateTime.of(2019, 7, 10, 9, 0),
                        Duration.ofMinutes(990) // 16.5h
                },
                {
                        LocalDateTime.of(2019, 6, 29, 12, 00),
                        LocalDateTime.of(2019, 7, 10, 9, 0),
                        Duration.ofMinutes(3600) // 60h
                },
```

Output

![](../capture-screen/cap22.png)