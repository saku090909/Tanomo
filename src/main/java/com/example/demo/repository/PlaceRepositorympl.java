package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Place;

@Repository
public class PlaceRepositorympl extends DAOTemplate implements PlaceRepository {
	
	@Override
	public ArrayList<Place> retrieve() {
		ArrayList<Place> list = new ArrayList<Place>();
		
		System.out.println("プレイス取り出し");
		
		String sql = "SELECT * FROM TANOMO.PLACE;";
		
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				int placeId = rs.getInt("PLACEID");
				String placeName = rs.getString("PLACENAME");
				Place place = new Place(placeId, placeName);
				list.add(place);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println(list);
		System.out.println("プレイス取り出し完了");
		return list;
	}
}
