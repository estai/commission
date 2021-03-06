package com.epam.action;

import com.epam.entity.Score;
import com.epam.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private  static final String LOGIN_REGEX="^[A-Za-z0-9_-]{3,15}";
    private  static final String PASS_REGEX="^[A-Za-z0-9_-]{5,}";
    public static boolean isEmptyLogin(String login,HttpServletRequest req){
        if (login==null ||login.isEmpty()) {
            req.setAttribute("loginError", "login can't be null");
            return true;
        }
        return false;
    }
    public static boolean isEmptyPass(String pass,HttpServletRequest req){
        if (pass==null || pass.isEmpty()) {
            req.setAttribute("passError", "password can't be empty");
            return true;
        }
        return false;
    }





    public static boolean isLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isCertificate(String certificate) {
        return certificate.matches("");
    }

    public static boolean isPass(String password) {
        return password.matches(PASS_REGEX);
    }
    public static boolean register(String login,String pass,String confirmPass,HttpServletRequest req){
        if(isEmptyLogin(login,req)) return false;
        if(isEmptyPass(pass,req)) return false;
       // if(!isLogin(login)){ req.setAttribute("loginError","incorrect login"); return false;}
      //  if(!isPass(pass)){req.setAttribute("passError", "incorrect pass"); return false; }
        if (!pass.equals(confirmPass)) {req.setAttribute("passError", "passwords do not match"); return false;}
        return true;
    }


    public  static boolean isNullParameters(String lastname, String firstname, String middlename, String certificate, String numberCertificate) {
        if (lastname == null || firstname == null ||
                middlename == null || certificate == null || numberCertificate == null || lastname.isEmpty() || firstname.isEmpty() ||
                middlename.isEmpty() || certificate.isEmpty() || numberCertificate.isEmpty() ) {
            return true;
        }
        return false;
    }
    public  static boolean isNullParameters(String parameter) {
        if (parameter.isEmpty() || parameter==null) {
            return true;
        }
        return false;
    }


    public static boolean isChange(Score newScore, Map<Subject, Score> scoreMap) {
        Iterator<Subject> iterator = scoreMap.keySet().iterator();
        while(iterator.hasNext()) {
            Subject subject = iterator.next();
            if(!subject.isMain()) {
                Score score = scoreMap.get(subject);
                if(!score.equals(newScore)) return true;
            }
        }

        return false;
    }
}
