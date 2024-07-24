package com;

import com.entity.StaffEntity;
import com.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

@EnableSwagger2
@SpringBootApplication
public class PDFExportApplication implements CommandLineRunner {

    @Autowired
    StaffRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(PDFExportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (employeeRepository.count() == 0) {

            AtomicInteger seq = new AtomicInteger();

            //save list of employees
            employeeRepository.saveAll(Arrays.asList(
                    new StaffEntity(nextVal(seq), "Emily Davis", "Sr. Manger", "IT"),
                    new StaffEntity(nextVal(seq), "Theodore Dinh", "Technical Architect", "IT"),
                    new StaffEntity(nextVal(seq), "Luna Sanders", "Director", "Finance"),
                    new StaffEntity(nextVal(seq), "Penelope Jordan", "Computer Systems Manager", "Accounting"),
                    new StaffEntity(nextVal(seq), "Eli Jones", "Manager", "Human Resources"),
                    new StaffEntity(nextVal(seq), "Leonardo Dixon", "Analyst", "Sales")
            ));
        }
    }

    private int nextVal(AtomicInteger seq) {
        return seq.incrementAndGet();
    }

    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {

        return new ApiInfo(
                "Generate PDF Api",
                "Application in JAVA",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Kanza Jamal", "https://github.com/skanzajamal", "k122060@nu.ed.pk"),
                "API License",
                "https://github.com/skanzajamal",
                Collections.emptyList());
    }

}// ENDCLASS
