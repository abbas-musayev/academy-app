package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dto.request.TeachersRequestDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;

import java.util.List;

public interface TeachersService {

    String saveTeachers(TeachersRequestDto request);
    String updateTeachers(TeachersRequestDto request);
    List<TeachersResponseDto> findAllTeachersByCoursesName(String name);

}
