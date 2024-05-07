package com.example.CharityOrganization.controller;
import com.example.CharityOrganization.model.Admin.Admin;
import com.example.CharityOrganization.model.Admin.AdminDTO;
import com.example.CharityOrganization.service.Admin.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Master/Admin")
public class MasterAdminController {

@Autowired
AdminService adminService;


@PostMapping("/add_admin")
public String addNewUser(@RequestBody Admin admin){
adminService.addAdminWithUser(admin);
return "Successfully added new user!";
}


@GetMapping("/admins")

public List<AdminDTO> getAllAdmins(){
return adminService.getAdminsWithDecryptedData();
}


@DeleteMapping("/delete_admin/{id}")
public String deleteAdmin(@PathVariable Integer id){
    return adminService.deleteAdminById(id);
}





}
















