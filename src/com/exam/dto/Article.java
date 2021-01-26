package com.exam.dto;

public class Article extends Dto{
	public String title;
	public String body;
    public int hit;
	
    //hit�� 0�� ����
	public Article(int id, String title, String body, String regDate) {
		this(id, title, body, 0, regDate);
	}
	
	//hit�� �ܺο��� ������ ����
	public Article(int id, String title, String body, int hit, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.hit = hit; //�ܺο��� �̸� ������������ 0�� �ƴ� hit
		this.regDate = regDate;
	}
	
	public void increaseHit() {
		hit++;
	}

}
