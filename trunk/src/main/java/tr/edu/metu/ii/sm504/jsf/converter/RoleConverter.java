package tr.edu.metu.ii.sm504.jsf.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.service.RoleService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Configurable
@FacesConverter("tr.edu.metu.ii.sm504.jsf.converter.RoleConverter")
public class RoleConverter implements Converter{

    @Autowired
    private RoleService roleService;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return roleService.findRole(id);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Role ? ((Role) value).getId().toString() : "";
    }
}
