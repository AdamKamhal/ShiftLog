import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventMenu {

    public void menu(List<Equipment> list){
        while (true) {
            System.out.println("Pick one of Selections: " +
                    "\n1 - Add Event" +
                    "\n2 - Show Events" +
                    "\n3 - Find Events by Equipment" +
                    "\n0 - Back");


            switch (HelpMethods.InputInteger()) {
                case 0:
                    return;
                case 1:
                    addEvent(list);
                    break;
                case 2:
                    showEvents(list);
                    break;
                case 3:
                    findEventsByEquipment(list);
                    break;
            }
        }
    }
    public void addEvent(List<Equipment> equipments){
        if(equipments.isEmpty()){
            System.out.println("No equipments available to add event");
            return;
        }
        AddEvent newEvent =new AddEvent();
        Event event =newEvent.addEvent(equipments);
            for (Equipment eq : equipments) {
                if (event.getEquipment().equals(eq)) {
                    eq.getEvents().add(event);
                    System.out.println("Event was successfully added");
                    return;
                }
            }

    }

    public void showEvents(List<Equipment> list){
        if(!list.isEmpty()) {
            for (Equipment eq : list) {
                System.out.println("All events: " +"\n"+eq.getName() + ": ");
                for (Event event : eq.getEvents()){
                    System.out.println(event.toString());
                }
            }
            return;
        }
        System.out.println("Equipment list is empty");
    }

    public void findEventsByEquipment(List<Equipment> equipments){
        if(!equipments.isEmpty()){
            System.out.println("Write ID of equipment!");
            int input=HelpMethods.InputInteger();
            for (Equipment eq : equipments){
                if(eq.getID().equals(input)){
                    for (Event event : eq.getEvents()){
                        System.out.println(event);
                    }
                    return;
                }
            }
            System.out.println("Input ID not match any Equipment ID");
        }
        else {
            System.out.println("Equipment list is empty");
        }

    }
}
