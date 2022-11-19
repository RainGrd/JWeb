package com.yscf.core.service.radio.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.StringUtil;
import com.yscf.core.dao.radio.BizProgramMapper;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.model.radio.SocalAppModel;
import com.yscf.core.service.radio.IProgramListService;
import com.yscf.core.util.HReportUtil;
/**
 * Description：<br> 
 * 电台节目列表服务实现
 * @author  heng.wang
 * @date    2015年10月19日
 * @version v1.0.0
 */
@Service("programListServiceImpl")
public class ProgramListServiceImpl extends BaseService<BaseEntity, String>
implements IProgramListService {
	
	Logger logger = Logger.getLogger(ProgramListServiceImpl.class);
	
	@Autowired
	public ProgramListServiceImpl(BizProgramMapper dao) {
		super(dao);
	}
	@Override
	public PageList<BizProgram> selectAllPage(BizProgram bizProgram, PageInfo info) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
 		return mapper.selectAllPage(bizProgram, info);
	}
	
	@Override
	public int beatchDeleteProgramList(String pids) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		String [] arr = pids.split(",");
		List<String> list = Arrays.asList(arr);
		return mapper.beatchDeleteProgramList(list);
		
		
	}
	@Override
	public PageList<BizProgram> previewOrEditProgramById(String pid) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.previewOrEditProgramById(pid);
	}
	@Override
	public int updateProgramListById(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.updateByPrimaryKeySelective(bizProgram);
	}
	@Override
	public int uploadProgram(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.uploadProgram(bizProgram);
	}
	@Override
	public int updatepublishTime(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.updatepublishTime(bizProgram);
	}
	@Override
	public int offlineProgram(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.offlineProgram(bizProgram);
	}
	@Override
	public int savePredict(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.savePredict(bizProgram);
	}
	
	@Override
	public PageList<BizProgram> selectStatisticsList(BizProgram bizProgram,PageInfo pageinfo) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		PageList<BizProgram> relist = mapper.selectStatisticsList(bizProgram, pageinfo);
		//统计总数
		PageList<BizProgram> bplist = mapper.selectStatisticsList(bizProgram);
		//统计总数的信息
		BizProgram bptatol = new BizProgram();
			bptatol.setProgramTitle("总计");
		Integer tatollistNum = 0;
		Integer tatolprasNum = 0;
		Integer tatolforwNum = 0;
		for(BizProgram bpo : bplist){
			tatollistNum = tatollistNum + (bpo.getListenNum() == null ? 0 : bpo.getListenNum());
			tatolprasNum = tatolprasNum + (bpo.getPraiseNum() == null ? 0 : bpo.getPraiseNum());
			tatolforwNum = tatolforwNum + (bpo.getForwardNum() == null ? 0 : bpo.getForwardNum());
		}
			bptatol.setListenNum(tatollistNum);
			bptatol.setPraiseNum(tatolprasNum);
			bptatol.setForwardNum(tatolforwNum);
		//添加总数信息
		relist.add(bptatol);
		return relist;
	}
	@Override
	public Map<String, Object> selecUserSexTotal() {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		//查节目板块类型
		PageList<BizProgram> list =  mapper.selectProgramPlateType();
	    Map<String, Object> map = new HashMap<String, Object>();
	    List<Integer> nan = new ArrayList<Integer>();
	    List<Integer> nv = new ArrayList<Integer>();
	    List<String> title = new ArrayList<String>();
	    List<Integer> result = null;
		for(int i=0;i<list.size();i++){
			title.add(list.get(i).getProgramPlateId());
			result = mapper.selecUserSexTotal(list.get(i));
			nan.add(result.get(0));
			nv.add(result.get(1));
		}
		map.put("title", title);
		map.put("nan", nan);
		map.put("nv", nv);
		return map;
	}
	
	@Override
	public HashMap<String, Integer[]> selectOneProgrameData(String type,
			String param,String prgpid) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		HashMap<String, Integer[]> reobject = new HashMap<String, Integer[]>();
		if(StringUtil.isNotEmpty(type)){
		try{
			HashMap<String, String> querymap = new HashMap<String, String>();
			querymap.put("type", type);
			querymap.put("ppid", prgpid);
			if("1".equals(type)){
				String[] yearm = HReportUtil.comMonth();
				Integer[] praisenum = new Integer[yearm.length];
				Integer[] playnum = new Integer[yearm.length];
				Integer[] listennum = new Integer[yearm.length];
				Integer[] forwardnum = new Integer[yearm.length];
				//封装数据信息
				for(int i = 0 ; i < yearm.length ; i++){
					querymap.put("param", param + "-" + yearm[i]);
					HashMap<String, Object> result = mapper.selectOneProgrameData(querymap);
					if(null != result){
						praisenum[i] = Integer.parseInt(String.valueOf(result.get("praisenum")));
						playnum[i] = Integer.parseInt(String.valueOf(result.get("listennum")));
						listennum[i] = Integer.parseInt(String.valueOf(result.get("listennum")));
						forwardnum[i] = Integer.parseInt(String.valueOf(result.get("forwardnum")));
					}else{
						praisenum[i] = 0;
						playnum[i] = 0;
						listennum[i] = 0;
						forwardnum[i] = 0;
					}
				}
				//set数据信息
				reobject.put("playnum", playnum);
				reobject.put("listennum", listennum);
				reobject.put("praisenum", praisenum);
				reobject.put("forwardnum", forwardnum);
			}
			
			//按月统计
			if("2".equals(type)){
				String[] yearmd = HReportUtil.comDay(param);
				Integer[] praisenum = new Integer[yearmd.length];
				Integer[] playnum = new Integer[yearmd.length];
				Integer[] listennum = new Integer[yearmd.length];
				Integer[] forwardnum = new Integer[yearmd.length];
				//封装数据信息
				for(int i = 0 ; i < yearmd.length ; i++){
					querymap.put("param", param + "-" + yearmd[i]);
					HashMap<String, Object> result = mapper.selectOneProgrameData(querymap);
					if(null != result){
						praisenum[i] = Integer.parseInt(String.valueOf(result.get("praisenum")));
						playnum[i] = Integer.parseInt(String.valueOf(result.get("listennum")));
						listennum[i] = Integer.parseInt(String.valueOf(result.get("listennum")));
						forwardnum[i] = Integer.parseInt(String.valueOf(result.get("forwardnum")));
					}else{
						praisenum[i] = 0;
						playnum[i] = 0;
						listennum[i] = 0;
						forwardnum[i] = 0;
					}
				}
				//set数据信息
				reobject.put("playnum", playnum);
				reobject.put("listennum", listennum);
				reobject.put("praisenum", praisenum);
				reobject.put("forwardnum", forwardnum);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		}
		return reobject;
	}
	@Override
	public HashMap<String, Integer[]> getUserCountData(String days) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		HashMap<String, Integer[]> reobject = new HashMap<String, Integer[]>();
		try{
			if(StringUtil.isNotEmpty(days)){
				Integer[] redata = new Integer[24];
				String[] hh = HReportUtil.comYMDH(false, "");
				//获取结果集
				List<HashMap<String, Object>> datalist = mapper.selectUserCountData(days);
				for(int i = 0 ; i < redata.length ; i++){
					if(null != datalist && datalist.size() > 0){
						for(HashMap<String, Object> hdata : datalist){
							String formday = (String) hdata.get("formday");
							Integer tatolip = Integer.parseInt(String.valueOf(hdata.get("tatolip")));
							if(StringUtil.isNotEmpty(formday) && hh[i].equals(formday) && null != tatolip){
								redata[i] = tatolip;
								break;
							}else{
								redata[i] = 0;
							}
						}
					}else{
						redata[i] = 0;
					}
				}
				reobject.put("USERCOUNT", redata);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return reobject;
	}
	
	@Override
	public List<SocalAppModel> selectSocalAppInfo(HashMap<String, Object> param) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectSocalAppInfo(param);
	}
	
	@Override
	public List<SocalAppModel> selectAreaStatistics(
			HashMap<String, Object> param) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectAreaStatistics(param);
	}
	@Override
	public List<BizProgram> selectRadioList(String userId,Integer pageNum, Integer pageSize) {
		Integer start = 0;
		Integer end = 10;
		if(pageSize!=null){
			end = pageSize;
		}
		if(pageNum!=null){
			start = pageNum * end;
		}
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectRadioList(userId,start,end);
	}
	@Override
	public Integer selectRadioListCount() {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectRadioListCount();
	}
	@Override
	public List<BizProgram> selectRadioFilePath(String programId) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectRadioFilePath(programId);
	}
	@Override
	public List<BizProgram> radioPopularityList() {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.radioPopularityList();
	}
	@Override
	public int insertBizProgramCust(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.insertBizProgramCust(bizProgram);
	}
	@Override
	public int selectProgramTypeCountByRadioId(String radioId) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectProgramTypeCountByRadioId(radioId);
	}
	@Override
	public List<BizProgram> selectPraiseNum(String radioId) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectPraiseNum(radioId);
	}
	@Override
	public int updatePraiseNum(BizProgram bizProgram) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.updatePraiseNum(bizProgram);
	}
	@Override
	public List<BizProgram>  selectIsPraise(String userId) {
		BizProgramMapper mapper = (BizProgramMapper) getDao();
		return mapper.selectIsPraise(userId);
	}
	
}
