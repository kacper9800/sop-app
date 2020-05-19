package pl.sop.enums;

public enum EventDuration {

    QUARTER1(0),
    HALFHOUR(1),
    QUARTER3(2),
    ONEHOUR(3),
    ONEHOURANDHALF(4),
    TWOHOURSANDQUARTER(5);

    Integer type;

    EventDuration(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
