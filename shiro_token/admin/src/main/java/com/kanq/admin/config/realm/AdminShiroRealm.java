package com.kanq.admin.config.realm;

import com.kanq.admin.dao.AdminUserRepository;
import com.kanq.admin.entity.po.AdminRole;
import com.kanq.admin.entity.po.AdminUser;
import com.kanq.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class AdminShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override//权限获取 获取指定身份的权限，并返回相关信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account = (String)   SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Optional<AdminUser> adminUserOptional = adminUserRepository.findAdminUserByAccount(account);

        if (!adminUserOptional.isPresent()) {
            throw new AccountException("用户名不正确");
        }
        AdminUser adminUser = adminUserOptional.get();
        AdminRole adminRole = adminUser.getRole();
        info.addRole(adminRole.getName());//添加角色的名称
        if (!adminRole.getPermissionList().isEmpty()) {

            // 获取所有权限的 也就是能访问的接口的路径  添加getPermission组成的集合
            //java8 stream().map().collect()用法 见下面的说明

            info.addStringPermissions(adminRole.getPermissionList()
                    .stream()
                    .map(adminPermission -> adminPermission.getPermission())
                    .collect(Collectors.toList()));
            //-----------------上面代码的拆分---------------------------
            // 获取所有权限的 也就是能访问的接口的路径
            /**List<String> urlList = adminRole.getPermissionList().stream()
                    .map(adminPermission -> adminPermission.getPermission())
                    .collect(Collectors.toList());
            info.addStringPermissions(urlList);
             */

        }

        return info;
    }

    @Override//身份验证 验证账户和密码，并返回相关信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String account = (String) authenticationToken.getPrincipal();
        String userpwd = new String((char[])authenticationToken.getCredentials());
        if (account == null) {
            throw new AccountException("用户名不正确");
        }

        Optional<AdminUser> adminUserOptional = adminUserRepository.findAdminUserByAccount(account);
        if (!adminUserOptional.isPresent()) {
            throw new AccountException("用户名不正确");
        }
        AdminUser adminUser = adminUserOptional.get();

        //在用户注册时密码也同样方式加密
        //String md5Pwd = new SimpleHash("MD5", userpwd, ByteSource.Util.bytes(account + "salt"), 2).toHex();
        String md5pwd = Md5Util.MD5pwd(account, userpwd);
        if (!md5pwd.equalsIgnoreCase(adminUser.getPassword())) {
            throw new RuntimeException("密码有误");
        }
//        SessionUtil.put(SessionConstant.ADMIN_USER, adminUser);

//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        session.setAttribute("adminUser", adminUser);
        //等于上面三行代码
        //SecurityUtils.getSubject().getSession().setAttribute("adminUser", adminUser);
//        AdminUser adminUser1 = (AdminUser) SecurityUtils.getSubject().getSession().getAttribute("adminUser");
//        System.out.println("adminUser----------->" + adminUser1.toString());
        return new SimpleAuthenticationInfo(account, userpwd, getName());
    }
}


/*
有一个集合：

List<User> users = getList(); //从数据库查询的用户集合

现在想获取User的身份证号码；在后续的逻辑处理中要用；

常用的方法我们大家都知道，用for循环，

List<String> idcards=new ArrayList<String>();//定义一个集合来装身份证号码

for(int i=0;i<users.size();i++){

　　idcards.add(users.get(i).getIdcard());

}

这种方法要写好几行代码，有没有简单点的，有，java8 API能一行搞定：

List<String> idcards= users.stream().map(User::getIdcard).collect(Collectors.toList())

解释下一这行代码：

users：一个实体类的集合，类型为List<User>
User：实体类
getIdcard：实体类中的get方法，为获取User的idcard




stream()优点

无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
stream().map()方法的使用示例:


再看几个例子：数组字母小写变大写
List<String> list= Arrays.asList("a", "b", "c", "d");

List<String> collect =list.stream().map(String::toUpperCase).collect(Collectors.toList());
System.out.println(collect); //[A, B, C, D]

数组所有元素，按某种规律计算：
List<Integer> num = Arrays.asList(1,2,3,4,5);
List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
System.out.println(collect1); //[2, 4, 6, 8, 10]
 */