package com.hishop.protal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.common.util.JsonUtils;
import com.hishop.pojo.TbItem;
import com.hishop.pojo.TbItemDesc;
import com.hishop.pojo.TbItemParamItem;
import com.hishop.protal.pojo.ItemInfo;
import com.hishop.protal.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	@Override
	public ItemInfo getItemById(long itemId) {
		// TODO Auto-generated method stub
		try {
			String json =HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			if(!StringUtils.isBlank(json)){
				HishopResult result  = HishopResult.formatToPojo(json, ItemInfo.class);
				if(result.getStatus() == 200){
					ItemInfo info = (ItemInfo) result.getData();
					return info;
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String getItemDescById(Long itemId) {
		// TODO Auto-generated method stub
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
			HishopResult result = HishopResult.formatToPojo(json, TbItemDesc.class);
			if(result.getStatus() == 200){
				TbItemDesc itemDesc = (TbItemDesc) result.getData();
				String reslut = itemDesc.getItemDesc();
				return reslut;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	@Override
	public String getItemParam(Long itemId) {
		// TODO Auto-generated method stub
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_PARAM_URL+itemId);
			HishopResult item = HishopResult.formatToPojo(json, TbItemParamItem.class);
			if(item.getStatus()==200){
				TbItemParamItem tbItemParamItem = (TbItemParamItem) item.getData();
				String restult = tbItemParamItem.getParamData();
				List<Map> jsonList = JsonUtils.jsonToList(restult, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for(Map m1:jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for(Map m2:list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
						sb.append("            <td>"+m2.get("v")+"</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				//返回html片段
				return sb.toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

}
