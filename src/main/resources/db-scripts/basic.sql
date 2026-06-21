
-- may-24-2026
-- create table
CREATE TABLE STUDENTS (
  ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  NAME VARCHAR2(100) NOT NULL,
  AGE NUMBER(3),
  CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
-- create sequence
CREATE SEQUENCE student_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
NOCYCLE;

-----------------------------------------------------------------------
-- Create USER_SEQ if it does not exist
-----------------------------------------------------------------------
DECLARE
v_count NUMBER;
BEGIN
SELECT COUNT(*)
INTO v_count
FROM USER_SEQUENCES
WHERE SEQUENCE_NAME = 'USER_SEQ';

IF v_count = 0 THEN
        EXECUTE IMMEDIATE '
            CREATE SEQUENCE USER_SEQ
            START WITH 1
            INCREMENT BY 1
            NOCACHE
            NOCYCLE';
END IF;
END;
/


-----------------------------------------------------------------------
-- Create ROLE_SEQ if it does not exist
-----------------------------------------------------------------------
DECLARE
v_count NUMBER;
BEGIN
SELECT COUNT(*)
INTO v_count
FROM USER_SEQUENCES
WHERE SEQUENCE_NAME = 'ROLE_SEQ';

IF v_count = 0 THEN
        EXECUTE IMMEDIATE '
            CREATE SEQUENCE ROLE_SEQ
            START WITH 1
            INCREMENT BY 1
            NOCACHE
            NOCYCLE';
END IF;
END;
/

-----------------------------------------------------------------------
-- Create USERS table
-----------------------------------------------------------------------
DECLARE
v_count NUMBER;
BEGIN
SELECT COUNT(*)
INTO v_count
FROM USER_TABLES
WHERE TABLE_NAME = 'USERS';

IF v_count = 0 THEN

        EXECUTE IMMEDIATE '
        CREATE TABLE USERS
        (
            ID          NUMBER PRIMARY KEY,

            USERNAME    VARCHAR2(100) NOT NULL,

            PASSWORD    VARCHAR2(255) NOT NULL,

            IS_ACTIVE   CHAR(1) DEFAULT ''Y'' NOT NULL,

            CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

            UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

            CONSTRAINT UK_USERS_USERNAME UNIQUE (USERNAME)
        )';

END IF;
END;
/

-----------------------------------------------------------------------
-- Create ROLES table
-----------------------------------------------------------------------
DECLARE
v_count NUMBER;
BEGIN
SELECT COUNT(*)
INTO v_count
FROM USER_TABLES
WHERE TABLE_NAME = 'ROLES';

IF v_count = 0 THEN

        EXECUTE IMMEDIATE '
        CREATE TABLE ROLES
        (
            ID          NUMBER PRIMARY KEY,

            NAME        VARCHAR2(50) NOT NULL,

            IS_ACTIVE   CHAR(1) DEFAULT ''Y'' NOT NULL,

            USER_ID     NUMBER NOT NULL,

            CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

            UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

            CONSTRAINT FK_ROLE_USER
                FOREIGN KEY (USER_ID)
                REFERENCES USERS(ID),

            CONSTRAINT UK_ROLE_USER
                UNIQUE(USER_ID, NAME)
        )';

END IF;
END;
/
