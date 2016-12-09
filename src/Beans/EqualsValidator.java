package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "equalsValidator")
public class EqualsValidator implements Validator {

	/** 
	 * Methode checkt, ob die beiden Passwoerter gleich sind
	 * 
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Object value2 = component.getAttributes().get("pw2");

		// Testen, ob die Werte gleich sind
		// NullPointerException verhindern
		if (value != null && value2 != null && !value.equals(value2)) {
			throw new ValidatorException(new FacesMessage("Values are not equal."));
		}
	}
}