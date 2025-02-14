package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Admin;
import com.errandbuddy.errandbuddy.Dto.request.CreateAdminRequest;
import com.errandbuddy.errandbuddy.Dto.request.EmailDetails;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Data.Repository.AdminRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation  implements AdminService{
    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ErrandBuddyResponse createAdmin(CreateAdminRequest adminRequest) {
        if (adminRepository.existsAdminByEmail(adminRequest.getEmail())){
            return ErrandBuddyResponse.builder()
                    .responseMessage(ErrandBuddyUtils.Admin_Exist_Message)
                    .build();

        }

        Admin newAdmin = Admin.builder()
                .email(adminRequest.getEmail())
                .name(adminRequest.getName())
                .password(adminRequest.getPassword())
                .phoneNumber(adminRequest.getPhoneNumber())
                .build();

        Admin savedAdmin = adminRepository.save(newAdmin);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedAdmin.getEmail())
                .subject("welcome!")
                .messageBody("Welcome to Errand Buddy, where running your errands are magical\" +\n" +
                        "                        \"\\nErrandBuddyAdmin Name: \" + savedAdmin.getName() + \" \" + \"\\nErrandBuddyAdmin Email:\" + savedAdmin.getEmail() + \" \" + \"\\nErrandBuddyAdmin Password:\" + savedAdmin.getPassword())")
                .build();

        emailService.sendEmailAlert(emailDetails);

        return ErrandBuddyResponse.builder()
                .responseMessage(ErrandBuddyUtils.Admin_Created_Successfully)
                .build();



    }

    @Override
    public Boolean loginAdmin(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByEmail(loginRequest.getEmail());

        if ( admin == null) {
            return false;
        }
        return loginRequest.getPassword().equals(admin.getPassword());
    }
}
