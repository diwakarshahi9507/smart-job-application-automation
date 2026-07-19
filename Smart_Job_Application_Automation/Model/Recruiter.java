package com.telusko.Smart_Job_Application_Automation.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Recruiter {

        private String firstName;
        private String email;
        private String company;
        private String position;
        private EmailTemplate template;
        private String status;
        private LocalDateTime sentTime;

        public Recruiter() {
        }
}
