-- Insert addresses for branches, persons and payees
INSERT INTO ADDRESS
   VALUES(1, '585 1st St.', NULL, 'Idaho Falls', 'ID', '83402');
INSERT INTO ADDRESS
   VALUES(2, '77 E Main', NULL, 'Rexburg', 'ID', '83440');
INSERT INTO ADDRESS
   VALUES(3, '183 S State', NULL, 'Rigby', 'ID', '83445');
INSERT INTO ADDRESS
   VALUES(4, '1455 University Ave.', NULL, 'Provo', 'UT', '84540');
INSERT INTO ADDRESS
   VALUES(5, '65 S. Center', NULL, 'Rexburg', 'ID', '83440');
INSERT INTO ADDRESS
   VALUES(6, '101 Main St.', NULL, 'Nauvoo', 'IL', '70512');
INSERT INTO ADDRESS
   VALUES(7, '424 Nephi Ave.', NULL, 'Kirtland', 'OH', '12891');
INSERT INTO ADDRESS
   VALUES(8, '0001 Boulder Lane', NULL, 'Neanderthal', 'PL', '11111');
INSERT INTO ADDRESS
   VALUES(9, '0003 Boulder Lane', NULL, 'Neanderthal', 'PL', '11111');
INSERT INTO ADDRESS
   VALUES(10, '1001 Main Street', NULL, 'Nauvoo', 'IL', '35578');
INSERT INTO ADDRESS
   VALUES(11, 'Celestial Apartments, #26', '842 College Ave', 'Rexburg', 'ID', '83440');
INSERT INTO ADDRESS
   VALUES(12, '825 North Yellowstone', NULL, 'Idaho Falls', 'ID', '83410');
INSERT INTO ADDRESS
   VALUES(13, '435 Woodruff', NULL, 'Idaho Falls', 'ID', '83410');
INSERT INTO ADDRESS
   VALUES(14, '7823 Alpine', NULL, 'Rexburg', 'ID', '83440');
INSERT INTO ADDRESS
   VALUES(15, '355 2nd East 3rd South ', NULL, 'Rigby', 'ID', '83446');
INSERT INTO ADDRESS
   VALUES(16, '1123 17th Street', NULL, 'Idaho Falls', 'ID', '83410');
INSERT INTO ADDRESS
   VALUES(17, '9825 Broadway', NULL, 'Boise', 'ID', '83493');
INSERT INTO ADDRESS
   VALUES(18, '1821 Spring Hill', NULL, 'Salt Lake City', 'UT', '83561');
INSERT INTO ADDRESS
   VALUES(19, '1352 7th South 5th East', NULL, 'Rexburg', 'ID', '83440');


-- PHONES: Insert phone type and number into PHONE
INSERT INTO PHONE
   VALUES(1, 'H', '0010011111');

INSERT INTO PHONE
   VALUES(2, 'W', '0010012222');

INSERT INTO PHONE
   VALUES(3,'H', '0010011234');

INSERT INTO PHONE
   VALUES(4, 'C', '0010015678');

INSERT INTO PHONE
   VALUES(5, 'W', '0010019876');

INSERT INTO PHONE
   VALUES(6, 'H', '2083568126');

INSERT INTO PHONE
   VALUES(7, 'C', '6105218324');

INSERT INTO PHONE
   VALUES(8, 'C', '6105218412');

INSERT INTO PHONE
   VALUES(9, 'W', '2084968314');

INSERT INTO PHONE
   VALUES(10, 'H', '2086561845');

INSERT INTO PHONE
   VALUES(11, 'W', '2084961182');

INSERT INTO PHONE
   VALUES(12, 'W', '2087458221');

INSERT INTO PHONE
   VALUES(13, 'W', '2085213632');

INSERT INTO PHONE
   VALUES(14, 'W', '2085221233');

INSERT INTO PHONE
   VALUES(15, 'W', '2087456551');

INSERT INTO PHONE
   VALUES(16, 'W', '2083563482');

INSERT INTO PHONE
   VALUES(17, 'W', '2088351232');

INSERT INTO PHONE
   VALUES(18, 'W', '2086567103');


-- Insert persons
INSERT INTO PERSON
   VALUES(1, 'Fred', 'F', 'Flintstone', 'flintstonef', 'pebbles', 8);

INSERT INTO PERSON
   VALUES(2, 'Barney', 'B', 'Rubble', 'rubbleb', 'bambam', 9);

INSERT INTO PERSON
   VALUES(3, 'Joseph', '', 'Smith', 'smithj', 'kirtland', 10);

INSERT INTO PERSON
   VALUES(4, 'Emma', NULL, 'Smith', 'smithe', 'nauvoo', 10);

    INSERT INTO PERSON
   VALUES(5, 'Richard', NULL, 'Isa', 'asdf', 'asdf', 11);

-- Insert branches
INSERT INTO BRANCH
   VALUES ('1', 'US Bank - Idaho Falls', '1');
INSERT INTO BRANCH
   VALUES ('2', 'US Bank - Rexburg', '2');
INSERT INTO BRANCH
   VALUES ('3', 'US Bank - Rigby', '3');
INSERT INTO BRANCH
   VALUES ('4', 'Beehive Credit Union - Provo', '4');
INSERT INTO BRANCH
   VALUES ('5', 'Beehive Credit Union - Rexburg', '5');
INSERT INTO BRANCH
   VALUES('6', 'Bank of Nauvoo', '6');
INSERT INTO BRANCH
   VALUES('7', 'Bank of Kirtland','7');

-- Assign phones to persons
INSERT INTO PERSONPHONE
   VALUES(1, 1);

INSERT INTO PERSONPHONE
   VALUES(1, 2);

INSERT INTO PERSONPHONE
   VALUES(2, 3);

INSERT INTO PERSONPHONE
   VALUES(2, 4);

INSERT INTO PERSONPHONE
   VALUES(2, 5);

INSERT INTO PERSONPHONE
   VALUES(3, 6);

INSERT INTO PERSONPHONE
   VALUES(3, 7);

INSERT INTO PERSONPHONE
   VALUES(4, 6);

INSERT INTO PERSONPHONE
   VALUES(4, 8);

INSERT INTO PERSONPHONE
   VALUES(4, 9);

INSERT INTO PERSONPHONE
   VALUES(5, 10);

INSERT INTO PERSONPHONE
   VALUES(5, 11);