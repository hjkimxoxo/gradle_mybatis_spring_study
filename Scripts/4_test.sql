SELECT * FROM user_tables;

select stud_id, name, email, dob,
	substr(phone, 1, 3) as f, 
	substr(phone, 5, 3) as m, 
	substr(phone, 9, 4) as l
from students where stud_id=1;


select stud_id as studId, name, email, phone, dob from students;

SELECT * FROM students;
SELECT * FROM ADDRESSES;

DELETE FROM STUDENTS WHERE STUD_ID = 3;

INSERT INTO students VALUES ('3', 'jin', 'test@test.com', 345-678-9123, to_date('1991-12-19', 'yyyy-MM-dd'), NULL, NULL, 2);

INSERT INTO STUDENTS(stud_Id, name, email, phone, dob) VALUES ('4', 'kim', 'test2@test', '010-5656-5656', to_date('1991-12-19', 'yyyy-MM-dd'));


--일대일매핑
SELECT STUD_ID,NAME,EMAIL,PHONE,DOB,a.ADDR_ID,STREET, CITY, STATE, ZIP,COUNTRY FROM STUDENTS s JOIN ADDRESSES a ON s.ADDR_ID = a.ADDR_ID WHERE s.STUD_ID = 1; 

--일대다매핑 
SELECT * FROM TUTORS;

select t.tutor_id, t.name as tutor_name, email, c.course_id, c.name, description, start_date, end_date
		from tutors t left outer join courses c on t.tutor_id=c.tutor_id
		where t.tutor_id = 1;

--컬럼추가
ALTER TABLE STUDENTS ADD GENDER NUMBER(1,0);
SELECT * FROM STUDENTS;

SELECT STUD_ID,NAME,EMAIL,PHONE,DOB FROM STUDENTS WHERE gender = 1;

--userpic
SELECT * FROM USER_PICS;

DELETE FROM USER_PICS WHERE id = 1;
