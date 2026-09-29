public class Event {
    private int date;
    private String workerName;
    private Equipment equipment;
    private Type eventType;
    private Priority priority;
    private String log;

    public Event(int date,String workerName,Equipment equipment,
                 Type eventType,Priority priority,String log){
        this.date=date;
        this.workerName=workerName;
        this.equipment=equipment;
        this.eventType=eventType;
        this.priority=priority;
        this.log=log;
    }
    public String toString(){
        return "---->Log/Event<---- " +
                "\nDate: " + date +
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
}
