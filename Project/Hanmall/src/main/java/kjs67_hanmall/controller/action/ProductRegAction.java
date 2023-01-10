package kjs67_hanmall.controller.action;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class ProductRegAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext application = request.getServletContext();
		String path = application.getRealPath("/");
//		System.out.println("ProductRegAction : RealPath : " + path);
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		ProductDTO dto = new ProductDTO();
		HttpSession session = request.getSession();
//		String imgDirPath = "D:\\Dev\\Workspace\\Hanmall\\src\\main\\webapp\\com\\yju\\2wda\\kjs67\\src\\image\\product\\"; // 개발용. 다른 컴퓨터에선 경로가 안맞아서 못써먹음
		String imgDirPath = path + "com\\yju\\2wda\\kjs67\\src\\image\\product\\"; // 제출용. 서버에 저장되어서 다른 컴퓨터에서도 써먹을 수 있지만 개발 폴더엔 저장 안됨
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest multi = new MultipartRequest(request, imgDirPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		makeThumb(multi, imgDirPath);
		
//		System.out.println(multi.getParameter("productName"));
//		System.out.println(multi.getParameter("inputGroupFile00"));
//		System.out.println(multi.getParameter("inputGroupFile01"));
//		System.out.println(multi.getParameter("inputGroupFile02"));
//		System.out.println(multi.getParameter("inputGroupFile03"));
//		System.out.println(multi.getParameter("inputGroupFile04"));
//		System.out.println(multi.getParameter("inputGroupFile05"));
		
		Enumeration files = multi.getFileNames();
		Vector<String> imageNames = new Vector<String>();
		while (files.hasMoreElements()) {
			String formName = (String) files.nextElement();
			String imageName = multi.getFilesystemName(formName);
			if (imageName == null || imageName.equals("")) 
				continue;
			imageNames.add(imageName); // 파일의 이름 얻기
		}
//		for (String s : imageNames) {
//			System.out.println(s);
//		}
		
		dto.setProd_name(multi.getParameter("productName"));
		dto.setProd_seller(multi.getParameter("sellerName"));
		dto.setProd_cate1(multi.getParameter("category1"));
		dto.setProd_cate2(multi.getParameter("category2"));
		dto.setProd_price(Integer.parseInt(multi.getParameter("price")));
		dto.setProd_count(Integer.parseInt(multi.getParameter("productCount")));
		dto.setProd_summary(multi.getParameter("productSummary"));
		dto.setUser_num(Integer.parseInt(multi.getParameter("user_num")));
		dto.setProd_content(multi.getParameter("editorTxt"));
		try {
			if (imageNames.get(0) != null || !imageNames.get(0).equals(""))
				dto.setProd_image0(imageNames.get(0));
			if (imageNames.get(1) != null || !imageNames.get(1).equals(""))
				dto.setProd_image1(imageNames.get(1));
			if (imageNames.get(2) != null || !imageNames.get(2).equals(""))
				dto.setProd_image2(imageNames.get(2));
			if (imageNames.get(3) != null || !imageNames.get(3).equals(""))
				dto.setProd_image3(imageNames.get(3));
			if (imageNames.get(4) != null || !imageNames.get(4).equals(""))
				dto.setProd_image4(imageNames.get(4));
			if (imageNames.get(5) != null || !imageNames.get(5).equals(""))
				dto.setProd_image5(imageNames.get(5));
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		dto.setProd_thumb_200("thumb_200_" + imageNames.get(0));
		dto.setProd_thumb_80("thumb_80_" + imageNames.get(0));
		
//		System.out.println(dto.getProd_image0());
//		System.out.println(dto.getProd_image1());
//		System.out.println(dto.getProd_image2());
//		System.out.println(dto.getProd_image3());
//		System.out.println(dto.getProd_image4());
//		System.out.println(dto.getProd_image5());
		
		boolean isSuccess = dao.productReg(dto);
		
		if (isSuccess) {
			forward.setRedirect(false);
			forward.setPath(Directory.INDEX.getDirMid());
			session.setAttribute("alert", "상품이 정상적으로 등록되었습니다.");
		} else {
			forward.setRedirect(false);
			forward.setPath(Directory.PRODUCTREG.getDirMid());
			session.setAttribute("alert", "상품 등록에 실패했습니다.");
		}
		
		return forward;
	}

	private void makeThumb (MultipartRequest multi, String imgDirPath) {
		int maxSize = 1024 * 1024 * 10;
		
		Enumeration<?> files = multi.getFileNames();
		File originalImage = null;
		
		String element = "";
		String filesystemName = "";
		String originalFileName = "";
		String contentType = "";
		long length = 0;
		
		// 원본 파일 경로
		String path = "";
		
		if (files.hasMoreElements()) {
			element = (String)files.nextElement();
			
			filesystemName = multi.getFilesystemName(element);
			originalFileName = multi.getOriginalFileName(element);
			contentType = multi.getContentType(element);
			length = multi.getFile(element).length();
			originalImage = multi.getFile(element);
			path = imgDirPath + filesystemName;
			
			int index = path.lastIndexOf(".");
			String ext = path.substring(index + 1); // 파일 확장자
			
			// 저장 경로
			String tempPath = originalImage.getParent() + File.separator + "\\thumb\\thumb_200_" + originalImage.getName();
			// 썸네임을 담아둘 임시 파일
			File tempFile = new File(tempPath);
			
			try {
				// 원본 이미지
				BufferedImage image = ImageIO.read(originalImage);
				
				int width = 200;
				double temp = 200.0 / image.getWidth(); // 이미지 배율
				int height = (int)((double)image.getHeight() * temp);
				
				// 썸네일을 담아둘 임시 이미지
				BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				// 그리는 툴
				Graphics2D g = tempImage.createGraphics();
				// 원본 이미지 정보
				Image img = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				// 그리기
				g.drawImage(img, 0, 0, width, height, null);
				// 리소스 해제
				g.dispose();
				
				// 그린 이미지 파일 형태로 출력
				ImageIO.write(tempImage, ext, tempFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			tempPath = originalImage.getParent() + File.separator + "\\thumb\\thumb_80_" + originalImage.getName();
			tempFile = new File(tempPath);
			try {
				// 원본 이미지
				BufferedImage image = ImageIO.read(originalImage);
				
				int width = 80;
				double temp = 80.0 / image.getWidth(); // 이미지 배율
				int height = (int)((double)image.getHeight() * temp);
				
				// 썸네일을 담아둘 임시 이미지
				BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				// 그리는 툴
				Graphics2D g = tempImage.createGraphics();
				// 원본 이미지 정보
				Image img = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				// 그리기
				g.drawImage(img, 0, 0, width, height, null);
				// 리소스 해제
				g.dispose();
				
				// 그린 이미지 파일 형태로 출력
				ImageIO.write(tempImage, ext, tempFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
