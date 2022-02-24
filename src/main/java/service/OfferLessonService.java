package service;

import entity.OfferLesson;
import repository.OfferLessonRepository;

import java.sql.SQLException;
import java.util.List;

public class OfferLessonService {
    private OfferLessonRepository offerLessonRepository = new OfferLessonRepository();

    public OfferLessonService() throws SQLException, ClassNotFoundException {
    }

    public List<OfferLesson> findAll() throws SQLException {
        return offerLessonRepository.findAll();
    }

    public void addOfferLesson(OfferLesson offerLesson) throws SQLException {
        offerLessonRepository.add(offerLesson);
    }


}
