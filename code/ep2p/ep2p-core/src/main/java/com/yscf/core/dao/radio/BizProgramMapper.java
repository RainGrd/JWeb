package com.yscf.core.dao.radio;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.model.radio.SocalAppModel;

public interface BizProgramMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizProgram record);

    int insertSelective(BizProgram record);

    BizProgram selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(@Param("bizProgram") BizProgram bizProgram);

    int updateByPrimaryKey(BizProgram record);
    
    /**
	 * Description：查询节目列表,带分页功能的
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
    public PageList<BizProgram> selectAllPage(@Param("bizProgram") BizProgram bizProgram, PageInfo info);
    /**
	 * Description：批量删除节目列表
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
    @SuppressWarnings("rawtypes")
	public int beatchDeleteProgramList(List list);
    /**
	 * Description：根据pid预览或查看节目列表
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param userName 系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
    public PageList<BizProgram> previewOrEditProgramById(String pid);
    /**
   	 * Description：上传节目列表
   	 * @author  heng.wang
   	 * @date    2015年10月20日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int uploadProgram(BizProgram bizProgram);
    /**
   	 * Description：根据pid更新发布时间
   	 * @author  heng.wang
   	 * @date    2015年10月20日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int updatepublishTime(@Param("bizProgram")BizProgram bizProgram);
    /**
   	 * Description：根据pid下架节目列表
   	 * @author  heng.wang
   	 * @date    2015年10月20日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int offlineProgram(@Param("bizProgram")BizProgram bizProgram);
    /**
   	 * Description：根据pid设置预发布时间
   	 * @author  heng.wang
   	 * @date    2015年10月20日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int savePredict(@Param("bizProgram")BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * 查询统计的列表信息
     * @author  Lin Xu
     * @date    2015年10月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public PageList<BizProgram> selectStatisticsList(@Param("bizProgram")BizProgram bizProgram,PageInfo pageinfo);
    
    /**
     * Description：<br> 
     * 查询统计的列表信息
     * @author  Lin Xu
     * @date    2015年10月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public PageList<BizProgram> selectStatisticsList(@Param("bizProgram")BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * 按月按天进行统计数据
     * @author  Lin Xu
     * @date    2015年10月28日
     * @version v1.0.0
     * @param map
     * @return
     */
    public HashMap<String, Object> selectOneProgrameData(HashMap<String, String> map);
    
    /**
     * Description：<br> 
     * 按月按天进行统计数据
     * @author  Lin Xu
     * @date    2015年10月28日
     * @version v1.0.0
     * @param map
     * @return
     */
    public List<HashMap<String, Object>> selectUserCountData(@Param("days") String days);
    
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
     * 查询节目板块类型
     * @author  heng.wang
     * @date    2015年10月28日
     * @version v1.0.0
     * @return
     */
    public PageList<BizProgram> selectProgramPlateType();
    
    /**
     * Description：<br> 
     * 统计用户性别分类
     * @author  heng.wang
     * @date    2015年10月28日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public  List<Integer> selecUserSexTotal(@Param("bizProgram")BizProgram bizProgram);
   
    /**
     * Description：<br> 
     * api：查询电台播放列表
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public List<BizProgram> selectRadioList(@Param("userId")String userId,@Param("start")Integer start,@Param("end")Integer end);
    
    /**
     * Description：<br> 
     * api：查询电台播放列表总数
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public Integer selectRadioListCount();
    
    /**
     * Description：<br> 
     * api：查询电台播放列表路径
     * @author  heng.wang
     * @date    2016年1月12日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public List<BizProgram> selectRadioFilePath(@Param("programId")String programId);
    
    /**
     * Description：<br> 
     * api：查询电台人气列表
     * @author  heng.wang
     * @date    2016年1月25日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public List<BizProgram> radioPopularityList();
    
    /**
     * Description：<br> 
     * api：如果app端点赞了,就往数据库加条记录
     * @author  heng.wang
     * @date    2016年1月25日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public int insertBizProgramCust(@Param("bizProgram")BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * api：根据电台id查节目类型总数
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public int selectProgramTypeCountByRadioId(String radioId);
    
    /**
     * Description：<br> 
     * api：查询点赞数
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public List<BizProgram> selectPraiseNum(String radioId);
    
    /**
     * Description：<br> 
     * api：更新点赞数
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public int updatePraiseNum(@Param("bizProgram")BizProgram bizProgram);
    
    /**
     * Description：<br> 
     * api：查是否已经点赞
     * @author  heng.wang
     * @date    2016年1月26日
     * @version v1.0.0
     * @param bizProgram
     * @return
     */
    public List<BizProgram>  selectIsPraise(String userId);
}