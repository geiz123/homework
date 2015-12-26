package hh.view;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class FormTestView implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log4jLogger = Logger.getLogger(FormTestView.class);

    private String firstName;
    private String lastName;
    private String yap;

    @PostConstruct
    public void init() {

    }

    /**
     * Called by Facelet for validation
     * 
     * @param context
     * @param component
     * @param value
     *            - the HTML input object containing stuff like the text type by user
     */
    public void chkYap(FacesContext context, UIComponent component, Object value) {
        log4jLogger.debug("In chkYap...");

        Random ran = new Random();

        if (ran.nextInt(10) % 2 == 0) {

            log4jLogger.debug("In chkYap...Even and throw error.");
            UIInput yap_ = (UIInput) component;

            yap_.setValid(false);

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setDetail("You yap too much!");
            msg.setSummary("Summary");

            throw new ValidatorException(msg);

        }
    }

    public String doSubmit() {
        log4jLogger.debug("In doSubmit...Going to do some work");

        /*
         * Avoid re-execution of form view refresh
         */
        // http://stackoverflow.com/questions/7850831/how-to-avoid-re-execution-of-last-form-submit-action-when-the-page-is-refreshed
        String viewId = "/index.xhtml";//FacesContext.getCurrentInstance().getViewRoot().getViewId();
        viewId += "?x=1";
        log4jLogger.debug("In doSubmit..." + viewId);
        
        return viewId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYap() {
        return yap;
    }

    public void setYap(String yap) {
        this.yap = yap;
    }

}
