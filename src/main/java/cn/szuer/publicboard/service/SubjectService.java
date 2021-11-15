package cn.szuer.publicboard.service;

import cn.szuer.publicboard.model.SubjectInfo;

import java.util.List;

public interface SubjectService {
    
    List<SubjectInfo> getAll();

    boolean addSubject(SubjectInfo subject);
}
