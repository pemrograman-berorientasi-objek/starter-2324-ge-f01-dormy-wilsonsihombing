package pbo.f01.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false , length = 225)
    private String id_student;
    @Column(name = "student_name", nullable = false , length = 225)
    private String name_student;
    @Column(name = "years", nullable = false , length = 225)
    private String year;
    @Column(name = "genders", nullable = false , length = 225)
    private String gender;
    @Column(name = "dormy_stud", nullable = true, length = 255)
    private String dormy;

    public Student() {
    
    }

    public Student(String id_student, String name_student, String year, String gender) {
        this.id_student = id_student;
        this.name_student = name_student;
        this.year = year;
        this.gender = gender;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDormy(String dorm){
        this.dormy = dorm;
    }

    public String getDormy(){
        return dormy;
    }

    @Override
    public String toString() {
        return id_student + "|" + name_student + "|" + year ;
    }

}
