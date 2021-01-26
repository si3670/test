package com.exam.controller;

import java.util.List;
import java.util.Scanner;

import com.exam.dto.Member;
import com.exam.util.Util;

public class MemberController {
	private Scanner sc;
	private List<Member> members;
	public MemberController(Scanner sc, List<Member> members){
		this.sc = sc;
		this.members = members;
	}
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		//없으면(-1) 가입 가능
		if(index == -1) {
			return true;
		}
		return false;
	}
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return i;
			}
		}
		i++;
		return -1;
	}

	public void doJoin() {
		System.out.println("======회원가입======");
		int id = members.size() + 1; //write안에 들어있는 게시글 갯수 +1
		String regDate = Util.getNowDateStr();
		
		String loginId = null;
		while(true) {
			System.out.printf("아이디 :");
			loginId = sc.nextLine();
			
			if(isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용 중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}
		
		
		String loginPw = null;
		String loginPwConfirm = null;
		
		while(true) {
			System.out.printf("비밀번호 :");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인:");
			loginPwConfirm = sc.nextLine();
			
			if(loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 확인 해주세요");
				continue;
			}
			
			break;
		}
		
		System.out.printf("이름 :");
		String name = sc.nextLine();
		
		Member member = new Member(id, loginId, loginPw, name, regDate); // 생성자
		members.add(member); // 데이터 받은 것을 articles에 저장

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
		
	}

}
