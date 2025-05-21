package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
/*
 *     Repository : DAO  => 순수하게 데이터베이스 연동 
 *       
 *     Service : DAO에서 데이터를 받아서 => 처리후 Controller로 전송 
 *     Controller : 요청 처리가 완료된 데이터만 받아서 전송
 *      
 */
@Service
public class BusanInfoService {
   @Autowired
   private BusanInfoRepository bDao;
   
   public List<BusanInfoEntity> getBusanInfoData(int cno)
   {
	   List<BusanInfoEntity> list=bDao.findByCno(cno);
	   List<BusanInfoEntity> list2=new ArrayList<BusanInfoEntity>();
	   for(int i=0;i<8;i++)
	   {
		   list2.add(list.get(i));
	   }
	   return list2;
   }
   
   public List<BusanInfoEntity> getBusanInfoAll(int page)
   {
	   int rowSize=12;
	   Pageable pg=PageRequest.of(page-1, rowSize,Sort.by(Sort.Direction.DESC,"id"));
	   Page<BusanInfoEntity> pList=bDao.findAll(pg);
	   
	   List<BusanInfoEntity> list=new ArrayList<BusanInfoEntity>();
	   if(pList!=null && pList.hasContent())
	   {
		   list=pList.getContent();
	   }
	   return list;
   }
   
   public int[] getPageDatas(int page)
   {
	   int[] data=new int[4];
	   int count=(int)bDao.count();
	   int totalpage=(int)(Math.ceil(count/12.0));
	   
	   final int BLOCK=10;
	   int startPage=((page-1)/BLOCK*BLOCK)+1;
	   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	   
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   data[0]=page;
	   data[1]=totalpage;
	   data[2]=startPage;
	   data[3]=endPage;
	   
	   return data;
   }
   
   public List<BusanInfoEntity> busanInfoFindData(String title)
   {
	   List<BusanInfoEntity> list=new ArrayList<BusanInfoEntity>();
	   try
	   {
		   String strUrl="http://localhost:9200/busaninfo/_search?q=title="
				     +URLEncoder.encode(title, "UTF-8");
		   URL url=new URL(strUrl);
		   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		   StringBuffer sb=new StringBuffer();
		   if(conn!=null)
		   {
			   BufferedReader in=
					   new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			   while(true)
			   {
				   String data=in.readLine();
				   if(data==null) break;
				   sb.append(data);
			   }
			   in.close();
			   
			   System.out.println(sb.toString());
		   }
		   
	   }catch(Exception ex) {}
	   
	   return list;
   }
}