/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月24日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.adv.ContAdvContentMapper;
import com.yscf.core.dao.pub.PubFileMapper;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.content.IContAdvContentService;

/**
 * Description：<br>
 * 广告内容服务实现
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@Service("contAdvContentServiceImpl")
public class ContAdvContentServiceImpl extends BaseService<BaseEntity, String>
		implements IContAdvContentService {

	@Resource
	private PubFileMapper pubFileMapper;

	@Resource
	private ContAdvContentMapper advContentmapper;

	@Autowired
	public ContAdvContentServiceImpl(ContAdvContentMapper dao) {
		super(dao);
	}

	@Override
	public PageList<ColumnContent> selectAdvContentByParameter(
			ContAdvContent column, PageInfo info) {

		PageList<ColumnContent> d = advContentmapper
				.selectAdvContentByParameter(column, info);
		return d;
	}

	@Override
	public void batchUpdateByPids(ContAdvContent params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", params.getStatus());
		map.put("pid", params.getPid().split(","));
		advContentmapper.batchUpdateByPids(map);
	}

	@Override
	public void saveFileInfo(PubFile pubFile, ContAdvContent content) {

		pubFile.setPid(pubFile.getPK());
		pubFileMapper.insert(pubFile);

		content.setFileId(pubFile.getPid());
		advContentmapper.insert(content);
	}

	@Override
	public void updateByPrimaryKey(PubFile pubFile, ContAdvContent content) {
		pubFile.setPid(pubFile.getPK());
		pubFileMapper.insert(pubFile);

		content.setFileId(pubFile.getPid());
		advContentmapper.updateByPrimaryKey(content);
	}

	@Override
	public List<ContAdvContent> selectAdvContentByWebTag(String avdCode) {
		return advContentmapper.selectAdvContentByWebTag(avdCode);
	}
}
