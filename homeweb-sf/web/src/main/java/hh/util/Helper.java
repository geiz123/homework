package hh.util;

import javax.faces.context.FacesContext;

import org.springframework.web.jsf.FacesContextUtils;

public class Helper {
    public static <T> T findBean(Class<T> type) {
        return (T) FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()).getBean(type);
    }
}
