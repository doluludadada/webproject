package org.gla.springbackend.controller;

import jakarta.validation.Valid;
import org.gla.springbackend.entity.User;
import org.gla.springbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller // 標記為 Spring MVC 控製器
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 顯示登錄頁面
    @GetMapping("/login")
    public String showLoginPage(Model model, @RequestParam(value = "showSignupForm", required = false) Boolean showSignupForm) {
        if (showSignupForm != null && showSignupForm) {
            // 如果請求參數中有 showSignupForm=true，則顯示登錄檔單
            model.addAttribute("showSignupForm", true);
            model.addAttribute("user", new User());
        } else {
            // 默認顯示登錄表單
            model.addAttribute("showSignupForm", false);
        }
        return "login";
    }


    // 處理註冊請求
    @PostMapping("/signUp")
    public String processSignUp(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult,
                                @RequestParam("cpassword") String confirmPassword,
                                Model model) {

        // 驗證密碼是否一致
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.user", "Passwords do not match");
        }

        // 檢查使用者名稱或信箱是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            // 設定標誌，顯示登錄檔單
            model.addAttribute("showSignupForm", true);
            // 新增 'user' 對象到模型中，保留使用者已輸入的資料
            model.addAttribute("user", user);
            return "login";
        }

        // 加密密碼
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 保存使用者
        userRepository.save(user);

        // 重新導向到登錄頁面，顯示註冊成功資訊
        return "redirect:/login?signupSuccess";
    }

    // 顯示首頁
    @GetMapping("/homepage")
    public String showHomepage(Model model, Principal principal) {
        // 獲取當前登入的使用者名稱
        String username = principal.getName();
        // 根據使用者名稱查詢使用者資料
        User user = userRepository.findByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        return "homepage";
    }
}