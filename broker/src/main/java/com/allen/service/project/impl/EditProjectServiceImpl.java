package com.allen.service.project.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.log.LogDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.log.Log;
import com.allen.entity.project.Project;
import com.allen.service.project.EditProjectService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class EditProjectServiceImpl implements EditProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    @Transactional
    public void edit(Project project, long loginId, String newFileName, String oldFileName, String domain, HttpServletRequest request) throws Exception {
        Project project2 = projectDao.findByName(project.getName());
        if(null != project2 && project2.getId() != project.getId()){
            throw new BusinessException("名称已存在！");
        }
        if(null == project2){
            project2 = projectDao.findOne(project.getId());
        }

        String content = "";
        String name = project2.getName();
        if(!project2.getName().equals(project.getName())){
            content += "项目名称由<span style='color:red'>"+name+"</span>改为<span style='color:red'>"+project.getName()+"</span>  ";
        }
        if(project2.getAuditType() != project.getAuditType()){
            content += "审批模式由<span style='color:red'>"+(Project.AUDITTYPE_AUTO == project2.getAuditType() ? "自动" : "手动")+"</span>改为<span style='color:red'>"+(Project.AUDITTYPE_AUTO == project.getAuditType() ? "自动" : "手动")+"</span>  ";
        }
        if(project2.getFrequency() != project.getFrequency()){
            content += "客户访问频次由<span style='color:red'>"+project2.getFrequency()+"</span>改为<span style='color:red'>"+project.getFrequency()+"</span>  ";
        }
        if(project2.getRatio() != project.getRatio()){
            content += "比例由<span style='color:red'>"+project2.getRatio()+"%</span>改为<span style='color:red'>"+project.getRatio()+"%</span>  ";
        }
        if(!StringUtil.isEmpty(project2.getContent()) && !StringUtil.isEmpty(project.getContent())){
            if(!project2.getContent().equals(project.getContent())){
                content += "申请经纪人说明由<span style='color:red'>"+project2.getContent()+"</span>改为<span style='color:red'>"+project.getContent()+"</span>  ";
            }
        }
        if(StringUtil.isEmpty(project2.getContent()) && !StringUtil.isEmpty(project.getContent())){
            content += "申请经纪人说明由<span style='color:red'>空</span>改为<span style='color:red'>"+project.getContent()+"</span>  ";
        }
        if(!StringUtil.isEmpty(project2.getContent()) && StringUtil.isEmpty(project.getContent())){
            content += "申请经纪人说明由<span style='color:red'>"+project2.getContent()+"</span>改为<span style='color:red'>空</span>  ";
        }
        if(!StringUtil.isEmpty(project2.getProtocol()) && !StringUtil.isEmpty(project.getProtocol())){
            if(!project2.getProtocol().equals(project.getProtocol())){
                content += "合作协议说明由<span style='color:red'>"+project2.getProtocol()+"</span>改为<span style='color:red'>"+project.getProtocol()+"</span>  ";
            }
        }
        if(StringUtil.isEmpty(project2.getProtocol()) && !StringUtil.isEmpty(project.getProtocol())){
            content += "合作协议说明由<span style='color:red'>空</span>改为<span style='color:red'>"+project.getProtocol()+"</span>  ";
        }
        if(!StringUtil.isEmpty(project2.getProtocol()) && StringUtil.isEmpty(project.getProtocol())){
            content += "合作协议说明由<span style='color:red'>"+project2.getProtocol() +"</span>改为<span style='color:red'>空</span>  ";
        }


        if(!StringUtil.isEmpty(content)) {
            project2.setName(project.getName());
            project2.setAuditType(project.getAuditType());
            project2.setFrequency(project.getFrequency());
            project2.setRatio(project.getRatio());
            project2.setContent(project.getContent());
            project2.setProtocol(project.getProtocol());
            project2.setOperator(project.getOperator());
            project2.setOperateTime(DateUtil.getLongNowTime());
            projectDao.save(project2);

            Log log = new Log();
            log.setOperatorId(loginId);
            log.setOperatorName(project.getOperator());
            log.setType(Log.TYPE_EDIT);
            log.setContent(name+"项目的"+content);
            logDao.save(log);
        }

        //更换了图片，删除掉以前的图片
        if(!oldFileName.equals(newFileName)){
            //删掉老照片
            if(!StringUtil.isEmpty(oldFileName)) {
                UpLoadFileUtil.delFile(request, configProp.getUpload().get("projectPic") + oldFileName.substring(oldFileName.lastIndexOf("/")+1, oldFileName.length()));
            }
            if(!StringUtil.isEmpty(newFileName)){
                //保存新图片
                UpLoadFileUtil.custFile(request, configProp.getUpload().get("tempPath") + newFileName, configProp.getUpload().get("projectPic"), project2.getId() + "_" + newFileName);
            }
            //修改新路径
            project2.setPic(domain + configProp.getUpload().get("projectPic") + project2.getId() + "_" + newFileName);
            projectDao.save(project2);
        }
    }
}
