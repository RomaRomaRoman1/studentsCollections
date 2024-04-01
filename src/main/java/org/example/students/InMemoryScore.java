package org.example.students;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryScore implements Examination{
    private int calls = 0;

    private static final int INITIAL_CAPACITY = 256;
    private static final Map <String, Score> scores = new LinkedHashMap<>(INITIAL_CAPACITY);
    private static final Set<String> badStudent = new HashSet<>(INITIAL_CAPACITY);//список сдающих не впервые экзамен
    @Override
    public void addScore(Score score) {
        if ((scores.get(score.name()+score.subject()))!=null){
            badStudent.add(score.name());
        }
    scores.put(score.name()+score.subject(), score);
    }

    @Override
    public Score getScore(String name, String subject) throws StudentNotFoundExeption{
        Score score = scores.get(name + subject);
        if(score==null) {
            throw new StudentNotFoundExeption(name + subject);
        }
        return scores.get(name+subject);

    }

    @Override
    public double getAverageForSubject(String subject) {
        List <String> matchingKeys = scores.keySet().stream().filter(key->key.contains(subject)).collect(Collectors.toList());
        //сортируем карту по ключу, делаем список, который содержит в ключе предмет
        calls++;//счетчик вызова метода
        if (matchingKeys.isEmpty()) {//если нет предмета, то возвращаем 0.0
            return 0.0;
        }
        double sum=0.0;//начальное значение суммы оценок
        int count = 0;//счетчик для получения количества оценок
        for(String key: matchingKeys) {//итерация по списку
            Score score = scores.get(key);//создаем новые объекты Score
            sum+=score.score();//суммируем все оценки
            count++;//суммируем количество оценок
        }
        return sum/count;//вычисляем среднюю оценку (сумму всех оценок делим на количество оценок
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
    return badStudent;//просто возвращаем множество
    }

    @Override
    public Collection<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        List<String> allGoodStudents =  scores.values().stream().//делаем поток из значений таблицы студентов
                filter(score -> score.score()==5).//фильтруем значения только студентов сдавших на отлично
                map(Score::name).//преобразуем объекты в поле объекта name
                collect(Collectors.toCollection(LinkedList::new));//собираем все в LinkedList
        Collections.reverse(allGoodStudents);//меняем порядок элементов в коллекции
        List<String> lastGoodStudent = allGoodStudents.stream().
                limit(5).
                collect(Collectors.toList());//собираем список с лимитом позиций в нем 5

        return lastGoodStudent;//возвращаем список 5-ти последних студентов
    }

    @Override
    public Collection<String> getAllScores() {
        Set<String> setAllSubject=new HashSet<>();
        scores.values().forEach(score -> setAllSubject.add(score.subject()));//итерируемся по всем значениям карты и добавляем предметы в множество Set
        return setAllSubject;
    }

    @Override
    public Map<String, Score> getAllStudentsResult() {
        return new LinkedHashMap<>(scores);//просто возвращаем новую карту
    }
    public int getCalls() {
        return calls;
    }
}

