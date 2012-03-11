package tr.edu.metu.ii.sm504.jsf.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.service.UserService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Configurable
@FacesConverter("tr.edu.metu.ii.sm504.jsf.converter.UserConverter")
public class UserConverter implements Converter{

    @Autowired
    private UserService userService;
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return userService.findUser(id);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof User ? ((User) value).getId().toString() : "";
    }
}
