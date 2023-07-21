package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.UserDTO;
import com.example.ominitrixw.entities.User;
import com.example.ominitrixw.repository.AccountRepository;
import com.example.ominitrixw.repository.UserRepository;
import com.example.ominitrixw.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    private UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO addUser(User newUser) {
        User user = userRepository.findById(newUser.getUserID()).orElse(null);
        if (user == null) {
            userRepository.save(newUser);
            return convertEntityToDTO(newUser);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User newUser) {
        User user = userRepository.findById(newUser.getUserID()).orElse(null);
        if (user != null) {
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO findUserByID(String id) {
        User user= userRepository.findById(id).orElse(null);
        if (user == null)
            return null;
        else {
            return convertEntityToDTO(user);
        }
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        return convertEntityToDTO(user);
    }

    @Override
    public List<UserDTO> findAllUser() {
        return userRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateOTP(String email, String OTP, Timestamp OTPexpire) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (Objects.nonNull(user)) {
            user.setOTP(OTP);
            user.setOTPexpire(OTPexpire);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean sendEmail(String email) {
        try {
            String fromEmail = "canhmail292@gmail.com";
            String password = "ebrhrjedapdtovof";

            String toEmail = email;
            // String toEmail = "pxcpaze@gmail.com";
            String fromName = "Ominitrix Watch";
            String subject = "Khoi phuc mat khau";
            String body = "Noi dung email";
            String OTP = String.format("%06d", new Random().nextInt(999999));

            Timestamp hetHanOTP = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
            // System.out.println(hetHanOTP);
            if (updateOTP(toEmail, OTP, hetHanOTP) == false)
                return false;

            body = "Mã OTP: " + OTP + "  Lưu ý: OTP hết hạn sau 5 phút";

            // cac thong so gmail
            Properties config = new Properties();
            config.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
            config.put("mail.smtp.port", "587"); // TLS Port
            config.put("mail.smtp.auth", "true"); // enable authentication
            config.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

            // dangnhapgmail
            Authenticator authenticator = new Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(config, authenticator);

            MimeMessage mail = new MimeMessage(session);

            InternetAddress sender = new InternetAddress(fromEmail, fromName, "utf-8");

            mail.setFrom(sender);
            mail.setReplyTo(InternetAddress.parse(fromEmail, false));
            mail.setSubject(subject, "utf-8");
            mail.setText(body, "utf-8");
            mail.setSentDate(new Date());
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            Transport.send(mail);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        return true;
    }



}
