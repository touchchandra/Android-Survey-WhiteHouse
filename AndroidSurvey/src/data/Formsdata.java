package data;

public class Formsdata {
	int id;
	String name;
	String description;
	String form_start_date;
	String form_endate;
	String author;
	int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getForm_start_date() {
		return form_start_date;
	}

	public void setForm_start_date(String form_start_date) {
		this.form_start_date = form_start_date;
	}

	public String getForm_endate() {
		return form_endate;
	}

	public void setForm_endate(String form_endate) {
		this.form_endate = form_endate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
