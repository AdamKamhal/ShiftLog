public class Event {
    private int date;
    private String workerName;
    private Equipment equipment;
    private String eventType;
    private String priority;
    private String log;

    public Event(int date,String workerName,Equipment equipment,
                 String eventType,String priority,String log){
        this.date=date;
        this.workerName=workerName;
        this.equipment=equipment;
        this.eventType=eventType;
        this.priority=priority;
        this.log=log;
    }
    public String toString(){
        return "---->Log/Event<---- \n-------------------- \n" +
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
}
