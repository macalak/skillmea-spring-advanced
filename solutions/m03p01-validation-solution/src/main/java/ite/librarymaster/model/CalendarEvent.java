package ite.librarymaster.model;

import org.hibernate.validator.constraints.ScriptAssert;

import java.util.Date;

@ScriptAssert(script = "_this.startDate.before(_this.endDate)",
              lang = "groovy")
public class CalendarEvent {
    private Date startDate;
    private Date endDate;

    public CalendarEvent(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
