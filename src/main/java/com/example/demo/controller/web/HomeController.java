package com.example.demo.controller.web;

import com.example.demo.model.request.BuildingSearchRequest;
import com.example.demo.utils.DistrictCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    // 🏠 Trang chủ
    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage(BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("districts", DistrictCode.type());
        return mav;
    }

    // 📘 Giới thiệu
    @GetMapping("/gioi-thieu")
    public ModelAndView introducePage() {
        return new ModelAndView("web/introduce");
    }

    // 🏗️ Sản phẩm (danh sách dự án)
    @GetMapping("/san-pham")
    public ModelAndView buildingList() {
        return new ModelAndView("web/list");
    }

    // 📰 Tin tức
    @GetMapping("/tin-tuc")
    public ModelAndView newsPage() {
        return new ModelAndView("web/news");
    }

    // 📞 Liên hệ
    @GetMapping("/lien-he")
    public ModelAndView contactPage() {
        return new ModelAndView("web/contact");
    }

    // 🔐 Đăng nhập
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    // 🚫 Truy cập bị từ chối
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        return new ModelAndView("redirect:/login?accessDenied");
    }

    // 🚪 Đăng xuất
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/trang-chu");
    }
}
