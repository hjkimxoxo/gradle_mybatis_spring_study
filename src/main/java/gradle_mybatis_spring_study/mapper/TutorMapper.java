package gradle_mybatis_spring_study.mapper;

import gradle_mybatis_spring_study.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
	int insertTutor(Tutor tutor);
	int deleteTutor(int tutorId);
	
}
