package handler;

import java.util.Arrays;
import java.util.Map;

public class Student {

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
