package org.example.icebraking.repository;

//import lombok.RequiredArgsConstructor;
//import org.example.icebraking.domain.User;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;

//@Repository
//@RequiredArgsConstructor
//public class DbUserRepository implements UserRepository{
//
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Override
//    public void save(User user) {
//        String sql = "INSERT INTO users (user_id, password, name, department, student_id) " +
//                "VALUES (:userId, :password, :name, :department, :studentId)";
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("userId", user.getUserId())
//                .addValue("password", user.getPassword())
//                .addValue("name", user.getName())
//                .addValue("department", user.getDepartment())
//                .addValue("studentId", user.getStudentId());
//        jdbcTemplate.update(sql, params);
//    }
//}
