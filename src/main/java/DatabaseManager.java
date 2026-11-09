import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.IO.*;

public class DatabaseManager {

    //Create tables ====================================================================================================

    public static void createTables() throws SQLException {
       try(Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
           Statement statement = connection.createStatement()) {
           statement.execute("PRAGMA foreign_keys = ON");
           String equipmentsSql = ("CREATE TABLE IF NOT EXISTS equipment (id INTEGER PRIMARY KEY, name TEXT NOT NULL, location TEXT NOT NULL)");
           String eventsSql = ("""
                   CREATE TABLE IF NOT EXISTS events 
                   (id INTEGER PRIMARY KEY, 
                   equipment_id INTEGER NOT NULL , 
                   type TEXT NOT NULL, 
                   priority TEXT NOT NULL,
                   worker_name TEXT NOT NULL, 
                   log TEXT NOT NULL, date TEXT NOT NULL, 
                   FOREIGN KEY (equipment_id) REFERENCES equipment(id))""");
           statement.executeUpdate(equipmentsSql);
           statement.executeUpdate(eventsSql);
           println("Database tables initialized successfully");
       }
    }

    //==================================================================================================================

 /*   public static void showTables() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
             Statement statement = connection.createStatement()){
            String sql =("SELECT name FROM sqlite_master WHERE type = 'table';");
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    println(resultSet.getString("name"));
                }
            }
        }catch (SQLException e){
            println(e.getMessage());
        }
    } */

    //Equipment table ==================================================================================================

    public static int insertEquipment(String name, String location) throws SQLException{
        try (Connection connection =DriverManager.getConnection("jdbc:sqlite:shiftlog.db")){
            String sql ="""
               INSERT INTO equipment (name,location)
               VALUES(? , ?);""";
            try (PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
               ps.setString(1,name);
               ps.setString(2,location);
               ps.executeUpdate();
               try (ResultSet keys =ps.getGeneratedKeys()){
                   if(keys.next()){
                       int id = keys.getInt(1);
                       return id;
                   }
               }
            }
        }
        throw new SQLException("Fail to insert  equipment to Database , or generate ID");
    }

    public static Map<Integer, Equipment> loadEquipments() throws SQLException{
        Map<Integer,Equipment> loaded = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
             Statement statement = connection.createStatement()){
            String sql =("""
                    SELECT id,name,location FROM equipment
                    """);
            try (ResultSet load = statement.executeQuery(sql)){
                while (load.next()){
                    int id =load.getInt("id");
                    String name = load.getString("name");
                    String location = load.getString("location");
                    loaded.put(id,new Equipment(name,id,location));
                }
            }
        }
        return loaded;
    }

    public static boolean deleteEquipment(int id) throws SQLException{
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
            Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
            String sql = ("""
                    DELETE FROM equipment
                    WHERE id = ?""");
            try (PreparedStatement prepared = connection.prepareStatement(sql)){
                prepared.setInt(1,id);
                return prepared.executeUpdate()>0;
            }
        }
    }

    public static boolean updateEquipment(int id, String name, String location) throws SQLException{
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db")){
            String sql =("""
                    UPDATE equipment
                    SET name = ? , location = ?
                    WHERE id = ?""");
            try (PreparedStatement prepared = connection.prepareStatement(sql)){
                prepared.setString(1,name);
                prepared.setString(2,location);
                prepared.setInt(3,id);
                return prepared.executeUpdate()>0;
            }

        }
    }

    //==================================================================================================================

    //Event table ======================================================================================================

    public static int insertEvent(Event event) throws SQLException{
        try (Connection connection =DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
             Statement statement = connection.createStatement()){
            statement.execute("PRAGMA foreign_keys = ON");
            String sql = """
                    INSERT INTO events (equipment_id,type,priority,worker_name,log,date)
                    VALUES (?,?,?,?,?,?);
                    """;
            try (PreparedStatement prepared = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                prepared.setInt(1,event.getEquipment().getID());
                prepared.setString(2,event.getEventType().name());
                prepared.setString(3,event.getPriority().name());
                prepared.setString(4,event.getWorkerName());
                prepared.setString(5, event.getLog());
                prepared.setString(6,event.getDate().toString());
                prepared.executeUpdate();
                try (ResultSet keys = prepared.getGeneratedKeys()) {
                    if(keys.next()){
                        int id = keys.getInt(1);
                        return id;
                    }
                }
            }
        }
        throw new SQLException("Fail to insert event into database");
    }

    public static void loadEvents(Map<Integer, Equipment> equipments) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:shiftlog.db");
             Statement statement =connection.createStatement()){
            String sql = """
                    SELECT * FROM events
                    """;
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    Equipment equipment = equipments.get(resultSet.getInt("equipment_id"));
                    if(equipment==null){
                        println("Log with no equipment");
                        continue;
                    }
                    Type type =Type.valueOf(resultSet.getString("type"));
                    Priority priority = Priority.valueOf(resultSet.getString("priority"));
                    String workerName = resultSet.getString("worker_name");
                    String log = resultSet.getString("log");
                    LocalDateTime date = LocalDateTime.parse(resultSet.getString("date"));
                    equipment.addEvent(new Event(workerName,equipment,type,priority,log,date));
                }
            }
        }
    }

    //==================================================================================================================
}
