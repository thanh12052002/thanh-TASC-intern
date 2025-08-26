package java_core_basic.string;

/*
Giả sử ta thiết kế web quản lý sản phẩm sportswear
Tạo lớp searchRequest gồm tên , thể loại đại diện cho data người dùng tìm kiếm từ client truyền vào (có thể null nếu người dùng không nhập).
Từ thông tin trên ta cần query db lấy ra danh sách sản phẩm thỏa mãn tương ứng
--> Câu query đầy đủ là:
SELECT b.* 
FROM sportswear b 
INNER JOIN sportswear_category 
    ON b.sportswear_category_id = sportswear_category.id
WHERE 1 = 1 
  AND b.name LIKE '%áo thể thao%'  (giả sử client tìm áo thể thao)
  AND sportswear_category.name LIKE '%Nike%'  (giả sử client tìm loại là Nike)
  AND b.status = 1 
ORDER BY RAND();
 */

/*
 Sau đây ta sẽ dùng StringBuilder để tách query để xử lý nếu người dùng không nhập lựa chọn 
 Hay để câu query có thể tái sử dụng gọn gàng hơn
 */
class SearchRequest {
    // class mô phỏng request client truyền đến be
    private String key; // key nhập của người dùng như tên sản phẩm
    private String categoryName; // đại diện thông tin loại sản phẩm
    // constructor

    public SearchRequest(String key, String categoryName) {
        this.key = key;
        this.categoryName = categoryName;
    }

    // Getter
    public String getKey() {
        return key;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

// tạo lớp ultils để kiểm tra data client truyền có null (không nhập gì ở fe hay
// không)
class StringUtils {
    public static boolean check(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

// tạo class xử lý SQL với StringBuilder
class SQLBuilder {
    public static void joinTable(SearchRequest searchRequest, StringBuilder sql) {
        String category = searchRequest.getCategoryName();
        if (StringUtils.check(category)) {
            sql.append(" INNER JOIN sportswear_category ON b.sportswear_category_id = sportswear_category.id ");
        }
    }

    public static void querySpecial(SearchRequest searchRequest, StringBuilder where) {
        String category = searchRequest.getCategoryName();
        if (StringUtils.check(category)) {
            where.append(" AND sportswear_category.name LIKE '%")
                    .append(category)
                    .append("%' ");
        }
    }

    public static void queryNormal(SearchRequest searchRequest, StringBuilder where) {
        String name = searchRequest.getKey();
        if (StringUtils.check(name)) {
            where.append(" AND b.name LIKE '%")
                    .append(name)
                    .append("%' ");
        }
    }

    public static String buildSQL(SearchRequest searchRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM sportswear b");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");

        joinTable(searchRequest, sql);
        queryNormal(searchRequest, where);
        querySpecial(searchRequest, where);

        where.append(" AND b.status = 1");
        where.append(" ORDER BY RAND();");

        sql.append(where);
        return sql.toString();
    }
}

public class DynamicSQL_StringBuilder {
    public static void main(String[] args) {
        // Tao object chứa thông tin client gửi đến BE
        SearchRequest searchRequest = new SearchRequest("Ao the thao", "Nike");
        // xử lý đưa ra câu truy vấn SQL
        String finalSQL = SQLBuilder.buildSQL(searchRequest);
        System.out.println("Cau lech SQL dc tao");
        System.out.println(finalSQL);
    }
}
