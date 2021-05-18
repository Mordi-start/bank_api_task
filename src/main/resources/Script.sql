-- DROP TABLE CARDS;
-- DROP TABLE ACCOUNTS;
-- DROP TABLE USERS;

CREATE TABLE USERS(
                      ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                      NAME VARCHAR2,
                      SURNAME VARCHAR2
);
CREATE TABLE ACCOUNTS(
                         ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                         NUMBER VARCHAR2(20),
                         BALANCE DOUBLE,
                         USER_ID INT,
                         FOREIGN KEY (USER_ID) REFERENCES USERS(ID) ON DELETE CASCADE
);
CREATE TABLE CARDS(
                      ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                      NUMBER VARCHAR2(20),
                      TYPE VARCHAR2,
                      PAYMENT_SYSTEM VARCHAR2,
                      ACCOUNT_ID INT,
                      FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS(ID) ON DELETE CASCADE
);
INSERT INTO USERS VALUES ( DEFAULT, 'Dmitry', 'Morel' );
INSERT INTO USERS VALUES ( DEFAULT, 'Yuri', 'Sikalov' );
INSERT INTO USERS VALUES ( DEFAULT, 'Alex', 'Kotov' );

INSERT INTO ACCOUNTS VALUES ( DEFAULT, '11111111111111111111', 900000, 1 );
INSERT INTO ACCOUNTS VALUES ( DEFAULT, '22222222222222222222', 900909, 2 );
INSERT INTO ACCOUNTS VALUES ( DEFAULT, '33333333333333333333', 909090, 3 );
INSERT INTO ACCOUNTS VALUES ( DEFAULT, '33333333334233333333', 99090, 1 );
INSERT INTO ACCOUNTS VALUES ( DEFAULT, '35363333333333333333', 900000, 3 );

INSERT INTO CARDS VALUES ( DEFAULT, '1111', 'DEB', 'VISA', 1 );
INSERT INTO CARDS VALUES ( DEFAULT, '1111 5555 3333 4444', 'DEB', 'MASTER', 1 );
INSERT INTO CARDS VALUES ( DEFAULT, '1111 2222 5555 4444', 'CRED', 'MIR', 1 );
INSERT INTO CARDS VALUES ( DEFAULT, '2222 2222 3333 4444', 'DEB', 'VISA', 2 );
INSERT INTO CARDS VALUES ( DEFAULT, '3333 2222 3333 4444', 'DEB', 'MASTER', 3 );
INSERT INTO CARDS VALUES ( DEFAULT, '3333 2222 3333 4444', 'DEB', 'VISA', 4 );
INSERT INTO CARDS VALUES ( DEFAULT, '3333 2222 3333 4444', 'DEB', 'MIR', 5 );

-- DELETE FROM USERS WHERE ID = 1;
SELECT * FROM USERS;
SELECT * FROM ACCOUNTS;
SELECT * FROM CARDS;