package com.knusolution.datahub.application

import com.knusolution.datahub.domain.ArticleDto
import com.knusolution.datahub.domain.asEntity
import com.knusolution.datahub.domain.ArticleRepository
import com.knusolution.datahub.system.domain.DetailCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import java.util.*

@Service
class PostService(
    private val articleRepository: ArticleRepository,
    private val detailCategoryRepository: DetailCategoryRepository
){
    val uploadDir= "C:\\Users\\in_q\\Documents\\etc\\"
    fun getArticles() = articleRepository.findAll()

    fun saveArticle( detailCategoryId : Long , file : MultipartFile){
        val existingDetailCategory = detailCategoryRepository.findById(detailCategoryId)
        val detailCategory = existingDetailCategory.get()
        val originalFileName = file.originalFilename
        val saveFileName = getSaveFileName(originalFileName)
        val fileUrl = uploadDir+saveFileName
        file.transferTo(File(fileUrl))
        val datetime=LocalDateTime.now()
        val article = ArticleDto( datetime , "대기" ,"",fileUrl,originalFileName ?: detailCategory.detailCategoryName ,"","", detailCategory )
        articleRepository.save(article.asEntity())
    }
    fun postDeclineFile(articleId : Long , approval : String , declineDetail : String , file : MultipartFile){
        val existingArticle = articleRepository.findById(articleId)
        val article = existingArticle.get()
        article.approval = approval

        if(approval == "반려" ) {
            val originalFileName = file.originalFilename
            val saveFileName = getSaveFileName(originalFileName)

            article.declineDetail = declineDetail
            article.declineFileName = originalFileName ?: "declineFile"
            article.declineFileUrl = uploadDir + saveFileName

        }
        articleRepository.save(article)
    }
    private fun getSaveFileName(originalFilename: String?): String {
        val extPosIndex: Int? = originalFilename?.lastIndexOf(".")
        val ext = originalFilename?.substring(extPosIndex?.plus(1) as Int)

        return UUID.randomUUID().toString() + "." + ext
    }


}