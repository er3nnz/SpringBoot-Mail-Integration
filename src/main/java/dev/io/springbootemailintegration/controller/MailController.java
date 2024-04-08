package dev.io.springbootemailintegration.controller;

import dev.io.springbootemailintegration.model.MailStructure;
import dev.io.springbootemailintegration.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestParam("to") String mail,
                           @RequestParam("subject") String subject,
                           @RequestParam("message") String message,
                           @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject(subject);
        mailStructure.setMessage(message);
        mailService.sendMail(mail, mailStructure, attachment);
        return "Mail sent successfully!";
    }
}
