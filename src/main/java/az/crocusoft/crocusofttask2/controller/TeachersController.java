package az.crocusoft.crocusofttask2.controller;

import az.crocusoft.crocusofttask2.dto.request.TeachersRequestDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;
import az.crocusoft.crocusofttask2.services.TeachersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final Logger log = LoggerFactory.getLogger(TeachersController.class);
    private final TeachersService teachersService;

    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @PostMapping
    public ResponseEntity<String> saveTeachers(@RequestBody TeachersRequestDto request){
        log.info("TeachersController: saveTeachers STARTED");
        log.info("TeachersController: Request {}",request);
        return ResponseEntity.ok(teachersService.saveTeachers(request));
    }

    @GetMapping("/allcourse")
    public ResponseEntity<List<TeachersResponseDto>> findAllTeachersByCoursesName(@RequestParam String name){
        log.info("TeachersController: findAllTeachersByCoursesName STARTED");
        return ResponseEntity.ok(teachersService.findAllTeachersByCoursesName(name));
    }

}
