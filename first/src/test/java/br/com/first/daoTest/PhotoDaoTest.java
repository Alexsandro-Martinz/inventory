package br.com.first.daoTest;

import org.junit.jupiter.api.Test;

import br.com.first.dao.PhotoDao;
import br.com.first.model.Photo;
import junit.framework.TestCase;

class PhotoDaoTest extends TestCase {

	@Test
	void insertPhoto() {
		
		PhotoDao dao = new PhotoDao();
		Photo p1 = new Photo();
		p1.setPhoto("flkdsjflawsdfkajskdfjlas");
		p1.setPhotoExtension(".png");

		p1 = dao.insert(p1);// insert photo
		assertNotNull(p1.getPhotoId());
		
		assertEquals(p1, dao.get(p1.getPhotoId())); // get photo
		
		String newExtension = ".jpg";
		
		p1.setPhotoExtension(newExtension);
		dao.update(p1);
		
		assertEquals(p1, dao.get(p1.getPhotoId()));
		
		dao.delete(p1.getPhotoId());
		assertNull(dao.get(p1.getPhotoId()));
		
	}

}	
