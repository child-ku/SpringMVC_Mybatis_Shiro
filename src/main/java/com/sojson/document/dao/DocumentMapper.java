package com.sojson.document.dao;

import java.util.List;
import java.util.Map;

import com.sojson.document.model.Document;

public interface DocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
    
    List<Document> findByDirectoryId(Long directoryId);
    
    List<Document> findByCreateBy(Long createBy);
    
    List<Document> search(String keyword);
}
