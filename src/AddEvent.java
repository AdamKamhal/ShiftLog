import java.util.*;

public class AddEvent {

    public Event addEvent(Map<Integer,Equipment> equipments){
        System.out.println("Enter date of event!");
        int date = HelpMethods.InputInteger();

        System.out.println("Enter your lastname!");
        String worker = HelpMethods.InputString();

        System.out.println("Choose Equipment!");
        Equipment equipment = findEquipment(equipments);

        Type type =chooseType();

        Priority priority = choosePriority();

        System.out.println("Write short description of event!");
        String log = HelpMethods.InputString();

        return new Event(date,worker,equipment,type,priority,log);
    }
    private Equipment findEquipment(Map<Integer,Equipment> equipments){
            while (true) {
                System.out.println("Enter ID of equipment!");
                for(Map.Entry<Integer,Equipment> entry : equipments.entrySet()){
                    System.out.println("ID: " + entry.getKey() + " - " + entry.getValue().getName());
                }
                int idInput =HelpMethods.InputInteger();
                Equipment equipment = equipments.get(idInput);
                if(equipment!=null){
                    return equipment;
                }
                System.out.println("Invalid ID");
            }
    }

    private Priority choosePriority() {
        Priority[]priorities = Priority.values();
        while (true) {
            System.out.println("Enter priority of event!");
            int i=0;
            for(Priority priority: priorities){
                System.out.println(i + " - " + priority);
                i++;
            }
            int priorityInput=HelpMethods.InputInteger();
            if(priorityInput>=0 && priorityInput<priorities.length ){
                return priorities[priorityInput];
            }
            System.out.println("Invalid input");
        }
    }

    private  Type chooseType(){
        Type[]types = Type.values();
        while (true){
            System.out.println("Enter type of event!");
            int i=0;
            for (Type type:types){
                System.out.println(i + " - " + type);
                i++;
            }
            int typeInput =HelpMethods.InputInteger();
            if(typeInput>=0 && typeInput<types.length){
                return types[typeInput];
            }
            System.out.println("Invalid input!");
        }
    }
}
