package tr.edu.metu.ii.sm504.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 12.03.2012
 * Time: 05:28
 * To change this template use File | Settings | File Templates.
 */
public final class ApplicationUtil {

    public static final String EUROPE_ISTANBUL = "Europe/Istanbul";

    public static void handleExceptionForUI(Throwable t){
        handleExceptionForUI(t, null);
    }

    public static void handleExceptionForUI(String message){
        handleExceptionForUI(null, message);
    }
    
    public static void handleExceptionForUI(Throwable t, String message) {
        String msjDetail = "";
        if (t != null) {
            t.printStackTrace();
            msjDetail = t.getMessage();
        }
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, msjDetail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static final Date getCurrentDate(){
        DateTime dt = new DateTime();
        DateTime dtLocal = dt.withZone(DateTimeZone.forID(EUROPE_ISTANBUL));
        return dtLocal.toDate();
    }
}
