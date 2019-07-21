import database
import mysql.connector
import datetime
import mysqldb
from redisdb import *
import databaseFactory
import sys



READ_BUFFER = 2048

# connector = mysql.connector.connect(user='root', database='chatApp')
# cursor = connector.cursor(dictionary=True)



color = [ "\033[1;31m" , "\033[1;32m",  "\033[1;33m", "\033[1;34m","\033[1;35m", "\033[1;36m", "\033[0;37m",  "\033[1;38m", "\033[0;0m" ]


# database = mysqldb.mysqldb()
# database = redisdb()




#

class User:
    def __init__(self,socket=0,userName="new",userPassword=0,dob=0,email=0):
        socket.setblocking(0)
        self.socket = socket
        self.userName = userName
        self.userPassword = userPassword
        self.dob = dob
        self.email = email
        # self.idChatMem = idChatMem

    def fileno(self):
        return  self.socket.fileno()

class ChatRoom:

    def __init__(self,roomName):
        self.users = []  # list of socket of user
        self.roomName = roomName
        self.color = color[len(roomName)%9]


    def addNewUser(self,fromUser):
        msg = self.color +" "+ self.roomName + " : " + fromUser.userName + " joined to the conversation\n"
        for user in self.users:
            user.socket.sendall(msg.encode())


    def broadcast(self,fromUser,msg):

        database.addMessage(self.roomName,fromUser.userName,msg.split("\n")[0])
        if("-" in self.roomName):
            msg = self.color +" "+  fromUser.userName+ " send to you: "   + msg
        else:
            msg = self.color +" "+  fromUser.userName+ ": " + self.roomName + ":" + msg
        for user in self.users:
            user.socket.sendall(msg.encode())

    def loadOldMessOfRoom(self,user):
        user.socket.sendall(self.color.encode()  + b" \n ---------------- Chat room %s --------------: \n" %self.roomName.encode())
        oldMess = database.loadOldMessage(self.roomName)

        for mess in oldMess:
            msg =self.color.encode() +b" "+  mess["roomName"].encode() + b": " +  mess["userName"].encode() + b":" + mess["content"].encode() +b"\n"
            # for user in self.users:
            user.socket.sendall(msg)


    def removeUser(self,user):
        self.users.remove(user)
        # outMsg =self.color.encode() +" "+  user.userName.encode() +b" left the conversation\n"
        # self.broadcast(user,outMsg)


