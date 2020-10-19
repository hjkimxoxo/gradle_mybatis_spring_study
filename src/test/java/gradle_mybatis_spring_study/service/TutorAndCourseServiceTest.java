package gradle_mybatis_spring_study.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Address;
import gradle_mybatis_spring_study.dto.Course;
import gradle_mybatis_spring_study.dto.PhoneNumber;
import gradle_mybatis_spring_study.dto.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorAndCourseServiceTest {
	
	protected static final Log log = LogFactory.getLog(TutorAndCourseServiceTest.class);
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private TutorAndCourseService service;

	//교수~4 과목 ~6 
	@Test(expected = DuplicateKeyException.class)
	public void test01TrJoinTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");
		//1번 교수는 존재
		Tutor tutor = new Tutor(1, "hjkim", "test@test.com", address, phone);
		Course course = new Course(7, "Python", "Programming", new Date(), new Date(), 4); 
		service.trJoinTutorAndCourse(tutor, course);
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void test02TrJoinTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");
		Tutor tutor = new Tutor(5, "hjkim", "test@test.com", address, phone);
		//2번 과목 존재, 해당 교수번호를가진 교수가 존재하지 않음
		Course course = new Course(2, "Python", "Programming", new Date(), new Date(), 100);
		service.trJoinTutorAndCourse(tutor, course);

	}
	
	@Test
	public void test03TrJoinTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");
		Tutor tutor = new Tutor(5, "hjkim", "test@test.com", address, phone);
		Course course = new Course(7, "Python", "Programming", new Date(), new Date(), 5);
		service.trJoinTutorAndCourse(tutor, course);

	}
	//교수~4 과목 ~6 /insert: 7번코스 5번교수
	@Test(expected = RuntimeException.class)
	public void test04TrRemoveTutorAndCourse() { //course -> tutor
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.trRemoveTutorAndCourse(7, 10); // 7번 코스 o, 10번교수x
	}
	
	@Test(expected = RuntimeException.class)
	public void test05TrRemoveTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.trRemoveTutorAndCourse(8, 5); // 8번 코스 x, 5번교수 o
	}
	
	@Test
	public void test06TrRemoveTutorAndCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		service.trRemoveTutorAndCourse(7, 5);
	}
}
