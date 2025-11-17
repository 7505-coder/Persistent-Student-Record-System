package service;

import model.Student;
import exceptions.StudentNotFoundException;
import java.util.List;

public interface RecordActions {
    boolean addStudent(Student s);
    boolean deleteStudent(String name) throws StudentNotFoundException;
    boolean updateStudent(Integer rollNo, Student s) throws StudentNotFoundException;
    Student searchStudent(String name) throws StudentNotFoundException;
    List<Student> viewAllStudents();
}
