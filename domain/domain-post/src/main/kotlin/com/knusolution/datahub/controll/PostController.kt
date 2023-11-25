package com.knusolution.datahub.controll

import com.knusolution.datahub.application.PostService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class PostController(
    private val postService : PostService
){
    @GetMapping("/articles")
    fun getArticles() = postService.getArticles()
    @PostMapping("/file/article")
    fun postArticle(
        @RequestParam detailCategoryId : Long,
        @RequestPart file : MultipartFile
    ){
        postService.saveArticle(detailCategoryId, file)
    }
    @PutMapping("/decline/article")
    fun postDeclineFile(
        @RequestParam articleId : Long,
        @RequestParam approval : String,
        @RequestParam(required = false) declineDetail :String,
        @RequestPart(required = false) file : MultipartFile
    ){
        postService.postDeclineFile(articleId, approval, declineDetail, file)
    }

}

