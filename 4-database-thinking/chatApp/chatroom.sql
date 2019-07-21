CREATE DATABASE chatApp;
use chatApp;


CREATE TABLE userInfor(
    userName VARCHAR(50) NOT NULL ,
    userPassword VARCHAR(16),
    dob DATE,
    email VARCHAR(50),

    PRIMARY KEY (userName)
);



CREATE TABLE friendList(
    idFl int NOT NULL  auto_increment ,
    userName VARCHAR(50),
    friendName VARCHAR(50),
    isOnline BOOLEAN,

    PRIMARY KEY (idFl)
    
);

CREATE TABLE chatRoom(
    idRoom int NOT NULL auto_increment,
    roomName NVARCHAR(50),
    numOfMenber int ,
    dateCreate DATE,

    PRIMARY KEY (idRoom)
);

CREATE TABLE chatMember(
    idChatMem int NOT NULL auto_increment, 
    idRoom int,
    userName VARCHAR(50),

    PRIMARY Key(idChatMem)
);

CREATE TABLE chatMessage(
    idChatMess int NOT NULL auto_increment,
    idChatMem int,
    content text,

    PRIMARY KEY (idChatMess)
);


ALTER TABLE friendList
ADD CONSTRAINT FK_userInfor_friendList_userName
FOREIGN KEY (userName) REFERENCES userInfor(userName);

ALTER TABLE friendList
ADD CONSTRAINT FK_userInfor_friendList_friendName
FOREIGN KEY (friendName) REFERENCES userInfor(userName);

ALTER TABLE chatMember
ADD CONSTRAINT FK_chatRoom_chatMember
FOREIGN KEY (idRoom) REFERENCES chatRoom(idRoom);

ALTER TABLE chatMember
ADD CONSTRAINT FK_userInfor_chatMember
FOREIGN KEY (userName) REFERENCES userInfor(userName);

ALTER TABLE chatMessage
ADD CONSTRAINT FK_chatMember_chatMessage
FOREIGN KEY (idChatMem) REFERENCES chatMember(idChatMem);


INSERT INTO userInfor (userName,userPassword,dob,email)
VALUES ("danghuyentram","abc","1998-7-04","dht@gmail.com");

INSERT INTO userInfor (userName,userPassword,dob,email)
VALUES ("trandt","abc","1998-7-04","nt@gmail.com");

INSERT INTO userInfor (userName,userPassword,dob,email)
VALUES ("sonph","abc","1998-7-04","son@gmail.com");


INSERT INTO friendList (userName,friendName)
VALUES ("danghuyentram","trandt");

INSERT INTO friendList (userName,friendName)
VALUES ("danghuyentram","sonph");

INSERT INTO friendList (userName,friendName)
VALUES ("trandt","danghuyentram");

INSERT INTO chatRoom (roomName,dateCreate)
VALUES ("abc","2019-07-05");

