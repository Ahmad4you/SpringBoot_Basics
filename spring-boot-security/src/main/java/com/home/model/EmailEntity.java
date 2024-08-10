package com.home.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */
@Entity
@Table(name = "emails")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "email_recipients", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "recipient")
    private List<String> to;

    @ElementCollection
    @CollectionTable(name = "email_cc", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "cc")
    private List<String> cc;

    private String subject;

    @Column(length = 10485760) // Set a length to accommodate large content
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    
}

