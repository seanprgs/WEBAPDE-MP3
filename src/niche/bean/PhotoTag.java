package niche.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tags")
public class PhotoTag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tagid;
	@Column(nullable=false)
	private String tag;
	
	public PhotoTag() {
		
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
}
