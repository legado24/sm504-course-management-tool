// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import tr.edu.metu.ii.sm504.domain.Course;

privileged aspect Course_Roo_Jpa_Entity {
    
    declare @type: Course: @Entity;
    
    declare @type: Course: @Table(name = "COURSE");
    
}
