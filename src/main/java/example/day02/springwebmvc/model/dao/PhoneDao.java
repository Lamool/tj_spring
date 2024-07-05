package example.day02.springwebmvc.model.dao;

import example.day02.springwebmvc.model.dto.PhoneDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneDao {
    // [1] 싱글톤 패턴 ((패턴 : 문법 X))
    private static PhoneDao phoneDao = new PhoneDao();   // ((객체를 미리 만들어 놓는 것))
    // 다른 곳에서 사용하지 못하도록 생성자 private
    private PhoneDao(){
        try {   // DB 연동
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springexample", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static PhoneDao getInstance() {
        return phoneDao;
    }

    // [2] JDBC 인터페이스
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    // 1.
    public boolean postPhone(PhoneDto phoneDto) {
        try{
            String sql = "Insert into phoneBook(name, phone) values (?, ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, phoneDto.getName());
            ps.setString(2, phoneDto.getPhone());
            int count = ps. executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    // 2.
    public ArrayList<PhoneDto> getPhone() {
        ArrayList<PhoneDto> list = new ArrayList<>();
        try {
            String sql = "select * from phonebook";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);  // 첫 번째 필드 순서번호 또는 필드명인 필드의 값 호출
                String name = rs.getString("name");
                String phone = rs.getString(3);
                PhoneDto phoneDto = new PhoneDto(id, name, phone);
                list.add(phoneDto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

}
