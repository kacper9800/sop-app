/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.controllers;

import org.springframework.web.bind.annotation.*;
import pl.sop.dao.entitiy.Test;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    private List<Test> tests;

    public TestController() {
        this.tests = new ArrayList<>();
        tests.add(new Test("Nowy","Test"));
        tests.add(new Test("Nowy2","Test2"));
        tests.add(new Test("Nowy3","Test3"));
    }

    @GetMapping(value = "/api/test")
    public List<Test> testMethod() {
        return tests;
    }

    @PostMapping(value = "/api/test")
    public boolean getTest(@RequestBody Test test) {
        return tests.add(test);
    }
    @DeleteMapping(value = "/api/test")
    public void deleteTest(@RequestParam int index) {
        tests.remove(index);
    }

}
