# Java Spring Security Loggin Practice

> 簡單會員登入系統練習
![image](https://i.imgur.com/m7zd4Mk.png)

## 功能
1. 登入驗證
2. 使用者密碼加密
3. 區別user存取權限(guest與不同user可存取不同內容)
4. 區別user畫面(guest與不同user看到不同內容)

## 使用技術
- 後端 - Spring Boot( Spring MVC + Spring Security, Spring Data JPA)
- 資料庫 - MySQL
- 前端 - BootStrap, Thymeleaf

## 須自行設定項目
- Application.properties內mysql連線
- 建立db schema(src/main/resources/static/create_script.sql)
- 請以maven專案打開
