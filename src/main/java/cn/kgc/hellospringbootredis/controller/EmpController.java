package cn.kgc.hellospringbootredis.controller;

import cn.kgc.hellospringbootredis.entities.Emp;
import cn.kgc.hellospringbootredis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/emp")
    public String addEmp(Emp emp){
        empService.add(emp);
        return "add success";
    }

    @GetMapping("emp/{id}")
    public Object getEmpById(@PathVariable("id") Integer id){
        ExecutorService es =  Executors.newFixedThreadPool(200);
        for(int i = 0; i < 500; i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    empService.getEmpById(id);
                }
            });
        }

        return empService.getEmpById(id);
    }
}
