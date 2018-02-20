package allegroAPP.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Repo {

	private String updated_at;

	private String name;

	private String pushed_at;

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public Date getPushedAtAsDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(getPushed_at());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public Date getUpdatedAtAsDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(getUpdated_at());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	

	@Override
	public String toString() {
		return "Repo [updated_at=" + updated_at + ", name=" + name + ", pushed_at=" + pushed_at + "]";
	}

}
