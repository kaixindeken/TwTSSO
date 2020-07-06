# 天外天单点登陆`Api`（适用于`java`）

## 1、使用依赖安装
> 目前因为网络原因无法对该项目进行在 `maven` 中央仓库的部署

## 2、使用源码安装
* `clone` 并使用 `idea` 打开本项目
* 等待加载完成后，点击侧边栏的 `Maven` 
* 打开本项目的 `Lifecycle`
* 双击 `install` (如果之前安装过本项目请先双击 `clean`)

## 3、依赖引入以及使用
* 依赖引入(注意版本号)
```xml
<dependency>
    <groupId>io.github.kaixindeken</groupId>
    <artifactId>TwTSSO</artifactId>
    <version>0.2</version>
</dependency>
```
* 使用说明（以 `SpringBoot` 框架为例 ）

  `LoginController`

  ```java
  package com.example.ssotest.controllers;
  
  import com.example.ssotest.services.Result;
  import com.example.ssotest.services.ResultGenerator;
  import io.github.kaixindeken.TwTSSO.Api;
  import org.springframework.web.bind.annotation.*;
  
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  @RestController
  class LoginController {
  
      private static Api sso = null;
  
      //使用提供的app_id和app_key进行实例化
      public static Api getSSO(){
          String app_key = "";
          String app_id = "";
          return new Api(app_id, app_key);
      }
  
      //这里需要声明一个需要返回的页面,单点登陆会跳转到另一个网站(登陆页面),而这个页面被所有项目共用,在那个页面用户点击登陆后,之所以会返回这个项目,而不是其他的项目,就是这个$link的作用
      //这个$link会在用户点击登陆成功后访问,会携带一个token,这个token用于获得登录用户的信息
      //如String link = "http://127.0.0.1:8080/api/ssoLogin";
      //getLoginUrl 会返回一个跳转到单点登陆页面连接的url
      @ResponseBody
      @RequestMapping(value = "/api/login", method = RequestMethod.GET)
      public static void login(HttpServletResponse response) throws Exception {
          sso = getSSO();
  
          String link = "";
  
          String login_url = sso.getLoginUrl(link);
          response.sendRedirect(login_url);
      }
  
      //通过fetchUserInfo获取用户信息
      @ResponseBody
      @RequestMapping(value = "/api/ssoLogin", method = RequestMethod.GET)
      public static Object ssoLogin(@RequestParam String token,HttpServletRequest request, HttpServletResponse response) throws Exception {
          return sso.fetchUserInfo(token);
      }
  }
  ```

  

  
