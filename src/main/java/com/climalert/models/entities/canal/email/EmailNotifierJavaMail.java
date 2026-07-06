package com.climalert.models.entities.canal.email;

import com.climalert.models.entities.canal.CanalNotificacion;
import com.climalert.models.entities.notificacion.Notificacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class EmailNotifierJavaMail implements CanalNotificacion {

  private final JavaMailSender mailSender;
  private final String remitente;

  public EmailNotifierJavaMail(JavaMailSender mailSender, String remitente) {
    this.mailSender = mailSender;
    this.remitente = remitente;
  }

  @Override
  public void enviar(Notificacion notificacion) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(remitente);
    message.setTo(notificacion.getDestinatario());
    message.setSubject(notificacion.getAsunto());
    message.setText(notificacion.getCuerpo());
    mailSender.send(message);
    log.info("[EMAIL] Enviado a {}", notificacion.getDestinatario());
  }
}
