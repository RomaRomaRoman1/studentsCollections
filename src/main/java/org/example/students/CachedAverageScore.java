package org.example.students;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CachedAverageScore implements Examination {
    private final Map<String, Double> cache = new LRUCache<String, Double>(2);//LinkedHashMap которая хранит в себе кэш
    private final Examination inMemoryScore;//определяем поле для фасада

    public CachedAverageScore(Examination inMemoryScore) {
        this.inMemoryScore = inMemoryScore;
    }
    //все методы ниже просто выполняются классом InMemoryScore
    @Override
    public void addScore(Score score) {
        inMemoryScore.addScore(score);
    }

    @Override
    public Score getScore(String name, String subject) throws StudentNotFoundExeption {
        return inMemoryScore.getScore(name, subject);
    }

    @Override
    public double getAverageForSubject(String subject) {
//        double integer = cache.get(subject);
//        if (integer==0.0){
//            integer = inMemoryScore.getAverageForSubject(subject);
//            cache.put(subject, integer);
//        }
//        return integer;
        return cache.computeIfAbsent(subject, inMemoryScore::getAverageForSubject);//вычисляет значение с cache, если значение отсутствует, то значение берется с другого места
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        return inMemoryScore.multipleSubmissionsStudentNames();
    }

    @Override
    public Collection<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        return inMemoryScore.lastFiveStudentsWithExcellentMarkOnAnySubject();
    }

    @Override
    public Collection<String> getAllScores() {
        return inMemoryScore.getAllScores();
    }

    @Override
    public Map<String, Score> getAllStudentsResult() {
        return inMemoryScore.getAllStudentsResult();
    }
}
class LRUCache<KEY, VALUE> extends LinkedHashMap<KEY, VALUE> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<KEY, VALUE> eldest) {
        return size() > capacity;
    }
}
