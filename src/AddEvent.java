import java.util.List;

public class AddEvent {

    public Event addEvent(List<Equipment> equipments){
        System.out.println("Enter date of event!");
        int date = HelpMethods.InputInteger();

        System.out.println("Enter your lastname!");
        String worker = HelpMethods.InputString();

        System.out.println("Choose Equipment!");
        Equipment equipment = findEquipment(equipments);

        System.out.println("Enter type of event!");
        String type = HelpMethods.InputString();

        System.out.println("Enter priority of event!");
        String priority = HelpMethods.InputString();

        System.out.println("Write short description of event!");
        String log = HelpMethods.InputString();

        return new Event(date,worker,equipment,type,priority,log);
    }
    private Equipment findEquipment(List<Equipment> equipments){
            System.out.println("Enter number of equipment!");
            while (true) {
                int i = 0;
                for (Equipment eq : equipments) {
                    System.out.println(i + ": " + eq.getName());
                    i++;
                }
                int input = HelpMethods.InputInteger();
                if (input > -1 && input < equipments.size()) {
                    Equipment equipment = equipments.get(input);
                    return equipment;
                } else {
                    System.out.println("Invalid input, try again");
                }
            }


    }
}
