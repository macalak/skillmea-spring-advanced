package ite.librarymaster.domain.model;

import jakarta.persistence.MappedSuperclass;

/**
 * This class models general textual media types.
 * 
 * @author macalak@itexperts.sk
 *
 */

@MappedSuperclass
public abstract class Text extends Medium {
	
	private String publisher;

	public Text() {
		super();
	}

	public Text(Long id, String catId, String title, String publisher, MediumAvailability availability) {
		super(id, catId, title, availability);
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Text{");
		sb.append("publisher='").append(publisher).append('\'');
		sb.append(super.toString());
		sb.append('}');
		return sb.toString();
	}
}
