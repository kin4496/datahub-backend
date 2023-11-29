package com.knusolution.datahub.application

import com.knusolution.datahub.domain.*
import com.knusolution.datahub.system.domain.DetailCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class PostService(
    private val articleRepository: ArticleRepository,
    private val detailCategoryRepository: DetailCategoryRepository
){
    val uploadDir= "C:\\Users\\in_q\\Documents\\etc\\"
    val pageSize=10

    fun getWaitPage():Int
    {
        val aprove="대기"
        val articles=articleRepository.findByApproval(aprove)
        val allpage = if (articles.size % pageSize == 0) {
            articles.size / pageSize
        } else {
            articles.size / pageSize + 1
        }

        return allpage
    }

    fun getWaitArticles(page: Int): List<ArticleEntity>
    {
        val aprove="대기"
        val articles=articleRepository.findByApproval(aprove)

        val startIndex=(page-1)*pageSize
        if (startIndex >= articles.size) {
            return emptyList()
        }
        val endIndex = startIndex + pageSize
        return articles.subList(startIndex, minOf(endIndex, articles.size))
    }

    fun getArticles(detailCategoryId: Long,page: Int): List<ArticleEntity>{
        val existingDetailCategory = detailCategoryRepository.findById(detailCategoryId)
        val detailCategory = existingDetailCategory.get()
        val articles=articleRepository.findByDetailCategoryId(detailCategory)
        val startIndex=(page-1)*pageSize
        if (startIndex >= articles.size) {
            return emptyList()
        }
        val endIndex = startIndex + pageSize
        return articles.subList(startIndex, minOf(endIndex, articles.size))
    }
    fun getPage(detailCategoryId: Long): Int
    {
        val existingDetailCategory = detailCategoryRepository.findById(detailCategoryId)
        val detailCategory = existingDetailCategory.get()
        val articles=articleRepository.findByDetailCategoryId(detailCategory)
        val allpage = if (articles.size % pageSize == 0) {
            articles.size / pageSize
        } else {
            articles.size / pageSize + 1
        }

        return allpage
    }
    fun saveArticle( detailCategoryId : Long , file : MultipartFile){
        val existingDetailCategory = detailCategoryRepository.findById(detailCategoryId)
        val detailCategory = existingDetailCategory.get()
        val originalFileName = file.originalFilename
        val saveFileName = getSaveFileName(originalFileName)
        val fileUrl = uploadDir+saveFileName
        file.transferTo(File(fileUrl))
        val datetime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val article = ArticleDto( datetime , "대기" ,"",fileUrl,originalFileName ?: detailCategory.detailCategoryName ,"","",detailCategory)
        articleRepository.save(article.asEntity())
    }
    fun postDeclineFile(articleId : Long , approval : String , declineDetail : String? , file : MultipartFile?){
        val existingArticle = articleRepository.findById(articleId)
        val article = existingArticle.get()
        article.approval = approval

        if(approval == "반려" && file != null && declineDetail!= null ) {
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