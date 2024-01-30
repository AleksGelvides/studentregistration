package com.example.studentregistration.init;

import com.example.studentregistration.entity.Student;
import com.example.studentregistration.entity.StudentRepo;
import com.example.studentregistration.event.EventHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.example.studentregistration.enums.EventAction.INIT_ERROR;
import static com.example.studentregistration.enums.EventAction.INIT_SUCCESS;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty("app.studentsList.init")
public class StudentInitializer {

    @Value("${app.studentsList.filepath}")
    private String studentFile;

    private final ResourceLoader resourceLoader;
    private final ApplicationEventPublisher publisher;
    private final StudentRepo repo;

    @EventListener(value = ContextRefreshedEvent.class,
            condition = "@environment.getProperty('app.studentsList.init')")
    public void init() {
        Gson gson = new Gson();
        try {
            Resource resource = resourceLoader.getResource(studentFile);
            InputStream inputStream = resource.getInputStream();
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            List<Student> studentList = gson.fromJson(json, new TypeToken<ArrayList<Student>>(){}.getType());
            studentList.forEach(student -> repo.addNewOnInit(student.getId(), student));
            publisher.publishEvent(new EventHolder(this, INIT_SUCCESS, studentList.size()));
        } catch (Exception e) {
            publisher.publishEvent(new EventHolder(this, INIT_ERROR, e.getMessage()));
        }
    }
}
