package ite.librarymaster.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

@Component
public class AdaptiveTrigger implements Trigger {
    private final Logger LOG = LoggerFactory.getLogger(AdaptiveTrigger.class);

    @Override
    public Instant nextExecution(TriggerContext triggerContext) {
        int WORKING_DAY_PERIOD_SECONDS = 20;
        int WEEKEND_DAY_PERIOD_SECONDS = 300;
        Instant lastExecution = triggerContext.lastActualExecution();
        Calendar nextExecution = Calendar.getInstance();
        if(lastExecution != null) {
            // Find week day of last execution
            ZonedDateTime zonedDateTime = lastExecution.atZone(ZoneId.systemDefault());
            DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
            if(dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY) ){
                nextExecution.add(Calendar.SECOND, WEEKEND_DAY_PERIOD_SECONDS);
                LOG.info("We have weekend day ...");
            } else {
                nextExecution.add(Calendar.SECOND, WORKING_DAY_PERIOD_SECONDS);
                LOG.info("We have working day ...");
            }
        } else{
            LOG.info("Initializing next execution ...");
            nextExecution.setTime(new Date());
        }
        return nextExecution.toInstant();
    }
}
