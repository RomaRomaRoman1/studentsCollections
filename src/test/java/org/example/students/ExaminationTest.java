package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExaminationTest {
    Examination examination;
    @BeforeEach
    void setUp() {
        examination=new InMemoryScore();
    }

    @Test
    void addScore() throws StudentNotFoundExeption {
        Score student1 = new Score("Роман Хорошеев", "математика", 5);
        examination.addScore(student1);
        Score actual = examination.getScore("Роман Хорошеев", "математика");
        Assertions.assertEquals(student1, actual);
    }

    @Test
    void getScoreIfNull() throws StudentNotFoundExeption{
        Assertions.assertThrows(StudentNotFoundExeption.class, ()->examination.getScore("Петька", "тупология"));
    }

    @Test
    void showAverageScore() {
        Score student1 = new Score("Роман Хорошеев", "математика", 5);
        Score student2 = new Score("Игорь Петров", "математика", 3);
        Score student3 = new Score("Харитонов Василий", "математика", 4);
        Score student4 = new Score("Антонов Василий", "математика", 5);
        examination.addScore(student1);
        examination.addScore(student2);
        examination.addScore(student3);
        examination.addScore(student4);
        double averageScore = examination.getAverageForSubject("математика");
        double averageNullScore = examination.getAverageForSubject("петухоллогия");
        Assertions.assertEquals(4.25, averageScore);
        Assertions.assertEquals(0.0, averageNullScore);
    }
    @Test
    void showAverageScoreSingleRequest() {
        Score student1 = new Score("Роман Хорошеев", "математика", 5);
        Score student2 = new Score("Игорь Петров", "математика", 3);
        Score student3 = new Score("Харитонов Василий", "математика", 4);
        Score student4 = new Score("Антонов Василий", "математика", 5);
        examination.addScore(student1);
        examination.addScore(student2);
        examination.addScore(student3);
        examination.addScore(student4);
        double averageScore = examination.getAverageForSubject("математика");
        double averageNullScore = examination.getAverageForSubject("петухоллогия");
        Assertions.assertEquals(4.25, averageScore);
        Assertions.assertEquals(0.0, averageNullScore);
    }

    @Test
    void showWhoTakesExamMoreOneTime() {
        Score student1 = new Score("Роман Хорошеев", "математика", 5);
        Score student4 = new Score("Роман Хорошеев", "математика", 3);
        Score student5 = new Score("Роман Хорошеев", "математика", 2);
        Score student2 = new Score("Игорь Петров", "математика", 3);
        Score student3 = new Score("Харитонов Василий", "физика", 4);
        Score student6 = new Score("Харитонов Василий", "физика", 2);
        examination.addScore(student1);
        examination.addScore(student2);
        examination.addScore(student3);
        examination.addScore(student4);
        examination.addScore(student5);
        examination.addScore(student6);
        Collection<String> allScore = examination.getAllScores();
        System.out.println(allScore);
        System.out.println(examination.multipleSubmissionsStudentNames());
    }

    @Test
    void lastFiveGoodResult() {
        Score student1 = new Score("Роман Хорошеев", "математика", 3);
        Score student4 = new Score("Роман Хорошеев", "математика", 2);
        Score student5 = new Score("Роман Хорошеев", "математика", 5);
        Score student2 = new Score("Игорь Петров", "математика", 3);
        Score student3 = new Score("Харитонов Василий", "физика", 4);
        Score student6 = new Score("Харитонов Василий", "физика", 2);
        Score student7 = new Score("Петличкин Василий", "физика", 5);
        Score student8 = new Score("Птичкин Василий", "математика", 5);
        Score student9 = new Score("Пташкин Василий", "химия", 5);
        Score student10 = new Score("Павлов Василий", "химия", 3);
        Score student11 = new Score("Хакаев Василий", "химия", 5);
        Score student12 = new Score("Седов Василий", "химия", 5);
        Score student13 = new Score("Ульянов Василий", "химия", 5);
        examination.addScore(student1);
        examination.addScore(student2);
        examination.addScore(student3);
        examination.addScore(student4);
        examination.addScore(student5);
        examination.addScore(student6);
        examination.addScore(student7);
        examination.addScore(student8);
        examination.addScore(student9);
        examination.addScore(student10);
        examination.addScore(student11);
        examination.addScore(student12);
        examination.addScore(student13);
        List<String> assertList = List.of(student13.name(), student12.name(), student11.name(), student9.name(), student8.name());
        Collection<String> goodStudent = examination.lastFiveStudentsWithExcellentMarkOnAnySubject();
        System.out.println(goodStudent);
    }
}
