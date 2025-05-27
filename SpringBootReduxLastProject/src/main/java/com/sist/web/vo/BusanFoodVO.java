package com.sist.web.vo;

// SELECT fno, poster, name ...
public interface BusanFoodVO {
	public String getFno();
	public String getPoster();
	public String getName();
	public int getHit();
	public int getJjimcount();
	public int getLikecount();
	public double getScore();
	public String getType();
	public String getTheme();
	public String getContent();
}
