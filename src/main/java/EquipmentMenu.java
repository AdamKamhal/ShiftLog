import java.sql.*;
import java.util.*;
import static java.lang.IO.*;

public class EquipmentMenu {
   private Map<Integer,Equipment> equipments = new HashMap<>();

   public EquipmentMenu() throws SQLException{
           equipments.putAll(DatabaseManager.loadEquipments());
           DatabaseManager.loadEvents(equipments);
   }
    public void menu(){
        while (true) {
            System.out.println("""
                    Pick one of Selections:
                    1 - Add Equipment
                    2 - Show Equipment
                    3 - Find Equipment
                    4 - Remove Equipment
                    5 - Update Equipment
                    0 - Back""");

                switch (HelpMethods.InputInteger()) {
                    case 0-> {
                        return;
                    }
                    case 1-> addEquipent();
                    case 2-> showEquipment();
                    case 3 -> FindEquipment();
                    case 4 -> removeEquipment();
                    case 5 -> updateEquipment();
                    default -> println("Invalid input");
                }
        }
    }

    private void addEquipent() {
        try {
            println("ID is generated automatically");
            System.out.println("Enter equipment name:");
            String name = HelpMethods.InputString();
            System.out.println("Enter location of equipment");
            String location = HelpMethods.InputString();
            int ID = DatabaseManager.insertEquipment(name,location);
            Equipment equipment = new Equipment(name, ID, location);
            equipments.put(ID, equipment);
            System.out.println("Successfully create new Equipment");
        }catch (SQLException e){
            println(e.getMessage());
        }
    }


    private void showEquipment(){
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

    private void FindEquipment(){
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

    private void removeEquipment(){
       try {
           if (equipments.isEmpty()) {
               println("No equipments logged.");
               return;
           }
           println("Enter ID of Equipment to remove");
           int idToRemove = HelpMethods.InputInteger();
           if (DatabaseManager.deleteEquipment(idToRemove)) {
               Equipment removed = equipments.remove(idToRemove);
               println(removed.getName() + " has been successfully removed");
               return;
           }
           println("Invalid ID input!");

       }catch (SQLException e){
           println("Fail to delete equipment from database " + e.getMessage());
       }
    }

    private void updateEquipment(){
       try {
           if(equipments.isEmpty()){
               println("No equipments logged.");
               return;
           }
           println("Enter ID of Equipment to Update!");
           int idToUpdate = HelpMethods.InputInteger();
           if(!equipments.containsKey(idToUpdate)){
               println("Equipment not found");
               return;
           }
           println("Enter new name of equipment");
           String name = HelpMethods.InputString();
           println("Enter new location of equipment");
           String location = HelpMethods.InputString();
           if(DatabaseManager.updateEquipment(idToUpdate,name,location)){
               Equipment equipment=equipments.get(idToUpdate);
               equipment.setName(name);
               equipment.setLocation(location);
               println(equipment.getName() + " has been successfully updated" );
           }
       }catch (SQLException e){
           println("Fail to update equipment in database " + e.getMessage());
       }
    }

    public Map<Integer,Equipment> getEquipments(){
        return Collections.unmodifiableMap(equipments);
    }


}


