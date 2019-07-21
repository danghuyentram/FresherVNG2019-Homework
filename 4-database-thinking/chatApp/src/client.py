import select,socket, sys
from chatApp import *
import getpass

READ_BUFFER =2048
ADDRESS = "127.0.0.1"
PORT = 50000
quitString = "quit"


def login():
    print("Enter your username")
    userName  = sys.stdin.readline()

    print("Enter your password")
    # password = sys.stdin.readline()
    password = getpass.getpass()

    msg = "login %s %s" %(userName,password)
    serverConnection.sendall(msg.encode())


def signup():
    print("Enter your username")
    userName  = sys.stdin.readline()

    print("Enter your password")
    password = sys.stdin.readline()

    print("Enter your dob with format: YYYY-MM-DD")
    dob = sys.stdin.readline()

    print("Enter your email")
    email = sys.stdin.readline()

    msg = "signup %s %s %s %s" %(userName,password,dob,email)
    serverConnection.sendall(msg.encode())



serverConnection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverConnection.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,1)
serverConnection.connect((ADDRESS,PORT))

print ("Connected to server\n")
tmp=""
connectionList = [sys.stdin,serverConnection]


man = 'Manual:\n'\
            + '[<login>] to login with your account\n'\
            + '[<signup> ] to create new account\n' \

print(man)
control = sys.stdin.readline()


if("login" in control):
    login()
elif("signup" in control):
    signup()


while True:
    readSockets, writeSockets, errorSockets = select.select(connectionList, [], [])
    for sock in readSockets:
        if sock is serverConnection: # message from server
            msg = sock.recv(READ_BUFFER)
            if not msg:
                print ("Server down")
                sys.exit(2)
            else:
                # print("mess" + msg.decode() + "\n")
                if msg.decode() == 'quit':
                    sys.stdout.write('Bye\n')
                    sys.exit(2)
                elif('Username or password is not correct' in msg.decode()):
                    print("Username or password is not correct \n Try again\n")
                    login()
                else:
                    sys.stdout.write(msg.decode())
                    if 'Enter your username' in msg.decode():
                        tmp = "name: "
                    elif ('Enter your password' in msg.decode()):
                        tmp = "password: "
                    elif ('Enter your dob like format: YYYY-MM-DD' in msg.decode()):
                        tmp = "dob: "
                    elif ('Enter your email:' in msg.decode()):
                        tmp = "email: "
                    else:
                        tmp = ""
                    print ("> ")
        else:
            msg = tmp + sys.stdin.readline()
            serverConnection.sendall(msg.encode())
