package com.dongl.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * indexName ：Elasticsearch 的索引名。
 * type ：Elasticsearch 的类型。
 * @Description: 文章关联es 实例
 * @author: YaoGuangXun
 * @date: 2020/3/17 16:43
 * @Version: 1.0
 */
@Data
@Document(indexName = "blog_articlr", type = "articlr")
public class ArticleES implements Serializable {

    @Id
    private String id;//ID

    /**
     * 是否索引：表示该字段是否可以被搜索到。
     * 是否分词：表示搜索时是整体搜索还是单词匹配
     * 是否存储：表示是否在页面上显示，该实体类中的熟悉即为要存储的字段。
     * analyzer ：存储时指定分词器
     * searchAnalyzer ：查询时候使用的分词器
     **/
    // 在需要分词的属性上面加 下列注解
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     *  如果要搜索出的结果尽可能全，可以使用ik_max_word，
     *  如果需要结果尽可能精确，可以使用ik_smart。
     **/

    @Field(index = true ,analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;//文章正文

    private String state;//审核状态
}
