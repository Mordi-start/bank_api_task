DROP TABLE IF EXISTS TRANSACTIONS;
DROP TABLE if exists CARDS;
DROP TABLE if exists ACCOUNTS;
drop table if exists ROLES;
DROP TABLE if exists USERS;

CREATE TABLE USERS
(
    ID       INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME     VARCHAR2,
    SURNAME  VARCHAR2,
    LOGIN    VARCHAR2 UNIQUE,
    PASSWORD VARCHAR2,
    ENABLED  BOOLEAN DEFAULT false
);

CREATE TABLE ROLES
(
    USER_ID   VARCHAR2,
    ADMIN BOOLEAN,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

CREATE TABLE ACCOUNTS
(
    ID      INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NUMBER  VARCHAR2(20) UNIQUE,
    BALANCE DOUBLE DEFAULT 0,
    USER_ID INT,
    ENABLED BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE
);

CREATE TABLE CARDS
(
    ID             INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NUMBER         VARCHAR2(20) UNIQUE,
    ACCOUNT_ID     INT,
    ENABLED        BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS (ID) ON DELETE CASCADE
);

CREATE TABLE TRANSACTIONS
(
    ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ACCOUNT_ID INT,
    TYPE VARCHAR2,
    SUM DOUBLE,
    ENABLED BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS (ID)
);

INSERT INTO USERS
VALUES (DEFAULT, 'Dmitry', 'Morel', 'morda_0xd', '{noop}34626jh-34_ON', true);
INSERT INTO USERS
VALUES (DEFAULT, 'Yuri', 'Sikalov', 'sikel123', '{noop}26432gsd', true);
INSERT INTO USERS
VALUES (DEFAULT, 'Alex', 'Kotov', 'cat1796', '{noop}90734_ngdfUIN', true);

INSERT INTO ROLES
VALUES (1, TRUE);
INSERT INTO ROLES
VALUES (2, FALSE);
INSERT INTO ROLES
VALUES (2, FALSE);

INSERT INTO ACCOUNTS
VALUES (DEFAULT, '11111111111111111111', 900000, 1, true);
INSERT INTO ACCOUNTS
VALUES (DEFAULT, '22222222222222222222', 900909, 2, true);
INSERT INTO ACCOUNTS
VALUES (DEFAULT, '33333333333333333333', 909090, 3, true);
INSERT INTO ACCOUNTS
VALUES (DEFAULT, '44444444444444444444', 99090, 1, true);
INSERT INTO ACCOUNTS
VALUES (DEFAULT, '55555555555555555555', 0, 3, false);

INSERT INTO CARDS
VALUES (DEFAULT, '1111', 1, true);
INSERT INTO CARDS
VALUES (DEFAULT, '1111 5555 3333 4444', 1, false);
INSERT INTO CARDS
VALUES (DEFAULT, '1111 2222 5555 4444', 1, true);
INSERT INTO CARDS
VALUES (DEFAULT, '2222 2222 3333 4444', 2, true);
INSERT INTO CARDS
VALUES (DEFAULT, '3333 4444 3333 4444', 3, true);
INSERT INTO CARDS
VALUES (DEFAULT, '3333 2222 5555 4444', 4, true);
INSERT INTO CARDS
VALUES (DEFAULT, '3333 1111 3333 4444', 5, false);