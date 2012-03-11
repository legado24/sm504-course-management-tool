package tr.edu.metu.ii.sm504.jsf.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;
import org.springframework.stereotype.Component;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.service.PermissionService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Configurable
@FacesConverter("tr.edu.metu.ii.sm504.jsf.converter.PermissionConverter")
public class PermissionConverter implements Converter{

    @Autowired
    private PermissionService permissionService;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return permissionService.findPermission(id);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Permission ? ((Permission) value).getId().toString() : "";
    }
}
