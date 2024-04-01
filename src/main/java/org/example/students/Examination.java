package org.example.students;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Examination {
    void addScore(Score score);

    Score getScore(String name, String subject) throws StudentNotFoundExeption;

    double getAverageForSubject(String subject);

    Set<String> multipleSubmissionsStudentNames();

    Collection<String> lastFiveStudentsWithExcellentMarkOnAnySubject();

    Collection<String> getAllScores();
    Map<String, Score> getAllStudentsResult();
}
