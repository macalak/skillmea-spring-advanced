package ite.librarymaster.model;

import java.util.Date;

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
