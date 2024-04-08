package dev.io.springbootemailintegration.service;

import dev.io.springbootemailintegration.model.MailStructure;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(String mail, MailStructure mailStructure, MultipartFile attachment) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromMail);
            helper.setTo(mail);
            helper.setSubject(mailStructure.getSubject());
            helper.setText(mailStructure.getMessage());

            if (attachment != null && !attachment.isEmpty()) {
                helper.addAttachment(attachment.getOriginalFilename(), attachment);
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }
}
