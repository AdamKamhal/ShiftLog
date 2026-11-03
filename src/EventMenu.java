import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.lang.IO.*;


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
                     6 - All Events sorted by date
                     7 - Advanced filtering
                     8 - Statistics
                     0 - Back""");

            switch (HelpMethods.InputInteger()) {
                case 0-> {return;}
                case 1-> addEvent(equipments);
                case 2 -> showEvents(equipments);
                case 3 -> findEventsByEquipment(equipments);
                case 4 -> filterEventsByType(equipments);
                case 5 -> filterEventsByPriority(equipments);
                case 6 -> sortEventsByDateTime(equipments);
                case 7 -> advancedFilterMenu(equipments);
                case 8 -> showEventStatistics(equipments);
                default -> println("Invalid input!");
            }
        }
    }

    private void addEvent(Map<Integer,Equipment> equipments){
        if(equipments.isEmpty()){
            System.out.println("No equipments available to add event");
            return;
        }
        AddEvent createEvent =new AddEvent();
        Event newEvent = createEvent.addEvent(equipments);
        newEvent.getEquipment().addEvent(newEvent);
    }

    private void showEvents(Map<Integer,Equipment> equipments) {
        if (equipments.isEmpty()) {
            System.out.println("No logged Equipment!");
            return;
        }
        System.out.println("Showing all save Evens");
        for (Equipment equipment : equipments.values()){
            if(!equipment.getEvents().isEmpty()) {
                equipment.getEvents().forEach(System.out::println);
            }
        }
    }

    private void sortEventsByDateTime(Map<Integer,Equipment> equipments){
       List<Event> event= getAllEvents(equipments)
                 .sorted(Comparator.comparing(Event::getDate).reversed())
                 .toList();
       if(event.isEmpty()){
           println("No logged Events");
           return;
        }
       event.forEach(System.out::println);
    }

    private Stream<Event> getAllEvents(Map<Integer,Equipment> equipments){
        return equipments.values().stream().flatMap(equipment -> equipment.getEvents().stream());
    }



    private void findEventsByEquipment(Map<Integer,Equipment> equipments){
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

    private void filterEventsByType(Map<Integer,Equipment> equipments){
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

    private void filterEventsByPriority(Map<Integer,Equipment> equipments){
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

    private void advancedFilterMenu(
            Map<Integer, Equipment> equipments) {
        println("Choose at least one filter!");
        Type typeFilter = askEventType();
        Priority priorityFilter = askPriority();
        LocalDateTime dateTimeFilter = askFromDate();

        advancedFilter(equipments,typeFilter,priorityFilter,dateTimeFilter);
    }

    private Type askEventType() {
        Type [] types =Type.values();
            int i=1;
            for (Type type : types){
                println(i + ": " + type.name());
                i++;
            }
        while (true) {
            println("Choose type filter , 0 for ignoring this filter");
            int filter = HelpMethods.InputInteger();
            if(filter==0){
                return null;
            }
            if(filter>0 && filter<= types.length) {
                return types[filter-1];
            }
            println("Invalid input, try again!");
        }
    }

    private Priority askPriority() {
        Priority [] priorities =Priority.values();
        int i = 1;
        for (Priority priority:priorities){
            println(i + ": " + priority.name());
            i++;
        }
        while (true) {
            println("Choose priority filter, 0 for ignoring this filter");
            int filter = HelpMethods.InputInteger();
            if(filter==0){
                return null;
            }
            if(filter>0 && filter<=priorities.length){
                return priorities[filter-1];
            }
            println("Invalid input , try again!");
        }
    }

    private LocalDateTime askFromDate() {
        while (true) {
            try {
                println("Enter date: DD.MM.YYYY or 0 for skip");
                String dateFilter = HelpMethods.InputString();
                if (dateFilter.equals("0")) {
                    return null;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                LocalDateTime date = LocalDate.parse(dateFilter, formatter).atStartOfDay();
                return date;


            }catch(DateTimeException e){
            println("Invalid date enter");
            }
        }
    }

    private void advancedFilter(
            Map<Integer, Equipment> equipments,
            Type type,
            Priority priority,
            LocalDateTime fromDate){
        if (type==null && priority==null && fromDate == null){
            println("No filter selected");
            return;
        }

       List<Event> list = getAllEvents(equipments)
                .filter(event -> type==null || event.getEventType() == type)
                .filter(event -> priority ==null || event.getPriority() == priority)
                .filter(event -> fromDate==null || !event.getDate().isBefore(fromDate))
                .toList();
       if(list.isEmpty()){
           println("No event match choose conditions");
           return;
       }
        list.forEach(System.out::println);
    }

    private <T> void genericsFilter(Map<Integer,Equipment> equipments, T filter, Function<Event,T> function){
      List<Event> eventList = getAllEvents(equipments)
               .filter(event -> filter.equals(function.apply(event)))
              .toList();

           if(eventList.isEmpty()){
               System.out.println("No event find");
               return;
           }
           eventList.forEach(System.out::println);
    }

    private void showEventStatistics(
            Map<Integer, Equipment> equipments){
        List<Event> events = getAllEvents(equipments).toList();
        println("===== EVENTS STATISTICS =====");

        println("Total Events: " + events.size());

        println("Events by priority:");
        Map<Priority,Long> priorityCount = events.stream()
                .collect(Collectors.groupingBy(Event::getPriority,Collectors.counting()));
        for (Priority priority:Priority.values()){
            println(priority.name() + ": " + priorityCount.getOrDefault(priority,0L));
        }

        println("Events by type:");
        Map<Type,Long> typeCount = events.stream()
                .collect(Collectors.groupingBy(Event::getEventType,Collectors.counting()));
        for(Type type:Type.values()){
            println(type.name() + ": " + typeCount.getOrDefault(type,0L));
        }
    }
}
