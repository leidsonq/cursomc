package o.q.leidson.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import o.q.leidson.cursomc.domain.Cliente;
import o.q.leidson.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail (Pedido obj);
	
	void sendEmail (SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
