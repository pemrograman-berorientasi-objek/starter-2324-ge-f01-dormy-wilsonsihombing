package pbo.f01.model;

import javax.persistence.*;

@Entity
@Table(name = "dorm")
public class Dorm {
    @Id
    @Column(name = "name_dorm", nullable = false , length = 225)
    private String dorm_name;

    @Column(name = "dorm_capacity", nullable = false)
    private int capacity;
    
    @Column(name = "dorm_type", nullable = false , length = 225)
    private String type;
    
    @Column(name = "dorm_fill", nullable = false)
    private int fill;

    public Dorm() {
    
    }

    public Dorm(String dorm_name, int capacity, String type) {
        this.dorm_name = dorm_name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getDorm_name() {
        return dorm_name;
    }

    public void setDorm_name(String dorm_name) {
        this.dorm_name = dorm_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void Setfill(int fills){
        this.fill = fill + fills;
    }

    public int getfill(){
        return fill;
    }

    @Override
    public String toString() {
        return dorm_name + "|" + type + "|" + capacity + "|" +fill;
    }
    
}
