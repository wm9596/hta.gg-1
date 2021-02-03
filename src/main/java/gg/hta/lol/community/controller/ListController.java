package gg.hta.lol.community.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gg.hta.lol.service.CommunityService;
import gg.hta.lol.util.PageUtil;
import gg.hta.lol.vo.CommunityVo;

@Controller
public class ListController {
	@Autowired private CommunityService service;
	
	@RequestMapping("/community/list")
	public ModelAndView list(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,String field,String keyword) {
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("field", field);
		map.put("keyword",keyword);
		
		int totalRowCount=service.count(map);
		PageUtil pu=new PageUtil(pageNum, 10, 10, totalRowCount);
		System.out.println("pu:"+ pu);
		int startRow=pu.getStartRow();
		int endRow=pu.getEndRow();
		map.put("startRow",startRow);
		map.put("endRow",endRow);
		
		List<CommunityVo> list=service.list(map);
		
		ModelAndView mv=new ModelAndView("list");
		mv.addObject("list", list);
		mv.addObject("pu",pu);
		mv.addObject("field", field);
		mv.addObject("keyword",keyword);
		return mv;
	}
}