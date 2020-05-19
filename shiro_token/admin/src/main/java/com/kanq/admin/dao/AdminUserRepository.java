package com.kanq.admin.dao;

import com.kanq.admin.entity.po.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {


    Optional<AdminUser> findAdminUserByAccount (String account);

    @Query(value = "select * from admin_user   ",nativeQuery = true)
    List<AdminUser> selectAll();

//    Optional<AdminUser> findAdminUserByAccount(String account);
}
