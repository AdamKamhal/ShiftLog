import java.util.*;

public class EquipmentMenu {
  // private List<Equipment> equipments =new ArrayList<>();
   private Map<Integer,Equipment> equipments = new HashMap<>();

    public void menu(){
        while (true) {
            System.out.println("""
                    Pick one of Selections:
                    1 - Add Equipment
                    2 - Show Equipment
                    3 - Find Equipment
                    4 - Remove Equipment
                    0 - Back""");




                switch (HelpMethods.InputInteger()) {
                    case 0-> {
                        return;
                    }
                    case 1->{
                        AddEquipent();
                    }
                    case 2-> {
                        ShowEquipment();
                    }
                    case 3 ->{
                        FindEquipment();
                    }
                    case 4 ->{
                        removeEquipment();
                    }
                }
        }
    }

    public void AddEquipent() {
        int ID = addUniqueID();
        System.out.println("Enter equipment name:");
        String name = HelpMethods.InputString();
        System.out.println("Enter location of equipment");
        String location = HelpMethods.InputString();
        Equipment equipment = new Equipment(name, ID, location);
        equipments.put(ID, equipment);
        System.out.println("Successfully create new Equipment");
    }

    private int addUniqueID () {
        System.out.println("Enter equipment ID:");
        while (true) {
            int inputID = HelpMethods.InputInteger();
            if (!equipments.containsKey(inputID)) {
                return inputID;
            }
            System.out.println("ID is already use");
        }
    }

    public void ShowEquipment(){
            if(equipments.isEmpty()) {
                System.out.println("NO logged Equipment.");
                return;
            }
            System.out.println("Showing All Equipment logs");
            for(Equipment equipment : equipments.values()){
                System.out.println("=================");
                System.out.println(equipment);
                System.out.println("=================");
            }
    }

    public void FindEquipment(){
        if(!equipments.isEmpty()) {  //notgate
                    System.out.println("Enter ID number of ");
                    int keySearch = HelpMethods.InputInteger();
                    Equipment equipment = equipments.get(keySearch);
                    if(equipment!=null){
                        System.out.println(equipment);
                        return;
                    }
                    System.out.println("Equipment not found");
                    return;
        }
        System.out.println("No Logged Equipment");
    }

    public void removeEquipment(){
        if(equipments.isEmpty()){
            System.out.println("No equipments logged.");
            return;
        }
        System.out.println("Enter ID of Equipment to remove");
        int idToRemove = HelpMethods.InputInteger();
        if(equipments.remove(idToRemove)!=null){
            System.out.println("Equipment has been successfully removed.");
            return;
        }
        System.out.println("Invalid ID input!");
    }

    public Map<Integer,Equipment> getEquipments(){
        return Collections.unmodifiableMap(equipments);
    }

}


