package com.faisal.reportgeneratorusingjasper;

import com.faisal.reportgeneratorusingjasper.initializedata.StudentDataInitializer;
import com.faisal.reportgeneratorusingjasper.util.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class ReportGeneratorUsingJasperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ReportGeneratorUsingJasperApplication.class, args);
        StudentDataInitializer studentDataInitializer = ctx.getBeanFactory().getBean(StudentDataInitializer.class);
        studentDataInitializer.run(Constant.SCHEMA_NAME_IIUC);
        studentDataInitializer.run(Constant.SCHEMA_NAME_CUET);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(
                new ApiInfoBuilder()
                    .title("Report Generator Application" + " API")
                    .description("Report Generator Application" + " REST API")
                    .version("1.0.0")
                    .license("©Faisal")
                    .licenseUrl("https://github.com/faisal1104/")
                    .contact(
                        new Contact(
                            "Faisal", "https://github.com/faisal1104/", "faisal1104a@gmail.com"))
                    .build())
            .directModelSubstitute(Void.class, void.class);
    }
}
