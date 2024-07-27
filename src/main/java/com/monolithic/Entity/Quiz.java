package com.monolithic.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String category;
	@Transient
	//@Access(AccessType.FIELD)
	private Integer noOfQuestions;
	private String title;
	@ManyToMany
	private List<Question> question;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Quiz(Integer id, String category, Integer noOfQuestions, String title, List<Question> question) {
		super();
		this.id = id;
		this.category = category;
		this.noOfQuestions = noOfQuestions;
		this.title = title;
		this.question = question;
	}
	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	public String getTitle() {
		return title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", category=" + category + ", noOfQuestions=" + noOfQuestions + ", title=" + title
				+ ", question=" + question + "]";
	}
	public Quiz(Integer id,String category, Integer noOfQuestions, String title) {
		super();
		this.id=id;
		this.category = category;
		this.noOfQuestions = noOfQuestions;
		this.title = title;
		
	}
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
}
