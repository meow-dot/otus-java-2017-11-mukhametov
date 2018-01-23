package l8;

import com.google.gson.Gson;
import l8.handler.JsonHandler;
import java.util.*;

public class JsonHandlerTest {

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



}