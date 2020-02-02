package com.apolinario.elasticsearch;

import java.util.Date;

public class DocumentDTO {
	private String someName;
	private String fullText;
	private Date date;
	private double number;

	public void generateContent() {
		DocumentFactor documentFactor = new DocumentFactor();
		setSomeName(documentFactor.createFakeName());
		setFullText(documentFactor.createFakeText());
		setDate(documentFactor.createDate());
		setNumber(documentFactor.randonNumber());
	}

	public String getSomeName() {
		return someName;
	}

	public void setSomeName(String someName) {
		this.someName = someName;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}
}
