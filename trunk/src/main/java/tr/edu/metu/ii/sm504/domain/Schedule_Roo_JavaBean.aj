// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import tr.edu.metu.ii.sm504.domain.Schedule;
import tr.edu.metu.ii.sm504.domain.ScheduleTemplate;
import tr.edu.metu.ii.sm504.domain.Semester;

privileged aspect Schedule_Roo_JavaBean {
    
    public ScheduleTemplate Schedule.getScheduleTemplate() {
        return this.scheduleTemplate;
    }
    
    public void Schedule.setScheduleTemplate(ScheduleTemplate scheduleTemplate) {
        this.scheduleTemplate = scheduleTemplate;
    }
    
    public Semester Schedule.getSemester() {
        return this.semester;
    }
    
    public void Schedule.setSemester(Semester semester) {
        this.semester = semester;
    }
    
}