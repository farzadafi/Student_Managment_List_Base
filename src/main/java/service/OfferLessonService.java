package service;

import entity.OfferLesson;
import repository.OfferLessonRepository;

import java.sql.SQLException;
import java.util.List;

public class OfferLessonService {
    private OfferLessonRepository offerLessonRepository = new OfferLessonRepository();

    public OfferLessonService() {
    }

    public List<OfferLesson> findAll() {
        try {
            return offerLessonRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void addOfferLesson(OfferLesson offerLesson) {
        try {
            offerLessonRepository.add(offerLesson);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
