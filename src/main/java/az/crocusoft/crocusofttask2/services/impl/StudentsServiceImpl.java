package az.crocusoft.crocusofttask2.services.impl;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dao.entity.Students;
import az.crocusoft.crocusofttask2.dao.repository.CoursesRepo;
import az.crocusoft.crocusofttask2.dao.repository.StudentsRepo;
import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.request.StudentsRequestDto;
import az.crocusoft.crocusofttask2.dto.response.StudentsResponseDto;
import az.crocusoft.crocusofttask2.exception.CustomNotFoundException;
import az.crocusoft.crocusofttask2.services.StudentsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    private final Logger log = LoggerFactory.getLogger(StudentsServiceImpl.class);

    private final StudentsRepo studentsRepo;
    private final CoursesRepo coursesRepo;
    private final ModelMapper modelMapper;

    public StudentsServiceImpl(
            StudentsRepo studentsRepo,
            CoursesRepo coursesRepo,
            ModelMapper modelMapper) {
        this.studentsRepo = studentsRepo;
        this.coursesRepo = coursesRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveStudents(StudentsRequestDto request) {
        Students students = modelMapper.map(request, Students.class);

        log.info("StudentsServiceImpl: StudentEntity {}",students);

        List<CoursesRequestDto> courses = request.getCourses();
        List<Courses> courseList = new ArrayList<>();
        for (CoursesRequestDto item : courses) {
            courseList.add(
                    coursesRepo.findById(item.getId())
                    .orElseThrow(()-> new CustomNotFoundException("Course Id is Null!")));
        }

        students.setCourses(courseList);
        studentsRepo.save(students);
        return "Students Saved!";
    }

    @Override
    public String updateStudents(StudentsRequestDto request) {

        if (request.getId()==null){
            return "Student Id is Null!";
        }
        Students map = modelMapper.map(request, Students.class);

        List<CoursesRequestDto> courses = request.getCourses();
        List<Courses> list = new ArrayList<>();
        for (CoursesRequestDto item : courses) {
            list.add(
                    coursesRepo.findById(item.getId())
                            .orElseThrow(()-> new CustomNotFoundException("Course Id is Null!")));
        }
        map.setCourses(list);
        studentsRepo.save(map);
        return "Students Updated";

    }

    @Override
    public StudentsResponseDto findStudentByNameAndSurname(String name, String surname) {
        log.info("StudentsServiceImpl: findStudentByNameAndSurname STARTED");
        log.info("Name: "+ name+ " surname: "+surname);
        Students studentsByNameAndSurname = studentsRepo.findStudentsByNameAndSurname(name, surname);
        log.info("Finded Student : {}",studentsByNameAndSurname);
        return modelMapper.map(studentsByNameAndSurname,StudentsResponseDto.class);
    }


}
