package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.DTO.Request.AdminRequestDTO;
import com.gym.gym.DTO.Request.LoginRequestDTO;
import com.gym.gym.DTO.Response.AdminResponseDTO;
import com.gym.gym.DTO.Response.LoginResponse;
import com.gym.gym.Services.AdminService;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminservicio;

    public AdminController(AdminService adminService){
        this.adminservicio=adminService;
    }
    @PostMapping
    public ResponseEntity<?> crearAdmin(@RequestBody AdminRequestDTO admin){
        AdminResponseDTO admincreado = adminservicio.crearAdmin(admin);
        return ResponseEntity.ok(admincreado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> ingresarlogin(@RequestBody LoginRequestDTO login) {
        LoginResponse loginingreso = adminservicio.loginadmin(login);
        return ResponseEntity.ok(loginingreso);
    }
    

}
