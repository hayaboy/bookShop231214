package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

@Controller("mainController")
@EnableAspectJAutoProxy  //Spring의 <aop:aspectj-autoproxy> XML 요소에 있는 기능과 유사하게 AspectJ의 @Aspect 주석으로 표시된 구성 요소 처리에 대한 지원을 활성화합니다.
public class MainController extends BaseController {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		//로그인 한 사용자(관리자(admin), 일반 사용자(고객)에 따라 화면의 구성이 달라지게 하기 위해 세션에 저장된 사용자별로 화면 구성을 달리 
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}
}
