package com.example.at.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.at.dto.Article;

@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();
}