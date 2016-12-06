package Beans;

import java.io.Serializable;
import javax.faces.bean.*;

@ManagedBean
@RequestScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = -5459300954012448102L;

	// managed property liest den Parameter param von request aus
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	public String showPage() {
		if (pageId == null) {
			return "index";
		}
		if (pageId.equals("1")) {
			return "login";
		} else {
			return "home";
		}
	}
}
