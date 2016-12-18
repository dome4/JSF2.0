package Beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/* Hier soll mit Annotationen die LoginBean unter dem Namen "loginBean" dem JSF-Framework bekannt gemacht werden
 *  und festgelegt werden, dass eine Instanz dieser Bean fuer eine ganze Sitzung besteht */

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Datenbank mit allen Usern
	private ArrayList<StudentBean> liste = new ArrayList<StudentBean>(Arrays.asList(new StudentBean("admin"),
			new StudentBean("Timm"), new StudentBean("Jonas"), new StudentBean("Kilian"), new StudentBean("Marco"),
			new StudentBean("Florian"), new StudentBean("Julian"), new StudentBean("Lucas"),
			new StudentBean("Alexander"), new StudentBean("Philipp"), new StudentBean("Christian"),
			new StudentBean("Rebecca"), new StudentBean("Carolin"), new StudentBean("Lukas"), new StudentBean("Samuel"),
			new StudentBean("Sascha"), new StudentBean("Waldemar"), new StudentBean("Roberto"),
			new StudentBean("Marius"), new StudentBean("Tobias"), new StudentBean("Christoph"),
			new StudentBean("Dominic")));

	// Login-Button
	@ManagedProperty(value = "#{UIComponent}")
	private UIComponent button;

	// diese StudentBean entspricht immer dem akutellen User
	@ManagedProperty(value = "#{studentBean}")
	private StudentBean studentBean;

	// Beispiel ueberlegen, was man mit diesem Event machen koennte
	public void changedUser(ValueChangeEvent event) {
		System.out.println("Der Username hat sich in der Eingabe von " + event.getOldValue() + " zu "
				+ event.getNewValue() + " geändert.");

	}

	/**
	 * Methode prüft, ob der Nutzer in der Datenbank gelistet ist
	 * 
	 * @return index-page
	 */
	public String login() {
		String user = "", pw = "";

		// suche in der Liste der gespeicherten Nutzer
		for (StudentBean student : liste) {
			if (studentBean.getUsername().equals(student.getUsername())) {
				user = student.getUsername();
				pw = student.getPassword();
				break;
			}
		}

		if (studentBean.getPassword().equals(pw) && !user.isEmpty()) {
			studentBean.setAngemeldet(true);
			return "index";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(button.getClientId(), new FacesMessage("Username-Passwort-Kombination falsch"));

			return null;
		}
	}

	/**
	 * Methode registriert einen neuen User
	 * 
	 * @return index-page
	 */
	public String register() {

		// wenn Student noch nicht gelistet wurde
		if (!this.findUser()) {
			liste.add(studentBean);

			this.login();

			return "index";
		} else {
			// username existiert bereits
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(button.getClientId(), new FacesMessage("Username existiert bereits"));
			return null;
		}
	}

	/**
	 * Methode schaut in der Datenbank, ob es den aktuellen Usernamen bereits
	 * gibt
	 * 
	 * @return true or false
	 */
	public boolean findUser() {
		for (StudentBean student : liste) {
			if (this.studentBean.getUsername().equalsIgnoreCase(student.getUsername())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode loggt den aktuellen User aus
	 * 
	 * @return index.xhtml
	 */
	public String logout() {
		this.studentBean = new StudentBean();
		return "index";
	}

	/**
	 * Die Methode gibt einen zufälligen Nutzer zurück, um auf der Profil-Seite
	 * zufällige Usernamen anzuzeigen
	 * 
	 * @return username
	 */
	public String randomUser() {
		int index = (int) (Math.random() * liste.size());
		StudentBean user = liste.get(index);

		return user.getUsername();
	}

	/**
	 * Methode dient zum Wechseln des Users
	 * 
	 * @return login-page
	 */
	public String changeUser() {

		// Benutzer muss zuerst ausgeloggt werden, sonst wird keine neue
		// StudentBean-Instanz erzeugt
		this.logout();

		return "login";
	}

	// Getter- und Setter-Methoden
	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	public ArrayList<StudentBean> getListe() {
		return this.liste;
	}

	public void setListe(ArrayList<StudentBean> liste) {
		this.liste = liste;
	}

	public UIComponent getButton() {
		return this.button;
	}

	public void setButton(UIComponent button) {
		this.button = button;
	}
}
