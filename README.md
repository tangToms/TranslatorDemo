# TranslatorDemo
Android的例子：使用的WebService，实现英汉单词查询互译

## 項目簡介
   使用WebService:
   網站：http://www.webxml.com.cn/zh_cn/web_services_item.aspx?id=56647553443555386D2F4D3D;        
   使用服務中英雙向翻譯：
   http://fy.webxml.com.cn/webservices/EnglishChinese.asmx；     
   通過使用WebService，實現一個英漢互譯小程序例子；    

## 項目結構分析
  activity目錄：    
  MainActivity:主程序入口，main_activity.xml:首頁，一個歡迎頁面；     
  SearchPageActivity:查詢單詞，顯示結果Activity；search_page.xml:查詢顯示頁面UI;      
  entity目錄：實體類；    
  SampleSentence:例句類；   
  WordsInfo:單詞查詢信息類；    
  adapter目錄：適配器；   
  SampleSentenceAdapter:例句Adapter;      
  util目錄：工具目錄；        
  WebServiceUtil:訪問WebService工具類；   
  
  

