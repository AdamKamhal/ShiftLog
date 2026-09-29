
public class Main {
    public static void main(String[] args) {
        EquipmentMenu equipmentMenu = new EquipmentMenu();
        EventMenu eventMenu = new EventMenu();
        System.out.println("=======================" + "\n|      ShiftLogs      |" + "\n=======================");
        while (true) {
            System.out.println("Pick one of Selections: " +
                    "\n1 - Equipment" +
                    "\n2 - Events/Logs" +
                    "\n0 - Exit");


            switch (HelpMethods.InputInteger()) {
                case 0-> {
                    return;
                }
                case 1->{
                    equipmentMenu.menu();
                }
                case 2->{
                    eventMenu.menu(equipmentMenu.getEquipments());
                }
            }
        }
    }
}



