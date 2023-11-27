package com.knusolution.datahub.controll

import com.knusolution.datahub.application.ArticleResponse
import com.knusolution.datahub.application.PostService
import com.knusolution.datahub.domain.ArticleDto
import com.knusolution.datahub.domain.asDto
import com.knusolution.datahub.system.domain.asDto
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class PostController(
    private val postService : PostService
){
    @GetMapping("/wait-article")
    fun waitArticles(): List<ArticleDto>
    {
        return postService.getWaitArticles().map{it.asDto()}
    }
    @GetMapping("/articles")
    fun getArticles(
        @RequestParam detailCategoryId: Long,
        @RequestParam page: Int
    ):ArticleResponse?
    {
        val allpage = postService.getPage(detailCategoryId)
        val articles = postService.getArticles(detailCategoryId,page).map{it.asDto()}

        return ArticleResponse(allPage = allpage, page = page, articles = articles)
    }

    @PostMapping("/article-file")
    fun postArticle(
        @RequestParam detailCategoryId : Long,
        @RequestPart file : MultipartFile
    ){
        postService.saveArticle(detailCategoryId, file)
    }
    @PutMapping("/article-review")
    fun postDeclineFile(
        @RequestParam articleId : Long,
        @RequestParam approval : String,
        @RequestParam(required = false) declineDetail :String,
        @RequestPart(required = false) file : MultipartFile
    ){
        postService.postDeclineFile(articleId, approval, declineDetail, file)
    }

}

