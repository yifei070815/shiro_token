package com.kanq.user.dao;

import com.kanq.user.entiey.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "select * from employee  where phoneNum = ?1 ",nativeQuery = true)
    Optional<Employee> findByPhoneNum(String phoneNum);



    //After在这个时间之后  Before之前
    List<Employee> findByCreateTimeAfter(Date time);


    //Date格式的时间 也可以用 <   > 来比较
    @Query(value = "select * from employee  where createTime > ?1 ",nativeQuery = true)
    List<Employee> findByTimeBefore(Date time);


}
