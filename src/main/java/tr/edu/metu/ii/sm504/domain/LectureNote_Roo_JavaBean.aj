// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.LectureNote;

privileged aspect LectureNote_Roo_JavaBean {
    
    public String LectureNote.getName() {
        return this.name;
    }
    
    public void LectureNote.setName(String name) {
        this.name = name;
    }
    
    public Course LectureNote.getCourse() {
        return this.course;
    }
    
    public void LectureNote.setCourse(Course course) {
        this.course = course;
    }
    
    public byte[] LectureNote.getData() {
        return this.data;
    }
    
    public void LectureNote.setData(byte[] data) {
        this.data = data;
    }
    
}