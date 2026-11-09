import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.lang.IO.*;

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
    // toString=========================================================================================================

    @Override
    public String toString(){
        return "Name: " + name +
                "\nID: " + ID +
                "\nLocation: " + location;
    }

    //==================================================================================================================

    //Setters===========================================================================================================

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //==================================================================================================================

    //Getters ==========================================================================================================

    public String getName() {
        return name;
    }

    public Integer getID(){
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public List<Event> getEvents(){
        return Collections.unmodifiableList(events);
    }

    //==================================================================================================================

    public void addEvent(Event event){
        if(event==null){
            throw new IllegalArgumentException("Event value cannot be null");
        }
        if(event.getEquipment() != this){
            throw new IllegalArgumentException("this event doesnt belong to this equipment");
        }
        events.add(event);
    }
}
