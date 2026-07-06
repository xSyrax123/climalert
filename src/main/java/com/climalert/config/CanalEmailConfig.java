package com.climalert.config;

import com.climalert.models.entities.canal.CanalNotificacion;
import com.climalert.models.entities.canal.email.EmailNotifierJavaMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class CanalEmailConfig {

  @Bean
  public CanalNotificacion canalEmail(
      JavaMailSender mailSender,
      @Value("${alertas.remitente}") String remitente
  ) {
    return new EmailNotifierJavaMail(mailSender, remitente);
  }
}
