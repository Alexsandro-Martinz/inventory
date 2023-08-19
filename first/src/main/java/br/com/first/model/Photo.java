package br.com.first.model;

public class Photo {
	private Long photoId;
	private String photo;
	private String photoExtension;

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
