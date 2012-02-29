// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.jsf.converter.CourseConverter;

privileged aspect CourseConverter_Roo_Converter {
    
    declare parents: CourseConverter implements Converter;
    
    declare @type: CourseConverter: @FacesConverter("tr.edu.metu.ii.sm504.jsf.converter.CourseConverter");
    
    public Object CourseConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Course.findCourse(id);
    }
    
    public String CourseConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Course ? ((Course) value).getId().toString() : "";
    }
    
}
