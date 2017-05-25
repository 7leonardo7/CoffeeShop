package com.esipovich.coffeeshop.util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

//реализовать сравнение дат
@FacesValidator("dateTimeValidator")
public class DateTimeValidator implements Validator {
    //private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        UIInput dateFromUI = (UIInput)uiComponent.getAttributes().get("startDate");
        Date startDate = (Date)dateFromUI.getValue();
        Date endDate = (Date)o;
        if(!startDate.before(endDate)){
            FacesMessage msg = new FacesMessage("Entered dates are invalid: first date must be before second date");
            throw new ValidatorException(msg);
        }
    }
}
