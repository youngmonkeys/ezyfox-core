package com.tvd12.ezyfox.sfs2x.testing.extensionconfig;

import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.entities.ApiUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@UserAgent
@Data
@EqualsAndHashCode(callSuper=false)
public class TestUser extends ApiUser {

	public TestUser() {
	}

	public TestUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void incrementMoney(Long addition) {
		this.money += addition;
	}

	@Variable(visible = true)
	private String username;

	@Variable
	private String password;

	@Variable(visible = true)
	private Long money;

}
