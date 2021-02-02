package com.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.dto.Member;
import com.exam.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actinMethodName;
	private Member loginedMember;

	public MemberController(Scanner sc) {
		this.sc = sc;

		members = new ArrayList<Member>();
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actinMethodName = actinMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		}
	}

	private void doLogin() {
		System.out.println("======�α���======");

		System.out.printf("���̵� :");
		String loginId = sc.nextLine();
		System.out.printf("��й�ȣ :");
		String loginPw = sc.nextLine();
		
		Member member = getMemberByLoginId(loginId);
		if(member == null) {
			System.out.println("�������� �ʴ� ȸ���Դϴ�.");
			return;
		}
		if(member.loginPw.equals(loginPw)==false){
			System.out.println("��й�ȣ�� Ȯ�����ּ���.");
			return;
		}
		loginedMember = member;
		System.out.printf("%s ȯ���մϴ�.\n", loginedMember.name);
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return null;
		}
		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		// ������(-1) ���� ����
		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
		}
		i++;
		return -1;
	}

	private void doJoin() {
		System.out.println("======ȸ������======");
		int id = members.size() + 1; // write�ȿ� ����ִ� �Խñ� ���� +1
		String regDate = Util.getNowDateStr();

		String loginId = null;
		while (true) {
			System.out.printf("���̵� :");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(��)�� �̹� ��� ���� ���̵��Դϴ�.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("��й�ȣ :");
			loginPw = sc.nextLine();
			System.out.printf("��й�ȣ Ȯ��:");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("��й�ȣ�� Ȯ�� ���ּ���");
				continue;
			}

			break;
		}

		System.out.printf("�̸� :");
		String name = sc.nextLine();

		Member member = new Member(id, loginId, loginPw, name, regDate); // ������
		members.add(member); // ������ ���� ���� articles�� ����

		System.out.printf("%d�� ȸ���� �����Ǿ����ϴ�.\n", id);

	}

}
