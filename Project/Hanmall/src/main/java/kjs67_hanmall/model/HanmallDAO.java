package kjs67_hanmall.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HanmallDAO {
	private PreparedStatement pstmt = null;
	private Connection con = null;
	
	Context init = null; // 컨텍스트 객체 변수
	DataSource ds = null; // 데이터소스 객체 변수
	
	ResultSet rs = null;
	
	public HanmallDAO() {
		super();
		dbConnect();
	}
	
	public void dbConnect() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc_mariadb");
			con = ds.getConnection();
			
//			System.out.println("HanmallDAO : DB연결 성공");
		} catch (Exception e) {
			System.out.println("HanmallDAO : DB연결 실패");
			e.printStackTrace();
		}
	}
	
	public void disConnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public LoginDTO login(LoginDTO dto) {
		LoginDTO result = new LoginDTO();
		String sql = "SELECT * FROM user WHERE user_id=? AND user_pw=?";
		boolean isLogin = false;
		
//		System.out.println("HanmallDAO : " + dto.getUser_id());
//		System.out.println("HanmallDAO : " + dto.getUser_pw());
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				isLogin = true;
				result.setUser_num(rs.getInt("user_num"));
				result.setUser_id(rs.getString("user_id"));
				result.setUser_pw(rs.getString("user_pw"));
				result.setUser_name(rs.getString("user_name"));
				result.setUser_phone(rs.getString("user_phone"));
				result.setUser_mail(rs.getString("user_mail"));
				result.setUser_zip(rs.getString("user_zip"));
				result.setUser_addr(rs.getString("user_addr"));
				result.setUser_detail_addr(rs.getString("user_detail_addr"));
				result.setUser_date(rs.getString("user_date"));
				result.setUser_class(rs.getInt("user_class"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
//		System.out.println("HanmallDAO : " + result.getUser_mail());
		if (isLogin) {
			return result;
		} else {
			return null;
		}
	}

	public boolean register(LoginDTO dto) {
		boolean isSuccess = false;
		String sql = "INSERT INTO user (user_id, user_pw, user_name, user_phone, user_mail, user_zip, user_addr, user_detail_addr, user_class) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 2);";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			pstmt.setString(3, dto.getUser_name());
			pstmt.setString(4, dto.getUser_phone());
			pstmt.setString(5, dto.getUser_mail());
			pstmt.setString(6, dto.getUser_zip());
			pstmt.setString(7, dto.getUser_addr());
			pstmt.setString(8, dto.getUser_detail_addr());
			
			int result = pstmt.executeUpdate();
			
			if (result == 1) isSuccess = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			System.err.println("HanmallDAO : Register ERROR !!");
		} finally {
			disConnect();
		}
		
		return isSuccess;
	}

	public boolean productReg(ProductDTO dto) {
		boolean isSuccess1 = false;
		boolean isSuccess2 = false;
		String sql = "INSERT INTO product (user_num, prod_name, prod_seller, prod_cate1, prod_cate2, ";
		sql += "prod_price, prod_count, prod_summary, prod_content) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// 상품을 product 테이블에 등록
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getUser_num());
			pstmt.setString(2, dto.getProd_name());
			pstmt.setString(3, dto.getProd_seller());
			pstmt.setString(4, dto.getProd_cate1());
			pstmt.setString(5, dto.getProd_cate2());
			pstmt.setInt(6, dto.getProd_price());
			pstmt.setInt(7, dto.getProd_count());
			pstmt.setString(8, dto.getProd_summary());
			pstmt.setString(9, dto.getProd_content());
			
			int result = pstmt.executeUpdate();
			isSuccess1 = (result == 1) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 상품 이미지를 productimage 테이블에 등록하기 위한 준비
		String sql2 = "SELECT prod_num from product where prod_name='" + dto.getProd_name() + "'";
		int prod_num = 0;
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			prod_num = rs.getInt(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 상품 이미지를 productimage 테이블에 등록
		String sql3 = "INSERT INTO productimage (prod_num, prod_image0, prod_thumb_200, prod_thumb_80, ";
		sql3 += "prod_image1, prod_image2, prod_image3, prod_image4, prod_image5)";
		sql3 += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (prod_num > 0) {
			try {
				pstmt = con.prepareStatement(sql3);
				pstmt.setInt(1, prod_num);
				pstmt.setString(2, dto.getProd_image0());
				pstmt.setString(3, dto.getProd_thumb_200());
				pstmt.setString(4, dto.getProd_thumb_80());
				pstmt.setString(5, dto.getProd_image1());
				pstmt.setString(6, dto.getProd_image2());
				pstmt.setString(7, dto.getProd_image3());
				pstmt.setString(8, dto.getProd_image4());
				pstmt.setString(9, dto.getProd_image5());
				
				int result = pstmt.executeUpdate();
				isSuccess2 = (result == 1) ? true : false;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
		
		return isSuccess1 & isSuccess2;
	}

	public CategoryDTO getDetailCategory(CategoryDTO dto) {
		CategoryDTO result = new CategoryDTO();
		result.setCategory(dto.getCategory());
		int cate_num = 0;
		
		switch (dto.getCategory()) {
		case "all":
		case "popular":
			cate_num = 1;
			break;
			
		case "office":
			cate_num = 2;
			break;
			
		case "fashion":
			cate_num = 3;
			break;
			
		case "living":
			cate_num = 4;
			break;
			
		case "craft":
			cate_num = 5;
			break;
		}
		
//		System.out.println("HanmallDAO : cate_num : " + cate_num);
		String sql = "SELECT dcate_name FROM detailcategory WHERE cate_num=" + cate_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<String> arr = new ArrayList<String>();
			
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
			
			result.setDetailCategory(arr);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public ArrayList<ProductDTO> getProductList(CategoryDTO dto) {
		/*
		* category = 현재 선택된 카테고리 대분류. all / office / fashion / living / craft / popular
		* deatailCategory = 현재 category의 모든 세부 카테고리 목록
		* order = 현재 정렬 순서. date / sell / price / rating / none
		* nowDCategory = 현재 선택된 세부 카테고리. 없으면 0
		*/
		
		/*
		 * -- product table --
		 * prod_cate1 = office, fashion,  ...
		 * prod_cate2 = 1, 2, ...
		 */
		ArrayList<ProductDTO> products = new ArrayList<ProductDTO>();
		String prod_cate1 = dto.getCategory();
		int prod_cate2 = dto.getNowDCategory();
		
//		String sql1 = "SELECT * FROM product WHERE prod_cate1='" + prod_cate1 + "'";
//		String sql2 = "SELECT * FROM product WHERE prod_cate1=? AND prod_cate2=?";
//		System.out.println("HanmallDAO : prod_cate1 : " + prod_cate1);
//		System.out.println("HanmallDAO : prod_cate2 : " + prod_cate2);
		
		// 큰 카테고리 선택
		String sql1 = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql1 += "WHERE p.prod_cate1='" + prod_cate1 + "' ";
		sql1 += "ORDER BY p.prod_num desc";
		
		// 큰 카테고리 선택 + 세부 카테고리 선택 
		String sql2 = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql2 += "WHERE prod_cate1=? AND prod_cate2=? ";
		sql2 += "ORDER BY p.prod_num desc";
		
		// 전체 상품
		String sql3 = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql3 += "ORDER BY p.prod_num desc";
		
		// 전체 상품 (판매량순)
		String sql4 ="SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql4 += "ORDER BY p.prod_sellcount desc";
		
		// 전체 상품 + 가격 조건
		String sql5 = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql5 += "WHERE prod_price >= ? AND prod_price <= ? ";
		sql5 += "ORDER BY p.prod_num desc";
		
		// 전체 상품 (판매량순) + 가격 조건
		String sql6 = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql6 += "WHERE prod_price >= ? AND prod_price <= ? ";
		sql6 += "ORDER BY p.prod_sellcount desc";
		
		try {
			if (prod_cate1.equals("all")) { // 전체 상품
				if (prod_cate2 == 0) { // 전체 상품, 가격 조건 x
					pstmt = con.prepareStatement(sql3);
				} else if (prod_cate2 == 1) { // 전체 상품, 0원~10000원
					pstmt = con.prepareStatement(sql5);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, 10000);
				} else if (prod_cate2 == 2) { // 전체 상품, 10000원~30000원
					pstmt = con.prepareStatement(sql5);
					pstmt.setInt(1, 10000);
					pstmt.setInt(2, 30000);
				} else if (prod_cate2 == 3) { // 전체 상품, 30000원~50000원
					pstmt = con.prepareStatement(sql5);
					pstmt.setInt(1, 30000);
					pstmt.setInt(2, 50000);
				} else if (prod_cate2 == 4) { // 전체 상품, 50000원~
					pstmt = con.prepareStatement(sql5);
					pstmt.setInt(1, 50000);
					pstmt.setInt(2, 2147483647);
				}
			} else if (prod_cate1.equals("popular")) { // 전체 상품 (판매량순)
				if (prod_cate2 == 0) { // 전체 상품 (판매량순), 가격 조건 x
					pstmt = con.prepareStatement(sql4);
				} else if (prod_cate2 == 1) { // 전체 상품 (판매량순), 0원~10000원
					pstmt = con.prepareStatement(sql6);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, 10000);
				} else if (prod_cate2 == 2) { // 전체 상품 (판매량순), 10000원~30000원
					pstmt = con.prepareStatement(sql6);
					pstmt.setInt(1, 10000);
					pstmt.setInt(2, 30000);
				} else if (prod_cate2 == 3) { // 전체 상품 (판매량순), 30000원~50000원
					pstmt = con.prepareStatement(sql6);
					pstmt.setInt(1, 30000);
					pstmt.setInt(2, 50000);
				} else if (prod_cate2 == 4) { // 전체 상품 (판매량순), 50000원~
					pstmt = con.prepareStatement(sql6);
					pstmt.setInt(1, 50000);
					pstmt.setInt(2, 2147483647);
				}
			} else if (prod_cate2 == 0) { // 큰 카테고리 선택
				pstmt = con.prepareStatement(sql1);
			} else { // 큰 카테고리 선택 + 세부 카테고리 선택
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, prod_cate1);
				pstmt.setInt(2, prod_cate2);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO temp = new ProductDTO();
				temp.setProd_num(rs.getInt("prod_num"));
				temp.setUser_num(rs.getInt("user_num"));
				temp.setProd_name(rs.getString("prod_name"));
				temp.setProd_seller(rs.getString("prod_seller"));
				temp.setProd_cate1(rs.getString("prod_cate1"));
				temp.setProd_cate2(rs.getString("prod_cate2"));
				temp.setProd_price(rs.getInt("prod_price"));
				temp.setProd_count(rs.getInt("prod_count"));
				temp.setProd_summary(rs.getString("prod_summary"));
				temp.setProd_grade(rs.getString("prod_grade"));
				temp.setProd_content(rs.getString("prod_content"));
				temp.setProd_sellcount(rs.getInt("prod_sellcount"));
				temp.setProd_date(rs.getString("prod_date"));
				temp.setProd_image0(rs.getString("prod_image0"));
				temp.setProd_image1(rs.getString("prod_image1"));
				temp.setProd_image2(rs.getString("prod_image2"));
				temp.setProd_image3(rs.getString("prod_image3"));
				temp.setProd_image4(rs.getString("prod_image4"));
				temp.setProd_image5(rs.getString("prod_image5"));
				temp.setProd_thumb_200(rs.getString("prod_thumb_200"));
				temp.setProd_thumb_80(rs.getString("prod_thumb_80"));
				products.add(temp);
			}
			
//			for (ProductDTO productDTO : products) {
//				System.out.println("HanmallDAO : products : " + productDTO.getProd_name());
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return products;
	}

	public ProductDTO getProductInfo(ProductDTO dto) {
		String sql = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql += "WHERE p.prod_num=" + dto.getProd_num();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setUser_num(rs.getInt("user_num"));
			dto.setProd_name(rs.getString("prod_name"));
			dto.setProd_seller(rs.getString("prod_seller"));
			dto.setProd_cate1(rs.getString("prod_cate1"));
			dto.setProd_cate2(rs.getString("prod_cate2"));
			dto.setProd_price(rs.getInt("prod_price"));
			dto.setProd_count(rs.getInt("prod_count"));
			dto.setProd_summary(rs.getString("prod_summary"));
			dto.setProd_grade(rs.getString("prod_grade"));
			dto.setProd_content(rs.getString("prod_content"));
			dto.setProd_sellcount(rs.getInt("prod_sellcount"));
			dto.setProd_date(rs.getString("prod_date"));
			dto.setProd_image0(rs.getString("prod_image0"));
			dto.setProd_image1(rs.getString("prod_image1"));
			dto.setProd_image2(rs.getString("prod_image2"));
			dto.setProd_image3(rs.getString("prod_image3"));
			dto.setProd_image4(rs.getString("prod_image4"));
			dto.setProd_image5(rs.getString("prod_image5"));
			dto.setProd_thumb_200(rs.getString("prod_thumb_200"));
			dto.setProd_thumb_80(rs.getString("prod_thumb_80"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return dto;
	}

	public LoginDTO getUserInfo(LoginDTO buyer) {
		String sql = "SELECT * FROM user WHERE user_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, buyer.getUser_num());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				buyer.setUser_id(rs.getString("user_id"));
				buyer.setUser_pw(rs.getString("user_pw"));
				buyer.setUser_name(rs.getString("user_name"));
				buyer.setUser_phone(rs.getString("user_phone"));
				buyer.setUser_mail(rs.getString("user_mail"));
				buyer.setUser_zip(rs.getString("user_zip"));
				buyer.setUser_addr(rs.getString("user_addr"));
				buyer.setUser_detail_addr(rs.getString("user_detail_addr"));
				buyer.setUser_date(rs.getString("user_date"));
				buyer.setUser_class(rs.getInt("user_class"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return buyer;
	}

	public int checkID(String user_id) {
		String sql = "SELECT user_id FROM user WHERE user_id=?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public boolean addToCart(int user_num, int prod_num, int cart_count) {
		boolean result = false;
		String sql = "INSERT INTO cart (user_num, prod_num, cart_count)";
		sql += "VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			pstmt.setInt(2, prod_num);
			pstmt.setInt(3, cart_count);
			int insertResult = pstmt.executeUpdate();
			
			if (insertResult == 1) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public ArrayList<ProductDTO> getCartList(LoginDTO userInfo) {
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
//					  SELECT * FROM cart AS c JOIN product AS p ON c.prod_num=p.prod_num
//				JOIN productimage as i ON p.prod_num=i.prod_num
//				WHERE c.user_num=1;
		String sql = "SELECT * FROM cart AS c JOIN product AS p ON c.prod_num=p.prod_num ";
		sql += "JOIN productimage as i ON p.prod_num=i.prod_num ";
		sql += "WHERE c.user_num=? ";
		sql += "ORDER BY p.prod_num desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userInfo.getUser_num());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO temp = new ProductDTO();
				temp.setProd_num(rs.getInt("prod_num"));
				temp.setProd_name(rs.getString("prod_name"));
				temp.setBuyCount(rs.getInt("cart_count"));
				temp.setProd_price(rs.getInt("prod_price"));
				temp.setProd_thumb_200(rs.getString("prod_thumb_200"));
				temp.setCart_num(rs.getInt("cart_num"));
				
//				System.out.println(temp);
				result.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public int removeCartItem(int cart_num) {
		String sql = "DELETE FROM cart WHERE cart_num=" + cart_num;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public void cartListOrder(int user_num) {
		String sql = "DELETE FROM cart WHERE user_num=" + user_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return;
	}

	public boolean updateUser(LoginDTO dto) {
		boolean result = false;
		int queryResult = 0;
		String sql = "UPDATE user SET user_pw=?, user_name=?, user_phone=?, user_mail=?, user_zip=?, user_addr=?, user_detail_addr=? ";
		sql += "WHERE user_num=?";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_pw());
			pstmt.setString(2, dto.getUser_name());
			pstmt.setString(3, dto.getUser_phone());
			pstmt.setString(4, dto.getUser_mail());
			pstmt.setString(5, dto.getUser_zip());
			pstmt.setString(6, dto.getUser_addr());
			pstmt.setString(7, dto.getUser_detail_addr());
			pstmt.setInt(8, dto.getUser_num());
			
			queryResult = pstmt.executeUpdate();
			
			if (queryResult == 1) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	
		return result;
	}

	public ArrayList<ProductDTO> getSearchResult(String searchKeyword) {
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		String sql = "SELECT * FROM product as p JOIN productimage as i ON p.prod_num=i.prod_num WHERE p.prod_name LIKE ? ";
		sql += "ORDER BY p.prod_num desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO prod = new ProductDTO();
				prod.setProd_num(rs.getInt("prod_num"));
				prod.setUser_num(rs.getInt("user_num"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_seller(rs.getString("prod_seller"));
				prod.setProd_cate1(rs.getString("prod_cate1"));
				prod.setProd_cate2(rs.getString("prod_cate2"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setProd_count(rs.getInt("prod_count"));
				prod.setProd_summary(rs.getString("prod_summary"));
				prod.setProd_grade(rs.getString("prod_grade"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setProd_sellcount(rs.getInt("prod_sellcount"));
				prod.setProd_date(rs.getString("prod_date"));
				prod.setProd_image0(rs.getString("prod_image0"));
				prod.setProd_image1(rs.getString("prod_image1"));
				prod.setProd_image2(rs.getString("prod_image2"));
				prod.setProd_image3(rs.getString("prod_image3"));
				prod.setProd_image4(rs.getString("prod_image4"));
				prod.setProd_image5(rs.getString("prod_image5"));
				prod.setProd_thumb_200(rs.getString("prod_thumb_200"));
				prod.setProd_thumb_80(rs.getString("prod_thumb_80"));
				result.add(prod);
			}
			
//			for (ProductDTO p : result) {
//				System.out.println("HanmallDAO : getSearchResult : " + p);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public ArrayList<LoginDTO> getAllUserList() {
		ArrayList<LoginDTO> result = new ArrayList<LoginDTO>();
		String sql = "SELECT user_num, user_id, user_name, user_phone, user_mail, user_date, user_class FROM user";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				LoginDTO user = new LoginDTO();
				user.setUser_num(rs.getInt(1));
				user.setUser_id(rs.getString(2));
				user.setUser_name(rs.getString(3));
				user.setUser_phone(rs.getString(4));
				user.setUser_mail(rs.getString(5));
				user.setUser_date(rs.getString(6));
				user.setUser_class(rs.getInt(7));
				result.add(user);
//				System.out.println("HanmallDAO : getAllUserList : " + user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public boolean setUserClass(int user_num, int newClass) {
		int result = 0;
		String sql = "UPDATE user SET user_class=? WHERE user_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newClass);
			pstmt.setInt(2, user_num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return (result == 1) ? true : false;
	}

	public boolean deleteUser(int user_num) {
		int result = 0;
		String sql = "DELETE FROM user WHERE user_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return (result == 1) ? true : false;
	}

	public boolean isAlreadyExist(int user_num, int prod_num) {
		String sql = "SELECT prod_num FROM cart WHERE user_num=? AND prod_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			pstmt.setInt(2, prod_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return false;
	}

	public ArrayList<ProductDTO> getProductListAtCart(int user_num) {
//					  SELECT * FROM product AS p JOIN productimage AS i ON p.prod_num=i.prod_num
//				JOIN cart AS c ON p.prod_num=c.prod_num WHERE c.user_num=1;
		String sql = "SELECT p.prod_num, p.prod_name, p.prod_price, i.prod_thumb_80, c.cart_count FROM product AS p JOIN productimage AS i ON p.prod_num=i.prod_num ";
		sql += "JOIN cart AS c ON p.prod_num=c.prod_num WHERE c.user_num=?";
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO temp = new ProductDTO();
				temp.setProd_num(rs.getInt(1));
				temp.setProd_name(rs.getString(2));
				temp.setProd_price(rs.getInt(3));
				temp.setProd_thumb_80(rs.getString(4));
				temp.setCart_count(rs.getInt(5));
				result.add(temp);
//				System.out.println("HanmallDAO : getProductListAtCart : " + temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public ArrayList<ProductDTO> getUserProductList(int user_num) {
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		// SELECT * FROM product AS p JOIN productimage AS i ON p.prod_num=i.prod_num WHERE p.user_num=1;
		String sql = "SELECT * FROM product AS p JOIN productimage AS i ON p.prod_num=i.prod_num WHERE p.user_num=" + user_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO prod = new ProductDTO();
				prod.setProd_num(rs.getInt("prod_num"));
				prod.setUser_num(rs.getInt("user_num"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_seller(rs.getString("prod_seller"));
				prod.setProd_cate1(rs.getString("prod_cate1"));
				prod.setProd_cate2(rs.getString("prod_cate2"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setProd_count(rs.getInt("prod_count"));
				prod.setProd_summary(rs.getString("prod_summary"));
				prod.setProd_grade(rs.getString("prod_grade"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setProd_sellcount(rs.getInt("prod_sellcount"));
				prod.setProd_date(rs.getString("prod_date"));
				prod.setProd_image0(rs.getString("prod_image0"));
				prod.setProd_image1(rs.getString("prod_image1"));
				prod.setProd_image2(rs.getString("prod_image2"));
				prod.setProd_image3(rs.getString("prod_image3"));
				prod.setProd_image4(rs.getString("prod_image4"));
				prod.setProd_image5(rs.getString("prod_image5"));
				prod.setProd_thumb_200(rs.getString("prod_thumb_200"));
				prod.setProd_thumb_80(rs.getString("prod_thumb_80"));
				result.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public boolean updateProduct(ProductDTO product) {
		boolean result = false;
		String sql = "UPDATE product SET prod_name=?, prod_seller=?, prod_price=?, prod_count=?, prod_summary=?, prod_content=? WHERE prod_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getProd_name());
			pstmt.setString(2, product.getProd_seller());
			pstmt.setInt(3, product.getProd_price());
			pstmt.setInt(4, product.getProd_count());
			pstmt.setString(5, product.getProd_summary());
			pstmt.setString(6, product.getProd_content());
			pstmt.setInt(7, product.getProd_num());
			
			int resultCnt = pstmt.executeUpdate();
			if (resultCnt == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public boolean deleteProduct(int prod_num) {
		boolean result1 = false;
		boolean result2 = false;
		String sql1 = "DELETE FROM productimage WHERE prod_num="+prod_num;
		String sql2 = "DELETE FROM product WHERE prod_num="+prod_num;
		
		try {
			pstmt = con.prepareStatement(sql1);
			int resultCnt = pstmt.executeUpdate();
			if (resultCnt == 1) {
				result1 = true;
			}
			
			pstmt = con.prepareStatement(sql2);
			resultCnt = pstmt.executeUpdate();
			if (resultCnt == 1) {
				result2 = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result1 & result2;
	}

	public LoginDTO findUserId(LoginDTO dto) {
		String sql = "SELECT user_id FROM user WHERE user_name=? AND user_phone=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_phone());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return dto;
	}

	public boolean paymentSuccess(PaymentDTO dto) {
		String sql = "INSERT INTO payments VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getUser_num());
			pstmt.setString(2, dto.getUser_name());
			pstmt.setString(3, dto.getUser_phone());
			pstmt.setString(4, dto.getUser_zip());
			pstmt.setString(5, dto.getUser_addr());
			pstmt.setString(6, dto.getUser_detail_addr());
			
			pstmt.setString(7, dto.getDeliv_name());
			pstmt.setString(8, dto.getDeliv_phone());
			pstmt.setString(9, dto.getDeliv_zip());
			pstmt.setString(10, dto.getDeliv_addr());
			pstmt.setString(11, dto.getDeliv_detail_addr());
			
			pstmt.setString(12, dto.getOrder_id());
			pstmt.setString(13, dto.getPay_method());
			pstmt.setInt(14, dto.getPay_amount());
			pstmt.setInt(15, dto.getPay_success());
			pstmt.setString(16, dto.getPay_date());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return (result == 1) ? true : false;
	}

	public void clearCart(int user_num) {
		String sql = "DELETE FROM cart WHERE user_num=" + user_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	public LoginDTO getUserPassword(LoginDTO dto) {
		String sql = "SELECT user_num, user_pw FROM user WHERE user_name=? AND user_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_id());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setUser_num(rs.getInt(1));
				dto.setUser_pw(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return dto;
	}

	public void changePassword(String encryptedNewPw, LoginDTO dto) {
		String sql = "UPDATE user SET user_pw=? WHERE user_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, encryptedNewPw);
			pstmt.setInt(2, dto.getUser_num());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	public void reduceProductCount(int prod_num, int cart_count) {
		String sql = "UPDATE product SET prod_count=prod_count-? WHERE prod_num=?; ";
		String sql2 = "UPDATE product SET prod_sellcount=prod_sellcount+? WHERE prod_num=?;";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_count);
			pstmt.setInt(2, prod_num);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, cart_count);
			pstmt.setInt(2, prod_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	public ArrayList<PaymentDTO> getUserProductHistory(int user_num) {
		ArrayList<PaymentDTO> result = new ArrayList<PaymentDTO>();
		String sql = "SELECT * FROM payments WHERE user_num=" + user_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PaymentDTO temp = new PaymentDTO();
				temp.setUser_num(user_num);
				temp.setUser_name(rs.getString(2));
				temp.setUser_phone(rs.getString(3));
				temp.setUser_zip(rs.getString(4));
				temp.setUser_addr(rs.getString(5));
				temp.setUser_detail_addr(rs.getString(6));
				
				temp.setDeliv_name(rs.getString(7));
				temp.setDeliv_phone(rs.getString(8));
				temp.setDeliv_zip(rs.getString(9));
				temp.setDeliv_addr(rs.getString(10));
				temp.setDeliv_detail_addr(rs.getString(11));
				
				temp.setOrder_id(rs.getString(12));
				temp.setPay_method(rs.getString(13));
				temp.setPay_amount(rs.getInt(14));
				temp.setPay_success(rs.getInt(15));
				temp.setPay_date(rs.getString(16));
				
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return result;
	}

	public void clearpaymentList(int user_num) {
		String sql = "DELETE FROM payments WHERE user_num="+user_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

}