package Entity;

public class Lesson {
    private int idStudent,quarterNumber,year,unitNumber,grade;
    private String lessonName,lastProfessorName;

    public Lesson(int idStudent, int quarterNumber, int year, int unitNumber, int grade, String lessonName, String lastProfessorName) {
        this.idStudent = idStudent;
        this.quarterNumber = quarterNumber;
        this.year = year;
        this.unitNumber = unitNumber;
        this.grade = grade;
        this.lessonName = lessonName;
        this.lastProfessorName = lastProfessorName;
    }

    public Lesson() {
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getQuarterNumber() {
        return quarterNumber;
    }

    public void setQuarterNumber(int quarterNumber) {
        this.quarterNumber = quarterNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLastProfessorName() {
        return lastProfessorName;
    }

    public void setLastProfessorName(String lastProfessorName) {
        this.lastProfessorName = lastProfessorName;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "idStudent=" + idStudent +
                ", quarterNumber=" + quarterNumber +
                ", year=" + year +
                ", unitNumber=" + unitNumber +
                ", grade=" + grade +
                ", lessonName='" + lessonName + '\'' +
                ", lastProfessorName='" + lastProfessorName + '\'' +
                '}';
    }
}
