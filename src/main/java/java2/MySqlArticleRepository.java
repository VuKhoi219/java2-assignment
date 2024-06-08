package java2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MySqlArticleRepository implements ArticleRepository {
    private final String MYSQL_CONNECTION = "jdbc:mysql://localhost:3306/article";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    @Override
    public void deleteByUrl(String url) {
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            Article article = new Article();
            String sql = "Update new_articles set status = -1, deleted_at = ? where url = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, article.getDeleteAt().toString());
            ps.setString(2, url);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Article> findAll() {
        ArrayList<Article> arrayList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String sql = "Select * from new_articles";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getDouble("id"));
                article.setBaseUrl(rs.getString("base_url"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setContent(rs.getString("content"));
                article.setThumbnail(rs.getString("thumbnail"));
                article.setCreatedAt(rs.getString("created_at"));
                article.setUpdateAt(LocalDate.parse(rs.getString("updated_at")));
                article.setUpdateAt(LocalDate.parse(rs.getString("deleted_at")));
                article.setStatus(rs.getInt("status"));
                arrayList.add(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

    @Override
    public Article findByUrl(String url) {
        Article article = null;
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String sql = "select * from new_articles where base_url = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, url);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article();
                article.setBaseUrl(rs.getString("base_url"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setContent(rs.getString("content"));
                article.setThumbnail(rs.getString("thumbnail"));
                article.setCreatedAt(rs.getString("created_at"));
                article.setUpdateAt(LocalDate.parse(rs.getString("updated_at")));
                article.setDeleteAt(LocalDate.parse(rs.getString("deleted_at")));
                article.setStatus(rs.getInt("status"));
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public Article save(Article article) {
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String sql = "INSERT INTO new_articles (base_url, title, description, content,thumbnail,created_at,status) VALUES (?, ?, ?, ?, ? , ? ,1)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, article.getBaseUrl());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getDescription());
            ps.setString(4, article.getContent());
            ps.setString(5, article.getThumbnail());
            ps.setString(6, article.getCreatedAt());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public Article update(Article article) {
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            article = new Article();
            String sql = "Update new_articles set title = ?, description = ?, content = ?,thumbnail = ?,created_at = ?,update_at = ? where url = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getDescription());
            ps.setString(3, article.getContent());
            ps.setString(4, article.getThumbnail());
            ps.setString(5, article.getCreatedAt());
            ps.setString(6, article.getUpdateAt().toString());
            ps.setString(6, article.getBaseUrl());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }
}
