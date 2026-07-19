package com.telusko.Smart_Job_Application_Automation.Service;
import com.telusko.Smart_Job_Application_Automation.Model.EmailTemplate;
import com.telusko.Smart_Job_Application_Automation.Model.Recruiter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class TemplateService {
    public String getMessage(EmailTemplate template) throws IOException {

        String fileName = switch (template) {

            case JAVA -> "java.html";

            case BACKEND -> "backend.html";

            case INTERNSHIP -> "internship.html";

        };

        ClassPathResource resource =
                new ClassPathResource(STR."templates/\{fileName}");

        return new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

    }
    public String prepareTemplate(EmailTemplate template, Recruiter recruiter) throws IOException {

        String body = getMessage(template);

        body = body.replace("{{firstName}}", recruiter.getFirstName())
                .replace("{{company}}", recruiter.getCompany())
                .replace("{{position}}", recruiter.getPosition());

        return body;
    }
}
