package by.matkun.crowdfunding_company.controller;

import by.matkun.crowdfunding_company.model.Company;
import by.matkun.crowdfunding_company.model.User;
import by.matkun.crowdfunding_company.service.CompanyServiceImplement;
import by.matkun.crowdfunding_company.service.UserServiceImplement;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private CompanyServiceImplement companyService;

    @Autowired
    private UserServiceImplement userService;

    @GetMapping("/{userId}")
    public String getListCompany(@AuthenticationPrincipal User currentUser, @PathVariable (name = "userId") User user, Model model){
        Set<Company> listCompany = user.getCompanies();
        model.addAttribute("listCompany",listCompany);
        model.addAttribute("isCurrentUser",currentUser.equals(user));
        model.addAttribute("currentUser",user.getId());
        return "user";
    }

    @PostMapping("/{userId}")
    public String createNewCompany(Company company, @AuthenticationPrincipal User currentUser,@PathVariable (name = "userId")User user){
        company.setOwner(user);
        companyService.save(company);
        return "redirect:/user/{userId}";
    }
    @GetMapping("/{userId}/editCompany/{companyId}")
    public String editCompany(@PathVariable (name = "companyId")Long companyId,@PathVariable (name = "userId") User user , Model model){
        model.addAttribute("company",companyService.find(companyId));
        model.addAttribute("currentUser",user);
        return "editCompany";
    }

    @PostMapping("{userId}/editCompany/{companyId}")
    public String updateCompany(Company company, @PathVariable(name = "companyId") Long companyId,@RequestParam Long owner){
        company.setId(companyId);
        companyService.save(company);
        return "redirect:/user/" + owner;
    }
    @PostMapping("/{userId}/deleteCompany")
        public String deleteCompany(@RequestParam Long companyId, @PathVariable (name = "userId") User user){
        companyService.delete(companyId);
            return "redirect:/user/" + user.getId() ;
        }
    }
