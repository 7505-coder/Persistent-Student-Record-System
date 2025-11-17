package service;

import model.Student;
import util.FileUtil;
import exceptions.StudentNotFoundException;
import threads.Loader;
import java.util.*;

public class StudentManager implements RecordActions {
    private List<Student> students = new ArrayList<>();
    private Map<Integer, Student> map = new HashMap<>();
    private String dataPath;

    public StudentManager(String dataPath) {
        this.dataPath = dataPath;
        load();
    }

    private void load() {
        List<Student> fromFile = FileUtil.readStudents(dataPath);
        for (Student s : fromFile) {
            students.add(s);
            map.put(s.rollNo, s);
        }
    }

    @Override
    public synchronized boolean addStudent(Student s) {
        Thread t = new Thread(new Loader("Adding"));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) { }
        if (map.containsKey(s.rollNo)) {
            System.out.println("Duplicate roll number.");
            return false;
        }
        students.add(s);
        map.put(s.rollNo, s);
        return true;
    }

    @Override
    public synchronized boolean deleteStudent(String name) throws StudentNotFoundException {
        Iterator<Student> it = students.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.name.equalsIgnoreCase(name)) {
                it.remove();
                map.remove(s.rollNo);
                removed = true;
            }
        }
        if (!removed) throw new StudentNotFoundException("Student not found: " + name);
        return removed;
    }

    @Override
    public synchronized boolean updateStudent(Integer rollNo, Student s) throws StudentNotFoundException {
        if (!map.containsKey(rollNo)) throw new StudentNotFoundException("Roll no not found: " + rollNo);
        Student old = map.get(rollNo);
        old.name = s.name; old.email = s.email; old.course = s.course; old.marks = s.marks;
        old.calculateGrade();
        return true;
    }

    @Override
    public Student searchStudent(String name) throws StudentNotFoundException {
        for (Student s : students) if (s.name.equalsIgnoreCase(name)) return s;
        throw new StudentNotFoundException("Student not found: " + name);
    }

    @Override
    public List<Student> viewAllStudents() {
        return new ArrayList<>(students);
    }

    public void sortByMarksDesc() {
        students.sort((a,b) -> Double.compare(b.marks, a.marks));
    }

    public void save() {
        Thread t = new Thread(new Loader("Saving"));
        t.start();
        try { t.join(); } catch (InterruptedException e) {}
        FileUtil.writeStudents(dataPath, students);
    }
}
