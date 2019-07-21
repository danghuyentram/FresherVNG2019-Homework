import mysql.connector
import datetime
from database import *



class mysqldb(database):
    def __init__(self):
        self.connector = mysql.connector.connect(user='root', database='chatApp')
        self.cursor = self.connector.cursor(dictionary=True)

    def addMessage(self,roomName,userName,content):
      query =('select * from chatMember join chatRoom on chatMember.idRoom=chatRoom.idRoom where userName like "%s" and chatRoom.roomName like "%s"') %(userName,roomName)

      self.cursor.execute(query)
      for id in self.cursor:
        idChatMem = id['idChatMem']

      query =("insert into chatMessage (idChatMem,content) values(%d,'%s')") %(idChatMem,content)
      self.cursor.execute(query)
      self.connector.commit()


    def loadOldMessage(self,roomName):
      query = 'select chatMember.userName, chatRoom.roomName, chatMessage.content from (chatMessage join chatMember on chatMessage.idChatMem = chatMember.idChatMem) join chatRoom on chatMember.idRoom = chatRoom.idRoom where chatRoom.roomName like "%s" ORDER BY chatMessage.idChatMess ASC' %roomName
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return result



    def getFriendByUserName(self,userName):
      query = 'select friendName, isOnline from friendList where userName like "%s"' %(userName)
      self.cursor.execute(query)

      result = self.cursor.fetchall()
      return result


    def getRoomInforByUserName(self,userName):
        query = 'select * from chatRoom join chatMember on chatRoom.idRoom = chatMember.idRoom where chatMember.userName like "%s"' %userName
        cursor.execute(query)
        result = cursor.fetchall()
        return result

    def checkUserPassword(self,userName,password):
      query = 'select count(*) from userInfor where userName like "%s" and userPassword  like "%s"' %(userName,password)
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      if(result[0]["count(*)"] ==1):
        return True
      else:
        return False



    def isOffline(self,userName):
      query = 'update friendList set isOnline = false where friendName like "%s"' %userName
      self.cursor.execute(query)
      self.connector.commit()


    def isOnline(self,userName):
      query = 'update friendList set isOnline = true where friendName like "%s"' %userName
      self.cursor.execute(query)
      self.connector.commit()


    def createUserAccount(self,userName,userPassword,dob,email):
      query = 'insert into userInfor (userName,userPassword,dob,email) values("%s","%s","%s","%s")' %(userName,userPassword,dob,email)
      cursor.execute(query)
      self.connector.commit()


    def createChatRoom(self,roomName):
      # check exists room
      query = 'select count(*) from chatRoom where roomName like "%s"' %roomName
      self.cursor.execute(query)
      result = self.cursor.fetchall()

      if(result[0]["count(*)"]==0):
        now = datetime.datetime.now()
        formatDate = now.strftime('%Y-%m-%d')

        query = 'insert into chatRoom (roomName,dateCreate) values(%s,%s)'
        self.cursor.execute(query,(roomName,formatDate))
        self.connector.commit()

    def createChatMember(self,roomName,userName):

      # check exists chatmember
      query = ' select count(*) from chatMember join chatRoom on chatMember.idRoom = chatRoom.idRoom where chatRoom.roomName like "%s" and chatMember.userName like "%s"'  %(roomName,userName)
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      if (result[0]["count(*)"]==0):
        idRoom = getIdRoomByRoomName(roomName)

        query = 'insert into chatMember (idRoom,userName) values(%d,"%s")' %(idRoom,userName)
        self.cursor.execute(query)
        self.connector.commit()


    def findUserByUserNameOrEmail(self,condition):
      condition = "%"+condition+"%"
      if "@" in condition:
        query = 'select userName from userInfor where email like "%s"' %(condition)
      else:
        query = 'select userName from userInfor where userName like "%s"' %(condition)

      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return (result)



    def addFriend(self,userName,friendName):
      query = 'insert into friendList (userName,friendName,isOnline) values("%s","%s",%d)' %(userName,friendName,0)
      print(query)
      self.cursor.execute(query)
      self.connector.commit()



    def getRoomNameByUserName(self,userName):
      query = 'select chatRoom.roomName from chatRoom join chatMember on chatRoom.idRoom = chatMember.idRoom where chatMember.userName like "%s"' %userName
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return result


#****
    def getIdRoomByRoomName(roomName):
      query = 'select idRoom from chatRoom where roomName like "%s"'  %roomName
      cursor.execute(query)
      result = cursor.fetchall()

      return result[0]["idRoom"]


    def createUserInfor(userName,userPassword,dob,email):
      formatDate = dob.strftime('%Y-%m-%d')
      query = 'insert into userInfor (userName,userPassword,dob,email) values("%s","%s","%s","%s")'
      self.cursor.execute(query,(userName,userPassword,formatDate,email))
      self.connector.commit()




    def getUsersByIdRoom(idRoom):
      query = 'select * from chatMember where idRoom = %d' %idRoom
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return result

    def getIdRoomByRoomNameAndUserName(roomName,userName):
      query = 'select chatRoom.idRoom,chatRoom.numOfMenber  from chatRoom join chatMember  on chatRoom.idRoom = chatMember.idRoom where chatRoom.roomName like "%s" and chatMember.userName like "%s";' %(roomName,userName)
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return result[0]

    def getIdChatMemByRoomNameAndUserName(roomName,userName):
      query = 'select idChatMem from chatMember join chatRoom on chatMember.idRoom = chatRoom.idRoom where chatRoom.roomName like "%s"  and chatMember.userName like "%s"' %(roomName,userName)
      self.cursor.execute(query)
      result = self.cursor.fetchall()
      return (result[0]["idChatMem"])


# *****
# mysqldb = mysqldb()
# print(mysqldb.getRoomNameByUserName("danghuyentram"))
# print(mysqldb.loadOldMessage("got7"))

# print(mysqldb.findUserByUserNameOrEmail("n"))

# print(findUserByUserNameOrEmail(connector,cursor,"n@"))
#print(createChatRoom(connector,cursor,"ahgase"))

#print(getIdRoomByRoomNameAndUserName(connector,cursor,"abc","danghuyentram"))
# print(getUsersByIdRoom(connector,cursor,1))

# k = getRoomInforByUserName(connector,cursor,"danghuyentram")
# print(k[0]["roomName"])

# print(getRoomInforByUserName("danghuyentram"))
#createChatRoom("got7")
#isOnline("danghuyentram")

#addFriend("trandt","sonph")

#addMessage("got7","danghuyentram","i love got7 so much")

#cursor.execute("select * from chatMessage")

# connector.close()
