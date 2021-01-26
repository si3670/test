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
		System.out.println("======�Խù� �ۼ��ϱ�======");
		int id = articles.size() + 1; //write�ȿ� ����ִ� �Խñ� ���� +1
		String regDate = Util.getNowDateStr();
		System.out.printf("���� :");
		String title = sc.nextLine();
		System.out.printf("���� :");
		String body = sc.nextLine();

		Article article = new Article(id, title, body, regDate); // ������
		articles.add(article); // ������ ���� ���� articles�� ����

		System.out.println(id + "�� ���� �����Ǿ����ϴ�");
	}

	public void showList(String command) {
		System.out.println("======�Խù� list======");
		if(articles.size() == 0) {
			System.out.println("�Խù��� �����ϴ�.");
			return;
		}
		
		//                     article list.�� �κ��� �ִٸ� ©�� searchKeyword�� �־���
		String searhKeyword = command.substring("article list".length()).trim(); 
		System.out.printf("�˻��� : %s\n", searhKeyword);
		List<Article> forListArticles = articles;
		
		//�˻�� �ִٸ�
		if(searhKeyword.length() > 0) {
			forListArticles = new ArrayList<>(); //�� â����� (�˻��� �ֵ鸸 �ִ� â��)
			
			for(Article article : articles) {
				//���� title�� �˻�� ���ԵǾ��ִٸ�
				if(article.title.contains(searhKeyword)) {
					forListArticles.add(article); //��ġ�� �Խñ� for�� ���
				}
			}
			if (articles.size() == 0) {
				System.out.println("�˻� ����� ���������ʽ��ϴ�.");
				return;

			}
		}
		System.out.println("��ȣ - ���� - ��ȸ");
		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);
			System.out.printf("%d - %s - %d\n", article.id, article.title, article.hit);
		}

	}

	public void showDetail(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======�Խù� �󼼺���======");

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "�� �Խù��� �������� �ʽ��ϴ�");
			return;
		}
		
		foundArticle.increaseHit();

		System.out.println("��ȣ :" + foundArticle.id);
		System.out.println("���� :" + foundArticle.title);
		System.out.println("���� :" + foundArticle.body);
		System.out.println("��ȸ�� :" + foundArticle.hit);
		System.out.println("��¥ :" + foundArticle.regDate);
		
	}
	
	
	
	

	public void doDelete(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======�Խù� �����======");

		int foundIndex = getArticleIndexById(id); //��ã�Ҵ�, ���ٶ�� ��, index�� ���ȣ
		if (foundIndex == -1) {
			System.out.println(id + "�� �Խù��� �������� �ʽ��ϴ�");
			return;
		}
		articles.remove(foundIndex);
		System.out.println(id + "�� �Խù��� �����Ǿ����ϴ�.");
		
	}

	public void doModify(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		System.out.println("======�Խù� �����ϱ�======");

		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "�� �Խù��� �������� �ʽ��ϴ�");
			return;
		}
		
		System.out.printf("���� :");
		String title = sc.nextLine();
		System.out.printf("���� :");
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.body = body;

		System.out.println("��ȣ :" + foundArticle.id);
		System.out.println("���� :" + foundArticle.title);
		System.out.println("���� :" + foundArticle.body);
		System.out.println("��¥ :" + foundArticle.regDate);

		
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
//		return null; // ��ã��
		
		//#3
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
		}
		return null;
	}


}
