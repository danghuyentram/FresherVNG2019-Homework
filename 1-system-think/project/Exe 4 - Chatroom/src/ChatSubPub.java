package chatroom;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;





public class ChatSubPub {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String[] color = {"\u001B[32m","\u001B[33m","\u001B[34m","\u001B[35m","\u001B[37m","\u001B[36m"};




    RedissonClient client;
    RList<MessageContent> listmess;
    String name;


    public ChatSubPub() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);

    }


    public void sub(String channel) {
        listmess = client.getList(channel);

        RTopic subTopic = client.getTopic(channel);
        subTopic.addListener(MessageContent.class, new MessageListener<MessageContent>() {
            public void onMessage(CharSequence charSequence, MessageContent messageContent) {
                System.out.println(color[messageContent.getName().length()] +  messageContent.getMessageContent(name) + ANSI_RESET);
            }
        });
    }

    public void getOldMessage(String channel,String name){
        System.out.println("Old mess");
        for (int i = 0; i < listmess.size(); i++)
            if (listmess.get(i).channel.equals(channel))
            {
                System.out.println(listmess.get(i).getMessageContent(name));

            }
    }



    public void pub() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your channel");
        String channel = scanner.nextLine();

        sub(channel);

        System.out.println("Enter your name: ");
        name = scanner.nextLine();

        System.out.println("Welcome to "+ channel+"\n");


        getOldMessage(channel,name);


        RTopic pubTopic = client.getTopic(channel);
        System.out.println("Enter your message: ");

        while (true) {
            String content = scanner.nextLine();
            Date date = new Date();
            MessageContent messageContent = new MessageContent(channel, name, content, date);
            listmess.add(messageContent);
            listmess.expire(1, TimeUnit.DAYS);
            pubTopic.publish(messageContent);
        }

    }

}