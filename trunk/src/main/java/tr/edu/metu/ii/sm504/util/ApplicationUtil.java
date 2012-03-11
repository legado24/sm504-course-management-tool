package tr.edu.metu.ii.sm504.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 12.03.2012
 * Time: 05:28
 * To change this template use File | Settings | File Templates.
 */
public final class ApplicationUtil {
    public static void handleExceptionForUI(Throwable t){
        handleExceptionForUI(t, null);
    }
    public static void handleExceptionForUI(Throwable t, String message){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
