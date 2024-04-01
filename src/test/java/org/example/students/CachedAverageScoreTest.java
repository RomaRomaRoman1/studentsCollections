package org.example.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class CachedAverageScoreTest {
    private final InMemoryScore inMemoryScore = new InMemoryScore();
    private final Examination cachedExamination = new CachedAverageScore(inMemoryScore);

    @Test
    void callOnceForRepeatingRequest() {
        Score student1 = new Score("Роман Хорошеев", "математика", 5);
        Score student2 = new Score("Игорь Петров", "математика", 3);
        Score student3 = new Score("Харитонов Василий", "математика", 4);
        Score student4 = new Score("Антонов Василий", "математика", 5);
        inMemoryScore.addScore(student1);
        inMemoryScore.addScore(student2);
        inMemoryScore.addScore(student3);
        inMemoryScore.addScore(student4);

        System.out.println(cachedExamination.getAverageForSubject("математика"));
        cachedExamination.getAverageForSubject("математика");
        cachedExamination.getAverageForSubject("физика");
        cachedExamination.getAverageForSubject("математика");
        Assertions.assertEquals(2,inMemoryScore.getCalls());
        cachedExamination.getAverageForSubject("химия");
        Assertions.assertEquals(3,inMemoryScore.getCalls());
        cachedExamination.getAverageForSubject("математика");
        Assertions.assertEquals(3,inMemoryScore.getCalls());
        cachedExamination.getAverageForSubject("физика");
        Assertions.assertEquals(4,inMemoryScore.getCalls());

    }
}