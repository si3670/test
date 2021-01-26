package com.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.controller.ArticleController;
import com.exam.controller.MemberController;
import com.exam.dto.Article;
import com.exam.dto.Member;
import com.exam.util.Util;

public class App {
private ArrayList<Article> articles; // �Խù� �������� ����, ���� �����ϵ��� ���� ��������
private ArrayList<Member> members;
	
	//�����ڸ� ���� ����
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	} 

	public void start(String[] args) {
		System.out.println("==exam ���α׷� ����==");
		
		makeTestData(); //�Խñ� �̸� ��������
		
		Scanner sc = new Scanner(System.in); // Ű���带 ��ĵ�ϴ� �� ź��
		
		MemberController membercontroller = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		while (true) {
			System.out.printf("��ɾ�) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue; // �ؿ��� �����ϰ� �ٽ� while������ ���ư�
			}

			if (command.equals("exit")) {
				break;
			}
			if (command.equals("article write")) {
				articleController.doWrite();
				
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
				
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
				
			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);
				
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			}
			
			//ȸ������
			else if (command.equals("member join")) {
				membercontroller.doJoin();
			}
		}

			sc.close();

			System.out.println("���α׷� ��");

		}

	private void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");
		articles.add(new Article(1, "����1", "����1", 12, Util.getNowDateStr()));
		articles.add(new Article(2, "����2", "����2", 100, Util.getNowDateStr()));
		articles.add(new Article(3, "����3", "����3", 34, Util.getNowDateStr()));
	}

}
