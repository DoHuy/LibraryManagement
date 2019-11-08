/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import utils.DataAccessHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class này quản lý các thông tin liên quan đến user trong cơ sở dữ liệu.
 *
 * @author Raph
 */
public class User {


    private String username;
    private String password;
    private String name;
    private boolean isFemale;
    private String email;
    private String contact;

    public User(){}

    public User(String username, String password, String name, boolean isFemale, String email, String contact) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isFemale = isFemale;
        this.email = email;
        this.contact = contact;
    }
    
    
    
    /**
     * Lấy username trong bảng TaiKhoan
     *
     * @return String là username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gán giá trị cho username trong bảng TaiKhoan
     *
     * @param username là username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Lấy password trong bảng TaiKhoan
     *
     * @return String là password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gán giá trị cho password trong bảng TaiKhoan
     *
     * @param password là password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Lấy tên trong bảng TaiKhoan
     *
     * @return String là name
     */
    public String getName() {
        return name;
    }

    /**
     * Gán giá trị cho tên trong bảng TaiKhoan
     *
     * @param name là name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lấy giá trị gioiTinhNu trong bảng TaiKhoan
     *
     * @return boolean là gioiTinhNu
     */
    public boolean isIsFemale() {
        return isFemale;
    }

    /**
     * Gán giá trị cho GioiTinhNu trong bảng TaiKhoan
     *
     * @param isFemale là isFemale
     */
    public void setIsFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }

    /**
     * Hàm lấy giá trị email
     *
     * @return String là email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Hàm gán giá trị cho email
     *
     * @param email : email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Hàm lấy giá trị liên hệ
     *
     * @return String : liên hệ
     */
    public String getContact() {
        return contact;
    }

    /**
     * Hàm gán giá trị liên hệ
     *
     * @param contact : liên hệ
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
}
