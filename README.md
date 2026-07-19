# Smart Job Application Automation System

A Spring Boot-based application that automates sending personalized job application emails to multiple recruiters using Excel-based contact management. Built to reduce manual effort in job outreach by combining dynamic email templates, resume attachments, and validation checks into a single automated workflow.

## Features

- **Excel-based contact management** — read recruiter details (name, email, company, role) directly from an Excel sheet using Apache POI
- **Dynamic HTML email templates** with placeholder-based personalization (e.g., recruiter name, company name, role)
- **Automated resume attachment** with every email
- **Email validation** to prevent sending to invalid addresses
- **Duplicate email detection** to avoid sending multiple emails to the same recruiter
- **Template selection** for different job roles
- Modular architecture built on **SOLID principles** for easy extension

## Tech Stack

- **Backend:** Java, Spring Boot
- **Email:** JavaMail API
- **Excel Handling:** Apache POI
- **Build Tool:** Maven

## How It Works

1. Recruiter contact details are maintained in an Excel file (name, email, company, role).
2. The application reads this data and validates each email address.
3. Duplicate entries are filtered out automatically.
4. Based on the job role, an appropriate HTML email template is selected.
5. Placeholders in the template are replaced with recruiter-specific details.
6. The resume is attached, and the personalized email is sent via JavaMail API.

## Getting Started

### Prerequisites
- Java 17+
- Maven
- An email account with SMTP access (e.g., Gmail with an app password)

### Setup
```bash
git clone https://github.com/diwakarshahi9507/smart-job-application-automation.git
cd smart-job-application-automation
```

Update the following in `application.properties`:
```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

Place your recruiter contact list in the `resources` folder as an Excel file, and update the file path in the configuration.

### Run
```bash
mvn spring-boot:run
```

## Future Enhancements

- Recruiter response tracking
- Email scheduling
- Database integration for storing application history
- REST APIs for external integration
- Email campaign dashboard for analytics

## Author

**Diwakar Kumar**
[LinkedIn](https://www.linkedin.com/in/diwakar-k-077194204) | [GitHub](https://github.com/diwakarshahi9507)
