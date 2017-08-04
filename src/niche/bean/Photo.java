package niche.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="photos")
public class Photo 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int photoid;
	
	@Column(nullable=false)
	private boolean visible;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String path;
    
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER )
	@JoinTable(name = "photo_tags", joinColumns = { @JoinColumn(name = "photoid") }, inverseJoinColumns = { @JoinColumn(name = "tagid") })
	private Set <PhotoTag> tags;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER )
	@JoinTable(name = "photo_access", joinColumns = { @JoinColumn(name = "photoid") }, inverseJoinColumns = { @JoinColumn(name = "userid") })
	private Set <User> hasAccess;
	
	public final static String FILE_PATH = "path/to/photos/";
	
	public Photo()
	{
		
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Set <PhotoTag> getTags() {
		return tags;
	}

	public void setTags(Set <PhotoTag> tags) {
		this.tags = tags;
	}

	public Set <User> getHasAccess() {
		return hasAccess;
	}

	public void setHasAccess(Set <User> hasAccess) {
		this.hasAccess = hasAccess;
	}

	@Override
	public String toString() {
		return "Photo [photoid=" + photoid + ", visible=" + visible + ", description=" + description + ", title="
				+ title + ", path=" + path + ", user=" + user + ", tags=" + tags + ", hasAccess=" + hasAccess + "]";
	}
}
