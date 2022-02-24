package manager;

import entity.Login;
import manager.exception.*;
import service.LoginService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private String password,firstName,username,nationalId;
    private LoginService loginService = new LoginService();

    public Utility() throws SQLException, ClassNotFoundException {
    }

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
    }

    public String setFirstName(){
        while(true){
            System.out.print("Enter first name(just alpha):");
            try {
                firstName = input.nextLine();
                checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setLastName(){
        while(true){
            System.out.print("Enter last name(just alpha):");
            try {
                firstName = input.nextLine();
                checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setUsername() throws SQLException {
        while(true) {
            while(true) {
                System.out.print("Enter username(start with alpha):");
                try {
                    username = input.nextLine();
                    checkUsername(username);
                    break;
                } catch (InvalidUsername except) {
                    System.out.println(except.getMessage());
                }
            }
            List<Login> loginList = loginService.findAll();
            if(loginList == null)
                break;
            int i = 0;
            for (Login login:loginList)
            {
                Login login1 = new Login();
                login1 = login;
                if(login.getUsername().equals(username)){
                    i = 1;
                    break;
                }
            }
            if(i == 1)
                System.out.println("This username is defined before!");
            else
                break;
        }
        return username;
    }

    public String setNationalId(){
        while(true) {
            System.out.print("Enter nationalId:");
            nationalId = input.nextLine();
            try {
                nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalIdException exception){
                System.out.println("You enter a wrong nationalId!");
            }
        }
        return nationalId;
    }

    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");
        for (Character ch:name.toCharArray()
        ) {
            if(Character.isDigit(ch))
                throw new InvalidName("name can not have number!");
        }
        for (Character ch:name.toCharArray()
        ) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");

        }
    }


    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalIdException();
        if(nationalId.equals(""))
            throw new InvalidNationalIdException();
        for (Character ch:nationalId.toCharArray()) {
            if(!Character.isDigit(ch))
                throw new InvalidNationalIdException();
        }
    }

    public void passwordCheck(String password){
        if(password.length() < 3 )
            throw new InvalidPassword("password should be more than 2 ");
        char[] passwordArray = password.toCharArray();
        char[] signArray =  new char[] {'!','@','#','$','%','^','&','*','(',')','-','+','=','.',',','>','<','?','/','|',':',';'};
        int space = 0,lowerCase = 0,upperCase = 0,sign = 0,digit = 0;
        for(int i=0;i<passwordArray.length;i++)
            if(Character.isSpaceChar(passwordArray[i]))
                ++space;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isUpperCase(passwordArray[i]))
                ++upperCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isLowerCase(passwordArray[i]))
                ++lowerCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isDigit(passwordArray[i]))
                ++digit;
        for(int i=0;i<signArray.length;i++)
            for(int j=0;j<passwordArray.length;j++)
                if(signArray[i] == passwordArray[j])
                    ++sign;
        if( (space == 0) || (lowerCase == 0) || (upperCase == 0) || (sign == 0) || (digit == 0) )
            throw new InvalidPassword("password should have space+lowerCase+upperCase+sign+digit!");
    }

    public void checkUsername(String username){
        if(username.length() < 3 )
            throw new InvalidUsername("Username should be more than 2!");
        char ch = username.charAt(0);
        if(Character.isDigit(ch))
            throw new InvalidUsername("Username can not start with digit!");
    }

}
