package handler;

import com.google.gson.Gson;

import java.util.*;

public class JsonHandlerDemo {

    public static void main(String[] args) {

        Map<String, Integer> grades = new HashMap<>();
        grades.put("Math", 5);
        grades.put("History", 4);
        String[] hobbies = new String[]{"Golf", "Singing"};

        Student student = new Student(1, grades, hobbies);
        String result1 = JsonHandler.write(student);
        System.out.println(result1);

        Gson gson = new Gson();
        String result2 = gson.toJson(student);
        System.out.println(result2);

        Student studentCopy = gson.fromJson(result1, Student.class);
        System.out.println(studentCopy);
    }

    public static class Student {

        private final int id;
        private final Map<String, Integer> grades;
        private final String[] hobbies;

        public Student(int id, Map<String, Integer> grades, String[] hobbies) {
            this.id = id;
            this.grades = grades;
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return "\n" +
                    "id: " + id + ";\n" +
                    "grades: " + grades + ";\n" +
                    "hobbies: " + Arrays.toString(hobbies);
        }
    }
}
