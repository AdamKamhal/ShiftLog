import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Event {
    private final LocalDateTime date;
    private final String workerName;
    private final Equipment equipment;
    private final Type eventType;
    private final Priority priority;
    private final String log;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.forLanguageTag("sk-SK"));

    public Event(String workerName,Equipment equipment,
                 Type eventType,Priority priority,String log){
        this.date=LocalDateTime.now();
        this.workerName=workerName;
        this.equipment=equipment;
        this.eventType=eventType;
        this.priority=priority;
        this.log=log;
    }
    public String toString(){
        return "---->Log/Event<---- " +
                "\nDate: " + date.format(DATE_FORMAT) +
                "\nWorker: " +workerName +
                "\nEquipment: " + equipment +
                "\nType: " +eventType +
                "\nPriority: " +priority +
                "\nLog: " +log;
    }


    public Equipment getEquipment(){
        return equipment;
    }

    public Type getEventType() {
        return eventType;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getDate(){
        return date;
    }
}
