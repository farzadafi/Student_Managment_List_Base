package Entity;

public class OfferLesson {
    int id;
    private String lessonName;
    private int unitNumber;

    public OfferLesson(String lessonName, int unitNumber) {
        this.lessonName = lessonName;
        this.unitNumber = unitNumber;
    }

    public OfferLesson() {
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
