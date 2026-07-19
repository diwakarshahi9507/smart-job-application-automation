package com.telusko.Smart_Job_Application_Automation.Service;

import com.telusko.Smart_Job_Application_Automation.Model.EmailTemplate;
import com.telusko.Smart_Job_Application_Automation.Model.Recruiter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    public List<Recruiter> readRecruiters(String filePath) throws Exception{

        List<Recruiter> recruiters = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            boolean header = true;
            for (Row row : sheet) {
                if (header) {
                    header = false;
                    continue;
                }
                if (row == null || row.getCell(0) == null) {
                    continue;
                }
                String firstName = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                String company = row.getCell(2).getStringCellValue();
                String position = row.getCell(3).getStringCellValue();
                String template = row.getCell(4).getStringCellValue();

                // Skip if the template is empty
                if (template.isEmpty()) {
                    System.out.println("Template missing at row: " + row.getRowNum());
                    continue;
                }
                try {
                    Recruiter recruiter = new Recruiter();

                    recruiter.setFirstName(firstName);
                    recruiter.setEmail(email);
                    recruiter.setCompany(company);
                    recruiter.setPosition(position);
                    recruiter.setTemplate(EmailTemplate.valueOf(template.toUpperCase()));
                    recruiters.add(recruiter);

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid Template at row " + (row.getRowNum() + 1) + " : " + template);
                }
            }
            workbook.close();
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return recruiters;
    }
}
