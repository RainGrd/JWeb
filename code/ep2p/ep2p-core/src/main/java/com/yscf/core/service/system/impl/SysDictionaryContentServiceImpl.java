/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  数据字典内容业务交互实现类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysDictionaryContentMapper;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.system.ISysDictionaryContentService;

/**
 * Description：<br>
 * 数据字典内容业务交互实现类
 * 
 * @author Yu.Zhang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Service("sysDictionaryContentService")
public class SysDictionaryContentServiceImpl extends BaseService<BaseEntity, String> implements ISysDictionaryContentService {

	private Logger logger = LoggerFactory.getLogger(SysDictionaryContentServiceImpl.class);
	
	@Autowired
	public SysDictionaryContentServiceImpl(SysDictionaryContentMapper dao) {
		super(dao);
	}

	@Override
	public int updateStatusBatch(SysDictionaryContent sysDictionaryContent) {
		int result = 0;
		if (null != sysDictionaryContent && null != sysDictionaryContent.getPid()) {
			SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", sysDictionaryContent.getStatus());
			map.put("idItem", sysDictionaryContent.getPid().split(","));
			result = mapper.updateStatusBatch(map);
		}
		return result;
	}

	public List<SysDictionaryContent> selectAll(SysDictionaryContent sysDictionaryContent) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
		return mapper.selectAll(sysDictionaryContent);
	}

	public PageList<SysDictionaryContent> selectAllPage(SysDictionaryContent sysDictionaryContent, PageInfo info) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();

		return mapper.selectAllPage(sysDictionaryContent, info);
	}

	@Override
	public boolean validateCode(SysDictionaryContent sysDictionaryConten) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
		List<SysDictionaryContent> list = mapper.validateCode(sysDictionaryConten);
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int updateByPrimaryKeySelective(SysDictionaryContent sysDictionaryContent) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
		return mapper.updateByPrimaryKeySelective(sysDictionaryContent);
	}

	@Override
	public List<SysDictionaryContent> selectByDisctCode(String dictCode) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
		return mapper.selectByDisctCode(dictCode);
	}

	/**
	 * 
	 * @Description : 根据数据字典code和数据字典内容code查询数据字典内容名称
	 * @param dictCode
	 *            数据字典code
	 * @param dictContCode
	 *            数据字典内容code
	 * @return 数据字典类型名称
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午3:31:02
	 */
	@Override
	public String selectDictionaryContentName(String dictCode, String dictContCode) {
		String dictContName = null;
		try {
			SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
			dictContName = mapper.selectDictionaryContentName(dictCode, dictContCode);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("根据数据字典code和数据字典内容code查询数据字典内容名称：", e.getMessage());
			}
		}
		return dictContName;
	}

	@Override
	public Map<String, String> selectByDisctCodeMap(String dictCode) {
		SysDictionaryContentMapper mapper = (SysDictionaryContentMapper) getDao();
		List<SysDictionaryContent> list = mapper.selectByDisctCode(dictCode);
		Map<String,String> resultMap = new HashMap<String,String>();
		if(null!=list && list.size()>0){
			for(SysDictionaryContent sys: list){
				resultMap.put(sys.getDictContCode(), sys.getDictContName());
			}
		}
		return resultMap;
	}

}
