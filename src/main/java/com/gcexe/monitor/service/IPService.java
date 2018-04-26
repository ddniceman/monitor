package com.gcexe.monitor.service;
import com.gcexe.monitor.utils.ResultCodeVo;

public interface IPService extends BaseService {
	/**
	 * IP(域名数)TOP10
	 * @param json
	 * @return
	 */
	public ResultCodeVo  domainCnt(String json);
	/**
	 * 非法域名IPTOP10
	 * @param json
	 * @return
	 */
	public ResultCodeVo  illegalcnt(String json);
	/**
	 * IP域名总数/非法域名总数态势
	 * @param json
	 * @return
	 */
	public ResultCodeVo  situation(String json);
	/**
	 * IP平均挂域名态势
	 * @param json
	 * @return
	 */
	public ResultCodeVo  avgsituation(String json);

}
