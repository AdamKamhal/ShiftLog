import java.sql.SQLException;

import static java.lang.IO.println;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager.createTables();
            EquipmentMenu equipmentMenu = new EquipmentMenu();
            EventMenu eventMenu = new EventMenu();

            println("""
                    =======================
                    |      ShiftLogs      |
                    =======================""");
            while (true) {
                println("""
                        Pick one of Selections:
                        1 - Equipment
                        2 - Events/Logs
                        0 - Exit""");

                switch (HelpMethods.InputInteger()) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> equipmentMenu.menu();
                    case 2 -> eventMenu.menu(equipmentMenu.getEquipments());
                }
            }
        }catch (SQLException e){
            println("SQL error " + e.getMessage());
        }
    }
}



