package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public class CustomerRepositoryImpl extends DAOTemplate implements CustomerRepository {

	@Override
	public boolean login(Customer customer) {
		System.out.println("ログイン");
		System.out.println(customer);
		
		String sql = "SELECT * FROM TANOMO.CUSTOMER WHERE USERID = ? AND PASSWORD = ?;";
		boolean check = false;
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, customer.getUserId());
			pstmt.setString(2, customer.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(check);
		System.out.println("ログイン完了");
		return check;
	}
	
	@Override
	public boolean userIdCheck(Customer customer) {
		System.out.println("ユーザーID確認");
		System.out.println(customer);
		
		String sql = "SELECT * FROM TANOMO.CUSTOMER WHERE USERID = ?;";
		boolean check = false;

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, customer.getUserId());
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				check = true;
				signUp(customer);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(check);
		System.out.println("ユーザーID確認完了");
		return check;
	}
	
	public void signUp(Customer customer) {
		System.out.println("新規登録");
		System.out.println(customer);
		
		String sql = "INSERT INTO TANOMO.CUSTOMER VALUES (?,?,?,'USER');";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, customer.getUserId());
			pstmt.setString(2, customer.getPassword());
			pstmt.setInt(3, customer.getPlaceId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("新規登録完了");
	}
	
	@Override
	public ArrayList<Customer> userRetrieve(String userId) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		System.out.println("ユーザー情報取り出し");
		System.out.println(userId);
		
		String sql = "SELECT TANOMO.CUSTOMER.PLACEID,TANOMO.PLACE.PLACENAME,AUTHORITY FROM TANOMO.CUSTOMER "
				+ "INNER JOIN TANOMO.PLACE ON TANOMO.CUSTOMER.PLACEID = TANOMO.PLACE.PLACEID "
				+ "WHERE USERID = ?;";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == true) {
				int placeId = rs.getInt("PLACEID");
				String placeName = rs.getString("PLACENAME");
				String authority = rs.getString("AUTHORITY");
				Customer customer = new Customer(placeId,placeName, authority);
				list.add(customer);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("ユーザー情報取り出し完了");
		return list;
	}
}
