package com.kanq.admin.controller;

import com.kanq.admin.dao.AdminUserRepository;
import com.kanq.admin.entity.Result;
import com.kanq.admin.entity.po.AdminUser;
import com.kanq.common.enums.CommonStatusEnum;
import com.kanq.common.enums.ResultEnum;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testController")
public class TestController {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @RequiresPermissions("testController:test1")//权限管理;
    @RequestMapping("/test1")
    public Result getMessageTest1(String account){

        Optional<AdminUser> adminUserByAccount = adminUserRepository.findAdminUserByAccount(account);
        if(!adminUserByAccount.isPresent()){
            throw new RuntimeException(ResultEnum.USER_NOT_EXIST.getMessage());
        }

        AdminUser adminUser = adminUserByAccount.get();

        System.out.println(adminUser.toString());

        return new Result(200,ResultEnum.SUCCESS.getMessage(),adminUser) ;
    }


    @RequiresPermissions("testController:test2")//权限管理;
    @RequestMapping("/test2")
    public String getMessageTest2(){
        return "getMessageTest2 "  ;
    }


    //将数据导出成Excel文件
    @RequiresPermissions("testController:UserExcelDownloads")//权限管理;
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(
            //输入文件保存名称，默认的名子是 AdminUser
            @RequestParam(value="saveName",required=false,defaultValue="AdminUser") String saveName,
            HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<AdminUser> adminUserList = adminUserRepository.selectAll();

        String fileName = saveName  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "ID", "姓名realName", "账号account",
                "密码password","手机号phoneNum","email","imageUrl",
                "roleId","createTime","updateTime","目前状态status"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (AdminUser adminUser : adminUserList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(adminUser.getId());
            row1.createCell(1).setCellValue(adminUser.getRealName());
            row1.createCell(2).setCellValue(adminUser.getAccount());
            row1.createCell(3).setCellValue(adminUser.getPassword());
            row1.createCell(4).setCellValue(adminUser.getPhoneNum());
            row1.createCell(5).setCellValue(adminUser.getEmail());
            row1.createCell(6).setCellValue(adminUser.getImageUrl());
            row1.createCell(7).setCellValue(adminUser.getId());
            row1.createCell(8).setCellValue(adminUser.getCreateTime()+"");
            row1.createCell(9).setCellValue(adminUser.getUpdateTime()+"");
            row1.createCell(10).setCellValue(CommonStatusEnum.valueOf(adminUser.getStatus()+"").getDescription());
            rowNum++;
        }

        //准备将Excel的输出流通过response输出到页面下载

        //八进制输出流
        //response.setContentType("application/octet-stream");
        response.setContentType("application/ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        //刷新缓冲
        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }




//    public static void main(String[] args) {
//        System.out.println(CommonStatusEnum.fromStatus(0).getDescription());
//
//        System.out.println(CommonStatusEnum.valueOf("NOMAL").getDescription());
//
//    }









}
