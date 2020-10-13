package gradle_mybatis_spring_study.mapper;

import java.util.List;
import java.util.Map;

import gradle_mybatis_spring_study.dto.Student;

public interface StudentMapper {
	//typeHandler 이용
	Student selectStudentByNO(Student student);
	
	//ResultMap 이용
	Student selectStudentByNoWithResultMap(Student student);
	
	List<Student> selectStudentByAll();
	
	int insertStudent(Student student);
	
	int updateStudent(Student student);
	
	int deleteStudent(Student student);
	
	//ResultMap
	List<Student> selectStudentByAllForResultMap();
	
	//HashMap
	List<Map<String, Object>> selectStudentByAllForHashMap();
	
	//일대일매핑
	Student selectStudentByNoAssociation(Student student);
	
	int insertEnumStudent(Student student);
	Student selectStudentByNoForEnum(Student student);

}
