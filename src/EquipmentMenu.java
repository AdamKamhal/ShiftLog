import java.util.ArrayList;
import java.util.List;

public class EquipmentMenu {
   private List<Equipment> equipments =new ArrayList<>();

    public void menu(){
        while (true) {
            System.out.println("Pick one of Selections: " +
                    "\n1 - Add Equipment" +
                    "\n2 - Show Equipment" +
                    "\n3 - Find Equipment" +
                    "\n0 - Back");


                switch (HelpMethods.InputInteger()) {
                    case 0:
                        return;
                    case 1:
                        AddEquipent();
                        break;
                    case 2:
                        ShowEquipment();
                        break;
                    case 3:
                        FindEquipment();
                        break;
                }
        }
    }

    public void AddEquipent(){
        System.out.println("Enter equipment name:");
        String name =HelpMethods.InputString();
        int ID=addUniqueID();
        System.out.println("Enter location of equipment");
        String location=HelpMethods.InputString();
            Equipment equipment = new Equipment(name, ID, location);
            System.out.println("Successfully create new Equipment");
            equipments.add(equipment);
    }

    private int addUniqueID () {
        System.out.println("Enter equipment ID:");
        while (true) {
            int idInput = HelpMethods.InputInteger();
            if (!equipments.isEmpty()) {
                boolean duplicity = false;
                for (Equipment eq : equipments) {
                    if (eq.getID().equals(idInput)) {
                        duplicity = true;
                        break;
                    }
                }
                if (!duplicity) {
                    return idInput;
                }
                System.out.println("Already used ID, enter again");
            } else {
                return idInput;
            }
        }
    }

    public void ShowEquipment(){
            if(!equipments.isEmpty()) {  //notgate
                int i = 0;
                System.out.println("Pick one Equipment");
                for (Equipment eq : equipments) {
                    System.out.println(i + ": " + eq.getName());
                    i++;
                }
                System.out.println("Enter number of equipment you want show");
                int input=HelpMethods.InputInteger();
                if(input<equipments.size() && input>-1){
                    System.out.println(equipments.get(input));
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            else {
                System.out.println("Equipment list is empty!");
            }
    }

    public void FindEquipment(){
        if(!equipments.isEmpty()) {  //notgate
                    System.out.println("Enter ID number of ");
                    int keySearch = HelpMethods.InputInteger();
                    for (Equipment eq : equipments) {
                        if (eq.getID().equals(keySearch)) {
                            System.out.println(eq);
                            return;
                        }
                    }
                    System.out.println("Equipment not found");
        }
        else {
            System.out.println("Equipment list is empty!");
        }
    }

    public List<Equipment> getEquipments(){
        return equipments;
    }

}


