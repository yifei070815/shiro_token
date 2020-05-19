package com.kanq.user.enums;

import lombok.Getter;

/**
 * 返回结果枚举类
 */
@Getter
public enum ResultEnum {
    SUCCESS(1, "成功"),
    FAILED(0, "失败"),
    PASSWORD_WRONG(0, "密码错误"),
    DATA_INPUT_WRONG(0, "数据输入有误"),
    NO_OR_WRONG_NUMBER(0, "手机号错误"),
    VERIFY_CODE_WRONG(0, "验证码错误或失效"),
    JOB_NOT_EXIST(0, "岗位信息不存在"),
    LOGIN_FAILED(0, "用户登录失败"),
    ACCOUNT_OR_PSW_NULL(0, "用户名或密码不能为空"),
    PSW_IS_NULL(0, "密码不能为空"),
    ACCOUNT_IS_NULL(0, "用户名不能为空"),
    ACCOUNT_IS_WRONG(0, "用户不存在或用户名不正确"),
    ACCOUNT_NOT_MATE_TYPE(0, "用户名用户类型不匹配"),
    PHONE_NUM_IS_EXIST(0, "该手机号已被注册"),
    USER_NOT_EXIST(0, "用户不存在"),
    ROLE_NOT_EXIST(0, "角色不存在"),
    PERMISSION_NOT_EXIST(0, "权限不存在"),
    IS_SOLDIER_WRONG(0, "个人用户信息类型有误"),
    NO_PERSON_DATA(0, "没有个人用户信息"),
    COMPANY_DETAILS_WRONG(0, "企业用户信息查询失败"),
    DATA_WRONG(0, "数据类型有误"),
    QUERY_ACTIVITY_FAIL(0, "活动查询失败"),
    ACTIVITY_OVERDUE(0, "活动已过期"),
    ACTIVITY_NOT_EXIST(0, "该活动信息不存在"),
    ACTIVITY_TYPE_WRONG(0, "活动类型输入有误"),
    ACTIVITY_ALREADY_APPLY(0, "该活动已经报名"),
    QUERY_DATA_WRONG(0, "数据查询失败"),
    PROJECT_NOT_EXIST(0, "该项目信息不存在"),
    NO_DATA(0, "没有数据");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
