package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Pedido obj);
    public void sendEmail(SimpleMailMessage msg);
}
