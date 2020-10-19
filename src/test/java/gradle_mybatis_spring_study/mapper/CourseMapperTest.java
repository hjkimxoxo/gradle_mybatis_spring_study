package gradle_mybatis_spring_study.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import gradle_mybatis_spring_study.dto.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseMapperTest {

	protected static final Log log = LogFactory.getLog(CourseMapperTest.class);
	
	@Autowired
	private CourseMapper mapper;
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	//@Test
	public void test01SelectCoursesByCondition() {
		System.out.println("test01");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 1);
		List<Course> list = mapper.selectCoursesByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);

	}
	
	//@Test
	public void test02SelectCoursesByCondition() {
		System.out.println("test02");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseName", "%Java%");
		List<Course> list = mapper.selectCoursesByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test03SelectCoursesByCondition() {
		System.out.println("test03");
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		System.out.println();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", cal.getTime());
		List<Course> list = mapper.selectCoursesByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);

	}
	
	//@Test
	public void test04SelectCasesByCondition() {
		System.out.println("selectCases");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchBy", "Tutor");
		map.put("tutorId", 1);
		System.out.println();
		
		List<Course> list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.replace("searchBy", "CourseName");
		map.remove("tutorId");
		map.put("courseName", "%Java%");
		list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test05SelectWhereCourses() {
		System.out.println("whereCourses");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> list = mapper.selectWhereCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.put("tutorId", 1);
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.put("courseName", "%Java%");
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		map.clear();
		System.out.println();
		
		map.put("endDate", new Date());
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test06SelectTrimCourses() {
		System.out.println("selectTrimCourses");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println); 
		System.out.println();
		
		map.put("tutorId", 1); 
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.clear();
		map.put("courseName", "%Java%");
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.put("tutorId", 1);
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
	}
	
	//@Test
	public void test07SelectCoursesForeachByTutors() {
		System.out.println("selectCoursesForeachTutors");
		List<Integer> tutorIds = new ArrayList<Integer>();
		tutorIds.add(1);
		tutorIds.add(2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorIds", tutorIds);
		List<Course> list = mapper.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test08insertCourses() {
		System.out.println("insert");
		List<Course> tutors = new ArrayList<Course>();
		tutors.add(new Course(4, "mysql", "database", new Date(), new Date(), 3));
		tutors.add(new Course(5, "mssql", "database", new Date(), new Date(), 3));
		tutors.add(new Course(6, "mariaDb", "database", new Date(), new Date(), 4));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutors", tutors);
		
		int res = mapper.insertCourses(map);
		Assert.assertEquals(3, res);
		List<Course> list = mapper.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test09DeleteCourses() {
		System.out.println("delete");
		List<Integer> courseIds = Arrays.asList(4, 5, 6);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseIds", courseIds);
		int res = mapper.deleteCourses(map);
		Assert.assertEquals(3, res);
		System.out.println();
		
		List<Course> list = mapper.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test10UpdateSetCourses() {
		System.out.println("updateSetCourses");
		Course uc = new Course();
		uc.setCourseId(5);
		
		uc.setDescription("공짜데이터베이스");
		uc.setName("공짜디비");
		int res = mapper.updateCourses(uc);
		Assert.assertEquals(1, res);
		
		uc.setStartDate(new GregorianCalendar(1992, 06, 25).getTime());
		uc.setEndDate(new GregorianCalendar(1993, 06, 25).getTime());
		res = mapper.updateCourses(uc);
		Assert.assertEquals(1, res);
	
	}
	
	@Test
	public void test11insertCourseAndDeleteCourse() {
		Course course = new Course(7, "oracle", "database", new Date(), new Date(), 4);
		int res = mapper.insertCourse(course);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 4);
		
		List<Course> list = mapper.selectCoursesByCondition(map);
		list.stream().forEach(System.out::println);
		
		res += mapper.deleteCourse(course.getCourseId());
		Assert.assertEquals(2, res);
	}
	
	
}
