import select, socket
from chatApp import *
import sys

READ_BUFFER = 2048
MAX_CLIENTS = 50
PORT = 50000




def create_socket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # set socket to non blocking
    s.setblocking(0)
    s.bind(('localhost',PORT))
    s.listen(MAX_CLIENTS)

    print ("Listening in ",PORT)
    return s


chatApp = ChatApp()
listenSocket = create_socket()

connectionList = [] # list socket connect
connectionList.append(listenSocket)

while True:
   # User.fileno()

    readUsers, writeUsers, errorSockets = select.select(connectionList,[],[])
    for user in readUsers:
        if user is listenSocket: # new connection from socket
            newSocket, add = user.accept()
            newUser = User(newSocket)
            connectionList.append(newUser)
            # chatApp.addNewUser(newUser)

        else: # new message
            msg = user.socket.recv(READ_BUFFER)
            if msg:
                msg = msg.decode().lower()
                chatApp.handleMsg(user,msg)
            else:
                user.socket.close()
                connectionList.remove(user)


    for socket in errorSockets: # close error socket
        socket.close()
        connectionList.remove(socket)



