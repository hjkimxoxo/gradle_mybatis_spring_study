package gradle_mybatis_spring_study.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Gender;
import gradle_mybatis_spring_study.dto.PhoneNumber;
import gradle_mybatis_spring_study.dto.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
protected static final Log log = LogFactory.getLog(StudentMapperTest.class);

	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Autowired
	private StudentMapper mapper;
	

	//@Test
	public void testSelectStudentByNO() {
		System.out.println("selectStudentByNo()");
//		Student student = mapper.selectStudentByNO(new Student(1));
		Student student = new Student();
		student.setStudId(1);
		Student std = mapper.selectStudentByNO(student);
		Assert.assertNotNull(std);
		log.debug(std.toString());
	}
	
	//@Test
	public void testSelectStudentByNoWithResultMap() {
		System.out.println("SelectStudentByNoWithResultMap()");
		Student student = new Student();
		student.setStudId(1);
		Student std = mapper.selectStudentByNoWithResultMap(student);
		Assert.assertNotNull(std);
		log.debug(std.toString());
	}

	@Test
	public void testSelectStudentByAll() {
		System.out.println("All");
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Student> list = mapper.selectStudentByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test01InsertStudent() {
		System.out.println("insert");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);

		Student newStd = new Student();
		newStd.setStudId(6);
		newStd.setName("jin");
		newStd.setEmail("test@test.com");
		newStd.setPhone(new PhoneNumber("010-1234-1234"));
		newStd.setDob(newDate.getTime());
		
		int res = mapper.insertStudent(newStd);
		Assert.assertEquals(1, res);
	}
	
	//@Test
	public void test02updateStudent() {
		System.out.println("update");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1991, 12, 19); //mm+1
		
		Student student = new Student();
		student.setStudId(6);
		Student std = mapper.selectStudentByNO(student);
		Assert.assertNotNull(std);
		log.debug(std.toString());

		std.setName("jin2");
		std.setEmail("test2@test2.com");
		std.setPhone(new PhoneNumber("010-4321-4321"));
		std.setDob(newDate.getTime());
		
		int res = mapper.updateStudent(std);
		Assert.assertEquals(1, res);
		log.debug(std.toString());
		
	}
	
	//@Test
	public void test03deleteStudent() {
		System.out.println("delete");
		
		Student student = new Student();
		student.setStudId(5);
		Student std = mapper.selectStudentByNO(student);
		
		int res = mapper.deleteStudent(std);
		Assert.assertEquals(1, res);
	}
	
	//@Test
	public void testselectStudentByAllForResutlMap() {
		System.out.println("ResultMap");
		List<Student> list = mapper.selectStudentByAllForResultMap();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test9SelectStudentByAllForHashMap(){
		System.out.println("HashMap");
		List<Map<String,Object>> list = mapper.selectStudentByAllForHashMap();
		for(Map<String, Object> map : list) {
			for(Entry<String, Object> e : map.entrySet()) {
			log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
			}
		}
		Assert.assertNotNull(list);
	}
	
	//@Test
	public void test10SelectStudentByNoAssociation() {
		System.out.println("SelectStudentByNoAssociation");
		Student student = new Student();
		student.setStudId(1);
		Student seletedStd = mapper.selectStudentByNoAssociation(student);
		Assert.assertNotNull(seletedStd);
		log.debug(seletedStd.toString());
	}
	
	//@Test
	public void testInsertEnumStudent() {
		System.out.println("insertEnumStudent");
		Calendar newDate = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);

		Student newStd1 = new Student();
		newStd1.setStudId(3);
		newStd1.setName("test");
		newStd1.setEmail("test@test.com");
		newStd1.setPhone(new PhoneNumber("010-1234-1234"));
		newStd1.setDob(newDate.getTime());
		newStd1.setGender(Gender.FEMALE);
		
		int res = mapper.insertEnumStudent(newStd1);
		Assert.assertEquals(1, res);
		
		Calendar newDate2 = GregorianCalendar.getInstance();
		newDate.set(1990, 2, 28);

		Student newStd2 = new Student();
		newStd2.setStudId(4);
		newStd2.setName("test");
		newStd2.setEmail("test@test.com");
		newStd2.setPhone(new PhoneNumber("010-1234-1234"));
		newStd2.setDob(newDate2.getTime());
		newStd2.setGender(Gender.MALE);
		
		int res2 = mapper.insertEnumStudent(newStd2);
		Assert.assertEquals(1, res2);
	}
	
	//@Test
	public void testSelectStudentByNoForEnum() {
		System.out.println("female");
		Student student = new Student();
		student.setGender(Gender.FEMALE);
		Student std = mapper.selectStudentByNoForEnum(student);
		Assert.assertNotNull(std);
		log.debug(std.toString());
		
		System.out.println("male");
		Student student2 = new Student();
		student2.setGender(Gender.MALE);
		Student std2 = mapper.selectStudentByNoForEnum(student2);
		Assert.assertNotNull(std2);
		log.debug(std2.toString());
	}
	
	//@Test
	public void test12SelectStudentByMap() {
		System.out.println("selectStudentByMap");
		Map<String, String> maps = new HashMap<>();
		maps.put("name", "Timothy");
		maps.put("email", "timothy@gmail.com");
		Student student = mapper.selectStudentByMap(maps);
		Assert.assertNotNull(student);
		log.debug(student.toString());
		System.out.println();
		
		maps.remove("email");
		student = mapper.selectStudentByMap(maps);
		log.debug(student.toString());
		System.out.println();
		
		maps.clear();
		maps.put("email", "timothy@gmail.com");
		student = mapper.selectStudentByMap(maps);
		log.debug(student.toString());
	}
	
	//@Test
	public void test13SelectAllStudentByMap() {
		System.out.println("selectAllStudentByMap");
		Map<String, String> maps = new HashMap<>();
		maps.put("name", "Douglas");
		maps.put("email", "douglas@gmail.com");
		System.out.println();
		
		List<Student> list = mapper.selectAllStudentByMap(maps);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		maps.remove("email");
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		maps.clear();
		maps.put("email", "douglas@gmail.com");
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		maps.clear();
		list = mapper.selectAllStudentByMap(maps);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test14UpdateSetStudent() {
		System.out.println("updateSet");
		Student student = new Student();
		student.setStudId(1);
		
		student.setPhone(new PhoneNumber("111-321-214"));
		student.setDob(new GregorianCalendar(1992, 06, 25).getTime());
		int result = mapper.updateSetStudent(student);
		Assert.assertEquals(1, result);
	}

}
