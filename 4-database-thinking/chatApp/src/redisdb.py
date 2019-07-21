from database import *
import redis
import json
from collections.abc import MutableMapping
import random
import datetime
import uuid
import collections



class redisdb(database):
    def __init__(self):
        self.r = redis.Redis()

    def getAll(self,key):
        # print(self.r.keys())
        for u in self.r.scan_iter(match="%s:*" %key):
            print(self.r.hgetall(u))

    def decodeDict(self,dictArray):
        result = []
        for d in dictArray:
            dic = {}
            for item in d:
                dic[item.decode()] = d[item].decode()
            result.append(dic)
        return result

    def setToRedis(self,key,obj):
        with self.r.pipeline() as pipe:
            print(key+ " "+json.dumps(obj))
            pipe.hmset(key,obj)
            pipe.execute()
        # self.r.bgsave()


    def createUserAccount(self,userName,userPassword,dob,email):
        key = "userInfor:"+userName
        user = {
            "userPassword" : userPassword,
            "dob" : dob,
            "email" : email
        }
        self.setToRedis(key,user)

    def addFriend(self,userName,friendName):
        key = "friendList:" + str(uuid.uuid1())
        friendList = {
            "userName" : userName,
            "friendName" : friendName,
            "isOnline" : 0
        }
        self.setToRedis(key,friendList)


    def createChatRoom(self,roomName):
        now = datetime.datetime.now()
        formatDate = now.strftime('%Y-%m-%d')
        key = "chatRoom:" + roomName
        chatRoom = {
            "dateCreate" : formatDate
        }
        self.setToRedis(key,chatRoom)


    def createChatMember(self,roomName,userName):
        key = "chatMember:" + roomName +":" + userName
        chatMember = {
            "roomName" : roomName,
            "userName" :  userName
        }
        self.setToRedis(key,chatMember)


    def addMessage(self,roomName,userName,content):
        now = datetime.datetime.now()
        key = "chatMessage:" + roomName + ":" + str(now)
        chatMessage = {
            "userName" : userName,
            # "roomName" :roomName,
            "content" : content,
        }
        self.setToRedis(key,chatMessage)


    def isOffline(self,userName):
        for friend in self.r.scan_iter(match="friendList:*"):
            if(self.r.hget(friend,"friendName").decode() in userName):
                self.r.hset(friend,"isOnline",0)
                print(self.r.hgetall(friend))



    def isOnline(self,userName):
         for friend in self.r.scan_iter(match="friendList:*"):
            if(self.r.hget(friend,"friendName").decode() in userName):
                self.r.hset(friend,"isOnline",1)



    def loadOldMessage(self,roomName):
        messDict  = {}
        for mess in self.r.scan_iter(match = "chatMessage:*"):
              # if(self.r.hget(mess,"roomName").decode() in roomName):
                if(mess.decode().split(":")[1] in roomName):
                    messDict[mess]=  self.r.hgetall(mess)

        orderMessDict = collections.OrderedDict(sorted(messDict.items()))

        result = []
        for mess in orderMessDict:
            result.append((orderMessDict[mess]))
        result = self.decodeDict(result)
        for mess in result:
            mess["roomName"] = roomName

        return result



    def getRoomNameByUserName(self,userName):
        result = []
        for chatMem in self.r.scan_iter(match = "chatMember:*"):
            if(self.r.hget(chatMem,"userName").decode() in userName):
               result.append({"roomName" : self.r.hget(chatMem,"roomName").decode()})
        return  result




    def getFriendByUserName(self,userName):
        friendList = []
        for friend in self.r.scan_iter(match= "friendList:*"):
            if(self.r.hget(friend,"userName").decode() in userName):
                friendList.append(self.r.hgetall(friend))
        return self.decodeDict( friendList)



    def checkUserPassword(self,userName,password):
        for user in self.r.scan_iter(match = "userInfor:*"):
            if(self.r.hget(user,"userPassword").decode() == password and userName in user.decode()):
                  return True
        return  False



    def findUserByUserNameOrEmail(self,condition):
        condition = condition
        result = []
        if "@" in condition:
            for user in self.r.scan_iter(match= "userInfor:*"):
                if(self.r.hget(user,"email").decode() in condition):
                    result.append(user.split(":")[1].decode())

        else:
            for user in self.r.scan_iter(match= "userInfor:*"):
                if(condition in user.decode().split(":")[1] ):
                    result.append({"userInfor":user.decode().split(":")[1]})

        return result



# #
# #
# redisdb  = redisdb()
# # print(redisdb.loadOldMessage("danghuyentram-trandt"))
# # print(redisdb.findUserByUserNameOrEmail("n"))
#
# # print(redisdb.getFriendByUserName("danghuyentram"))
# # print(redisdb.getRoomNameByUserName("danghuyentram"))
# # print( redisdb.checkUserPassword("danghuyentram","abc"))
#
#
#
# redisdb.createUserAccount("danghuyentram","abc","1998-07-04","dht@gmail.com")
# redisdb.createUserAccount("trandt","abc","1998-07-04","dht@gmail.com")
# redisdb.createUserAccount("sonph","xyz","1998-07-04","sonph@gmail.com")
#
# redisdb.getAll("userName")
#
#
# redisdb.addFriend("danghuyentram","trandt")
# redisdb.addFriend("danghuyentram","sonph")
# redisdb.addFriend("trandt","danghuyentram")
# redisdb.addFriend("sonph","danghuyentram")
#
# redisdb.getAll("friendList")
#
# # redisdb.isOnline("danghuyentram")
# # redisdb.isOffline("danghuyentram")
#
# # redisdb.getAll("friendList")
#
# redisdb.createChatRoom("abc")
# redisdb.createChatRoom("got7")
# redisdb.getAll("roomName")
#
# #
# redisdb.addMessage("abc","danghuyentram","hi")
# redisdb.addMessage("abc","trandt","haha")
#
# redisdb.addMessage("abc","danghuyentram","fgdf")
# redisdb.getAll("chatMessage")
#
# # redisdb.loadOldMessage("abc")
#
# redisdb.createChatMember("abc","danghuyentram")
# redisdb.createChatMember("abc","trandt")
# redisdb.createChatMember("got7","danghuyentram")
# redisdb.createChatMember("got7","sonph")

#
# redisdb.getAll("chatMember")
