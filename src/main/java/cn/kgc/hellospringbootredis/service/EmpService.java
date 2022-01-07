package cn.kgc.hellospringbootredis.service;

import cn.kgc.hellospringbootredis.entities.Emp;

public interface EmpService {
    public void add(Emp emp);
    public Object getEmpById(Integer id);
}
