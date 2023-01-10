package kjs67_hanmall.controller.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.CategoryDTO;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class GetDetailCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("GetDetailCategoryAction : it's work");
//		String param = request.getParameter("category");
//		System.out.println("GetDetailCategoryAction : param : " + param);
		
		/*
		* category = 현재 선택된 카테고리 대분류. all / office / fashion / living / craft / popular
		* deatailCategory = 현재 category의 모든 세부 카테고리 목록
		* order = 현재 정렬 순서. date / sell / price / rating / none
		* nowDCategory = 현재 선택된 세부 카테고리. 없으면 0
		*/
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		CategoryDTO dto = new CategoryDTO();
		HanmallDAO dao = new HanmallDAO(); // fullCategory 조회용. 상단에 세부 카테고리 버튼 생성용
		HanmallDAO dao2 = new HanmallDAO(); // products 조회용. 상품 보여주기용
		
		String category = request.getParameter("category");
		String nowDCategoryStr = request.getParameter("nowDCategoryStr");
//		System.out.println("GetDetailCategoryAction : nowDCategoryStr : " + nowDCategoryStr);
		int nowDCategoryNum = getNowDCategory(nowDCategoryStr, category);
//		System.out.println("GetDetailCategoryAction : nowDCategory : " + nowDCategory);
		
		dto.setCategory(category);
		dto.setNowDCategory(nowDCategoryNum);
		
		CategoryDTO result = dao.getDetailCategory(dto);
		ArrayList<ProductDTO> products = dao2.getProductList(dto);
		
		request.setAttribute("fullCategory", result);
		request.setAttribute("products", products);
		forward.setRedirect(false);
		forward.setPath(Directory.CATEGORY.getDirMid());
		return forward;
	}

	private int getNowDCategory(String nowDCategoryStr, String category) {
		HashMap<String, Integer> map = new HashMap<>();
		int result = 0;
		map.put("1만원 이하", 1);
		map.put("1만원 ~ 3만원", 2);
		map.put("3만원 ~ 5만원", 3);
		map.put("5만원 이상", 4);
		
		map.put("노트/수첩", 1);
		map.put("필기구", 2);
		map.put("카드/엽서", 3);
		map.put("데스크용품", 4);
//		map.put("기타", 5);
		
		map.put("스카프/손수건", 1);
		map.put("의류", 2);
		map.put("가방/지갑", 3);
		map.put("악세사리", 4);
//		map.put("기타", 5);
		
		map.put("인테리어 소품", 1);
		map.put("주방/식기", 2);
		map.put("생활잡화", 3);
//		map.put("기타", 4);
		
		map.put("도자기", 1);
		map.put("수공예품", 2);
//		map.put("기타", 3);
		
		// 기타가 겹쳐서 category 받아서 따로 처리
		if (nowDCategoryStr == null) return 0;
		if (nowDCategoryStr.equals("기타")) {
			switch (category) {
			case "office":
			case "fashion":
				return 5;
				
			case "living":
				return 4;
				
			case "craft":
				return 3;
			}
		}
		
		try {
			result = map.get(nowDCategoryStr);
			return result;
		} catch (Exception e) {
			return 0;
		}
//		int result = map.get(nowDCategoryStr);
//		return result;
	}

}
