package br.com.first.model;

import java.util.Objects;

public class Photo {
	private Long photoId;
	private String photo;
	private String photoExtension;

	@Override
	public int hashCode() {
		return Objects.hash(photo, photoExtension);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		return Objects.equals(photo, other.photo) && Objects.equals(photoExtension, other.photoExtension);
	}

	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", photo=" + photo + ", photoExtension=" + photoExtension + "]";
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

}
