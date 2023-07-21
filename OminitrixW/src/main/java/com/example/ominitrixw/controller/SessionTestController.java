package com.example.ominitrixw.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ominitrix/session")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionTestController {
    @GetMapping("/data")
    public String getData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object data = session.getAttribute("cart");
        System.out.println(data);
        return "data";
    }

//    @PostMapping("/data")
//    public void saveData(HttpServletRequest request, @RequestBody String data) {
//        HttpSession session = request.getSession();
//        session.setAttribute("data", data);
//    }
//@GetMapping("/addData")
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Tạo một đối tượng Cookie với tên và giá trị tương ứng
//        Cookie cookie = new Cookie("session-id", "ABC123");
//
//        // Đặt thời gian sống của cookie (tùy chọn)
//        cookie.setMaxAge(3600); // Thời gian sống là 1 giờ (3600 giây)
//
//        // Đặt các thuộc tính khác cho cookie (tùy chọn)
//        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
//        cookie.setHttpOnly(true); // Chỉ cho phép truy cập thông qua HTTP
//
//        // Đưa cookie vào phản hồi (response)
//        response.addCookie(cookie);
//    }
//    @GetMapping("/cuki")
//    public String getCookie(@CookieValue("ABC123") String cookieValue) {
//        // Xử lý cookieValue tại đây
//        return cookieValue;
//    }
}
