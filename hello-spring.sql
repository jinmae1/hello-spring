--=====================================
-- 관리자계정 - spring계정 생성
--=====================================
ALTER SESSION SET "_oracle_script" = TRUE -- 일반사용자 c## 접두어 없이 계정 생성
CREATE USER spring
identified BY spring
DEFAULT tablespace users;

ALTER USER spring quota unlimited ON users;

GRANT CONNECT, resource TO spring;


--=====================================
-- spring
--=====================================
-- dev 테이블
CREATE TABLE dev(
	NO number,
	name varchar2(50) NOT NULL,
	career number NOT NULL,
	email varchar2(200) NOT NULL,
	gender char(1),
	lang varchar2(100) NOT NULL, -- vo String[] <---> varchar2 'java,c,js'
	CONSTRAINT pk_dev_no PRIMARY key(no),
	CONSTRAINT ck_dev_gender check(gender IN ('M', 'F'))
);

CREATE sequence seq_dev_no;