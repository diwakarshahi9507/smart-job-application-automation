package com.telusko.Smart_Job_Application_Automation.Controller;

import com.telusko.Smart_Job_Application_Automation.Model.Recruiter;
import com.telusko.Smart_Job_Application_Automation.Service.EmailService;
import com.telusko.Smart_Job_Application_Automation.Service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    private final ExcelService excelService;

    public EmailController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/send")
    public String sendMail() throws Exception{
        List<Recruiter> recruiters = excelService.readRecruiters("E:\\Java\\Telusko Java\\JobApp\\Recruiters.xlsx");
        emailService.sendEmail(recruiters);
        return "Email sent sucessfully";
    }
}
//ujjwals346@gmail.com