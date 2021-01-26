package com.exam.dto;

public class Article extends Dto{
	public String title;
	public String body;
    public int hit;
	
    //hit가 0인 버전
	public Article(int id, String title, String body, String regDate) {
		this(id, title, body, 0, regDate);
	}
	
	//hit가 외부에서 들어오는 버전
	public Article(int id, String title, String body, int hit, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.hit = hit; //외부에서 미리 만들어놓기위해 0이 아닌 hit
		this.regDate = regDate;
	}
	
	public void increaseHit() {
		hit++;
	}

}
