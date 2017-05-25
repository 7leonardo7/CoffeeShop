package com.esipovich.coffeeshop.util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.esipovich.coffeeshop.util.validator.QuantityValidator")
public class QuantityValidator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        Double valueFromComponent = Double.parseDouble(o.toString());
        if(valueFromComponent < 100){
            throw new ValidatorException(new FacesMessage("Value must be more than 100!"));
        }
    }
}
