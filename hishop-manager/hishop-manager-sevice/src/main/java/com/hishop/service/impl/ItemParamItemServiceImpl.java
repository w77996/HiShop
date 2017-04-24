package com.hishop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishop.common.util.JsonUtils;
import com.hishop.mapper.TbItemParamItemMapper;
import com.hishop.pojo.TbItem;
import com.hishop.pojo.TbItemParamItem;
import com.hishop.pojo.TbItemParamItemExample;
import com.hishop.pojo.TbItemParamItemExample.Criteria;
import com.hishop.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public String getItemParamByItemId(Long itemId) {
		// TODO Auto-generated method stub
		TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
		Criteria criteria = tbItemParamItemExample.createCriteria();
		criteria.andItemIdEqualTo(itemId);

		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
		if (list == null || list.size() == 0) {
			return "";
			// 取出参数信息
		}
			TbItemParamItem itemParamItem = list.get(0);
			String paramData = itemParamItem.getParamData();
			// 把json数据转换成java对象
			List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
			// 将参数信息转换成html
			StringBuffer sb = new StringBuffer();
			// sb.append("<div>");
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
			sb.append("    <tbody>\n");
			for (Map map : paramList) {
				sb.append("        <tr>\n");
				sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
				sb.append("        </tr>\n");
				List<Map> params = (List<Map>) map.get("params");
				for (Map map2 : params) {
					sb.append("        <tr>\n");
					sb.append("            <td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
					sb.append("            <td>" + map2.get("v") + "</td>\n");
					sb.append("        </tr>\n");
				}
			}
			sb.append("    </tbody>\n");
			sb.append("</table>"); 
			// sb.append("</div>");
			return sb.toString();
		}
	

}
