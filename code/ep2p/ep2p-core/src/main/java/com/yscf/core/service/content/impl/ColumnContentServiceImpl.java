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
 * 2015年9月18日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.ArrayList;
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
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.core.dao.content.ColumnContentFileRelMapper;
import com.yscf.core.dao.content.ColumnContentMapper;
import com.yscf.core.dao.content.ContTagMapper;
import com.yscf.core.dao.pub.PubFileMapper;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.ColumnContentFileRel;
import com.yscf.core.model.content.ContTag;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.content.IColumnContentService;

/**
 * Description：<br>
 * 栏目内容管理
 * 
 * @author shiliang.feng
 * @date 2015年9月18日
 * @version v1.0.0
 */
@Service("columnContentService")
public class ColumnContentServiceImpl extends BaseService<BaseEntity, String>
		implements IColumnContentService {
	@Resource
	private PubFileMapper pubFileMapper;

	@Resource
	private ColumnContentMapper contentMapper;

	@Resource
	private ContTagMapper tagMapper;

	@Resource
	private ColumnContentFileRelMapper fileRelMapper;

	@Autowired
	public ColumnContentServiceImpl(ColumnContentMapper dao) {
		super(dao);
	}

	@Override
	public PageList<ColumnContent> selectColuContentByParameter(
			ColumnContent column, PageInfo info) {
		PageList<ColumnContent> d = contentMapper.selectColuContentByParameter(
				column, info);
		return d;
	}

	@Override
	public void batchUpdateByPids(ColumnContent params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", params.getStatus());
		map.put("pid", params.getPid().split(","));
		contentMapper.batchUpdateByPids(map);
	}

	@Override
	public void updateByPrimaryKey(PubFile pubFileImg, PubFile pubFile,
			ColumnContent content) {

		ColumnContentFileRel fileRel = new ColumnContentFileRel();
		// 保存附件
		if (pubFile != null) {
			pubFile.setPid(pubFile.getPK());
			pubFileMapper.deleteByPrimaryKey(pubFile.getPid());
			pubFileMapper.insert(pubFile);
		}
		// 保存封面
		if (pubFileImg != null) {
			// 保存到文件信息表
			pubFileImg.setPid(pubFileImg.getPK());
			pubFileMapper.deleteByPrimaryKey(pubFileImg.getPid());
			pubFileMapper.insert(pubFileImg);
			content.setImgUrl(pubFileImg.getPid());// 图片路径 对应的是 栏目内容文件关系表id
		}
		// 如果不是新增栏目的内容的话 需要先删除栏目标签表里对应的信息
		if (!StringUtil.isBlank(content.getPid())) {
			tagMapper.deleteByColuContId(content.getPid());
		}
		// 保存主信息
		content.setUrl(fileRel.getPid());
		contentMapper.updateByPrimaryKey(content);
		// 保存到栏目内容和文件关系表
		if (pubFile != null) {
			fileRel.setPid(fileRel.getPK());
			fileRel.setColuContId(content.getPid());
			fileRel.setFileId(pubFile.getPid());
			fileRel.setCreateUser(content.getCreateUser());
			fileRel.setCreateTime(content.getCreateTime());
			// 根据内容的id 删除之前上传的文件信息
			fileRelMapper.deleteByContentId(content.getPid());
			fileRelMapper.insert(fileRel);
		}
		if (!"1".equals(content.getIsLowerLevel())) {
			// 如果选择了标签 则保存标签
			saveColumnTag(content);
		}

	}

	@Override
	public void saveFileInfo(PubFile pubFileImg, PubFile pubFile,
			ColumnContent content) {
		// 保存到栏目内容和文件关系表
		ColumnContentFileRel fileRel = new ColumnContentFileRel();
		// 保存附件
		if (pubFile != null) {
			// 保存到文件信息表
			pubFile.setPid(pubFile.getPK());
			pubFileMapper.deleteByPrimaryKey(pubFile.getPid());
			pubFileMapper.insert(pubFile);
		}
		// 保存封面
		if (pubFileImg != null) {
			// 保存到文件信息表
			pubFileImg.setPid(pubFileImg.getPK());
			pubFileMapper.deleteByPrimaryKey(pubFileImg.getPid());
			pubFileMapper.insert(pubFileImg);
			content.setImgUrl(pubFileImg.getPid());// 图片路径 对应的是 栏目内容文件关系表id
		}
		// 如果不是新增栏目的内容的话 需要先删除栏目标签表里对应的信息
		if (!StringUtil.isBlank(content.getPid())) {
			tagMapper.deleteByColuContId(content.getPid());
		}
		content.setPid(content.getPK());
		// 保存栏目内容
		contentMapper.insertSelective(content);
		// 保存到 栏目内容与文件关系表
		if (pubFile != null) {
			fileRel.setPid(fileRel.getPK());
			fileRel.setColuContId(content.getPid());
			fileRel.setFileId(pubFile.getPid());
			fileRel.setCreateUser(content.getCreateUser());
			fileRel.setCreateTime(content.getCreateTime());
			// 根据内容的id 删除之前上传的文件信息
			fileRelMapper.deleteByContentId(content.getPid());
			fileRelMapper.insert(fileRel);
		}
		if (!"1".equals(content.getIsLowerLevel())) {
			saveColumnTag(content);
		}

	}

	private void saveColumnTag(ColumnContent content) {
		// 如果选择了标签 则保存标签
		if (!StringUtil.isBlank(content.getTagIds())) {
			String[] selectedTagId = content.getTagIds().split(",");
			for (int i = 0; i < selectedTagId.length; i++) {
				ContTag tag = new ContTag();
				tag.setCreateTime(DateUtil.getToday());
				tag.setLastUpdateTime(DateUtil.getToday());
				tag.setCreateUser(content.getCreateUser());
				tag.setLastUpdateUser(content.getLastUpdateUser());
				tag.setColuContId(content.getPid());
				tag.setDictContId(selectedTagId[i]);
				tag.setStatus("1");
				tag.setPid(tag.getPK());
				tagMapper.insert(tag);

			}
		}
	}

	@Override
	public List<ColumnContent> selectColContentByWebTag(String webTag) {
		return contentMapper.selectAdvContentByWebTag(webTag);
	}

	@Override
	public List<ColumnContent> selectColumnContentByParentId(String parentId,
			Integer pageIndex, Integer pageSize) {
		return contentMapper.selectColumnContentByParentId(parentId, pageIndex,
				pageSize);
	}

	@Override
	public List<ColumnContent> selectColContentByWebTagSpecial(String webTag,
			Integer pageIndex, Integer pageSize) {
		List<ColumnContent> contentList = new ArrayList<ColumnContent>();
		ColumnContent content = new ColumnContent();
		content = contentMapper.selectContentMiddleByWebTag(webTag);// 取得二级栏目
		if (content == null) {
			return contentList;
		}
		// 取三级栏目
		contentList = selectColumnContentByParentId(content.getPid(),
				pageIndex, pageSize);
		return contentList;
	}

	@Override
	public Boolean batchUpdateIsReadingByPids(String isReading, String pIds)
			throws RuntimeException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isReading", isReading);
		map.put("pid", pIds.split(","));
		return contentMapper.batchUpdateIsReadingByPids(map);
	}

	@Override
	public List<ColumnContent> selectColContentListByWebTag(String webTag,
			String userId, Integer pageIndex, Integer pageSize, Integer isHome) {
		return contentMapper.selectColContentListByWebTag(webTag, userId,
				pageIndex, pageSize, isHome);
	}

	@Override
	public ColumnContent selectContentByWebTag(String webTag) {
		return contentMapper.selectContentByWebTag(webTag);
	}
}
