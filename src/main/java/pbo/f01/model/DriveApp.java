package pbo.f01.model;

import java.util.*;

import javax.persistence.*;
public class DriveApp {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    private static ArrayList<Student> containerStd = new ArrayList<Student>();
    private static ArrayList<Dorm> containerDrm = new ArrayList<Dorm>();
    
    public static void Initialize(){
        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();
    }

    public static void clearTableStudent(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE  FROM Student s").executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static void clearTableDorm(){
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE  FROM Dorm d").executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public static void  addStudent(String id_student, String name_student, String year, String gender){
        boolean cek = false;
        for(Student std : containerStd){
            if(std.getId_student().equals(id_student)){
                cek = true;
                break;
            }
        }

        if(cek == false){
            Student newStudent = new Student(id_student, name_student, year,gender);
            containerStd.add(newStudent);
            entityManager.getTransaction().begin();
            entityManager.persist(newStudent);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        
    }

    public static void  addDorm(String dorm_name, int capacity, String type){
        boolean cek = false;
        for(Dorm drm : containerDrm){
            if(drm.getDorm_name().equals(dorm_name)){
                cek = true;
                break;
            }
        }

        if(cek == false){
            Dorm newDorm = new Dorm(dorm_name, capacity, type);
            containerDrm.add(newDorm);
            entityManager.getTransaction().begin();
            entityManager.persist(newDorm);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
    }

    public static void displayStudent(String type, String dorm_name){
        String showstd = "SELECT s FROM Student s WHERE s.gender = :type AND s.dormy = :dormName ORDER BY s.name_student ASC";
        List<Student> students = entityManager.createQuery(showstd, Student.class).
                setParameter("type", type).
                setParameter("dormName", dorm_name).    
                getResultList();

        for(Student std : students){
            System.out.println(std.toString());
        }  

    }

    public static void displayDorm(){
        String showdrm = "SELECT d FROM Dorm d ORDER BY d.dorm_name ASC";
        List<Dorm> dorms = entityManager.createQuery(showdrm, Dorm.class).
                getResultList();

        for(Dorm drm : dorms){
            System.out.println(drm.toString());
            if(drm.getfill() > 0){
                displayStudent(drm.getType(), drm.getDorm_name());
            }
            
        }
    }

    public static void assignStudent(String id_student, String dorm_name){
        String query1 = "SELECT s FROM Student s ORDER BY s.id_student ASC";
        List<Student> students = entityManager.createQuery(query1, Student.class)
                .getResultList();

        String query2 = "SELECT d FROM Dorm d ORDER BY d.dorm_name ASC";
        List<Dorm> dorms = entityManager.createQuery(query2, Dorm.class)
                .getResultList();

        for(Dorm drm : dorms){
            if(drm.getDorm_name().equals(dorm_name)){
                for(Student std : students){
                    if(std.getId_student().equals(id_student) && drm.getType().equals(std.getGender())){
                        if(drm.getfill() < drm.getCapacity()){
                            entityManager.getTransaction().begin();
                            drm.Setfill(1);
                            std.setDormy(dorm_name);

                            entityManager.flush();
                            entityManager.getTransaction().commit();
                        }
                    }
                }
            }
        }
    }
}
