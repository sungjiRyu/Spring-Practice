package com.sjryu.book_info;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sjryu.book_info.entity.AdminAccountEntity;
import com.sjryu.book_info.repository.AdminAccountRepository;

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

}
