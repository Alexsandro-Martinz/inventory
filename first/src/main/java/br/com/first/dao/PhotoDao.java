package br.com.first.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.first.connection.SingleConnection;
import br.com.first.model.Photo;
import br.com.first.model.User;

public class PhotoDao {

	private Connection connection;

	public PhotoDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void delete(Long id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM photos WHERE photo_id="+id) ) {
			stm.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	public void update(Photo photo) {
		String sql = "UPDATE public.photos SET photo=?, photo_extension=? WHERE photo_id=?";
		
		try {
			
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, photo.getPhoto());
			stm.setString(2, photo.getPhotoExtension());
			stm.setLong(3, photo.getPhotoId());
			stm.execute();
			connection.commit();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

	public Photo get(Long photoId) {
		Photo photo = null;

		String sql = "SELECT * FROM public.photos WHERE photo_id=" + photoId;
		try {

			ResultSet result = connection.prepareStatement(sql).executeQuery();
			if (result.next()) {
				photo = new Photo();
				photo.setPhotoId(result.getLong(1));
				photo.setPhoto(result.getString(2));
				photo.setPhotoExtension(result.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return photo;
	}

	public Photo insert(Photo photo) {
		String sql = "INSERT INTO public.photos(photo, photo_extension) ";
		sql += "VALUES (?,?) returning photo_id";

		PreparedStatement stm = null;

		try {

			stm = connection.prepareStatement(sql);
			stm.setString(1, photo.getPhoto());
			stm.setString(2, photo.getPhotoExtension());

			ResultSet result = stm.executeQuery();
			connection.commit();
			if (result.next()) {
				photo.setPhotoId(result.getLong(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return photo;
	}

}
