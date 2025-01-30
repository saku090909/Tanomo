package com.example.demo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public class OrderRepositoryImpl extends DAOTemplate implements OrderRepository {

	@Override
	public void rootRegist(Order order) {
		
		System.out.println("ルートオーダー登録");
		System.out.println(order);
		
		String sql = "INSERT INTO TANOMO.ORDERROOT (DATE,USERID,ITEMID,ITEMNAME,PRICE,PLACEID,PLACENAME,ORDERID,BUY,CANCEL) "
				+ "SELECT ?,?,?,?,?,?,?,COUNT(DISTINCT TANOMO.ORDER.ORDERID) + 1,0,0 FROM TANOMO.ORDERROOT "
				+ "INNER JOIN TANOMO.ORDER;";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setString(4, order.getItemName());
			pstmt.setInt(5, order.getPrice());
			pstmt.setInt(6, order.getPlaceId());
			pstmt.setString(7, order.getPlaceName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("ルートオーダー登録完了");
		userRegist(order);
	}
	
	public void userRegist(Order order) {
		
		System.out.println("ユーザーオーダー登録");
		System.out.println(order);
		
		String sql = "INSERT INTO TANOMO.ORDER (DATE,USERID,ITEMID,PLACEID,ORDERID,BUY,CANCEL) "
				+ "SELECT ?,?,?,?,COUNT(TANOMO.ORDER.ORDERID) + 1,0,0 FROM TANOMO.ORDER;";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setInt(4, order.getPlaceId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("ユーザーオーダー登録完了");
	}
	
	
	@Override
	public void rootBuy(Order order) {
		System.out.println("ルートオーダー購入");
		System.out.println(order);
		
		String sql = "UPDATE TANOMO.ORDERROOT SET BUY = 1 "
				+ "WHERE DATE = ? AND USERID = ? AND ITEMID = ? AND ORDERID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setInt(4, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("ルートオーダー購入完了");
		userBuy(order);
	}
	
	public void userBuy(Order order) {
		System.out.println("ユーザーオーダー購入");
		System.out.println(order);
		
		String sql = "UPDATE TANOMO.ORDER SET BUY = 1 "
				+ "WHERE DATE = ? AND USERID = ? AND ITEMID = ? AND ORDERID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setInt(4, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("ユーザーオーダー購入完了");
	}
	
	@Override
	public void rootCancel(Order order) {
		System.out.println("ルートオーダー取り消し");
		System.out.println(order);
		
		String sql = "UPDATE TANOMO.ORDERROOT SET CANCEL = 1 "
				+ "WHERE DATE = ? AND USERID = ? AND ITEMID = ? AND ORDERID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setInt(4, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("ルートオーダー取り消し完了");
		userCancel(order);
	}
	
	public void userCancel(Order order) {
		System.out.println("ユーザーオーダー取り消し");
		System.out.println(order);
		
		String sql = "UPDATE TANOMO.ORDER SET CANCEL = 1 "
				+ "WHERE DATE = ? AND USERID = ? AND ITEMID = ? AND ORDERID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setDate(1, order.getDate());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getItemId());
			pstmt.setInt(4, order.getOrderId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("ユーザーオーダー取り消し完了");
	}
	
	@Override
	public ArrayList<Order> rootRetrieve() {
		ArrayList<Order> list = new ArrayList<Order>();
		
		System.out.println("ルートオーダー取り出し");
		
		String sql = "SELECT DATE,USERID,ITEMID,ITEMNAME,PRICE,PLACEID,PLACENAME,ORDERID,BUY,CANCEL FROM TANOMO.ORDERROOT;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				Date date = rs.getDate("DATE");
				String userId = rs.getString("USERID");
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				int price = rs.getInt("PRICE");
				int placeId = rs.getInt("PLACEID");
				String placeName = rs.getString("PLACENAME");
				int orderId = rs.getInt("ORDERID");
				byte buy = rs.getByte("BUY");
				byte cancel = rs.getByte("CANCEL");
				Order order = new Order(date, userId, itemId, itemName, price, placeId, placeName, orderId, buy, cancel);
				list.add(order);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("ルートオーダー取り出し完了");
		return list;
	}
	
	@Override
	public ArrayList<Order> userRetrieve(String loginId) {
		ArrayList<Order> list = new ArrayList<Order>();
		
		System.out.println("ユーザーオーダー取り出し");
		
		String sql = "SELECT DATE,USERID,TANOMO.ORDER.ITEMID,ITEMNAME,PRICE,TANOMO.ORDER.PLACEID,PLACENAME,ORDERID FROM TANOMO.ORDER "
				+ "INNER JOIN TANOMO.ITEM ON TANOMO.ORDER.ITEMID = TANOMO.ITEM.ITEMID "
				+ "INNER JOIN TANOMO.PLACE ON TANOMO.ORDER.PLACEID = TANOMO.PLACE.PLACEID "
				+ "WHERE USERID = ? AND BUY = 0 AND CANCEL = 0;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, loginId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				Date date = rs.getDate("DATE");
				String userId = rs.getString("USERID");
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				int price = rs.getInt("PRICE");
				int placeId = rs.getInt("PLACEID");
				String placeName = rs.getString("PLACENAME");
				int orderId = rs.getInt("ORDERID");
				Order order = new Order(date, userId, itemId, itemName, price, placeId, placeName, orderId);
				list.add(order);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("ユーザーオーダー取り出し完了");
		return list;
	}
	
	@Override
	public ArrayList<Order> rootMatch(int orderId) {
		ArrayList<Order> list = new ArrayList<Order>();
		
		System.out.println("ルートオーダー一致確認");
		System.out.println(orderId);
		
		String sql = "SELECT DATE,USERID,ITEMID,ITEMNAME,PRICE,PLACEID,PLACENAME,BUY,CANCEL FROM TANOMO.ORDERROOT "
				+ "WHERE ORDERID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, orderId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == true) {
				Date date = rs.getDate("DATE");
				String userId = rs.getString("USERID");
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				int price = rs.getInt("PRICE");
				int placeId = rs.getInt("PLACEID");
				String placeName = rs.getString("PLACENAME");
				byte buy = rs.getByte("BUY");
				byte cancel = rs.getByte("CANCEL");
				Order order = new Order(date, userId, itemId, itemName, price, placeId, placeName, orderId, buy, cancel);
				list.add(order);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("ルートオーダー一致確認完了");
		return list;
	}
	
}
