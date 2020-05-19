package com.kanq.user.tonkenService;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kanq.user.entiey.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String createToken(Employee employee) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() +  60 * 1000;// 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        String employeeString = JSONObject.toJSONString(employee);
        token = JWT.create()
                //将用户信息加入载荷
                .withClaim("employee", employeeString)
                .withClaim("password", employee.getPassword())
                //将用户账号加入载荷
                .withAudience(employee.getPhoneNum())
                //将token发布时间加入载荷
                .withIssuedAt(start)
                //将token失效时间加入载荷
                .withExpiresAt(end)
                //使用秘钥为密码 进行HMAC256加密
                .sign(Algorithm.HMAC256("KANQ-TOKEN-SECRET"));

        return token;
    }



    //String phoneNum = JWT.decode(token).getAudience().get(0);

//    //获取user信息  token中的信息
//    String employee_json = JWT.decode(token).getClaim("employee").asString();
//			System.out.println(employee_json);
//    //获取发布时间
//    Date iat_time = JWT.decode(token).getIssuedAt();
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(df.format(iat_time));
//    //获取失效时间
//    Date exp_time = JWT.decode(token).getExpiresAt();
//			System.out.println(df.format(exp_time));
//    //获取整个载荷Base64URL
//    String payload = JWT.decode(token).getPayload();
//			System.out.println(payload);
//    //获取整个头部Base64URL
//    String header = JWT.decode(token).getHeader();
//			System.out.println(header);
//    //获取整个签名的Base64URL
//    String signature = JWT.decode(token).getSignature();
//			System.out.println(signature);
//    //头部详细信息
//    String type = JWT.decode(token).getType();
//			System.out.println(type);
//    String algorithm = JWT.decode(token).getAlgorithm();


    //token的生成方法
    //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
    //withAudience()存入需要保存在token的信息，这里我把用户ID存入token中

}
