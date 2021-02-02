package com.exam;

import java.util.ArrayList;
import java.util.Scanner;

import com.exam.controller.ArticleController;
import com.exam.controller.Controller;
import com.exam.controller.MemberController;
import com.exam.dto.Article;
import com.exam.dto.Member;
import com.exam.util.Util;

public class App {
	public void start(String[] args) {
		System.out.println("==exam 프로그램 시작==");

		Scanner sc = new Scanner(System.in); // 키보드를 스캔하는 것 탄생

		MemberController membercontroller = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData(); //게시물 미리 만들어 놓기
		
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

			String[] commandBits = command.split(" "); // article detail
			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}
			String controllerName = commandBits[0]; // article
			String actionMethodName = commandBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = membercontroller;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			controller.doAction(command, actionMethodName);

		}

		sc.close();

		System.out.println("프로그램 끝");

	}

}
