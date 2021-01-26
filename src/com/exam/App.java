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
private ArrayList<Article> articles; // 게시물 차곡차곡 저장, 접근 가능하도록 위로 꺼내놓기
private ArrayList<Member> members;
	
	//생성자를 통해 접근
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	} 

	public void start(String[] args) {
		System.out.println("==exam 프로그램 시작==");
		
		makeTestData(); //게시글 미리 만들어놓기
		
		Scanner sc = new Scanner(System.in); // 키보드를 스캔하는 것 탄생
		
		MemberController membercontroller = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue; // 밑에꺼 무시하고 다시 while문으로 돌아감
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
			
			//회원가입
			else if (command.equals("member join")) {
				membercontroller.doJoin();
			}
		}

			sc.close();

			System.out.println("프로그램 끝");

		}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, "제목1", "내용1", 12, Util.getNowDateStr()));
		articles.add(new Article(2, "제목2", "내용2", 100, Util.getNowDateStr()));
		articles.add(new Article(3, "제목3", "내용3", 34, Util.getNowDateStr()));
	}

}
