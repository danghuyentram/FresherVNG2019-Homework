CREATE DATABASE rock_paper_scissors;
use rock_paper_scissors;


CREATE TABLE user(
    id int NOT NULL  AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(16),
    win_rate float,
    PRIMARY KEY (id)
);

CREATE TABLE game(
    id int NOT NULL  AUTO_INCREMENT,
    id_user int,
    result int,
    date_time_play DATETIME,
    turn_number int,
    PRIMARY KEY (id),
    FOREIGN KEY (id_user) REFERENCES user(id)

);

CREATE TABLE turn(
    id int NOT NULL AUTO_INCREMENT,
    id_game int,
    user_step int,
    bot_step int,
    result int,
    date_time_play DATETIME,

    PRIMARY KEY (id),
    FOREIGN KEY (id_game) REFERENCES game(id)

);




