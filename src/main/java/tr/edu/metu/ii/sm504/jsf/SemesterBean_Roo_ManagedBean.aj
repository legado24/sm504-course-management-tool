// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.jsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LongRangeValidator;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import tr.edu.metu.ii.sm504.domain.Semester;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.SemesterBean;
import tr.edu.metu.ii.sm504.jsf.converter.UserConverter;

privileged aspect SemesterBean_Roo_ManagedBean {
    
    declare @type: SemesterBean: @ManagedBean(name = "semesterBean");
    
    declare @type: SemesterBean: @SessionScoped;
    
    private String SemesterBean.name = "Semesters";
    
    private Semester SemesterBean.semester;
    
    private List<Semester> SemesterBean.allSemesters;
    
    private boolean SemesterBean.dataVisible = false;
    
    private List<String> SemesterBean.columns;
    
    private HtmlPanelGrid SemesterBean.createPanelGrid;
    
    private HtmlPanelGrid SemesterBean.editPanelGrid;
    
    private HtmlPanelGrid SemesterBean.viewPanelGrid;
    
    private boolean SemesterBean.createDialogVisible = false;
    
    @PostConstruct
    public void SemesterBean.init() {
        columns = new ArrayList<String>();
        columns.add("updateTime");
        columns.add("creationTime");
        columns.add("year");
        columns.add("name");
    }
    
    public String SemesterBean.getName() {
        return name;
    }
    
    public List<String> SemesterBean.getColumns() {
        return columns;
    }
    
    public List<Semester> SemesterBean.getAllSemesters() {
        return allSemesters;
    }
    
    public void SemesterBean.setAllSemesters(List<Semester> allSemesters) {
        this.allSemesters = allSemesters;
    }
    
    public String SemesterBean.findAllSemesters() {
        allSemesters = Semester.findAllSemesters();
        dataVisible = !allSemesters.isEmpty();
        return null;
    }
    
    public boolean SemesterBean.isDataVisible() {
        return dataVisible;
    }
    
    public void SemesterBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid SemesterBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void SemesterBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid SemesterBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void SemesterBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid SemesterBean.getViewPanelGrid() {
        if (viewPanelGrid == null) {
            viewPanelGrid = populateViewPanel();
        }
        return viewPanelGrid;
    }
    
    public void SemesterBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid SemesterBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText updateTimeCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateTimeCreateOutput.setId("updateTimeCreateOutput");
        updateTimeCreateOutput.setValue("Update Time:   ");
        htmlPanelGrid.getChildren().add(updateTimeCreateOutput);
        
        Calendar updateTimeCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updateTimeCreateInput.setId("updateTimeCreateInput");
        updateTimeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updateTime}", Date.class));
        updateTimeCreateInput.setNavigator(true);
        updateTimeCreateInput.setEffect("slideDown");
        updateTimeCreateInput.setPattern("dd/MM/yyyy");
        updateTimeCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updateTimeCreateInput);
        
        Message updateTimeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updateTimeCreateInputMessage.setId("updateTimeCreateInputMessage");
        updateTimeCreateInputMessage.setFor("updateTimeCreateInput");
        updateTimeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updateTimeCreateInputMessage);
        
        HtmlOutputText updatedByCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedByCreateOutput.setId("updatedByCreateOutput");
        updatedByCreateOutput.setValue("Updated By:   ");
        htmlPanelGrid.getChildren().add(updatedByCreateOutput);
        
        AutoComplete updatedByCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        updatedByCreateInput.setId("updatedByCreateInput");
        updatedByCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updatedBy}", User.class));
        updatedByCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{semesterBean.completeUpdatedBy}", List.class, new Class[] { String.class }));
        updatedByCreateInput.setDropdown(true);
        updatedByCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "updatedBy", String.class));
        updatedByCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{updatedBy.updateTime} #{updatedBy.creationTime} #{updatedBy.username} #{updatedBy.name}", String.class));
        updatedByCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{updatedBy}", User.class));
        updatedByCreateInput.setConverter(new UserConverter());
        updatedByCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updatedByCreateInput);
        
        Message updatedByCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updatedByCreateInputMessage.setId("updatedByCreateInputMessage");
        updatedByCreateInputMessage.setFor("updatedByCreateInput");
        updatedByCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updatedByCreateInputMessage);
        
        HtmlOutputText creationTimeCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationTimeCreateOutput.setId("creationTimeCreateOutput");
        creationTimeCreateOutput.setValue("Creation Time:   ");
        htmlPanelGrid.getChildren().add(creationTimeCreateOutput);
        
        Calendar creationTimeCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        creationTimeCreateInput.setId("creationTimeCreateInput");
        creationTimeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.creationTime}", Date.class));
        creationTimeCreateInput.setNavigator(true);
        creationTimeCreateInput.setEffect("slideDown");
        creationTimeCreateInput.setPattern("dd/MM/yyyy");
        creationTimeCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(creationTimeCreateInput);
        
        Message creationTimeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        creationTimeCreateInputMessage.setId("creationTimeCreateInputMessage");
        creationTimeCreateInputMessage.setFor("creationTimeCreateInput");
        creationTimeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(creationTimeCreateInputMessage);
        
        HtmlOutputText createdByCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdByCreateOutput.setId("createdByCreateOutput");
        createdByCreateOutput.setValue("Created By:   ");
        htmlPanelGrid.getChildren().add(createdByCreateOutput);
        
        AutoComplete createdByCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        createdByCreateInput.setId("createdByCreateInput");
        createdByCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.createdBy}", User.class));
        createdByCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{semesterBean.completeCreatedBy}", List.class, new Class[] { String.class }));
        createdByCreateInput.setDropdown(true);
        createdByCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "createdBy", String.class));
        createdByCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{createdBy.updateTime} #{createdBy.creationTime} #{createdBy.username} #{createdBy.name}", String.class));
        createdByCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{createdBy}", User.class));
        createdByCreateInput.setConverter(new UserConverter());
        createdByCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(createdByCreateInput);
        
        Message createdByCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        createdByCreateInputMessage.setId("createdByCreateInputMessage");
        createdByCreateInputMessage.setFor("createdByCreateInput");
        createdByCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(createdByCreateInputMessage);
        
        HtmlOutputText yearCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearCreateOutput.setId("yearCreateOutput");
        yearCreateOutput.setValue("Year: * ");
        htmlPanelGrid.getChildren().add(yearCreateOutput);
        
        Spinner yearCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        yearCreateInput.setId("yearCreateInput");
        yearCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.year}", Integer.class));
        yearCreateInput.setRequired(true);
        yearCreateInput.setMin(1000.0);
        yearCreateInput.setMax(9999.0);
        LongRangeValidator yearCreateInputValidator = new LongRangeValidator();
        yearCreateInputValidator.setMinimum(1000);
        yearCreateInputValidator.setMaximum(9999);
        yearCreateInput.addValidator(yearCreateInputValidator);
        
        htmlPanelGrid.getChildren().add(yearCreateInput);
        
        Message yearCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        yearCreateInputMessage.setId("yearCreateInputMessage");
        yearCreateInputMessage.setFor("yearCreateInput");
        yearCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(yearCreateInputMessage);
        
        HtmlOutputText nameCreateOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameCreateOutput.setId("nameCreateOutput");
        nameCreateOutput.setValue("Name:   ");
        htmlPanelGrid.getChildren().add(nameCreateOutput);
        
        InputText nameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameCreateInput.setId("nameCreateInput");
        nameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.name}", String.class));
        htmlPanelGrid.getChildren().add(nameCreateInput);
        
        Message nameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameCreateInputMessage.setId("nameCreateInputMessage");
        nameCreateInputMessage.setFor("nameCreateInput");
        nameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid SemesterBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText updateTimeEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateTimeEditOutput.setId("updateTimeEditOutput");
        updateTimeEditOutput.setValue("Update Time:   ");
        htmlPanelGrid.getChildren().add(updateTimeEditOutput);
        
        Calendar updateTimeEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        updateTimeEditInput.setId("updateTimeEditInput");
        updateTimeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updateTime}", Date.class));
        updateTimeEditInput.setNavigator(true);
        updateTimeEditInput.setEffect("slideDown");
        updateTimeEditInput.setPattern("dd/MM/yyyy");
        updateTimeEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updateTimeEditInput);
        
        Message updateTimeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updateTimeEditInputMessage.setId("updateTimeEditInputMessage");
        updateTimeEditInputMessage.setFor("updateTimeEditInput");
        updateTimeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updateTimeEditInputMessage);
        
        HtmlOutputText updatedByEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedByEditOutput.setId("updatedByEditOutput");
        updatedByEditOutput.setValue("Updated By:   ");
        htmlPanelGrid.getChildren().add(updatedByEditOutput);
        
        AutoComplete updatedByEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        updatedByEditInput.setId("updatedByEditInput");
        updatedByEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updatedBy}", User.class));
        updatedByEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{semesterBean.completeUpdatedBy}", List.class, new Class[] { String.class }));
        updatedByEditInput.setDropdown(true);
        updatedByEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "updatedBy", String.class));
        updatedByEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{updatedBy.updateTime} #{updatedBy.creationTime} #{updatedBy.username} #{updatedBy.name}", String.class));
        updatedByEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{updatedBy}", User.class));
        updatedByEditInput.setConverter(new UserConverter());
        updatedByEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(updatedByEditInput);
        
        Message updatedByEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        updatedByEditInputMessage.setId("updatedByEditInputMessage");
        updatedByEditInputMessage.setFor("updatedByEditInput");
        updatedByEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(updatedByEditInputMessage);
        
        HtmlOutputText creationTimeEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationTimeEditOutput.setId("creationTimeEditOutput");
        creationTimeEditOutput.setValue("Creation Time:   ");
        htmlPanelGrid.getChildren().add(creationTimeEditOutput);
        
        Calendar creationTimeEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        creationTimeEditInput.setId("creationTimeEditInput");
        creationTimeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.creationTime}", Date.class));
        creationTimeEditInput.setNavigator(true);
        creationTimeEditInput.setEffect("slideDown");
        creationTimeEditInput.setPattern("dd/MM/yyyy");
        creationTimeEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(creationTimeEditInput);
        
        Message creationTimeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        creationTimeEditInputMessage.setId("creationTimeEditInputMessage");
        creationTimeEditInputMessage.setFor("creationTimeEditInput");
        creationTimeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(creationTimeEditInputMessage);
        
        HtmlOutputText createdByEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdByEditOutput.setId("createdByEditOutput");
        createdByEditOutput.setValue("Created By:   ");
        htmlPanelGrid.getChildren().add(createdByEditOutput);
        
        AutoComplete createdByEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        createdByEditInput.setId("createdByEditInput");
        createdByEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.createdBy}", User.class));
        createdByEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{semesterBean.completeCreatedBy}", List.class, new Class[] { String.class }));
        createdByEditInput.setDropdown(true);
        createdByEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "createdBy", String.class));
        createdByEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{createdBy.updateTime} #{createdBy.creationTime} #{createdBy.username} #{createdBy.name}", String.class));
        createdByEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{createdBy}", User.class));
        createdByEditInput.setConverter(new UserConverter());
        createdByEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(createdByEditInput);
        
        Message createdByEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        createdByEditInputMessage.setId("createdByEditInputMessage");
        createdByEditInputMessage.setFor("createdByEditInput");
        createdByEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(createdByEditInputMessage);
        
        HtmlOutputText yearEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearEditOutput.setId("yearEditOutput");
        yearEditOutput.setValue("Year: * ");
        htmlPanelGrid.getChildren().add(yearEditOutput);
        
        Spinner yearEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        yearEditInput.setId("yearEditInput");
        yearEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.year}", Integer.class));
        yearEditInput.setRequired(true);
        yearEditInput.setMin(1000.0);
        yearEditInput.setMax(9999.0);
        LongRangeValidator yearEditInputValidator = new LongRangeValidator();
        yearEditInputValidator.setMinimum(1000);
        yearEditInputValidator.setMaximum(9999);
        yearEditInput.addValidator(yearEditInputValidator);
        
        htmlPanelGrid.getChildren().add(yearEditInput);
        
        Message yearEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        yearEditInputMessage.setId("yearEditInputMessage");
        yearEditInputMessage.setFor("yearEditInput");
        yearEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(yearEditInputMessage);
        
        HtmlOutputText nameEditOutput = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameEditOutput.setId("nameEditOutput");
        nameEditOutput.setValue("Name:   ");
        htmlPanelGrid.getChildren().add(nameEditOutput);
        
        InputText nameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameEditInput.setId("nameEditInput");
        nameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.name}", String.class));
        htmlPanelGrid.getChildren().add(nameEditInput);
        
        Message nameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameEditInputMessage.setId("nameEditInputMessage");
        nameEditInputMessage.setFor("nameEditInput");
        nameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid SemesterBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText updateTimeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateTimeLabel.setId("updateTimeLabel");
        updateTimeLabel.setValue("Update Time:   ");
        htmlPanelGrid.getChildren().add(updateTimeLabel);
        
        HtmlOutputText updateTimeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updateTimeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updateTime}", Date.class));
        DateTimeConverter updateTimeValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        updateTimeValueConverter.setPattern("dd/MM/yyyy");
        updateTimeValue.setConverter(updateTimeValueConverter);
        htmlPanelGrid.getChildren().add(updateTimeValue);
        
        HtmlOutputText updatedByLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedByLabel.setId("updatedByLabel");
        updatedByLabel.setValue("Updated By:   ");
        htmlPanelGrid.getChildren().add(updatedByLabel);
        
        HtmlOutputText updatedByValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        updatedByValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.updatedBy}", User.class));
        updatedByValue.setConverter(new UserConverter());
        htmlPanelGrid.getChildren().add(updatedByValue);
        
        HtmlOutputText creationTimeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationTimeLabel.setId("creationTimeLabel");
        creationTimeLabel.setValue("Creation Time:   ");
        htmlPanelGrid.getChildren().add(creationTimeLabel);
        
        HtmlOutputText creationTimeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        creationTimeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.creationTime}", Date.class));
        DateTimeConverter creationTimeValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        creationTimeValueConverter.setPattern("dd/MM/yyyy");
        creationTimeValue.setConverter(creationTimeValueConverter);
        htmlPanelGrid.getChildren().add(creationTimeValue);
        
        HtmlOutputText createdByLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdByLabel.setId("createdByLabel");
        createdByLabel.setValue("Created By:   ");
        htmlPanelGrid.getChildren().add(createdByLabel);
        
        HtmlOutputText createdByValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        createdByValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.createdBy}", User.class));
        createdByValue.setConverter(new UserConverter());
        htmlPanelGrid.getChildren().add(createdByValue);
        
        HtmlOutputText yearLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearLabel.setId("yearLabel");
        yearLabel.setValue("Year:   ");
        htmlPanelGrid.getChildren().add(yearLabel);
        
        HtmlOutputText yearValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.year}", String.class));
        htmlPanelGrid.getChildren().add(yearValue);
        
        HtmlOutputText nameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameLabel.setId("nameLabel");
        nameLabel.setValue("Name:   ");
        htmlPanelGrid.getChildren().add(nameLabel);
        
        HtmlOutputText nameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameValue.setId("nameValue");
        nameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{semesterBean.semester.name}", String.class));
        htmlPanelGrid.getChildren().add(nameValue);
        
        return htmlPanelGrid;
    }
    
    public Semester SemesterBean.getSemester() {
        if (semester == null) {
            semester = new Semester();
        }
        return semester;
    }
    
    public void SemesterBean.setSemester(Semester semester) {
        this.semester = semester;
    }
    
    public List<User> SemesterBean.completeUpdatedBy(String query) {
        List<User> suggestions = new ArrayList<User>();
        for (User user : User.findAllUsers()) {
            String userStr = String.valueOf(user.getUpdateTime() +  " "  + user.getCreationTime() +  " "  + user.getUsername() +  " "  + user.getName());
            if (userStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(user);
            }
        }
        return suggestions;
    }
    
    public List<User> SemesterBean.completeCreatedBy(String query) {
        List<User> suggestions = new ArrayList<User>();
        for (User user : User.findAllUsers()) {
            String userStr = String.valueOf(user.getUpdateTime() +  " "  + user.getCreationTime() +  " "  + user.getUsername() +  " "  + user.getName());
            if (userStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(user);
            }
        }
        return suggestions;
    }
    
    public String SemesterBean.onEdit() {
        return null;
    }
    
    public boolean SemesterBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void SemesterBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String SemesterBean.displayList() {
        createDialogVisible = false;
        findAllSemesters();
        return "semester";
    }
    
    public String SemesterBean.displayCreateDialog() {
        semester = new Semester();
        createDialogVisible = true;
        return "semester";
    }
    
    public String SemesterBean.persist() {
        String message = "";
        if (semester.getId() != null) {
            semester.merge();
            message = "Successfully updated";
        } else {
            semester.persist();
            message = "Successfully created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");
        
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllSemesters();
    }
    
    public String SemesterBean.delete() {
        semester.remove();
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllSemesters();
    }
    
    public void SemesterBean.reset() {
        semester = null;
        createDialogVisible = false;
    }
    
    public void SemesterBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}