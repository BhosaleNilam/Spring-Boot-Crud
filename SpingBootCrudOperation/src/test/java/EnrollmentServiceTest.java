import com.student.StudentManagementApplication;
import com.student.model.Course;
import com.student.model.Student;
import com.student.repository.CourseRepository;
import com.student.repository.StudentRepository;
import com.student.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = StudentManagementApplication.class) // Specify your main application class here
public class EnrollmentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    public void testEnrollInCourse_success(){
        Student student = new Student();
        Course course = new Course();

        course.setName("Java");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.save(any(Student.class))).thenReturn(student);


        Student enrolledStudent = enrollmentService.enrollInCourse(1L, 1L);

        assertTrue(enrolledStudent.getCourses().contains(course));
        verify(studentRepository, times(1)).save(student);


    }

    @Test
    public void testEnrollInCourse_limitExceeded(){
        Student student = new Student();
        Course course = new Course();
        course.setName("Java");

        for(int i =0; i<5; i++){
            student.enrollCourse(new Course());
        }

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    enrollmentService.enrollInCourse(1L, 1L);
                });
        assertEquals("Cant enroll in more than 5 subjects", exception.getMessage());

    }
}
