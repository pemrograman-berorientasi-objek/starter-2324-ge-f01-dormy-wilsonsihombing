package pbo.f01.model;

import java.util.*;

import javax.persistence.*;
public class DriveApp {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    private static ArrayList<Student> containerStd = new ArrayList<Student>();
    private static ArrayList<Dorm> containerDrm = new ArrayList<Dorm>();
    private static ArrayList<Student> DormK = new ArrayList<Student>();
    private static ArrayList<Student> DormP = new ArrayList<Student>();
    private static ArrayList<Student> DormM = new ArrayList<Student>();

    static int countmale = 0;
    static int countfemale = 0;
    
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

        for(Student std : containerStd){
            if(std.getGender().equals("male")){
                countmale = countmale + 1;
            }else{
                countfemale = countfemale + 1;
            }
        }
        
        if(cek == false && countmale < 6 && countfemale < 6){
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
        String showstd = "SELECT s FROM Student s WHERE s.gender = :type ORDER BY s.name_student ASC";
        List<Student> students = entityManager.createQuery(showstd, Student.class).
                setParameter("type", type).    
                getResultList();

        String showdorm = "SELECT d FROM Dorm d ORDER BY d.dorm_name ASC";
        List<Dorm> dorm = entityManager.createQuery(showdorm, Dorm.class).   
                getResultList();
        

        // // for(Dorm drm : containerDrm ){
        // //     for(Student std : students){
        // //         if(std.getGender().equals("male") && countmale < 6 && drm.getDorm_name().equals("") ){
        // //             countmale = countmale + 1;
        // //             System.out.println(std.toString());
        // //         }else(std.getGender().equals("female") && countfemale < 6)
        // //         System.out.println(std.toString());
        // //     }
        // // }
        // int jlh = students.size();

        // for(Dorm drm : dorm){
        //     for(Student std : students){
        //             if(drm.getDorm_name().equals(dorm_name) && std.getGender().equals("male") && jlh < 6){
        //                 System.out.println(std.toString());
        //             }else if(drm.getDorm_name().equals(dorm_name) && std.getGender().equals("female") && jlh < 6){
        //                 System.out.println(std.toString());
        //             }
        //     }
        // 
        // for(Dorm drm : dorm){
        //     if(drm.getDorm_name().equals("Kapernaum")){
        //         for(int i = 0; i < 5 ; i++){
        //             System.out.println(.toString());
        //         }
        //     }else if(drm.getDorm_name().equals("Mamre")){
        //         for(int i = 0; i < 5 ; i++){
        //             System.out.println(DormM.toString());
        //         }
        //     }else{
        //         for(int i = 0; i < 5 ; i++){
        //             System.out.println(DormP.toString());
        //         }
        //     }
        // }
           

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

                            Student newStd = new Student(std.getId_student(), std.getName_student(), std.getYear(),std.getGender());
                            if(drm.getDorm_name().equals("Kapernaum")){
                                DormK.add(newStd);
                            }else if(drm.getDorm_name().equals("Mamre")){
                                DormM.add(newStd);
                            }else{
                                DormP.add(newStd);
                            }

                            entityManager.getTransaction().begin();
                            drm.Setfill(1);
                            entityManager.flush();
                            entityManager.getTransaction().commit();
                        }
                    }
                }
            }
        }
    }

}
