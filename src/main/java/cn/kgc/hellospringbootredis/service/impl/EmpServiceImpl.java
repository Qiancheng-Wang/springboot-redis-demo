package cn.kgc.hellospringbootredis.service.impl;

import cn.kgc.hellospringbootredis.entities.Emp;
import cn.kgc.hellospringbootredis.mapper.EmpMapper;
import cn.kgc.hellospringbootredis.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    public RedisTemplate redisTemplate;

    @Resource
    private EmpMapper empMapper;

    @Override
    public void add(Emp emp){
        empMapper.insert(emp);
    }

    @Override
    public Object getEmpById(Integer id){

        // 1. from redis, if true, return
        Object user = redisTemplate.opsForValue().get("user:" + id);
        if(user == null){
            synchronized (this.getClass()) {
                Object user2 = redisTemplate.opsForValue().get("user:" + id);
                if(user2 == null){
                    log.debug(" -----> reading db <-------- ");
                    // read db
                    Emp emp = empMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set("user:" + id, emp);
                    return emp;
                }else{
                    log.debug(" -----> reading memory <-------- ");
                    return user2;
                }
            }
        }else{
            log.debug(" -----> reading memory <-------- ");
        }

        return user;
    }
}
