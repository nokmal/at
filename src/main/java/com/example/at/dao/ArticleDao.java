package com.example.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.at.dto.Article;
import com.example.at.dto.ArticleReply;

@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();

	Article getForPrintArticleById(@Param("id") int id);

	void write(Map<String, Object> param);

	void writeReply(Map<String, Object> param);

	List<ArticleReply> getForPrintArticleReplies(Map<String, Object> param);

	void deleteReply(@Param("id") int id);
}