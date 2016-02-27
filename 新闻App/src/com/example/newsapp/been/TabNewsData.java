package com.example.newsapp.been;

import java.util.ArrayList;

public class TabNewsData {
	/**
	 * 返回码
	 */
	public int recode;
	public TabData data;
	
	/**
	 * 
	 * @author AutismPerson 数据集合
	 *
	 */
	public class TabData{
		public String more;
		public String title;
		public ArrayList<TabNews> news;
		public ArrayList<TabTopNews> topnews;
		@Override
		public String toString() {
			return "TabData [more=" + more + ", title=" + title + ", news=" + news + ", topnews=" + topnews + "]";
		}
		
	}
	
	/**
	 * 
	 * @author AutismPerson 新闻集合
	 *
	 */
	class TabNews{
		public String id;
		public String listimage;	//图片信息
		public String pubdate;		//更新时间
		public String title;		//标题
		public String type;			//类型		
		public String url;			//内容超链接
		@Override
		public String toString() {
			return "TabNews [id=" + id + ", listimage=" + listimage + ", pubdate=" + pubdate + ", title=" + title
					+ ", type=" + type + ", url=" + url + "]";
		}
		
	}
	
	/**
	 * 
	 * @author AutismPerson  热门
	 *
	 */
	public class TabTopNews{
		public String id; 
		public String topimage;		//图片信息
		public String pubdate;		//更新时间
		public String title;		//标题
		public String type;			//类型		
		public String url;			//内容超链接
		@Override
		public String toString() {
			return "TabTopNews [id=" + id + ", topimage=" + topimage + ", pubdate=" + pubdate + ", title=" + title
					+ ", type=" + type + ", url=" + url + "]";
		}
		
	}

	@Override
	public String toString() {
		return "TabNewsData [recode=" + recode + ", data=" + data + "]";
	}
	
	
}
