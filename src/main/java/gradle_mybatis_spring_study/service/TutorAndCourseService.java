package gradle_mybatis_spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradle_mybatis_spring_study.dto.Course;
import gradle_mybatis_spring_study.dto.Tutor;
import gradle_mybatis_spring_study.mapper.CourseMapper;
import gradle_mybatis_spring_study.mapper.TutorMapper;

@Service
public class TutorAndCourseService {
	
	@Autowired
	private TutorMapper tMapper;
	
	@Autowired
	private CourseMapper cMapper;

	public void trJoinTutorAndCourse(Tutor tutor, Course course) {
		int res = tMapper.insertTutor(tutor);
		res += cMapper.insertCourse(course);
		if (res != 2)
			throw new RuntimeException();
	}

	public void trRemoveTutorAndCourse(int courseId, int tutorId) {
		int res = cMapper.deleteCourse(courseId);
		res += tMapper.deleteTutor(tutorId);
		if (res != 2)
			throw new RuntimeException();
	}

}
