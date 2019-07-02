package chatroom;

import java.io.Serializable;
import java.util.Date;

public class MessageContent implements Serializable {
    String channel, name, mess;
    Date date;

    public MessageContent(String channel, String name, String mess, Date date){
        this.channel = channel;
        this.name = name;
        this.mess = mess;
        this.date = date;
    }

    public String getMessageContent(String name){
        if (this.name.equals(name))
            return "["+date.toString() +"] You: "+mess;
        else
            return "["+date.toString() + "] "+this.name+ ": "+mess;

    }

    public String getChannel() {
        return channel;
    }

    public String getMess() {
        return mess;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setChannel(String channel){
        this.channel=channel;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setMess(String mess){
        this.mess=mess;
    }

    public void setDate(Date date){
        this.date=date;
    }


}
