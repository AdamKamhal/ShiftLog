import java.util.*;
import java.util.function.*;


public class EventMenu {

    public void menu(Map<Integer,Equipment> equipments){
        while (true) {
            System.out.println("""
                     Pick one of Selections:
                     1 - Add Event
                     2 - Show Events
                     3 - Find Events by Equipment
                     4 - All Events by type
                     5 - All Events by priority
                     0 - Back""");


            switch (HelpMethods.InputInteger()) {
                case 0-> {
                    return;
                }
                case 1->{
                    addEvent(equipments);
                }
                case 2 ->{
                    showEvents(equipments);
                }
                case 3 ->{
                    findEventsByEquipment(equipments);
                }
                case 4 ->{
                    filterEventsByType(equipments);
                }
                case 5 -> {
                    filterEventsByPriority(equipments);
                }
            }
        }
    }
    public void addEvent(Map<Integer,Equipment> equipments){
        if(equipments.isEmpty()){
            System.out.println("No equipments available to add event");
            return;
        }
        AddEvent createEvent =new AddEvent();
        Event newEvent = createEvent.addEvent(equipments);
        newEvent.getEquipment().getEvents().add(newEvent);
    }

    public void showEvents(Map<Integer,Equipment> equipments) {
        if (equipments.isEmpty()) {
            System.out.println("No logged Equipment!");
            return;
        }
        System.out.println("Showing all save Evens");
        for (Equipment equipment : equipments.values()){
            if(!equipment.getEvents().isEmpty()) {
                equipment.getEvents().forEach(event -> System.out.println(event));
            }
        }
    }

    public void findEventsByEquipment(Map<Integer,Equipment> equipments){
        if(equipments.isEmpty()){
            System.out.println("No logged Equipment!");
            return;
        }
        while (true){
            System.out.println("Enter equipment ID");
            int inputID = HelpMethods.InputInteger();
            Equipment equipment = equipments.get(inputID);
            if(equipment!=null){
                if(!equipment.getEvents().isEmpty()) {
                    equipment.getEvents().forEach(event -> System.out.println(event));
                    return;
                }
                System.out.println("has no saved events");
                return;
            }
            System.out.println("ID not found");
        }
    }

    public void filterEventsByType(Map<Integer,Equipment> equipments){
        Type[]types=Type.values();
        while (true) {
            int i =0;
            System.out.println("Enter filter to use");
            for (Type t : types) {
                System.out.println(i + " - " + t);
                i++;
            }
            int typesInput = HelpMethods.InputInteger();
            if (typesInput >= 0 && typesInput < types.length) {
                Type filterType = types[typesInput];
                genericsFilter(equipments,filterType,event -> event.getEventType());
                return;
            }
            System.out.println("Invalid input");
        }
    }

    public void filterEventsByPriority(Map<Integer,Equipment> equipments){
        Priority[]priorities=Priority.values();
        while(true){
            int i =0;
            System.out.println("Enter priority!");
            for(Priority priority:priorities){
                System.out.println(i + " - " + priority);
                i++;
            }
            int priorityInput = HelpMethods.InputInteger();
            if(priorityInput>= 0 && priorityInput < priorities.length){
                Priority priorityFilter = priorities[priorityInput];
                genericsFilter(equipments,priorityFilter,event -> event.getPriority());
                return;
            }
            System.out.println("Invalid input");
        }
    }

    private <T> void genericsFilter(Map<Integer,Equipment> equipments, T filter, Function<Event,T> function){
        boolean findSomething =false;
           for (Equipment equipment: equipments.values()){
               for(Event event: equipment.getEvents()){
                 T value = function.apply(event);
                 if(value.equals(filter)){
                     System.out.println(event);
                     findSomething=true;
                 }
               }
           }
           if(!findSomething){
               System.out.println("No event find");
           }
    }
}