class ChatApp:
    manual =b'\033[1;33m\n ---------------- Manual --------------:\n'\
            + b'[<show list friend> ] to show your list friend\n' \
            + b'[<find> friendname or email ] to find friend with username or email\n' \
            + b'[<addfriend> friendname or email ] to add friend with username or email\n' \
            + b'[<chat> friendname or email ] to chat with friend by username or email\n' \
            + b'[<list>] to list all rooms\n'\
            + b'[<join> room_name] to join/create/switch to a room\n' \
            + b'[<manual>] to show instructions\n' \
            + b'[<quit>] to quit\n' \


    def chooseTypeOfDB(self):
        print("\033[1;33m\n ---------------- Choose type of DB --------------:\n [<mysql>]: to choose mysql db\n [<redis>]: to choose redis db\n")
        type = sys.stdin.readline()
        dbFactory = databaseFactory.dbFactory()
        database = dbFactory.getDB(type)
        return database


    def __init__(self):
        self.chatRooms = {} # dictionary {roomName: Room}
        self.chatMemberOfRoom = {} # dictionary {userName: roomName}
        self.database = self.chooseTypeOfDB()




    def addNewUser(self,newUser):
        newUser.socket.sendall(self.manual)

    def handleMsg(self,user,msg):

        print (user.userName + " say: " + msg)
        if( "login" in msg): # new connection
           self.login(user,msg)

        elif("signup" in msg):
            self.signup(user,msg)

        elif("join" in msg) or("chat" in msg): # user want to join room
            self.join(user,msg)

        elif("find" in msg):
            self.find(user,msg)

        elif("show list friend" in msg):
            self.showListFriend(user)

        elif("addfriend" in msg):
            self.addFriend(user,msg)

        elif ("list" in msg):
            self.listRoom(user)

        elif("manual" in msg):
            user.socket.sendall(self.manual)

        elif("quit" in msg):
            self.quit(user)

        else:
            if user.userName in self.chatMemberOfRoom:
                self.chatRooms[self.chatMemberOfRoom[user.userName]].broadcast(user,msg)



    def showListFriend(self,user):
        msg = "\n ---------------- List friend of you --------------\n"
        listFriend = self.database.getFriendByUserName(user.userName)

        for friend in listFriend:
            if friend["isOnline"]==1:
                msg += friend["friendName"] + " is online\n"
            else:
                msg += friend["friendName"] + " is offline\n"

        user.socket.sendall(msg.encode())


    def listRoom(self,user):
        # get list room chat by username
        listRoomInfor = self.database.getRoomNameByUserName(user.userName)

        listRoomName = "\n ---------------- List chat room of you ----------------\n"
        for roomName in listRoomInfor:
            listRoomName += roomName["roomName"] + "\n"
        user.socket.sendall(listRoomName.encode())

        if(len(self.chatRooms)==0): # have no chatroom
            msg = "\n ------ Don't have any chatRoom. Let create one of your own ---------\n"
            user.socket.sendall(msg.encode())
        else:
            msg = "\n---------------- List of current chat room ----------------\n"
            for chatroom in self.chatRooms:
                msg += chatroom + ": " + str(len(self.chatRooms[chatroom].users)) + " user(s)\n"
            user.socket.sendall(msg.encode())


    def joinToListRoomOfUser(self,user):
        # join to list old room
        userName = user.userName.split("\n")[0]
        listRoom = self.database.getRoomNameByUserName(userName)
        for room in listRoom:
            if not room["roomName"]  in self.chatRooms:
                newRoom  = ChatRoom(room["roomName"])
                self.chatRooms[room["roomName"]] = newRoom
            self.chatRooms[room["roomName"]].users.append(user)
            self.chatRooms[room["roomName"]].addNewUser(user)
            # self.chatMemberOfRoom[user.userName] = room["roomName"]


    def login(self,user,msg):
        userName = msg.split()[1]
        userName = userName.split("\n")[0]

        password = msg.split()[2]
        password = password.split("\n")[0]

        if (self.database.checkUserPassword(userName,password) == True):
            self.addNewUser(user)
            user.userName = userName
            user.userPassword = password
            # user.socket.sendall(self.manual)
            print ("New connection from user: ", user.userName)

            # change status to online
            self.database.isOnline(user.userName)

            self.joinToListRoomOfUser(user)


        else:
            user.socket.sendall(b"Username or password is not correct\n")
            self.removeUser(user)


    def signup(self,user,msg):
        userName = msg.split()[1]
        userName = userName.split("\n")[0]

        password = msg.split()[2]
        password = password.split("\n")[0]

        dob = msg.split()[3]
        dob = dob.split("\n")[0]
        dobdate = datetime.datetime.strptime(dob, '%Y-%m-%d')
        dobdate = dobdate.strftime('%Y-%m-%d')

        email = msg.split()[4]
        email = email.split("\n")[0]


        self.database.createUserAccount(userName,password,dobdate,email)
        self.addNewUser(user)


        # user.socket.sendall(self.manual)

    def join(self,user,msg):
        sameRoom = False
        if len(msg.split())>=2: # correct syntax
                control = msg.split()[0]
                if("join" in control):
                    roomName = msg.split()[1]
                else: # chat
                    friendName = msg.split()[1]
                    if(friendName<user.userName):
                        roomName = friendName+"-"+user.userName
                    elif(friendName>user.userName):
                        roomName = user.userName+"-"+friendName
                    else: # chat alone
                        roomName = user.userName

                    print(roomName)

                if(user.userName in self.chatMemberOfRoom): # user in a room and switch to other room
                    if(self.chatMemberOfRoom[user.userName] == roomName): # same current room
                        user.socket.sendall(b'You are already in room ' + roomName.encode())
                        sameRoom = True

                if not sameRoom:
                    if not roomName in self.chatRooms: # create new room
                        newRoom  = ChatRoom(roomName)
                        self.chatRooms[roomName] = newRoom
                        self.database.createChatRoom(roomName)

                    if not user in self.chatRooms[roomName].users: # switch to other room
                        self.chatRooms[roomName].users.append(user)
                        self.chatRooms[roomName].addNewUser(user)

                    if("chat" in control):
                        self.database.createChatMember(roomName,friendName)

                    self.database.createChatMember(roomName,user.userName)
                    self.chatMemberOfRoom[user.userName] = roomName
                    self.chatRooms[roomName].loadOldMessOfRoom(user)

        else:
                user.socket.sendall(self.manual)

    def find(self,user,msg):
        condition = msg.split()[1]
        friendlist = self.database.findUserByUserNameOrEmail(condition)

        result = b"\n ----------------Search result ----------------\n"
        for friend in friendlist:
            result += friend["userName"].encode()+ b"\n"

        user.socket.sendall(result)

    def addFriend(self,user,msg):
        friend = msg.split()[1]
        friend = friend.split("\n")[0]

        friendlist = self.database.findUserByUserNameOrEmail(friend)
        for friendName in friendlist:
            if friendName["userName"]==friend:
                self.database.addFriend(user.userName,friend)
                user.socket.sendall(b"\n ---------------- Now %s is your friend ----------------\n" %friend.encode())
            else:
                user.socket.sendall(b"\n ---------------- Friend name is not exists ----------------\n")

    def quit(self,user):
        # change status to offline
        self.database.isOffline(user.userName)

        quitString = "quit"
        user.socket.sendall(quitString.encode())
        self.removeUser(user)

    def removeUser(self,user):
        if(user.userName in self.chatMemberOfRoom):
            self.chatRooms[self.chatMemberOfRoom[user.userName]].removeUser(user)
            del self.chatMemberOfRoom[user.userName]
        print ("User: "+ user.userName + " left\n")



