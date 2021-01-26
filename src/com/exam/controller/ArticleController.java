package com.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exam.dto.Article;
import com.exam.util.Util;

public class ArticleController {
	private Scanner sc;
	private List<Article> articles;
	public ArticleController(Scanner sc, List<Article> articles){
		this.sc = sc;
		this.articles = articles;
	}
	
	public void doWrite() {
		System.out.println("======게시물 작성하기======");
		int id = articles.size() + 1; //write안에 들어있는 게시글 갯수 +1
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 :");
		String title = sc.nextLine();
		System.out.printf("내용 :");
		String body = sc.nextLine();

		Article article = new Article(id, title, body, regDate); // 생성자
		articles.add(article); // 데이터 받은 것을 articles에 저장

		System.out.println(id + "번 글이 생성되었습니다");
	}

	public void showList(String command) {
		System.out.println("======게시물 list======");
		if(articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}
		
		//                     article list.뒷 부분이 있다면 짤라서 searchKeyword로 넣어줌
		String searhKeyword = command.substring("article list".length()).trim(); 
		System.out.printf("검색어 : %s\n", searhKeyword);
		List<Article> forListArticles = articles;
		
		//검색어가 있다면
		if(searhKeyword.length() > 0) {
			forListArticles = new ArrayList<>(); //새 창고만들기 (검색된 애들만 넣는 창고)
			
			for(Article article : articles) {
				//만약 title에 검색어가 포함되어있다면
				if(article.title.contains(searhKeyword)) {
					forListArticles.add(article); //일치한 게시글 for에 담기
				}
			}
			if (articles.size() == 0) {
				System.out.println("검색 결과가 존재하지않습니다.");
				return;

			}
		}
		System.out.println("번호 - 제목 - 조회");
		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);
			System.out.printf("%d - %s - %d\n", article.id, article.title, article.hit);
		}

	}

	public void showDetail(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======게시물 상세보기======");

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다");
			return;
		}
		
		foundArticle.increaseHit();

		System.out.println("번호 :" + foundArticle.id);
		System.out.println("제목 :" + foundArticle.title);
		System.out.println("내용 :" + foundArticle.body);
		System.out.println("조회수 :" + foundArticle.hit);
		System.out.println("날짜 :" + foundArticle.regDate);
		
	}
	
	
	
	

	public void doDelete(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======게시물 지우기======");

		int foundIndex = getArticleIndexById(id); //못찾았다, 없다라는 뜻, index는 방번호
		if (foundIndex == -1) {
			System.out.println(id + "번 게시물은 존재하지 않습니다");
			return;
		}
		articles.remove(foundIndex);
		System.out.println(id + "번 게시물이 삭제되었습니다.");
		
	}

	public void doModify(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======게시물 수정하기======");

		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다");
			return;
		}
		
		System.out.printf("제목 :");
		String title = sc.nextLine();
		System.out.printf("내용 :");
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.body = body;

		System.out.println("번호 :" + foundArticle.id);
		System.out.println("제목 :" + foundArticle.title);
		System.out.println("내용 :" + foundArticle.body);
		System.out.println("날짜 :" + foundArticle.regDate);

		
	}
	
	private int getArticleIndexById(int id) {
		int i = 0;
		for(Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}
		
	private Article getArticleById(int id) {
		//#1
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//
//			if (article.id == id) {
//				return article;
//			}
//		}
		//#2
//		for(Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}	
//		}	
//		return null; // 못찾음
		
		//#3
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
		}
		return null;
	}


}
