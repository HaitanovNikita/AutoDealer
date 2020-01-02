package tables;

import javax.persistence.*;

@Entity
@Table(name = "EngineCar")
public class EngineCar {

    private int ID;
    private String type_engine;

    public EngineCar() {

    }

    public EngineCar(int iD, String type_engine) {
        super();
        ID = iD;
        this.type_engine = type_engine;
    }

    @Id
    @JoinColumn(name = "ID")
    public int getID() {
        return ID;
    }

    @Column(name = "type_engine")
    public String getType_engine() {
        return type_engine;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public void setType_engine(String type_engine) {
        this.type_engine = type_engine;
    }


    @Override
    public String toString() {
        return ID + " " + type_engine;
    }
}
