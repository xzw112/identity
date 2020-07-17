package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.TpMainFileMapper;
import com.tiptimes.identity.entity.TpMainFile;
import com.tiptimes.identity.service.TpMainFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;

/**
 * 文件管理模块 Service层
 */
@Service
@Transactional
public class TpMainFileServiceImpl implements TpMainFileService {

    @Resource
    private TpMainFileMapper tpMainFileMapper;

    @Override
    public TpMainFile getMainFileByFileMD5(String fileMD5) {
        Example example = new Example(TpMainFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fileMd5", fileMD5);
        List<TpMainFile> mainFileList = tpMainFileMapper.selectByExample(example);
        if(mainFileList.size()!=0){
            TpMainFile mainFile = mainFileList.get(0);
            mainFileList=null;
            return mainFile;
        }else{
            mainFileList=null;
            return null;
        }
    }

    @Override
    public TpMainFile getMainFileByFileID(String fileId) {
        return tpMainFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int saveMainFile(TpMainFile tpMainFile) {
        return tpMainFileMapper.insert(tpMainFile);
    }
}
