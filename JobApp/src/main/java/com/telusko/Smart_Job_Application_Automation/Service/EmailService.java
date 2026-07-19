package com.telusko.Smart_Job_Application_Automation.Service;

import com.telusko.Smart_Job_Application_Automation.Model.Recruiter;
import com.telusko.Smart_Job_Application_Automation.Util.EmailValidator;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmailService {

        @Autowired
        private ReportService reportService;

        @Autowired
        private JavaMailSender mailSender;

        @Autowired
        private TemplateService templateService;

    private String getFirstName(String email) {

        String username = email.substring(0, email.indexOf('@'));
        String[] parts = username.split("[._\\-0-9]+");
        String firstName = parts[0];
        return firstName.substring(0,1).toUpperCase()
                + firstName.substring(1).toLowerCase();
    }

        public void sendEmail(List<Recruiter> recruiters) throws Exception{

            Set<String> uniqueEmails = new HashSet<>();

        for(Recruiter recruiter : recruiters){

                if (!uniqueEmails.add(recruiter.getEmail())) {
                    System.out.println(STR."Duplicate email skipped : \{recruiter.getEmail()}");
                    continue;
                }
                if (!EmailValidator.isValid(recruiter.getEmail())) {
                    System.out.println(STR."Invalid Email : \{recruiter.getEmail()}");
                    continue;
                }
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,true);
                helper.setTo(recruiter.getEmail());
                helper.setSubject(STR."Application for \{recruiter.getPosition()} at \{recruiter.getCompany()}");

                String body = templateService.prepareTemplate(recruiter.getTemplate(),recruiter);
                helper.setText(body,true);
                helper.addAttachment("Diwakar Kumar_JAVA Developer.docx", new File("D:\\Diwakar Kumar_JAVA Developer.docx"));

            try {
                mailSender.send(message);
                recruiter.setStatus("SUCCESS");
                recruiter.setSentTime(LocalDateTime.now());
                System.out.println("Mail sent to : " + recruiter.getEmail());
            } catch (Exception e) {
                recruiter.setStatus("FAILED");
                recruiter.setSentTime(LocalDateTime.now());
                System.out.println("Mail failed : " + recruiter.getEmail());
                e.printStackTrace();
            }

        }
        reportService.generateReport(recruiters);

    }
}
