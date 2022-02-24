package entity;

public class Student extends Person {
    public Student(String firstName, String lastName, String nationalId, String username, String password) {
        super(firstName, lastName, nationalId, username, password);
    }

    public Student() {
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getNationalId() {
        return super.getNationalId();
    }

    @Override
    public void setNationalId(String nationalId) {
        super.setNationalId(nationalId);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

}
