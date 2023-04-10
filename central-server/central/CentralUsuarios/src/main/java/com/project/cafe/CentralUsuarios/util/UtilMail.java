package com.project.cafe.CentralUsuarios.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.MailDTO;

@Component
public class UtilMail {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendMail(MailDTO mail, String urlMail) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(mail.getModel());
			String html = templateEngine.process(urlMail, context);
			String htmlReemplazado = new String(mail.getHtmlReemplazado(html));

			helper.setTo(mail.getTo());
			helper.setText(htmlReemplazado, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendMailActa(MailDTO mail,String[] cc) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(mail.getModel());
			String htmlReemplazado="Buen Día,<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "Mediante la presente se notifica a las partes interesadas que el acta fue aprobada.<br><br>Cordialmente ";
			helper.setTo(mail.getTo());
			if(cc!=null) {
				helper.setCc(cc);
			}
			helper.setText(htmlReemplazado, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendMailPdf(MailDTO mail,String[] cc,ArchivoDTO archivo) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(mail.getModel());
			String htmlReemplazado="Buen Día,<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "Mediante la presente se notifica a las partes interesadas que el Documento de transferencia fue generado.<br><br>Cordialmente ";
			helper.setTo(mail.getTo());
			if(cc!=null) {
				helper.setCc(cc);
			}
			helper.setText(htmlReemplazado, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			InputStreamSource targetStream = new ByteArrayResource(archivo.getArchivo());
			helper.addAttachment(archivo.getNombreArchivo(), targetStream);

			emailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}