package processscheduller;

import java.util.Collections;
import java.util.List;

public class FirstComeFirstServe extends CPUScheduler
{
    FirstComeFirstServe() 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void process()
    {        
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        List<Event> timeline = this.getTimeline();
        
        this.getRows().stream().forEach((row) -> {
            if (timeline.isEmpty())
            {
                timeline.add(new Event(row.getProcessName(), row.getArrivalTime(), row.getArrivalTime() + row.getBurstTime()));
            }
            else
            {
                Event event = timeline.get(timeline.size() - 1);
                timeline.add(new Event(row.getProcessName(), event.getFinishTime(), event.getFinishTime() + row.getBurstTime()));
            }
        });
        
        this.getRows().stream().map((row) -> {
            
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            return row;
        }).forEach((row) -> {
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        });
    }
}