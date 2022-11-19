package com.yscf.core.service.user.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.common.util.EncodedUtil;
import com.yscf.core.dao.user.BankMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.Bank;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.user.IBankService;
/**
 * Description：<br> 
 * 客户管理服务实现
 * @author  Allen
 * @date    2015年9月14日
 * @version v1.0.0
 */
@Service("bankService")
public class BankServiceImpl extends BaseService<BaseEntity, String> 
		implements IBankService{
	@Autowired
	public BankServiceImpl(BankMapper dao) {
		super(dao);
	}
//	客户的
	@Resource(name = "cusTomerMapper")
	CusTomerMapper customerMapper;
	
	//读取系统参数
	@Resource(name="sysParamsService")
	ISysParamsService sysParamsServiceImpl;

	@Override
	public int insert(Bank bank) throws FrameworkException {
		BankMapper mapper = (BankMapper) getDao();
		return mapper.insertSelective(bank);
	}

	@Override
	public List<Bank> selectAll(Bank bank, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBtach(String pids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageList<Bank> selectAllPage(Bank bank, PageInfo info) {
		BankMapper mapper = (BankMapper) getDao();
		
 		return mapper.selectAllPage(bank, info);
	}

	@Override
	public int updatCusIdByPrimaryKey(Bank bank) {
		int result=0;
		if(null !=bank && null!=bank.getPid()){
			BankMapper mapper = (BankMapper) getDao();
			Map<String,Object> map = new HashMap<String,Object>();
			//map.put("cusmoterServiceUser", bank.getCustomerServiceUser());
			map.put("idItem", bank.getPid().split(","));
			result = mapper.updatCusIdByPrimaryKey(map);
		}
		return result;
	}

	@Override
	public PageList<Bank> selectCusByPid(String pid) {
		BankMapper mapper = (BankMapper) getDao();
		Map<String,Object> map = new HashMap<String,Object>();
		if(pid!=null){
			//
			map.put("pid",pid.split(","));
		}
		return mapper.selectCusByPid(map);
	}
	
	@Override
	public PageList<Bank> selectBankInfoById(String pid) {
		BankMapper mapper = (BankMapper) getDao();
		PageList<Bank> list = new PageList<Bank>();
		if(pid!=null){
		   list = mapper.selectBankInfoById(pid);
		}
		return list;
	}

	@Override
	public int selectBankTradingPwdByBankCar(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		int count = 0;
	    if(bank!=null){
	    	count=mapper.selectBankTradingPwdByBankCar(bank);
	    }
		return count;
	}

	@Override
	public int updateBankStatus(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		int size = 0;
	    if(bank!=null){
	    	size=mapper.updateBankStatus(bank);
	    }
		return size;
	}

	@Override
	public int validateTradingPwd(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		//加密
		String pwd = EncodedUtil.getEncodedPassword(bank.getLoginPassWord(), bank.getIndex()+"");
		bank.setPassWord(pwd);
		return mapper.validateTradingPwd(bank);
	}

	@Override
	public int saveTradingPwd(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		// 保存成功后，取出加密引子与密码加密
		Integer index = customerMapper.selectIndexByPid(bank.getCustomerId());
		String tradPassword = EncodedUtil.getEncodedPassword(
				bank.getNewPwd(), index.toString());
		bank.setNewPwd(tradPassword);
		return mapper.saveTradingPwd(bank);
	}

	@Override
	public int selectUserName(String userName) {
		BankMapper mapper = (BankMapper) getDao();
		return mapper.selectUserName(userName);
	}

	@Override
	public int updateLoginPwd(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		// 保存成功后，取出加密引子与密码加密
		Integer index = customerMapper.selectIndexByPid(bank.getCustomerId());
		String loginPwd = EncodedUtil.getEncodedPassword(
				bank.getLoginPassWord(), index.toString());
		bank.setLoginPassWord(loginPwd);
		return mapper.updateLoginPwd(bank);
	}

	@Override
	public int selectBankCountByCusId(String cusId) throws FrameworkException {
		BankMapper dao = (BankMapper) getDao();
		return dao.selectBankCountByCusId(cusId);
	}

	@Override
	public int addBankInfo(HashMap<String, Object> map) {
		BankMapper dao = (BankMapper) getDao();
		return dao.addBankInfo(map);
	}

	@Override
	public int updateBankInfoApi(Bank bank) {
		BankMapper mapper = (BankMapper) getDao();
		return mapper.updateBankInfoApi(bank);
	}

	@Override
	public int selectQuickPayment(String bankCardId) {
		BankMapper mapper = (BankMapper) getDao();
		return mapper.selectQuickPayment(bankCardId);
	}
	@Override
	public Bank getByCardBank(String key,String card) {
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost("http://apis.haoservice.com/lifeservice/bankcard/query");  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("key", key));  
        formparams.add(new BasicNameValuePair("card", card));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	String jsonStr = EntityUtils.toString(entity, "UTF-8");
                	return parse(jsonStr);
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;
	}
	
	private static Bank parse(String jsonStr) {
		JSONObject jsonObject;
		Bank b = new Bank();
		try {
			jsonObject = new JSONObject(jsonStr);
			String result = jsonObject.getString("result");
			JSONObject j = new JSONObject(result);
			String card = j.getString("card");
			String province = j.getString("province");
			String city = j.getString("city");
			String bank = j.getString("bank");
			String type = j.getString("type");
			String cardname = j.getString("cardname");
			String tel = j.getString("tel");
			b.setBankcardNo(card);
			b.setBelongingProvince(province);
			b.setBelongingCity(city);
			b.setBelongingBank(bank);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	public Bank getByCardBank(String card) {
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost("http://apis.haoservice.com/lifeservice/bankcard/query");  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        //获取验证银行卡信息的key值
  		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("BANK_KEY");
  		String key = sysParams.getParamValue();
        formparams.add(new BasicNameValuePair("key", key));  
        formparams.add(new BasicNameValuePair("card", card));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	String jsonStr = EntityUtils.toString(entity, "UTF-8");
                	return parse(jsonStr);
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;
	}
	
}
