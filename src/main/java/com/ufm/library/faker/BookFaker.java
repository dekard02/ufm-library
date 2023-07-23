package com.ufm.library.faker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.BookPhoto;
import com.ufm.library.entity.Category;
import com.ufm.library.entity.LocationBook;
import com.ufm.library.repository.AuthorRepository;
import com.ufm.library.repository.BookPhotoRepository;
import com.ufm.library.repository.BookRepository;
import com.ufm.library.repository.CategoryRepository;
import com.ufm.library.repository.LocationBookRepository;
import com.ufm.library.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookFaker {

    private final Faker faker;
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final BookPhotoRepository bookPhotoRepo;
    private final LocationBookRepository locationBookRepo;
    private final LocationRepository locationRepo;
    private final CategoryRepository categoryRepo;

    public void fake() {
        nguyenNhatAnhBook();
        rosieNguyenBook();
        yuvalNoahHarariBook();
        fakeLocationBook();
    }

    private void nguyenNhatAnhBook() {
        var nguyenNhatAnhAuthor = authorRepo.getReferenceById(9L);
        // mat biec -----------------------------------------------------------------

        var matBiecBook = Book.builder()
                .title("Mắt biếc")
                .totalPages(234)
                .categories(new ArrayList<Category>())
                .summary(
                        "Mắt Biếc kể về cuộc đời của nhân vật chính tên Ngạn. Ngạn sinh ra và lớn lên ở một ngôi làng tên là Đo Đo. Lớn lên cùng với Ngạn là cô bạn hàng xóm có đôi mắt tuyệt đẹp tên là Hà Lan. Tuổi thơ của Ngạn và Hà Lan gắn bó với bao nhiêu kỉ niệm cùng đồi sim, đánh trống trường... Tình bạn trẻ thơ dần dần biến thành tình yêu thầm lặng của Ngạn dành cho Hà Lan. Đến khi lớn hơn một chút, cả hai phải rời làng ra thành phố để tiếp tục học. Khi tấm lòng của Ngạn luôn hướng về Hà Lan và về làng, thì Hà Lan không cưỡng lại được cám dỗ của cuộc sống xa hoa nơi đô thị và ngã vào vòng tay của Dũng.")
                .author(nguyenNhatAnhAuthor).build();

        matBiecBook.getCategories().add(categoryRepo.getReferenceById(1L));
        matBiecBook.getCategories().add(categoryRepo.getReferenceById(3L));
        matBiecBook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(matBiecBook);

        var matBiecPhotos = new ArrayList<BookPhoto>();
        matBiecPhotos.add(BookPhoto.builder()
                .directory("https://upload.wikimedia.org/wikipedia/vi/9/92/Mat_Biec.gif")
                .book(matBiecBook)
                .build());
        matBiecPhotos.add(BookPhoto.builder()
                .directory("https://isach.info/images/story/cover/mat_biec__nguyen_nhat_anh.jpg")
                .book(matBiecBook)
                .build());

        matBiecPhotos.add(BookPhoto.builder()
                .directory("https://bloganchoi.com/wp-content/uploads/2019/08/bia-sach-mat-biec-1.jpg")
                .book(matBiecBook)
                .build());

        bookPhotoRepo.saveAll(matBiecPhotos);

        // cho toi mot ve di tuoi tho ---------------------------------------------

        var choToiMotVeDiTuoiThoBook = Book.builder()
                .title("Cho Tôi Xin Một Vé Đi Tuổi Thơ")
                .totalPages(218)
                .summary(
                        "\"Cho Tôi Xin Một Vé Đi Tuổi Thơ\" là tập truyện dài đã vô cùng quen thuộc với nhiều người của nhà văn nổi tiếng Nguyễn Nhật Ánh. Bởi tính nhân văn sâu sắc trong nó, đây được coi là một trong những sáng tác thành công nhất của ông và giúp ông giành được Giải thưởng Văn học ASEAN năm 2010.")
                .categories(new ArrayList<Category>())
                .author(nguyenNhatAnhAuthor).build();

        choToiMotVeDiTuoiThoBook.getCategories().add(categoryRepo.getReferenceById(1L));
        choToiMotVeDiTuoiThoBook.getCategories().add(categoryRepo.getReferenceById(3L));
        choToiMotVeDiTuoiThoBook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(choToiMotVeDiTuoiThoBook);

        var choToiMotVeDiTuoiThoPhotos = new ArrayList<BookPhoto>();
        choToiMotVeDiTuoiThoPhotos.add(BookPhoto.builder()
                .directory("https://www.nxbtre.com.vn/Images/Book/nxbtre_full_22142021_051437.jpg")
                .book(choToiMotVeDiTuoiThoBook)
                .build());
        choToiMotVeDiTuoiThoPhotos.add(BookPhoto.builder()
                .directory(
                        "https://upload.wikimedia.org/wikipedia/vi/thumb/c/c9/Cho_t%C3%B4i_xin_m%E1%BB%99t_v%C3%A9_%C4%91i_tu%E1%BB%95i_th%C6%A1.jpg/220px-Cho_t%C3%B4i_xin_m%E1%BB%99t_v%C3%A9_%C4%91i_tu%E1%BB%95i_th%C6%A1.jpg")
                .book(choToiMotVeDiTuoiThoBook)
                .build());

        bookPhotoRepo.saveAll(choToiMotVeDiTuoiThoPhotos);

        // toi thay hoa vang tren co xanh
        var toiThayHoaVangTrenCoXanhBook = Book.builder()
                .title("Tôi thấy hoa vàng trên cỏ xanh")
                .totalPages(378)
                .summary(
                        "Đây là một trong các truyện dài của Nguyễn Nhật Ánh, ra đời sau Đảo mộng mơ và trước Lá nằm trong lá. Tác phẩm như một tập nhật ký xoay quanh cuộc sống của những đứa trẻ ở một vùng quê Việt Nam nghèo khó, nổi bật lên là thông điệp về tình anh em, tình làng nghĩa xóm và những tâm tư của tuổi mới lớn.")
                .categories(new ArrayList<Category>())
                .author(nguyenNhatAnhAuthor)
                .build();

        toiThayHoaVangTrenCoXanhBook.getCategories().add(categoryRepo.getReferenceById(1L));
        toiThayHoaVangTrenCoXanhBook.getCategories().add(categoryRepo.getReferenceById(3L));
        toiThayHoaVangTrenCoXanhBook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(toiThayHoaVangTrenCoXanhBook);

        var toiThayHoaVangTrenCoXanhPhotos = new ArrayList<BookPhoto>();
        toiThayHoaVangTrenCoXanhPhotos.add(BookPhoto.builder()
                .directory(
                        "https://upload.wikimedia.org/wikipedia/vi/thumb/3/3d/T%C3%B4i_th%E1%BA%A5y_hoa_v%C3%A0ng_tr%C3%AAn_c%E1%BB%8F_xanh.jpg/330px-T%C3%B4i_th%E1%BA%A5y_hoa_v%C3%A0ng_tr%C3%AAn_c%E1%BB%8F_xanh.jpg")
                .book(toiThayHoaVangTrenCoXanhBook)
                .build());

        toiThayHoaVangTrenCoXanhPhotos.add(BookPhoto.builder()
                .directory(
                        "https://salt.tikicdn.com/cache/w1200/media/catalog/product/t/o/toi_thay_hoa_vang.jpg")
                .book(toiThayHoaVangTrenCoXanhBook)
                .build());
        bookPhotoRepo.saveAll(toiThayHoaVangTrenCoXanhPhotos);

        // co hai con meo ben cua so
        var coHaiConMeoBenCuaSoBook = Book.builder()
                .title("Có hai con mèo ngồi bên cửa sổ")
                .totalPages(210)
                .summary(
                        "Cuốn sách giúp ta thấy tình yêu có thể làm nên những điều tuyệt diều, có thể thay đổi những điều tưởng chừng là không thể. Đọc xong “Hai con mèo ngồi bên cửa sổ” không chỉ thấy tình yêu mà còn có một thứ gì đó nhẹ nhàng, man mát và bình yên ở trong lòng - nhẹ và mát như âm thanh của một chiếc lông rơi xuống thềm nhà")
                .author(nguyenNhatAnhAuthor)
                .categories(new ArrayList<Category>())
                .build();

        coHaiConMeoBenCuaSoBook.getCategories().add(categoryRepo.getReferenceById(1L));
        coHaiConMeoBenCuaSoBook.getCategories().add(categoryRepo.getReferenceById(3L));
        coHaiConMeoBenCuaSoBook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(coHaiConMeoBenCuaSoBook);

        var coHaiConMeoBenCuaSoPhotos = new ArrayList<BookPhoto>();
        coHaiConMeoBenCuaSoPhotos.add(BookPhoto.builder()
                .directory("https://images.thuvienpdf.com/004/r3KxA0.webp")
                .book(coHaiConMeoBenCuaSoBook)
                .build());
        coHaiConMeoBenCuaSoPhotos.add(BookPhoto.builder()
                .directory("https://www.nxbtre.com.vn/Images/Book/nxbtre_full_07292022_022951.jpg")
                .book(coHaiConMeoBenCuaSoBook)
                .build());
        coHaiConMeoBenCuaSoPhotos.add(BookPhoto.builder()
                .directory(
                        "https://salt.tikicdn.com/cache/w1200/media/catalog/product/c/o/co-hai-con-meo-ngoi-ben-cua-so.jpg")
                .book(coHaiConMeoBenCuaSoBook)
                .build());
        bookPhotoRepo.saveAll(coHaiConMeoBenCuaSoPhotos);
    }

    private void rosieNguyenBook() {
        var rosieNguyenAuthor = authorRepo.getReferenceById(8L);

        // tuoiTreDangGiaBaoNhieu---------------------------------------------------
        var tuoiTreDangGiaBaoNhieuBook = Book.builder()
                .title("Tuổi trẻ đáng giá bao nhiêu?")
                .totalPages(285)
                .summary(
                        "\"Tuổi trẻ đáng giá bao nhiêu?\" chia sẻ những câu chuyện quen thuộc mà ai cũng trải qua thời trẻ, như một bài học người đi trước dành cho giới trẻ. Tác giả Rosie Nguyễn kể về những sự việc thực tế mình trải qua và đưa ra những quan điểm cá nhân về nhiều vấn đề, đặc biệt là về\"sách\".")
                .categories(new ArrayList<Category>())
                .author(rosieNguyenAuthor)
                .build();

        tuoiTreDangGiaBaoNhieuBook.getCategories().add(categoryRepo.getReferenceById(1L));
        tuoiTreDangGiaBaoNhieuBook.getCategories().add(categoryRepo.getReferenceById(3L));
        tuoiTreDangGiaBaoNhieuBook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(tuoiTreDangGiaBaoNhieuBook);

        var tuoiTreDangGiaBaoNhieuPhoto = new ArrayList<BookPhoto>();
        tuoiTreDangGiaBaoNhieuPhoto.add(BookPhoto.builder()
                .directory(
                        "https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG")
                .book(tuoiTreDangGiaBaoNhieuBook)
                .build());
        tuoiTreDangGiaBaoNhieuPhoto.add(BookPhoto.builder()
                .directory(
                        "https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png")
                .book(tuoiTreDangGiaBaoNhieuBook)
                .build());
        tuoiTreDangGiaBaoNhieuPhoto.add(BookPhoto.builder()
                .directory("https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg")
                .book(tuoiTreDangGiaBaoNhieuBook)
                .build());
        bookPhotoRepo.saveAll(tuoiTreDangGiaBaoNhieuPhoto);

        // Ta ba lô trên đất Á------------------------------------
        var taBaLoTrenDatABook = Book.builder()
                .title("Ta ba lô trên đất Á")
                .totalPages(412)
                .summary(
                        "Quyển sách đầu tiên của Rosie Nguyễn, nay trở lại với một diện mạo mới và một quốc gia mới mà trước đây tác giả chưa có dịp nhắc đến. Ta ba lô trên đất Á không chỉ là cẩm nang du lịch bụi dành cho những ai yêu thích khám phá Đông Nam Á, mà còn là dấu ấn rất riêng của Rosie Nguyễn khi một mình đeo ba lô, tay cầm bản đồ ngược xuôi khắp các nước láng giềng để đi tìm chính mình và theo đuổi đam mê.")
                .categories(new ArrayList<Category>())
                .author(rosieNguyenAuthor)
                .build();

        taBaLoTrenDatABook.getCategories().add(categoryRepo.getReferenceById(1L));
        taBaLoTrenDatABook.getCategories().add(categoryRepo.getReferenceById(3L));
        taBaLoTrenDatABook.getCategories().add(categoryRepo.getReferenceById(7L));

        bookRepo.save(taBaLoTrenDatABook);

        var taBaLoTrenDatAPhoto = new ArrayList<BookPhoto>();
        taBaLoTrenDatAPhoto.add(BookPhoto.builder()
                .directory(
                        "https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG")
                .book(taBaLoTrenDatABook)
                .build());
        taBaLoTrenDatAPhoto.add(BookPhoto.builder()
                .directory(
                        "https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png")
                .book(taBaLoTrenDatABook)
                .build());
        taBaLoTrenDatAPhoto.add(BookPhoto.builder()
                .directory("https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg")
                .book(taBaLoTrenDatABook)
                .build());
        bookPhotoRepo.saveAll(taBaLoTrenDatAPhoto);
    }

    private void yuvalNoahHarariBook() {
        var yuvalNoahHarariAuthor = authorRepo.getReferenceById(1L);

        // sapiens luoc su loai nguoi---------------------------------------------------
        var sapiensBook = Book.builder()
                .title("Sapiens: Lược Sử Loài Người")
                .totalPages(554)
                .summary(
                        "Sapiens, đưa chúng ta vào một chuyến đi kinh ngạc qua toàn bộ lịch sử loài người, từ những gốc rễ tiến hóa của nó đến thời đại của chủ nghĩa tư bản và kỹ thuật di truyền, để khám phá tại sao chúng ta đang trong những điều kiện sinh sống hiện tại..")
                .categories(new ArrayList<Category>())
                .author(yuvalNoahHarariAuthor)
                .build();

        sapiensBook.getCategories().add(categoryRepo.getReferenceById(1L));
        sapiensBook.getCategories().add(categoryRepo.getReferenceById(4L));
        sapiensBook.getCategories().add(categoryRepo.getReferenceById(13L));

        bookRepo.save(sapiensBook);

        var sapiensPhoto = new ArrayList<BookPhoto>();
        sapiensPhoto.add(BookPhoto.builder()
                .directory("https://omegaplus.vn/wp-content/uploads/2018/07/sapiens-luoc-su-loai-nguoi.jpg")
                .book(sapiensBook)
                .build());
        sapiensPhoto.add(BookPhoto.builder()
                .directory("https://cdn-amz.woka.io/images/I/81nQ+oGgI3L.jpg")
                .book(sapiensBook)
                .build());
        sapiensPhoto.add(BookPhoto.builder()
                .directory(
                        "https://www.sachkhaitri.com/Data/Sites/2/Product/49562/sapiens-luoc-su-loai-nguoi-4.jpg")
                .book(sapiensBook)
                .build());
        bookPhotoRepo.saveAll(sapiensPhoto);

        // homo deus ------------------------------------
        var homoDeusBook = Book.builder()
                .title("Homo Deus: Lược Sử Tương Lai")
                .totalPages(512)
                .summary(
                        "Homo sapiens có phải là một dạng sống siêu đẳng, hay chỉ là một tay đầu gấu địa phương? Làm thế nào con người lại tin rằng họ không chỉ đã kiểm soát thế giới, mà còn mang lại ý nghĩa cho nó? Công nghệ sinh học và trí thông minh nhân tạo đe doạ loài người ra sao? Sinh vật nào có thể kế thừa loài người, và tôn giáo mới nào sẽ được sản sinh?")
                .categories(new ArrayList<Category>())
                .author(yuvalNoahHarariAuthor)
                .build();

        homoDeusBook.getCategories().add(categoryRepo.getReferenceById(1L));
        homoDeusBook.getCategories().add(categoryRepo.getReferenceById(4L));
        homoDeusBook.getCategories().add(categoryRepo.getReferenceById(13L));

        bookRepo.save(homoDeusBook);

        var homoDeusPhotos = new ArrayList<BookPhoto>();
        homoDeusPhotos.add(BookPhoto.builder()
                .directory(
                        "https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG")
                .book(homoDeusBook)
                .build());
        homoDeusPhotos.add(BookPhoto.builder()
                .directory(
                        "https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png")
                .book(homoDeusBook)
                .build());
        homoDeusPhotos.add(BookPhoto.builder()
                .directory("https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg")
                .book(homoDeusBook)
                .build());
        bookPhotoRepo.saveAll(homoDeusPhotos);

        // 21 bài học của thế kỷ 21-------------------------------------------
        var baiHocTheKy21Book = Book.builder()
                .title("21 bài học của thế kỷ 21")
                .totalPages(432)
                .summary(
                        "Máy tính và robot đã thay đổi ý nghĩa cuộc sống của chúng ta như thế nào? Làm thế nào để đối phó với những tin tức giả mạo? Xung đột sắc tộc và tôn giáo đến khi nào mới kết thúc? Chúng ta cần phải dạy gì cho lũ trẻ?")
                .categories(new ArrayList<Category>())
                .author(yuvalNoahHarariAuthor)
                .build();

        baiHocTheKy21Book.getCategories().add(categoryRepo.getReferenceById(1L));
        baiHocTheKy21Book.getCategories().add(categoryRepo.getReferenceById(4L));
        baiHocTheKy21Book.getCategories().add(categoryRepo.getReferenceById(13L));

        bookRepo.save(baiHocTheKy21Book);

        var baiHocTheKy21Photos = new ArrayList<BookPhoto>();
        baiHocTheKy21Photos.add(BookPhoto.builder()
                .directory(
                        "https://sachhay24h.com/uploads/images/2020/T5/471/tuoi-tre-dang-gia-bao-nhieu-1.PNG")
                .book(baiHocTheKy21Book)
                .build());
        baiHocTheKy21Photos.add(BookPhoto.builder()
                .directory(
                        "https://revisach.com/wp-content/uploads/2020/06/review-sach-tuoi-tre-dang-gia-bao-nhieu-rosie-nguyen-3.png")
                .book(baiHocTheKy21Book)
                .build());
        baiHocTheKy21Photos.add(BookPhoto.builder()
                .directory("https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg")
                .book(baiHocTheKy21Book)
                .build());
        bookPhotoRepo.saveAll(baiHocTheKy21Photos);

    }

    private void fakeLocationBook() {
        var locations = List.of(
                locationRepo.getReferenceById(1L),
                locationRepo.getReferenceById(2L),
                locationRepo.getReferenceById(3L));
        var books = bookRepo.findAll();
        books.forEach(book -> {
            locations.forEach(location -> {
                var quantity = faker.number().numberBetween(10, 25);
                var onLoaning = faker.number().numberBetween(1, quantity);
                var locationBook = LocationBook.builder()
                        .quantity(quantity)
                        .booksOnLoan(onLoaning)
                        .book(book)
                        .location(location)
                        .build();
                locationBookRepo.save(locationBook);
            });
        });
    }

}
