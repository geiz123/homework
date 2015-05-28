-- Create sequences
--CREATE SEQUENCE seqPerson INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE seqBranch INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE seqAddress INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE seqPhone INCREMENT BY 1 START WITH 1000;

-- Create tables
CREATE TABLE PERSON
   (PERSONID BIGINT AUTO_INCREMENT PRIMARY KEY,
    FIRSTNAME VARCHAR2(15) NOT NULL,
    MIDDLENAME VARCHAR2(15) NULL,
    LASTNAME VARCHAR2(15) NOT NULL,
    USERID VARCHAR2(15) NOT NULL,
    PASSWORD VARCHAR2(10) NOT NULL,
    ADDRESSID INTEGER NULL,
    BRANCHID INTEGER NULL);

CREATE TABLE BRANCH
   (BRANCHID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRANCHNAME VARCHAR2(50) NOT NULL,
    ADDRESSID INTEGER NULL); 

--ALTER TABLE BRANCH
--   ADD CONSTRAINT BRANCHPK PRIMARY KEY (BRANCHID);

CREATE TABLE ADDRESS
  (ADDRESSID BIGINT AUTO_INCREMENT PRIMARY KEY,
   STREET1 VARCHAR(30) NULL,
   STREET2 VARCHAR(30) NULL,
   CITY VARCHAR(20) NULL,
   STATE VARCHAR(2) NULL,
   ZIPCODE VARCHAR(9) NULL);

--ALTER TABLE ADDRESS
--   ADD CONSTRAINT ADDRESSPK PRIMARY KEY (ADDRESSID);

CREATE TABLE PHONE
   (PHONEID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PHONETYPE VARCHAR2(1) NOT NULL,
    PHONE VARCHAR(13) NOT NULL);

--ALTER TABLE PHONE
--   ADD CONSTRAINT PHONEPK PRIMARY KEY (PHONEID);

CREATE TABLE PERSONPHONE
   (PERSONID BIGINT NOT NULL,
    PHONEID BIGINT NOT NULL,
    PRIMARY KEY (PERSONID, PHONEID));

--ALTER TABLE PERSONPHONE
--   ADD CONSTRAINT PERSONPHONEPK PRIMARY KEY (PERSONID,PHONEID);


-- Create foreign key constraints
ALTER TABLE BRANCH
    ADD CONSTRAINT BRANCHADDRESSFK FOREIGN KEY (ADDRESSID)
    REFERENCES ADDRESS(ADDRESSID);

ALTER TABLE PERSON
    ADD CONSTRAINT PERSONBRANCHFK FOREIGN KEY (BRANCHID)
    REFERENCES BRANCH(BRANCHID);

ALTER TABLE PERSON
    ADD CONSTRAINT PERSONADDRESSFK FOREIGN KEY (ADDRESSID)
    REFERENCES ADDRESS(ADDRESSID);

ALTER TABLE PERSONPHONE
    ADD CONSTRAINT PERSONPHONEFK FOREIGN KEY (PERSONID)
    REFERENCES PERSON(PERSONID);

ALTER TABLE PERSONPHONE
    ADD CONSTRAINT PHONEPERSONFK FOREIGN KEY (PHONEID)
    REFERENCES PHONE(PHONEID);