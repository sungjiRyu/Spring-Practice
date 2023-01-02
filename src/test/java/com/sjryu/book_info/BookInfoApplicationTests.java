package com.sjryu.book_info;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sjryu.book_info.entity.AdminAccountEntity;
import com.sjryu.book_info.entity.StudentInfoEntity;
import com.sjryu.book_info.entity.StudentMajorEntity;
import com.sjryu.book_info.repository.AdminAccountRepository;
import com.sjryu.book_info.repository.BookInfoRepository;
import com.sjryu.book_info.repository.StudentInfoRepository;
import com.sjryu.book_info.repository.StudentMajorRepository;
import com.sjryu.book_info.repository.WriterInfoRepository;

@SpringBootTest
class BookInfoApplicationTests {
	@Autowired AdminAccountRepository aaRepo;
	@Test
	// @Transactional // 메서드 사용후 원상태로 데이터를 되돌린다(흔적이 남지 않음)
	void adminAccountAdd() {
		AdminAccountEntity account
		 = AdminAccountEntity
		 	.builder()
			.aiId("admin")
			.aiPwd("1234")
			.aiName("관리자")
			.build();
		aaRepo.save(account);
		System.out.println(account);
	}

	@Test
	void testLogin(){
		String id = "adminuser1";
		String pwd = "1234";
		AdminAccountEntity loginUser = aaRepo.findByAiIdAndAiPwd(id,pwd);
		// assertNotEquals
		assertNotEquals(loginUser, null); // loginUser가 null과 같지 않으면 통과
		
	}
	@Autowired BookInfoRepository bRepo;
	@Test
	void bookFindAll(){
		System.out.println(bRepo.findAll());
	}

	@Autowired WriterInfoRepository wRepo;
	@Test
	void writerFindAll(){
		System.out.println(wRepo.findAll());
	}

	@Autowired StudentMajorRepository majorRepo;
	@Autowired StudentInfoRepository  stuRepo;		
	@Test
	void getStudents(){
		System.out.println(stuRepo.findAll());
	}
	@Test
	void getMajors(){
		System.out.println(majorRepo.findAll());
	}
	@Test
	void addStudent(){
		StudentInfoEntity stu = new StudentInfoEntity();
		stu.setStuName("이학생");
		stu.setMajor(new StudentMajorEntity(null, "컴퓨터공학과"));
		stuRepo.save(stu);
	}
	@Test
	void addStudent2(){
		StudentInfoEntity stu = new StudentInfoEntity();
		stu.setStuName("박학생");
		stu.setMajor(majorRepo.findById(3L).get());
		stuRepo.save(stu);
	}
}
