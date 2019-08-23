package com.leemon.mall.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-23 15:14
 * @desc 商品信息文档对象
 **/
@Data
@Document(indexName = "pms", type = "product", shards = 5, replicas = 1)
public class EsProduct {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    //货号
    @Field(type = FieldType.Keyword)//不会进行分词建立索引的类型
    private String prroductSn;

    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    private Long productCategoryId;

    @Field(type = FieldType.Keyword)
    private String productCategoryName;

    private String pic;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)//分词器名称  进行分词建立索引的类型
    private String name;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)//分词器名称  进行分词建立索引的类型
    private String subTitle;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)//分词器名称  进行分词建立索引的类型
    private String keywords;

    private BigDecimal price;

    private Integer sale;

    private Integer newStatus;

    private Integer recommandStatus;

    private Integer stock;

    private Integer promotionType;

    private Integer sort;

    private List<EsProductAttributeValue> attributeValueList;


}
