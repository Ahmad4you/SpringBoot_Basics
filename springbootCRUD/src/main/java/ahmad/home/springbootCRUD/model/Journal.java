package ahmad.home.springbootCRUD.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Ahmad Alrefai
 */

@Entity
@Table(name = "journal")
public class Journal implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7682154192300901870L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private Date created;
    
    @Column(name = "summary")
    private String summary;
    
    @Transient
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    
    public Journal(String title, String summary, String date) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = format.parse(date);
    }
    
    public Journal() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created=" + format.format(created) +
                ", summary='" + summary + '\'' +
                '}';
    }
}
