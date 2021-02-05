package com.example.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.at.dao.ArticleDao;
import com.example.at.dto.Article;
import com.example.at.dto.ArticleReply;
import com.example.at.dto.Member;
import com.example.at.dto.ResultData;
import com.example.at.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public List<Article> getForPrintArticles() {
		List<Article> articles = articleDao.getForPrintArticles();

		return articles;
	}

	public Article getForPrintArticleById(int id) {
		Article article = articleDao.getForPrintArticleById(id);

		return article;
	}

	public int write(Map<String, Object> param) {
		articleDao.write(param);

		return Util.getAsInt(param.get("id"));
	}

	public int writeReply(Map<String, Object> param) {
		articleDao.writeReply(param);

		return Util.getAsInt(param.get("id"));
	}

	public List<ArticleReply> getForPrintArticleReplies(@RequestParam Map<String, Object> param) {
		List<ArticleReply> articleReplies = articleDao.getForPrintArticleReplies(param);

		Member actor = (Member) param.get("actor");

		for (ArticleReply articleReply : articleReplies) {
			// 출력용 부가데이터를 추가
			updateForPrintInfo(actor, articleReply);
		}

		return articleReplies;
	}

	private void updateForPrintInfo(Member actor, ArticleReply articleReply) {
		articleReply.getExtra().put("actorCanDelete", actorCanDelete(actor, articleReply));
		articleReply.getExtra().put("actorCanModify", actorCanModify(actor, articleReply));
	}

	// 액터가 해당 댓글을 수정할 수 있는지 확인
	public boolean actorCanModify(Member actor, ArticleReply articleReply) {
		return actor != null && actor.getId() == articleReply.getMemberId() ? true : false;
	}

	// 액터가 해당 댓글을 삭제할 수 있는지 확인
	public boolean actorCanDelete(Member actor, ArticleReply articleReply) {
		return actorCanModify(actor, articleReply);
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}

	public ArticleReply getForPrintArticleReplyById(int id) {
		return articleDao.getForPrintArticleReplyById(id);
	}

	public ResultData modfiyReply(Map<String, Object> param) {
		articleDao.modifyReply(param);
		return new ResultData("S-1", String.format("%d번 댓글을 수정하였습니다.", Util.getAsInt(param.get("id"))), param);
	}
}