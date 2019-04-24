package persist;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Answer
 *
 */

// entity where we save the values the candidate gives

@Entity
@Table(name = "VASTAUKSET")
public class Answer implements Serializable {
	
	@Id
	@Column(name = "EHDOKAS_ID")
	private int ehdokasid;
	
	@Column(name = "KYSYMYS_ID")
	private int kysymysid;
 
	private int vastaus;
 
	private String kommentti;

	
	private static final long serialVersionUID = 1L;

	public Answer() {
		super();
	}

	public int getEhdokasid() {
		return ehdokasid;
	}

	public void setEhdokasid(int ehdokasid) {
		this.ehdokasid = ehdokasid;
	}

	public int getKysymysid() {
		return kysymysid;
	}

	public void setKysymysid(int kysymysid) {
		this.kysymysid = kysymysid;
	}

	public int getVastaus() {
		return vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public String getKommentti() {
		return kommentti;
	}

	public void setKommentti(String kommentti) {
		this.kommentti = kommentti;
	}
   
}
