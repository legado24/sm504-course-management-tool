package tr.edu.metu.ii.sm504.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.application.RooJsfApplicationBean;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

//@RooJsfApplicationBean
@ManagedBean
@RequestScoped
@Configurable
public class ApplicationBean {

    private MenuModel menuModel;

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public String getAppName() {
        return "Sm504";
    }

    public String getColumnName(String column) {
        if (column == null || column.length() == 0) {
            return column;
        }
        final Pattern p = Pattern.compile("[A-Z][^A-Z]*");
        final Matcher m = p.matcher(Character.toUpperCase(column.charAt(0)) + column.substring(1));
        final StringBuilder builder = new StringBuilder();
        while (m.find()) {
            builder.append(m.group()).append(" ");
        }
        return builder.toString().trim();
    }

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        menuModel = new DefaultMenuModel();
        Submenu submenu;
        MenuItem item;

        submenu = new Submenu();
        submenu.setId("mainMenu");
        submenu.setLabel("Control Menu");

        item = new MenuItem();
        item.setId("homeMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_home}", String.class));
        item.setUrl("main-flow");
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setId("roleListMenuItem");
        item.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.label_menu_roleList}", String.class));
        item.setUrl("roleList-flow");
        item.setIcon("ui-icon ui-icon-document");
        item.setAjax(false);
        item.setAsync(false);
        item.setUpdate(":dataForm:data");
        submenu.getChildren().add(item);

        menuModel.addSubmenu(submenu);
    }
}
