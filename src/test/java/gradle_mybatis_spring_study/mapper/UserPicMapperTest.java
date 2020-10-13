package gradle_mybatis_spring_study.mapper;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.UserPic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserPicMapperTest {
	protected static final Log log = LogFactory.getLog(UserPicMapperTest.class);
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private UserPicMapper mapper;
	
	@Test
	public void test01InsertUserPic() {
		System.out.println("insert");
		UserPic userPic = new UserPic();
		userPic.setId(1);
		userPic.setName("irene");
		userPic.setBio("put some lengthy bio here");
		userPic.setPic(getPicFile());
		int result = mapper.insertUserPic(userPic);
		Assert.assertEquals(1, result);
	}

	private byte[] getPicFile() {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir") + "\\images\\i.jpg");
		try (InputStream is = new FileInputStream(file);) {
		pic = new byte[is.available()];
		is.read(pic);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return pic;
	}

	@Test
	public void test02GetUserPic() {
		System.out.println("getUserPic");
		UserPic userPic = mapper.getUserPic(1);
		if (userPic.getPic() != null) {
		File file = getPicFile(userPic);
		log.debug("file path " + file.getAbsolutePath());
		}
		Assert.assertNotNull(userPic);

	}

	private File getPicFile(UserPic userPic) {
		File pics = new File(System.getProperty("user.dir") + "\\pics\\");
		if (!pics.exists()) {
		pics.mkdir();
		}
		File file = new File(pics, userPic.getName() + ".jpg");
		try (FileOutputStream output = new FileOutputStream(file)) {
		output.write(userPic.getPic());
		} catch (IOException e) {
		e.printStackTrace();
		}
		return file;

	}

	@Test
	public void test03DeleteUserPic() {
		System.out.println("delete");
		int res = mapper.deleteUserPic(1);
		Assert.assertEquals(1, res);
	}

}
