package util;

import model.Student;
import java.io.*;
import java.util.*;

public class FileUtil {
    public static List<Student> readStudents(String path) {
        List<Student> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);
                if (p.length < 5) continue;
                Integer r = Integer.parseInt(p[0].trim());
                String name = p[1].trim();
                String email = p[2].trim();
                String course = p[3].trim();
                Double marks = Double.parseDouble(p[4].trim());
                list.add(new Student(r, name, email, course, marks));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        return list;
    }

    public static void writeStudents(String path, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
