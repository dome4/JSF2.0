package Beans;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.validation.constraints.*;
/* Hier soll mit Annotationen die StudentBean unter dem Namen "studentBean" dem JSF-Framework bekannt gemacht werden
 *  und festgelegt werden, dass eine Instanz dieser Bean fuer eine ganze Sitzung besteht */
@ManagedBean(name = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	/* alle noetigen Instanzvariablen deklarieren */
	
	
	//Validierung ist auch direkt in h:inputTetxt mit required="true" möglich
	@NotNull(message="darf nicht leer sein")
	private String username, password;
	@NotNull(message="Geschlecht muss angegeben werden")
	private String geschlecht;	
	@AssertTrue(message="AGB muessen akzeptiert werden")
	private boolean agb;
	private boolean angemeldet;
	@NotNull(message="darf nicht leer sein")
	@Min(18)
	@Max(100)
	private Integer age;
	
	public StudentBean(){}
	public StudentBean(String string){
		this.username = string;
		this.password = "pw:"+string;
	}
	
	/**
	 * Methode prüft, ob der aktuelle User der Admin ist
	 * 
	 * @return true or false
	 */
	public boolean isAdmin(){
		if(this.username.equalsIgnoreCase("admin")){
			return true;
		}
		return false;
	}
	
	/* alle noetigen Methoden einer JavaBean angeben */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAngemeldet() {
		return angemeldet;
	}

	public void setAngemeldet(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isAgb() {
		return agb;
	}

	public void setAgb(boolean agb) {
		this.agb = agb;
	}

}
