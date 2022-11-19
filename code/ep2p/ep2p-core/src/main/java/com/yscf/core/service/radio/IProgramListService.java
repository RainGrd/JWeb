package com.yscf.core.service.radio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.model.radio.SocalAppModel;

public interface IProgramListService {
	/**
	 * Description：查询节目列表，带分页功能
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<BizProgram> selectAllPage(BizProgram bizProgram, PageInfo info);
	/**
	 * Description：批量删除节目列表
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int beatchDeleteProgramList(String pids);
	/**
	 * Description：根据节目列表预览或编辑节目
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	PageList<BizProgram>  previewOrEditProgramById(String pid);
	/**
	 * Description：根据节目列表pid修改节目
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updateProgramListById(BizProgram bizProgram);
	/**
	 * Description：上传节目列表
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int uploadProgram(BizProgram bizProgram);
	/**
	 * Description：根据客户ID更新发布时间
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updatepublishTime(BizProgram bizProgram);
	/**
	 * Description：根据客户ID下架节目列表
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int offlineProgram(BizProgram bizProgram);
	/**
	 * Description：根据客户ID设置预发布时间
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int savePredict(BizProgram bizProgram);
	
	/**
     * Description：<br> 
     * 查询统计的列表信息
     * @author  Lin Xu
     * @date    2015年10月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public PageList<BizProgram> selectStatisticsList(BizProgram bizProgram,PageInfo pageinfo);
    
    /**
     * Description：<br> 
     * 获取结果信息数据
     * @author  Lin Xu
     * @date    2015年10月28日
     * @version v1.0.0
     * @param type
     * @param param
     * @return
     */
    public HashMap<String, Integer[]> selectOneProgrameData(String type,String param,String prgpid);
    
    /**
     * Description：<br> 
     * 统计用户量信息
     * @author  Lin Xu
     * @date    2015年10月29日
     * @version v1.0.0
     * @param days
     * @return
     */
    public HashMap<String, Integer[]> getUserCountData(String days);
    
    /**
     * Description：<br> 
     * 查询所有的转发量信息
     * @author  Lin Xu
     * @date    2015年11月2日
     * @version v1.0.0
     * @param param
     * @return
     */
    public List<SocalAppModel> selectSocalAppInfo(HashMap<String, Object> param);
    
    /**
     * Description：<br> 
     * 统计所有的地区访问量
     * @author  Lin Xu
     * @date    2015年11月2日
     * @version v1.0.0
     * @param param
     * @return
     */
    public List<SocalAppModel> selectAreaStatistics(HashMap<String, Object> param);
    
    /**
     * Description：<br> 
     * 查询用户类型性别
     * @author  heng.wang
     * @date    2015年10月28日
     * @version v1.0.0
     * @return
     */
    public Map<String, Object> selecUserSexTotal();
    
    /**
     * Description：<br> 
     * api:查询电台节目列表
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @return
     */
    public List<BizProgram> selectRadioList(String userId,Integer pageNum,Integer pageSize);
    
    /**
     * Description：<br> 
     * api:查询电台节目列表总记录数
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @return
     */
    public Integer selectRadioListCount();
    
    /**
     * Description：<br> 
     * api:查询电台节目列表路径
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @return
     */
    public List<BizProgram>  selectRadioFilePath(String programId);
    
    

    /**
     * Description：<br> 
     * api:查询电台人气节目列表
     * @author  heng.wang
     * @date    2016年1月25日
     * @version v1.0.0
     * @return
     */
    public List<BizProgram> radioPopularityList();
    
    /**
     * Description：<br> 
     * api:如果app端点了赞，就新加条数据
     * @author  heng.wang
     * @date    2016年1月25日
     * @version v1.0.0
     * @return
     */
    public int insertBizProgramCust(BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * api:根据节目id查节目类型
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @return
     */
    public int selectProgramTypeCountByRadioId(String radioId);
    
    /**
     * Description：<br> 
     * api:查询点赞数
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @return
     */
    public List<BizProgram> selectPraiseNum(String radioId);
    
    /**
     * Description：<br> 
     * api:更新点赞数
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @return
     */
    public int updatePraiseNum(BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * api:查是否点赞
     * @author  heng.wang
     * @date    2016年1月28日
     * @version v1.0.0
     * @return
     */
    public List<BizProgram>  selectIsPraise(String userId);
}
