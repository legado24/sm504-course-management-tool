// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tr.edu.metu.ii.sm504.domain.SubmissionActivity;
import tr.edu.metu.ii.sm504.jsf.converter.SubmissionActivityConverter;

privileged aspect SubmissionActivityConverter_Roo_Converter {
    
    declare parents: SubmissionActivityConverter implements Converter;
    
    declare @type: SubmissionActivityConverter: @FacesConverter("tr.edu.metu.ii.sm504.jsf.converter.SubmissionActivityConverter");
    
    public Object SubmissionActivityConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return SubmissionActivity.findSubmissionActivity(id);
    }
    
    public String SubmissionActivityConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof SubmissionActivity ? ((SubmissionActivity) value).getId().toString() : "";
    }
    
}