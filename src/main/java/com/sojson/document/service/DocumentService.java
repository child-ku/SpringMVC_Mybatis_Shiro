package com.sojson.document.service;

import java.util.List;
import java.util.Map;

import com.sojson.document.model.Document;
import com.sojson.core.mybatis.page.Pagination;

public interface DocumentService {
    
    int deleteByPrimaryKey(Long id);

    Document insert(Document record);

    Document insertSelective(Document record);

    Document selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
    
    Pagination<Document> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    
    List<Document> findByDirectoryId(Long directoryId);
    
    List<Document> findByCreateBy(Long createBy);
    
    List<Document> search(String keyword);
    
    Map<String, Object> uploadDocument(Document record);
    
    Map<String, Object> deleteDocumentById(String ids);
    
    Map<String, Object> updateDocumentStatus(Long id, Long status);
}
