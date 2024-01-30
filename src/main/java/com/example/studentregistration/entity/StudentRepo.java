package com.example.studentregistration.entity;
import com.example.studentregistration.event.EventHolder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.studentregistration.enums.EventAction.*;

@Service
public class StudentRepo {
    private final Map<UUID, Student> students;
    private final ApplicationEventPublisher publisher;

    public StudentRepo(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.students = new HashMap<>();
    }

    public Collection<Student> getAll() {
        return this.students.values();
    }

    public void addNewOnInit(UUID uuid, Student student) {
        students.put(uuid, student);
    }
    public void addNew(String name, String lastName, Integer age) {
        var id = UUID.randomUUID();
        var student = Student.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .build();
        students.put(id, student);
        publisher.publishEvent(new EventHolder(this, CREATE_NEW_STUDENT, student));
    }

    public void removeStudentById(UUID uuid) {
        var student = this.students.remove(uuid);
        if (Objects.isNull(student)) {
            publisher.publishEvent(new EventHolder(this, WAS_NOT_REMOVED, uuid));
        } else {
            publisher.publishEvent(new EventHolder(this, REMOVE_STUDENT, student.getId()));
        }
    }

    public void clearAll() {
        this.students.clear();
    }


}
