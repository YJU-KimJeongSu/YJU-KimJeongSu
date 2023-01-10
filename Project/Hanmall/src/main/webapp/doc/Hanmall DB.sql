-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.9.1-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- kjs67_hanmall_db 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `kjs67_hanmall_db`;
CREATE DATABASE IF NOT EXISTS `kjs67_hanmall_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `kjs67_hanmall_db`;

-- 테이블 kjs67_hanmall_db.cart 구조 내보내기
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_num` int(11) NOT NULL AUTO_INCREMENT,
  `user_num` int(11) NOT NULL,
  `prod_num` int(11) NOT NULL,
  `cart_count` int(11) NOT NULL,
  PRIMARY KEY (`cart_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.cart:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.category 구조 내보내기
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `cate_num` int(11) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(10) NOT NULL,
  PRIMARY KEY (`cate_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.category:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cate_num`, `cate_name`) VALUES
	(1, 'all'),
	(2, 'office'),
	(3, 'fashion'),
	(4, 'living'),
	(5, 'craft');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.detailcategory 구조 내보내기
DROP TABLE IF EXISTS `detailcategory`;
CREATE TABLE IF NOT EXISTS `detailcategory` (
  `cate_num` int(11) NOT NULL,
  `dcate_num` int(11) NOT NULL,
  `dcate_name` varchar(10) NOT NULL,
  KEY `FK_detailcategory_category` (`cate_num`),
  CONSTRAINT `FK_detailcategory_category` FOREIGN KEY (`cate_num`) REFERENCES `category` (`cate_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.detailcategory:~21 rows (대략적) 내보내기
/*!40000 ALTER TABLE `detailcategory` DISABLE KEYS */;
INSERT INTO `detailcategory` (`cate_num`, `dcate_num`, `dcate_name`) VALUES
	(1, 1, '1만원 이하'),
	(1, 2, '1만원 ~ 3만원'),
	(1, 3, '3만원 ~ 5만원'),
	(1, 4, '5만원 이상'),
	(2, 1, '노트/수첩'),
	(2, 2, '필기구'),
	(2, 3, '카드/엽서'),
	(2, 4, '데스크용품'),
	(2, 5, '기타'),
	(3, 1, '스카프/손수건'),
	(3, 2, '의류'),
	(3, 3, '가방/지갑'),
	(3, 4, '악세사리'),
	(3, 5, '기타'),
	(4, 1, '인테리어 소품'),
	(4, 2, '주방/식기'),
	(4, 3, '생활잡화'),
	(4, 4, '기타'),
	(5, 1, '도자기'),
	(5, 2, '수공예품'),
	(5, 3, '기타');
/*!40000 ALTER TABLE `detailcategory` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.payments 구조 내보내기
DROP TABLE IF EXISTS `payments`;
CREATE TABLE IF NOT EXISTS `payments` (
  `user_num` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL DEFAULT '',
  `user_phone` varchar(50) NOT NULL DEFAULT '',
  `user_zip` varchar(50) NOT NULL DEFAULT '',
  `user_addr` varchar(50) NOT NULL DEFAULT '',
  `user_detail_addr` varchar(50) NOT NULL DEFAULT '',
  `deliv_name` varchar(50) NOT NULL DEFAULT '',
  `deliv_phone` varchar(50) NOT NULL DEFAULT '',
  `deliv_zip` varchar(50) NOT NULL DEFAULT '',
  `deliv_addr` varchar(50) NOT NULL DEFAULT '',
  `deliv_detail_addr` varchar(50) NOT NULL DEFAULT '',
  `order_id` varchar(50) NOT NULL,
  `pay_method` varchar(50) NOT NULL,
  `pay_amount` int(11) NOT NULL DEFAULT 0,
  `pay_success` int(11) NOT NULL DEFAULT 0,
  `pay_date` varchar(50) DEFAULT NULL,
  UNIQUE KEY `order_id` (`order_id`),
  KEY `FK__user` (`user_num`),
  CONSTRAINT `FK__user` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.payments:~18 rows (대략적) 내보내기
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` (`user_num`, `user_name`, `user_phone`, `user_zip`, `user_addr`, `user_detail_addr`, `deliv_name`, `deliv_phone`, `deliv_zip`, `deliv_addr`, `deliv_detail_addr`, `order_id`, `pay_method`, `pay_amount`, `pay_success`, `pay_date`) VALUES
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-26-20221220161413', 'tossPay', 1000, 1, '20221220161413'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-28-20221220165840', 'tossPay', 36000, 1, '20221220165840'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-28-5-20221220175729', 'kakaoPay', 92000, 1, '20221220175729'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-34-20221220155917', 'tossPay', 30000, 1, '20221220155917'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-34-20221220171142', 'tossPay', 45000, 1, '20221220171142'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '배송지', '입니다', '1-37-20221220165439', 'tossPay', 7500, 1, '20221220165439'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-37-20221220165730', 'tossPay', 7500, 1, '20221220165730'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-37-20221220174538', 'tossPay', 7500, 1, '20221220174538'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '대구', '우리집', '1-37-20221220174852', 'kakaoPay', 5000, 1, '20221220174852'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '대구', '영진대', '1-37-20221220210906', 'kakaoPay', 5000, 1, '20221220210906'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-37-5-20221220175425', 'kakaoPay', 40000, 1, '20221220175425'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-39-20221220160158', 'tossPay', 23000, 1, '20221220160158'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-5-20221220174407', 'tossPay', 21000, 1, '20221220174407'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '대구광역시 달서구 대명천로 10길 62-5', '1층', '1-5-20221220175924', 'kakaoPay', 21000, 1, '20221220175924'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-5-28-20221220175131', 'kakaoPay', 50000, 1, '20221220175131'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-5-28-20221220175317', 'kakaoPay', 57000, 1, '20221220175317'),
	(1, '관리자', '01028287305', '12345', '주소', '상세주소', '관리자', '01028287305', '12345', '주소', '상세주소', '1-5-41-20221222121106', 'kakaoPay', 96000, 1, '20221222121106'),
	(2, '판매자', '01028287305', '12345', '주소', '상세주소', '판매자', '01028287305', '12345', '주소', '상세주소', '2-5-20221221140935', 'kakaoPay', 28000, 1, '20221221140935');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.product 구조 내보내기
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `prod_num` int(11) NOT NULL AUTO_INCREMENT,
  `user_num` int(11) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `prod_seller` varchar(20) NOT NULL,
  `prod_cate1` varchar(20) NOT NULL,
  `prod_cate2` varchar(20) NOT NULL,
  `prod_price` int(11) NOT NULL,
  `prod_count` int(11) NOT NULL,
  `prod_summary` varchar(210) DEFAULT NULL,
  `prod_grade` varchar(3) NOT NULL DEFAULT '0',
  `prod_content` varchar(5000) DEFAULT NULL,
  `prod_sellcount` int(11) NOT NULL DEFAULT 0,
  `prod_date` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`prod_num`),
  KEY `FK_product_user` (`user_num`),
  CONSTRAINT `FK_product_user` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.product:~47 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`prod_num`, `user_num`, `prod_name`, `prod_seller`, `prod_cate1`, `prod_cate2`, `prod_price`, `prod_count`, `prod_summary`, `prod_grade`, `prod_content`, `prod_sellcount`, `prod_date`) VALUES
	(1, 1, '만년 다이어리', '김정수', 'office', '1', 10000, 1000, '상품 크기 : 150 x 210mm, 케이스 크기 : 220 x 158 x 17mm, 상품 소재 : 종이, 폴리우레탄 원단, 상품 구성 : 총 160p', '0', '', 0, '2022-12-04'),
	(2, 1, '색동 노트 세트', '김정수', 'office', '1', 5000, 200, '상품 크기 : 115x180m, 상품 소재 : 종이, 상품 구성 : 1세트에 3매입', '0', '', 0, '2022-12-04'),
	(3, 1, '민화 미니 노트(무지)', '김정수', 'office', '1', 2000, 300, '상품 크기 : 130 x 180mm, 상품 소재 : 종이, 상품 구성 : 무지, 미니 노트 1개(3종 택1)', '0', '', 100, '2022-12-04'),
	(4, 1, '초충도 실크 양장수첩', '김정수', 'office', '1', 7500, 100, '상품 크기 : 135 x 190 x 21mm, 상품 구성 : 양장수첩 1개, 무선 노트', '0', '', 0, '2022-12-04'),
	(5, 1, '앎.참.얼.빛.삶 반양장 무지 노트', '김정수', 'office', '1', 7000, 483, '상품 크기 : 148x210x10mm, 무지, 약100매(200페이지), 상품 소재 : 종이, 상품 구성 : 노트 1개', '0', '', 1017, '2022-12-04'),
	(6, 1, '도자기 메모지(대)', '김정수', 'office', '1', 10000, 200, '상품 크기 : 10 x 10 x 5.3cm, 케이스 크기 : 5.3 x 5.3 x 10.8cm, 상품 소재 : 도자기,알루미늄,메모패드, 상품 구성 : 메모지, 케이스', '0', '', 0, '2022-12-04'),
	(7, 1, '수계도권 수첩', '김정수', 'office', '1', 4000, 200, '상품 크기 : 140×200mm, 약54p, 상품 소재 : 종이, 한지,노루지 봉투 패키지', '0', '', 0, '2022-12-04'),
	(8, 1, '특별한 양장수첩 (꽃과 나비)', '김정수', 'office', '1', 7000, 1000, '상품 무게 : 141g, 상품 소재 : 종이, 금속, 상품 크기 : 90*170mm(60매)', '0', '', 0, '2022-12-04'),
	(9, 1, '문화재기념볼펜(진주)', '김정수', 'office', '2', 5000, 300, '상품 크기 : 9x9x136mm, 0.7mm, 상품 소재 : 검정잉크 볼펜', '0', '', 600, '2022-12-04'),
	(10, 1, '문화재기념샤프(진주)', '김정수', 'office', '2', 5000, 300, '상품 크기 : 9x9x142mm, 0.5mm, 상품 소재 : 황동, 플라스틱', '0', '', 600, '2022-12-04'),
	(11, 1, '문화재기념볼펜(사진)', '김정수', 'office', '2', 5000, 300, '상품 크기 : 9x9x136mm, 0.7mm, 상품 소재 : 검정잉크 볼펜', '0', '', 400, '2022-12-04'),
	(12, 1, '문화재기념볼펜(아이콘)', '김정수', 'office', '2', 5000, 300, '상품 크기 : 9x9x136mm, 0.7mm, 상품 소재 : 검정잉크 볼펜', '0', '', 500, '2022-12-04'),
	(13, 1, '한글 흑목 연필 세트', '김정수', 'office', '2', 4000, 200, '1세트에 5개입', '0', '', 0, '2022-12-04'),
	(14, 1, '꽃과나비 종이필통', '김정수', 'office', '2', 7000, 300, '상품 무게 : 132g, 연필 지우개 세트, 재질: 종이, 자석, 나무, 흑연, 고무, 자석으로 간편하게 여닫을 수 있는 제품', '0', '', 300, '2022-12-04'),
	(15, 1, '나전종이필통', '김정수', 'office', '2', 7000, 400, '상품 무게 : 132g, 상품 크기 : 213*55*28mm / 종이, 나무, 흑연, 지우개, 자석', '0', '', 250, '2022-12-04'),
	(16, 1, '금동반가사유상 봉투세트(5입)', '김정수', 'office', '3', 5000, 100, '상품 크기 : 175 x 85 x 1mm, 상품 소재 : 종이(350g 크리에이티브 용지), 상품 구성 : 1세트에 봉투 5개', '0', '', 0, '2022-12-04'),
	(17, 1, '목안 봉투세트(2입)', '김정수', 'office', '3', 4000, 200, '상품 크기 : 175 x 85 x 1mm, 상품 소재 : 종이(350g 크리에이티브 용지), 상품 구성 : 1세트에 봉투 2개', '0', '', 0, '2022-12-04'),
	(18, 1, '선덕대왕신종 봉투', '김정수', 'office', '3', 2000, 300, '상품 크기 : 175 x 85 x 1mm, 상품 소재 : 종이(350g 크리에이티브 용지), 상품 구성 : 봉투 1개(단품)', '0', '', 0, '2022-12-04'),
	(19, 1, '얼굴무늬수막새 엽서', '김정수', 'office', '3', 1000, 300, '상품 크기 : 153 x 10 x 0.5mm, 상품 소재 : 종이(350g 크리에이티브 용지), 상품 구성 : 엽서 1장(단품)', '0', '', 0, '2022-12-04'),
	(20, 1, '사랑방 호랑이 입체카드(낮)', '김정수', 'office', '3', 10000, 500, '상품 크기 : 카드 135×135mm, 봉투 140×140mm, 상품 소재 : 종이', '0', '', 100, '2022-12-04'),
	(21, 1, '사랑방 호랑이 입체카드(밤)', '김정수', 'office', '3', 10000, 700, '상품 크기 : 카드 135×135mm, 봉투 140×140mm, 상품 소재 : 종이', '0', '', 150, '2022-12-04'),
	(22, 1, '나전칠기 마그넷', '김정수', 'office', '4', 10000, 300, '나전칠기 마그넷', '0', '', 50, '2022-12-04'),
	(23, 1, '초충도 책갈피', '김정수', 'office', '4', 3000, 300, '초충도 책갈피', '0', '', 0, '2022-12-04'),
	(24, 1, '민화마우스패드', '김정수', 'office', '4', 20000, 10, '민화마우스패드', '0', '', 710, '2022-12-04'),
	(25, 1, '우리놀이 북마크세트 (책갈피)', '김정수', 'office', '4', 9000, 300, '우리놀이 북마크세트 (책갈피)', '0', '', 0, '2022-12-04'),
	(26, 1, '오색 영롱 테이프 세트', '김정수', 'office', '4', 1000, 1000, '오색 영롱 테이프 세트 (3개입)', '0', '', 0, '2022-12-04'),
	(27, 1, '2023 국립박물관 달력', '김정수', 'office', '5', 15000, 1000, '상품 크기 : 260x210x80mm, 케이스 크기 : 230x280mm, 상품 소재 : 종이, 상품 구성 : 달력 1개', '0', '', 400, '2022-12-04'),
	(28, 1, '자개 스티커(신라금관 백색)', '김정수', 'office', '5', 18000, 0, '상품 크기 : 가로 32㎜×세로 70㎜×높이 0.5㎜, 케이스 크기 : 가로 115㎜×세로 135㎜×높이 3㎜, 상품 소재 : 천연자개(전복패,뉴질랜드패), 접착 필름 외, 상품 구성 : 자개스티커 1개', '0', '', 152, '2022-12-04'),
	(29, 1, '자개 스티커 신라의 미소(꽃수막새)', '김정수', 'office', '5', 15000, 100, '상품 크기 : 얼굴 : 가로22㎜×세로24㎜×높이0.5㎜ 꽃 : 가로 13㎜×세로13㎜×높이0.5㎜, 케이스 크기 : 가로 115㎜×세로 135㎜×높이 3㎜, 상품 소재 : 천연 자개(전복패), 접착 필름 외', '0', '', 150, '2022-12-04'),
	(30, 1, '자개 스티커(신라금관 금색)', '김정수', 'office', '5', 18000, 100, '상품 크기 : 가로 32㎜×세로 70㎜×높이 0.5㎜, 케이스 크기 : 가로 115㎜×세로 135㎜×높이 3㎜, 상품 소재 : 천연자개(전복패,뉴질랜드패), 접착 필름 외, 상품 구성 : 자개스티커 1개', '0', '', 100, '2022-12-04'),
	(31, 2, '매쉬 스카프', '김정수', 'fashion', '1', 30000, 100, '상품 크기: 50x180cm, 상품소재: 실크100%', '0', '', 100, '2022-12-04'),
	(32, 2, '사리외호 손수건(분홍색)', '김정수', 'fashion', '1', 15000, 200, '상품 무게 : 45g, 상품 크기 : 550x550mm, 케이스 크기 : 140x140x8mm, 상품 소재 : 면100%(80수)', '0', '', 0, '2022-12-04'),
	(33, 2, '사리외호 손수건(남색)', '김정수', 'fashion', '1', 15000, 300, '상품 무게 : 45g, 상품 크기 : 550x550mm, 케이스 크기 : 140x140x8mm, 상품 소재 : 면100%(80수)', '0', '', 100, '2022-12-04'),
	(34, 2, '조선시대 실경산수화 손수건', '김정수', 'fashion', '1', 15000, 392, '60x60cm, 면100%(80수)', '0', '', 8, '2022-12-04'),
	(35, 2, '화접도 실크스카프', '김정수', 'fashion', '1', 45000, 100, '상품 무게 : 82g, 상품 크기 : 53x180cm, 케이스 크기 : 24.5x22cm, 상품 소재 : 실크100%', '0', '', 0, '2022-12-04'),
	(36, 2, '맹호도 양말세트 (3p)', '김정수', 'fashion', '2', 16000, 1000, '상품 규격: 남녀공용 프리사이즈 230 - 275mm 상품재질: 코튼, 폴리에스터, 폴리우레탄 케이스크기: opp 봉투: 120 x 200 mm', '0', '', 0, '2022-12-04'),
	(37, 2, '천마도 양말', '김정수', 'fashion', '2', 2500, 3494, '상품 크기 : 여성용(230~250), 남성용(260~280), 케이스 크기 : 115x90x28m, 상품 구성 : 양말 1개(2종 택1), 상품 소재 : 면, 폴리에스터, 폴리우레탄', '0', '', 6, '2022-12-04'),
	(38, 2, '뮤지엄 티셔츠', '김정수', 'fashion', '2', 22000, 1000, '상품 크기 : 남녀공용 100호 (총장 72.5cm) 상품 옵션 : 연분홍색, 밤색 상품 소재 : 면100% (20수), 국내산 원사 상품 구성 : 티셔츠 1매, opp 비닐 제 조 국 : 대한민국', '0', '', 0, '2022-12-04'),
	(39, 2, '궁중잔치 이야기 천 가방(에코백)', '김정수', 'fashion', '3', 23000, 1000, '상품 크기 : 350x360mm, 상품 소재 :면', '0', '', 600, '2022-12-04'),
	(40, 2, '토우장식 항아리 패드 파우치', '김정수', 'fashion', '3', 36000, 1000, '토우장식 항아리 패드 파우치', '0', '', 0, '2022-12-04'),
	(41, 2, '일월오봉도 천가방(무지개)', '김정수', 'fashion', '3', 25000, 997, '상품크기: 360 mm x 360mm , 상품소재: 면 100% (10수2합), 상품 무게 : 190g', '0', '', 503, '2022-12-04'),
	(42, 2, '반가사유상 카드지갑', '김정수', 'fashion', '3', 18000, 250, '상품 크기 : (카드 케이스)105 x 72mm, (키링) 20 x 45mm, 상품 소재 : 이태리 베지터블 천연가죽, 금속, 상품 구성 : 카드지갑, 키링, 패키지, 상품 설명서', '0', '', 0, '2022-12-04'),
	(43, 2, '일월오봉도 천가방(골드)', '김정수', 'fashion', '3', 25000, 1000, '상품 크기 : 360x360mm, 상품 소재 : 면100%', '0', '', 0, '2022-12-04'),
	(44, 2, '별헤는 밤 카드지갑', '김정수', 'fashion', '3', 12000, 1500, '상품 무게 : 66g, 상품 크기 : 8.3x10.2cm, 케이스 크기 : 10x15x2cm, 상품 소재 : 인조가죽+폴리에스테르, 상품 구성 : 카드지갑, 목걸이 줄, 플라스틱 케이스', '0', '', 0, '2022-12-04'),
	(48, 1, '스마트 에디터 테스트', '네이버', 'craft', '3', 10000000, 1, '사랑해요 네이버!', '0', '<p><span style="font-size: 24pt;">﻿돋움 볼드 24pt</span></p><p><span style="font-size: 24pt;"><b><br></b></span></p><p style="text-align: center; " align="center"><span style="font-size: 12pt; font-family: 바탕, Batang, AppleMyungjo;"><u>​바탕 밑줄 12pt 중앙정렬</u></span>&nbsp;</p><p style="text-align: center; " align="center"><span style="font-size: 12pt; font-family: 바탕, Batang, AppleMyungjo;"><u><br></u></span></p><p style="text-align: left;" align="left"><span style="font-family: 바탕, Batang, AppleMyungjo; color: rgb(255, 170, 0);"><span style="font-size: 16px; background-color: rgb(225, 225, 225); color: rgb(255, 0, 0); font-family: 굴림, Gulim;"><u>빨간글씨 회색배경</u></span></span></p><p style="text-align: left;" align="left"><span style="font-family: 바탕, Batang, AppleMyungjo; color: rgb(255, 170, 0);"><span style="font-size: 16px; background-color: rgb(225, 225, 225); color: rgb(255, 0, 0); font-family: 굴림, Gulim;"><u><br></u></span></span></p><p style="text-align: left;" align="left"><span style="font-family: 바탕, Batang, AppleMyungjo; color: rgb(255, 170, 0);"><span style="font-size: 16px; background-color: rgb(225, 225, 225); color: rgb(255, 0, 0); font-family: 굴림, Gulim;"><u>수정도 한번에 되나요assdasad</u></span></span></p>', 0, '2022-12-22');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.productimage 구조 내보내기
DROP TABLE IF EXISTS `productimage`;
CREATE TABLE IF NOT EXISTS `productimage` (
  `prod_num` int(11) NOT NULL,
  `prod_image0` varchar(100) NOT NULL,
  `prod_thumb_200` varchar(100) NOT NULL,
  `prod_thumb_80` varchar(100) NOT NULL,
  `prod_image1` varchar(100) DEFAULT NULL,
  `prod_image2` varchar(100) DEFAULT NULL,
  `prod_image3` varchar(100) DEFAULT NULL,
  `prod_image4` varchar(100) DEFAULT NULL,
  `prod_image5` varchar(100) DEFAULT NULL,
  KEY `FK_Product_TO_ProductImage_1` (`prod_num`),
  CONSTRAINT `FK_Product_TO_ProductImage_1` FOREIGN KEY (`prod_num`) REFERENCES `product` (`prod_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.productimage:~45 rows (대략적) 내보내기
/*!40000 ALTER TABLE `productimage` DISABLE KEYS */;
INSERT INTO `productimage` (`prod_num`, `prod_image0`, `prod_thumb_200`, `prod_thumb_80`, `prod_image1`, `prod_image2`, `prod_image3`, `prod_image4`, `prod_image5`) VALUES
	(1, '만년 다이어리.jpg', 'thumb_200_만년 다이어리.jpg', 'thumb_80_만년 다이어리.jpg', '만년 다이어리3.jpg', '만년 다이어리2.jpg', NULL, NULL, NULL),
	(2, '색동 노트 세트.jpg', 'thumb_200_색동 노트 세트.jpg', 'thumb_80_색동 노트 세트.jpg', '색동 노트 세트3.jpg', '색동 노트 세트2.jpg', NULL, NULL, NULL),
	(3, '민화 미니 노트.jpg', 'thumb_200_민화 미니 노트.jpg', 'thumb_80_민화 미니 노트.jpg', NULL, NULL, NULL, NULL, NULL),
	(4, '초충도 실크 양장수첩.jpg', 'thumb_200_초충도 실크 양장수첩.jpg', 'thumb_80_초충도 실크 양장수첩.jpg', NULL, NULL, NULL, NULL, NULL),
	(5, '앎.참.얼.빛.삶 반양장 무지 노트3.jpg', 'thumb_200_앎.참.얼.빛.삶 반양장 무지 노트3.jpg', 'thumb_80_앎.참.얼.빛.삶 반양장 무지 노트3.jpg', '앎.참.얼.빛.삶 반양장 무지 노트.jpg', '앎.참.얼.빛.삶 반양장 무지 노트2.jpg', NULL, NULL, NULL),
	(6, '도자기 메모지(대).jpg', 'thumb_200_도자기 메모지(대).jpg', 'thumb_80_도자기 메모지(대).jpg', NULL, NULL, NULL, NULL, NULL),
	(7, '수계도권 수첩.jpg', 'thumb_200_수계도권 수첩.jpg', 'thumb_80_수계도권 수첩.jpg', NULL, NULL, NULL, NULL, NULL),
	(8, '특별한 양장수첩 (꽃과나비).jpg', 'thumb_200_특별한 양장수첩 (꽃과나비).jpg', 'thumb_80_특별한 양장수첩 (꽃과나비).jpg', '특별한 양장수첩 (꽃과나비)2.jpg', NULL, NULL, NULL, NULL),
	(9, '문화재기념볼펜(진주).jpg', 'thumb_200_문화재기념볼펜(진주).jpg', 'thumb_80_문화재기념볼펜(진주).jpg', '문화재기념볼펜(진주)2.jpg', NULL, NULL, NULL, NULL),
	(10, '문화재기념샤프(진주).jpg', 'thumb_200_문화재기념샤프(진주).jpg', 'thumb_80_문화재기념샤프(진주).jpg', NULL, NULL, NULL, NULL, NULL),
	(11, '문화재기념볼펜(사진)1.jpg', 'thumb_200_문화재기념볼펜(사진)1.jpg', 'thumb_80_문화재기념볼펜(사진)1.jpg', NULL, NULL, NULL, NULL, NULL),
	(12, '문화재기념볼펜(아이콘).jpg', 'thumb_200_문화재기념볼펜(아이콘).jpg', 'thumb_80_문화재기념볼펜(아이콘).jpg', NULL, NULL, NULL, NULL, NULL),
	(13, '한글 흑목 연필 세트.jpg', 'thumb_200_한글 흑목 연필 세트.jpg', 'thumb_80_한글 흑목 연필 세트.jpg', NULL, NULL, NULL, NULL, NULL),
	(14, '꽃과나비 종이필통.jpg', 'thumb_200_꽃과나비 종이필통.jpg', 'thumb_80_꽃과나비 종이필통.jpg', '꽃과나비 종이필통2.jpg', NULL, NULL, NULL, NULL),
	(15, '나전종이필통.jpg', 'thumb_200_나전종이필통.jpg', 'thumb_80_나전종이필통.jpg', '나전종이필통2.jpg', NULL, NULL, NULL, NULL),
	(16, '금동반가사유상 봉투세트.jpg', 'thumb_200_금동반가사유상 봉투세트.jpg', 'thumb_80_금동반가사유상 봉투세트.jpg', '금동반가사유상 봉투세트2.jpg', NULL, NULL, NULL, NULL),
	(17, '목안 봉투세트(2입).jpg', 'thumb_200_목안 봉투세트(2입).jpg', 'thumb_80_목안 봉투세트(2입).jpg', NULL, NULL, NULL, NULL, NULL),
	(18, '선덕대왕신종 봉투.jpg', 'thumb_200_선덕대왕신종 봉투.jpg', 'thumb_80_선덕대왕신종 봉투.jpg', NULL, NULL, NULL, NULL, NULL),
	(19, '얼굴무늬수막새 엽서.jpg', 'thumb_200_얼굴무늬수막새 엽서.jpg', 'thumb_80_얼굴무늬수막새 엽서.jpg', NULL, NULL, NULL, NULL, NULL),
	(20, '사랑방 호랑이 입체카드 (낮).jpg', 'thumb_200_사랑방 호랑이 입체카드 (낮).jpg', 'thumb_80_사랑방 호랑이 입체카드 (낮).jpg', '사랑방 호랑이 입체카드 (낮)2.jpg', NULL, NULL, NULL, NULL),
	(21, '사랑방 호랑이 입체카드 (밤).jpg', 'thumb_200_사랑방 호랑이 입체카드 (밤).jpg', 'thumb_80_사랑방 호랑이 입체카드 (밤).jpg', NULL, NULL, NULL, NULL, NULL),
	(22, '나전칠기 마그넷.jpg', 'thumb_200_나전칠기 마그넷.jpg', 'thumb_80_나전칠기 마그넷.jpg', NULL, NULL, NULL, NULL, NULL),
	(23, '초충도 책갈피.jpg', 'thumb_200_초충도 책갈피.jpg', 'thumb_80_초충도 책갈피.jpg', NULL, NULL, NULL, NULL, NULL),
	(24, '민화마우스패드.jpg', 'thumb_200_민화마우스패드.jpg', 'thumb_80_민화마우스패드.jpg', NULL, NULL, NULL, NULL, NULL),
	(25, '우리놀이 북마크세트 (책갈피).jpg', 'thumb_200_우리놀이 북마크세트 (책갈피).jpg', 'thumb_80_우리놀이 북마크세트 (책갈피).jpg', NULL, NULL, NULL, NULL, NULL),
	(26, '오색 영롱 테이프 세트.jpg', 'thumb_200_오색 영롱 테이프 세트.jpg', 'thumb_80_오색 영롱 테이프 세트.jpg', '오색 영롱 테이프 세트2.jpg', NULL, NULL, NULL, NULL),
	(27, '2023 국립박물관 달력.jpg', 'thumb_200_2023 국립박물관 달력.jpg', 'thumb_80_2023 국립박물관 달력.jpg', '2023 국립박물관 달력2.jpg', NULL, NULL, NULL, NULL),
	(28, '자개 스티커(신라금관 백색).jpg', 'thumb_200_자개 스티커(신라금관 백색).jpg', 'thumb_80_자개 스티커(신라금관 백색).jpg', '자개 스티커(신라금관 백색)2.jpg', NULL, NULL, NULL, NULL),
	(29, '자개 스티커 신라의 미소(꽃수막새).jpg', 'thumb_200_자개 스티커 신라의 미소(꽃수막새).jpg', 'thumb_80_자개 스티커 신라의 미소(꽃수막새).jpg', '자개 스티커 신라의 미소(꽃수막새)2.jpg', NULL, NULL, NULL, NULL),
	(30, '자개 스티커(신라금관 금색).jpg', 'thumb_200_자개 스티커(신라금관 금색).jpg', 'thumb_80_자개 스티커(신라금관 금색).jpg', NULL, NULL, NULL, NULL, NULL),
	(31, '매쉬 스카프.jpg', 'thumb_200_매쉬 스카프.jpg', 'thumb_80_매쉬 스카프.jpg', '매쉬 스카프3.jpg', '매쉬 스카프2.jpg', NULL, NULL, NULL),
	(32, '사리외호 손수건(분홍색).jpg', 'thumb_200_사리외호 손수건(분홍색).jpg', 'thumb_80_사리외호 손수건(분홍색).jpg', NULL, NULL, NULL, NULL, NULL),
	(33, '사리외호 손수건(남색).jpg', 'thumb_200_사리외호 손수건(남색).jpg', 'thumb_80_사리외호 손수건(남색).jpg', NULL, NULL, NULL, NULL, NULL),
	(34, '조선시대 실경산수화 손수건.jpg', 'thumb_200_조선시대 실경산수화 손수건.jpg', 'thumb_80_조선시대 실경산수화 손수건.jpg', '조선시대 실경산수화 손수건2.jpg', NULL, NULL, NULL, NULL),
	(35, '화접도 실크스카프.jpg', 'thumb_200_화접도 실크스카프.jpg', 'thumb_80_화접도 실크스카프.jpg', '화접도 실크스카프2.jpg', NULL, NULL, NULL, NULL),
	(36, '맹호도 양말세트 (3p).jpg', 'thumb_200_맹호도 양말세트 (3p).jpg', 'thumb_80_맹호도 양말세트 (3p).jpg', NULL, NULL, NULL, NULL, NULL),
	(37, '천마도 양말.jpg', 'thumb_200_천마도 양말.jpg', 'thumb_80_천마도 양말.jpg', '천마도 양말2.jpg', NULL, NULL, NULL, NULL),
	(38, '뮤지엄 티셔츠.jpg', 'thumb_200_뮤지엄 티셔츠.jpg', 'thumb_80_뮤지엄 티셔츠.jpg', NULL, NULL, NULL, NULL, NULL),
	(39, '궁중잔치 이야기 천가방(에코백).jpg', 'thumb_200_궁중잔치 이야기 천가방(에코백).jpg', 'thumb_80_궁중잔치 이야기 천가방(에코백).jpg', '궁중잔치 이야기 천가방(에코백)2.jpg', NULL, NULL, NULL, NULL),
	(40, '토우장식 항아리 패드 파우치.jpg', 'thumb_200_토우장식 항아리 패드 파우치.jpg', 'thumb_80_토우장식 항아리 패드 파우치.jpg', '토우장식 항아리 패드 파우치2.jpg', NULL, NULL, NULL, NULL),
	(41, '일월오봉도 천가방(무지개).jpg', 'thumb_200_일월오봉도 천가방(무지개).jpg', 'thumb_80_일월오봉도 천가방(무지개).jpg', '일월오봉도 천가방(무지개)2.jpg', NULL, NULL, NULL, NULL),
	(42, '반가사유상 카드지갑.jpg', 'thumb_200_반가사유상 카드지갑.jpg', 'thumb_80_반가사유상 카드지갑.jpg', '반가사유상 카드지갑2.jpg', NULL, NULL, NULL, NULL),
	(43, '일월오봉도 천가방(골드).jpg', 'thumb_200_일월오봉도 천가방(골드).jpg', 'thumb_80_일월오봉도 천가방(골드).jpg', NULL, NULL, NULL, NULL, NULL),
	(44, '별헤는 밤 카드지갑.jpg', 'thumb_200_별헤는 밤 카드지갑.jpg', 'thumb_80_별헤는 밤 카드지갑.jpg', '별헤는 밤 카드지갑2.jpg', NULL, NULL, NULL, NULL),
	(48, 'NAVER.png', 'thumb_200_NAVER.png', 'thumb_80_NAVER.png', NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `productimage` ENABLE KEYS */;

-- 테이블 kjs67_hanmall_db.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_num` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `user_pw` varchar(100) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_mail` varchar(100) DEFAULT NULL,
  `user_zip` varchar(10) DEFAULT NULL,
  `user_addr` varchar(200) DEFAULT NULL,
  `user_detail_addr` varchar(200) DEFAULT NULL,
  `user_date` date NOT NULL DEFAULT current_timestamp(),
  `user_class` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_num`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 kjs67_hanmall_db.user:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_num`, `user_id`, `user_pw`, `user_name`, `user_phone`, `user_mail`, `user_zip`, `user_addr`, `user_detail_addr`, `user_date`, `user_class`) VALUES
	(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '관리자', '01028287305', 'ique45@naver.com', '12345', '주소', '상세주소', '2022-12-03', 9),
	(2, 'seller', 'a4279eae47aaa7417da62434795a011ccb0ec870f7f56646d181b5500a892a9a', '판매자', '01028287305', 'ique45@gmail.com', '12345', '주소', '상세주소', '2022-12-03', 2),
	(3, 'user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', '일반회원', '01028287305', 'ique45@g.yju.ac.kr', '12345', '주소', '상세주소', '2022-12-03', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
