package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

@Repository
public class ItemRepositoryImpl extends DAOTemplate implements ItemRepository {

	@Override
	public int regist(Item item){
		System.out.println("アイテム追加");
		System.out.println(item);
		
		String sql = "INSERT INTO TANOMO.ITEM VALUES (NULL,?,NULL,?,?,?,?,0);";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, item.getItemName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setString(3, item.getWeekDay());
			pstmt.setInt(4, item.getInventory());
			pstmt.setByte(5, item.getViews());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int maxItemId = maxItemIdRetrieve();
		System.out.println("アイテム追加完了");
		return maxItemId;
	}
	
	
	@Override
	public void update(Item item){
		System.out.println("アイテム更新");
		System.out.println(item);
		
		String sql = "UPDATE TANOMO.ITEM SET ITEMNAME = ?, PRICE = ?, "
				+ "WEEKDAY = ?, INVENTORY = ?, VIEWS = ?, DELETION = 0 "
				+ "WHERE ITEMID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, item.getItemName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setString(3, item.getWeekDay());
			pstmt.setInt(4, item.getInventory());
			pstmt.setByte(5, item.getViews());
			pstmt.setInt(6, item.getItemId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("アイテム更新完了");
	}
	
	@Override
	public void itemImageUpdate(int maxItemId){
		System.out.println("アイテム画像更新");
		System.out.println(maxItemId);
		
		String sql = "UPDATE TANOMO.ITEM SET ITEMIMAGE = ? "
				+ "WHERE ITEMID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "/image/" + maxItemId + ".jpg");
			pstmt.setInt(2, maxItemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("アイテム画像更新完了");
	}
	
	@Override
	public void delete(int itemId){
		System.out.println("アイテム削除");
		System.out.println(itemId);
		
		String sql = "UPDATE TANOMO.ITEM SET VIEWS = 0, DELETION = 1 "
				+ "WHERE ITEMID = ?;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("アイテム削除完了");
	}
	
	
	@Override
	public ArrayList<Item> retrieve() {
		ArrayList<Item> list = new ArrayList<Item>();
		
		System.out.println("アイテム取り出し");
		
		String sql = "SELECT * FROM TANOMO.ITEM "
				+ "WHERE DELETION = 0;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String itemImage = rs.getString("ITEMIMAGE");
				int price = rs.getInt("PRICE");
				String weekDay = rs.getString("WEEKDAY");
				int inventory = rs.getInt("INVENTORY");
				byte views = rs.getByte("VIEWS");
				Item item = new Item(itemId, itemName, itemImage, price, weekDay, inventory, views);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("アイテム取り出し完了");
		return list;
	}
	
	@Override
	public ArrayList<Item> deletionRetrieve() {
		ArrayList<Item> list = new ArrayList<Item>();
		
		System.out.println("削除済みのアイテム取り出し");
		
		String sql = "SELECT * FROM TANOMO.ITEM "
				+ "WHERE DELETION = 1;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String itemImage = rs.getString("ITEMIMAGE");
				int price = rs.getInt("PRICE");
				String weekDay = rs.getString("WEEKDAY");
				int inventory = rs.getInt("INVENTORY");
				byte views = rs.getByte("VIEWS");
				Item item = new Item(itemId, itemName, itemImage, price, weekDay, inventory, views);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("削除済みのアイテム取り出し完了");
		return list;
	}
	
	@Override
	public ArrayList<Item> viwesRetrieve() {
		ArrayList<Item> list = new ArrayList<Item>();
		
		System.out.println("表示状態のアイテム取り出し");
		
		String sql = "SELECT * FROM TANOMO.ITEM "
				+ "WHERE VIEWS = 1 AND DELETION = 0;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				int itemId = rs.getInt("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String itemImage = rs.getString("ITEMIMAGE");
				int price = rs.getInt("PRICE");
				String weekDay = rs.getString("WEEKDAY");
				int inventory = rs.getInt("INVENTORY");
				byte views = rs.getByte("VIEWS");
				Item item = new Item(itemId, itemName, itemImage, price, weekDay, inventory, views);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("表示状態のアイテム取り出し完了");
		return list;
	}
	
	public int maxItemIdRetrieve() {
		int itemId = 0;
		
		System.out.println("アイテム番号の最大値取り出し");
		
		String sql = "SELECT MAX(ITEMID) FROM TANOMO.ITEM;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next() == true) {
				itemId = rs.getInt("MAX(ITEMID)");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(itemId);
		System.out.println("アイテム番号の最大値取り出し完了");
		return itemId;
	}
}
