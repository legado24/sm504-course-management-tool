package tr.edu.metu.ii.sm504.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
    public static final String GROWL_WITH_DETAILS = "growlForm:details";
    public static final String GROWL_WITHOUT_DETAILS = "growlForm:info";

    public static void raiseExceptionToUI(Throwable t){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal Exception Occured!", ExceptionUtils.getStackTrace(t));
        FacesContext.getCurrentInstance().addMessage(GROWL_WITH_DETAILS, facesMessage);
    }
    public static void raiseMessageToUI(String messageHeader) {
        raiseMessageToUI(messageHeader, "");
    }
    public static void raiseMessageToUI(String messageHeader, String messageDetail) {
        raiseMessageToUI(messageHeader, messageDetail, null);
    }
    public static void raiseMessageToUI(String messageHeader, FacesMessage.Severity severity) {
        raiseMessageToUI(messageHeader, "", severity);
    }
    public static void raiseMessageToUI(String messageHeader, String messageDetail, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(messageHeader, messageDetail);
        if (severity != null) {
            facesMessage.setSeverity(severity);
        }
        if (StringUtils.isEmpty(messageDetail)) {
            FacesContext.getCurrentInstance().addMessage(GROWL_WITHOUT_DETAILS, facesMessage);
        } else {
            FacesContext.getCurrentInstance().addMessage(GROWL_WITH_DETAILS, facesMessage);
        }
    }

    public static final Date getCurrentDate(){
        DateTime dt = new DateTime();
        DateTime dtLocal = dt.withZone(DateTimeZone.forID(EUROPE_ISTANBUL));
        return dtLocal.toDate();
    }
}
