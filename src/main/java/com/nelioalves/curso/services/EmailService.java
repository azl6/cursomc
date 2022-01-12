package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.Cliente;
import com.nelioalves.curso.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Pedido obj);
    public void sendEmail(SimpleMailMessage msg);
    void sendOrderConfirmationHtmlEmail(Pedido obj);
    void sendHtmlEmail(MimeMessage msg);
    void sendNewPasswordEmail(Cliente cliente, String newPass);

}
