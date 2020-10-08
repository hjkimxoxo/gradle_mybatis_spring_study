/* 주소 */
CREATE TABLE addresses (
	addr_id NUMBER NOT NULL, /* 주소코드 */
	street VARCHAR2(50) NOT NULL, /* 도로 */
	city VARCHAR2(50) NOT NULL, /* 시 */
	state VARCHAR2(50) NOT NULL, /* 구 */
	zip VARCHAR2(10), /* 우편번호 */
	country VARCHAR2(50) NOT NULL /* 읍 */
);
ALTER TABLE ADDRESSES ADD CONSTRAINT PK_ADDRESS_ID PRIMARY KEY (addr_id);
/* 과목 */
CREATE TABLE courses (
	course_id NUMBER NOT NULL, /* 과목코드 */
	name VARCHAR2(100) NOT NULL, /* 과목명 */
	description VARCHAR2(512), /* 설명 */
	start_date DATE, /* 시작일 */
	end_date DATE, /* 종료일 */
	tutor_id NUMBER NOT NULL /* 교수번호 */
);
ALTER TABLE courses ADD CONSTRAINT PK_COURSE_ID PRIMARY KEY (course_id);


/* 수강등록 */
CREATE TABLE course_enrollment (
	course_id NUMBER NOT NULL, /* 과목코드 */
	stud_id NUMBER NOT NULL /* 학생코드 */
);
ALTER TABLE course_enrollment ADD CONSTRAINT PK_COURSE_ENROLL_CID_SID PRIMARY KEY (course_id, stud_id);
/* 학생 */
CREATE TABLE students (
	stud_id NUMBER NOT NULL, /* 학생코드 */
	name VARCHAR2(50) NOT NULL, /* 이름 */
	email VARCHAR2(50) NOT NULL, /* 이메일 */
	phone NCHAR(13), /* 연락처 */
	dob DATE, /* 생일 */
	bio CLOB, /* 자기소개 */
	pic BLOB, /* 사진 */
	addr_id NUMBER /* 주소코드 */
);
ALTER TABLE students ADD CONSTRAINT PK_STUDENT_ID PRIMARY KEY (stud_id);

/* 교수 */
CREATE TABLE tutors (
	tutor_id NUMBER NOT NULL, /* 교수번호 */
	name VARCHAR2(50) NOT NULL, /* 이름 */
	email VARCHAR2(50) NOT NULL, /* 이메일 */
	phone NCHAR(13), /* 연락처 */
	dob DATE, /* 생일 */
	bio CLOB, /* 자기소개 */
	pic BLOB, /* 사진 */
	addr_id NUMBER /* 주소코드 */
);
ALTER TABLE tutors ADD CONSTRAINT PRIMARY PRIMARY KEY (tutor_id);
ALTER TABLE courses ADD CONSTRAINT FK_COURSE_TUTOR FOREIGN KEY (tutor_id) REFERENCES tutors (tutor_id);
ALTER TABLE course_enrollment ADD CONSTRAINT FK_ENROLLMENT_COURSE FOREIGN KEY (course_id) REFERENCES courses 
(course_id);
ALTER TABLE course_enrollment ADD CONSTRAINT FK_ENROLLMENT_STUD FOREIGN KEY (stud_id) REFERENCES students (stud_id);
ALTER TABLE students ADD CONSTRAINT FK_STUDENTS_ADDR FOREIGN KEY ( addr_id ) REFERENCES addresses (addr_id);
ALTER TABLE tutors ADD CONSTRAINT FK_TUTORS_ADDR FOREIGN KEY (addr_id) REFERENCES addresses ( addr_id );
