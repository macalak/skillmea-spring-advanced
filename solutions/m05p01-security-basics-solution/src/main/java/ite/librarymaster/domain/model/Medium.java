package ite.librarymaster.domain.model;

import jakarta.persistence.*;

/**
 * 
 * This class models general Medium, which can be stored in Library.
 * 
 * @author macalak@itexperts.sk
 *
 */
@MappedSuperclass
public abstract class Medium {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String catId;
	private String title;
	@Enumerated(EnumType.STRING)
	private MediumAvailability availability;
	
	public Medium() {
		super();
	}
	
	public Medium(Long id, String catId, String title, MediumAvailability availability) {
		super();
		this.id = id;
		this.catId = catId;
		this.title = title;
		this.availability = availability;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public MediumAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(MediumAvailability availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Medium{");
		sb.append("id=").append(id);
		sb.append(", cid='").append(catId).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", availability=").append(availability);
		sb.append('}');
		return sb.toString();
	}
}
