package com.ufm.library.ufmlibrary.faker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.ufmlibrary.entity.Book;
import com.ufm.library.ufmlibrary.entity.BookPhoto;
import com.ufm.library.ufmlibrary.entity.LocationBook;
import com.ufm.library.ufmlibrary.entity.key.LocationBookKey;
import com.ufm.library.ufmlibrary.repository.AuthorRepository;
import com.ufm.library.ufmlibrary.repository.BookPhotoRepository;
import com.ufm.library.ufmlibrary.repository.BookRepository;
import com.ufm.library.ufmlibrary.repository.LocationBookRepository;
import com.ufm.library.ufmlibrary.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookFaker {

	private final Faker faker;
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final BookPhotoRepository bookPhotoRepository;
	private final LocationBookRepository locationBookRepository;
	private final LocationRepository locationRepository;

	public void fake() {
		nguyenNhatAnhBook();
		rosieNguyenBook();
		yuvalNoahHarariBook();
		fakeLocationBook();
	}

	private void nguyenNhatAnhBook() {
		var nguyenNhatAnhAuthor = authorRepository.getReferenceById(9L);
		// mat biec -----------------------------------------------------------------

		var matBiecBook = new Book(null, "Mắt biếc", 234,
				"Mắt Biếc kể về cuộc đời của nhân vật chính tên Ngạn. Ngạn sinh ra và lớn lên ở một ngôi làng tên là Đo Đo. Lớn lên cùng với Ngạn là cô bạn hàng xóm có đôi mắt tuyệt đẹp tên là Hà Lan. Tuổi thơ của Ngạn và Hà Lan gắn bó với bao nhiêu kỉ niệm cùng đồi sim, đánh trống trường... Tình bạn trẻ thơ dần dần biến thành tình yêu thầm lặng của Ngạn dành cho Hà Lan. Đến khi lớn hơn một chút, cả hai phải rời làng ra thành phố để tiếp tục học. Khi tấm lòng của Ngạn luôn hướng về Hà Lan và về làng, thì Hà Lan không cưỡng lại được cám dỗ của cuộc sống xa hoa nơi đô thị và ngã vào vòng tay của Dũng.",
				null, nguyenNhatAnhAuthor, null, null);

		var matBiecPhotos = new ArrayList<BookPhoto>();
		matBiecPhotos
				.add(new BookPhoto(null,
						"https://upload.wikimedia.org/wikipedia/vi/9/92/Mat_Biec.gif", matBiecBook));
		matBiecPhotos
				.add(new BookPhoto(null,
						"https://isach.info/images/story/cover/mat_biec__nguyen_nhat_anh.jpg",
						matBiecBook));
		matBiecPhotos.add(
				new BookPhoto(null,
						"https://bloganchoi.com/wp-content/uploads/2019/08/bia-sach-mat-biec-1.jpg",
						matBiecBook));

		bookRepository.save(matBiecBook);
		bookPhotoRepository.saveAll(matBiecPhotos);

		// cho toi mot ve di tuoi tho ---------------------------------------------

		var choToiMotVeDiTuoiThoBook = new Book(null, "Cho Tôi Xin Một Vé Đi Tuổi Thơ", 218,
				"\"Cho Tôi Xin Một Vé Đi Tuổi Thơ\" là tập truyện dài đã vô cùng quen thuộc với nhiều người của nhà văn nổi tiếng Nguyễn Nhật Ánh. Bởi tính nhân văn sâu sắc trong nó, đây được coi là một trong những sáng tác thành công nhất của ông và giúp ông giành được Giải thưởng Văn học ASEAN năm 2010.",
				null, nguyenNhatAnhAuthor, null, null);
		bookRepository.save(choToiMotVeDiTuoiThoBook);

		var choToiMotVeDiTuoiThoPhotos = new ArrayList<BookPhoto>();
		choToiMotVeDiTuoiThoPhotos.add(
				new BookPhoto(null,
						"https://www.nxbtre.com.vn/Images/Book/nxbtre_full_22142021_051437.jpg",
						choToiMotVeDiTuoiThoBook));
		choToiMotVeDiTuoiThoPhotos
				.add(new BookPhoto(null,
						"https://upload.wikimedia.org/wikipedia/vi/thumb/c/c9/Cho_t%C3%B4i_xin_m%E1%BB%99t_v%C3%A9_%C4%91i_tu%E1%BB%95i_th%C6%A1.jpg/220px-Cho_t%C3%B4i_xin_m%E1%BB%99t_v%C3%A9_%C4%91i_tu%E1%BB%95i_th%C6%A1.jpg",
						choToiMotVeDiTuoiThoBook));
		bookPhotoRepository.saveAll(choToiMotVeDiTuoiThoPhotos);

		// toi thay hoa vang tren co xanh

		var toiThayHoaVangTrenCoXanhBook = new Book(null, "Tôi thấy hoa vàng trên cỏ xanh", 378,
				"Đây là một trong các truyện dài của Nguyễn Nhật Ánh, ra đời sau Đảo mộng mơ và trước Lá nằm trong lá. Tác phẩm như một tập nhật ký xoay quanh cuộc sống của những đứa trẻ ở một vùng quê Việt Nam nghèo khó, nổi bật lên là thông điệp về tình anh em, tình làng nghĩa xóm và những tâm tư của tuổi mới lớn.",
				null, nguyenNhatAnhAuthor, null, null);
		bookRepository.save(toiThayHoaVangTrenCoXanhBook);

		var toiThayHoaVangTrenCoXanhPhotos = new ArrayList<BookPhoto>();
		toiThayHoaVangTrenCoXanhPhotos.add(
				new BookPhoto(null,
						"https://upload.wikimedia.org/wikipedia/vi/thumb/3/3d/T%C3%B4i_th%E1%BA%A5y_hoa_v%C3%A0ng_tr%C3%AAn_c%E1%BB%8F_xanh.jpg/330px-T%C3%B4i_th%E1%BA%A5y_hoa_v%C3%A0ng_tr%C3%AAn_c%E1%BB%8F_xanh.jpg",
						toiThayHoaVangTrenCoXanhBook));
		toiThayHoaVangTrenCoXanhPhotos
				.add(new BookPhoto(null,
						"https://salt.tikicdn.com/cache/w1200/media/catalog/product/t/o/toi_thay_hoa_vang.jpg",
						toiThayHoaVangTrenCoXanhBook));
		bookPhotoRepository.saveAll(toiThayHoaVangTrenCoXanhPhotos);

		// co hai con meo ben cua so
		var coHaiConMeoBenCuaSoBook = new Book(null, "Có hai con mèo ngồi bên cửa sổ", 210,
				"Cuốn sách giúp ta thấy tình yêu có thể làm nên những điều tuyệt diều, có thể thay đổi những điều tưởng chừng là không thể. Đọc xong “Hai con mèo ngồi bên cửa sổ” không chỉ thấy tình yêu mà còn có một thứ gì đó nhẹ nhàng, man mát và bình yên ở trong lòng - nhẹ và mát như âm thanh của một chiếc lông rơi xuống thềm nhà",
				null, nguyenNhatAnhAuthor, null, null);
		bookRepository.save(coHaiConMeoBenCuaSoBook);

		var coHaiConMeoBenCuaSoPhotos = new ArrayList<BookPhoto>();
		coHaiConMeoBenCuaSoPhotos.add(
				new BookPhoto(null, "https://images.thuvienpdf.com/004/r3KxA0.webp", coHaiConMeoBenCuaSoBook));
		coHaiConMeoBenCuaSoPhotos
				.add(new BookPhoto(null, "https://www.nxbtre.com.vn/Images/Book/nxbtre_full_07292022_022951.jpg",
						coHaiConMeoBenCuaSoBook));
		coHaiConMeoBenCuaSoPhotos
				.add(new BookPhoto(null,
						"https://salt.tikicdn.com/cache/w1200/media/catalog/product/c/o/co-hai-con-meo-ngoi-ben-cua-so.jpg",
						coHaiConMeoBenCuaSoBook));
		bookPhotoRepository.saveAll(coHaiConMeoBenCuaSoPhotos);
	}

	private void rosieNguyenBook() {
		var rosieNguyenAuthor = authorRepository.getReferenceById(8L);

		// tuoiTreDangGiaBaoNhieu---------------------------------------------------
		var tuoiTreDangGiaBaoNhieuBook = new Book(null, "Tuổi trẻ đáng giá bao nhiêu?", 285,
				"“Tuổi trẻ đáng giá bao nhiêu?” chia sẻ những câu chuyện quen thuộc mà ai cũng trải qua thời trẻ, như một bài học người đi trước dành cho giới trẻ. Tác giả Rosie Nguyễn kể về những sự việc thực tế mình trải qua và đưa ra những quan điểm cá nhân về nhiều vấn đề, đặc biệt là về “sách”.",
				null, rosieNguyenAuthor, null, null);
		bookRepository.save(tuoiTreDangGiaBaoNhieuBook);

		var tuoiTreDangGiaBaoNhieuPhoto = new ArrayList<BookPhoto>();
		tuoiTreDangGiaBaoNhieuPhoto.add(new BookPhoto(null,
				"https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG",
				tuoiTreDangGiaBaoNhieuBook));
		tuoiTreDangGiaBaoNhieuPhoto.add(new BookPhoto(null,
				"https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png",
				tuoiTreDangGiaBaoNhieuBook));
		tuoiTreDangGiaBaoNhieuPhoto
				.add(new BookPhoto(null, "https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg",
						tuoiTreDangGiaBaoNhieuBook));
		bookPhotoRepository.saveAll(tuoiTreDangGiaBaoNhieuPhoto);

		// Ta ba lô trên đất Á------------------------------------

		var taBaLoTrenDatABook = new Book(null, "Ta ba lô trên đất Á", 412,
				"Quyển sách đầu tiên của Rosie Nguyễn, nay trở lại với một diện mạo mới và một quốc gia mới mà trước đây tác giả chưa có dịp nhắc đến. Ta ba lô trên đất Á không chỉ là cẩm nang du lịch bụi dành cho những ai yêu thích khám phá Đông Nam Á, mà còn là dấu ấn rất riêng của Rosie Nguyễn khi một mình đeo ba lô, tay cầm bản đồ ngược xuôi khắp các nước láng giềng để đi tìm chính mình và theo đuổi đam mê.",
				null, rosieNguyenAuthor, null, null);
		bookRepository.save(taBaLoTrenDatABook);
		var taBaLoTrenDatAPhoto = new ArrayList<BookPhoto>();
		taBaLoTrenDatAPhoto.add(new BookPhoto(null,
				"https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG",
				taBaLoTrenDatABook));
		taBaLoTrenDatAPhoto.add(new BookPhoto(null,
				"https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png",
				taBaLoTrenDatABook));
		taBaLoTrenDatAPhoto.add(new BookPhoto(null, "https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg",
				taBaLoTrenDatABook));

		bookPhotoRepository.saveAll(taBaLoTrenDatAPhoto);
	}

	private void yuvalNoahHarariBook() {
		var yuvalNoahHarariAuthor = authorRepository.getReferenceById(1L);

		// sapiens luoc su loai nguoi---------------------------------------------------

		var sapiensBook = new Book(null, "Sapiens: Lược Sử Loài Người", 554,
				"Sapiens, đưa chúng ta vào một chuyến đi kinh ngạc qua toàn bộ lịch sử loài người, từ những gốc rễ tiến hóa của nó đến thời đại của chủ nghĩa tư bản và kỹ thuật di truyền, để khám phá tại sao chúng ta đang trong những điều kiện sinh sống hiện tại..",
				null, yuvalNoahHarariAuthor, null, null);
		bookRepository.save(sapiensBook);

		var sapiensPhoto = new ArrayList<BookPhoto>();
		sapiensPhoto.add(new BookPhoto(null,
				"https://omegaplus.vn/wp-content/uploads/2018/07/sapiens-luoc-su-loai-nguoi.jpg", sapiensBook));
		sapiensPhoto.add(new BookPhoto(null, "https://cdn-amz.woka.io/images/I/81nQ+oGgI3L.jpg", sapiensBook));
		sapiensPhoto.add(new BookPhoto(null,
				"https://www.sachkhaitri.com/Data/Sites/2/Product/49562/sapiens-luoc-su-loai-nguoi-4.jpg",
				sapiensBook));
		bookPhotoRepository.saveAll(sapiensPhoto);

		// homo deus ------------------------------------

		var homoDeusBook = new Book(null, "Homo Deus: Lược Sử Tương Lai", 512,
				"Homo sapiens có phải là một dạng sống siêu đẳng, hay chỉ là một tay đầu gấu địa phương? Làm thế nào con người lại tin rằng họ không chỉ đã kiểm soát thế giới, mà còn mang lại ý nghĩa cho nó? Công nghệ sinh học và trí thông minh nhân tạo đe doạ loài người ra sao? Sinh vật nào có thể kế thừa loài người, và tôn giáo mới nào sẽ được sản sinh?",
				null, yuvalNoahHarariAuthor, null, null);
		bookRepository.save(homoDeusBook);

		var homoDeusPhotos = new ArrayList<BookPhoto>();
		homoDeusPhotos.add(new BookPhoto(null,
				"https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG", homoDeusBook));
		homoDeusPhotos.add(new BookPhoto(null,
				"https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png",
				homoDeusBook));
		homoDeusPhotos
				.add(new BookPhoto(null, "https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg",
						homoDeusBook));

		bookPhotoRepository.saveAll(homoDeusPhotos);

		// 21 bài học của thế kỷ 21-------------------------------------------

		var baiHocTheKy21Book = new Book(null, "21 bài học của thế kỷ 21", 432,
				"Máy tính và robot đã thay đổi ý nghĩa cuộc sống của chúng ta như thế nào? Làm thế nào để đối phó với những tin tức giả mạo? Xung đột sắc tộc và tôn giáo đến khi nào mới kết thúc? Chúng ta cần phải dạy gì cho lũ trẻ?",
				null, yuvalNoahHarariAuthor, null, null);
		bookRepository.save(baiHocTheKy21Book);
		var baiHocTheKy21Photos = new ArrayList<BookPhoto>();
		baiHocTheKy21Photos.add(new BookPhoto(null,
				"https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG",
				baiHocTheKy21Book));
		baiHocTheKy21Photos.add(new BookPhoto(null,
				"https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png",
				baiHocTheKy21Book));
		baiHocTheKy21Photos.add(new BookPhoto(null, "https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg",
				baiHocTheKy21Book));
		bookPhotoRepository.saveAll(baiHocTheKy21Photos);

	}

	private void fakeLocationBook() {
		var locations = List.of(
				locationRepository.getReferenceById(1L),
				locationRepository.getReferenceById(2L),
				locationRepository.getReferenceById(3L));
		var books = bookRepository.findAll();
		books.forEach(book -> {
			locations.forEach(location -> {
				var quantity = faker.number().numberBetween(10, 25);
				var onLoaning = faker.number().numberBetween(1, quantity);
				var locationBook = new LocationBook(
						new LocationBookKey(),
						quantity,
						onLoaning,
						book,
						location,
						null);
				locationBookRepository.save(locationBook);
			});
		});
	}

}
