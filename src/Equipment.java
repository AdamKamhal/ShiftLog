import java.util.ArrayList;
import java.util.List;

public class Equipment {
   private String name;
   private Integer ID;
   private String location;
   private List<Event> events = new ArrayList<>();
    public Equipment(String name,Integer ID,String location){
        this.name=name;
        this.ID=ID;
        this.location=location;
    }
    // toString=============================================

    @Override
    public String toString(){
        return "Name: " + name +
                "\nID: " + ID +
                "\nLocation: " + location;
    }

    //======================================================


    //Getters ==============================================

    public String getName() {
        return name;
    }

    public Integer getID(){
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public List<Event> getEvents(){return events;}

    //========================================================
}
