package com.example.studentregistration;

import com.example.studentregistration.entity.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.*;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentsRepository;
    @ShellMethod(key = "p")
    public void printAll() {
        studentsRepository.getAll().forEach(System.out::println);
    }

    @ShellMethod(key = "n")
    public void addNew(@ShellOption(value = "n") String name,
                       @ShellOption(value = "ln") String lastName,
                       @ShellOption(value = "a") Integer age) {
        studentsRepository.addNew(name, lastName, age);
    }

    @ShellMethod(key = "r")
    public void removeStudent(@ShellOption(value = "id") UUID uuid) {
        studentsRepository.removeStudentById(uuid);
    }

    @ShellMethod(key = "c")
    public String clearAll() {
        studentsRepository.clearAll();
        return "All students was removed";
    }
}
