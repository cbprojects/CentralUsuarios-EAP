package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = -7979403290212524827L;

	private String from;

	private String to;

	private String subject;

	private Map<String, Object> model;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getHtmlReemplazado(String html) {
		if (StringUtils.isNotBlank(html) && this.model != null && !this.model.isEmpty()) {
			for (String key : this.model.keySet()) {
				html = html.replace("@" + key, this.model.get(key).toString());
			}
		}

		return html;
	}

}
